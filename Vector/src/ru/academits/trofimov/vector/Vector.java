package ru.academits.trofimov.vector;

public class Vector {
    private double[] vectorArray;

    public Vector(int n) {
        if(n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0.");
        }

        vectorArray = new double[n];
    }

    public Vector(Vector vector) {
        this.vectorArray = vector.vectorArray;
    }

    public Vector(double[] array) {
        for (int i = 0; i < vectorArray.length; i++) {
            if (i < array.length) {
                vectorArray[i] = array[i];
            } else {
                vectorArray[i] = 0;
            }
        }
    }

    public Vector(int n, double[] array) {
        if(n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0.");
        }

        vectorArray = new double[n];

        for (int i = 0; i < n; i++) {
            if (i < array.length) {
                vectorArray[i] = array[i];
            } else {
                vectorArray[i] = 0;
            }
        }
    }

    // получение размерности вектора
    public int getSize() {
        return vectorArray.length;
    }

    // преобразование компонент вектора в строку
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");

        for (int i = 0; i < vectorArray.length; i++) {
            sb.append(vectorArray[i]);
            if (i < vectorArray.length - 1) {
                sb.append(", ");
            }
        }

        sb.append(" }");
        return sb.toString();
    }

    // сумма векторов
    public Vector getSum(Vector vector2) {
        int maxLength = Math.max(this.getSize(), vector2.getSize());
        int minLength = Math.min(this.getSize(), vector2.getSize());

        if (maxLength != this.getSize()) {
            double[] tempArray = new double[maxLength];
            System.arraycopy(this.vectorArray, 0, tempArray, 0, minLength);

            this.vectorArray = new double[maxLength];
            for (int i = 0; i < maxLength; i++) {
                this.vectorArray[i] = tempArray[i] + vector2.vectorArray[i];
            }
            return this;
        }

        for (int i = 0; i < minLength; i++) {
            this.vectorArray[i] = this.vectorArray[i] + vector2.vectorArray[i];
        }
        return this;
    }

    // разность векторов
    public Vector getDifference(Vector vector2) {
        int maxLength = Math.max(this.getSize(), vector2.getSize());
        int minLength = Math.min(this.getSize(), vector2.getSize());

        if (maxLength != this.getSize()) {
            double[] tempArray = new double[maxLength];
            System.arraycopy(this.vectorArray, 0, tempArray, 0, minLength);

            this.vectorArray = new double[maxLength];
            for (int i = 0; i < maxLength; i++) {
                this.vectorArray[i] = tempArray[i] - vector2.vectorArray[i];
            }
            return this;
        }

        for (int i = 0; i < minLength; i++) {
            this.vectorArray[i] = this.vectorArray[i] - vector2.vectorArray[i];
        }
        return this;
    }

    // умножение вектора на скаляр
    public Vector getMultiplication(double scalar) {
        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] = vectorArray[i] * scalar;
        }
        return this;
    }

    // разворот вектора
    public Vector getRotation() {
        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] = vectorArray[i] * (-1);
        }
        return this;
    }

    // получение длины вектора
    public double getLength() {
        double length = 0;

        for (double component : vectorArray) {
            length = length + Math.pow(component, 2);
        }
        return Math.sqrt(length);
    }

    // установка компоненты вектора по индексу
    public Vector setComponent(double component, int index) {
        vectorArray[index] = component;
        return this;
    }

    // сложение двух векторов (статический метод)
    public static Vector getSum(Vector vector1, Vector vector2) {
        int maxLength = Math.max(vector1.getSize(), vector2.getSize());
        int minLength = Math.min(vector1.getSize(), vector2.getSize());
        double[] newVector = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            if (i < minLength) {
                newVector[i] = vector1.vectorArray[i] + vector2.vectorArray[i];
            } else {
                if (maxLength != vector1.getSize()) {
                    newVector[i] = vector2.vectorArray[i];
                } else {
                    newVector[i] = vector1.vectorArray[i];
                }
            }
        }
        return new Vector(maxLength, newVector);
    }

    // вычитание двух векторов (статический метод)
    public static Vector getDifference(Vector vector1, Vector vector2) {
        int maxLength = Math.max(vector1.getSize(), vector2.getSize());
        int minLength = Math.min(vector1.getSize(), vector2.getSize());
        double[] newVector = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            if (i < minLength) {
                newVector[i] = vector1.vectorArray[i] - vector2.vectorArray[i];
            } else {
                if (maxLength != vector1.getSize()) {
                    newVector[i] = vector2.vectorArray[i];
                } else {
                    newVector[i] = vector1.vectorArray[i];
                }
            }
        }
        return new Vector(maxLength, newVector);
    }

    // скалярное произведение векторов
    public static double getMultiplication(Vector vector1, Vector vector2) {
        int minLength = Math.min(vector1.getSize(), vector2.getSize());
        double scalarMultiplication = 0;

        for (int i = 0; i < minLength; i++) {
            scalarMultiplication = scalarMultiplication + vector1.vectorArray[i] * vector2.vectorArray[i];
        }
        return scalarMultiplication;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector)) {
            return false;
        }

        Vector vector = (Vector) o;
        if(this.vectorArray.length == vector.vectorArray.length) {
            for (int i = 0; i < vectorArray.length; i++) {
                if (this.vectorArray[i] != vector.vectorArray[i]) {
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

        for(double element : vectorArray) {
            result = prime * result + (int) element;
        }
        return result;
    }
}