package ru.academits.trofimov.main;

import ru.academits.trofimov.matrix.Matrix;
import ru.academits.trofimov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, 4, 7});
        Vector vector2 = new Vector(new double[]{4, 6, 2});
        Vector vector3 = new Vector(new double[]{12, 4, 2});
        Vector[] vectorArray = {vector1, vector2, vector3};
        Matrix matrix1 = new Matrix(vectorArray);

        vector1 = new Vector(new double[]{5, 2, 1});
        vector2 = new Vector(new double[]{5, 6, 1});
        vector3 = new Vector(new double[]{3, 2, 7});
        vectorArray = new Vector[]{vector1, vector2, vector3};
        Matrix matrix2 = new Matrix(vectorArray);

        Matrix matrix3 = new Matrix(matrix1);

        System.out.println("Количество строк матрицы: " + matrix2.getRowsNumber());
        System.out.println("Количество столбцов матрицы: " + matrix2.getColumnsNumber());

        Vector vector4 = matrix3.getVectorString(0);
        System.out.println("Получение вектора-строки по индексу: " + vector4.toString());

        vector1 = matrix3.getVectorColumn(0);
        System.out.println("Получение вектора-столбца по индексу: " + vector1.toString());

        double[] array3 = {7, 2, 7};
        vector1 = new Vector(array3);

        matrix3.setVectorString(0, vector1);
        System.out.println("После установки вектора-строки: " + matrix3.toString());

        matrix3.setVectorColumn(0, vector1);
        System.out.println("После установки вектора-столбца: " + matrix3.toString());

        matrix3.transpose();
        System.out.println("Транспонирование матрицы: " + matrix3.toString());

        matrix3.scalarMultiplication(2);
        System.out.println("Умножение на скаляр: " + matrix3.toString());

        System.out.println("Определитель матрицы: " + matrix1.getDeterminant());

        Vector vector5 = matrix3.getVectorMultiplication(vector1);
        System.out.println("Умножение матрицы на вектор: " + vector5.toString());

        matrix3 = Matrix.getSum(matrix1, matrix2);
        System.out.println("Сумма матриц: " + matrix3.toString());

        matrix3 = Matrix.getDifference(matrix1, matrix2);
        System.out.println("Разнсть матриц: " + matrix3.toString());

        matrix3 = Matrix.getMultiplication(matrix1, matrix2);
        System.out.println("Умножение матриц: " + matrix3.toString());
    }
}