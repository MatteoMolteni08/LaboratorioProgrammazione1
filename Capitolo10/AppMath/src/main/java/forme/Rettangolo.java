package main.java.forme;

public class Rettangolo extends Forma{
    private double base;
    private double altezza;

    public Rettangolo(double base, double altezza) {
        if (base <= 0 || altezza <=0){
            throw new IllegalArgumentException("I valori non possono essere minori o uguali a 0");
        }
        this.base = base;
        this.altezza = altezza;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return base * altezza;
    }

    @Override
    public double perimetro() {
        return (base + altezza)*2;
    }
}
