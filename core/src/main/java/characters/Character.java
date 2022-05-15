package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import mapTileByTile.Tile;

import java.io.FileReader;

public class Character {
    private Texture t;
    private TextureRegion[] regionMovement;
    private Animation animation;
    private float time = 0f;
    private TextureRegion currentFrame;


    private Vector2 charMapPos;
    private Vector2 charWorldPos;

    private Integer movementCapacity;

    public Character(Texture t, Vector2 charMapPos) {
        this.t = t;
        this.charMapPos = charMapPos;
        this.charWorldPos = calculateWorldPos(charMapPos);
        this.movementCapacity = 2;

        makeAnimation(t);
    }

    private Vector2 calculateWorldPos(Vector2 charMapPos) {
        float x = (charMapPos.x - charMapPos.y) * Tile.TILE_WIDTH / 2.0001f;
        float y = (charMapPos.y + charMapPos.x) * Tile.TILE_HEIGHT / 2f;
        return new Vector2(x, y);
    }

    private void makeAnimation(Texture t) {
        int frames = t.getWidth() / 32;
        TextureRegion[][] tmp = TextureRegion.split(t, t.getWidth() / frames, t.getHeight());
        regionMovement = new TextureRegion[frames];
        for (int i = 0; i < frames; i++) {
            regionMovement[i] = tmp[0][i];
        }

        animation = new Animation(0.5f, regionMovement);
    }

    public void render(SpriteBatch batch) {
        time += Gdx.graphics.getDeltaTime(); // Es el tiempo que paso desde el ultimo render
        currentFrame = (TextureRegion) animation.getKeyFrame(time, true);
        batch.draw(currentFrame, charWorldPos.x, charWorldPos.y);
    }

    public Vector2 getCharMapPos() {
        return charMapPos;
    }

    public Integer getMovementCapacity() {
        return movementCapacity;
    }

    public void setCharMapPos(Vector2 charMapPos) {
        this.charMapPos = charMapPos;
    }

    public void setCharWorldPos(Vector2 charWorldPos) {
        this.charWorldPos = charWorldPos;
    }
}
