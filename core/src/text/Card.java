package text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import memory.CurrentCommand;

public class Card {

    private static final int STRING_START = 0;
    protected Expando expando;
    protected boolean EOE;
    protected int lineNumber;
    protected String text;
    protected Card PreviousCard;
    protected Card NextCard;
    private int indentLevel;
    private String fileName;

    private Command com;
    private ArrayList<String> P_text;
    private ArrayList<Name> names;
    private ArrayList<String> params;
    private ArrayList<Integer> locs;

    private String r_text; // removed text

    // for the link list card instructor
    public Card(int ln, String tx, Card pc, String fn, Expando ex) {
        this.lineNumber = ln;
        this.PreviousCard = pc;
        this.fileName = fn;
        this.expando = ex;
        this.text = tx;
        this.indentLevel = getIndentLevel();

        if (expando != null) {
            // this.loadNextCard(expando);


        }

    }

    // for generic building
    public Card(String line, int lineNum, String filename) {
        this.lineNumber = lineNum;
        this.text = line;
        this.fileName = filename;

    }

    public boolean advance() {
        // expando.cardNumber++;
        if (this.NextCard == null) {
            return false;
        }


        return true;
    }

    public boolean devance() {
        if (expando.cardNumber == 0) {
            return false;
        }
        // expando.cardNumber--;
        return (this.PreviousCard != null);
    }

    private void loadCard() {
        this.parse();
    }

    private void loadNextCard(Expando expando) {

        // code assumes you won't start a script with an indent.
        Card storage;
        try {
            storage = expando.cards.get(expando.cardNumber + 1);
            expando.cardNumber++;
        } catch (IndexOutOfBoundsException e) {
            storage = null;
        }

        // we've ran out of cards so run the GC
        if (storage == null) {
            expando.cards = null;
            System.gc(); // call the GC
            return;
        }

        // create a card and put it as the next card
        this.NextCard = new Card(storage.lineNumber, storage.text, this, this.fileName,
                this.expando);
        this.NextCard.parse();
        this.NextCard.loadNextCard(expando);
    }

    private ArrayList<String> matchRegex(String re, boolean remove_text) {

        if (r_text == null) {
            r_text = this.text.replaceAll("([\"\'])(?:(?=(\\\\?))\\2.)*?\\1", "");
        }

        Pattern pattern = Pattern.compile(re, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        if (remove_text)
            matcher = pattern.matcher(this.r_text);
        else
            matcher = pattern.matcher(this.text);

        // check all occurance
        ArrayList<String> text = new ArrayList<>();
        while (matcher.find()) {
            String temp = matcher.group();
            if (!temp.isEmpty()) {
                text.add(temp);
            }
        }
        return text;
    }

    private ArrayList<String> match(String re, boolean remove_text) {
        this.skipWhiteSpace();
        return matchRegex(re, remove_text);
    }

    private ArrayList<String> match(String re) {
        this.skipWhiteSpace();
        return matchRegex(re, false);
    }

    private void skipWhiteSpace() {
        String line = this.text;
        boolean started = false;
        int count = line.length() - line.replace("\"", "").length();
        String game_text = "";
        for (char x : this.text.toCharArray()) {
            if (x == '"') {
                count--;
                started = true;
                // game_text += x;
            } else if (!started || count == 0) {
                if (Character.isWhitespace(x)) {
                    continue;
                }
            }

            game_text += x;

        }
        this.text = game_text;
    }

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

    private void parse() {
        this.P_text = text();
        this.com = command();
        this.names = name();
        this.params = parameters();
        this.locs = integer();

    }

    private Command command() {

        this.skipWhiteSpace();

        if (this.text == null)
            return null;

        if (this.text.length() < 2)
            return null;

        Name[] names = Name.values();

        Boolean name = Arrays.stream(names).anyMatch(
                (n -> this.text.toLowerCase().startsWith(n.toString().toLowerCase())));

        if (name) {
            return Command.SAY;
        }
        switch (this.text.charAt(STRING_START)) {

            case 'i':
                return Command.IF;
            case '\"':
                return Command.GENERIC_SAY;
            case 'c':
                return Command.CHOICE;
            case 'a':
                return Command.ANIMATION;
            case 'b':
                return Command.BACKGROUND;
            case 'm':
                return Command.MUSIC;
            case 'f':
                return Command.SOUND_EFFECT;
            case 'v':
                return Command.VARIABLE;
            case 'w':
                return Command.WAIT;

        }
        return null;
    }

    private ArrayList<String> text() {
        return match("([\"\'])(?:(?=(\\\\?))\\2.)*?\\1");
    }

    private ArrayList<Integer> integer() {

        ArrayList<String> str_nums = match("(\\d*)", true);

        try {
            return str_nums.stream()
                    .filter(p -> p != null).map(Integer::new)
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (NumberFormatException e) {
        }
        return null;
    }

    private ArrayList<Name> name() {

        Name[] names = Name.values();
        ArrayList<Name> temp = new ArrayList<>();

        for (Name x : names) {

            String name = x.toString();
            if (this.r_text.toUpperCase().contains(name.toUpperCase()))
                temp.add(x);
        }

        return temp;
    }

    private ArrayList<String> parameters() {

        if (!this.r_text.contains(")"))
            return null;
        if (!this.r_text.contains("("))
            return null;

        int start = this.r_text.indexOf("(");
        int end = this.r_text.indexOf(")");

        String Params = this.r_text.substring(start, end);

        String temp = this.r_text;

        this.r_text = Params;
        ArrayList<String> params = match("([A-Za-z_]*)", true);
        this.r_text = temp;
        return params;
    }

    // only gets set on first, the the cards automatically create and add
    // eachother.
    public void setExpando(Expando expando2) {
        this.expando = expando2;
        this.loadCard();
        this.loadNextCard(expando);

    }

    public boolean send() {
        if (this.indentLevel > expando.allowedLevel) {
            return false;
        }
        CurrentCommand.getInstance().setCommand(com, P_text, names, params, locs);
        //reset the allowed level, so if an if statement ends we don't grab the next indent.
        expando.allowedLevel = this.indentLevel;
        //evaluates an if statement
        expando.allowedLevel += CurrentCommand.getInstance().getLevelModifier();

        return CurrentCommand.getInstance().getCom() == Command.SAY;
    }
}
