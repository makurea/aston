public class Main {
    public static void main(String[] args) {
        // Вызов методов для выполнения заданий
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
    }

    // 1. Метод для вывода трех слов в столбец
    public static void task1() {
        System.out.println("Задание 1: Вывод трех слов в столбец");
        printThreeWords();
        System.out.println("\n======================");
    }

    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    // 2. Метод для проверки суммы двух чисел и вывода сообщения
    public static void task2() {
        System.out.println("Задание 2: Проверка суммы двух чисел");
        checkSumSign();
        System.out.println("\n======================");
    }

    public static void checkSumSign() {
        int a = 5;
        int b = -10;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    // 3. Метод для вывода цвета в зависимости от значения переменной
    public static void task3() {
        System.out.println("Задание 3: Определение цвета");
        printColor();
        System.out.println("\n======================");
    }

    public static void printColor() {
        int value = 150;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // 4. Метод для сравнения двух чисел и вывода сообщения
    public static void task4() {
        System.out.println("Задание 4: Сравнение двух чисел");
        compareNumbers();
        System.out.println("\n======================");
    }

    public static void compareNumbers() {
        int a = 5;
        int b = 3;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // 5. Метод для проверки, лежит ли сумма двух чисел в диапазоне от 10 до 20
    public static void task5() {
        System.out.println("Задание 5: Проверка суммы в диапазоне от 10 до 20");
        boolean sumInRange = isSumInRange(5, 7);
        System.out.println("Сумма в диапазоне: " + sumInRange);
        System.out.println("\n======================");
    }

    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // 6. Метод для проверки положительное или отрицательное число передано
    public static void task6() {
        System.out.println("Задание 6: Проверка знака числа");
        printSign(10);
        System.out.println("\n======================");
    }

    public static void printSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // 7. Метод для проверки, является ли число отрицательным
    public static void task7() {
        System.out.println("Задание 7: Проверка на отрицательное число");
        boolean isNegativeNumber = isNegative(-5);
        System.out.println("Число отрицательное: " + isNegativeNumber);
        System.out.println("\n======================");
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    // 8. Метод для печати строки указанное количество раз
    public static void task8() {
        System.out.println("Задание 8: Печать строки указанное количество раз");
        printStringMultipleTimes("Hello", 3);
        System.out.println("\n======================");
    }

    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    // 9. Метод для определения, является ли год високосным
    public static void task9() {
        System.out.println("Задание 9: Проверка, является ли год високосным");
        boolean isLeap = isLeapYear(2024);
        System.out.println("Год високосный: " + isLeap);
        System.out.println("\n======================");
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else return year % 4 == 0;
    }

    // 10. Метод для изменения значений в бинарном массиве
    public static void task10() {
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        invertBinaryArray(binaryArray);
        System.out.println("Задание 10: Изменение бинарного массива");
        printArray(binaryArray);
        System.out.println("\n======================");
    }

    public static void invertBinaryArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] == 0) ? 1 : 0;
        }
    }

    // 11. Метод для заполнения массива значениями от 1 до 100
    public static void task11() {
        int[] filledArray = fillArray(100);
        System.out.println("Задание 11: Заполнение массива значениями от 1 до 100");
        printArray(filledArray);
        System.out.println("\n======================");
    }

    public static int[] fillArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // 12. Метод для умножения значений массива меньше 6 на 2
    public static void task12() {
        int[] modifyArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        modifyArrayValues(modifyArray);
        System.out.println("Задание 12: Умножение значений массива меньше 6 на 2");
        printArray(modifyArray);
        System.out.println("\n======================");
    }

    public static void modifyArrayValues(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    // 13. Метод для заполнения диагоналей квадратного массива единицами
    public static void task13() {
        int[][] squareArray = fillDiagonal(5);
        System.out.println("Задание 13: Заполнение диагоналей квадратного массива единицами");
        print2DArray(squareArray);
        System.out.println("\n======================");
    }

    public static int[][] fillDiagonal(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < size; i++) {
            array[i][i] = 1;
            array[i][size - i - 1] = 1;
        }
        return array;
    }

    // 14. Метод для создания массива заданной длины и значения
    public static void task14() {
        int[] customArray = createArray(10, 5);
        System.out.println("Задание 14: Создание массива заданной длины и значения");
        printArray(customArray);
        System.out.println("\n======================");
    }

    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    // Методы для печати массивов
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void print2DArray(int[][] array) {
        for (int[] row : array) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
