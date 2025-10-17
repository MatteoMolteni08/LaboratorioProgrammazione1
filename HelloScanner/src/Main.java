import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int eta = 0;
        String nome = "";
        String cognome = "";

        Scanner input = new Scanner(System.in); //Creato oggetto scanner
        try{
            System.out.print("Inserisci il tuo nome: ");
            nome = input.nextLine(); //Letto input tastiera e salvato nella variabile nome

            System.out.print("Inserisci il tuo cognome: ");
            cognome = input.nextLine(); //Input cognome

            System.out.print("Inserisci la tua età: ");
            eta = input.nextInt();//Input età
        }catch (InputMismatchException ime){
            System.out.println("Inserire l'età in formato numerico");
        }

        System.out.println("Nome: " + nome + " | cognome: " + cognome + " | età: " + eta);
    }
}