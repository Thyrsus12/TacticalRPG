package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import mapTileByTile.Tile;
import utilities.AuxCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CharactersOperations {

    public static LinkedList<Charact> characts;

    private HashMap<String, Integer> characterPosEquivalence;

    public CharactersOperations(ArrayList<Integer> numCharacters, ArrayList<Integer> numCharacters2, int mapSize) {
        characts = new LinkedList<>();
        fillCharacters(numCharacters, "blue", mapSize);
        fillCharacters(numCharacters2, "red", mapSize);
        this.characterPosEquivalence = new HashMap<>();
        fillEquivalences();
    }

    public CharactersOperations(LinkedList<AuxCharacter> cs) {
        characts = new LinkedList<>();
        parseCharacters(cs);
        this.characterPosEquivalence = new HashMap<>();
        fillEquivalences();
    }

    private void parseCharacters(LinkedList<AuxCharacter> cs) {
        for (AuxCharacter c : cs) {
            this.characts.add(new Charact(c.getType(), c.getCharMapPos(), c.getMovementCapacity()));
        }
    }

    private void fillCharacters(ArrayList<Integer> numCharacters, String team, int mapSize) {
        int cont = 0;
        int spawnPosX = 1;
        int spawnPosY = 1;
        switch (team) {
            case "blue":
                spawnPosX = 1;
                break;
            case "red":
                spawnPosX = mapSize;
                break;
        }
        for (int i : numCharacters) {
            for (int j = 0; j < i; j++) {
                switch (cont) {
                    case 0:
                        characts.add(new Charact("characters/" + team + "Magician.png",  new Vector2(spawnPosX, spawnPosY), 2));
                        spawnPosY += 2;
                        break;
                    case 1:
                        characts.add(new Charact("characters/" + team + "Archer.png",  new Vector2(spawnPosX, spawnPosY), 2));
                        spawnPosY += 2;
                        break;
                    case 2:
                        characts.add(new Charact("characters/" + team + "Hoplite.png", new Vector2(spawnPosX, spawnPosY), 2));
                        spawnPosY += 2;
                        break;
                    case 3:
                        characts.add(new Charact("characters/medusa.png", new Vector2(spawnPosX, spawnPosY), 2));
                        spawnPosY += 2;
                        break;
                    case 4:
                        characts.add(new Charact("characters/chimera.png", new Vector2(spawnPosX, spawnPosY), 2));
                        spawnPosY += 2;
                        break;
                    case 5:
                        characts.add(new Charact("characters/hydra.png", new Vector2(spawnPosX, spawnPosY), 2));
                        spawnPosY += 2;
                        break;
                }

                if (spawnPosY >= mapSize) {
                    spawnPosY = 1;
                    if (team.equals("blue"))
                        spawnPosX++;
                    else
                        spawnPosX--;
                }
            }
            cont++;
        }
    }

    private void fillEquivalences() {
        int cont = 0, mapX, mapY;
        for (Charact charact : characts) {
            mapX = (int) charact.getCharMapPos().x;
            mapY = (int) charact.getCharMapPos().y;
            characterPosEquivalence.put(mapX + "," + mapY, cont);
            cont++;
        }
    }

    public void moveCharacter(int mapX, int mapY, Charact c) {
        c.setCharMapPos(new Vector2(mapX, mapY));
        float x = (mapX - mapY) * Tile.TILE_WIDTH / 2.0001f;
        float y = (mapY + mapX) * Tile.TILE_HEIGHT / 2f;
        c.setCharWorldPos(new Vector2(x, y));
    }

    public void updateHashMap(String charPos, String targetPos, int charArrayPos) {
        characterPosEquivalence.remove(charPos);
        characterPosEquivalence.put(targetPos, charArrayPos);
    }

    public LinkedList<Charact> getCharacters() {
        return characts;
    }

    public HashMap<String, Integer> getCharacterPosEquivalence() {
        return characterPosEquivalence;
    }
}
