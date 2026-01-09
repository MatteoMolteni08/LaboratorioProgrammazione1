import ch.samt.clashroyale.battle.BattleEngine;
import ch.samt.clashroyale.model.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TroopCard troop1 = new TroopCard("Scheletri", 1, 6, 81, 81);
        TroopCard troop2 = new TroopCard("P.E.K.K.A", 7, 7, 3760, 816);
        SpellCard spell1 = new SpellCard("Giant snowball", 2, 2, 175, 2.5);
        Deck deck = new Deck();
        Player player = new Player("Nickgal", deck);

        deck.addCard(troop1);
        deck.addCard(troop2);
        deck.addCard(spell1);

        BattleEngine battleEngine = new BattleEngine();
        battleEngine.playCard(troop1);
        battleEngine.playCard(troop2);
        battleEngine.playCard(spell1);
    }
}