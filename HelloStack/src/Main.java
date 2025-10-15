//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        metodoA(0);
    }
    public static void metodoA(int n) {
        metodoB(n);
    }
    public static void metodoB(int n) {
        metodoC(n);
    }
    public static void metodoC(int n) {
        try {
            int result = 1 / n;
            System.out.println("Risultato " + result);
        }catch (ArithmeticException ea) {
            System.out.println("Errore: " + ea);
        }
    }
}