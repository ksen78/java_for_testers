package ru.stqa.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {

    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
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
}