package ru.academits.trofimov.matrix;

import ru.academits.trofimov.vector.*;

public class Matrix {
    private Vector[] matrixRows;

    public Matrix(int n, int m) {
        if (n == 0 || m == 0) {
            throw new ArithmeticException("Матрица не может иметь размерность \"0\"");
        }

        for (int i = 0; i < n; i++) {
            matrixRows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.matrixRows);
    }

    public Matrix(double[][] array) {
        int maxLength = 0;

        for (double[] e : array) {
            if (e.length > maxLength) {
                maxLength = e.length;
            }
        }

        if (array.length == 0 || maxLength == 0) {
            throw new ArithmeticException("Матрица не может иметь размерность \"0\"");
        }

        matrixRows = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            matrixRows[i] = new Vector(maxLength, array[i]);
        }
    }

    public Matrix(Vector[] array) {
        int maxLength = 0;

        for (Vector e : array) {
            if (e.getSize() > maxLength) {
                maxLength = e.getSize();
            }
        }

        if (array.length == 0 || maxLength == 0) {
            throw new ArithmeticException("Матрица не может иметь размерность \"0\"");
        }

        matrixRows = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            matrixRows[i] = new Vector(maxLength);

            for (int j = 0; j < array[i].getSize(); j++) {
                matrixRows[i].setComponent(array[i].getComponent(j), j);
            }
        }
    }

    // получение количества строк матрицы
    public int getRowsNumber() {
        return matrixRows.length;
    }

    // получение количества стролбцов матрицы
    public int getColumnsNumber() {
        return matrixRows[0].getSize();
    }

    // получение вектора-строки по индексу
    public Vector getVectorString(int index) {
        return new Vector(matrixRows[index]);
    }

    // задание вектора-строки по индексу
    public void setVectorString(int index, Vector vector) {
        if(matrixRows[index].getSize() != vector.getSize()) {
            throw new ArithmeticException("Задаваемый вектор должен соответствовать длине строки матрицы.");
        }
        matrixRows[index] = new Vector(vector);
    }

    // получение вектора-столбца по индексу
    public Vector getVectorColumn(int index) {
        double[] vector = new double[matrixRows.length];

        for (int i = 0; i < matrixRows.length; i++) {
            vector[i] = matrixRows[i].getComponent(index);
        }
        return new Vector(vector.length, vector);
    }

    // задание вектора-столбца по индексу
    public void setVectorColumn(int index, Vector vector) {
        if(matrixRows.length != vector.getSize()) {
            throw new ArithmeticException("Задаваемый вектор должен соответствовать количеству строк матрицы.");
        }

        for (int i = 0; i < matrixRows.length; i++) {
            matrixRows[i].setComponent(vector.getComponent(i), index);
        }
    }

    // транспонирование матрицы
    public void transpose() {
        Vector[] temp = new Vector[matrixRows[0].getSize()];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = new Vector(matrixRows.length);
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].getSize(); j++) {
                temp[i].setVector(j, matrixRows[j].getComponent(i));
            }
        }

        matrixRows = temp;
    }

    // умножение на скаляр
    public void getScalarMultiplication(double scalar) {
        for (Vector e : matrixRows) {
            e.getMultiplication(scalar);
        }
    }

    // получение определителя матрицы
    private double calculateDeterminant(Vector[] matrixStrings) {
        if (matrixStrings.length != matrixStrings[0].getSize()) {
            throw new IllegalArgumentException("Число строк матрицы должно быть равно числу столбцов.");
        }

        if (matrixStrings.length == 1) {
            return matrixStrings[0].getComponent(0);
        } else if (matrixStrings.length == 2) {
            return matrixStrings[0].getComponent(0) * matrixStrings[1].getComponent(1)
                    - matrixStrings[0].getComponent(1) * matrixStrings[1].getComponent(0);
        }

        Vector[] array = new Vector[matrixStrings.length - 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Vector(matrixStrings.length - 1);
        }

        double sum = 0;
        int exception = 0;

        for (int i = 0; i < matrixStrings.length; i++) {
            int x = 0;

            for (int j = 0; j < matrixStrings.length; j++) {
                if (j != exception) {
                    int y = 0;

                    for (int k = 1; k < matrixStrings.length; k++) {
                        array[x].setVector(y, matrixStrings[j].getComponent(k));
                        y++;
                    }

                    x++;
                }
            }

            sum += Math.pow(-1, i) * matrixStrings[i].getComponent(0) * calculateDeterminant(array);
            exception++;
        }
        return sum;
    }

    public double getDeterminant() {
        return calculateDeterminant(matrixRows);
    }

    // умножение матрицы на вектор
    public Vector getVectorMultiplication(Vector vector) {
        if (matrixRows[0].getSize() != vector.getSize()) {
            throw new ArithmeticException("Число столбцов в матрице должно быть равно количеству элементов вектора.");
        }

        Vector newVector = new Vector(vector.getSize());
        for (int i = 0; i < matrixRows.length; i++) {
            double sum = 0;

            for (int j = 0; j < matrixRows[0].getSize(); j++) {
                sum += matrixRows[i].getComponent(j) * vector.getComponent(j);
            }

            newVector.setComponent(sum, i);
        }
        return newVector;
    }

    // сложение матриц
    public Matrix getSum(Matrix matrixRows) {
        if (this.matrixRows.length != matrixRows.matrixRows.length
                || this.matrixRows[0].getSize() != matrixRows.matrixRows[0].getSize()) {
            throw new ArithmeticException("Для сложения матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.matrixRows.length; i++) {
            this.matrixRows[i].getSum(matrixRows.matrixRows[i]);
        }
        return this;
    }

    // вычитание матриц
    public Matrix getDifference(Matrix matrixRows) {
        if (this.matrixRows.length != matrixRows.matrixRows.length
                || this.matrixRows[0].getSize() != matrixRows.matrixRows[0].getSize()) {
            throw new ArithmeticException("Для вычитания матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.matrixRows.length; i++) {
            this.matrixRows[i].getDifference(matrixRows.matrixRows[i]);
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
        if (matrix1.matrixRows[0].getSize() != matrix2.matrixRows.length) {
            throw new ArithmeticException("Количество столбцов первой матрицы должно быть равно количеству " +
                    "строк второй матрицы");
        }

        double[][] array = new double[matrix1.matrixRows.length][matrix1.matrixRows.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int sum = 0;

                for (int l = 0; l < array.length; l++) {
                    sum += matrix1.matrixRows[i].getComponent(l) * matrix2.matrixRows[l].getComponent(j);
                }

                array[i][j] = sum;
            }
        }
        return new Matrix(array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrixRows.length; i++) {
            sb.append(matrixRows[i].toString());

            if(i != matrixRows.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}