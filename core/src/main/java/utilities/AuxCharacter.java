package utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public class AuxCharacter implements Serializable {
    private String type;

    private float time = 0f;

    private Vector2 charMapPos;

    private Integer movementCapacity;

    public AuxCharacter(String type, Vector2 charMapPos, Integer movementCapacity) {
        this.type = type;
        this.charMapPos = charMapPos;
        this.movementCapacity = movementCapacity;
    }

    public String getType() {
        return type;
    }

    public Vector2 getCharMapPos() {
        return charMapPos;
    }

    public Integer getMovementCapacity() {
        return movementCapacity;
    }
}
