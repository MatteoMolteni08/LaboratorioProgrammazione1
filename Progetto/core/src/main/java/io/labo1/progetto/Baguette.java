package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Baguette extends Element {
    // 1. Dichiarazione e inizializzazione della lista (Ok qui)
    private ArrayList<int[]> posPos = new ArrayList<>();

    public Baguette(float x, float y) {
        super(x, y, 50f, 50f);

        // aggiunta dei dati dentro il costruttore
        posPos.add(new int[]{1000, 90});
        posPos.add(new int[]{700, 300});
        posPos.add(new int[]{1000, 460});
        posPos.add(new int[]{15, 540});
        posPos.add(new int[]{100, 340});
        posPos.add(new int[]{480, 90});

    }

    @Override
    public Rectangle toRectangle() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    public ArrayList<int[]> getPosPos() {
        return posPos;
    }
}
