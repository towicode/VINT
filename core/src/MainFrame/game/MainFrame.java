package MainFrame.game;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import input.Keyboard;
import text.Command;
import text.Expando;
import text.Spreader;
import memory.CurrentCommand;
import MainFrame.Model.ScriptReader;
import MainFrame.Model.console;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainFrame extends ApplicationAdapter implements Observer {

  public static AssetManager manager = new AssetManager();
    private Keyboard keyboard;
    Expando script;
    private SpriteBatch batch;
    private BitmapFont font;

  @Override
  public void create() {

    CurrentCommand.getInstance().addObserver(this);
      keyboard = new Keyboard();
      Gdx.input.setInputProcessor(keyboard);

    Spreader loader = new Spreader(null);


    try {
      script = loader.CreateExpando();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

      batch = new SpriteBatch();
      font = new BitmapFont();
      font.setColor(Color.BLACK);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



      if(keyboard.cont() || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
          script.next();

      batch.begin();
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
