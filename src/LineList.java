import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.out;

public class LineList {
    private ArrayList<Line> list;

    public LineList(String filename) {
        int counter = 0;
        list = new ArrayList<Line>();
        boolean newMessage = true;
        boolean plan = false;
        ArrayList<String> userList = new ArrayList<String>();

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                Line l = new Line(counter, str);
                list.add(l);
                counter += 1;
            }
            br.close();
        } catch (IOException e) {
            out.println("File not found");
        }

        list.get(0).stripText();

        for (int i=0; i < list.size(); i++) {
            Line l = list.get(i);
            if (newMessage == true) {
                l.setType(1);
                if (!(userList.contains(l.getStrung().getText()))) {
                    userList.add(l.getStrung().getText());
                }
                newMessage = false;
            }

            else if ((l.getStrung().getText().contains("Plan Created:")) && (l.getStrung().getText().charAt(0) == 'P')){
                if (list.get(i-1).getStrung().getText().contains("started a plan.")) {
                    plan = true;
                }
            }

            else if (l.getStrung().isDate() == true) {
                if (plan == false) {
                    if (list.get(i-1).getType() != 1) {
                        l.setType(2);
                        newMessage = true;
                    }
                }
                else {
                    plan = false;
                }
            }

            else if (l.getStrung().isEmpty()) {
                l.setType(4);
            }
        }

        for (int i=0; i < list.size(); i++) {
            Line l = list.get(i);

            if (l.getType() == 0) {
                String s = l.getStrung().getText();
                boolean p = (s.charAt(s.length()-1) == '.');

                if (l.getStrung().isReaction(userList)) {
                    if (list.get(i-1).getType() != 1) {
                        l.setType(3);
                    }
                }

                else if (l.getStrung().isGameText()) {
                    l.setType(11);
                }

                else if (l.getStrung().isVotedPoll()) {
                    l.setType(20);
                }

                else if (l.getStrung().isCretedPoll()) {
                    l.setType(21);
                }

                else if (p) {
                    if (l.getStrung().isGIF()) {
                        if (list.get(i+1).getType() == 4) {
                            l.setType(5);
                        }
                    }
                    else if (l.getStrung().isVideo()) {
                        l.setType(6);
                    }
                    else if (l.getStrung().isMultiVideo()) {
                        l.setType(7);
                    }
                    else if (l.getStrung().isPhoto()) {
                        if (list.get(i+1).getType() == 4) {
                            l.setType(8);
                        }
                    }
                    else if (l.getStrung().isMultiPhoto()) {
                        if (list.get(i+1).getType() == 4) {
                            l.setType(9);
                        }
                    }
                    else if (l.getStrung().isSticker()) {
                        if (list.get(i+1).getType() == 4) {
                            l.setType(10);
                        }
                    }
                    else if (l.getStrung().isSetOwnNickname()) {
                        l.setType(12);
                    }
                    else if (l.getStrung().isSetOtherNickname()) {
                        l.setType(13);
                    }
                    else if (l.getStrung().isClearOwnNickname()) {
                        l.setType(14);
                    }
                    else if (l.getStrung().isClearOtherNickname()) {
                        l.setType(15);
                    }
                    else if (l.getStrung().isGoing()) {
                        l.setType(16);
                    }
                    else if (l.getStrung().isNotGoing()) {
                        l.setType(17);
                    }
                    else if (l.getStrung().isStartedPlan()) {
                        l.setType(18);
                    }
                    else if (l.getStrung().isUpdatedPlan()) {
                        l.setType(19);
                    }
                    else if (l.getStrung().isCall()) {
                        l.setType(22);
                    }
                    else if (l.getStrung().isVideoChat()) {
                        l.setType(23);
                    }
                    else if (l.getStrung().isAddedMember()) {
                        l.setType(24);
                    }
                    else if (l.getStrung().isAddedAdmin()) {
                        l.setType(25);
                    }
                    else if (l.getStrung().isRemovedMember()) {
                        l.setType(26);
                    }
                    else if (l.getStrung().isRemovedAdmin()) {
                        l.setType(27);
                    }
                    else if (l.getStrung().isLeftGroup()) {
                        l.setType(28);
                    }
                    else if (l.getStrung().isNamedGroup()) {
                        l.setType(29);
                    }
                    else if (l.getStrung().isChangedGroupPhoto()) {
                        l.setType(30);
                    }
                    else if (l.getStrung().isReminder()) {
                        l.setType(31);
                    }
                    else if (l.getStrung().isAttachment()) {
                        l.setType(32);
                    }
                    else if (l.getStrung().isVoiceMessage()) {
                        l.setType(33);
                    }
                    else if (l.getStrung().isSetEmoji()) {
                        l.setType(34);
                    }
                    else if (l.getStrung().isChangeColour()) {
                        l.setType(35);
                    }
                }
            }
        }
    }
    public ArrayList<Line> getList() {
        return list;
    }
}

// Any user who has not posted will have their reactions counted as text in the person's message
