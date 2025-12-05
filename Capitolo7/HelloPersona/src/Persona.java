import java.util.Objects;

public class Persona {
    private String nome;
    private int anni;
    private String avs;

    public Persona(String nome, int anni, String avs) {
        this.nome = nome;
        this.anni = anni;
        this.avs = avs;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getAnni() {
        return anni;
    }
    public void setAnni(int anni) {
        this.anni = anni;
    }
    public String getAvs() {
        return avs;
    }
    public void setAvs(String avs) {
        this.avs = avs;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nome='" + nome + '\'' +
                ", anni=" + anni +
                ", avs='" + avs + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return anni == persona.anni && Objects.equals(nome, persona.nome) && Objects.equals(avs, persona.avs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, anni, avs);
    }
}
