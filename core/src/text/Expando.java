package text;

import MainFrame.game.Console;

public class Expando {

    protected int allowedLevel;
    protected String fileName;
    public Card currentCard;


    public Expando(String fileName, String[] card_strings) {
        this.fileName = fileName;

        if (card_strings == null || card_strings.length <= 0) {
            Console.WriteLine("Error: cards was empty @expando.cs"); //TODO error log
            return;
        }

        this.currentCard = new Card(card_strings[0], 0);
        currentCard.load(currentCard, card_strings);

    }

    public Boolean next() {
        if (!currentCard.canAdvance()) {
            Console.WriteLine("could not advance");
            return false;
        }

        currentCard = currentCard.nextCard;

        // send will return true, once it reaches a point that requires human feedback
        // otherwise we will keep sending data to the engine that doesn't require human feedback.
        while (!currentCard.send()) {
            return next();
        }

        return true;
    }

    public Boolean previous() {
        if (!currentCard.canDeadvance())
            return false;

        currentCard = currentCard.previousCard;
        // send will return true, once it reaches a point that requires human feedback
        // otherwise we will keep sending data to the engine that doesn't require human feedback.
        while (!currentCard.send()) {
            return next();
        }

        return true;
    }
}
