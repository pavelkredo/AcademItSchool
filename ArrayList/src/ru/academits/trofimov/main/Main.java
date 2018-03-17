package ru.academits.trofimov.main;

import ru.academits.trofimov.arraylist.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>();
        a.add("ааа");
        a.add("bbb");
        a.add("ccc");

        ArrayList<String> b = new ArrayList<>();
        b.add("r2r");
        b.add("e2e");
        b.add("p2p");

        a.addAll(b);
        System.out.println(a);
    }
}
