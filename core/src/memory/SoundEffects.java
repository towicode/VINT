package memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import VINT.Model.BackgroundActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.Arrays;

/**
 * Created by Todd on 2/26/2016.
 */
public class SoundEffects {

    public String sound_effect_list = "";

    private static SoundEffects instance = null;

    private SoundEffects(){
        Arrays.stream(Gdx.files.internal("sounds").list()).forEach(s -> sound_effect_list+= " " + s.nameWithoutExtension() + " ");
    }

    public static SoundEffects getInstance() {
        if (instance == null) {
            instance = new SoundEffects();
        }
        return instance;
    }

    public Sound loadSound (String filename) {
        return Gdx.audio.newSound(Gdx.files.internal("sounds/" + filename));
    }


}
