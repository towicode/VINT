package text;

import MainFrame.game.MainFrame;
import memory.CurrentCommand;

public class Card {

    protected int lineNumber;
    protected String text;
    protected Card previousCard;
    protected Card nextCard;
    private int indentLevel;


    // for the link list card instructor

    /**
     * Constructor for card that provides an option for the previous card
     *
     * @param ln line number
     * @param tx text
     * @param pc previous card
     */
    public Card(int ln, String tx, Card pc) {
        this.lineNumber = ln;
        this.previousCard = pc;
        this.text = tx;
        this.indentLevel = getIndentLevel();

    }

    /**
     * Generic Constructor for card that is used initially by the expando
     *
     * @param line    the text of the first line
     * @param lineNum the line number
     */
    public Card(String line, int lineNum) {
        this.lineNumber = lineNum;
        this.text = line;

    }

    /**
     * This special static void loads all additional commands after the current card.
     *
     * @param card_strings
     */
    public static void load(Card start, String[] card_strings) {

        Card temp = start;

        if (card_strings.length <= 1)
            return;

        for (int i = 1; i < card_strings.length; i++) {
            temp.nextCard = new Card(temp.lineNumber + 1, card_strings[i], temp);
            temp = temp.nextCard;
        }

    }

    /**
     * Returns wether or not this card has a next component
     *
     * @return
     */
    public boolean canAdvance() {
        return this.nextCard != null;
    }


    /**
     * returns wether or no this card has a previous component
     *
     * @return
     */
    public boolean canDeadvance() {
        return this.previousCard != null;
    }


    /**
     * this function simply calculates the current indent level of the line
     * @return
     */
    private int getIndentLevel() {
        int spaces = 0;
        for (char x : this.text.toCharArray()) {
            if (Character.isWhitespace(x)) {
                spaces++;
            } else {
                break;
            }
        }
        return (int) Math.ceil((double) spaces / 4.00);
    }

    /**
     * The send function does a number of things.
     *  1: Checks to see if we even need to send this card based on the current allowance in the indent statements
     *  2: Creates and parses the text of the command in the claim engine
     *  3: Calls the send function of the claim engine
     *  4: checks the allowed level of the current script and adjusts based on the current claim
     *  5: this method returns true if the user needs to provide input based on the current line
     * @return
     */
    public boolean send() {
        if (this.indentLevel > MainFrame.getScript().allowedLevel) {
            return false;
        }

        Claim claim = new Claim(this.text);
        try {
            claim.send();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        claim.printToLine(); //TODO : This is only for debug
        //reset the allowed level, so if an if statement ends we don't grab the next indent.
        MainFrame.getScript().allowedLevel = this.indentLevel;
        //evaluates an if statement
        MainFrame.getScript().allowedLevel += CurrentCommand.getInstance().getLevelModifier();

        return claim.requiresInput();
    }
}
