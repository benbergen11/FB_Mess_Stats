import java.util.ArrayList;
import java.lang.Character;

public class Strung {
    private String text;

    public void stripText() {
        text = text.substring(1);
    }

    private int numCapitals() {
        int counter = 0;
        for (int i=0; i<text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i)))
                counter += 1;
            }
        return counter;
    }

    public Strung(String s) {
        this.text = s;
    }

    public String getText() {
        return this.text;
    }

    public boolean isDate() {
        if (text.contains(":") && text.contains(",")) {
            if (text.endsWith("AM") || text.endsWith("PM")); {
                if (text.contains("2014") || text.contains("2015") || text.contains("2016") || text.contains("2017") || text.contains("2018") || text.contains("2019") || text.contains("2020")) {
                    if (text.contains("Jan") || text.contains("Feb") || text.contains("Mar") || text.contains("Apr") || text.contains("May") || text.contains("Jun") || text.contains("Jul") || text.contains("Aug") || text.contains("Sep") || text.contains("Oct") || text.contains("Nov") || text.contains("Dec")) {
                        if (text.length() < 25) { return true; }
                    }
                }
            }
        }
        return false;
    }

    public boolean isReaction(ArrayList<String> u) {
        boolean a = (text.contains("\uD83D\uDE06") || text.contains("\uD83D\uDE22") || text.contains("\uD83D\uDC4D") || text.contains("\uD83D\uDE0D") || text.contains("\uD83D\uDE2E") || text.contains("\uD83D\uDC4E") || text.contains("\uD83D\uDE20"));
        if (!a) {
            return false;
        }

        if (text.length() <= 3) {
            return false;
        }

        if (text.charAt(2) == ' ') {
            return false;
        }

        for (String s : u) {
            if (text.contains(s)) {
                char test1 = text.charAt(2);
                char test2 = s.charAt(0);
                if (test1 == test2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return (text.equals(""));
    }

    public boolean isGIF() {
        if (text.contains(" sent a GIF from ") || text.contains(" sent a photo from ")) { return true; }
        return false;
    }

    public boolean isVideo() {
        if (text.contains(" sent a video.")) { return true; }
        return false;
    }

    public boolean isMultiVideo() {
        if (text.contains(" videos.")) {
            for (int i = 2; i <= 50; i++) {
                if (text.contains(" sent " + i + " videos.")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPhoto() {
        if (text.contains(" sent a photo.")) { return true; }
        return false;
    }

    public boolean isMultiPhoto() {
        if (text.contains(" photos.")) {
            for (int i = 2; i <= 50; i++) {
                if (text.contains(" sent " + i + " photos.")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSticker() {
        if (text.contains(" sent a sticker.")) { return true; }
        return false;
    }

    public boolean isGameText() {
        char c = text.charAt(text.length() - 1);
        boolean p = (c == '.');
        boolean e = (c == '!');
        boolean q = (c == '?');

        if (text.contains(" scored ") && (text.contains(" in a game."))) {   //scored #### in a game.
            if (p) { return true; }
        }
        else if (text.contains(" scored ") && ((text.contains(" points in ")) || (text.contains(" point playing basketball.")) || (text.contains(" points playing basketball."))|| (text.contains(" point playing soccer.")) || (text.contains(" points playing soccer.")))) {  //scored #### points in ____. (or playing basketball, soccer)
            if (p) { return true; }
        }
        else if (text.contains(" set a new personal best ")) {
            if (p || q) { return true; } // personal best score statement
        }
        else if (text.contains(" moved up the leaderboard in ")) {
            if (p) { return true; } // leaderboard statement
        }
        else if (text.contains(" first place ")) {
            if ((text.contains(" is now in first place in ")) && p) { // first place statement
                return true;
            }
            else if (text.contains(" first place in EverWing")) {
                return true;
            }
        }
        else if ((text.contains(" 8 Ball Pool!") && text.contains(" challenged ")) || (text.contains(" challenged you ") && (p || e || q))) { //game challenges
            return true;
        }
        else if ((text.contains(" passed ") && text.contains("EverWing")) || text.contains(" passed you and made a big gap.")) { //passed statement
            return true;
        }
        else if (text.contains("failed to pot any balls.") || ((text.contains(" left.") || text.contains("missed!")) && text.contains("'s turn.")) || (text.contains("! Will") && text.contains(" want a rematch?"))) { // pool
            if (p || e || q) { return true; }
        }
        else if (text.contains("Putt Paradise!") || (text.contains("hole,") && text.contains(" finished ") && text.contains(" turn!")) || (text.contains("points, ") && text.contains(" beat them?"))) { //Putt Paradise
            return true;
        }
        else if (text.contains(" TRACK & FIELD 100M.")) { //track & field
            return true;
        }
        else if (text.contains(" in Shuffle Cats Mini.")) { //shuffle cats mini
            return true;
        }
        else if (text.contains(" SongPop Arcade!")) { // songpop arcade
            return true;
        }
        else if (text.contains(" How many words can you guess?")) { //4 pics 1 word
            return true;
        }
        else if (text.contains(" Jelly Crush.")) { //Jelly Crush
            return true;
        }
        else if (text.contains("an you earn more?")) { //Money game
            if (q) { return true; }
        }
        else if (text.contains(" invited you to play ")) { //game invitation
            return true;
        }
        return false;
    }

    public boolean isSetOwnNickname() {
        if (text.contains(" set ") && text.contains(" own nickname to ")) { return true; }
        return false;
    }

    public boolean isSetOtherNickname() {
        if (text.contains(" set the nickname for ") || text.contains(" set your nickname to ")) { return true; }
        return false;
    }

    public boolean isClearOwnNickname() {
        if (text.contains(" cleared ") && text.contains("own nickname.")) { return true; }
        return false;
    }

    public boolean isClearOtherNickname() {
        if (text.contains(" cleared the nickname for ")) { return true; }
        return false;
    }

    public boolean isGoing() {
        if (text.contains(" responded Going to ")) { return true; }
        return false;
    }

    public boolean isNotGoing() {
        if (text.contains(" responded Can't Go to ")) { return true; }
        return false;
    }

    public boolean isStartedPlan() {
        if (text.contains(" started a plan.")) { return true; }
        return false;
    }

    public boolean isUpdatedPlan() {
        if (text.contains(" updated the plan ") || text.contains(" named the plan ")) { return true; }
        return false;
    }

    public boolean isVotedPoll() {
        if (((text.contains(" voted for ")) || (text.contains(" changed vote to ")) || (text.contains(" removed vote for "))) && (text.contains(" in the poll: "))) {
            if ((text.charAt(text.length() - 1) == '.') || (text.charAt(text.length() - 1) == '?') || (text.charAt(text.length() - 1) == '!')) {
                return true;
            }
        }
        return false;
    }

    public boolean isCretedPoll() {
        if (text.contains(" created a poll: ")) {
            if ((text.charAt(text.length() - 1) == '.') || (text.charAt(text.length() - 1) == '?') || (text.charAt(text.length() - 1) == '!')) {
                return true;
            }
        }
        return false;
    }

    public boolean isCall() {
        if ((text.contains(" started a call.")) || (text.contains(" joined the call.")) || (text.contains("The call ended."))) { return true; }
        return false;
    }

    public boolean isVideoChat() {
        if ((text.contains(" started a video chat.")) || (text.contains(" joined the video chat.")) || (text.contains("The video chat ended."))) { return true; }
        return false;
    }

    public boolean isAddedMember() {
        if (text.contains(" added ")) {
            if (this.numCapitals() >= 2) {
                if (text.length() <= 70) {
                    if (!(text.contains("group admin"))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isAddedAdmin() {
        if ((text.contains(" added ")) && (text.contains(" as a group admin."))) { return true; }
        return false;
    }

    public boolean isRemovedMember() {
        if ((text.contains(" removed ")) && (text.contains(" from the group."))) { return true; }
        return false;
    }

    public boolean isRemovedAdmin() {
        if ((text.contains(" removed ")) && (text.contains(" as a group admin."))) { return true; }
        return false;
    }

    public boolean isLeftGroup() {
        if (text.contains(" left the group.")) { return true; }

        return false;
    }

    public boolean isNamedGroup() {
        if (text.contains(" named the group ")) { return true; }
        return false;
    }

    public boolean isChangedGroupPhoto() {
        if (text.contains(" changed the group photo.")) { return true; }
        return false;
    }

    public boolean isReminder() {
        if (text.contains("Reminder, ")) {
            if (text.endsWith("AM.") || text.endsWith("PM.")) {
                return true;
            }
        }
        return false;
    }

    public boolean isAttachment() {
        if (text.contains(" sent an attachment.")) { return true; }
        return false;
    }

    public boolean isVoiceMessage() {
        if (text.contains(" sent a voice message.")) { return true; }
        return false;
    }

    public boolean isSetEmoji() {
        if (text.contains(" set the emoji to")) { return true; }
        return false;
    }

    public boolean isChangeColour() {
        if (text.contains(" changed the chat colors.")) { return true; }
        return false;
    }
}
