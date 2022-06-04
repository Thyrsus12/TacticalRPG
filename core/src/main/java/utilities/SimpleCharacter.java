package utilities;

import com.badlogic.gdx.math.Vector2;
import java.io.Serializable;

public class SimpleCharacter implements Serializable {
    private String type;
    private Vector2 charMapPos;
    private Integer movementCapacity;

    public SimpleCharacter(String type, Vector2 charMapPos, Integer movementCapacity) {
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
