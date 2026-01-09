package ch.samt.clashroyale.battle;
import ch.samt.clashroyale.model.Card;
import ch.samt.clashroyale.model.SpellCard;
import ch.samt.clashroyale.model.TroopCard;

public class BattleEngine {
    public void playCard(Card card){
        TroopCard troopCard;
        SpellCard spellCard;
        if (card instanceof TroopCard){
            troopCard = (TroopCard) card;
            System.out.println(troopCard.getDamage() + " " + troopCard.getHitPoints());
        } else if (card instanceof SpellCard) {
            spellCard = (SpellCard) card;
            System.out.println(spellCard.getAreaDamage() + " " + spellCard.getRadius());
        }
    }
}
