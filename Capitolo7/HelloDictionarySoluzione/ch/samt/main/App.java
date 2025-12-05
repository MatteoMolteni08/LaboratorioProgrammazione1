package ch.samt.main;
import ch.samt.dictionary.Dictionary;
import ch.samt.dictionary.Entry;

public class App {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        Entry e1 = new Entry("gatto", "cat");
        Entry e2 = new Entry("cane", "dog");
        Entry e3 = new Entry("tavolo", "table");

        dict.aggiungi(e1);
        dict.aggiungi(e2);
        dict.aggiungi(e3);

        System.out.println(dict.cerca("cane"));
        dict.stampaTutto();


    }
}