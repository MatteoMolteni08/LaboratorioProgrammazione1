package io.labo1.progetto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture player;
    private ShapeRenderer sr;
    private String path;
    private int[] poseNum;
    private int skinNum;
    private float gravity;
    private float jump;
    private float[] playerPos;
    private int screenWidth;
    private int screenHeight;
    private boolean gravityOn;

    private Rectangle playerBounds;
    private Rectangle groundBounds;
    private Platform platform1;
    private Rectangle platform1Bounds;

    private float dt;
    private float vel;
    private boolean isJumping;
    private int maxJumpHeight;
    private float lastPlatformTouchedHeight;

    private Texture baguette;
    private Rectangle baguetteBounds;

    private Music bgMusic;
    private Sound jumpSound;
    private Sound bonkSound;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        player = new Texture("teto/default_pose1.png");
        baguette = new Texture("baguette.png");
        path = "teto/";
        poseNum = new int[]{24, 4, 9, 4, 18}; /** default_pose, run, jump, death, drill_attack **/
        skinNum = 1;
        gravity = -100f;
        jump = 350f;
        playerPos = new float[] {90f, 100f};
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        playerBounds = new Rectangle(playerPos[0], playerPos[1], 51, 70);
        groundBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),90);
        baguetteBounds = new Rectangle(1000, 90, 40,40);
        isJumping = false;
        maxJumpHeight = 160;
        platform1 = new Platform(500, 190, 300, 30);
        platform1Bounds = new Rectangle(platform1.toRectangle());

        // Caricamento diretto nel metodo Create()
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/teto-territory-8-BITS.mp3"));
        // Configurazione
        bgMusic.setVolume(0.5f); // 0.0 → 1.0
        bgMusic.setLooping(true); // true = riparte automaticamente
        // Avvio
        bgMusic.play();
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/jump.mp3"));
        bonkSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bonk.mp3"));

    }

    @Override
    public void render() {
        dt = Gdx.graphics.getDeltaTime();
        vel = 220 * dt;

        // 1. APPLICA I MOVIMENTI (Input e Forze)
        // Movimento orizzontale
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (playerPos[0] > 0) playerPos[0] -= vel;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (playerPos[0] + 51 < screenWidth) playerPos[0] += vel;
        }

        // 1. APPLICA SEMPRE LA GRAVITÀ (Se non stai saltando)
        if (isJumping) {
            if (playerPos[1] < maxJumpHeight + lastPlatformTouchedHeight) {
                playerPos[1] += 150 * dt;
            } else {
                isJumping = false;
            }
        } else {
            // Applica sempre la forza di gravità verso il basso
            playerPos[1] += gravity * dt;
        }

        // 2. AGGIORNA I BOUNDS CON LA NUOVA POSIZIONE
        playerBounds.x = playerPos[0];
        playerBounds.y = playerPos[1];

        // 3. RISOLVI LE COLLISIONI (Riposiziona solo se atterri davvero)
        if (playerBounds.overlaps(groundBounds)) {
            playerPos[1] = 90f; // Sopra il terreno esatto
            lastPlatformTouchedHeight = 90f;
            isJumping = false; // Resetta lo stato di salto
            playerBounds.y = playerPos[1]; // Sincronizza hitbox

            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                isJumping = true;
                jumpSound.play(1.0f);
            }
        }
        else if (playerBounds.overlaps(platform1Bounds)) {
            // CONDIZIONE CRITICA: Gestisci la collisione SOLO se Teto sta scendendo
            // e i suoi piedi sono effettivamente sopra il livello della piattaforma
            if (playerPos[1] >= platform1.getPosY() + platform1.getHeight() - 8f) {
                playerPos[1] = platform1.getPosY() + platform1.getHeight(); // Blocca sopra la piattaforma
                lastPlatformTouchedHeight = platform1.getPosY() + platform1.getHeight();
                isJumping = false;
                playerBounds.y = playerPos[1]; // Sincronizza hitbox

                if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    isJumping = true;
                    jumpSound.play(1.0f);
                }
            }if (playerPos[1] + playerBounds.height <= platform1.getPosY() + 10f){
                bonkSound.play(1.0f);
                playerPos[1] = platform1.getPosY() - playerBounds.height;
                isJumping = false;
            }
            // Se tocca lateralmente o dal basso, non fare nulla: la gravità continuerà
            // a farla cadere naturalmente senza bloccarla a mezz'aria!
        }

        // 4. RENDERING GRAFICO
        ScreenUtils.clear(0.53f, 0.81f, 0.99f, 1f);

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.GREEN);
        sr.rect(0, 0, Gdx.graphics.getWidth(), 90);
        sr.rect(platform1.getPosX(), platform1.getPosY(), platform1.getWidth(), platform1.getHeight());
        sr.end();

        batch.begin();
        batch.draw(baguette, 1000, 90, 40, 40);
        batch.draw(player, playerPos[0], playerPos[1]);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        baguette.dispose();
        sr.dispose();
    }
}
