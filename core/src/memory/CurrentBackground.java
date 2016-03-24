package memory;

import MainFrame.Model.BackgroundActor;
import MainFrame.game.MainFrame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentBackground {


    private String name;

    public TextureAtlas getBackgroundAtlas() {
        return backgroundAtlas;
    }

    private TextureAtlas backgroundAtlas;

    public String background_list = "";

    private static CurrentBackground instance = null;

    private CurrentBackground(){
        this.backgroundAtlas = new TextureAtlas(Gdx.files.internal("backgrounds/pack.atlas"));
        backgroundAtlas.getRegions().forEach(s -> background_list += " " + s + " ");
    }

    public static CurrentBackground getInstance() {
        if (instance == null) {
            instance = new CurrentBackground();
        }
        return instance;
    }

}
