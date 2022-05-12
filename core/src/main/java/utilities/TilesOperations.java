package utilities;

import characters.Character;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import mapTileByTile.Tile;
import mapTileByTile.TileMap;

import java.util.ArrayList;
import java.util.LinkedList;

public class TilesOperations {
    private Boolean characterSelected;
    private ArrayList<Integer> possibleTilesToMove;
    private ArrayList<TextureRegion> beforeTheBlueTiles;

    private int oldPosition;
    private Tile oldTile;

    public TilesOperations() {
        this.characterSelected = false;
        this.possibleTilesToMove = new ArrayList<>();
        this.beforeTheBlueTiles = new ArrayList<>();
        this.oldTile = null;
    }

    public void modifyTile(TileMap map, int mapX, int mapY, Character character) {
        /**Modify the tile of the tileLinkedList list and insert it again to select or unselect it*/
        int cont = 0;
        LinkedList<Tile> tileLinkedList = map.getTileLinkedList();

        for (Tile t : tileLinkedList) {
            /**Check if tile clicked contains a character*/
            if (character.getCharMapPos().x == mapX + 1 && character.getCharMapPos().y == mapY + 1 && !characterSelected) {
                movementPossibilitiesPainter(mapX, mapY, character.getMovementCapacity(), tileLinkedList);
                characterSelected = true;
            }

            /**If !characterSelected*/
            //System.out.println("WorldPos -> X=" + t.getTileWorldPos().x + " Y=" + t.getTileWorldPos().y);
            if (t.getTileMapPos().x == mapX && t.getTileMapPos().y == mapY && !t.getSelected() && !characterSelected) {
                /**Set true isSelected to tile clicked*/
                t.setSelected(true);
                //tileLinkedList.set(cont, t);

                /**Checking a new Tile unchecks the previous one.*/
                oldTileChecker(tileLinkedList);

                /**Fill with the current tile*/
                oldTileFiller(t, cont);

                /**If characterSelected*/
            } else if (t.getTileMapPos().x == mapX && t.getTileMapPos().y == mapY && !t.getSelected() && characterSelected) {

                /**Move the character if click in blue tile*/
                String tileSelectedCoords = mapX + "" + mapY;
                if (possibleTilesToMove.contains(TileMap.coordsToIndexEquivalence.get(tileSelectedCoords))) {
                    mapX += 1;
                    mapY += 1;
                    character.setCharMapPos(new Vector2(mapX, mapY));
                    float x = (mapX - mapY) * Tile.TILE_WIDTH / 2.0001f;
                    float y = (mapY + mapX) * Tile.TILE_HEIGHT / 2f;
                    character.setCharWorldPos(new Vector2(x, y));
                }

                /**Set true isSelected to tile clicked*/
                t.setSelected(true);

                /**Checking a new Tile unchecks the previous one.*/
                oldTileChecker(tileLinkedList);

                /**Fill with the current tile*/
                oldTileFiller(t, cont);

                /**Eliminate the blue tiles because you have unchecked the character's tile.*/
                turnBlueBack(tileLinkedList);
                characterSelected = false;
            }
            cont++;
        }
    }

    private void oldTileChecker(LinkedList<Tile> tileLinkedList) {
        if (oldTile != null) {
            oldTile.setSelected(false);
            tileLinkedList.set(oldPosition, oldTile);
        }
    }

    private void oldTileFiller(Tile t, int cont) {
        oldTile = t;
        oldPosition = cont;
    }

    private void turnBlueBack(LinkedList<Tile> tileLinkedList) {
        Tile t;
        int cont = 0;
        for (Integer index : possibleTilesToMove) {
            t = tileLinkedList.get(index);
            t.setT(beforeTheBlueTiles.get(cont));
            //tileLinkedList.set(index, t);
            cont++;
        }
    }

    /**
     * Calculates the accessible tiles taking into account the character's movement and the accessibility of the tiles
     */
    public void movementPossibilitiesPainter(Integer mapX, Integer mapY, Integer movementCapacity, LinkedList<Tile> tileLinkedList) {
        int cont = 0, auxX, auxY;
        possibleTilesToMove = new ArrayList<>();
        beforeTheBlueTiles = new ArrayList<>();

        /**Insert the main cross (vertical and horizontal axis)*/
        for (int i = 1; i <= movementCapacity; i++) {
            /**Right*/
            auxX = mapX + i;
            if (coordValidator(auxX, mapY, 0, 7)) {
                if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY)).isAccessible())
                    possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY));
            }
            /**Left*/
            auxX = mapX - i;
            if (coordValidator(auxX, mapY, 0, 7)) {
                if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY)).isAccessible())
                    possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY));
            }
            /**Up*/
            auxY = mapY + i;
            if (coordValidator(mapX, auxY, 0, 7)) {
                if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY)).isAccessible())
                    possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY));
            }
            /**Down*/
            auxY = mapY - i;
            if (coordValidator(mapX, auxY, 0, 7)) {
                if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY)).isAccessible())
                    possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY));
            }
        }

        /**Insert the intermediate tiles os each axis*/
        for (int y = movementCapacity - 1; y > 0; y--) {
            cont++;
            for (int x = y; x > 0; x--) {
                /**Down-Left*/
                auxX = mapX - x;
                auxY = mapY - cont;
                if (coordValidator(auxX, auxY, 0, 7)) {
                    if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                        possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
                }
                /**Down-Right*/
                auxX = mapX + x;
                auxY = mapY - cont;
                if (coordValidator(auxX, auxY, 0, 7)) {
                    if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                        possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
                }
                /**Up-Left*/
                auxX = mapX - x;
                auxY = mapY + cont;
                if (coordValidator(auxX, auxY, 0, 7)) {
                    if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                        possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
                }
                /**Up-Right*/
                auxX = mapX + x;
                auxY = mapY + cont;
                if (coordValidator(auxX, auxY, 0, 7)) {
                    if (tileLinkedList.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                        possibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
                }
            }
        }

        /**Change to blue the tile that can be movement options and fill beforeTheBlueTiles */
        Tile t;
        TextureRegion blueTexture = RegionGiver.getRegion(false, "blue");
        for (Integer pT : possibleTilesToMove) {
            t = tileLinkedList.get(pT);
            beforeTheBlueTiles.add(t.getT());
            t.setT(blueTexture);
            //tileLinkedList.set(pT, t);
        }
    }

    public boolean coordValidator(int x, int y, int min, int max) {
        boolean isValid = false;
        if  (x>=min && x<=max && y>=min && y<=max){
            isValid = true;
        }
        return isValid;
    }

}