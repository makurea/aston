package task_1;

public class Cat extends Animal {
    public static int catCount = 0;
    private boolean full;

    public Cat() {
        catCount++;
        this.full = false;
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println("Кот пробежал " + distance + " м ");
        } else {
            System.out.println("Кот не может пробежать " + distance + " м ");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Кот не умеет плавать.");
    }

    public void eat(FoodCap foodCap, int amount) {
        if (foodCap.getFood() >= amount) {
            foodCap.decreaseFood(amount);
            this.full = true;
            System.out.println("Кот поел " + amount + " кол-во еды.");
        } else {
            System.out.println("Коту не хватило еды.");
        }
    }

    public boolean isFull() {
        return full;
    }
}
