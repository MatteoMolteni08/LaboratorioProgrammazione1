package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

public class Platform extends Element{

    public Platform(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Rectangle toRectangle(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
