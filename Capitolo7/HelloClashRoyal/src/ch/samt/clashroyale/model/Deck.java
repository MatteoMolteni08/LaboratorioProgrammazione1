package ch.samt.clashroyale.model;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card){
        for (Card c: cards){
            if (card.equals(c)){
                System.out.println("La carta è già presente nel deck");
                return;
            }
        }
        cards.add(card);
    }

    public void removeCard(Card card){
        for (Card c: cards){
            if (card.equals(c)){
                cards.remove(card);
                return;
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
