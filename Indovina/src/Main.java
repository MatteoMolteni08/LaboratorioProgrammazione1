import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rnd = new Random();

        int numero = 0;
        int indovinaNumero = rnd.nextInt(1, 101) ;
        int tentativoNumero = 0;

        System.out.println("Indovina il numero tra 1 e 100");
        while(numero != indovinaNumero){
            while (true){
                try {
                    System.out.print("Il tuo tentativo: ");
                    numero = input.nextInt();
                    input.nextLine();
                    break;
                } catch (InputMismatchException ime){
                    System.out.println("Inserire un numero");
                }
            }
            if (numero > indovinaNumero){
                System.out.println("Troppo alto!");
            } else if (numero < indovinaNumero){
                System.out.println("Troppo basso!");
            }
            tentativoNumero++;
        }
        if (tentativoNumero < 10){
            System.out.print("Bravo!^w^ ");
        } else if (tentativoNumero < 15) {
            System.out.print("Buono!:3 ");
        }else if (tentativoNumero < 20){
            System.out.print("Discreto!:) ");
        }else if (tentativoNumero < 25){
            System.out.print("Ci siamo quasi!;) ");
        }else if (tentativoNumero < 30){
            System.out.print("Possiamo migliorare!:| ");
        }else if (tentativoNumero < 40) {
            System.out.print("Non ci siamo!:( ");
        } else if (tentativoNumero < 50) {
            System.out.print("AAAAARGH! Non ci siamo proprio!>:( ");
        }else if (tentativoNumero >= 50){
            System.out.print("Malissimo! Non provare tutti i numeri!-_- ");
        }
        System.out.println("Hai indovinato in " + tentativoNumero + " tentativi");
    }
}