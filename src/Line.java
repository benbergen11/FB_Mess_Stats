public class Line {

    private int position;
    private Strung text;
    private int type;

    public Line(int p, String t) {
        this.position = p;
        this.text = new Strung(t);
        this.type = 0;
    }

    public Line() {
    }

    public int getPosition() {
        return position;
    }
    public Strung getStrung() {
        return text;
    }
    public int getType() {
        return type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void stripText() {
        this.text.stripText();
    }
}
