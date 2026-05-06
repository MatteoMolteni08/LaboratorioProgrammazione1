package io.github.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont; //importa il font per scrivere in game
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle; //Importa il rettangolo per le collisioni
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends ApplicationAdapter {
    Texture bgTex, pipeTex, flappyTex;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    float flappyY = 200;
    float flappyX = 120;
    float flappyHeight = 40;
    float flappyWidth = 40;
    float velY = 0;
    float GRAVITY = -500;
    final float FLAP_FORCE = 200;
    BitmapFont font;
    int score = 0;
    Rectangle flappyBounds;
    Rectangle groundBounds;
    Rectangle skyBounds;
    boolean isGameOVer;

    @Override
    public void create() {
        bgTex     = new Texture("background.png");
        pipeTex   = new Texture("pipe.png");
        flappyTex = new Texture("flappy.png");
        batch     = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.getData().setScale(1.2f);
        flappyBounds = new Rectangle(flappyX,flappyY,flappyWidth,flappyHeight);
        groundBounds = new Rectangle(0,0,Gdx.graphics.getWidth() ,50 );
        skyBounds = new Rectangle(0,Gdx.graphics.getHeight()-20 ,Gdx.graphics.getWidth() ,50 );
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        // --- LOGICA DI AGGIORNAMENTO ---
        if (!isGameOVer) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !flappyBounds.overlaps(skyBounds) ) {
                velY = FLAP_FORCE;
            }

            velY += GRAVITY * dt;
            flappyY += velY * dt;
            flappyBounds.y = flappyY;

            if (flappyBounds.overlaps(groundBounds)) {
                isGameOVer = true;
            }
        } else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                ReStartGame();
            }
        }
        // --- LOGICA DI DISEGNO ---
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(bgTex,0,0,280,480);
        batch.draw(pipeTex,210,40,70,200);
        batch.draw(flappyTex, flappyX,flappyY, flappyWidth,flappyHeight);
        font.draw(batch, "Punteggio: " + score, 20, Gdx.graphics.getHeight() - 20);
        if(isGameOVer == true)
            font.draw(batch, "GameOver: ", Gdx.graphics.getWidth()/2 -50,Gdx.graphics.getHeight()/2 );
        batch.end();
    }

    @Override
    public void dispose() {
        bgTex.dispose();
        pipeTex.dispose();
        flappyTex.dispose();
        batch.dispose();
    }

    private void ReStartGame(){
        flappyY = 200;
        flappyX = 120;
        score = 0;
        flappyBounds.y = flappyY;
        velY = 0;
        isGameOVer = false;
    }
}
