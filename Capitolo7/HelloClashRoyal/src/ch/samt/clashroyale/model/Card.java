package ch.samt.clashroyale.model;

import java.util.Objects;

public class Card {
    private String name;
    private int elixirCost;
    private int level;

    public Card(String name, int elixirCost, int level) {
        this.name = name;
        this.elixirCost = elixirCost;
        this.level = level;
    }

    public String getName() {
        return name;
    }
    public int getElixirCost() {
        return elixirCost;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", elixirCost=" + elixirCost +
                ", level=" + level +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(name, card.name);
    }

}
