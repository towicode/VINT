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

public class BackgroundActor extends Actor {
    Texture region;

    public BackgroundActor (Texture background) {
        region = background;
        if (background == null){
            System.out.println("A texture was null");
            //TODO background size
            Pixmap pixmap = new Pixmap( 1280, 720, Pixmap.Format.RGBA8888 );
            pixmap.setColor( 0, 1, 0, 0.75f );
            pixmap.fill();
            region = new Texture( pixmap );
            pixmap.dispose();
        } else {
            region = background;
        }
        this.setZIndex(0);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, 0, 0 );
    }
}