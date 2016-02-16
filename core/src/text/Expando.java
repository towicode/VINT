package text;

import java.util.LinkedList;

import MainFrame.Model.console;

public class Expando {

  protected int cardNumber;
  protected int allowedLevel;
  protected String fileName;
  protected LinkedList<Card> cards;
  protected Card currentCard;

  /**
   * @param fileName
   * @param cards
   */
  public Expando(String fileName, LinkedList<Card> cards) {
    this.fileName = fileName;
    this.cards = cards;
    this.allowedLevel = 0;

    if (cards == null)
      return;

    if (cards.size() <= 1)
      return;

    currentCard = cards.getFirst();
    currentCard.send();
  }

  public boolean next() {

    if (currentCard.canAdvance()) {
      currentCard = currentCard.nextCard;
      while (!currentCard.send()) {

        return next();

      }
      return true;
    }
    return false;
  }

  public boolean previous() {

    if (currentCard.canDeadvance()) {
      currentCard = currentCard.previousCard;
      return true;
    }
    return false;
  }

}
