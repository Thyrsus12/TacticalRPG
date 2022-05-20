package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import mapTileByTile.Tile;

import java.util.HashMap;
import java.util.LinkedList;

public class CharactersOperations {

    private LinkedList<Character> characters;

    private HashMap<String, Integer> characterPosEquivalence;

    public CharactersOperations() {
        characters = new LinkedList<>();
        characters.add(new Character(new Texture("blueHoplite.png"), new Vector2(3, 1), 2));
        characters.add(new Character(new Texture("redHoplite.png"), new Vector2(5, 1), 2));
        characters.add(new Character(new Texture("blueMagician.png"), new Vector2(4, 1), 2));
        characters.add(new Character(new Texture("redMagician.png"), new Vector2(5, 4), 2));
        characters.add(new Character(new Texture("blueArcher.png"), new Vector2(2, 4), 2));
        characters.add(new Character(new Texture("redArcher.png"), new Vector2(1, 4), 2));

        this.characterPosEquivalence = new HashMap<>();
        fillEquivalences();
    }

    private void fillEquivalences() {
        int cont = 0, mapX, mapY;
        for (Character character : characters) {
            mapX = (int) character.getCharMapPos().x;
            mapY = (int) character.getCharMapPos().y;
            characterPosEquivalence.put(mapX + "," + mapY, cont);
            cont++;
        }
    }

    public void moveCharacter(int mapX, int mapY, Character c) {
        c.setCharMapPos(new Vector2(mapX, mapY));
        float x = (mapX - mapY) * Tile.TILE_WIDTH / 2.0001f;
        float y = (mapY + mapX) * Tile.TILE_HEIGHT / 2f;
        c.setCharWorldPos(new Vector2(x, y));
    }

    public void updateHashMap(String charPos, String targetPos, int charArrayPos) {
        characterPosEquivalence.remove(charPos);
        characterPosEquivalence.put(targetPos, charArrayPos);
    }

    public LinkedList<Character> getCharacters() {
        return characters;
    }

    public HashMap<String, Integer> getCharacterPosEquivalence() {
        return characterPosEquivalence;
    }
}
