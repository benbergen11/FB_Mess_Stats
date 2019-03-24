import java.util.ArrayList;

public class Interaction {
    private String user;
    private ArrayList<String> body = new ArrayList<String>();
    private ArrayList<Reaction> reactions = new ArrayList<Reaction>();
    private Date date;
    private int type;
    private int index;

    public Interaction(String u, ArrayList<String> b, ArrayList<String> r, String d, int t, int i) {
        this.user = u;
        for (String s : b) {
            this.body.add(s);
        }
        for (String s : r) {
            Reaction reac = new Reaction(s);
            this.reactions.add(reac);
        }
        this.date = new Date(d);
        this.type = t;
        this.index = i;
    }

    public Interaction(String u, ArrayList<String> b, String d, int t, int i) {
        this.user = u;
        for (String s : b) {
            this.body.add(s);
        }
        this.date = new Date(d);
        this.type = t;
        this.index = i;
    }

    public void printInteraction() {
        System.out.println("---------------------");
        System.out.println("Type: " + type);
        System.out.println("Index: " + index);
        System.out.println("User: " + user);
        System.out.print("\n");
        for (String s : body) { System.out.println(s); }
        System.out.print("\n");
        if (!(reactions.isEmpty())) {
            System.out.println("Reactions:");
            for (Reaction r : reactions) { System.out.println(r.toString()); }
            System.out.print("\n");
        }
        System.out.println(date.toString());
    }

    public Date getDate() { return date; }
    public int getType() { return type; }
    public ArrayList<String> getBody() { return body; }
    public ArrayList<Reaction> getReactions() { return reactions; }
    public String getUser() { return user; }
    public int getIndex() { return index; }

    public boolean isTextMessage() {
        return (this.type == 0);
    }

    public boolean isMessage() {
        return ((this.type == 0) || (this.type == 4) || (this.type == 5) || (this.type == 6) || (this.type == 7) || (this.type == 8) || (this.type == 9) || (this.type == 10) || (this.type == 32) || (this.type == 33));
    }

    public boolean isInteraction() {
        return (this.type != 31);
    }

    public boolean inClass(int c) {
        if (c == 0) { return (this.isTextMessage()); }
        if (c == 1) { return (this.isMessage()); }
        if (c == 2) { return (this.isInteraction()); }
        return false;
    }

    public boolean containsAtLeastnCharacters(int n) {
        int number = 0;
        for (String s : body) {
            for (int i=0; i<s.length(); i++) {
                if (Character.isAlphabetic(s.charAt(i))) {
                    number += 1;
                }
            }
        }
        return (number >= n);
    }
}
