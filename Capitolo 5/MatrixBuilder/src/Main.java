//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Matrice matrice = new Matrice();
        Matrice matrice2 = new Matrice(6,3);

        matrice.popolaMatrice();
        matrice2.popolaMatrice();
        matrice.stampaMatrice();
        matrice2.stampaMatrice();
        System.out.println(matrice.getCella(2,4));
        System.out.println(matrice2.getCella(2,4));
        matrice2.setCella(0,0,3);
        matrice2.stampaMatrice();
    }
}