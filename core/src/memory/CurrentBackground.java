package memory;

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
    private TextureAtlas backgroundAtlas;
    private TextureAtlas.AtlasRegion region;
    private Sprite sprite;




    private static CurrentBackground instance = null;

    private CurrentBackground(){
        this.backgroundAtlas = new TextureAtlas(Gdx.files.internal("backgrounds/pack.atlas"));
        this.region = backgroundAtlas.findRegion("evansapartment_day_spring");
        this.sprite = new Sprite(this.region);

    }

    public static CurrentBackground getInstance() {
        if (instance == null) {
            instance = new CurrentBackground();
        }
        return instance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name == null){
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.BLACK);
            pixmap.fill();

            region.setTexture(new Texture(pixmap));
            pixmap.dispose();

        }
        else {
            this.name = name;
            region = backgroundAtlas.findRegion(name);
        }
        this.sprite = new Sprite(this.region);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
