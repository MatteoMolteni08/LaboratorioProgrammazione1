import java.time.LocalDate;

public abstract class Prenotazione implements Documentabile {
    private String codiceVolo;
    private LocalDate dataPartenza;
    private double prezzoBase;

    public Prenotazione(String codiceVolo, LocalDate dataPartenza, double prezzoBase) {
        if (codiceVolo.equals("")) {
            throw new IllegalArgumentException("Codice volo non può essere vuoto");
        } else if (dataPartenza.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data non può essere precedente alla data odierna");
        } else if (prezzoBase <= 0) {
            throw new IllegalArgumentException("prezzoBase non può essere negativo");
        }
        this.codiceVolo = codiceVolo;
        this.dataPartenza = dataPartenza;
        this.prezzoBase = prezzoBase;
    }

    public String getCodiceVolo() {
        return codiceVolo;
    }
    public void setCodiceVolo(String codiceVolo) {
        this.codiceVolo = codiceVolo;
    }

    public LocalDate getDataPartenza() {
        return dataPartenza;
    }
    public void setDataPartenza(LocalDate dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public double getPrezzoBase() {
        return prezzoBase;
    }
    public void setPrezzoBase(double prezzoBase) {
        this.prezzoBase = prezzoBase;
    }

    abstract String generaTicket();
}
