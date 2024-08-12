package task_2;

public class MainTask2 {
    public static void main(String[] args) {
        Shape circle = new Circle(5.0, "красный", "черный");
        Shape rectangle = new Rectangle(4.0, 6.0, "синий", "зеленый");
        Shape triangle = new Triangle(3.0, 4.0, 5.0, "желтый", "фиолетовый");

        System.out.println("Характеристики круга:");
        circle.printInfo();
        System.out.println();

        System.out.println("Характеристики прямоугольника:");
        rectangle.printInfo();
        System.out.println();

        System.out.println("Характеристики треугольника:");
        triangle.printInfo();
    }
}
