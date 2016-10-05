package VINT.stages;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by todd on 9/26/16.
 */
public class VintStage extends Stage implements Renderable{





    public VintStage(){
        super();
    }
    public VintStage(Viewport viewport){
        super(viewport);
    }

    public VintStage(Viewport viewport, Batch batch){
        super(viewport,batch);
    }



    @Override
    public void render() {
        System.out.println("YOU MUST IMPLEMENT RENDER IN YOUR STAGE");
    }
}
