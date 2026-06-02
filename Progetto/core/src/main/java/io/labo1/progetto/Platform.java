package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

/**
 * La classe per la gestione delle piattaforme all'interno del mondo di gioco.
 * Permette di creare superfici solide su cui il personaggio può camminare,
 * atterrare o urtare dal basso.
 * <p>
 * Estende la classe base {@link Element}.
 * </p>
 *
 * @author IlTuoNome
 * @version 1.0
 */
public class Platform extends Element {

    /**
     * Crea una nuova piattaforma specificando la sua posizione e le sue dimensioni.
     *
     * @param x      la coordinata X iniziale della piattaforma nello spazio di gioco
     * @param y      la coordinata Y iniziale della piattaforma nello spazio di gioco
     * @param width  la larghezza della piattaforma
     * @param height l'altezza della piattaforma
     */
    public Platform(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /**
     * Converte la posizione e le dimensioni attuali della piattaforma in un oggetto {@link Rectangle}.
     * Questo rettangolo viene utilizzato da libGDX per il calcolo e la gestione
     * delle collisioni fisiche con il giocatore.
     *
     * @return un nuovo oggetto {@link Rectangle} basato sulle coordinate e dimensioni della piattaforma
     */
    public Rectangle toRectangle(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
