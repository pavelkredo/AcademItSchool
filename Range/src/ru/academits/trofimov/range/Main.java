package ru.academits.trofimov.range;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(15, 27);
        Range range2 = new Range(7, 20);
        System.out.println("Длина интервала равна: " + range1.getIntervalLength());

        double number = 22;
        if (range1.isInside(number)) {
            System.out.println("Число принадлежит интервалу.");
        } else {
            System.out.println("Число не принадлежит интервалу.");
        }

        Range rangeCrossing = range1.getRangeCrossing(range2);
        if (rangeCrossing != null) {
            System.out.print("Пересечение интервалов: ");
            rangeCrossing.print();
        } else {
            System.out.print("Пересечение интервалов отсутствует.");
        }
        System.out.println();

        Range[] rangeCombination = range1.getRangeCombination(range2);
        if (rangeCombination != null) {
            System.out.print("Объеденение: ");
            rangeCombination[0].print();

            if (rangeCombination[1] != null) {
                System.out.print(", ");
                rangeCombination[1].print();
            }
        } else {
            System.out.print("Объеденение интервалов отсутствует.");
        }
        System.out.println();

        Range[] rangeDifference = range1.getRangeDifference(range2);
        if (rangeDifference != null) {
            System.out.print("Разность: ");
            rangeDifference[0].print();

            if (rangeDifference[1] != null) {
                System.out.print(", ");
                rangeDifference[1].print();
            }
        } else {
            System.out.print("Разность интервалов отсутствует.");
        }
    }
}