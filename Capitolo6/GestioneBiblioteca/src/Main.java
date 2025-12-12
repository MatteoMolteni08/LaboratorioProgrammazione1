import ch.samt.biblioteca.data.Biblioteca;
import ch.samt.biblioteca.model.Dvd;
import ch.samt.biblioteca.model.ItemBiblioteca;
import ch.samt.biblioteca.model.Libro;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.aggiungiItem(new Libro("A313", "Jurassic Park", 1990, "Fantascienza", "Michael Crichton", 492));
        biblioteca.aggiungiItem(new Dvd("ABC67", "Un film Minecraft", 2025, "Film", "Jared Hess", 101));
        biblioteca.aggiungiItem(new Libro("CB341", "Kendal: Nel regno delle tenebre", 2025, "Avventura", "Kendal", 160));
        biblioteca.aggiungiItem(new Libro("CB341", "Kendal: Nel regno delle tenebre", 2025, "Avventura", "Kendal", 160));
        System.out.println(biblioteca.getCatalogo());

    }
}