package memory;

import MainFrame.Model.Progress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentProgress {

    private static CurrentProgress instance = null;

    private Progress progress;

    protected CurrentProgress() {
        progress = new Progress();
    }

    public static CurrentProgress getInstance() {
        if (instance == null) {
            instance = new CurrentProgress();
        }
        return instance;
    }

    public void addProgress(String test, Boolean bool) {
        progress.add(test, bool);
    }

    public void addPoint(String name, Integer num) {
        progress.setPoint(name, num);
    }

    boolean checkProgress(String test) {

        //we need to remove quotes
        test = test.replace("\"", "");

        //test basic checkpoint
        if (!test.contains("=") && !test.contains(">") && !test.contains("<"))
            return progress.getProgress(test).isDone();

        //test point system.
        Pattern patt = Pattern.compile("([a-z]+) ?<?>?=? ?(\\d+)");
        Matcher m = patt.matcher(test);

        if (m.matches()) {
            String var = (m.group(1));
            int num = Integer.parseInt(m.group(2));

            int varnum = progress.getPoint(var).getValue();

            if (test.contains("<=")) {
                return varnum <= num;
            } else if (test.contains(">=")) {
                return varnum >= num;
            } else if (test.contains(">")) {
                return varnum > num;
            } else if (test.contains("<")) {
                return varnum < num;
            } else if (test.contains("=")) {
                return varnum == num;
            }
        }
        //none of the tests passed, return false
        return false;
    }
}
