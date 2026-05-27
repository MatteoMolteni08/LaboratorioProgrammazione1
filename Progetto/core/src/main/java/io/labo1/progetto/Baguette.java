package io.labo1.progetto;

import java.util.ArrayList;

public class Baguette extends Element {
    // 1. Dichiarazione e inizializzazione della lista (Ok qui)
    private ArrayList<int[]> posPos = new ArrayList<>();

    public Baguette(double x, double y) {
        super(x, y);

        // 2. CORREZIONE: L'aggiunta dei dati va dentro il costruttore
        posPos.add(new int[]{1000, 90});
        posPos.add(new int[]{700, 300});
        posPos.add(new int[]{1000, 460});
        posPos.add(new int[]{15, 540});
    }

    public ArrayList<int[]> getPosPos() {
        return posPos;
    }
}
