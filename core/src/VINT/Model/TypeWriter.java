package VINT.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import memory.CurrentCommand;
import net.dermetfan.gdx.CharSequenceInterpolator;
import net.dermetfan.gdx.Typewriter;

/**
 * Created by Todd on 3/9/2016.
 */
public class TypeWriter extends Typewriter {

    final float degressPerSecond = 10f;


    public void reset(){
        this.setInterpolator(new CharSequenceInterpolator(30.0F));
    }


    public void draw(float delta, SpriteBatch batch, float[] alphas, Font font){
        CharSequence cur = this.updateAndType(CurrentCommand.getInstance().getText().get(0), delta);

        float last_x = 240;
        float last_y = 167;
        for (int i = 0; i < cur.length(); i++){
            alphas[i] = (alphas[i] + Gdx.graphics.getDeltaTime() *  degressPerSecond) /*% 255*/;
            if (alphas[i] > 1)
                alphas[i] = 1;
            font.setColor(1, 1, 1, alphas[i]);
            char m = cur.charAt(i);
            if (m == ' '){
                float next_word = font.getWidthOfNextWord(i+1,CurrentCommand.getInstance().getText().get(0));
                float total_width = last_x + next_word;
                //TODO Constants
                if (total_width> 1040){
                    last_x = 240;
                    last_y = last_y - font.getLineHeight() * 2;
                    continue;
                }
            }
            String charString = String.valueOf(m);
            font.draw(batch, charString, last_x, last_y);
            last_x = last_x + font.getWidth(charString);
        }
    }
}
