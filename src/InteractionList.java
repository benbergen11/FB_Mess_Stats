import sun.java2d.pipe.SpanShapeRenderer;

import java.lang.reflect.Array;
import java.util.*;

public class InteractionList {
    private ArrayList<Interaction> list;

    public InteractionList(LineList ll) {
        ArrayList<Line> l = ll.getList();
        ArrayList<Interaction> I = new ArrayList<Interaction>();

        String user = "";
        ArrayList<String> body = new ArrayList<String>();
        ArrayList<String> reactions = new ArrayList<String>();
        String date = "";
        int type = 0;
        int index = 0;

        for (int i=0; i < l.size(); i++) {
            int typeLocal = l.get(i).getType();
            String text = l.get(i).getStrung().getText();

            if (typeLocal == 1) {
                user = text;
            }

            else if (typeLocal == 3) {
                reactions.add(text);
            }

            else if (typeLocal == 4) {
                body.add(text);
            }

            else if (typeLocal == 0) {
                body.add(text);
            }

            else if (typeLocal > 4) {
                body.add(text);
                type = typeLocal;
            }

            else if (typeLocal == 2) {
                date = text;
                if (reactions.isEmpty()) {
                    Interaction Inew = new Interaction(user, body, date, type, index);
                    I.add(Inew);
                }
                else {
                    Interaction Inew = new Interaction(user, body, reactions, date, type, index);
                    I.add(Inew);
                }
                user = "";
                reactions.clear();
                body.clear();
                date = "";
                type = 0;
                index += 1;
            }
        }
        this.list = I;
    }

    // Test functions /////////////////////////////////////////////////////////////////////////////////////////////////

    public void printAll() {
        for (Interaction i : list) {
            i.printInteraction();
        }
    }

    private void printClass(int c) {
        for (Interaction i : list) {
            if (i.inClass(c)) {
                i.printInteraction();
            }
        }
    }

    private void printType(int t) {
        for (Interaction i : list) {
            if (i.getType() == t) {
                i.printInteraction();
            }
        }
    }

    private void printYear(int y) {
        for (Interaction i : list) {
            if (i.getDate().getYear() == y) {
                i.printInteraction();
            }
        }
    }

    public int check() {
        for (int i=1; i<list.size(); i++) {
            Date current = list.get(i).getDate();
            Date previous = list.get(i - 1).getDate();
            if (previous.minuteDiff(current) < 0) {
                return 1;
            }
        }
        return 0;
    }

    public void printInteraction (int i) {
        if (i<list.size()) {
            list.get(i).printInteraction();
        }
    }

    public void printCapsLock() {
        boolean capsLock;
        for (Interaction i : list) {
            if (i.containsAtLeastnCharacters(2)) {
                capsLock = true;
                ArrayList<String> body = i.getBody();
                for (String s2 : body) {
                    if (!(s2.toUpperCase().equals(s2))) {
                        capsLock = false;
                    }
                }
                if (capsLock) {
                    i.printInteraction();
                }
            }
        }
    }

    public Interaction getInteraction(int i) {
        if (i < list.size()) {
            return list.get(i);
        }
        return null;
    }

    // private logic functions ////////////////////////////////////////////////////////////////////////////////////////

    private int numClass(int c) {
        int counter = 0;
        for (Interaction i : list) {
            if (i.inClass(c)) {
                counter += 1;
            }
        }
        return counter;
    }

    private boolean isOldestInteraction(Interaction i) {
        return (i.equals(list.get(list.size() - 1)));
    }

    private ArrayList<Month> numClassMonth(int c) {
        ArrayList<Month> result = new ArrayList<Month>();
        boolean latestInteraction = true;
        Month currentMonth = new Month();

        for (Interaction i : list) {

            if (latestInteraction) {
                Date latestDate = i.getDate();
                int latestDay = latestDate.getDay();
                latestInteraction = false;

                Month m = new Month(latestDate.getMonth(), latestDate.getYear(), latestDay);

                if (i.inClass(c)) {
                    m.increment();
                }

                result.add(m);
                currentMonth = m;
            }

            else {
                if (currentMonth.includes(i.getDate().getMonth(), i.getDate().getYear())) {
                    if (i.inClass(c)) {
                        currentMonth.increment();
                    }
                }

                else {
                    Month m = new Month(i.getDate().getMonth(), i.getDate().getYear());
                    result.add(m);
                    currentMonth = m;
                    if (i.inClass(c)) {
                        currentMonth.increment();
                    }
                }
            }

            if (isOldestInteraction(i)) {
                int oldestDay = i.getDate().getDay();
                currentMonth.adjustNumDays(oldestDay);
            }
        }
        return result;
    }

