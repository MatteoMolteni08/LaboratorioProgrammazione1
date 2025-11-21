public class Room {
    private String nome // es. "Cucina", "Camera Matrimoniale"
    private double superficieMq // es. 15.5

    public Room(String nome, double superficieMq) {
        this.nome = nome;
        this.superficieMq = superficieMq;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getSuperficieMq() {
        return superficieMq;
    }
    public void setSuperficieMq(double superficieMq) {
        this.superficieMq = superficieMq;
    }

    @Override
    public String toString() {
        return nome + ' (' + superficieMq +
                ' mq)';
    }
}
