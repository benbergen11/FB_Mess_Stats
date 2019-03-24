import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Filename: ");
        String filename = s.nextLine();
        LineList lines = new LineList(filename);
        InteractionList interactions = new InteractionList(lines);

        boolean foo = true;

        while (foo) {
            System.out.print("\n" + "0 - Exit" + "\n" + "1 - Total messages by class" + "\n" + "2 - Messages by month" + "\n" + "3 - Messages by time of day" + "\n" + "4 - Messages by user" + "\n" + "5 - Most active days" + "\n" + "6 - Most messages in n minutes" + "\n" + "7 - Messages containing given text" + "\n" + "8 - Caps-lock analysis" + "\n" + "Operation: ");
            int choice = s.nextInt();

            if (choice == 0) {
                foo = false;
                System.out.println("\n" + "Program exited");
                System.exit(0);
            }

            else if (choice == 1) {
                System.out.print("\n");
                interactions.printNumClass();
            }

            else if (choice == 2) {
                System.out.print("\n" + "Class to search for: ");
                int choice2 = s.nextInt();
                System.out.print("\n");
                interactions.printNumClassMonth(choice2);
            }

            else if (choice == 3) {
                System.out.print("\n" + "Class to search for: ");
                int choice2 = s.nextInt();
                System.out.print("\n");
                interactions.printNumClassTime(choice2);
            }

            else if (choice == 4) {
                System.out.print("\n" + "Class to search for: ");
                int choice2 = s.nextInt();
                System.out.print("\n");
                interactions.printNumClassUser(choice2);
            }

            else if (choice == 5) {
                System.out.print("\n" + "Class to search for: ");
                int choice2 = s.nextInt();
                System.out.print("\n");
                interactions.printMostActiveDays(choice2);
            }

            else if (choice == 6) {
                System.out.print("\n" + "Class to search for: ");
                int choice2 = s.nextInt();
                System.out.print("How many minutes: ");
                int choice3 = s.nextInt();
                System.out.print("\n");
                interactions.printMostActiveHour(choice2, choice3);
            }

            else if (choice == 7) {
                Scanner s2 = new Scanner(System.in);
                System.out.print("Text to search for: ");
                String choice2 = s2.nextLine();
                System.out.print("Case Sensitive? (y/n): ");
                String choice3 = s2.nextLine();
                System.out.print("Consider text messages with no alphabetic characters in percentages? (y/n): ");
                String choice4 = s2.nextLine();
                if (choice3.equals("y")) {
                    if (choice4.equals("y")) {
                        interactions.printTextMessagesContaining(choice2, true, true);
                    }
                    else if (choice4.equals("n")) {
                        interactions.printTextMessagesContaining(choice2, true, false);
                    }
                }
                else if (choice3.equals("n")) {
                    if (choice4.equals("y")) {
                        interactions.printTextMessagesContaining(choice2, false, true);
                    }
                    else if (choice4.equals("n")) {
                        interactions.printTextMessagesContaining(choice2, false, false);
                    }
                }
            }

            else if (choice == 8) {
                System.out.print("\n");
                interactions.printNumAllCaps();
            }
        }
    }
}
