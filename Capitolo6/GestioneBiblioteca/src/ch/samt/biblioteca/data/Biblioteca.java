package ch.samt.biblioteca.data;
import java.util.*;

import ch.samt.biblioteca.model.*;

public class Biblioteca {
    private ArrayList<ItemBiblioteca> catalogo;
    private Set<String> codiciUsati;
    private Map<String, ArrayList<ItemBiblioteca>> elementiPerAutore;
    private Queue<ItemBiblioteca> prenotazioniFIFO;
    private Stack<ItemBiblioteca> consegneUrgentiLIFO;


    public Biblioteca() {
        this.catalogo = new ArrayList<ItemBiblioteca>();
        codiciUsati = new HashSet<String>();
        elementiPerAutore = new HashMap<String, ArrayList<ItemBiblioteca>>();
        prenotazioniFIFO = new LinkedList<ItemBiblioteca>();
        consegneUrgentiLIFO = new Stack<ItemBiblioteca>();
    }

    public boolean aggiungiItem(ItemBiblioteca item) {
        for (String codice : codiciUsati) {
            if (codice.equals(item.getCodice())) {
                return false;
            }
        }
        catalogo.add(item);
        return true;
    }

    public ArrayList<ItemBiblioteca> getCatalogo() {
        return catalogo;
    }

    public ArrayList<ItemBiblioteca> getElementiDiAutore(String autore) {
        ArrayList<ItemBiblioteca> elementiAutore = new ArrayList<ItemBiblioteca>();
        for (ItemBiblioteca item : catalogo) {
            if (item instanceof Libro) {
                Libro libro = (Libro) item;
                elementiAutore.add(libro);
            }
        }
        return elementiAutore;
    }

    public void aggiungiPrenotazioneFIFO(ItemBiblioteca item) {
        prenotazioniFIFO.add(item);
    }
    public ItemBiblioteca prossimaPrenotazioneFIFO(){
        return prenotazioniFIFO.poll();
    }

    public void aggiungiConsegnaUrgenteLIFO(ItemBiblioteca item) {
        consegneUrgentiLIFO.add(item);
    }
    public ItemBiblioteca prossimaConsegnaLIFO() {
        return consegneUrgentiLIFO.peek();
    }
}
