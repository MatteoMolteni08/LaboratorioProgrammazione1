package io.labo1.progetto;

import io.labo1.progetto.Player;
import io.labo1.progetto.Ostacle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameMainTest {

    @Test
    public void testOstacoloInfliggeDanno() {
        // 1. Configurazione: Creiamo un giocatore con 100 di punti salute
        Player player = new Player(0f, 0f, 100, 220f, 220f);

        // Creiamo un ostacolo (passando le dimensioni come interi: 40, 40)
        Ostacle ostacolo = new Ostacle(100f, 90f, 40, 40, true, 1.5f);

        // Modifichiamo direttamente la vita del player simulando l'interazione per il test di logica
        player.setHealth((int) (player.getHealth() - 10 * ostacolo.getDamageCoefficient()));

        // 3. Verifica: 100 - (10 * 1.5) = 85. Verifichiamo la corretta riduzione
        assertEquals(85, player.getHealth(), "La salute del giocatore non è stata ridotta correttamente!");
    }

    @Test
    public void testMorteGiocatore() {
        // Creiamo un giocatore in fin di vita (10 HP)
        Player player = new Player(0f, 0f, 10, 220f, 220f);
        Ostacle ostacoloLetale = new Ostacle(100f, 90f, 40, 40, true, 5.0f);

        // Applichiamo un danno superiore ai punti salute rimasti
        player.setHealth((int) (player.getHealth() - 10 * ostacoloLetale.getDamageCoefficient()));

        // Verifica che il valore della vita sia sceso a zero o sotto zero per scatenare la morte
        assertTrue(player.getHealth() <= 0, "Il giocatore dovrebbe essere morto (salute minore o uguale a 0).");
    }
}
