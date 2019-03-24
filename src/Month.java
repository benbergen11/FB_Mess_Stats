public class Month {
    private int month;
    private int year;
    private int numDays;
    private int counter;

    public Month() {
        this.month = 1;
        this.year = 2000;
        this.numDays = 30;
        this.counter = 0;
    }

    public Month(int m, int y) {
        this.month = m;
        this.year = y;
        this.counter = 0;
        if (m == 2) {
            if (y == 2016)  {
                this.numDays = 29;
            }
            else { this.numDays = 28; }
        }
        else if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {
            this.numDays = 30;
        }
        else {
            this.numDays = 31;
        }
    }

    public Month(int m, int y, int n) {
        this.month = m;
        this.year = y;
        this.numDays = n;
        this.counter = 0;
    }

    public int getMonth() { return month; }
    public int getYear() { return year; }
    public int getCounter() { return counter; }
    public int getNumDays() { return numDays; }

    public void increment() {
        this.counter += 1;
    }

    public boolean includes(int m, int y) {
        return ((this.month == m) && (this.year == y));
    }


    public void adjustNumDays(int d) {
        this.numDays = (this.numDays - d + 1);
    }
}
