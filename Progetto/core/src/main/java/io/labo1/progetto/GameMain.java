package io.labo1.progetto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

    private Rectangle playerBounds;
    private Rectangle groundBounds;

    private float dt;
    private float vel;
    private boolean isJumping;
    private float jumpBoost;
    private int maxJumpHeight;

    private Texture baguette;
    private Rectangle baguetteBounds;

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
        playerPos = new float[] {140f, 210f};
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        playerBounds = new Rectangle(playerPos[0], playerPos[1], 51, 70);
        groundBounds = new Rectangle(0, 0, Gdx.graphics.getWidth(),90);
        baguetteBounds = new Rectangle(1000, 90, 40,40);
        isJumping = false;
        jumpBoost = 50;
        maxJumpHeight = 160;
    }

    @Override
    public void render() {
        dt = Gdx.graphics.getDeltaTime();
        vel = 220 * dt;

        if (!playerBounds.overlaps(groundBounds) && !isJumping){
            playerPos[1] += gravity * dt;
        }
        if (playerBounds.overlaps(groundBounds)){
            playerPos[1] = 89.99F;
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)){
                isJumping = true;
            }
        }

        if (isJumping){
            if (playerPos[1] < maxJumpHeight + 90){
                playerPos[1] += 150 * dt;
                jumpBoost -= 10;
            }else if(playerPos[1] >= maxJumpHeight + 90){
                isJumping = false;
            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (playerPos[0] > 0){
                playerPos[0] -= vel;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (playerPos[0] + 51 < screenWidth){
                playerPos[0] += vel;
            }

        }

        playerBounds.x = playerPos[0];
        playerBounds.y = playerPos[1];

        ScreenUtils.clear(0.53f, 0.81f, 0.99f, 1f);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.GREEN);
        sr.rect(0, 0, Gdx.graphics.getWidth(),90);
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
