public class Matrice {
    private double[][] matrice;
    private int colonne;
    private int righe;

    public int getColonne() {
        return colonne;
    }

    public int getRighe() {
        return righe;
    }

    public Matrice(int colonne, int righe) {
        this.colonne = colonne;
        this.righe = righe;
        this.matrice = new double[righe][colonne];
    }

    public Matrice(){
        this.matrice = new double[5][5];
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
                matrice[i][j] = Math.random();
            }
        }
    }

    public double getCella(int row, int col) {
        return matrice[row][col];
    }

    public void setCella(int row, int col, double val) {
        try {
            matrice[row][col] = val;
        }catch (NumberFormatException nfe){
            System.out.println("Errore! Inserisci un numero");
        }
    }

}
