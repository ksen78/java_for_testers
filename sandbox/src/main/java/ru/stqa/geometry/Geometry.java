package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
        var squares = List.of(new Square(7.), new Square( 5.), new Square( 3.));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }
        Consumer<Square> print = square -> {
            Square.printSquareArea(square);
        };
        squares.forEach(print);

//        Rectangle.printRectangleArea(3.0, 5.0);
//        Rectangle.printRectangleArea(7.0, 9.0);
//
//        Triangle.printPerimeter(3.0, 3.0, 3.0);
//        Triangle.printPerimeter(5.0, 5.0, 5.0);
//
//        Triangle.printArea(3, 3, 3);
    }
}
