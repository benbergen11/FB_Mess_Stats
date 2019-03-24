public class Date {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public Date(String date) {
        int l = date.length();

        this.year = Integer.parseInt((((date.substring(7, 12)).replace(',', ' '))).trim());
        this.month = monthConv(date.substring(0,3));
        this.day = Integer.parseInt((((date.substring(4,6)).replace(',', ' '))).trim());
        this.hour = hourConv(Integer.parseInt((date.substring(l-8, l-6)).trim()), date.substring(l-2));
        this.minute = Integer.parseInt(date.substring(l-5, l-3));
    }

    /////////////////////////////////////////////////////

    private int monthConv(String m) {
        if (m.equals("Jan")) { return 1; }
        if (m.equals("Feb")) { return 2; }
        if (m.equals("Mar")) { return 3; }
        if (m.equals("Apr")) { return 4; }
        if (m.equals("May")) { return 5; }
        if (m.equals("Jun")) { return 6; }
        if (m.equals("Jul")) { return 7; }
        if (m.equals("Aug")) { return 8; }
        if (m.equals("Sep")) { return 9; }
        if (m.equals("Oct")) { return 10; }
        if (m.equals("Nov")) { return 11; }
        if (m.equals("Dec")) { return 12; }
        return 13;
    }

    private String monthConv(int m) {
        if (m == 1) { return "Jan"; }
        if (m == 2) { return "Feb"; }
        if (m == 3) { return "Mar"; }
        if (m == 4) { return "Apr"; }
        if (m == 5) { return "May"; }
        if (m == 6) { return "Jun"; }
        if (m == 7) { return "Jul"; }
        if (m == 8) { return "Aug"; }
        if (m == 9) { return "Sep"; }
        if (m == 10) { return "Oct"; }
        if (m == 11) { return "Nov"; }
        if (m == 12) { return "Dec"; }
        return "Err";
    }

    private int hourConv(int hour, String ampm) {
        int numHour = hour;
        if (ampm.contains("PM") || hour != 12) {
            numHour += 12;
        }
        if (ampm.contains("AM") || hour == 12) {
            numHour -= 12;
        }
        return numHour;
    }

    public int totalMin() {
        int total = 0;

        total += ((this.year - 2015) * 525600);

        if (this.month > 1) { total += (31 * 1440); }
        if (this.month > 2) { total += (28 * 1440); }
        if (this.month > 3) { total += (31 * 1440); }
        if (this.month > 4) { total += (30 * 1440); }
        if (this.month > 5) { total += (31 * 1440); }
        if (this.month > 6) { total += (30 * 1440); }
        if (this.month > 7) { total += (31 * 1440); }
        if (this.month > 8) { total += (31 * 1440); }
        if (this.month > 9) { total += (30 * 1440); }
        if (this.month > 10) { total += (31 * 1440); }
        if (this.month > 11) { total += (30 * 1440); }

        total += ((this.day - 1) * 1440);
        total += (this.hour * 60);
        total += (this.minute);

        if ((this.year > 2016) || ((this.year == 2016) && (this.month > 2))) { total += 1440; } // leap year

        return total;
    }

    private String addZero(int m) {
        if (m < 10) {
            return ("0" + m);
        }
        return ("" + m);
    }

//////////////////////////////////////////////////////////

    public int minuteDiff(Date d) {
        return (this.totalMin() - d.totalMin());
    }

    public String toString() {
        String s = (monthConv(this.month) + " " + this.day + ", " + this.year + ", " + this.hour + ":" + addZero(this.minute));
        return s;
    }

    public boolean laterThan(Date d) {
        return (this.minuteDiff(d) > 0);
    }

    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public int getHour() { return hour; }
    public int getMinute() { return minute; }

    public int getWeekday() {
        int totalMin = this.totalMin();
        int totalDays = (totalMin/1440);
        int remainder = (totalDays % 7);

        if (remainder == 0) { return 4; }
        if (remainder == 1) { return 5; }
        if (remainder == 2) { return 6; }
        if (remainder == 3) { return 7; }
        if (remainder == 4) { return 1; }
        if (remainder == 5) { return 2; }
        if (remainder == 6) { return 3; }
        return 8;
    }
}
