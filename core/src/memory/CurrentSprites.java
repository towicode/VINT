package memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentSprites {


    private static CurrentSprites instance = null;
    private String name;
    private TextureAtlas backgroundAtlas;
    private TextureAtlas.AtlasRegion region;
    private ArrayList<Sprite> sprites;

    private CurrentSprites() {
        this.backgroundAtlas = new TextureAtlas(Gdx.files.internal("sprites/pack.atlas"));
        this.region = backgroundAtlas.findRegion("missingno");
    }

    public static CurrentSprites getInstance() {
        if (instance == null) {
            instance = new CurrentSprites();
        }
        return instance;
    }

    public boolean HasActiveSprite() {

        return false;

    }

    public String getName() {
        return name;
    }

    public void setName(String[] name) {

        if (name == null) {

        } else {
//            this.name = name;
//            region = backgroundAtlas.findRegion(name);
        }
        //this.sprite = new Sprite(this.region);
    }

    public Sprite getSprite() {
        //return sprite;
        return null;
    }
}
