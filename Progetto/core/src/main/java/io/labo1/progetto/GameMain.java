package io.labo1.progetto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameMain extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture player;
    private ShapeRenderer sr;
    private String path;
    private int[] poseNum;
    private double gravity;
    private double jump;
    private double[] playerPos;
    private int screenWidth;
    private int screenHeight;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        player = new Texture("teto/default_pose1.png");
        path = "teto/";
        poseNum = new int[]{24, 4, 9, 4, 18}; /** default_pose, run, jump, death, drill_attack **/
        gravity = 500f;
        jump = 350f;
        playerPos = new double[] {};
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.53f, 0.81f, 0.99f, 1f);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.GREEN);
        sr.rect(0, 0, Gdx.graphics.getWidth(),90);
        sr.end();

        batch.begin();
        batch.draw(player, 140, 210);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        sr.dispose();
    }
}
