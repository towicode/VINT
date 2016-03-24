package MainFrame.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Todd on 3/23/2016.
 */
public class MyActor extends Actor {
    Texture texture;
    TextureRegion region;

    public MyActor () {
        texture = new Texture(Gdx.files.internal("sprites/basic.png"));
        region = new TextureRegion(texture);
        this.setZIndex(15);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, 0, 0 );
    }
}