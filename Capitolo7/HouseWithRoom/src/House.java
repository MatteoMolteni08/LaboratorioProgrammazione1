import java.util.ArrayList;

public class House {
    private ArrayList<Room> stanze;
    private double superficeTotale;

    public House() {
        stanze = new ArrayList<Room>();
    }

    public void aggiungiStanza(String nome, double superficieMq) {
        stanze.add(new Room(nome, superficieMq));
    }

    public void visualizzaStanze() {
        if (stanze.isEmpty()) {
            System.out.println("La casa è vuota");
        }else {
            for (Room room : stanze) {
                System.out.println(room.toString());
            }
        }
    }

    public double getSuperficieTotale() {
        superficeTotale = 0;
        for (Room room : stanze) {
            superficeTotale += room.getSuperficieMq();
        }
        return superficeTotale;
    }

    public String trovaStanza(String nome) {
        for (Room room : stanze) {
            if (room.getNome().equals(nome)) {
                return room.toString();
            }
        }
        return "Stanza non trovata";
    }

    public void rimuoviStanza(String nome) {
        for (Room stanza : stanze) {
            if (stanza.getNome().equals(nome)) {
                stanze.remove(stanza);
            }
        }
    }
}
