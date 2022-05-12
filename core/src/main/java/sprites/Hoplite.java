package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import mapTileByTile.Tile;


public class Hoplite {


    private Animation animation;
    private float time;
    private TextureRegion[] regionMovement;
    private Texture image;
    private TextureRegion frameActual;
    private Vector2 charMapPos;
    private Vector2 charWorldPos;
    private Integer movementCapacity;

    public Hoplite() {
        this.charMapPos = new Vector2(4, 3);
        this.charWorldPos = calculateWorldPos(charMapPos);
        this.movementCapacity = 2;
        image = new Texture("bluehoplite.png");
        TextureRegion[][] tmp = TextureRegion.split(image, image.getWidth() / 3, image.getHeight());
        regionMovement = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            regionMovement[i] = tmp[0][i];
        }

        animation = new Animation(0.5f, regionMovement);
        time = 0f;
    }

    public void render(final SpriteBatch batch) {
        time += Gdx.graphics.getDeltaTime(); // Es el tiempo que paso desde el ultimo render
        frameActual = (TextureRegion) animation.getKeyFrame(time, true);
        batch.draw(frameActual, charWorldPos.x, charWorldPos.y);
    }

    private Vector2 calculateWorldPos(Vector2 charMapPos) {
        float x = (charMapPos.x - charMapPos.y) * Tile.TILE_WIDTH / 2.0001f;
        float y = (charMapPos.y + charMapPos.x) * Tile.TILE_HEIGHT / 2f;
        return new Vector2(x, y);
    }

    public Vector2 getCharMapPos() {
        return charMapPos;
    }

    public Integer getMovementCapacity() {
        return movementCapacity;
    }

    public void setMovementCapacity(Integer movementCapacity) {
        this.movementCapacity = movementCapacity;
    }

    public void setCharMapPos(Vector2 charMapPos) {
        this.charMapPos = charMapPos;
    }

    public void setCharWorldPos(Vector2 charWorldPos) {
        this.charWorldPos = charWorldPos;
    }

}
