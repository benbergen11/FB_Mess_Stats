public class Reaction {
    private String reactionText;
    private int reactionCode;
    private String user;

    public Reaction(String reaction) {
        this.reactionText = reaction.substring(0, 2);
        this.user = reaction.substring(2);

        if (reactionText.equals("\uD83D\uDE0D")) { this.reactionCode = 1; }
        else if (reactionText.equals("\uD83D\uDE06")) { this.reactionCode = 2; }
        else if (reactionText.equals("\uD83D\uDE2E")) { this.reactionCode = 3; }
        else if (reactionText.equals("\uD83D\uDE22")) { this.reactionCode = 4; }
        else if (reactionText.equals("\uD83D\uDE20")) { this.reactionCode = 5; }
        else if (reactionText.equals("\uD83D\uDC4D")) { this.reactionCode = 6; }
        else if (reactionText.equals("\uD83D\uDC4E")) { this.reactionCode = 7; }
        else {this.reactionCode = 8; }
    }

    public String getUser() {
        return user;
    }

    public int getReactionCode() {
        return reactionCode;
    }

    public String toString() {
        return (reactionText + " (" + reactionCode + ") " + user);
    }
}
