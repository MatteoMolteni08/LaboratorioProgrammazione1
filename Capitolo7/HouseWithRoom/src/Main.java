//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        House house = new House();

        house.aggiungiStanza("Cucina", 25);
        house.aggiungiStanza("Salotto", 50);
        house.aggiungiStanza("Camera da letto", 10);

        house.visualizzaStanze();

        house.aggiungiStanza("Sala da pranzo", 30);
        house.aggiungiStanza("Mansarda", 75);
        house.visualizzaStanze();
        System.out.println(house.getSuperficieTotale());
        System.out.println(house.trovaStanza("Salotto"));
        house.rimuoviStanza("Mansarda");

        house.visualizzaStanze();
    }
}