package memory;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import MainFrame.Model.console;
import groovy.lang.Binding;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import text.Command;
import text.Name;

public class CurrentCommand extends Observable {

  private static CurrentCommand instance = null;

  private Command com;
  private ArrayList<String> text;
  private ArrayList<Name> names;
  private ArrayList<String> params;
  private ArrayList<Integer> locs;
  @SuppressWarnings("rawtypes")
  private ArrayList empty = new ArrayList();

  protected CurrentCommand() {

  }

  public static CurrentCommand getInstance() {
    if (instance == null) {
      instance = new CurrentCommand();

    }
    return instance;
  }

  public void setCommand(Command com, ArrayList<String> p_text,
      ArrayList<Name> names, ArrayList<String> params, ArrayList<Integer> locs) {

    this.com = com;
    this.text = p_text;
    this.names = names;
    this.params = params;
    this.locs = locs;
    setChanged();
    notifyObservers();
  }

  public int getLevelModifier() {

      if (this.com != Command.IF)
          return 0;

    Binding binding = new Binding();
    GroovyShell shell = new GroovyShell(binding);

    for (String test : this.getText()) {
        test = test.substring(1,test.length() -1);

        Object value2 = shell.evaluate(test);

        try {
            if (!(boolean) value2)
                return 0;
        } catch(Exception e) {
            return 0;
        }
    }

    return 1;
  }

  public Command getCom() {
    if (com != null)
      return com;

    return null;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<String> getText() {
    if (text != null)
      return text;

    return empty;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Name> getNames() {
    if (names != null)
      return names;

    return empty;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<String> getParams() {
    if (params != null)
      return params;

    return empty;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Integer> getLocs() {
    if (locs != null)
      return locs;

    return empty;
  }

}
