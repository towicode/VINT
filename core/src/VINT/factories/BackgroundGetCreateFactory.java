package VINT.factories;

import VINT.Model.BackgroundActor;
import VINT.game.ActorLoader;
import VINT.game.Vint;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Todd on 8/12/2016.
 */
public class BackgroundGetCreateFactory {

    private String background_image_name;
    private BackgroundActor background_image_actor;

    public BackgroundGetCreateFactory(ArrayList<String> params){
        if(params.size() <= 0){
            System.out.println("Invalid Params for loading background");
            return;
        }

        this.background_image_name = params.get(0);
        params.remove(0);
    }


    public BackgroundActor getOrCreate(){
        Texture background_texture = ActorLoader.loadTexture("backgrounds", background_image_name);
        background_image_actor = new BackgroundActor(background_texture);
        background_image_actor.setVisible(false);
        Vint.bg.addActor(background_image_actor);
        return background_image_actor;
    }

    public void dispose() {
        this.background_image_actor = null;
        System.gc();
    }
}
