package utilities;

import characters.Character;
import characters.CharactersOperations;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import mapTileByTile.Tile;
import mapTileByTile.TileMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TilesOperations {
    private TileMap map;

    private Boolean characterSelected;
    private ArrayList<Integer> possibleTilesToMove;
    private ArrayList<TextureRegion> beforeTheBlueTiles;

    private int oldPosition;
    private Tile oldTile;

    private CharactersOperations charactersOps;
    private LinkedList<Character> characters;
    private HashMap<String, Integer> equivalences;

    private Character c;
    private String startCharPos;
    private int charArrayIndex;

    public TilesOperations(TileMap map, CharactersOperations charactersOps) {
        this.map = map;
        this.characterSelected = false;
        this.possibleTilesToMove = new ArrayList<>();
        this.beforeTheBlueTiles = new ArrayList<>();
        this.oldTile = null;

        this.charactersOps = charactersOps;
        this.characters = charactersOps.getCharacters();
        this.equivalences = charactersOps.getCharacterPosEquivalence();
        setInaccessibleInitialTiles();
    }

    /**
     * Only at startup does the program traverse the character array and set its tiles as inaccessible.
     */
    private void setInaccessibleInitialTiles() {
        int mapX, mapY;
        // Get the character pos
        for (Character c : characters) {
            //Transform it to tile pos
            mapX = (int) c.getCharMapPos().x - 1;
            mapY = (int) c.getCharMapPos().y - 1;
            //Search and modify the tile
            int tileArrayPos = TileMap.cordsToIndexEquivalence.get(mapX + "," + mapY);
            LinkedList<Tile> tileLinkedList = map.getTileLinkedList();
            tileLinkedList.get(tileArrayPos).setOccupied(true);
        }
    }

    /**
     * Modify the tile of the tileLinkedList list and insert it again to select or unselect it
     */
    public void modifyTile(int mapX, int mapY) {
        int cont = 0;
        LinkedList<Tile> tileLinkedList = map.getTileLinkedList();

        for (Tile t : tileLinkedList) {
            //Check if is the clicked tile
            if (t.getTileMapPos().x == mapX && t.getTileMapPos().y == mapY) {

                String stringPos = (mapX + 1) + "," + (mapY + 1);

                //Check if tile clicked contains a character
                if (equivalences.containsKey(stringPos) && !characterSelected) {
                    c = findChar(stringPos);
                    movementPossibilitiesPainter(mapX, mapY, c.getMovementCapacity(), tileLinkedList);

                    characterSelected = true;

                } else if (equivalences.containsKey(stringPos) && characterSelected) {
                    turnBlueBack(tileLinkedList);

                    c = findChar(stringPos);
                    movementPossibilitiesPainter(mapX, mapY, c.getMovementCapacity(), tileLinkedList);

                } else if (!equivalences.containsKey(stringPos) && characterSelected) {
                    if (possibleTilesToMove.contains(TileMap.cordsToIndexEquivalence.get(mapX + "," + mapY))) {
                        //Move the character and make its tile inaccessible
                        charactersOps.moveCharacter(mapX += 1, mapY += 1, c);
                        t.setOccupied(true);

                        //Update characters HasMap
                        String targetPos = mapX + "," + mapY;
                        charactersOps.updateHashMap(startCharPos, targetPos, charArrayIndex);

                        //Make the previous occupied tile accessible
                        oldTile.setOccupied(false);
                    }

                    //Eliminate the blue tiles because you have unchecked the character's tile.
                    turnBlueBack(tileLinkedList);
                    characterSelected = false;
                }
                newClickOps(tileLinkedList, t, cont);
            }
            cont++;
        }
    }

    private Character findChar(String stringPos) {
        startCharPos = stringPos;
        charArrayIndex = equivalences.get(startCharPos);
        return characters.get(charArrayIndex);
    }

    /**
     * Calculates the accessible tiles taking into account the character's movement and the accessibility of the tiles
     */
    public void movementPossibilitiesPainter(Integer mapX, Integer mapY, Integer movementCapacity, LinkedList<Tile> tileLinkedList) {
        int cont = 0, auxX, auxY;
        possibleTilesToMove = new ArrayList<>();
        beforeTheBlueTiles = new ArrayList<>();

        Tile tile;
        int tileIndex;
        /**Insert the main cross (vertical and horizontal axis)*/
        for (int i = 1; i <= movementCapacity; i++) {
            //Right
            auxX = mapX + i;
            if (cordValidator(auxX, mapY, 0, TileMap.world_map_size)) {
                tileIndex = TileMap.cordsToIndexEquivalence.get(auxX + "," + mapY);
                tile = tileLinkedList.get(tileIndex);
                if (tile.isAccessible() && !tile.getOccupied())
                    possibleTilesToMove.add(tileIndex);
            }
            //Left
            auxX = mapX - i;
            if (cordValidator(auxX, mapY, 0, TileMap.world_map_size)) {
                tileIndex = TileMap.cordsToIndexEquivalence.get(auxX + "," + mapY);
                tile = tileLinkedList.get(tileIndex);
                if (tile.isAccessible() && !tile.getOccupied())
                    possibleTilesToMove.add(tileIndex);
            }
            //Up
            auxY = mapY + i;
            if (cordValidator(mapX, auxY, 0, TileMap.world_map_size)) {
                tileIndex = TileMap.cordsToIndexEquivalence.get(mapX + "," + auxY);
                tile = tileLinkedList.get(tileIndex);
                if (tile.isAccessible() && !tile.getOccupied())
                    possibleTilesToMove.add(tileIndex);
            }
            //Down
            auxY = mapY - i;
            if (cordValidator(mapX, auxY, 0, TileMap.world_map_size)) {
                tileIndex = TileMap.cordsToIndexEquivalence.get(mapX + "," + auxY);
                tile = tileLinkedList.get(tileIndex);
                if (tile.isAccessible() && !tile.getOccupied())
                    possibleTilesToMove.add(tileIndex);
            }
        }

        /**Insert the intermediate tiles os each axis*/
        for (int y = movementCapacity - 1; y > 0; y--) {
            cont++;
            for (int x = y; x > 0; x--) {
                //Down-Left
                auxX = mapX - x;
                auxY = mapY - cont;
                if (cordValidator(auxX, auxY, 0, TileMap.world_map_size)) {
                    tileIndex = TileMap.cordsToIndexEquivalence.get(auxX + "," + auxY);
                    tile = tileLinkedList.get(tileIndex);
                    if (tile.isAccessible() && !tile.getOccupied())
                        possibleTilesToMove.add(tileIndex);
                }
                //Down-Right
                auxX = mapX + x;
                auxY = mapY - cont;
                if (cordValidator(auxX, auxY, 0, TileMap.world_map_size)) {
                    tileIndex = TileMap.cordsToIndexEquivalence.get(auxX + "," + auxY);
                    tile = tileLinkedList.get(tileIndex);
                    if (tile.isAccessible() && !tile.getOccupied())
                        possibleTilesToMove.add(tileIndex);
                }
                //Up-Left
                auxX = mapX - x;
                auxY = mapY + cont;
                if (cordValidator(auxX, auxY, 0, TileMap.world_map_size)) {
                    tileIndex = TileMap.cordsToIndexEquivalence.get(auxX + "," + auxY);
                    tile = tileLinkedList.get(tileIndex);
                    if (tile.isAccessible() && !tile.getOccupied())
                        possibleTilesToMove.add(tileIndex);
                }
                //Up-Right
                auxX = mapX + x;
                auxY = mapY + cont;
                if (cordValidator(auxX, auxY, 0, TileMap.world_map_size)) {
                    tileIndex = TileMap.cordsToIndexEquivalence.get(auxX + "," + auxY);
                    tile = tileLinkedList.get(tileIndex);
                    if (tile.isAccessible() && !tile.getOccupied())
                        possibleTilesToMove.add(tileIndex);
                }
            }
        }

        //Change to blue the tile that can be movement options and fill beforeTheBlueTiles
        TextureRegion blueTexture = RegionGiver.getRegion(false, "blue");
        for (Integer tI : possibleTilesToMove) {
            tile = tileLinkedList.get(tI);
            beforeTheBlueTiles.add(tile.getTexture());
            tile.setTexture(blueTexture);
        }
    }

    private boolean cordValidator(int x, int y, int min, int max) {
        boolean isValid = false;
        if (x >= min && x <= max && y >= min && y <= max) {
            isValid = true;
        }
        return isValid;
    }

    private void turnBlueBack(LinkedList<Tile> tileLinkedList) {
        Tile t;
        int cont = 0;
        for (Integer index : possibleTilesToMove) {
            t = tileLinkedList.get(index);
            t.setTexture(beforeTheBlueTiles.get(cont));
            cont++;
        }
    }

    private void newClickOps(LinkedList<Tile> tileLinkedList, Tile t, int cont) {
        //Check if there is an old tile, if yes: set it unselected
        if (oldTile != null) {
            oldTile.setSelected(false);
            tileLinkedList.set(oldPosition, oldTile);
        }
        //Fill old tile whit current
        oldTile = t;
        oldPosition = cont;
        //Set selected the tile just clicked
        t.setSelected(true);
    }
}
