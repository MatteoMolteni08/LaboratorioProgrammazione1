package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

/**
 * Classe astratta che rappresenta un elemento generico posizionabile nello spazio bidimensionale del gioco.
 * Fornisce le proprietà fondamentali di posizione (coordinate X e Y) e dimensione (larghezza e altezza),
 * fungendo da classe base per tutti gli oggetti di gioco (come entità, piattaforme o collezionabili).
 * <p>
 * Le classi che estendono questo elemento devono implementare il metodo {@link #toRectangle()}
 * per permettere la gestione delle collisioni.
 * </p>
 * La JavaDoc è stata creata con l'aiuto di Google AI
 *
 * @author Matteo Molteni
 * @version 1.0
 */
public abstract class Element {

    /** la coordinata X della posizione dell'elemento. */
    protected float x;

    /** la coordinata Y della posizione dell'elemento. */
    protected float y;

    /** la larghezza dell'elemento. */
    protected float width;

    /** l'altezza dell'elemento. */
    protected float height;

    /**
     * Costruttore completo per inizializzare un elemento con posizione e dimensioni specifiche.
     *
     * @param x      la coordinata X iniziale
     * @param y      la coordinata Y iniziale
     * @param width  la larghezza dell'elemento
     * @param height l'altezza dell'elemento
     */
    public Element(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Costruttore ridotto per inizializzare un elemento specificando solo la posizione.
     * Le dimensioni rimangono non inizializzate (pari a 0.0f di default) e dovranno essere
     * impostate successivamente.
     *
     * @param x la coordinata X iniziale
     * @param y la coordinata Y iniziale
     */
    public Element(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metodo astratto che deve essere implementato dalle sottoclassi per convertire
     * l'elemento in un oggetto {@link Rectangle} di libGDX.
     * Viene utilizzato per definire l'area di collisione dell'oggetto.
     *
     * @return un oggetto {@link Rectangle} che rappresenta la bounding box dell'elemento
     */
    public abstract Rectangle toRectangle();

    /**
     * Restituisce la coordinata X attuale dell'elemento.
     *
     * @return la coordinata X
     */
    public float getX() {
        return x;
    }

    /**
     * Imposta la coordinata X dell'elemento.
     *
     * @param x la nuova coordinata X da assegnare
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Restituisce la coordinata Y attuale dell'elemento.
     *
     * @return la coordinata Y
     */
    public float getY() {
        return y;
    }

    /**
     * Imposta la coordinata Y dell'elemento.
     *
     * @param y la nuova coordinata Y da assegnare
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Restituisce la larghezza attuale dell'elemento.
     *
     * @return la larghezza dell'elemento
     */
    public float getWidth() {
        return width;
    }

    /**
     * Imposta la larghezza dell'elemento.
     *
     * @param width la nuova larghezza da assegnare
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Restituisce l'altezza attuale dell'elemento.
     *
     * @return l'altezza dell'elemento
     */
    public float getHeight() {
        return height;
    }

    /**
     * Imposta l'altezza dell'elemento.
     *
     * @param height la nuova altezza da assegnare
     */
    public void setHeight(float height) {
        this.height = height;
    }
}
