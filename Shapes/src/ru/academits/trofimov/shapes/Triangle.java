package ru.academits.trofimov.shapes;

public class Triangle implements Shape {
    private double x1, y1, x2, y2, x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    public double getArea() {
        return 0.5 * getWidth() * getHeight();
    }

    public double[] getSides() {
        return new double[]{Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2)),
                Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2)), Math.sqrt(Math.pow(x3, 2) + Math.pow(y3, 2))};
    }

    public double getPerimeter() {
        double perimeter = 0;

        for (double e : getSides()) {
            perimeter += e;
        }

        return perimeter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Стороны треугольника равны: ");

        for (double e : getSides()) {
            sb.append(e);
            sb.append(" ");
        }

        return sb.toString();
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

        Triangle triangle = (Triangle) o;
        return this.x1 == triangle.x1 && this.y1 == triangle.y1 && this.x2 == triangle.x2 && this.y2 == triangle.y2
                && this.x3 == triangle.x3 && this.y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (int) x1;
        result = prime * result + (int) y1;
        result = prime * result + (int) x2;
        result = prime * result + (int) y2;
        result = prime * result + (int) x3;
        result = prime * result + (int) y3;

        return result;
    }
}