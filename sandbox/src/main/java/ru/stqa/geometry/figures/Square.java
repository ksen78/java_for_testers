package ru.stqa.geometry.figures;

public class Square {

    public static void printSquareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, area(side));
        System.out.printf(text);
    }

    public static double area(double side) {
        return side * side;
    }

    public static double perimetr(double a) {
        return 4 * a;
    }
}
