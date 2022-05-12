package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AnimationSprite {
    static TextureAtlas atlas;
    static Animation<Sprite> walk;

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("bluehoplite.atlas"));
        walk = new Animation<Sprite>(
                5,
                atlas.createSprite("Sprite-0001 0."),
                atlas.createSprite("Sprite-0001 1."),
                atlas.createSprite("Sprite-0001 2.")
        );
    }

    public static void dispone() {
        atlas.dispose();
    }
}
