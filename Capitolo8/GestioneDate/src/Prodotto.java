import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Prodotto {
    protected int id;
    protected LocalDate dataCreazione;
    protected LocalDate dataScadenza;
    protected DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    abstract LocalDate calcolaDataScadenza();
    void isScaduto(LocalDate data) {
        if (dataScadenza.isBefore(data)){
            System.out.println("Il prodotto è scaduto");
        }
    }

    public Prodotto(int id, LocalDate dataCreazione) {
        this.id = id;
        this.dataCreazione = dataCreazione;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", dataCreazione=" + dataCreazione.format(formato) +
                ", dataScadenza=" + dataScadenza.format(formato) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
