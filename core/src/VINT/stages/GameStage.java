package VINT.stages;

import VINT.Model.Font;
import VINT.Model.TypeWriter;
import VINT.game.Handler;
import VINT.game.Vint;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import input.Keyboard;
import memory.CurrentCommand;
import memory.CurrentModelActor;
import text.Command;
import text.Expando;
import text.Spreader;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by todd on 9/26/16.
 */
public class GameStage extends VintStage implements Observer{

    private Expando script;
    private Keyboard keyboard;
    private SpriteBatch batch;
    private Font font;
    private BitmapFont font38;
    private Sprite textbox;
    private TypeWriter typewriter = new TypeWriter();
    public static float[] alphas;
    public GameStageUI gameUI;



    public Expando getScript() {
        return script;
    }

    public GameStage(Viewport viewport, String name, String save){
        super(viewport);

        this.addActor(Vint.bg);
        this.addActor(Vint.fg);

        gameUI = new GameStageUI(new ScreenViewport());

        CurrentCommand.getInstance().addObserver(this);
        keyboard = new Keyboard();

        Vint.getInputMultiplexer().addProcessor(keyboard);
        //Gdx.input.setInputProcessor(keyboard);

        Spreader loader;
        if (save != null){
            loader = new Spreader(save, 1);
        } else {
            loader = new Spreader(name);
        }

        script = loader.CreateExpando();

        //TODO investigate why this exists.
        script.currentCard.send();
        script.next();

        batch = new SpriteBatch();
        font = new Font();
        font38 = new BitmapFont();
        font38.setColor(.35f,.87f,.56f,1f);

        // Textbox
        //Texture textur2e = Assets.instance.images.logo;

        Texture texture = new Texture(Gdx.files.internal("UI/textbox.png"));
        textbox = new Sprite(texture);

        typewriter.getInterpolator().setInterpolation(Interpolation.linear);

        // TODO set some custom cursors
        typewriter.getAppender().set(new CharSequence[]{""}, 1.5f / 4f);

    }


    public void render(){
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (keyboard.cont() || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){

            if (alphas[alphas.length -1] >= 1)
                script.next();
            else {
                for (int i = 0; i < alphas.length;i++){
                    alphas[i] = 1;
                }
                typewriter.setTime(44);
            }
        }


        this.act(delta);
        this.draw();

        batch.begin();
        typewriter.draw(delta,batch, alphas, font);
        String cur_model_actor = CurrentModelActor.getInstance().getActor();
        if (cur_model_actor != null && CurrentCommand.getInstance().getCom().equals(Command.SAY)) {
            Color a = CurrentModelActor.getInstance().getColor();
            font38.setColor(a);
            font38.draw(batch, (CurrentModelActor.getInstance().getActor()), 240, 228);
        }
        batch.end();

        gameUI.render();
    }

    @Override
    public void update(Observable e, Object arg1) {

        Handler.handle();
        typewriter.reset(); //Todo in text handler
        if (CurrentCommand.getInstance().getCom() == Command.SAY ||
                CurrentCommand.getInstance().getCom() == Command.GENERIC_SAY) {
                    alphas = new float[(CurrentCommand.getInstance().getText().get(0).length())];
        }
    }
}
