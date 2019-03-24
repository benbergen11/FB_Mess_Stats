public class Test3 {
    public static void main(String[] args) {
        LineList testLineList = new LineList("Crew(Feb 17).txt");
        InteractionList I = new InteractionList(testLineList);
        I.printNumClass();
        System.out.println("----------");

        //I.printNumClassMonth(0);
        //I.printNumClassTime(2);
        //I.printNumClassUser(1);
        //I.printMostActiveDays(2);
        //I.printMostActiveHour(2, 60);
        //I.printTextMessagesContaining("\uD83D\uDD25");
        //I.printNumAllCaps();
        I.printCapsLock();

        //String s = "\uD83D\uDE03hello!!";
        //String s2 = "\uD83D\uDE03HELLo!!";
        //String s3 = "\uD83D\uDE03HELLO!!";

        //System.out.println(s.toUpperCase().equals(s));
        //System.out.println(s2.toUpperCase().equals(s2));
        //System.out.println(s3.toUpperCase().equals(s3));
    }
}
