package mapTileByTile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Tile {

    public static final int TILE_WIDTH = 32;
    public static int TILE_HEIGHT = 16;

    private boolean accessible;
    private TextureRegion texture;
    private final TextureRegion textureSelected;
    private Vector2 tileMapPos;
    private Vector2 tileWorldPos;

    private Boolean isSelected;

    private Boolean occupied;

    public Tile(Boolean accessible, TextureRegion texture, TextureRegion textureSelected, Vector2 tileMapPos, Vector2 tileWorldPos) {
        this.occupied = false;
        this.isSelected = false;
        this.accessible = accessible;
        this.texture = texture;
        this.textureSelected = textureSelected;
        this.tileMapPos = tileMapPos;
        this.tileWorldPos = tileWorldPos;
    }

    public void render(SpriteBatch batch) {
        if (isSelected)
            batch.draw(textureSelected, tileWorldPos.x, tileWorldPos.y);
        else
            batch.draw(texture, tileWorldPos.x, tileWorldPos.y);
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

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }
}
