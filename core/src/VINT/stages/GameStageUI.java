package VINT.stages;

import VINT.game.Vint;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by todd on 9/26/16.
 */
public class GameStageUI extends VintStage {
    public Skin skin;
    public SpriteBatch batch;

    public Table table;

    public GameStageUI (Viewport viewport){
        super(viewport);
        InputProcessor baby = this;
        Vint.getInputMultiplexer().addProcessor(baby);
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

        HorizontalGroup vg = new HorizontalGroup();
        vg.space(15);
        vg.bottom();
        table.right().bottom();
        table.add(vg);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton start = new TextButton("Actual Q Save", skin);
        vg.addActor(start);

        final TextButton load = new TextButton("Q Save", skin);
        vg.addActor(load);

        final TextButton config = new TextButton("Nothing", skin);
        vg.addActor(config);

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        start.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: " + start.isChecked());
                Vint.getCurrentScript().quickSave();
            }
        });

        // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
        //table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);

    }

    public void resize (int width, int height) {
        this.getViewport().update(width, height, true);
    }

    public void render () {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.act(Gdx.graphics.getDeltaTime());
        this.draw();
    }

    public void dispose() {
        Vint.getInputMultiplexer().removeProcessor(this);
        super.dispose();
    }

}
