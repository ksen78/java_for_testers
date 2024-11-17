package ru.stqa.geometry.figures;

public class Square {

    public static void printSquareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, squareArea(side));
        System.out.printf(text);
    }

    private static double squareArea(double side) {
        return side * side;
    }
}
