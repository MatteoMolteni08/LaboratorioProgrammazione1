package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture player;
    private Texture enemy;
    private Texture background;
    private float[] velocitiesX;
    private float[] positions0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Texture("player.png");
        enemy = new Texture("enemy.png");
        background = new Texture("background.jpg");
        velocitiesX = new float[]{200, 300};
        positions0 = new float[]{0, Gdx.graphics.getWidth()-enemy.getWidth()};
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        batch.draw(background, 0, 0, 400, 300);
        batch.draw(player, positions0[0], 20);
        batch.draw(enemy, positions0[1], 20);
        batch.end();

        positions0[0] += velocitiesX[0]*Gdx.graphics.getDeltaTime();
        positions0[1] += velocitiesX[1]*Gdx.graphics.getDeltaTime();

        for (int i = 0; i < positions0.length; i++) {
            if (positions0[i] <=0 || positions0[i]+player.getWidth()>=Gdx.graphics.getWidth()){
                velocitiesX[i] *=-1;
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        enemy.dispose();
        background.dispose();
    }
}
