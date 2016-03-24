package text;

import MainFrame.game.Console;
import memory.CurrentCommand;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Todd on 2/15/2016.
 */
public class Claim {

    private Command command;
    private ArrayList<Name> names;
    private ArrayList<String> paramaters;
    private ArrayList<Integer> numbers;
    private ArrayList<String> dialog;

    //raw text
    private String r_text;

    //removed quote text
    private String rq_text;

    //used text
    private String text;


    public Claim(String line) {
        this.r_text = line;
        this.names = new ArrayList<>();
        this.dialog = new ArrayList<>();
        this.paramaters = new ArrayList<>();
        this.numbers = new ArrayList<>();

    }


    public void send() throws Exception {
        skipWhiteSpace();
        removeQuoteText();
        parseNames();
        this.command = parseCommand();
        parseText();
        parseParameters();
        parseNumbers();
        this.printToLine(); //TODO DEBUG ONLY
        CurrentCommand.getInstance().setCommand(command, dialog, names, paramaters, numbers);

    }

    public void printToLine() {
        Console.WriteLine("__________________");
        Console.WriteLine("Raw Text: " + this.r_text);
        Console.WriteLine("text: " + this.text);
        Console.WriteLine("RQ Text: " + this.rq_text);
        Console.WriteLine("Command: " + this.command);
        if (names == null)
            return;
        Console.WriteLine("names: ");
        for (Name x : names) {
            Console.WriteLine("    " + x);
        }
        if (dialog == null)
            return;
        Console.WriteLine("dialog(s): ");
        for (String x : dialog) {
            Console.WriteLine("    " + x);
        }
        if (paramaters == null)
            return;
        Console.WriteLine("paramaters: ");
        for (String x : paramaters) {
            Console.WriteLine("    " + x);
        }
        if (numbers == null)
            return;
        Console.WriteLine("numbers: ");
        for (int x : numbers) {
            Console.WriteLine("    " + x);
        }
        Console.WriteLine("__________________");
    }

    public void skipWhiteSpace() {
        String line = this.r_text;
        Boolean started = false;
        int count = (line.length() - line.replace("\"", "").length());

        String tempText = "";

        for (char x : line.toCharArray()) {
            if (x == '\"') {
                count--;
                started = true;
            } else if (!started || count == 0) {
                if (Character.isWhitespace(x))
                    continue;
            }

            tempText += x;

        }
        this.text = tempText;
    }

    /**
     * removeQuoteText:
     *
     * @ post: This function strips all text in quotations and places it in the rq_text string.
     */
    public void removeQuoteText() {
        if (rq_text == null) {
            Pattern rgx = Pattern.compile("([\"\'])(?:(?=(\\\\?))\\2.)*?\\1");
            Matcher m = rgx.matcher(this.text);
            rq_text = m.replaceAll("");
        }
    }

    /**
     * parseNames:
     *
     * @post: this function copies all instances of names in the removed quote text and places them in a special
     * name category
     */
    public void parseNames() {

        if (this.rq_text.contains("("))
            return;

        for (Name name : Name.values()) {
            if (this.rq_text.toUpperCase().contains(name.toString().toUpperCase()))
                this.names.add(name);
        }
    }

    /**
     * ParseCommand:
     *
     * @return returns the command that string.rq_text provides
     * @throws Exception
     */
    private Command parseCommand() throws Exception {

        if (this.text == null)
            throw new Exception("Something wrong with card.text");

        if (this.text.length() < 2)
            throw new Exception("Something wrong with card.text");

        for (Name name : Name.values()) {
            if (this.rq_text.toLowerCase().startsWith(name.toString().toLowerCase()))
                return Command.SAY;
        }


        switch (this.text.charAt(0)) {

            case 'i':
                return Command.IF;
            case '"':
                return Command.GENERIC_SAY;
            case 'c':
                return Command.CHARACTER;
            case 'a':
                return Command.ANIMATION;
            case 'b':
                return Command.BACKGROUND;
            case 'm':
                return Command.MUSIC;
            case 'x':
                return Command.SOUND_EFFECT;
            case 'v':
                return Command.VARIABLE;
            case 'w':
                return Command.WAIT;
        }
        printToLine();
        throw new Exception("Bad Command Choice");

    }

    /**
     * parseText:
     *
     * @post: puts all instances of dialog. Text with "" in the dialog arrayList
     */
    private void parseText() {
        Pattern pattern = Pattern.compile("([\"\'])(?:(?=(\\\\?))\\2.)*?\\1");
        Matcher m = pattern.matcher(this.text);

        while (m.find()) {
            dialog.add(m.group());
        }
    }

    /**
     * parseParameters
     *
     * @post: puts all paramaters inside () brackets into the paramaters array list
     */
    public void parseParameters() {

        if (!this.rq_text.contains(")"))
            return;
        if (!this.rq_text.contains("("))
            return;

        int start = this.rq_text.indexOf("(");
        int end = this.rq_text.indexOf(")");

        String paramse = this.rq_text.substring(start, end);
        Pattern pattern = Pattern.compile("([A-Za-z_=]*)");
        Matcher m = pattern.matcher((paramse));

        while (m.find()) {
            String check = m.group();
            if (check.length() < 1)
                continue;
            paramaters.add(check);
        }
    }

    /**
     * parseNumbers:
     */
    public void parseNumbers() {
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher m = pattern.matcher(this.rq_text);


        while (m.find()) {
            numbers.add(Integer.parseInt(m.group()));
        }
    }

    public Boolean requiresInput() {
        return this.command == Command.GENERIC_SAY || this.command == Command.SAY;
    }


}

