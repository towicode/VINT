package VINT.stages;

import VINT.game.Vint;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by todd on 9/26/16.
 */
public class MainMenuStage extends VintStage {

    public Skin skin;
    public SpriteBatch batch;

    public Table table;

    public MainMenuStage(Viewport viewport){
        super(viewport);

        Vint.getInputMultiplexer().addProcessor(this);
        table = new Table();
        table.setFillParent(true);
        this.addActor(table);
        table.setDebug(true);
        batch = new SpriteBatch();

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();

        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        skin.add("default", new BitmapFont());

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // Create a table that fills the screen. Everything else will go inside this table.
        table.setFillParent(true);

        VerticalGroup vg = new VerticalGroup();

        table.add(vg);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton start = new TextButton("Start Game", skin);
        vg.addActor(start);

        final TextButton load = new TextButton("Load Game", skin);
        vg.addActor(load);

        final TextButton config = new TextButton("Config", skin);
        vg.addActor(config);

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        start.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + start.isChecked());
                start.setText("Good job!");
                Vint.stage = new GameStage(new ScreenViewport(), "twiday", null);
                dispose();
            }
        });

        load.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + start.isChecked());
                start.setText("Good job!");
                Vint.stage = new GameStage(new ScreenViewport(), null, "rO0ABXNyAA9WSU5ULk1vZGVsLlNhdmU2VWRektAaiAIABUkABmdhbWVJREkACmxpbmVOdW1iZXJKABBzZXJpYWxWZXJzaW9uVUlETAAKU2NyaXB0TmFtZXQAEkxqYXZhL2xhbmcvU3RyaW5nO0wABGRhdGV0ABBMamF2YS91dGlsL0RhdGU7eHAAAAABAAAAUgAAAAAAAAABdAAGdHdpZGF5c3IADmphdmEudXRpbC5EYXRlaGqBAUtZdBkDAAB4cHcIAAABV2jlsYB4");
                dispose();
            }
        });


        // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
        //table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);

    }

    public void resize (int width, int height) {
        this.getViewport().update(width, height, true);
    }

    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.act(Gdx.graphics.getDeltaTime());
        this.draw();
    }

    public void dispose() {
        Vint.getInputMultiplexer().removeProcessor(this);
        super.dispose();
    }

}
