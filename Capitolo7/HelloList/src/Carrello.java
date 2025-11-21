import java.util.ArrayList;

public class Carrello {
    private String nome;
    private ArrayList<Alimento> alimenti;

    public Carrello(String nome) {
        this.nome = nome;
        alimenti = new ArrayList<>();
    }

    public void AggiungiAlimento(Alimento alimento) {
        alimenti.add(alimento);
    }
    
}
