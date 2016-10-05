package memory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import text.Name;

import java.util.Arrays;

/**
 * Created by Todd on 2/26/2016.
 */
public class CurrentModelActor {


    private static CurrentModelActor instance = null;
    private String actor;
    private String actor_name_list;
    private Color color;

    private CurrentModelActor() {

    }

    public static CurrentModelActor getInstance() {
        if (instance == null) {
            instance = new CurrentModelActor();
            StringBuilder m = new StringBuilder();
            m.append(' ');
            Arrays.stream(Name.values()).forEach(s -> m.append(s.toString()).append(" "));
            instance.actor_name_list = m.toString();
            System.out.println(m.toString());
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

    public String getActor_name_list() {
        return actor_name_list;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
