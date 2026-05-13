package io.labo1.progetto;

public class Player {
    protected String skinName;
    protected int health = 100;
    protected double speed;
    protected double jump;
    protected double x;
    protected double y;

    public Player(String skinName, double speed, double jump, double x, double y) {
        this.skinName = skinName;
        this.speed = speed;
        this.jump = jump;
        this.x = x;
        this.y = y;
    }

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
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
}
