package utilities;

import characters.Character;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import mapTileByTile.Tile;
import mapTileByTile.TileMap;

import java.util.ArrayList;
import java.util.LinkedList;

public class TilesOperations {
    int oldPosition;
    Boolean characterSelected = false;
    ArrayList<Integer> posibleTilesToMove = new ArrayList();

    ArrayList<TextureRegion> beforeTheBlueTiles = new ArrayList<>();

    Tile oldTile = null;

    public void modifyTile(TileMap map, int mapX, int mapY, Character character) {
        /**Modify the tile of the tileLinkedList list and insert it again to select o unselect it*/
        int cont = 0;
        LinkedList<Tile> tileLinkedList = map.getTileLinkedList();
        for (Tile t : tileLinkedList) {
            if (character.getCharMapPos().x == mapX + 1 && character.getCharMapPos().y == mapY + 1 && !characterSelected) {
                movementPosibilitiesPainter(mapX, mapY, character.getMovementCapacity(), tileLinkedList);
                characterSelected = true;
            }
            //System.out.println("WorldPos -> X=" + t.getTileWorldPos().x + " Y=" + t.getTileWorldPos().y);
            if (t.getTileMapPos().x == mapX && t.getTileMapPos().y == mapY && !t.getSelected() && !characterSelected) {
                System.out.println(characterSelected);
                t.setSelected(true);
                tileLinkedList.set(cont, t);

                /**Checking a new Tile unchecks the previous one.*/
                //System.out.println(oldTile);
                //System.out.println(oldPosition);
                if (oldTile != null) {
                    oldTile.setSelected(false);
                    tileLinkedList.set(oldPosition, oldTile);
                }
                oldTile = t;
                oldPosition = cont;

            }  else if (t.getTileMapPos().x == mapX && t.getTileMapPos().y == mapY && characterSelected) {
                String tileSelectedCoords = mapX + "" + mapY;

               if (posibleTilesToMove.contains(TileMap.coordsToIndexEquivalence.get(tileSelectedCoords))){
                    character.setCharMapPos(new Vector2(mapX, mapY));
                    System.out.println("azulón");
                    //Cambiar posiciones del World
                } else {
                    //PROBLEMA
                    System.out.println("yerba");
                    Tile tAux;

                    int cont2 = 0;

                    //Pinta el mapa de vuelta (ya no es asul)
                    for (Integer index : posibleTilesToMove) {
                        tAux = tileLinkedList.get(index);
                        tAux.setT(beforeTheBlueTiles.get(cont2));
                        tileLinkedList.set(index, tAux);
                        cont2++;
                    }
                }
                characterSelected = false;
            }

            /**If the tile clicked contains a character calls movementPosibilitiesPainter()*/
            //System.out.println(tileLinkedList.get(cont).getTileMapPos().x);
            //System.out.println(tileLinkedList.get(cont).getTileMapPos().y);
            //System.out.println(character.getCharMapPos().x + " " + character.getCharMapPos().y);
            //System.out.println(mx + " " + mapY);

            cont++;
        }
    }

    /**
     * Calculates the accessible tiles taking into account the character's movement and the accessibility of the tiles
     */
    public void movementPosibilitiesPainter(Integer mapX, Integer mapY, Integer movementCapacity, LinkedList<Tile> mapLayer0) {
        int cont = 0, auxX, auxY;

        /**Insert the main cross (vertical and horizontal axis)*/
        for (int i = 1; i <= movementCapacity; i++) {
            /**Right*/
            auxX = mapX + i;
            if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY)).isAccessible())
                posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY));
            /**Left*/
            auxX = mapX - i;
            if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY)).isAccessible())
                posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + mapY));
            /**Up*/
            auxY = mapY + i;
            if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY)).isAccessible())
                posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY));
            /**Down*/
            auxY = mapY - i;
            if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY)).isAccessible())
                posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(mapX + "" + auxY));
        }

        /**Insert the intermediate tiles os each axis*/
        for (int y = movementCapacity - 1; y > 0; y--) {
            cont++;
            for (int x = y; x > 0; x--) {
                /**Down-Left*/
                auxX = mapX - x;
                auxY = mapY - cont;
                if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                    posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
                /**Down-Right*/
                auxX = mapX + x;
                auxY = mapY - cont;
                if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                    posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
                /**Up-Left*/
                auxX = mapX - x;
                auxY = mapY + cont;
                if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                    posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
                /**Up-Right*/
                auxX = mapX + x;
                auxY = mapY + cont;
                if (mapLayer0.get(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY)).isAccessible())
                    posibleTilesToMove.add(TileMap.coordsToIndexEquivalence.get(auxX + "" + auxY));
            }
        }

        /**Extracts, modifies and inserts the previously selected tiles*/
        Tile t;
        TextureRegion blueTexture = RegionGiver.getRegion(false, "blue");

        //Pinta de azul los posibles movimientos
        for (Integer pT : posibleTilesToMove) {
            t = mapLayer0.get(pT);
            beforeTheBlueTiles.add(t.getT());
            t.setT(blueTexture);
            mapLayer0.set(pT, t);
        }
    }

    /**
     * Whitout HasMap tactic. VERY SLOWLYYYY!!!
     */
    /*public void movementPosibilities(int mx, int my, LinkedList<Tile> linked) {
        mx -= 1;
        my -= 1;
        int cont2 = 0;
        //System.out.println("MX y MY: " +  mx + " " + my);
        for (int i = mx; i < mx + 3; i++) {
            for (int j = my; j < my + 3; j++) {
                int cont = 0;
                for (Tile t : linked) {
                    //System.out.println(t.getTileMapPos().x + " " + t.getTileMapPos().y);
                    if (t.getTileMapPos().x == i && t.getTileMapPos().y == j) {
                        cont2++;
                        t.setT(RegionGiver.getRegion(false, "blue"));
                        linked.set(cont, t);
                    }
                    cont++;
                }
            }
        }
        System.out.println(cont2);
    }*/
}
