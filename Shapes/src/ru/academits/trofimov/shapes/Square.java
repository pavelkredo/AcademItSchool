package ru.academits.trofimov.shapes;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getWidth() {
        return side;
    }

    public double getHeight() {
        return side;
    }

    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return 2 * (side + side);
    }

    @Override
    public String toString() {
        return "Сторона квадрата равна " + side;
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

        Square square = (Square) o;
        return this.side == square.side;
    }

    @Override
    public int hashCode() {
        return 31 + (int) side;
    }
}