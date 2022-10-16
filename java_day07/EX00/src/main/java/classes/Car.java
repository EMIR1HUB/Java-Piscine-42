package classes;

public class Car {
    private final String mark;
    private final int year;
    private final int price;

    public Car(){
        this.mark = "name none";
        this.year = 0;
        this.price = 0;
    }

    public Car(String mark, int year, int price) {
        this.mark = mark;
        this.year = year;
        this.price = price;
    }

    public int yearHasPassed(){
        return 2022 - year;
    }

    @Override
    public String toString() {
        return "Car[" +
                "mark='" + mark + '\'' +
                ", year=" + year +
                ", price=" + price +
                ']';
    }
}
