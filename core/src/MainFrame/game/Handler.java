package MainFrame.game;

import MainFrame.Model.console;
import memory.*;
import text.Command;
import text.Name;

import java.util.ArrayList;

import static memory.CurrentCommand.getInstance;

/**
 * Created by league on 7/11/2015.
 */
public class Handler {


    public static void handle() {

        if (getInstance().getCom().equals(Command.VARIABLE)) {
            handleVariable();
        }
        
        else if (getInstance().getCom().equals(Command.BACKGROUND)){
            handleBackground();
        }

        else if (getInstance().getCom().equals(Command.SAY)){
            handleSay();
        }
        
        else if (getInstance().getCom().equals(Command.CHARACTER)){
            handleCharacter();
        }
    }

    private static void handleCharacter() {

        ArrayList<String> params = getInstance().getParams();

        if (params.size() == 0)
            CurrentSprites.getInstance().add("missingno");

        else {
            String character_name = params.stream().findFirst().get();
            CurrentSprites.getInstance().add(character_name);
        }

    }

    private static void handleSay() {

        ArrayList<Name> names = getInstance().getNames();
        if (names.size() == 0)
            CurrentModelActor.getInstance().setName(null);
        else {
            Name actor_name = names.stream().findFirst().get();
            CurrentModelActor.getInstance().setName(actor_name.full());
        }

    }

    private static void handleBackground() {

        // get the first one.
        // TODO: We need to have this handle options

        CurrentBackground.getInstance().setName(null);

        ArrayList<String> params = getInstance().getParams();
        String background_name = null;


        /**
         * This for block finds the first instance of a background name and removes it.
         * TODO: animation names like show and hide should not be allowed in background names
         */
        for (String k : params){
            if (isBackground(k)){
                background_name = k;
                params.remove(k);
                break;
            }
        }

        CurrentBackground.getInstance().setName(background_name);
        handleAnimation(params);

    }

    private static void handleAnimation() {
        ArrayList<String> params = getInstance().getParams();
        handleAnimation(params);
    }


    public static void handleAnimation(ArrayList<String> params){

        String possible_animations = " pkeuIfT7 show hide disolve bedroom_day ClCs7pb1 5jIg6p5z ahQIezVK gpvTi8wJ DFWYobkW 1clZzJhv zZAQbpke qO1YSWaM jxdVgOT0 Bq1fDtiq nShStL9F rVChRrNu cjpqTKjY fYl79Jst GHCh9yhe MYk6frkh mBwmilFo 7cIA1P2V u9ktzHJi oPHnl3VV 8GWmOL6A GEzMyK1y 1yx8kUgn XbG79BzU x9xghQsL GfB3BOBu 49Dlw0uN izsxm3iZ gx0hFDeC sHapDrYo i7eMSEl8 xJjBCdZ0 JHhK5fWY iG8t1FoD fade Ydemw28w ";

        System.out.println(System.currentTimeMillis());
        for (String k : params){
            if (possible_animations.contains(" " + k + " ")){
                System.out.println("animation is: " + k);
                params.remove(k);
                break;
            }
        }
        System.out.println(System.currentTimeMillis());
    }


    private static void handleVariable() {
        boolean yes;
        boolean no;
        yes = getInstance().getParams().stream().anyMatch(s -> s.toLowerCase().contains("true"));
        no = getInstance().getParams().stream().anyMatch(s -> s.toLowerCase().contains("false"));

        if (yes || no) {
            for (String line : getInstance().getParams()) {
                if (line.contains("true") || line.contains("false"))
                    continue;
                CurrentProgress.getInstance().addProgress(line, yes);
            }
            // we have a basic variable

            return;
        }

        int num = 0;

        if (!getInstance().getLocs().isEmpty()) {
            num = getInstance().getLocs().stream().findFirst().get();
        }

        for (String line : getInstance().getParams()) {
            if (!isInteger(line)) {
                console.log(line + " " + num);
                CurrentProgress.getInstance().addPoint(line, num);
            }
        }
    }

    private static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    private static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    private static boolean isBackground(String s){
        return CurrentBackground.background_list.contains(" " + s + " ");
    }
}

