public class Park {
    private String parkName;
    private Attraction[] attractions;

    public Park(String parkName, int numberOfAttractions) {
        this.parkName = parkName;
        this.attractions = new Attraction[numberOfAttractions];
    }

    public void addAttraction(int index, String name, String workingHours, double price) {
        if (index >= 0 && index < attractions.length) {
            attractions[index] = new Attraction(name, workingHours, price);
        }
    }

    public void printAttractions() {
        System.out.println("Park: " + parkName);
        for (Attraction attraction : attractions) {
            if (attraction != null) {
                attraction.printInfo();
                System.out.println("======================");
            }
        }
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Часы работы: " + workingHours);
            System.out.println("Стоимость: " + String.format("%.2f руб", price));
        }
    }

    public static void main(String[] args) {
        Park park = new Park("Chelyuskintsev Park", 3);
        park.addAttraction(0, "Calypso", "11:00 - 21:00", 100.50);
        park.addAttraction(1, "Autodrome", "11:00 - 21:00", 75.0);
        park.addAttraction(2, "Sliding Mountain", "12:00 - 23:00", 85.90);


        park.printAttractions();
    }
}
