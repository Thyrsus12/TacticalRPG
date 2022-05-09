package mapTileByTile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Tile {

    public static final int TILE_WIDTH = 32;
    public static int TILE_HEIGHT = 16;



    private boolean accessible;
    private TextureRegion t;
    private TextureRegion tS;
    private Vector2 tileMapPos;
    private Vector2 tileWorldPos;

    private Boolean isSelected;

    public Tile(Boolean accessible, TextureRegion t, TextureRegion tS, Vector2 tileMapPos, Vector2 tileWorldPos) {
        this.isSelected = false;
        this.accessible = accessible;
        this.t = t;
        this.tS = tS;
        this.tileMapPos = tileMapPos;
        this.tileWorldPos = tileWorldPos;
    }

    public Tile(Boolean isSelected, Boolean accessible, TextureRegion t, TextureRegion tS, Vector2 tileMapPos, Vector2 tileWorldPos) {
        this.isSelected = isSelected;
        this.accessible = accessible;
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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Vector2 getTileMapPos() {
        return tileMapPos;
    }

    public Vector2 getTileWorldPos() {
        return tileWorldPos;
    }

    public void setT(TextureRegion t) {
        this.t = t;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }
}
