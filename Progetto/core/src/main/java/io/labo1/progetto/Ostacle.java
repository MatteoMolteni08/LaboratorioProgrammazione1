package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

/**
 * Rappresenta un ostacolo generico all'interno del mondo di gioco.
 * Questa classe estende {@link Element} e implementa l'interfaccia {@link Danger},
 * consentendo all'ostacolo di comportarsi come un pericolo e di infliggere
 * danni al giocatore in base a un coefficiente specifico.
 * La JavaDoc è stata creata da Google AI, il lavoro resta dell'autore
 *
 * @author Matteo Molteni
 * @version 1.0
 */
public class Ostacle extends Element implements Danger {

    /** Flag che indica se l'ostacolo è attualmente attivo come pericolo. */
    private boolean isDanger;

    /** Coefficiente moltiplicativo applicato al danno base inflitto al giocatore. */
    private float damageCoefficient;

    /**
     * Costruttore completo per inizializzare un ostacolo con posizione,
     * dimensioni e parametri di danno specificati.
     *
     * @param x                 la coordinata X iniziale dell'ostacolo
     * @param y                 la coordinata Y iniziale dell'ostacolo
     * @param width             la larghezza dell'ostacolo
     * @param height            l'altezza dell'ostacolo
     * @param isDanger          true se l'ostacolo deve infliggere danni, false altrimenti
     * @param damageCoefficient il fattore di moltiplicazione del danno inflitto
     */
    public Ostacle(float x, float y, int width, int height, boolean isDanger, float damageCoefficient) {
        super(x, y, width, height);
        this.isDanger = isDanger;
        this.damageCoefficient = damageCoefficient;
    }

    /**
     * Verifica se l'ostacolo rappresenta una minaccia attiva per il giocatore.
     *
     * @return true se l'ostacolo è pericoloso, false altrimenti
     */
    public boolean isDanger() {
        return isDanger;
    }

    /**
     * Restituisce il coefficiente moltiplicativo del danno dell'ostacolo.
     *
     * @return il coefficiente di danno attuale
     */
    public float getDamageCoefficient() {
        return damageCoefficient;
    }

    /**
     * Riduce la salute del giocatore specificato quando avviene una collisione o un'interazione.
     * Il danno calcolato sottrae alla salute attuale del giocatore il valore della costante
     * {@code DAMAGE} (definita nell'interfaccia {@link Danger}) moltiplicata per il
     * coefficiente di danno di questo ostacolo.
     *
     * @param p il {@link Player} a cui infliggere il danno
     */
    @Override
    public void DamageDealing(Player p) {
        p.setHealth((int) (p.getHealth() - DAMAGE * damageCoefficient));
    }

    /**
     * Converte la posizione e le dimensioni attuali dell'ostacolo in un oggetto {@link Rectangle}.
     * Questo rettangolo viene utilizzato principalmente da libGDX per determinare l'area di contatto
     * e calcolare le collisioni con altre entità di gioco.
     *
     * @return un nuovo oggetto {@link Rectangle} basato sulle coordinate e dimensioni dell'ostacolo
     */
    @Override
    public Rectangle toRectangle() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
