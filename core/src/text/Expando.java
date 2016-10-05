package text;


import VINT.Model.Save;
import VINT.game.Console;
import VINT.game.Vint;

import java.io.IOException;

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

    public Expando(String script_name, String[] card_strings, Save save) {
        this.fileName = script_name;
        if (card_strings == null || card_strings.length <= 0) {
            Console.WriteLine("Error: cards was empty @expando.cs"); //TODO error log
            return;
        }


        //TODO is this how we want to do this?
        Vint.setCurrentScript(this);

        this.currentCard = new Card(card_strings[0], 0);
        currentCard.load(currentCard, card_strings);

        for (int i =0; i < save.lineNumber; i++){
            this.next();
        }
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

    public void quickSave() {
        Save q_save = new Save(this.fileName, this.currentCard.lineNumber);
        q_save.save();
    }
}
