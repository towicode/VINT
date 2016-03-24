package MainFrame.game;

import MainFrame.Model.BackgroundActor;
import MainFrame.Model.MyActor;
import MainFrame.Model.TypeWriter;
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
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
    final float degressPerSecond = 10f;
    private Keyboard keyboard;
    private SpriteBatch batch;
    private BitmapFont font;
    private Sprite textbox;
    private TypeWriter typewriter = new TypeWriter();
    private float rot;
    float[] alphas;

    public static Expando getScript() {
        return script;
    }

    public static void setScript(Expando script) {
        MainFrame.script = script;
    }

    private static Stage stage;
    static Group bg = new Group();
    static Group fg = new Group();



    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());

        stage.addActor(bg);
        stage.addActor(fg);

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

        // Textbox
        Texture texture = new Texture(Gdx.files.internal("UI/textbox.png"));
        textbox = new Sprite(texture);


        typewriter.getInterpolator().setInterpolation(Interpolation.linear);

        // set some custom cursors
        typewriter.getAppender().set(new CharSequence[]{""}, 1.5f / 4f);



    }

    @Override
    public void render() {


        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (keyboard.cont() || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
            script.next();

        stage.act(delta);
        stage.draw();

        batch.begin();
        //CurrentBackground.getInstance().getSprite().draw(batch);
        textbox.draw(batch);


        //TYPE WRITER TESTING:::

        CharSequence cur = typewriter.updateAndType(CurrentCommand.getInstance().getText().get(0), delta);

        float last_x = 200;
        for (int i = 0; i < cur.length(); i++){
            alphas[i] = (alphas[i] + Gdx.graphics.getDeltaTime() *  degressPerSecond) /*% 255*/;
            if (alphas[i] > 1)
                alphas[i] = 1;
            font.setColor(1, 1, 1, alphas[i]);
            String charString = String.valueOf(cur.charAt(i));
            font.draw(batch, charString, last_x, 200);
            last_x = last_x + font.getBounds(charString).width;
        }

        font.setColor(1, 1, 1, 1);
        font.draw(batch, (CurrentModelActor.getInstance().getActor()), 200, 220);
        batch.end();

    }

    @Override
    public void update(Observable e, Object arg1) {

        Handler.handle();
        typewriter.reset(); //Todo in text handler
        if (CurrentCommand.getInstance().getCom() == Command.SAY) {
            alphas = new float[(CurrentCommand.getInstance().getText().get(0).length())];
        }
    }


    public static void addActor(Actor b,boolean background){
        if (background)
            bg.addActor(b);
        else
            fg.addActor(b);
    }
}
