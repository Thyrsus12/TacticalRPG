package MapTileByTile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile {

    private Texture t;
    private Vector2 tileMapPos;
    private Vector2 tileWorldPos;

    public Tile(Texture t, Vector2 tileMapPos, Vector2 tileWorldPos) {
        this.t = t;
        this.tileMapPos = tileMapPos;
        this.tileWorldPos = tileWorldPos;
    }

    public void render(SpriteBatch batch) {
        batch.draw(t, tileWorldPos.x, tileWorldPos.y);
    }
}
