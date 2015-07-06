package MainFrame.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Background extends Graphic {

  public Background(Texture yourTexture) {
    super();
  }

  public Background(String auth, Integer valueOf, Integer valueOf2) {
    this(new Texture(auth));
  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

}
