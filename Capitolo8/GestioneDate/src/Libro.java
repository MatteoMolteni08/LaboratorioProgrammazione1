import java.time.LocalDate;

public class Libro extends Prodotto {
    int durataGiorni;

    public Libro(int id, LocalDate dataCreazione, int durata) {
        super(id, dataCreazione);
        this.durataGiorni = durata;
    }

    @Override
    LocalDate calcolaDataScadenza() {
        LocalDate dataScadenza = dataCreazione.plusDays(durataGiorni);
        return dataScadenza;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", dataCreazione=" + dataCreazione.format(formato) +
                ", durata=" + durataGiorni +
                ", dataScadenza=" + dataScadenza.format(formato) +
                '}';
    }
}
