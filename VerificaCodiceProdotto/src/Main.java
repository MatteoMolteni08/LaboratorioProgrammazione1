//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Inserisci almeno un argomento");
        }

        for(String arg: args){
            if (arg.substring(0,5).equals("PROD-")) {
                try {
                    int codiceProdotto = Integer.parseInt(arg.substring(5, 9));
                    System.out.println("Codice valido: " + arg);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Codice invalido: " + arg);
                    System.out.println("Motivo: Il codice deve contenere almeno 4 cifre numeriche dopo 'PROD-'.");
                }
            } else{
                System.out.println("Codice invalido: " + arg);
                System.out.println("Motivo: Il codice deve iniziare con 'PROD-'.");
            }
        }
    }
}