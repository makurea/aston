package task2;

public class MainTask2 {
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.add("Цветков", "+375 (29) 123-87-56");
        phoneDirectory.add("Лермонтова", "+375 (29) 553-29-27");
        phoneDirectory.add("Филиппов", "+375 (33) 124-42-97");
        phoneDirectory.add("Цветков", "+375 (29) 373-43-23");

        System.out.println("Номера Цветкова: " + phoneDirectory.get("Цветков"));
        System.out.println("Номера Лермонтова: " + phoneDirectory.get("Лермонтова"));
        System.out.println("Номера Филиппова: " + phoneDirectory.get("Филиппов"));
    }
}
