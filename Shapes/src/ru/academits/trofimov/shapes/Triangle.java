package ru.academits.trofimov.shapes;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private double getLineLength(double crd1, double crd2, double crd3) {
        return Math.max(Math.max(crd1, crd2), crd3) - Math.min(Math.min(crd1, crd2), crd3);
    }

    public double getWidth() {
        return getLineLength(x1, x2, x3);
    }

    public double getHeight() {
        return getLineLength(y1, y2, y3);
    }

    public double getArea() {
        return 0.5 * getWidth() * getHeight();
    }

    private double[] getSides() {
        return new double[]{Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)),
                Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2)),
                Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2))};
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
        return Double.hashCode(x1) + Double.hashCode(y1) + Double.hashCode(x2) + Double.hashCode(y2)
                + Double.hashCode(x3) + Double.hashCode(y3);
    }
}