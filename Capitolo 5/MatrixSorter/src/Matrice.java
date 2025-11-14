import java.util.Random;

public class Matrice {
    private int[][] matrice;
    private int colonne;
    private int righe;
    Random random = new Random();

    public int getColonne() {
        return colonne;
    }

    public int getRighe() {
        return righe;
    }

    public Matrice(int colonne, int righe) {
        this.colonne = colonne;
        this.righe = righe;
        this.matrice = new int[righe][colonne];
    }

    public Matrice(){
        this.matrice = new int[5][5];
    }
    public void stampaMatrice(){
        for (int i = 0; i < matrice.length; i++) {
            System.out.print("{");
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j]);
                if (j < matrice[i].length - 1) {
                    System.out.print("; ");
                }
            }
            System.out.println("}");
        }
    }

    public void popolaMatrice(){
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                matrice[i][j] = random.nextInt(1,10);
            }
        }
    }


}
