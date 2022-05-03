package MapTileByTile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import utilities.RegionGiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class TileMap {

    private final int WORLD_MAP_SIZE = 7;
    public static final int TILE_WIDTH = 32;
    public static int TILE_HEIGHT = 16;

    public LinkedList<Tile> layer0;
    //private LinkedList<Tile> layer1;
    private String[][] mapLayer0;
    //private String[][] mapLayer1;
    public int[] selector = {6, 6};

    public TileMap() {
        layer0 = new LinkedList<Tile>();
        //layer1 = new LinkedList<Tile>();
        mapLayer0 = new String[8][8];
        //mapLayer1 = new String[7][7];

        try {
            fillMap();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILLMAP CALL FILED");
        }
    }

    public void render(SpriteBatch batch) {
        /**render Layer0*/
        for (Tile t : layer0) {
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

        /*/String map of Layer1*
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                mapLayer1[i][j] = "nothing";
            }
        }
        mapLayer1[selector[0]][selector[1]] = "s";*/

        /**Position of the map tiles generator (1 time execute)*/
        for (int row = WORLD_MAP_SIZE; row >= 0; row--) {
            for (int col = WORLD_MAP_SIZE; col >= 0; col--) {
                float x = (row - col) * TILE_WIDTH / 2.0001f;
                float y = (col + row) * TILE_HEIGHT / 2f;

                /**Layer0*/
                Random r = new Random();
                if (mapLayer0[row][col].equals("g")) {
                    if (r.nextInt(10) < 1) {
                        layer0.add(new Tile(
                                false,
                                RegionGiver.getRegion(false, "flowers"),
                                RegionGiver.getRegion(true, "flowers"),
                                new Vector2(row, col), new Vector2(x, y)));
                        //System.out.println("World -> X=" + x + " Y=" + y);
                        //System.out.println("Map -> X=" + row + " Y=" + col);
                    } else {
                        layer0.add(new Tile(
                                false,
                                RegionGiver.getRegion(false, "grass"),
                                RegionGiver.getRegion(true, "grass"),
                                new Vector2(row, col), new Vector2(x, y)));
                    }
                } else if (mapLayer0[row][col].equals("w")) {
                    layer0.add(new Tile(
                            false,
                            RegionGiver.getRegion(false, "water"),
                            RegionGiver.getRegion(true, "water"),
                            new Vector2(row, col), new Vector2(x, y)));
                }

                //Layer1
                /*if (mapLayer1[row][col].equals("s")){
                    layer1.add(new Tile(selected,  new Vector2(row, col), new Vector2(x, y)));
                }*/
            }
        }

        /*/each tile position
        for(Tile t : layer0) {
            System.out.println("World -> X=" + t.getTileWorldPos().x + " Y=" + t.getTileWorldPos().y);
            System.out.println("Map -> X=" + t.getTileMapPos().x + " Y=" + t.getTileMapPos().y);
        }*/
    }

    /*public int[] getSelector() {
        return selector;
    }*/

    /*public void setSelector(int[] selector) {
        this.selector = selector;
    }*/

    public LinkedList<Tile> getLayer0() {
        return layer0;
    }
}
