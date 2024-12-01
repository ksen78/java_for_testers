package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(5.0, 5.0, 5.0);
        double result;
        result = t.Perimeter();
        Assertions.assertEquals(15, result);
    }

    @Test
    void canCalculateArea() {
        var a = new Triangle(7, 10, 4);
        double result;
        result = a.Area();
        Assertions.assertEquals(10.928746497197197, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 3.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //Ok
        }
    }

    @Test
    void cannotCreateTriangleWithSideInequality() {
        try {
            new Triangle(5, 6, 15);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //Ok
        }
    }
}