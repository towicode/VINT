package VINT.factories;

import VINT.Model.CharacterActor;
import VINT.game.ActorLoader;
import VINT.game.Vint;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.SnapshotArray;

import java.util.ArrayList;

/**
 * Created by Todd on 8/8/2016.
 */
public class CharacterGetCreateFactory {

    private CharacterActor sprite_image_actor = null;
    private boolean initialized = false;
    private String sprite_char_name;
    private String sprite_image_name;


    public CharacterGetCreateFactory(ArrayList<String> params) {
        if(params.size() <= 0){
            System.out.println("Invalid Params for loading character");
            return;
        }

        sprite_char_name = params.get(0);
        sprite_image_name = params.get(1);
        params.remove(0);
        params.remove(0);
        this.initialized = true;
    }

    /**
     * This method checks to see if the character is already on the stage, if so it sets the sprite_image_actor to
     * the variable and returns true
     * @return
     */
    public boolean characterOnStage(){

        if (!this.initialized){
            System.out.println("Failed to initialize factory before use");
            return false;
        }

        SnapshotArray<Actor> curChildren = Vint.fg.getChildren();
        Actor[] items = curChildren.begin();
        for (int i = 0, n = curChildren.size; i < n; i++) {
            Actor item = items[i];
            if (item.getName().equals(this.sprite_char_name)) {
                sprite_image_actor = (CharacterActor) item;
                return true;
            }
        }
        curChildren.end();
        return false;
    }


    /**
     * Uses the character on stage boolean to decide wether or not it needs to create a new
     * character or just use the existing one.
     * @return
     */
    public CharacterActor getOrCreate(){
        Texture sprite_texture = ActorLoader.loadTexture("sprites", sprite_image_name);


        if (characterOnStage()){
            this.sprite_image_actor.setRegion(sprite_texture);
            return this.sprite_image_actor;
        }

        sprite_image_actor = new CharacterActor(sprite_texture, sprite_char_name);
        sprite_image_actor.setVisible(false);
        Vint.fg.addActor(sprite_image_actor);
        return sprite_image_actor;
    }


    public void dispose(){
        this.sprite_char_name = null;
        this.sprite_image_name = null;
        System.gc();
    }
}
