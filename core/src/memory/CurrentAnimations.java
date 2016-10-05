package memory;

import VINT.Model.Actions;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * Created by Todd on 8/12/2016.
 */
public class CurrentAnimations {
    private static CurrentAnimations ourInstance = new CurrentAnimations();

    public static CurrentAnimations getInstance() {
        return ourInstance;
    }

    private String animation_list = "";

    private CurrentAnimations() {

        Class c = Actions.class;
        for (Method method : c.getDeclaredMethods()) {
            animation_list += " " + method.getName() + " ";
        }
    }

    public String getList(){
        return animation_list;
    }
}
