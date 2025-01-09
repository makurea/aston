package task_1;

public class Dog extends Animal {
    public static int dogCount = 0;

    public Dog() {
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println("Собака пробежала " + distance + " м ");
        } else {
            System.out.println("Собака не может пробежать " + distance + " м ");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println("Собака проплыла " + distance + " м ");
        } else {
            System.out.println("Собака не может проплыть " + distance + " м ");
        }
    }
}
