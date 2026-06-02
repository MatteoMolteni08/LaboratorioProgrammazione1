package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 * Rappresenta l'oggetto collezionabile principale del gioco (la Baguette),
 * fondamentale per il tema e il gameplay di Kasane Teto.
 * Questa classe gestisce le dimensioni dell'oggetto e contiene una lista di coordinate
 * predefinite utilizzate per posizionare le baguette all'interno del livello.
 * <p>
 * Estende la classe base {@link Element}.
 * </p>
 *
 * @author IlTuoNome
 * @version 1.0
 */
public class Baguette extends Element {

    /**
     * Lista contenente gli array di interi [x, y] che rappresentano
     * i punti di spawn predefiniti delle baguette nella mappa.
     */
    private ArrayList<int[]> posPos = new ArrayList<>();

    /**
     * Crea un nuovo oggetto Baguette specificando la sua posizione iniziale e la sua dimensione.
     * Trattandosi di un oggetto quadrato, il parametro size imposta sia la larghezza che l'altezza.
     * All'atto dell'inizializzazione, inserisce nella lista le coordinate fisse di spawn.
     *
     * @param x    la coordinata X iniziale della baguette
     * @param y    la coordinata Y iniziale della baguette
     * @param size la dimensione del lato della baguette (larghezza e altezza)
     */
    public Baguette(float x, float y, float size) {
        super(x, y, size, size);

        // aggiunta dei dati dentro il costruttore
        posPos.add(new int[]{1000, 90});
        posPos.add(new int[]{700, 300});
        posPos.add(new int[]{1000, 460});
        posPos.add(new int[]{15, 540});
        posPos.add(new int[]{100, 340});
        posPos.add(new int[]{480, 90});
    }

    /**
     * Converte la posizione e le dimensioni attuali della baguette in un oggetto {@link Rectangle}.
     * Questo rettangolo viene utilizzato da libGDX per determinare la collisione (overlap)
     * con il giocatore, permettendo a Teto di raccogliere l'oggetto.
     *
     * @return un nuovo oggetto {@link Rectangle} basato sulle coordinate e dimensioni della baguette
     */
    @Override
    public Rectangle toRectangle() {
        return new Rectangle(x, y, getWidth(), getHeight());
    }

    /**
     * Restituisce la lista completa di tutte le coordinate di spawn predefinite per le baguette.
     *
     * @return un {@link ArrayList} contenente array di interi con le coordinate [x, y]
     */
    public ArrayList<int[]> getPosPos() {
        return posPos;
    }
}
