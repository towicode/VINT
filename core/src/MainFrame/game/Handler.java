package MainFrame.game;

import MainFrame.Model.console;
import memory.CurrentCommand;
import memory.CurrentProgress;
import text.Command;

import static memory.CurrentCommand.getInstance;

/**
 * Created by league on 7/11/2015.
 */
public class Handler {


    public static void handle() {

        if (getInstance().getCom().equals(Command.VARIABLE)) {
            handleVariable();
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
