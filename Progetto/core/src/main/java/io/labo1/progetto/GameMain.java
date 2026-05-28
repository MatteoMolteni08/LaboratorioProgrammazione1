package io.labo1.progetto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Array; // Cosigliato dall'AI per la gestione di più piattaforme sospese
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import java.util.ArrayList;
import java.util.Random;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameMain extends ApplicationAdapter {
    private Player player;
    private SpriteBatch batch;
    private ArrayList<Texture> playerTexture;
    private Animation<Texture> defaultAnimation;
    private ArrayList<Texture> playerTextureRun;
    private Animation<Texture> runAnimation;
    private ArrayList<Texture> playerTextureDeath;
    private Animation<Texture> deathAnimation;
    private ArrayList<Texture> playerTextureJump;
    private Animation<Texture> jumpAnimation;
    private ShapeRenderer sr;
    private BitmapFont scoreFont;
    FreeTypeFontGenerator gen;
    FreeTypeFontParameter param = new FreeTypeFontParameter();
    private boolean isGrounded;

    // Variabili di controllo
    private Animation<Texture> currentAnimation; // Punta all'animazione attiva ora
    private float stateTime = 0f;

    // Definiamo gli stati possibili del personaggio
    public enum State { IDLE, RUNNING, JUMPING, DEAD }
    private State currentState = State.IDLE;

    private String path;
    private float gravity;
    private int screenWidth;
    private int screenHeight;

    private Rectangle playerBounds;
    private Rectangle groundBounds;
    private Array<Platform> platforms;

    private float dt;
    private float vel;
    private boolean isJumping;
    private int maxJumpHeight;
    private float lastPlatformTouchedHeight;
    private int score;

    private Texture baguetteTexture;
    private Rectangle baguetteBounds;

    private Music bgMusic;
    private Sound jumpSound;
    private Sound bonkSound;
    private Sound eating;
    private Texture background;
    private Baguette baguette;
    private Random rand;
    private int baguetteIndex;
    private String gameStat;
    private Texture bgMenu;
    private boolean flipX;

    @Override
    public void create() {
        rand = new Random();
        batch = new SpriteBatch();
        sr = new ShapeRenderer();

        path = "teto/";
        playerTexture = new ArrayList<>();
        for (int x = 1; x < 25; x++) {
            playerTexture.add(new Texture(path + "default_pose" + x + ".png"));
        }
        defaultAnimation = new Animation<>(0.15f, playerTexture.toArray(new Texture[0]));
        playerTextureRun = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playerTextureRun.add(new Texture(path+ "run" + (i + 1)+".png"));
        }
        runAnimation = new Animation<>(0.1f, playerTextureRun.toArray(new Texture[0]));
        playerTextureDeath= new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playerTextureDeath.add(new Texture(path+ "death" + (i + 1)+".png"));
        }
        deathAnimation = new Animation<>(0.16f, playerTextureDeath.toArray(new Texture[0]));
        playerTextureJump= new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            playerTextureJump.add(new Texture(path+ "jump" + (i + 1)+".png"));
        }
        jumpAnimation = new Animation<>(0.15f, playerTextureJump.toArray(new Texture[0]));

        // Imposta i loop dove serve
        defaultAnimation.setPlayMode(Animation.PlayMode.LOOP);
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);
        jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);  // Salto e morte di solito
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL); // si riproducono una volta sola

        // All'inizio il personaggio è fermo
        currentAnimation = defaultAnimation;

        scoreFont = new BitmapFont();
        scoreFont.setColor(Color.BLACK);
        scoreFont.getData().setScale(1.8f);
        gen = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/DS-DIGIB.TTF"));
        param.size = 32;
        scoreFont = gen.generateFont(param);

        player = new Player(90f, 100f, 100, 220, 220);


        baguetteTexture = new Texture("baguette.png");
        baguette = new Baguette(1000f, 90f, 50f);
        background = new Texture("bg.jpg");

        gravity = -250f;
        baguetteIndex = 0;
        score = 0;

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        playerBounds = new Rectangle(player.toRectangle());
        groundBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),90);
        baguetteBounds = new Rectangle(baguette.getPosPos().get(baguetteIndex)[0],baguette.getPosPos().get(baguetteIndex)[1], 40,40);

        isJumping = false;
        maxJumpHeight = 160;
        platforms = new Array<Platform>();

        // Aggiungi le tue piattaforme (x, y, larghezza, altezza)
        platforms.add(new Platform(400, 180, 150, 20));
        platforms.add(new Platform(650, 280, 200, 20));
        platforms.add(new Platform(100, 320, 180, 20));
        platforms.add(new Platform(370, 400, 200, 20));
        platforms.add(new Platform(600, 440, 450, 20));
        platforms.add(new Platform(10, 520, 350, 20));
        platforms.add(new Platform(550, 90, 200, 60));

        // Caricamento diretto nel metodo Create()
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/teto-territory-8-BITS.mp3"));
        // Configurazione
        bgMusic.setVolume(0.5f); // 0.0 → 1.0
        bgMusic.setLooping(true); // true = riparte automaticamente

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/jump.wav"));
        bonkSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bonk.wav"));
        eating = Gdx.audio.newSound(Gdx.files.internal("Sounds/nom-nom.wav"));

        gameStat = "menu";
        bgMenu = new Texture("teto_wallpaper.jpg");
        flipX = false; // false = guarda a destra, true = guarda a sinistra
        isGrounded = true;   // Il personaggio è a terra?
    }

    @Override
    public void render() {
        dt = Gdx.graphics.getDeltaTime();
        if (gameStat == "menu"){
            menu();
        } else if (gameStat == "play") {
            gameLevel();
        }
    }

    public void menu(){
        ScreenUtils.clear(0f, 0f, 0f, 0f);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            gameStat = "play";
            bgMusic.play();
        }

        batch.begin();
        batch.setColor(1f,1f,1f,0.5f);
        batch.draw(bgMenu, -10, 0);
        batch.setColor(1f,1f,1f,1f);
        batch.end();
    }

    public void gameLevel(){
        vel = player.speed * dt;

        // Logica di cambio stato (Esempio con tastiera)
        State newState = currentState;



        // 3. Aggiorna il tempo e disegna
        stateTime += Gdx.graphics.getDeltaTime();
        Texture currentFrame = currentAnimation.getKeyFrame(stateTime);

        // Calcola la velocità orizzontale desiderata in questo frame
        float moveX = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveX = -vel;
            flipX = true;
            if (!isJumping){
                newState = State.RUNNING;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveX = vel;
            flipX = false;
            if (!isJumping) {
                newState = State.RUNNING;
            }
        }
        if (!isJumping && moveX ==0){
            newState=State.IDLE;
        }
        // Calcola la velocità verticale desiderata in questo frame
        float moveY = 0;
        if (isJumping) {
            if (player.getY() < maxJumpHeight + lastPlatformTouchedHeight) {
                moveY = player.getJump() * dt;
            } else {
                isJumping = false;
            }
        } else {
            // Applica gravità (normale o potenziata dallo schianto)
            moveY = gravity * dt;
        }

        // ==========================================
        // FASE 1: MOVIMENTO E COLLISIONE ORIZZONTALE (X)
        // ==========================================
        player.setX(player.getX() + moveX);
        // Impedisci di uscire dai bordi dello schermo
        if (player.getX() < 0) player.setX(0);
        if (player.getX() + 51 > screenWidth) player.setX(screenWidth - player.getWidth());

        // Aggiorna la X della hitbox per il controllo laterale
        playerBounds.x = player.getX();

        // Controlla la collisione laterale con OGNI piattaforma
        for (Platform p : platforms) {
            // Creiamo al volo un rettangolo temporaneo per la piattaforma corrente
            Rectangle pBounds = new Rectangle(p.toRectangle());

            if (playerBounds.overlaps(pBounds)) {
                // Se andavi a destra, hai colpito il muro sinistro della piattaforma
                if (moveX > 0) {
                    player.setX(p.getX() - playerBounds.width);
                }
                // Se andavi a sinistra, hai colpito il muro destro della piattaforma
                else if (moveX < 0) {
                    player.setX(p.getX() + p.getWidth());
                }
                // Sincronizza subito i bounds dopo il blocco laterale
                playerBounds.x = player.getX();
            }
        }

        // ==========================================
        // FASE 2: MOVIMENTO E COLLISIONE VERTICALE (Y)
        // ==========================================
        player.setY(player.getY() + moveY);
        playerBounds.y = player.getY();

        // Collisione con il terreno fisso
        if (playerBounds.overlaps(groundBounds)) {
            player.setY(90f);
            lastPlatformTouchedHeight = 90f;
            isJumping = false;
            playerBounds.y = player.getY();

            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                isJumping = true;
                jumpSound.play(1.0f);
                newState = State.JUMPING;
            }
        }

        // Controlla la collisione verticale con OGNI piattaforma
        for (Platform p : platforms) {
            Rectangle pBounds = new Rectangle(p.toRectangle());

            if (playerBounds.overlaps(pBounds)) {
                // Caso A: Teto sta cadendo ed entra dall'alto (Atterraggio)
                if (moveY < 0 && player.getY() >= p.getY() + p.getHeight() - 8f) {
                    player.setY(p.getY() + p.getHeight());
                    lastPlatformTouchedHeight = p.getY() + p.getHeight();
                    isJumping = false;
                    playerBounds.y = player.getY();

                    if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                        isJumping = true;
                        jumpSound.play(1.0f);
                    }
                }
                // Caso B: Teto sta saltando e picchia la testa sotto la piattaforma
                else if (moveY > 0) {
                    bonkSound.play();
                    player.setY(p.getY() - playerBounds.height);
                    isJumping = false; // Interrompe il salto e la fa iniziare a cadere
                    playerBounds.y = player.getY();
                }
            }
        }

        //Se la baguette viene toccata si sposta e vengono aggiunti 5 punti allo score
        if (playerBounds.overlaps(baguetteBounds)) {
            int num;
            eating.play();
            do {
                num = rand.nextInt(0, baguette.getPosPos().size());
            }while (num == baguetteIndex);
            score+=5;
            baguetteIndex = num;
            // 1. Prendi l'array {X, Y} corrispondente all'indice
            int[] coordinate = baguette.getPosPos().get(baguetteIndex);

            // 2. Assegna i valori singolarmente
            baguetteBounds.x = coordinate[0];
            baguetteBounds.y = coordinate[1];
        }
        // Se lo stato è cambiato, cambia animazione e resetta il tempo
        if (currentState != newState) {
            currentState = newState;
            stateTime = 0f; // Reset fondamentale per far partire l'animazione da capo!

            switch (currentState) {
                case IDLE:    currentAnimation = defaultAnimation; break;
                case RUNNING: currentAnimation = runAnimation;     break;
                case JUMPING: currentAnimation = jumpAnimation;    break;
                case DEAD:    currentAnimation = deathAnimation;   break;
            }
        }

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = currentAnimation.getKeyFrame(stateTime);

        // 4. RENDERING GRAFICO
        ScreenUtils.clear(0.53f, 0.81f, 0.99f, 1f);
        batch.begin();
        batch.draw(background, 0, -50, screenWidth, 810);
        batch.end();

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(78f/ 255f, 235f/ 255f, 179f/ 255f, 1f);
        sr.rect(0, 0, Gdx.graphics.getWidth(), 90);
        sr.setColor(142f/ 255f, 142f/ 255f, 142f/ 255f, 1f);
        for (Platform p : platforms) {
            sr.rect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        }
        sr.end();


        batch.begin();
        batch.draw(baguetteTexture, baguette.getPosPos().get(baguetteIndex)[0], baguette.getPosPos().get(baguetteIndex)[1], 40, 40);
        batch.draw(
            currentFrame,
            player.getX(), player.getY(),
            currentFrame.getWidth(), currentFrame.getHeight(),
            0, 0,
            currentFrame.getWidth(), currentFrame.getHeight(),
            flipX, false // Passiamo flipX qui per girarlo orizzontalmente
        );
        if (score < 10){
            scoreFont.draw(batch, "Score: 000" + score, screenWidth - 200, screenHeight - 20);
        }else if (score < 100) {
            scoreFont.draw(batch, "Score: 00" + score, screenWidth - 200, screenHeight - 20);
        }else if (score < 1000){
            scoreFont.draw(batch, "Score: 0" + score, screenWidth - 200, screenHeight - 20);

        } else if (score < 10000) {
            scoreFont.draw(batch, "Score: " + score, screenWidth - 200, screenHeight - 20);
        }else {
            scoreFont.draw(batch, "Score: 9999", screenWidth - 200, screenHeight - 20);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Texture t: playerTexture){
            t.dispose();
        }
        for (Texture t: playerTextureDeath){
            t.dispose();
        }
        for (Texture t: playerTextureRun){
            t.dispose();
        }
        for (Texture t: playerTextureJump){
            t.dispose();
        }
        baguetteTexture.dispose();
        background.dispose();
        sr.dispose();
        scoreFont.dispose();
        bgMusic.dispose();
        bonkSound.dispose();
        jumpSound.dispose();
        eating.dispose();
        gen.dispose();
        bgMenu.dispose();
    }
}
