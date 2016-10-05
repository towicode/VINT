package memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentBackground {


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
