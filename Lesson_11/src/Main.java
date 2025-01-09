import task_1.MainTask1;
import task_2.MainTask2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите задание для выполнения: ");
        System.out.println("1 - Задание 1: Работа с животными");
        System.out.println("2 - Задание 2: Расчет периметра и площади фигур");
        System.out.print("Введите номер задания: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                MainTask1.main(args);
                break;
            case 2:
                MainTask2.main(args);
                break;
            default:
                System.out.println("Некорректный ввод");
                break;
        }

        scanner.close();
    }
}
