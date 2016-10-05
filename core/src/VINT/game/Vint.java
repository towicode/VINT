package VINT.game;

import VINT.Model.Font;
import VINT.Model.Script;
import VINT.Model.TypeWriter;
import VINT.stages.GameStage;
import VINT.stages.GameStates;
import VINT.stages.MainMenuStage;
import VINT.stages.VintStage;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import input.Keyboard;
import memory.*;
import text.Command;
import text.Expando;
import text.Spreader;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Vint extends ApplicationAdapter {
    public static VintStage stage;
    public static Group bg = new Group();
    public static Group fg = new Group();
    private static Expando currentScript;
    private static InputMultiplexer inputMultiplexer = new InputMultiplexer();



    @Override
    public void create() {
        stage = new MainMenuStage(new ScreenViewport());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
        stage.render();
    }

    public static void setCurrentScript(Expando currentScript) {
        Vint.currentScript = currentScript;
    }

    public static Expando getCurrentScript() {
        return currentScript;
    }

    public static InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public static void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        Vint.inputMultiplexer = inputMultiplexer;
    }
}
