public class Time {
    private int hour;
    private int counter;

    public Time(int h) {
        this.hour = h;
        this.counter = 0;
    }

    public int getHour() { return hour; }
    public int getCounter() { return counter; }

    public void increment() {
        counter += 1;
    }
}
