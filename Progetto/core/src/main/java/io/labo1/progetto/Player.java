package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

public class Player extends Element {
    protected int health;
    protected float speed;
    protected float jump;


    public Player(float x, float y, int health, float speed, float jump) {
        super(x, y, 51, 70);
        this.health = health;
        this.speed = speed;
        this.jump = jump;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getJump() {
        return jump;
    }
    public void setJump(float jump) {
        this.jump = jump;
    }


    public Rectangle toRectangle(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
