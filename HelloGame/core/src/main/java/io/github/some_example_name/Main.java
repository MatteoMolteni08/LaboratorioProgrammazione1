package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;

    ShapeRenderer shape;

    @Override
    public void create() {
        shape = new ShapeRenderer();
    }
    float x = 0;

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.RED);
        shape.circle(x, 100, 50);
        shape.end();
        x += 1;
    }

    @Override
    public void dispose() {
        shape.dispose();
    }

}
