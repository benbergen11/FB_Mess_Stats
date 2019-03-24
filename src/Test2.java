public class Test2 {
    public static void main(String[] args) {
        LineList testList = new LineList("Crew(Feb 11).txt");
        for (Line i : testList.getList()) {
            System.out.println(i.getStrung().getText() + " (type " + i.getType() + ")");
        }
    }
}
