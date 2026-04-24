package io.github.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture flappy;
    private Texture pipe;
    private Texture background;
    private float flappyY = 40;
    private float flappyX = 80;
    private float velY = 0;
    private float GRAVITY = -500;
    private float FLAP_FORCE = 200;

    @Override
    public void create() {
        batch = new SpriteBatch();
        flappy = new Texture("flappy.png");
        pipe = new Texture("pipe.png");
        background = new Texture("background.png");
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched())
            velY = FLAP_FORCE;

        velY  += GRAVITY * dt;
        flappyY += velY * dt;

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(pipe, 200, 50, pipe.getWidth(), 200);
        batch.draw(flappy, 80, flappyY, 60, flappy.getHeight()-20);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        flappy.dispose();
        pipe.dispose();
        background.dispose();
    }
}
