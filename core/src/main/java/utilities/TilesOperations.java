package utilities;

import characters.Character;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import mapTileByTile.Tile;
import mapTileByTile.TileMap;

import java.util.ArrayList;
import java.util.LinkedList;

public class TilesOperations {

    public void modifyTile(TileMap map, int mapX, int mapY, Character character) {
        /**Modify the tile of the array and insert it again*/
        int cont = 0;
        Tile oldTile = null;
        LinkedList<Tile> linked = map.getLayer0();
        for (Tile t : linked) {
            //System.out.println("WorldPos -> X=" + t.getTileWorldPos().x + " Y=" + t.getTileWorldPos().y);
            if (t.getTileMapPos().x == mapX && t.getTileMapPos().y == mapY && !t.getSelected()) {
                //Quizá podamos deseleccionar una casilla al poner una nueva?
                if (oldTile != null && oldTile != t) {
                    oldTile.setSelected(false);
                }

                t.setSelected(true);
                linked.set(cont, t);
                oldTile = t;
            } else if (t.getTileMapPos().x == mapX && t.getTileMapPos().y == mapY && t.getSelected()) {
                t.setSelected(false);
                linked.set(cont, t);
            }
            //System.out.println(linked.get(cont).getTileMapPos().x);
            //System.out.println(linked.get(cont).getTileMapPos().y);
            //System.out.println(character.getCharMapPos().x + " " + character.getCharMapPos().y);
            //System.out.println(mx + " " + mapY);
            if (character.getCharMapPos().x == mapX + 1 && character.getCharMapPos().y == mapY + 1) {
                movementPosibilitiesPainter(mapX, mapY, character.getMovementCapacity(), linked);
            }
            cont++;
        }
    }

    /**Como funciona esta vaina lo añado en cuanto pueda BBs*/
    public void movementPosibilitiesPainter(Integer mapX, Integer mapY, Integer movementCapacity, LinkedList<Tile> linked) {
        ArrayList<Integer> posibleTiles = new ArrayList();
        int cont = 0 , auxX, auxY;
        for (int y = movementCapacity - 1; y > 0; y--) {
            cont++;
            for (int x = y; x > 0; x--) {
                auxX = mapX-x;
                auxY = mapY-cont;
                posibleTiles.add(TileMap.layer0Map.get(auxX + "" + auxY));
                auxX = mapX+x;
                auxY = mapY-cont;
                posibleTiles.add(TileMap.layer0Map.get(auxX + "" + auxY));
                auxX = mapX-x;
                auxY = mapY+cont;
                posibleTiles.add(TileMap.layer0Map.get(auxX + "" + auxY));
                auxX = mapX+x;
                auxY = mapY+cont;
                posibleTiles.add(TileMap.layer0Map.get(auxX + "" + auxY));
            }
        }

        for (int i = 1; i <= movementCapacity; i++) {
            auxX = mapX+i;
            posibleTiles.add(TileMap.layer0Map.get(auxX + "" + mapY));
            auxX = mapX-i;
            posibleTiles.add(TileMap.layer0Map.get(auxX + "" + mapY));
            auxY = mapY+i;
            posibleTiles.add(TileMap.layer0Map.get(mapX + "" + auxY));
            auxY = mapY-i;
            posibleTiles.add(TileMap.layer0Map.get(mapX + "" + auxY));
        }

        Tile t;
        TextureRegion blueTexture = RegionGiver.getRegion(false, "blue");
        for (Integer pT: posibleTiles) {
            t = linked.get(pT);
            t.setT(blueTexture);
            linked.set(pT,t);
        }


        /*int potentialTiles = 0;
        String eachTile = null;
        int cont = movementCapacity;

        //Calculate the potential tiles. May change to a recursive method.
        while (cont>0){
            potentialTiles += cont*4;
            cont--;
        }

        Integer[] potentialMovementsX = new Integer[potentialTiles]; //-> array de referencias a los valores del mapa
        Integer[] potentialMovementsY = new Integer[potentialTiles]; //-> array de referencias a los valores del mapa
        */
        //Idea de hoy que puede que no tenga sentido mañana: hacer dos for rellenando la X y la Y y luego juntarlos.
        /*int contMovement = movementCapacity;
        for (int i = 0; i < potentialTiles; i++) {
                potentialMovementsY[i] = mapY-contMovement;
                if (i%2!=0){
                    contMovement--;
                }
        }
        System.out.println(Arrays.toString(potentialMovementsY));*/


        //for (int i = 0; i < potentialTiles; i++) {
        //    eachTile = String.valueOf(mapxX);
        //    potentialMovements[i] = TileMap.layer0Map.get(eachTile);
        //}


        //La i tiene que ser combinatoria de 2
        //La diferencia entre los números viejos y los nuevos es igual o menor que i

    }



/* public void movementPosibilities(int mx, int my, LinkedList<Tile> linked) {
        mx -= 1;
        my -= 1;
        int cont2 = 0;
        //System.out.println("MX y MY: " +  mx + " " + my);
        for (int i = mx; i < mx+3; i++) {
            for (int j = my; j < my+3; j++) {
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
