package text;

import MainFrame.game.Console;
import memory.CurrentCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Todd on 2/15/2016.
 */
public class Claim {

    private Command command;
    private ArrayList<String> P_text;
    private ArrayList<Name> names;
    private ArrayList<String> paramaters;
    private ArrayList<Integer> numbers;
    private ArrayList<String> dialog;

    //raw text
    private String r_text;


    public Claim(String line){
        this.r_text = line;
        this.names = new ArrayList<>();
        this.dialog = new ArrayList<>();
        this.paramaters = new ArrayList<>();
        this.numbers = new ArrayList<>()

    }


    public void send() {
        skipWhiteSpace ();
        removeQuoteText ();
        //whoops
        parseNames ();
        this.command = parseCommand ();
        parseText ();
        parseParameters ();
        parseNumbers ();
        CurrentCommand.getInstance().setCommand(command, P_text, names, params, locs);

    }

    public void printToLine ()
    {
        Console.WriteLine("Raw Text: " + this.r_text);
        Console.WriteLine("text: " + this.text);
        Console.WriteLine ("RQ Text: " + this.rq_text);
        Console.WriteLine ("Command: " + this.command);
        if (names == null)
            return;
        Console.WriteLine ("names: ");
        for(Name x : names) {
        Console.WriteLine ("    " + x);
    }
        if (dialog == null)
            return;
        Console.WriteLine ("dialog(s): ");
        for (String x : dialog) {
        Console.WriteLine ("    " + x);
    }
        if (paramaters == null)
            return;
        Console.WriteLine("paramaters: ");
        for (String x : paramaters) {
        Console.WriteLine ("    " + x);
    }
        if (numbers == null)
            return;
        Console.WriteLine("numbers: ");
        for (int x : numbers) {
        Console.WriteLine ("    " + x);
    }
    }

    public Boolean requiresInput ()
    {
        return this.command == Command.GENERIC_SAY || this.command == Command.CHOICE || this.command == Command.SAY;
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
}



