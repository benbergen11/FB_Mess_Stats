public class Day implements Comparable<Day>{
    private int year;
    private int month;
    private int day;
    private int counter;

    public Day(int y, int m, int d) {
        this.year = y;
        this.month = m;
        this.day = d;
        this.counter = 0;
    }

    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public int getCounter() {return counter; }

    public void increment() {
        this.counter += 1;
    }

    public int compareTo(Day d) {
        return (d.getCounter() - this.counter);
    }

}
