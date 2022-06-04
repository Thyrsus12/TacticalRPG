package utilities;

import characters.Character;

import java.io.Serializable;
import java.util.LinkedList;

public class SaveGame implements Serializable {
    LinkedList<SimpleCharacter> simpleCharacters = new LinkedList<>();
    Integer mapSize;
    String mapType;

    public SaveGame(LinkedList<Character> characters, Integer mapSize, String mapType) {
        generateSimpleCharacters(characters);
        this.mapSize = mapSize;
        this.mapType = mapType;
    }

    private void generateSimpleCharacters(LinkedList<Character> characters) {
        for (Character c: characters) {
            this.simpleCharacters.add(new SimpleCharacter(c.getType(), c.getCharMapPos(), c.getMovementCapacity()));
        }
    }

    public LinkedList<SimpleCharacter> getSimpleCharacters() {
        return simpleCharacters;
    }
    public Integer getMapSize() {
        return mapSize;
    }
    public String getMapType() {
        return mapType;
    }
}
