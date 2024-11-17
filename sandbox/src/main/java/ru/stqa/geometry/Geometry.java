package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.));
        Square.printSquareArea(new Square( 5.));
        Square.printSquareArea(new Square( 3.));

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);

        Triangle.printPerimeter(3.0, 3.0, 3.0);
        Triangle.printPerimeter(5.0, 5.0, 5.0);

        Triangle.printArea(3, 3, 3);
    }
}