    private ArrayList<Time> numClassTime(int c) {
        ArrayList<Time> result = new ArrayList<Time>();
        for (int i=0; i<24; i++) {
            Time t = new Time(i);
            result.add(t);
        }
        for (Interaction i : list) {
            if (i.inClass(c)) {
                int hour = i.getDate().getHour();
                int minutes = i.getDate().getMinute();

                if (minutes <= 30) {
                    result.get(hour).increment();
                }
                else {
                    if (hour == 23) {
                        result.get(0).increment();
                    }
                    else {
                        result.get(hour + 1).increment();
                    }
                }
            }
        }
        return result;
    }

    private ArrayList<Day> mostActiveDays(int c) {
        ArrayList<Day> result = new ArrayList<Day>();
        boolean newDay = true;

        for (Interaction i : list) {
            if (i.inClass(c)) {
                if (!result.isEmpty()) {
                    int latestDay = result.get(result.size() - 1).getDay();
                    int latestMonth = result.get(result.size() - 1).getMonth();
                    int latestYear = result.get(result.size() - 1).getYear();
                    newDay = ((i.getDate().getDay() != latestDay) || (i.getDate().getMonth() != latestMonth) || (i.getDate().getYear() != latestYear));
                }
                if (newDay) {
                    Day d = new Day(i.getDate().getYear(), i.getDate().getMonth(), i.getDate().getDay());
                    d.increment();
                    result.add(d);
                }
                else {
                    result.get(result.size() - 1).increment();
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    private SixtyMin mostActiveHour(int c, int min) {
        ArrayDeque<Interaction> tracker = new ArrayDeque<Interaction>();
        SixtyMin most = new SixtyMin(null, 0);
        int currentMin;

        for (Interaction i : list) {
            if (i.inClass(c)) {
                Date currentDate = i.getDate();
                tracker.addLast(i);
                while (tracker.peekFirst().getDate().minuteDiff(currentDate) >= min) {
                    tracker.removeFirst();
                }
                int currentAmount = tracker.size();
                if (currentAmount >= most.getCount()) {
                    SixtyMin newMost = new SixtyMin(currentDate, currentAmount);
                    most = newMost;
                }
            }
        }
        return most;
    }

    private ArrayList<User> generateUserList(int c) {
        ArrayList<User> result = new ArrayList<User>();
        boolean foundUser = false;

        for (Interaction i : list) {
            if (i.inClass(c)) {
                for (User u : result) {
                    if (!foundUser) {
                        if (i.getUser().equals(u.getName())) {
                            u.addInteraction(i);
                            foundUser = true;
                        }
                    }
                }
                if (!foundUser) {
                    User u = new User(i.getUser());
                    u.addInteraction(i);
                    result.add(u);
                }
                foundUser = false;
            }
        }
        return result;
    }

    private ArrayList<User> numClassUser(int c) {
        ArrayList<User> result = generateUserList(c);
        for (User u : result) {
            u.setCounter(u.numInteractions());
        }
        Collections.sort(result);
        return result;
    }

    private ArrayList<User> textMessagesContaining(String s) { //old
        ArrayList<User> result = generateUserList(0);
        for (User u : result) {
            ArrayList<Interaction> interactions = u.getInteractions();
            for (Interaction i : interactions) {
                boolean found = false;
                ArrayList<String> body = i.getBody();
                for (String s2 : body) {
                    if (s2.contains(s)) {
                        found = true;
                    }
                }
                if (found) { u.increment(); }
            }
        }
        Collections.sort(result);
        return result;
    }

    private ArrayList<User> textMessagesContaining(String s, boolean capSens, boolean considerEmoji) { // Allows for alternate capitalization.
        ArrayList<User> result = generateUserList(0);
        if (!capSens) {
            s = s.toLowerCase();
        }
        for (User u : result) {
            ArrayList<Interaction> interactions = u.getInteractions();
            for (Interaction i : interactions) {
                if (considerEmoji) {
                    u.increment2();
                }
                else if (i.containsAtLeastnCharacters(1)) {
                    u.increment2();
                }

                boolean found = false;
                ArrayList<String> body = i.getBody();
                for (String s2 : body) {
                    String s3;
                    if (!capSens) {
                        s3 = s2.toLowerCase();
                    }
                    else {
                        s3 = s2;
                    }
                    if (s3.contains(s)) {
                        found = true;
                    }
                }
                if (found) { u.increment(); }
            }
        }
        Collections.sort(result);
        return result;
    }

    public ArrayList<User> numAllCaps() {
        ArrayList<User> result = generateUserList(0);
        boolean capsLock;
        for (User u : result) {
            ArrayList<Interaction> interactions = u.getInteractions();
            for (Interaction i : interactions) {
                if (i.containsAtLeastnCharacters(2)) {
                    u.increment2();
                    capsLock = true;
                    ArrayList<String> body = i.getBody();
                    for (String s2 : body) {
                        if (!(s2.toUpperCase().equals(s2))) {
                            capsLock = false;
                        }
                    }
                    if (capsLock) { u.increment(); }
                }
            }
        }
        Collections.sort(result);
        return result;
    }

    // public print functions /////////////////////////////////////////////////////////////////////////////////////////

    public void printNumClass() {
        System.out.println("Text Messages: " + numClass(0));
        System.out.println("Messages: " + numClass(1));
        System.out.println("Interactions: " + numClass(2));
    }

    public void printNumClassTime(int c) {
        ArrayList<Time> result = numClassTime(c);
        for (Time t : result) {
            int hour = t.getHour();
            int counter = t.getCounter();
            System.out.println(hour + ":00 - " + counter);
            //
        }
    }

    public void printNumClassMonth(int c) {
        ArrayList<Month> result = numClassMonth(c);
        for (Month m : result) {
            float counter = m.getCounter();
            float numDays = m.getNumDays();
            float perDay = (counter/numDays);
            System.out.println(m.getYear() + " - " + m.getMonth() + ": " + m.getCounter() + " (" + String.format("%,1.1f", perDay) + "/day)");
        }
    }

    public void printNumClassUser(int c) {
        ArrayList<User> result = numClassUser(c);
        float total = numClass(c);
        int counter = 1;
        for (User u : result) {
            float subtotal = u.numInteractions();
            float percent = (subtotal/total)*100;
            System.out.println(counter + ". " + u.getName() + " - " + u.numInteractions() + " (" + String.format("%,1.2f", percent) + "%)");
            counter += 1;
        }
    }

    public void printMostActiveDays(int c) {
        ArrayList<Day> result = mostActiveDays(c);
        Day first = result.get(0);
        Day second = result.get(1);
        Day third = result.get(2);

        System.out.println("1. " + first.getYear() + "/" + first.getMonth() + "/" + first.getDay() + " - " + first.getCounter());
        System.out.println("2. " + second.getYear() + "/" + second.getMonth() + "/" + second.getDay() + " - " + second.getCounter());
        System.out.println("3. " + third.getYear() + "/" + third.getMonth() + "/" + third.getDay() + " - " + third.getCounter());
    }

    public void printMostActiveHour(int c, int min) {
        SixtyMin most = mostActiveHour(c, min);
        Date d = most.getDate();
        int amount = most.getCount();
        String flip = "";
        if (c == 0) {
            flip = "text messages";
        }
        else if (c == 1) {
            flip = "messages";
        }
        else if (c == 2) {
            flip = "interactions";
        }
        System.out.print("Most " + flip + " in " + min + " mintues is " + amount + " beginning at " + d.toString() + "\n");
    }

    public void printTextMessagesContaining(String s) { // old
        ArrayList<User> result = textMessagesContaining(s);
        int total = 0;
        for (User u : result) {
            total += u.getCounter();
        }
        System.out.println("Total text messages containing '" + s + "' - " + total + "\n");
        System.out.println("By user:");
        for (User u : result) {
            if (u.getCounter() != 0) {
                System.out.println(u.getName() + " - " + u.getCounter());
            }
        }
    }

    public void printTextMessagesContaining(String s, boolean capSens, boolean considerEmoji) {
        ArrayList<User> result = textMessagesContaining(s, capSens, considerEmoji);
        int total = 0;
        for (User u : result) {
            total += u.getCounter();
        }
        System.out.print("\n" + "Total text messages containing '" + s + "' - " + total);
        if (!capSens) {
            System.out.print("\n" + "(not case-sensitive)");
        }

        if (total != 0) {
            int number = 1;
            System.out.println("\n" + "\n" + "By user:");
            if (!considerEmoji) {
                System.out.println("Note - percentages are the percent of their text messages containing an alphabetic characters which contain " + s);
            }
            else {
                System.out.println("Note - percentages are the percent of their text messages which contain " + s);
            }

            for (User u : result) {
                if (u.getCounter() != 0) {
                    float userTotal = u.getCounter2();
                    float numCapsLock = u.getCounter();
                    float percent = (numCapsLock/userTotal)*100;
                    System.out.println(number + ". " + u.getName() + " - " + u.getCounter() + " (" + String.format("%,1.2f", percent) + "%)");
                    number += 1;
                }
            }
        }
        else { System.out.println(); }
    }

    public void printNumAllCaps() {
        ArrayList<User> result = numAllCaps();
        int total = 0;
        for (User u : result) {
            total += u.getCounter();
        }

        System.out.println("Total caps-lock text messages - " + total + "\n");

        if (total != 0) {
            System.out.println("By user:");
            System.out.println("Note - percentages are the percent of their text messages containing 2 or more alphabetic characters which are caps-lock");
            int number = 1;
            for (User u : result) {
                if (u.getCounter() != 0) {
                    float userTotal = u.getCounter2();
                    float numCapsLock = u.getCounter();
                    float percent = (numCapsLock/userTotal)*100;
                    System.out.println(number + ". " + u.getName() + " - " + u.getCounter() + " (" + String.format("%,1.2f", percent) + "%)");
                    number += 1;
                }
            }
        }
    }
}


