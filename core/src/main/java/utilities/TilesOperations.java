package utilities;

import characters.Character;
import mapTileByTile.Tile;
import mapTileByTile.TileMap;

import java.util.LinkedList;

public class TilesOperations {

    public void modifyTile(TileMap map, int mx, int my, Character character) {
        /**Modify the tile of the array and insert it again*/
        int cont = 0;
        LinkedList<Tile> linked = map.getLayer0();
        for (Tile t : linked) {
            //System.out.println("WorldPos -> X=" + t.getTileWorldPos().x + " Y=" + t.getTileWorldPos().y);
            if (t.getTileMapPos().x == mx && t.getTileMapPos().y == my && !t.getSelected()) {
                t.setSelected(true);
                linked.set(cont, t);
            } else if (t.getTileMapPos().x == mx && t.getTileMapPos().y == my && t.getSelected()) {
                t.setSelected(false);
                linked.set(cont, t);
            }
            //System.out.println(linked.get(cont).getTileMapPos().x);
            //System.out.println(linked.get(cont).getTileMapPos().y);
            //System.out.println(character.getCharMapPos().x + " " + character.getCharMapPos().y);
            //System.out.println(mx + " " + my);
            if (character.getCharMapPos().x == mx+1 && character.getCharMapPos().y == my+1) {
                movementPosibilities(mx, my, linked);
            }
            cont++;
        }
    }

    public void movementPosibilities(int mx, int my, LinkedList<Tile> linked) {
        mx -= 1;
        my -= 1;
        int cont2 = 0;
        System.out.println("MX y MY: " +  mx + " " + my);
        for (int i = mx; i < mx+3; i++) {
            for (int j = my; j < my+3; j++) {
                int cont = 0;
                for (Tile t : linked) {
                    System.out.println(t.getTileMapPos().x + " " + t.getTileMapPos().y);
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

    }
}
