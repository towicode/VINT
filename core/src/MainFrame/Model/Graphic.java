package MainFrame.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;

public abstract class Graphic extends Sprite implements Disposable {

  protected int locx;
  protected int locy;
  protected int width;
  protected int height;
  protected String name;
  protected String url;
  protected Texture texture;
  protected int opacity;

  public void fadeIn() {

    // define animation thread
    Thread fadeIn = new Thread() {
      public void run() {

        for (int i = 0; i < 51; i++) {
          int m = i * 5 == 250 ? 255 : i * 5;
          setOpacity(m);
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    // start animation thread
    fadeIn.start();
  }

  public void fadeOut() {

    // define animation thread
    Thread fadeOut = new Thread() {
      public void run() {
        for (int i = 51; i > 0; i--) {
          int m = i * 5 == 5 ? 0 : i * 5;
          setOpacity(m);
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      }
    };

    // start animation thread
    fadeOut.start();
  }

  public int getLocx() {
    return locx;
  }

  public void setLocx(int locx) {
    this.locx = locx;
  }

  public int getLocy() {
    return locy;
  }

  public void setLocy(int locy) {
    this.locy = locy;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Texture getTexture() {
    return texture;
  }

  public void setTexture(Texture texture) {
    this.texture = texture;
  }

  public int getOpacity() {
    return opacity;
  }

  public void setOpacity(int opacity) {
    com.badlogic.gdx.graphics.Color x = this.getColor();
    this.setColor(x.r, x.g, x.b, opacity);
    this.opacity = opacity;
  }

}
