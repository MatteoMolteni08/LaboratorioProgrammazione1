import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Alimento> alimenti = new ArrayList<Alimento>();
        Alimento a = new Alimento("Spaghetti", "Pasta", "01.06.2026", 4);
        Alimento a2 = new Alimento("Baguette", "Pane", "23.11.2025", 5);
        Alimento a3 = new Alimento("Pesto calabrese", "Sugo", "02.04.2026", 4);


        alimenti.add(a);
        alimenti.add(a2);
        alimenti.add(a3);
        alimenti.add(new Alimento("Porro", "Verdura", "24.11.2025", 16));

    }
}