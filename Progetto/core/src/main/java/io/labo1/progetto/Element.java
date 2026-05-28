package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

public abstract class Element {
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public Element(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Element(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract Rectangle toRectangle();

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
