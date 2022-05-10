package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class BlueHopliteVisuals {
    static Animation<Sprite> idle;
    static TextureAtlas blueHopliteAtlas;

    public static void load(){
        blueHopliteAtlas = new TextureAtlas(Gdx.files.internal("/home/maed2/IdeaProjects/TacticalRPG/assets/bluehoplite.atlas"));
        idle = new Animation<Sprite>(
                5
        );
    }

    public static void dispose(){
        blueHopliteAtlas.dispose();
    }
}
