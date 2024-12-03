package ru.stqa.geometry.figures;

import java.util.Objects;

import static java.lang.Math.sqrt;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ( a + b < c || a + c < b || b + c < a ) {
            throw new IllegalArgumentException("Triangle side inequality negative");
        }
    }

    public static void printPerimeter(double a, double b, double c){
        var text = String.format("Периметр треугольника со сторонами %f, %f, %f = %f", a, b, c, Perimeter(a, b, c));
        System.out.println(text);
    }

    public static double Perimeter(double a, double b, double c) {
        return (a + b + c);
    }

    public static void printArea(double a, double b, double c){
        var text = String.format("Площадь треугольника со сторонами %f, %f, %f = %f", a, b, c, Area(a, b, c));
        System.out.println(text);
    }

    public static double halfPerimeter(double a, double b, double c) {
        return ((a + b + c)/2);
    }

    private static double Area(double a, double b, double c) {
        var p = Perimeter(a, b, c);
        return sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public double Perimeter() {
        return (this.a + this.b + this.c);
}

    public double Area() {
        var p = halfPerimeter(this.a, this.b, this.c);
        return sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0
                || (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.b) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.b) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.a) == 0);

    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}