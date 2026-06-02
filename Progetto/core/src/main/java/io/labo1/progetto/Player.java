package io.labo1.progetto;

import com.badlogic.gdx.math.Rectangle;

/**
 * Rappresenta il personaggio giocabile (Player) all'interno del gioco.
 * Gestisce i parametri vitali, la velocità di movimento, la forza del salto
 * e fornisce strumenti per la gestione delle collisioni geometriche.
 *Eredita dalla classe Element
 *
 * @author Matteo Molteni
 * @version 1.0
 */
public class Player extends Element {
    protected int health;
    protected float speed;
    protected float jump;

    /**
     * Il costruttore di Player
     * @param x
     * @param y
     * @param health
     * @param speed
     * @param jump
     */
    public Player(float x, float y, int health, float speed, float jump) {
        super(x, y, 51, 70);
        this.health = health;
        this.speed = speed;
        this.jump = jump;
    }

    /**
     * Restituisce health
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * Modifica health
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Restituisce speed
     * @return
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Modifica speed
     * @param speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Restituisce jump
      * @return
     */
    public float getJump() {
        return jump;
    }

    /**
     * modifica il salto
     * @param jump
     */
    public void setJump(float jump) {
        this.jump = jump;
    }

    /**
     * Converte la posizione e le dimensioni attuali del giocatore in un oggetto {@link Rectangle}.
     * Questo rettangolo viene utilizzato principalmente da libGDX per il calcolo e la
     * risoluzione delle collisioni (hitbox).
     *
     * @return un nuovo oggetto {@link Rectangle} basato sulle coordinate X, Y, larghezza e altezza del giocatore
     */
    public Rectangle toRectangle(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
