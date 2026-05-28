package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

public class Ostacle extends Element implements Danger{
    private boolean isDanger;
    private float damageCoefficient;

    public Ostacle(float x, float y, int width, int height, boolean isDanger, float damageCoefficient) {
        super(x, y, width, height);
        this.isDanger = isDanger;
        this.damageCoefficient = damageCoefficient;
    }

    public boolean isDanger() {
        return isDanger;
    }

    public float getDamageCoefficient() {
        return damageCoefficient;
    }

    @Override
    public void DamageDealing(Player p) {
        p.setHealth((int) (p.getHealth() - DAMAGE * damageCoefficient));
    }

    @Override
    public Rectangle toRectangle() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
