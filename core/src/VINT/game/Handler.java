package VINT.game;

import VINT.Model.Actions;
import VINT.Model.BackgroundActor;
import VINT.Model.CharacterActor;
import VINT.Model.console;
import VINT.factories.BackgroundGetCreateFactory;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;
import VINT.factories.CharacterGetCreateFactory;
import memory.*;
import text.Command;
import text.Name;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static memory.CurrentCommand.getInstance;


/**
 * Created by league on 7/11/2015.
 */
public class Handler {

    // TODO convert this to not be static !

    public static void handle() {

        Command com = getInstance().getCom();
        ArrayList<String> params = getInstance().getParams();

        if (com.equals(Command.VARIABLE)) {
            handleVariable(params);
        } else if (com.equals(Command.BACKGROUND)) {
            handleBackground(params);
        } else if (com.equals(Command.SAY)) {
            handleSay(params);
        } else if (com.equals(Command.CHARACTER)) {
            handleCharacter(params);
        } else if (com.equals(Command.SOUND_EFFECT)) {
            handleSoundEffect(params);
        } else if (com.equals(Command.MUSIC)) {
            handleMusic(params);
        } else if (com.equals(Command.ANIMATION)){
            handleAnimation(params);
        }
    }

    private static void handleMusic(ArrayList<String> params) {

        String music_name = null;

        music_name = params.get(0);
        params.remove(0);

        final String finalMusic_name = music_name;
        new Thread() {
            public void run() {
                CurrentMusic.getInstance().createOrModify(finalMusic_name, params);
            }
        }.start();

    }

    private static void handleSoundEffect(ArrayList<String> params) {

        String sound_effect_name;

        sound_effect_name = params.get(0);
        params.remove(0);

        if (sound_effect_name == null)
            return;

        Sound x = SoundEffects.getInstance().loadSound(sound_effect_name + ".ogg");
        x.play();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        x.dispose();
                    }
                },
                10000
        );
    }

    private static void handleCharacter(ArrayList<String> params) {

        CharacterGetCreateFactory factory = new CharacterGetCreateFactory(params);
        handleAnimation(params, factory.getOrCreate());
        factory.dispose();
    }

    private static void handleSay(ArrayList<String> params) {

        ArrayList<Name> names = getInstance().getNames();
        if (names.size() == 0)
            CurrentModelActor.getInstance().setName(null);
        else {
            Name actor_name = names.stream().findFirst().get();
            CurrentModelActor.getInstance().setName(actor_name.full());
            CurrentModelActor.getInstance().setColor(actor_name.color());
        }
    }

    private static void handleBackground(ArrayList<String> params) {
        Vint.bg.getChildren().forEach(Actor::remove);
        BackgroundGetCreateFactory factory = new BackgroundGetCreateFactory(params);
        handleAnimation(params, factory.getOrCreate());
        factory.dispose();
    }

    private static void handleAnimation(ArrayList<String> params) {
        String sprite_char_name = params.get(0);

        SnapshotArray<Actor> curChildren = Vint.fg.getChildren();
        CharacterActor sprite_image_actor = null;

        Actor[] items = curChildren.begin();
        for (int i = 0, n = curChildren.size; i < n; i++) {
            Actor item = items[i];
            if (item.getName().equals(sprite_char_name)) {
                sprite_image_actor = (CharacterActor) item;
                break;
            }
        }
        curChildren.end();

        if (sprite_image_actor == null)
            return;
        handleAnimation(params, sprite_image_actor);
    }


    public static void handleAnimation(ArrayList<String> params, Actor actor_name) {

        // TODO


        String possible_animations = CurrentAnimations.getInstance().getList();
        String chosen_animation = null;
        for (String k : params) {
            if (possible_animations.contains(" " + k + " ")) {
                chosen_animation = k;
                params.remove(k);
                break;
            }
        }

        if (chosen_animation == null)
            return;

        Class<Actions> x = Actions.class;
        try {
            Class[] cArg = null;
            if (params.size() > 0) {
                cArg = new Class[1];
                cArg[0] = ArrayList.class;
                Method animation = x.getDeclaredMethod(chosen_animation, cArg);
                actor_name.addAction((Action) animation.invoke(x, params));
                return;
            }
            Method animation = x.getDeclaredMethod(chosen_animation, null);
            actor_name.addAction((Action) animation.invoke(x));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void handleVariable(ArrayList<String> params) {
        boolean yes = getInstance().getParams().stream().anyMatch(s -> s.toLowerCase().contains("true"));
        boolean no = getInstance().getParams().stream().anyMatch(s -> s.toLowerCase().contains("false"));

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
