package ru.academits.trofimov.matrix;

import ru.academits.trofimov.vector.*;

import java.util.Arrays;

public class Matrix {
    private double[][] matrix;

    public Matrix(int n, int m) {
        matrix = new double[n][m];
    }

    public Matrix(Matrix matrix) {
        this(matrix.matrix);
    }

    public Matrix(double[][] array) {
        matrix = new double[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            matrix[i] = Arrays.copyOf(array[i], array[i].length);
        }
    }

    public Matrix(Vector[] array) {
        for (int i = 0; i < array.length; i++) {
            double[] temp = array[i].getVector();

            for (int j = 0; j < temp.length; i++) {
                matrix[i][j] = temp[i];
            }
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    // получение количества строк матрицы
    public int getStringsNumber() {
        return matrix.length;
    }

    // получение количества стролбцов матрицы
    public int getColumnsNumber() {
        return matrix[0].length;
    }

    // получение вектора-строки по индексу
    public Vector getVectorString(int index) {
        return new Vector(matrix[index].length, Arrays.copyOf(matrix[index], matrix[index].length));
    }

    // задание вектора-строки по индексу
    public void setVectorString(int index, Vector vector) {
        matrix[index] = Arrays.copyOf(vector.getVector(), vector.getSize());
    }

    // получение вектора-столбца по индексу
    public Vector getVectorColumn(int index) {
        double[] vector = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            vector[i] = matrix[i][index];
        }

        return new Vector(vector.length, vector);
    }

    // задание вектора-столбца по индексу
    public void setVectorColumn(int index, Vector vector) {
        double[] array = vector.getVector();
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][index] = array[i];
        }
    }

    // транспонирование матрицы
    public void transpose() {
        double[][] temp = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = matrix[j][i];
            }
        }

        matrix = new double[temp.length][temp[0].length];
        for (int i = 0; i < temp.length; i++) {
            matrix[i] = Arrays.copyOf(temp[i], temp[i].length);
        }
    }

    // умножение на скаляр
    public void getScalarMultiplication(double scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= scalar;
            }
        }
    }

    // получение определителя матрицы
    private double calculateDeterminant(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Число строк матрицы должно быть равно числу столбцов.");
        }

        if (matrix.length == 1) {
            return matrix[0][0];
        } else if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double[][] array = new double[matrix.length - 1][matrix.length - 1];
        double sum = 0;
        int exception = 0;

        for (int i = 0; i < matrix.length; i++) {
            int x = 0;

            for (int j = 0; j < matrix.length; j++) {
                if (j != exception) {
                    int y = 0;

                    for (int k = 1; k < matrix.length; k++) {
                        array[x][y] = matrix[j][k];
                        y++;
                    }

                    x++;
                }
            }

            sum += Math.pow(-1, i) * matrix[i][0] * calculateDeterminant(array);
            exception++;
        }

        return sum;
    }

    public double getMatrixDeterminant() {
        return calculateDeterminant(matrix);
    }

    // умножение матрицы на вектор
    public Vector getVectorMultiplication(Vector vector) {
        if (matrix[0].length != vector.getVector().length) {
            throw new ArithmeticException("Число столбцов в матрице должно быть равно количеству элементов вектора.");
        }

        double[] newVector = new double[vector.getVector().length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newVector[i] += matrix[i][j] * vector.getVector()[j];
            }
        }

        return new Vector(vector.getVector().length, newVector);
    }

    // сложение матриц
    public Matrix getSum(Matrix matrix) {
        if (this.matrix.length != matrix.matrix.length || this.matrix[0].length != matrix.matrix[0].length) {
            throw new ArithmeticException("Для сложения матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                this.matrix[i][j] += matrix.matrix[i][j];
            }
        }

        return this;
    }

    // вычитание матриц
    public Matrix getDifference(Matrix matrix) {
        if (this.matrix.length != matrix.matrix.length || this.matrix[0].length != matrix.matrix[0].length) {
            throw new ArithmeticException("Для вычитания матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                this.matrix[i][j] -= matrix.matrix[i][j];
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
        if (matrix1.matrix[0].length != matrix2.matrix.length) {
            throw new ArithmeticException("Количество столбцов первой матрицы должно быть равно количеству " +
                    "строк второй матрицы");
        }

        double[][] array = new double[3][3];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int sum = 0;

                for (int l = 0; l < array.length; l++) {
                    sum += matrix1.matrix[i][l] * matrix2.matrix[l][j];
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

            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(matrix[i][j]);

                if (j != matrix[0].length - 1) {
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