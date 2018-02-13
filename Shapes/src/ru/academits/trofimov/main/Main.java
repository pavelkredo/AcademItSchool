package ru.academits.trofimov.main;

import ru.academits.trofimov.shapes.*;

import java.util.Arrays;

public class Main {
    private static double getMaxArea(Shape[] array) {
        ShapeAreaComparator comp = new ShapeAreaComparator();
        Arrays.sort(array, comp);

        return array[array.length - 1].getArea();
    }

    private static double getSecondPerimeter(Shape[] array) {
        ShapePerimeterComparator comp = new ShapePerimeterComparator();
        Arrays.sort(array, comp);

        return array[array.length - 2].getPerimeter();
    }

    public static void main(String[] args) {
        Square square1 = new Square(7);
        Square square2 = new Square(12);
        Rectangle rectangle1 = new Rectangle(8, 6);
        Rectangle rectangle2 = new Rectangle(10, 5);
        Triangle triangle1 = new Triangle(5, 3, 3, 5, -2, 2);
        Triangle triangle2 = new Triangle(5, 4, 3, 5, -2, 2);
        Circle circle1 = new Circle(6);
        Circle circle2 = new Circle(7.5);
        Shape[] array = {square1, square2, rectangle1, rectangle2, triangle1, circle1, circle2};

        System.out.println("Максимальная площадь равна: " + getMaxArea(array));
        System.out.println("Второй по величине периметр равен: " + getSecondPerimeter(array));

        System.out.println(triangle1.toString());

        if (triangle1.equals(triangle2)) {
            System.out.println("Фигуры равны.");
        } else {
            System.out.println("Фигуры не равны.");
        }
    }
}