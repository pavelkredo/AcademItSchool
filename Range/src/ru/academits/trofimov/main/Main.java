package ru.academits.trofimov.main;

import ru.academits.trofimov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(15, 25);
        Range range2 = new Range(25, 37);
        System.out.println("Длина интервала равна: " + range1.getLength());

        double number = 22;
        if (range1.isInside(number)) {
            System.out.println("Число принадлежит интервалу.");
        } else {
            System.out.println("Число не принадлежит интервалу.");
        }

        Range rangeIntersection = range1.getIntersection(range2);
        if (rangeIntersection != null) {
            System.out.print("Пересечение интервалов: ");
            rangeIntersection.print();
        } else {
            System.out.print("Пересечение интервалов отсутствует.");
        }
        System.out.println();

        Range[] rangeUnion = range1.getUnion(range2);
        System.out.print("Объеденение: ");
        for (Range union : rangeUnion) {
            union.print();
        }
        System.out.println();

        Range[] rangeDifference = range1.getDifference(range2);
        if (rangeDifference.length != 0) {
            System.out.print("Разность: ");

            for (Range difference : rangeDifference) {
                difference.print();
            }
        } else {
            System.out.print("Разность интервалов отсутствует.");
        }
    }
}