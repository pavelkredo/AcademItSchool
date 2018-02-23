package ru.academits.trofimov.matrix;

import ru.academits.trofimov.vector.*;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrix;

    public Matrix(int n, int m) {
        for (int i = 0; i < n; i++) {
            matrix[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.matrix);
    }

    public Matrix(double[][] array) {
        int maxLength = 0;

        for (double[] e : array) {
            if (e.length > maxLength) {
                maxLength = e.length;
            }
        }

        matrix = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            matrix[i] = new Vector(Arrays.copyOf(array[i], maxLength));
        }
    }

    public Matrix(Vector[] array) {
        int maxLength = 0;

        for(Vector e : array) {
            if (e.getSize() > maxLength) {
                maxLength = e.getSize();
            }
        }

        matrix = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            matrix[i] = new Vector(Arrays.copyOf(array[i].getVector(), maxLength));
        }
    }

    public Vector[] getMatrix() {
        return matrix;
    }

    // получение количества строк матрицы
    public int getStringsNumber() {
        return matrix.length;
    }

    // получение количества стролбцов матрицы
    public int getColumnsNumber() {
        return matrix[0].getSize();
    }

    // получение вектора-строки по индексу
    public Vector getVectorString(int index) {
        return matrix[index];
    }

    // задание вектора-строки по индексу
    public void setVectorString(int index, Vector vector) {
        matrix[index] = vector;
    }

    // получение вектора-столбца по индексу
    public Vector getVectorColumn(int index) {
        double[] vector = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            vector[i] = matrix[i].getVector()[index];
        }

        return new Vector(vector.length, vector);
    }

    // задание вектора-столбца по индексу
    public void setVectorColumn(int index, Vector vector) {
        double[] array = vector.getVector();
        for (int i = 0; i < matrix.length; i++) {
            matrix[i].setVector(index, array[i]);
        }
    }

    // транспонирование матрицы
    public void transpose() {
        double[][] temp = new double[matrix[0].getSize()][matrix.length];

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = matrix[j].getVector()[i];
            }
        }

        matrix = new Vector[temp.length];
        for (int i = 0; i < temp.length; i++) {
            matrix[i] = new Vector(temp[i]);
        }
    }

    // умножение на скаляр
    public void getScalarMultiplication(double scalar) {
        for (Vector e : matrix) {
            for (int j = 0; j < matrix[0].getSize(); j++) {
                e.setVector(j, e.getVector()[j] * scalar);
            }
        }
    }

    // получение определителя матрицы
    private double calculateDeterminant(Vector[] matrix) {
        if (matrix.length != matrix[0].getSize()) {
            throw new IllegalArgumentException("Число строк матрицы должно быть равно числу столбцов.");
        }

        if (matrix.length == 1) {
            return matrix[0].getVector()[0];
        } else if (matrix.length == 2) {
            return matrix[0].getVector()[0] * matrix[1].getVector()[1] - matrix[0].getVector()[1] * matrix[1].getVector()[0];
        }

        Vector[] array = new Vector[matrix.length - 1];
        for(int i = 0; i < array.length; i++) {
            array[i] = new Vector(matrix.length - 1);
        }

        double sum = 0;
        int exception = 0;

        for (int i = 0; i < matrix.length; i++) {
            int x = 0;

            for (int j = 0; j < matrix.length; j++) {
                if (j != exception) {
                    int y = 0;

                    for (int k = 1; k < matrix.length; k++) {
                        array[x].setVector(y, matrix[j].getVector()[k]);
                        y++;
                    }

                    x++;
                }
            }

            sum += Math.pow(-1, i) * matrix[i].getVector()[0] * calculateDeterminant(array);
            exception++;
        }
        return sum;
    }

    public double getMatrixDeterminant() {
        return calculateDeterminant(matrix);
    }

    // умножение матрицы на вектор
    public Vector getVectorMultiplication(Vector vector) {
        if (matrix[0].getSize() != vector.getVector().length) {
            throw new ArithmeticException("Число столбцов в матрице должно быть равно количеству элементов вектора.");
        }

        double[] newVector = new double[vector.getVector().length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].getSize(); j++) {
                newVector[i] += matrix[i].getVector()[j] * vector.getVector()[j];
            }
        }
        return new Vector(vector.getVector().length, newVector);
    }

    // сложение матриц
    public Matrix getSum(Matrix matrix) {
        if (this.matrix.length != matrix.matrix.length || this.matrix[0].getSize() != matrix.matrix[0].getSize()) {
            throw new ArithmeticException("Для сложения матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].getSize(); j++) {
                this.matrix[i].setVector(j, this.matrix[i].getVector()[j] + matrix.matrix[i].getVector()[j]);
            }
        }
        return this;
    }

    // вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        if (this.matrix.length != matrix.matrix.length || this.matrix[0].getSize() != matrix.matrix[0].getSize()) {
            throw new ArithmeticException("Для вычитания матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].getSize(); j++) {
                this.matrix[i].setVector(j, this.matrix[i].getVector()[j] - matrix.matrix[i].getVector()[j]);
            }
        }
        return this;
    }

    // сложение матриц (статический метод)
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix(matrix1);
        return matrix.getSum(matrix2);
    }

    // вычитание матриц (статический метод)
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix matrix = new Matrix(matrix1);
        return matrix.getDifference(matrix2);
    }

    // умножение матриц
    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix[0].getSize() != matrix2.matrix.length) {
            throw new ArithmeticException("Количество столбцов первой матрицы должно быть равно количеству " +
                    "строк второй матрицы");
        }

        double[][] array = new double[matrix1.matrix.length][matrix1.matrix.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int sum = 0;

                for (int l = 0; l < array.length; l++) {
                    sum += matrix1.matrix[i].getVector()[l] * matrix2.matrix[l].getVector()[j];
                }

                array[i][j] = sum;
            }
        }

        return new Matrix(array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < matrix.length; i++) {
            sb.append("{ ");

            for (int j = 0; j < matrix[0].getSize(); j++) {
                sb.append(matrix[i].getVector()[j]);

                if (j != matrix[0].getSize() - 1) {
                    sb.append(", ");
                } else {
                    sb.append(" ");
                }
            }

            if (i != matrix.length - 1) {
                sb.append("}, ");
            } else {
                sb.append("} }");
            }
        }

        return sb.toString();
    }
}