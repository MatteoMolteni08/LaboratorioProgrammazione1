//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5, 6};
        int[] array2 = {1, 2, 3, 4, 5, 6};
        int[] array3 = {3, 1, 5, 2};
        int[] array4 = {1, 1, 5, 2, 5, 3};

        System.out.println("array1 = array2: " + Utility.confrontaArray(array1, array2));
        System.out.println("array1 = array3: " + Utility.confrontaArray(array1, array3));
        System.out.println("array1 = array4: " + Utility.confrontaArray(array1, array4));
    }
}