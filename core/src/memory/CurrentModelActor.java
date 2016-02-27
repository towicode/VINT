package memory;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentModelActor {


    private static CurrentModelActor instance = null;
    private String actor;

    private CurrentModelActor() {

    }

    public static CurrentModelActor getInstance() {
        if (instance == null) {
            instance = new CurrentModelActor();
        }
        return instance;
    }


    public void setName(String name) {

        if (name == null) {
            actor = "";
        } else {
            this.actor = name;
        }

    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
