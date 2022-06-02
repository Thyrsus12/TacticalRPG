package utilities;

import characters.Charact;

import java.io.Serializable;
import java.util.LinkedList;

public class SaveGame implements Serializable {
    LinkedList<AuxCharacter> characters = new LinkedList<>();
    Integer mapSize;
    String mapType;

    public SaveGame(LinkedList<Charact> characters, Integer mapSize, String mapType) {
        this.mapSize = mapSize;
        this.mapType = mapType;

        generateCharacters(characters);
    }

    private void generateCharacters(LinkedList<Charact> characters) {
        for (Charact c: characters) {
            this.characters.add(new AuxCharacter(c.getType(), c.getCharMapPos(), c.getMovementCapacity()));
        }
    }

    public LinkedList<AuxCharacter> getCharacters() {
        return characters;
    }

    public Integer getMapSize() {
        return mapSize;
    }

    public String getMapType() {
        return mapType;
    }
}
