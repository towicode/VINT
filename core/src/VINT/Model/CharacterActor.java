package VINT.Model;

/**
 * Created by Todd on 3/23/2016.
 */

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import memory.CurrentSprites;

public class CharacterActor extends Actor {
    Texture region;



    public CharacterActor (Texture background, String name) {
        setName(name);

        if (background == null){
            System.out.println("A texture was null");
            Pixmap pixmap = new Pixmap( 400, 1280, Pixmap.Format.RGBA8888 );
            pixmap.setColor( 0, 1, 0, 0.75f );
            pixmap.fill();
            region = new Texture( pixmap );
            pixmap.dispose();
        } else {
            region = background;
        }
        this.setX(425);
        this.setY(-600);
        this.setZIndex(0);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        if (region != null)
            batch.draw(region, this.getX(), this.getY() );
    }

    public Texture getRegion() {
        return region;
    }

    public void setRegion(Texture region) {
        this.region = region;
    }
}