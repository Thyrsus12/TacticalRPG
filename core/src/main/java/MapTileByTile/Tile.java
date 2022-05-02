package MapTileByTile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Tile {

    private TextureRegion t;
    private TextureRegion tS;
    private Vector2 tileMapPos;
    private Vector2 tileWorldPos;
    Boolean isSelected;

    public Tile(Boolean isSelected, TextureRegion t, TextureRegion tS, Vector2 tileMapPos, Vector2 tileWorldPos) {
        this.isSelected = isSelected;
        this.t = t;
        this.tS = tS;
        this.tileMapPos = tileMapPos;
        this.tileWorldPos = tileWorldPos;

    }

    public void render(SpriteBatch batch) {
        if (isSelected)
            batch.draw(tS, tileWorldPos.x, tileWorldPos.y);
        else
            batch.draw(t, tileWorldPos.x, tileWorldPos.y);
    }

    public Vector2 getTileMapPos() {
        return tileMapPos;
    }

    public Vector2 getTileWorldPos() {
        return tileWorldPos;
    }
}
