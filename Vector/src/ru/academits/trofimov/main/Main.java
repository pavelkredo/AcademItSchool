package ru.academits.trofimov.main;

import ru.academits.trofimov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = {1, 2, 3, 4, 5, 6};
        double[] array2 = {2, 3, 4, 5, 6, 7, 8, 9};
        Vector vector1 = new Vector(7, array1);
        Vector vector2 = new Vector(8, array2);

        System.out.println("Размерность вектора: " + vector1.getSize());
        System.out.println("Компоненты вектора: " + vector1.toString());

        System.out.println("Значение суммы двух векторов: " + vector1.getSum(vector2));
        System.out.println("Значение разности двух векторов: " + vector1.getDifference(vector2));

        System.out.println("Разворот вектора: " + vector2.getRotation());
        System.out.println("Установка компоненты по индексу: " + vector2.setComponent(2, 2));

        System.out.println("Длина вектора: " + vector1.getLength());

        if (vector1.equals(vector2)) {
            System.out.println("Массивы имеют одинаковую размерность и компоненты.");
        } else {
            System.out.println("Массивы не имеют одинаковую размерность и компоненты.");
        }

        double valueOfMupltiplication = Vector.getMultiplication(vector1, vector2);
        System.out.println("Значение скалярного произведения векторов: " + valueOfMupltiplication);

        Vector vector3 = Vector.getSum(vector1, vector2);
        System.out.println("Сумма двух векторов: " + vector3);

        vector3 = Vector.getDifference(vector1, vector2);
        System.out.println("Разность двух векторов: " + vector3);
    }
}