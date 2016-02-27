package MainFrame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import input.Keyboard;
import memory.CurrentCommand;
import text.Command;
import text.Expando;
import text.Spreader;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends ApplicationAdapter implements Observer {

    public static AssetManager manager = new AssetManager();
    private Keyboard keyboard;


    private static Expando script;
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureAtlas backgroundAtlas;
    private Sprite sprite;

    public static Expando getScript() {
        return script;
    }

    public static void setScript(Expando script) {
        MainFrame.script = script;
    }

    @Override
    public void create() {

        CurrentCommand.getInstance().addObserver(this);
        keyboard = new Keyboard();
        Gdx.input.setInputProcessor(keyboard);

        Spreader loader = new Spreader(null);

        script = loader.CreateExpando("blank");
        script.currentCard.send();
        script.next();

        batch = new SpriteBatch();
        backgroundAtlas = new TextureAtlas(Gdx.files.internal("backgrounds/pack.atlas"));
        AtlasRegion region = backgroundAtlas.findRegion("evansapartment_day_spring");
        sprite = new Sprite(region);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (keyboard.cont() || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
            script.next();

        batch.begin();
        sprite.draw(batch);
        if (CurrentCommand.getInstance().getCom() == Command.SAY)
            font.draw(batch, (CurrentCommand.getInstance().getText().get(0)), 200, 200);
        batch.end();


        // batch.begin();
        // r.sprites.stream().forEach(s -> s.draw(batch));
        // batch.end();

    }

    @Override
    public void update(Observable e, Object arg1) {

        //System.out.println(CurrentCommand.getInstance().getCom());


        Handler.handle();
/*    CurrentCommand.getInstance().getText().stream()
        .forEach(p -> console.log(p));*/

/*
    CurrentCommand.getInstance().getNames().stream()
        .forEach(p -> console.log(p));

    CurrentCommand.getInstance().getLocs().stream()
        .forEach(p -> console.log(p));

    CurrentCommand.getInstance().getParams().stream()
        .forEach(p -> console.log(p));
*/

    }
}
