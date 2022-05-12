package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.LinkedList;

public class CharactersOperations {

    private LinkedList<Character> characters;

    private HashMap<String, Integer> characterPosEquivalence;

    public CharactersOperations() {
        characters = new LinkedList<>();
        characters.add(new Character(new Texture("bluehoplite.png"), new Vector2(3, 1)));
        characters.add(new Character(new Texture("redhoplite.png"), new Vector2(5, 1)));

        this.characterPosEquivalence = new HashMap<>();
        fillEquivalences();
    }

    private void fillEquivalences() {
        int cont = 0, mapX, mapY;
        for (Character character : characters) {
            mapX = (int) character.getCharMapPos().x;
            mapY = (int) character.getCharMapPos().y;
            characterPosEquivalence.put(mapX + "" + mapY, cont);
            cont++;
        }
    }

    public LinkedList<Character> getCharacters() {
        return characters;
    }

    public HashMap<String, Integer> getCharacterPosEquivalence() {
        return characterPosEquivalence;
    }
}
