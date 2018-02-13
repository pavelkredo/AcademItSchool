package ru.academits.trofimov.main;

import ru.academits.trofimov.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {
    public int compare(Shape a, Shape b) {
        return Double.compare(a.getArea(), b.getArea());
    }
}