public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private double salary;
    private int age;

    public Employee(String fullName, String position, String email, String phone, double salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo() {
        System.out.println("ФИО: " + fullName);
        System.out.println("Должность: " + position);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + phone);
        System.out.println("Зарплата: " + String.format("%.2f руб", salary));
        System.out.println("Возраст: " + age);
    }

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Petrova Anna", "QA Engineer", "petrova@mailbox.com", "892312313", 40000, 32);
        employees[2] = new Employee("Gerasimov Kirill", "Director", "gerasimov@mailbox.com", "892312314", 65000, 35);
        employees[3] = new Employee("Semenova Sofia", "Software Developer", "semenova@mailbox.com", "892312315", 35000, 23);
        employees[4] = new Employee("Levin Maxim", "Security Engineer", "levin@mailbox.com", "892312316", 45000, 30);

        for (Employee employee : employees) {
            employee.printInfo();
            System.out.println("======================");
        }
    }
}
