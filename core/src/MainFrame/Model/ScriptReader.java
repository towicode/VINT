package MainFrame.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class ScriptReader implements Runnable {

  Actor actor;
  Background background;
  Backlog backlog;
  Progress progress;
  ArrayList<Letter> text;
  public ArrayList<SpriteCus> sprites;
  public ArrayList<Choice> choiceList;
  public Script script;
  Dialog dialog;

  String auth;
  String brain;
  String command;
  String driver;
  String line;

  public static void main(String args[]) {
    ScriptReader x = new ScriptReader();
    x.run();
  }

  public void run() {

    int allowedLevel = 0;

    sprites = new ArrayList<SpriteCus>();
    progress = new Progress();
    choiceList = new ArrayList<Choice>();
    dialog = new Dialog();

    Choice x = new Choice(false, "masons_direction", null);
    x.choose("goto work");
    choiceList.add(x);

    while (true) { // TODO while game is running
      FileHandle handle = Gdx.files.internal("scripts/test.txt");
      String text = handle.readString();

      // Construct BufferedReader from InputStreamReader
      BufferedReader br = new BufferedReader(new StringReader(text));

      try {
        while ((line = br.readLine()) != null) {
          if (line.isEmpty())
            continue;

          if (line.contains("*"))
            continue;

          int count = 0;
          for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
              count++;
            } else
              break;
          }

          // We are

          if (Math.floor(count / 4) > allowedLevel) {
            continue;
          }

          allowedLevel = (int) (Math.floor(count / 4));

          line = line.substring(allowedLevel * 4, line.length());

          char k = line.toCharArray()[0];
          switch (k) {

          // points
          case 'p':
            String pointSub = line.substring(2, line.length() - 1);
            String[] pointSplit = pointSub.split(",");

            if (pointSplit.length != 2) {
              console.badScript();
              return;
            }

            auth = pointSplit[0];
            brain = pointSplit[1];

            if (brain.matches("-?\\d+"))
              progress.setPoint(auth, Integer.valueOf(brain));

            else if (brain.contentEquals("inc"))
              progress.incPoint(auth);

            else if (brain.contentEquals("dec"))
              progress.decPoint(auth);

            else {
              console.badScript();
              return;
            }

            break;
          // if
          case 'i':
            Pattern pattern = Pattern.compile("(if\\()(.+?)(\\))");
            Matcher n = pattern.matcher(line);
            while (n.find()) {
              if (n.groupCount() < 2) {
                console.badScript();
                return;
              }
              auth = n.group(2);

              String[] ifSplit = auth.split(",");
              if (ifSplit.length != 3) {
                console.badScript();
                return;
              }

              brain = ifSplit[0];
              command = ifSplit[1];
              driver = ifSplit[2].substring(1, ifSplit[2].length() - 1);

              if (command.contentEquals("is")) {

                Boolean isTrue = choiceList.stream()
                    .filter(s -> s.getName().contentEquals(brain))
                    .anyMatch(s -> s.getChoice().contentEquals(driver));
                if (isTrue) {
                  allowedLevel++;
                }

              } else if (command.toUpperCase().contentEquals(
                  "isnot".toUpperCase())) {

                Boolean isTrue = choiceList.stream()
                    .filter(s -> s.getName().contentEquals(brain))
                    .noneMatch(s -> s.getChoice().contentEquals(driver));
                if (isTrue) {
                  allowedLevel++;
                }
              }
            }
            break;

          // Choice
          case 'c':
            // Console.info("cccc");
            String choiceSub = line.substring(2, line.length() - 1);
            // String[] saySplit =
            // choiceSub.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            List<String> allMatches = new ArrayList<String>();
            Matcher m = Pattern.compile("\"([^\"]*)\"(,|})").matcher(choiceSub);
            while (m.find()) {
              allMatches.add(m.group().substring(1, m.group().length() - 2));
              // System.out.println(m.group().substring(1,
              // m.group().length()-2));

            }

            String[] choiceSplit = choiceSub.split(",");
            if (choiceSplit.length < 2) {
              console.badScript();
              return;
            }

            String choice_name = choiceSplit[0];
            console.info(choice_name);
            choiceList.add(new Choice(true, choice_name, allMatches));
            break;

          // LOADING
          case 'l':
            String sub = line.substring(2, line.length() - 1);
            String[] subSplit = sub.split(",");

            if (subSplit.length != 4)
              return;

            auth = subSplit[0];
            brain = subSplit[1];
            command = subSplit[2];
            driver = subSplit[3];

            try {
              MainFrame.game.MainFrame.manager.load("Sprites/testImage.png",
                  Texture.class);
              MainFrame.game.MainFrame.manager.finishLoading();
              Texture temp = MainFrame.game.MainFrame.manager.get(
                  "Sprites/testImage.png", Texture.class);

              sprites.add(new SpriteCus(temp));

            } catch (NumberFormatException | NullPointerException e2) {
              e2.printStackTrace();
              console.badScript();
            }
            break;

          // ANIMATION TODO ADD all the animations;
          case 'a':
            String aniSub = line.substring(2, line.length() - 1);
            String[] aniSplit = aniSub.split(",");
            if (aniSplit.length != 2)
              return;

            auth = aniSplit[0];
            brain = aniSplit[1];

            switch (auth) {
            case "show":
              sprites.stream().filter(s -> s.getName().contentEquals(brain))
                  .forEach(s -> s.fadeIn());
              break;
            case "hide":
              sprites.stream().filter(s -> s.getName().contentEquals(brain))
                  .forEach(s -> s.fadeOut());
              break;
            }
            break;

          // BACKGROUNDS
          case 'b':
            String sub1 = line.substring(2, line.length() - 1);
            String[] subSplit1 = sub1.split(",");
            if (subSplit1.length != 3)
              return;
            auth = subSplit1[0];
            brain = subSplit1[1];
            command = subSplit1[2];

            try {

              background = new Background(auth, Integer.valueOf(brain),
                  Integer.valueOf(command));

            } catch (NumberFormatException e1) {
              e1.printStackTrace();
              console.badScript();
            }
            break;

          // WAIT
          case 'w':
            String wait = line.substring(2, line.length() - 1);
            try {
              Thread.sleep(Integer.parseInt(wait));
            } catch (NumberFormatException | InterruptedException e1) {
              e1.printStackTrace();
              console.badScript();
            }
            break;
          // GENERIC SAY
          case '"':
            String text3 = line.substring(1, line.length() - 1);
            // dialog.drawAnimatedText(text);
            InputManager.waitForUser();
            // line skipping concept

            // if (!Letter.letterToString(dialog.getText())
            // .contentEquals(text)) {
            // dialog.setText(text);
            // Thread.sleep(500);
            // InputManager.waitForUser();
            // }

            break;

          // SPECIFICED SAY
          case 's':
            String saySub = line.substring(2, line.length() - 1);
            String[] saySplit = saySub
                .split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            auth = saySplit[0];
            brain = saySplit[1].trim();
            brain = brain.substring(1, brain.length() - 1);

            actor.setName(auth);
            // dialog.drawAnimatedText(brain);
            InputManager.waitForUser();

            // line skipping concept

            if (!Letter.letterToString(dialog.getText()).contentEquals(brain)) {
              dialog.setText(brain);
              Thread.sleep(500);
              InputManager.waitForUser();
            }

            break;

          }
          Thread.sleep(100);
        }
        progress.setTrue("testing");

      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
