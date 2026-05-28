package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

public class Player extends Element {
    protected String skinName;
    protected int health = 100;
    protected double speed;
    protected double jump;


    public Player(float x, float y, String skinName, int health, double speed, double jump) {
        super(x, y, 51, 70);
        this.skinName = skinName;
        this.health = health;
        this.speed = speed;
        this.jump = jump;
    }

/*    public String changeSkin(){

    }*/

    public String getSkinName() {
        return skinName;
    }
    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getJump() {
        return jump;
    }
    public void setJump(double jump) {
        this.jump = jump;
    }


    public Rectangle toRectangle(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
