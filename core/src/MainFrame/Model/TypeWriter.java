package MainFrame.Model;

import net.dermetfan.gdx.CharSequenceInterpolator;
import net.dermetfan.gdx.Typewriter;

/**
 * Created by Todd on 3/9/2016.
 */
public class TypeWriter extends Typewriter {


    public void reset(){
        this.setInterpolator(new CharSequenceInterpolator(30.0F));
    }
}
