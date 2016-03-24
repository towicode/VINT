package MainFrame.Model;

/**
 * Created by Todd on 3/23/2016.
 */

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BackgroundActor extends Actor {
    TextureAtlas.AtlasRegion region;

    public BackgroundActor (TextureAtlas.AtlasRegion background) {
        region = background;
        this.setZIndex(0);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, 0, 0 );
    }
}