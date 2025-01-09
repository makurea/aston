package task_2;

public interface Shape {
    double calculatePerimeter();
    double calculateArea();
    String getFillColor();
    String getBorderColor();

    // Дефолтный метод для вывода информации о фигуре
    default void printInfo() {
        System.out.println("Периметр: " + calculatePerimeter() + " м ");
        System.out.println("Площадь: " + calculateArea() + " м² ");
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}
