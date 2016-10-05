package VINT.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Todd on 8/12/2016.
 */
public  class ActorLoader {

    public static Texture loadTexture(String folder, String x, String file_type){

        try {
            return new Texture(Gdx.files.internal(folder + "/"+ x + "."+file_type));
        } catch (com.badlogic.gdx.utils.GdxRuntimeException e){
            return null;
        }
    }

    public static Texture loadTexture(String folder, String x){
        return loadTexture(folder,x,"png");
    }
}
