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

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;


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

    private Controller gamepad;

    private ShapeRenderer sr;
    private BitmapFont scoreFont;
    FreeTypeFontGenerator gen;
    FreeTypeFontParameter param = new FreeTypeFontParameter();

    FreeTypeFontGenerator tutorialGen;
    FreeTypeFontParameter tutorialParam;
    private BitmapFont tutorialFont;

    private FreeTypeFontGenerator titleGen;
    private FreeTypeFontParameter titleParam;
    private BitmapFont titleFont;

    // Variabili di controllo
    private Animation<Texture> currentAnimation; // Punta all'animazione attiva ora
    private float stateTime = 0f;

    private int highScore;

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
    private Sound deadSOund;
    private Texture background;
    private Baguette baguette;
    private Random rand;
    private int baguetteIndex;
    private String gameStat;
    private Texture bgMenu;
    private Texture titleLogo;
    private boolean flipX;
    private float gameTimer; // Il tempo totale della partita in secondi

    @Override
    public void create() {
        rand = new Random();
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        // --- CONTROLLO JOYSTICK ALL'AVVIO ---
        try {
            gamepad = Controllers.getControllers().first();
        } catch (Exception e) {
            gamepad = null;
            Gdx.app.error("GAMEPAD_CRASH", "Errore critico durante il caricamento del controller. Il gioco passerà automaticamente alla sola tastiera.");
            // Stampa la traccia dell'errore nella console di IntelliJ senza interrompere il gioco
            e.printStackTrace();
        }

        path = "teto/";
        playerTexture = new ArrayList<>();
        for (int x = 1; x < 25; x++) {
            playerTexture.add(new Texture(path + "default_pose" + x + ".png"));
        }
        defaultAnimation = new Animation<>(0.15f, playerTexture.toArray(new Texture[0]));

        playerTextureRun = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playerTextureRun.add(new Texture(path + "run" + (i + 1) + ".png"));
        }
        runAnimation = new Animation<>(0.1f, playerTextureRun.toArray(new Texture[0]));

        playerTextureDeath = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playerTextureDeath.add(new Texture(path + "death" + (i + 1) + ".png"));
        }
        deathAnimation = new Animation<>(0.25f, playerTextureDeath.toArray(new Texture[0]));

        playerTextureJump = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            playerTextureJump.add(new Texture(path + "jump" + (i + 1) + ".png"));
        }
        jumpAnimation = new Animation<>(0.15f, playerTextureJump.toArray(new Texture[0]));

        defaultAnimation.setPlayMode(Animation.PlayMode.LOOP);
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);
        jumpAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        deathAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        // All'inizio il personaggio è fermo
        currentAnimation = defaultAnimation;

        // Inizializzazione generatori font
        gen = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/DS-DIGIB.TTF"));
        tutorialGen = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/1up.ttf"));
        titleGen = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/impact.ttf"));

        // Inizializzazione parametri (CORRETTO!)
        param = new FreeTypeFontParameter(); // Assicurati sia istanziato se non globale
        tutorialParam = new FreeTypeFontParameter();
        titleParam = new FreeTypeFontParameter(); // <- RISOLTO IL NULL POINTER

        param.size = 32;
        tutorialParam.size = 15;
        titleParam.size = 50;

        // Generazione effettiva dei font dai file ttf
        titleFont = titleGen.generateFont(titleParam);
        scoreFont = gen.generateFont(param);
        tutorialFont = tutorialGen.generateFont(tutorialParam);
        tutorialFont.setColor(Color.BLACK);
        titleFont.setColor(Color.RED);

        player = new Player(90f, 100f, 100, 220, 220);

        titleLogo = new Texture("teto_logo.png");
        baguetteTexture = new Texture("baguette.png");
        baguette = new Baguette(1000f, 90f, 50f);
        background = new Texture("bg.jpg");

        gravity = -250f;
        baguetteIndex = 0;
        score = 0;

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        // Assegnazione hitbox corretta per libGDX (CORRETTO!)
        playerBounds = player.toRectangle();
        groundBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(), 90);
        baguetteBounds = new Rectangle(baguette.getPosPos().get(baguetteIndex)[0], baguette.getPosPos().get(baguetteIndex)[1], 40, 40);

        isJumping = false;
        maxJumpHeight = 160;
        platforms = new Array<Platform>();

        // Aggiungi le tue piattaforme
        platforms.add(new Platform(400, 180, 150, 20));
        platforms.add(new Platform(650, 280, 200, 20));
        platforms.add(new Platform(100, 320, 180, 20));
        platforms.add(new Platform(370, 400, 200, 20));
        platforms.add(new Platform(600, 440, 470, 20));
        platforms.add(new Platform(10, 520, 350, 20));
        platforms.add(new Platform(550, 90, 200, 60));
        platforms.add(new Platform(180, 540, 30, 90));

        // Caricamento audio
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/teto-territory-8-BITS.mp3"));
        bgMusic.setVolume(0.5f);
        bgMusic.setLooping(true);

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/jump.wav"));
        bonkSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bonk.wav"));
        eating = Gdx.audio.newSound(Gdx.files.internal("Sounds/nom-nom.wav"));
        deadSOund = Gdx.audio.newSound(Gdx.files.internal("Sounds/teetoo.wav"));

        gameStat = "menu";
        bgMenu = new Texture("teto_wallpaper.jpg");
        flipX = false;
        gameTimer = 30f;

        // --- LOGICA DI LETTURA (R) STANDARD JAVA ---
        java.io.File fileRecord = new java.io.File("record.txt");

        if (fileRecord.exists()) {
            // Usiamo il costruttore try-with-resources per chiudere automaticamente il file dopo la lettura
            try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(fileRecord))) {
                String linea = reader.readLine();
                if (linea != null) {
                    highScore = Integer.parseInt(linea.trim());
                    Gdx.app.log("JAVA_IO", "Record letto con successo: " + highScore);
                }
            } catch (Exception e) {
                highScore = 0; // In caso di file corrotto
                Gdx.app.error("JAVA_IO_ERR", "Errore durante la lettura del file. Record resettato a 0.");
            }
        } else {
            highScore = 0; // Primo avvio assoluto
        }

    }


    @Override
    public void render() {
        dt = Gdx.graphics.getDeltaTime();

        if (gameStat == "menu"){
            menu();
        } else if (gameStat == "play" || gameStat== "GAMEOVER") {
            gameLevel();
        }
    }

    public void menu() {
        ScreenUtils.clear(0f, 0f, 0f, 0f);

        // Coordinate e dimensioni del pulsante "Gioca"
        float btnX = 440; // CENTRATO: (1080 - 200) / 2
        float btnY = 250; // Altezza da terra (puoi alzarlo o abbassarlo a piacimento)
        float btnW = 200; // Larghezza del pulsante
        float btnH = 60;  // Altezza del pulsante

        // --- GESTIONE CLICK SUL PULSANTE ---
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            // Inversione della Y di libGDX per allinearla alla telecamera del gioco
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Verifica se il mouse si trova dentro il rettangolo del pulsante
            if ((mouseX >= btnX && mouseX <= btnX + btnW && mouseY >= btnY && mouseY <= btnY + btnH)) {
                gameStat = "play"; // Fai partire il gioco (assicurati che sia "play" o "PLAYING")
                bgMusic.play();
            }
        }else if(gamepad !=null && gamepad.getButton(6)){
            gameStat = "play"; // Fai partire il gioco (assicurati che sia "play" o "PLAYING")
            bgMusic.play();
        }

        // --- RENDERING GRAFICO ---
        batch.begin();
        // Disegna lo sfondo del menu
        batch.setColor(1f, 1f, 1f, 0.5f);
        batch.draw(bgMenu, -10, 0);
        batch.setColor(1f, 1f, 1f, 1f);
        batch.end();

        // Disegna la forma geometrica del pulsante sopra lo sfondo
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED); // Colore rosso tipico di Teto
        sr.rect(btnX, btnY, btnW, btnH);
        sr.end();

        // Disegna la scritta "GIOCA" centrata nel pulsante
        batch.begin();
        batch.draw(titleLogo, (screenWidth-200)/2, screenHeight-220, 200, 200);
        titleFont.draw(batch, "Teto: Baguette Rush", (screenWidth-400)/2, screenHeight - 250);
        // Puoi usare scoreFont o tutorialFont. Modifica i valori finali (+60, +40) per centrare il testo
        scoreFont.draw(batch, "GIOCA", btnX + 60, btnY + 40);
        batch.end();
    }


    // GameLevel è stato ottimizzato con l'AI
    public void gameLevel(){
        vel = player.speed * dt;

        State newState = currentState;

        // Fa scorrere il timer solo se il gioco è in corso (non in GAMEOVER o MENU)
        if (gameTimer > 0 && player.getHealth() > 0) {
            gameTimer -= dt;
        }else if (gameTimer <= 0 || player.getHealth() <= 0) {
            gameTimer = 0;
            if (gameStat != "GAMEOVER") {
                newState = State.DEAD;
                gameStat = "GAMEOVER";
                deadSOund.play(1.0f);
                // --- LOGICA DI SCRITTURA (W) STANDARD JAVA ---
                if (score > highScore) {
                    highScore = score; // Aggiorna la variabile locale

                    java.io.File fileRecord = new java.io.File("record.txt");

                    try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileRecord, false))) {
                        // Convertiamo il numero in stringa prima di scriverlo per evitare caratteri speciali strani
                        writer.write(String.valueOf(highScore));
                        Gdx.app.log("JAVA_IO", "Nuovo record scritto su file: " + highScore);
                    } catch (Exception e) {
                        Gdx.app.error("JAVA_IO_ERR", "Impossibile scrivere il record su disco.");
                    }
                }
            }
        }

        // 1. GESTIONE ANIMAZIONE (Unificato l'incremento del tempo)
        stateTime += Gdx.graphics.getDeltaTime();
        Texture currentFrame = currentAnimation.getKeyFrame(stateTime);

        if (gameStat != "GAMEOVER") {
            // Calcola la velocità orizzontale desiderata in questo frame
            float moveX = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT) || (gamepad != null && gamepad.getAxis(0)< -0.2f)) {
                moveX = -vel;
                flipX = true;
                if (!isJumping) {
                    newState = State.RUNNING;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (gamepad != null && gamepad.getAxis(0)> 0.2f)) {
                moveX = vel;
                flipX = false;
                if (!isJumping) {
                    newState = State.RUNNING;
                }
            }

            if (!isJumping && moveX == 0) {
                newState = State.IDLE;
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
                moveY = gravity * dt; // Applica gravità
            }

            // ==========================================
            // FASE 1: MOVIMENTO E COLLISIONE ORIZZONTALE (X)
            // ==========================================
            player.setX(player.getX() + moveX);
            if (player.getX() < 0) player.setX(0);
            if (player.getX() + 51 > screenWidth) player.setX(screenWidth - player.getWidth());

            // Sincronizza la hitbox SOLO per la X prima del controllo
            playerBounds.x = player.getX();
            playerBounds.y = player.getY(); // Tiene conto della Y attuale reale

            // Controlla la collisione laterale con OGNI piattaforma
            for (Platform p : platforms) {
                Rectangle pBounds = p.toRectangle(); // Evita "new Rectangle" inutile se toRectangle() ne dà già uno

                if (playerBounds.overlaps(pBounds)) {
                    if (moveX > 0) {
                        player.setX(p.getX() - playerBounds.width);
                    } else if (moveX < 0) {
                        player.setX(p.getX() + p.getWidth());
                    }
                    playerBounds.x = player.getX(); // Aggiorna subito dopo il blocco
                }
            }

            // ==========================================
            // FASE 2: MOVIMENTO E COLLISIONE VERTICALE (Y)
            // ==========================================
            player.setY(player.getY() + moveY);

            // Aggiorna la hitbox per l'asse Y mantenendo la X corretta di prima
            playerBounds.x = player.getX();
            playerBounds.y = player.getY();

            // Collisione con il terreno fisso
            if (playerBounds.overlaps(groundBounds)) {
                player.setY(90f);
                lastPlatformTouchedHeight = 90f;
                isJumping = false;
                playerBounds.y = player.getY();


                if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) || (gamepad != null && gamepad.getButton(0))) {
                    isJumping = true;
                    jumpSound.play(1.0f);
                    newState = State.JUMPING;
                }
            }

            // Controlla la collisione verticale con OGNI piattaforma
            for (Platform p : platforms) {
                Rectangle pBounds = p.toRectangle();

                if (playerBounds.overlaps(pBounds)) {
                    // Usa Math.abs(moveY) come tolleranza dinamica invece di 8f fisso
                    float tolerance = Math.max(8f, Math.abs(moveY));

                    // Caso A: Caduta dall'alto (Atterraggio)
                    if (moveY <= 0 && (player.getY() - moveY) >= p.getY() + p.getHeight() - tolerance) {
                        player.setY(p.getY() + p.getHeight());
                        lastPlatformTouchedHeight = p.getY() + p.getHeight();
                        isJumping = false;
                        playerBounds.y = player.getY();

                        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) || (gamepad != null && gamepad.getButton(0))) {
                            isJumping = true;
                            jumpSound.play(1.0f);
                            newState = State.JUMPING;
                        }
                    }
                    // Caso B: Salto da sotto (Testata)
                    else if (moveY > 0) {
                        bonkSound.play();
                        player.setY(p.getY() - playerBounds.height);
                        isJumping = false;
                        playerBounds.y = player.getY();
                    }
                }
            }
        }

        // ==========================================
        // FASE 3: INTERAZIONI E LOGICA DI GIOCO
        // ==========================================
        // Aggiorna un'ultima volta i bounds per gli oggetti collezionabili
        playerBounds.x = player.getX();
        playerBounds.y = player.getY();

        if (playerBounds.overlaps(baguetteBounds)) {
            int num = 0;
            eating.play();
            gameTimer += 5f; // Raccogliere una baguette regala 5 secondi extra!
            int tentativi = 0;
            boolean posizioneTrovata = false;

            while (!posizioneTrovata && tentativi < 100) { // Massimo 100 tentativi
                tentativi++;

                // Prendi una coordinata a caso dalla tua lista posPos
                int indiceCasuale = rand.nextInt(baguette.getPosPos().size());
                int[] coord = baguette.getPosPos().get(indiceCasuale);

                // I tuoi controlli di sicurezza attuali (es. non toccare ostacoli)
                if (!playerBounds.overlaps(new Rectangle(coord[0], coord[1], baguetteBounds.width, baguetteBounds.height))) {
                    baguetteBounds.x = coord[0];
                    baguetteBounds.y = coord[1];
                    posizioneTrovata = true;
                    num = indiceCasuale;
                }
            }

            // SE DOPO 100 TENTATIVI NON TROVA UN POSTO, FORZA LO SPAWN IN UN PUNTO SICURO DI DEFAULT
            if (!posizioneTrovata) {
                // Forza la prima coordinata della lista per evitare il freeze!
                int[] coordSicura = baguette.getPosPos().get(0);
                baguetteBounds.x = coordSicura[0];
                baguetteBounds.y = coordSicura[1];
                num = 0;

                Gdx.app.log("WARNING", "Failsafe attivato per evitare il freeze a quota " + score);
            }
            score += 5;
            baguetteIndex = num;

            int[] coordinate = baguette.getPosPos().get(baguetteIndex);
            baguetteBounds.x = coordinate[0];
            baguetteBounds.y = coordinate[1];
            if (player.getHealth() < 100){
                if (player.getHealth() <= 90){
                    player.setHealth(player.getHealth() + 10);
                }else{
                    player.setHealth(100);
                }
            }
            if (score > highScore) {
                highScore = score;

                // Scrittura immediata su file standard Java
                java.io.File fileRecord = new java.io.File("record.txt");
                try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileRecord, false))) {
                    writer.write(String.valueOf(highScore));
                } catch (Exception e) {
                    Gdx.app.error("JAVA_IO_ERR", "Impossibile salvare il record a runtime.");
                }
            }
        }


        // Gestione cambio di stato animazione
        if (currentState != newState) {
            currentState = newState;
            stateTime = 0f;

            switch (currentState) {
                case IDLE:    currentAnimation = defaultAnimation; break;
                case RUNNING: currentAnimation = runAnimation;     break;
                case JUMPING: currentAnimation = jumpAnimation;    break;
                case DEAD:    currentAnimation = deathAnimation;   break;
            }
        }

        // Recupera il frame corretto dopo il potenziale reset dello stato
        currentFrame = currentAnimation.getKeyFrame(stateTime);

        // ==========================================
        // FASE 4: RENDERING GRAFICO
        // ==========================================
        ScreenUtils.clear(0.53f, 0.81f, 0.99f, 1f);
        batch.begin();
        batch.draw(background, 0, -50, screenWidth, 810);
        batch.end();

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(50f/ 255f, 205f/ 255f, 50f/ 255f, 1f);
        sr.rect(0, 0, Gdx.graphics.getWidth(), 90);
        sr.setColor(142f/ 255f, 142f/ 255f, 142f/ 255f, 1f);
        for (Platform p : platforms) {
            sr.rect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        }
        // SE SEI IN GAMEOVER, DISEGNA IL RETTANGOLO DEL PULSANTE QUI DENTRO!
        float btnW = 250;
        float btnH = 60;
        float btnX = (1080 - btnW) / 2; // 415
        float btnY = 200;

        if (gameStat.equals("GAMEOVER")) {
            sr.setColor(Color.RED); // Colore rosso del pulsante
            sr.rect(btnX, btnY, btnW, btnH);
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
            flipX, false
        );

        // Grafica dello Score ottimizzata in scannabilità
        if (score < 10) scoreFont.draw(batch, "Score: 000" + score, screenWidth - 200, screenHeight - 20);
        else if (score < 100) scoreFont.draw(batch, "Score: 00" + score, screenWidth - 200, screenHeight - 20);
        else if (score < 1000) scoreFont.draw(batch, "Score: 0" + score, screenWidth - 200, screenHeight - 20);
        else if (score < 10000) scoreFont.draw(batch, "Score: " + score, screenWidth - 200, screenHeight - 20);
        else scoreFont.draw(batch, "Score: 9999", screenWidth - 200, screenHeight - 20);

        scoreFont.draw(batch, "Time left: " + Math.round(gameTimer), (float) 250, screenHeight-20);

        if (highScore < 10) scoreFont.draw(batch, "High Score: 000" + highScore, screenWidth - 470, screenHeight - 20);
        else if (highScore < 100) scoreFont.draw(batch, "High Score: 00" + highScore, screenWidth - 470, screenHeight - 20);
        else if (highScore < 1000) scoreFont.draw(batch, "High Score: 0" + highScore, screenWidth - 470, screenHeight - 20);
        else if (highScore < 10000) scoreFont.draw(batch, "High Score: " + highScore, screenWidth - 470, screenHeight - 20);
        else scoreFont.draw(batch, "High Score: 9999", screenWidth - 470, screenHeight - 20);
        if (player.getHealth() == 100){
            scoreFont.draw(batch, "Life: " + player.getHealth(), 10, screenHeight-20);
        } else if (player.getHealth() > 9) {
            scoreFont.draw(batch, "Life: 0" + player.getHealth(), 10, screenHeight-20);
        }else{
            scoreFont.draw(batch, "Life: 00" + player.getHealth(), 10, screenHeight-20);
        }

        if (score == 0){
            tutorialFont.draw(batch, "Use A and D or LEFT \nand RIGHT arrows for move", 10, 60);
            tutorialFont.draw(batch, "Use W or UP \narrow to \njump", 400, 160);
            tutorialFont.draw(batch, "Touch the \nbaguette to \neat it", 900, 75);
            tutorialFont.draw(batch, "Prendi la baguette prima \ndello scadere del tempo \ne muoia di fame", 400, screenHeight-50);
        }
        if (gameStat == "GAMEOVER"){
            scoreFont.draw(batch, "GAME OVER", btnX + 30, (float)screenWidth / 2 -20);

            // --- GESTIONE CLICK SUL PULSANTE (Sicura all'interno del batch) ---
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                float mouseX = Gdx.input.getX();
                float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

                if (mouseX >= btnX && mouseX <= btnX + btnW && mouseY >= btnY && mouseY <= btnY + btnH) {
                    resetGame(); // Riporta le variabili allo stato iniziale
                    gameStat = "menu";
                }
            } else if (gamepad !=null && gamepad.getButton(6)) {
                resetGame();
                gameStat = "menu";
            }

            // Disegna la scritta sopra il pulsante rosso che abbiamo fatto prima con lo sr
            scoreFont.draw(batch, "RICOMINCIA", btnX + 30, btnY + 40);
        }

        batch.end();
    }

    private void resetGame() {
        // 1. Ripristina i parametri vitali di Teto e del tempo
        player.setX(90f);
        player.setY(100f);
        player.setHealth(100);
        gameTimer = 30f; // Riporta il timer a 30 secondi (o il tuo valore iniziale)
        score = 0;       // Resetta il punteggio attuale
        flipX = false;
        // 2. Resetta gli stati delle animazioni fisiche
        isJumping = false;
        currentState = State.IDLE;
        currentAnimation = defaultAnimation;
        stateTime = 0f;


        // 3. Riposiziona gli oggetti interattivi (Baguette e Ostacoli)
        baguetteIndex = 0;
        // Aggiorna l'hitbox iniziale della prima baguette
        baguetteBounds.setPosition(baguette.getPosPos().get(baguetteIndex)[0], baguette.getPosPos().get(baguetteIndex)[1]);

        // Se hai inserito il paracadute o vuoi rigenerare gli ostacoli a inizio mappa
        // ostacles.clear();
        // ostacles.add(new Ostacle(...));

        // 4. Gestione Audio: Ferma eventuali suoni residui e fa ripartire la musica da capo
        bgMusic.stop();
        bgMusic.play();

        // 5. Cambia lo stato del gioco per far ricominciare il gameplay
        gameStat = "play";
    }


    @Override
    public void dispose() {
        // --- PARACADUTE DI SALVATAGGIO ALLA CHIUSURA DELLA FINESTRA ---
        if (score > highScore) {
            highScore = score;
            java.io.File fileRecord = new java.io.File("record.txt");
            try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileRecord, false))) {
                writer.write(String.valueOf(highScore));
                Gdx.app.log("JAVA_IO_CLOSE", "Gioco chiuso improvvisamente! Record salvato in extremis: " + highScore);
            } catch (Exception e) {
                // Silenzioso in chiusura
            }
        }

        // 1. Grafica e Rendering
        batch.dispose();
        sr.dispose();
        background.dispose();
        baguetteTexture.dispose();
        bgMenu.dispose();
        titleLogo.dispose();

        // 2. Texture dei cicli (Animazioni)
        for (Texture tex : playerTexture) {
            tex.dispose();
        }
        for (Texture tex : playerTextureRun) {
            tex.dispose();
        }
        for (Texture tex : playerTextureDeath) {
            tex.dispose();
        }
        for (Texture tex : playerTextureJump) {
            tex.dispose();
        }


        // 3. Font e Generatori
        scoreFont.dispose();
        tutorialFont.dispose();
        gen.dispose();
        tutorialGen.dispose();
        titleGen.dispose();
        titleFont.dispose();

        // 4. Audio
        bgMusic.dispose();
        jumpSound.dispose();
        bonkSound.dispose();
        eating.dispose();
    }
}
