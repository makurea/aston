package task_1;

public abstract class Animal {
    protected static int animalCount = 0;

    public Animal() {
        animalCount++;
    }

    // Абстрактные методы должны быть public, чтобы наследники могли их переопределить
    public abstract void run(int distance);
    public abstract void swim(int distance);
}
