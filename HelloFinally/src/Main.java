public class Main {
    public static void main(String[] args) {
        //Cicliamo args per verificare se sono presenti solo numeri
        for (String arg : args) {
            try {
                double numero = Double.parseDouble(arg);
                System.out.println("Numero valido: " + numero);
            } catch (NumberFormatException e) {
                System.out.println("Valore non valido: \"" + arg + "\"");
            } finally {
                System.out.println("Operazione eseguita");
            }
        }
        System.out.println("Fine del programma.");
    }
}