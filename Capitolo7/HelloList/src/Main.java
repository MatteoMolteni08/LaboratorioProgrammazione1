import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Carrello c1 = new Carrello("Spesa");

        Alimento pasta = new Alimento("Tagliatelle", "Pasta", "01.01.2050", 4);
        c1.AggiungiAlimento(pasta);
        c1.AggiungiAlimento("Corona di Sils", "Pane", "23.11.2025", 10);
    }
}