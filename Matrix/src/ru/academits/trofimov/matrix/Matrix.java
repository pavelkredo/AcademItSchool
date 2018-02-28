package ru.academits.trofimov.matrix;

import ru.academits.trofimov.vector.*;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        if (n == 0 || m == 0) {
            throw new ArithmeticException("Матрица не может иметь размерность \"0\"");
        }

        for (int i = 0; i < n; i++) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
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

        rows = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxLength, array[i]);
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

        rows = new Vector[array.length];
        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxLength);

            for (int j = 0; j < array[i].getSize(); j++) {
                rows[i].setComponent(j, array[i].getComponent(j));
            }
        }
    }

    // получение количества строк матрицы
    public int getRowsNumber() {
        return rows.length;
    }

    // получение количества стролбцов матрицы
    public int getColumnsNumber() {
        return rows[0].getSize();
    }

    // получение вектора-строки по индексу
    public Vector getVectorString(int index) {
        if (index >= this.getRowsNumber() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах границ матрицы.");
        }
        return new Vector(rows[index]);
    }

    // задание вектора-строки по индексу
    public void setVectorString(int index, Vector vector) {
        if (this.getColumnsNumber() != vector.getSize()) {
            throw new ArithmeticException("Задаваемый вектор должен соответствовать длине строки матрицы.");
        } else if (index >= this.getRowsNumber() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах границ матрицы.");
        }
        rows[index] = new Vector(vector);
    }

    // получение вектора-столбца по индексу
    public Vector getVectorColumn(int index) {
        if (index >= this.getColumnsNumber() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах границ матрицы.");
        }

        double[] vector = new double[this.getRowsNumber()];

        for (int i = 0; i < this.getRowsNumber(); i++) {
            vector[i] = rows[i].getComponent(index);
        }
        return new Vector(vector.length, vector);
    }

    // задание вектора-столбца по индексу
    public void setVectorColumn(int index, Vector vector) {
        if (this.getRowsNumber() != vector.getSize()) {
            throw new ArithmeticException("Задаваемый вектор должен соответствовать количеству строк матрицы.");
        } else if (index >= this.getColumnsNumber() || index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть в пределах границ матрицы.");
        }

        for (int i = 0; i < this.getRowsNumber(); i++) {
            rows[i].setComponent(index, vector.getComponent(i));
        }
    }

    // транспонирование матрицы
    public void transpose() {
        Vector[] temp = new Vector[rows[0].getSize()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Vector(this.getRowsNumber());
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].getSize(); j++) {
                temp[i].setComponent(j, rows[j].getComponent(i));
            }
        }

        rows = temp;
    }

    // умножение на скаляр
    public void scalarMultiplication(double scalar) {
        for (Vector e : rows) {
            e.getMultiplication(scalar);
        }
    }

    // получение определителя матрицы
    private double calculateDeterminant(Vector[] rows) {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Число строк матрицы должно быть равно числу столбцов.");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        } else if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        Vector[] array = new Vector[rows.length - 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Vector(rows.length - 1);
        }

        double sum = 0;
        int exception = 0;

        for (int i = 0; i < rows.length; i++) {
            int x = 0;

            for (int j = 0; j < rows.length; j++) {
                if (j != exception) {
                    int y = 0;

                    for (int k = 1; k < rows.length; k++) {
                        array[x].setComponent(y, rows[j].getComponent(k));
                        y++;
                    }

                    x++;
                }
            }

            sum += Math.pow(-1, i) * rows[i].getComponent(0) * calculateDeterminant(array);
            exception++;
        }
        return sum;
    }

    public double getDeterminant() {
        return calculateDeterminant(rows);
    }

    // умножение матрицы на вектор
    public Vector getVectorMultiplication(Vector vector) {
        if (this.getColumnsNumber() != vector.getSize()) {
            throw new ArithmeticException("Число столбцов в матрице должно быть равно количеству элементов вектора.");
        }

        Vector newVector = new Vector(this.getRowsNumber());
        for (int i = 0; i < this.getRowsNumber(); i++) {
            double sum = 0;

            for (int j = 0; j < this.getColumnsNumber(); j++) {
                sum += rows[i].getComponent(j) * vector.getComponent(j);
            }

            newVector.setComponent(i, sum);
        }
        return newVector;
    }

    // сложение матриц
    public Matrix getSum(Matrix rows) {
        if (this.getRowsNumber() != rows.getRowsNumber()
                || this.getColumnsNumber() != rows.getColumnsNumber()) {
            throw new ArithmeticException("Для сложения матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.getRowsNumber(); i++) {
            this.rows[i].getSum(rows.rows[i]);
        }
        return this;
    }

    // вычитание матриц
    public Matrix getDifference(Matrix rows) {
        if (this.getRowsNumber() != rows.getRowsNumber()
                || this.getColumnsNumber() != rows.getColumnsNumber()) {
            throw new ArithmeticException("Для вычитания матрицы должны быть одинаковых размеров.");
        }

        for (int i = 0; i < this.getRowsNumber(); i++) {
            this.rows[i].getDifference(rows.rows[i]);
        }
        return this;
    }

    // сложение матриц (статический метод)
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber()
                || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new ArithmeticException("Для вычитания матрицы должны быть одинаковых размеров.");
        }

        Matrix matrix = new Matrix(matrix1);
        return matrix.getSum(matrix2);
    }

    // вычитание матриц (статический метод)
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsNumber() != matrix2.getRowsNumber()
                || matrix1.getColumnsNumber() != matrix2.getColumnsNumber()) {
            throw new ArithmeticException("Для вычитания матрицы должны быть одинаковых размеров.");
        }

        Matrix matrix = new Matrix(matrix1);
        return matrix.getDifference(matrix2);
    }

    // умножение матриц
    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsNumber() != matrix2.getRowsNumber()) {
            throw new ArithmeticException("Количество столбцов первой матрицы должно быть равно количеству " +
                    "строк второй матрицы");
        }

        double[][] array = new double[matrix1.getRowsNumber()][matrix1.getRowsNumber()];

        for (int i = 0; i < matrix1.getRowsNumber(); i++) {
            for (int j = 0; j < matrix1.getRowsNumber(); j++) {
                int sum = 0;

                for (int l = 0; l < matrix1.getColumnsNumber(); l++) {
                    sum += matrix1.rows[i].getComponent(l) * matrix2.rows[l].getComponent(j);
                }

                array[i][j] = sum;
            }
        }
        return new Matrix(array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.getRowsNumber(); i++) {
            sb.append(rows[i].toString());

            if (i != this.getRowsNumber() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}