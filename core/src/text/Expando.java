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
    currentCard.setExpando(this);
    currentCard.send();
  }

  public boolean next() {

    if (currentCard.advance()) {
      currentCard = currentCard.NextCard;
      while (!currentCard.send()) {

        return next();

      }
      return true;
    }
    return false;
  }

  public boolean previous() {

    if (currentCard.devance()) {
      currentCard = currentCard.PreviousCard;
      return true;
    }
    return false;
  }

}
