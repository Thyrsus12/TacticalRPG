package mapTileByTile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import utilities.RegionGiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class TileMap {

    private final int WORLD_MAP_SIZE = 7;

    public static Map<String, Integer> coordsToIndexEquivalence = new HashMap<>();

    public LinkedList<Tile> tileLinkedList;
    private String[][] mapLayer0;
    //private LinkedList<Tile> layer1;
    //private String[][] mapLayer1;
    //public int[] selector = {6, 6};

    public TileMap() {
        tileLinkedList = new LinkedList<Tile>();
        mapLayer0 = new String[8][8];
        //layer1 = new LinkedList<Tile>();
        //mapLayer1 = new String[7][7];

        try {
            fillMap();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILLMAP CALL FILLED");
        }
    }

    public void render(SpriteBatch batch) {
        /**render Layer0*/
        for (Tile t : tileLinkedList) {
            t.render(batch);
        }

        /*for(Tile t : layer1) {
            t.render(batch);
        }*/

        /**render de unique tile in Layer1*/
        /*int row = getSelector()[0];
        int col = getSelector()[1];
        Tile selectedTile = new Tile(RegionGiver.getRegion(true,"lava"), new Vector2(row, col),
                new Vector2((row - col) * TILE_WIDTH / 2.0001f, (col +row) * TILE_HEIGHT / 2f));
        selectedTile.render(batch);*/
    }

    public void fillMap() throws IOException {
        String rute = new File("").getAbsolutePath() + "/assets/map.txt";
        //System.out.println("ARCHIVO: " + rute);

        /**String map of Layer0*/
        BufferedReader br = new BufferedReader(new FileReader(rute));
        String s;
        int count = 0;
        while ((s = br.readLine()) != null) {
            //System.out.println(s);
            mapLayer0[count] = s.split(" ");
            count++;
        }
        br.close();

        /**Position of the map tiles generator (1 time execute)*/
        int cont = 0;
        for (int row = WORLD_MAP_SIZE; row >= 0; row--) {
            for (int col = WORLD_MAP_SIZE; col >= 0; col--) {
                float x = (row - col) * Tile.TILE_WIDTH / 2.0001f;
                float y = (col + row) * Tile.TILE_HEIGHT / 2f;

                /**Layer0*/
                Random r = new Random();
                /**Fill Map(layer0Map) of mapCoords-tileArray equivalences*/
                coordsToIndexEquivalence.put(row + "" + col, cont);

                /**Choose the appropriate tile as indicated in map.txt and insert it*/
                if (mapLayer0[row][col].equals("g")) {
                    if (r.nextInt(10) < 5) {
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "flowers"),
                                RegionGiver.getRegion(true, "flowers"),
                                new Vector2(row, col), new Vector2(x, y)));
                        //System.out.println("World -> X=" + x + " Y=" + y);
                        //System.out.println("Map -> X=" + row + " Y=" + col);
                    } else {
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "grass"),
                                RegionGiver.getRegion(true, "grass"),
                                new Vector2(row, col), new Vector2(x, y)));
                    }
                } else if (mapLayer0[row][col].equals("w")) {
                    tileLinkedList.add(new Tile(
                            false,
                            RegionGiver.getRegion(false, "water"),
                            RegionGiver.getRegion(true, "water"),
                            new Vector2(row, col), new Vector2(x, y)));
                } else if (mapLayer0[row][col].equals("l")) {
                    tileLinkedList.add(new Tile(
                            false,
                            RegionGiver.getRegion(false, "lava"),
                            RegionGiver.getRegion(true, "lava"),
                            new Vector2(row, col), new Vector2(x, y)));
                }
                cont++;

            }
        }
        //layer0Map.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    public LinkedList<Tile> getTileLinkedList() {
        return tileLinkedList;
    }
}
