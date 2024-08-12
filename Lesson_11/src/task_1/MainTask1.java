package task_1;

public class MainTask1 {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();

        Dog dog1 = new Dog();
        Dog dog2 = new Dog();

        // Проверка бега и плавания
        cat1.run(150);
        cat2.run(250);
        cat3.swim(5);

        dog1.run(300);
        dog2.run(600);
        dog1.swim(5);
        dog2.swim(15);

        // Проверка сытости
        FoodCap foodCap = new FoodCap(15);

        Cat[] cats = {cat1, cat2, cat3};

        for (Cat cat : cats) {
            cat.eat(foodCap, 5);
            System.out.println("Сытость кота: " + cat.isFull());
        }

        System.out.println("Количество котов: " + Cat.catCount);
        System.out.println("Количество собак: " + Dog.dogCount);
        System.out.println("Общее количество животных: " + Animal.animalCount);
    }
}
