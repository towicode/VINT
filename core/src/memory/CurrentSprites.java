package memory;

import VINT.Model.CharacterActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentSprites {


    private static CurrentSprites instance = null;
    public String sprite_list = "";
    private TextureAtlas spriteAtlas;


    private CurrentSprites() {
        this.spriteAtlas = new TextureAtlas(Gdx.files.internal("sprites/pack.atlas"));
        spriteAtlas.getRegions().forEach(s -> sprite_list += " " + s + " ");

    }

    public static CurrentSprites getInstance() {
        if (instance == null) {
            instance = new CurrentSprites();
        }
        return instance;
    }

    public TextureAtlas getSpriteAtlas() {
        return spriteAtlas;
    }

}
