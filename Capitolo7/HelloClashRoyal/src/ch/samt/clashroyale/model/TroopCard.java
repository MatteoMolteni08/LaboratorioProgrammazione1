package ch.samt.clashroyale.model;

public class TroopCard extends Card{
    private int hitPoints;
    private int damage;

    public TroopCard(String name, int elixirCost, int level, int hitPoints, int damage) {
        super(name, elixirCost, level);
        this.hitPoints = hitPoints;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "TroopCard{" +
                "name = " + getName() +
                ", elixirCost = " + getElixirCost() +
                ", level = " + getLevel() +
                ", hitPoints = " + hitPoints +
                ", damage=" + damage +
                '}';
    }

    public int getHitPoints() {
        return hitPoints;
    }
    public int getDamage() {
        return damage;
    }
}
