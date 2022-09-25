package jom.com.softserve.s2.task5;


import java.util.List;
import java.util.Objects;

abstract class Figure {
    abstract double getPerimeter();
}

class Rectangle extends Figure {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getPerimeter() {
        return (a + b) * 2;
    }
}

class Square extends Figure {
    private double a;

    public Square(double a) {
        this.a = a;
    }

    @Override
    public double getPerimeter() {
        return a * 4;
    }
}

public class MyUtils {

    public double sumPerimeter(List<Figure> figures) {
        return figures.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Figure::getPerimeter)
                .sum();
    }

}
