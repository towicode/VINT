package MainFrame.game;

import MainFrame.Model.console;
import memory.CurrentBackground;
import memory.CurrentCommand;
import memory.CurrentModelActor;
import memory.CurrentProgress;
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

        ArrayList<String> params = getInstance().getParams();

        if (params.size() == 0)
            CurrentBackground.getInstance().setName(null);

        else {
            String background_name = params.stream().findFirst().get();
            CurrentBackground.getInstance().setName(background_name);
        }


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
}
