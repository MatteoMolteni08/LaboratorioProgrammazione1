import java.time.LocalDate;

public class Abbonamento extends Prodotto {
    int durataMesi;

    public Abbonamento(int id, LocalDate dataCreazione, int durataMesi) {
        super(id, dataCreazione);
        this.durataMesi = durataMesi;
    }

    @Override
    LocalDate calcolaDataScadenza() {
        dataScadenza = dataCreazione.plusMonths(durataMesi);
        return dataScadenza;
    }
}
