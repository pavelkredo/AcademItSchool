package ru.academits.trofimov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0.");
        }

        vector = new double[n];
    }

    public Vector(Vector vector) {
        this(vector.vector);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0.");
        }

        vector = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0.");
        }

        vector = Arrays.copyOf(array, n);
    }

    public double[] getVector () {
        return vector;
    }

    // получение размерности вектора
    public int getSize() {
        return vector.length;
    }

    // преобразование компонент вектора в строку
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");

        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            if (i < vector.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(" }");
        return sb.toString();
    }

    // сумма векторов
    public Vector getSum(Vector vector2) {
        int maxLength = Math.max(this.getSize(), vector2.getSize());

        if (maxLength != this.getSize()) {
            this.vector = Arrays.copyOf(this.vector, maxLength);
        }

        for (int i = 0; i < vector2.getSize(); i++) {
            this.vector[i] += vector2.vector[i];
        }
        return this;
    }

    // разность векторов
    public Vector getDifference(Vector vector2) {
        int maxLength = Math.max(this.getSize(), vector2.getSize());

        if (maxLength != this.getSize()) {
            this.vector = Arrays.copyOf(this.vector, maxLength);
        }

        for (int i = 0; i < vector2.getSize(); i++) {
            this.vector[i] -= vector2.vector[i];
        }
        return this;
    }

    // умножение вектора на скаляр
    public Vector getMultiplication(double scalar) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] *= scalar;
        }
        return this;
    }

    // разворот вектора
    public Vector getRotation() {
        return getMultiplication(-1);
    }

    // получение длины вектора
    public double getLength() {
        double length = 0;

        for (double component : vector) {
            length += Math.pow(component, 2);
        }
        return Math.sqrt(length);
    }

    // установка компоненты вектора по индексу
    public Vector setComponent(double component, int index) {
        vector[index] = component;
        return this;
    }

    // сложение двух векторов (статический метод)
    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector temp = new Vector(vector1);
        return temp.getSum(vector2);
    }

    // вычитание двух векторов (статический метод)
    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector temp = new Vector(vector1);
        return temp.getDifference(vector2);
    }

    // скалярное произведение векторов
    public static double getMultiplication(Vector vector1, Vector vector2) {
        int minLength = Math.min(vector1.getSize(), vector2.getSize());
        double scalarMultiplication = 0;

        for (int i = 0; i < minLength; i++) {
            scalarMultiplication += vector1.vector[i] * vector2.vector[i];
        }
        return scalarMultiplication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;
        if (this.vector.length == vector.vector.length) {
            for (int i = 0; i < this.vector.length; i++) {
                if (this.vector[i] != vector.vector[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        for (double element : vector) {
            result = prime * result + (int) element;
        }
        return result;
    }
}