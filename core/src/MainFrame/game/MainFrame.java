package MainFrame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.Keyboard;
import memory.CurrentBackground;
import memory.CurrentCommand;
import memory.CurrentModelActor;
import text.Command;
import text.Expando;
import text.Spreader;

import java.util.Observable;
import java.util.Observer;

public class MainFrame extends ApplicationAdapter implements Observer {

    public static AssetManager manager = new AssetManager();
    private static Expando script;
    private Keyboard keyboard;
    private SpriteBatch batch;
    private BitmapFont font;
    private Sprite textbox;

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
        CurrentBackground.getInstance();
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        //Textbox

        Texture texture = new Texture(Gdx.files.internal("UI/textbox.png"));
        textbox = new Sprite(texture);
    }

    @Override
    public void render() {


        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (keyboard.cont() || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
            script.next();

        batch.begin();
        CurrentBackground.getInstance().getSprite().draw(batch);
        textbox.draw(batch);
        if (CurrentCommand.getInstance().getCom() == Command.SAY)
            font.draw(batch, (CurrentModelActor.getInstance().getActor()), 200, 220);
        font.draw(batch, (CurrentCommand.getInstance().getText().get(0)), 200, 200);
        batch.end();


        // batch.begin();
        // r.sprites.stream().forEach(s -> s.draw(batch));
        // batch.end();

    }

    @Override
    public void update(Observable e, Object arg1) {

        Handler.handle();

    }
}
