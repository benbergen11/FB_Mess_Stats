import java.util.ArrayList;

public class User implements Comparable<User>{
    private String name;
    private ArrayList<Interaction> interactions;
    private int counter;
    private int counter2;

    public User(String n) {
        this.name = n;
        interactions = new ArrayList<Interaction>();
        this.counter = 0;
        this.counter2 = 0;
    }

    public String getName() { return name; }
    public ArrayList<Interaction> getInteractions() { return interactions; }
    public int getCounter() { return counter; }
    public int getCounter2() { return counter2; }

    public int numInteractions() {
        return this.interactions.size();
    }

    public int compareTo(User u) {
        return (u.counter - this.counter);
    }

    public void addInteraction(Interaction i) {
        this.interactions.add(i);
    }

    public void increment() {
        this.counter += 1;
    }

    public void increment2() {
        this.counter2 += 1;
    }

    public void setCounter(int n) { this.counter = n; }
}
