package ch.samt.main;

import ch.samt.dictionary.Dictionary;
import ch.samt.dictionary.Entry;

public class App {
    public static void main(String[] args) {
        Dictionary d = new Dictionary();
        d.aggiungi(new Entry("Porro", "Leek"));
        d.aggiungi(new Entry("Gatto", "Cat"));
        d.aggiungi(new Entry("Cane", "Dog"));
        d.aggiungi(new Entry("Baguette", "Baguette"));
        d.aggiungi(new Entry("Giacomo", "Jack"));

        System.out.println(d.cerca("Gatto").toString());
        System.out.println(d.cerca("Cane").toString());
        System.out.println(d.cerca("Baguette").toString());
        System.out.println(d.cerca("Porro").toString());
    }
}
