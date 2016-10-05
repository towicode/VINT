package memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    // libgdx logo comes with libgdx
    public AssetImages images;


    // singleton: prevent instantiation from other classes
    private Assets() {
    }

    public class AssetImages {
        public final Texture logo;

        public AssetImages() {
            logo = new Texture(Gdx.files.internal("data/libgdx.png"));
        }
    }


    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        // set asset manager error handler
        assetManager.setErrorListener(this);

        // load texture
        assetManager.load("data/libgdx.png", Texture.class);
        // load sounds
        assetManager.load("data/Jump.wav", Sound.class);
        // load music
        assetManager.load("data/angry_robot_3.mp3", Music.class);

        // start loading assets and wait until finished
        assetManager.finishLoading();

        Gdx.app.debug(TAG,
                "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, "asset: " + a);
        }

        // create game resource objects
        images = new AssetImages();

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset + "'",
                (Exception) throwable);

    }

    @Override
    public void dispose() {
        System.out.println("assets disposing...");
        assetManager.dispose();
    }

}