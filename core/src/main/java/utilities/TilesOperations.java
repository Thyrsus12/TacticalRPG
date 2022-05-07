package utilities;

import mapTileByTile.Tile;
import mapTileByTile.TileMap;

import java.util.LinkedList;

public class TilesOperations {

    public void modifyTile(TileMap map, int mx, int my) {
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
            cont++;
        }
    }
}
