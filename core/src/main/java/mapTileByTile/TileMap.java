package mapTileByTile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import mapGenerator.Cartographer;
import utilities.RegionGiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class TileMap {

    public static int mapSize;
    public static final int WORLD_MAP_SIZE = mapSize - 1;

    public static HashMap<String, Integer> cordsToIndexEquivalence = new HashMap<>();

    private final Cartographer cartographer;

    public LinkedList<Tile> tileLinkedList;
    private final String[][] mapLayer0;
    //private LinkedList<Tile> layer1;
    //private String[][] mapLayer1;
    //public int[] selector = {6, 6};


    public TileMap(int mapSize, String mapType) {
        this.mapSize = mapSize;
        System.out.println(mapSize + "tileMap");
        cartographer = new Cartographer();
        tileLinkedList = new LinkedList<>();
        mapLayer0 = new String[mapSize][mapSize];
        //layer1 = new LinkedList<Tile>();
        //mapLayer1 = new String[7][7];

        try {
            fillMap();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILL MAP FILLED");
        }
    }

    public void render(SpriteBatch batch) {
        //render Layer0
        for (Tile t : tileLinkedList) {
            t.render(batch);
        }

        /*for(Tile t : layer1) {
            t.render(batch);
        }*/

        //render de unique tile in Layer1
        /*int row = getSelector()[0];
        int col = getSelector()[1];
        Tile selectedTile = new Tile(RegionGiver.getRegion(true,"lava"), new Vector2(row, col),
                new Vector2((row - col) * TILE_WIDTH / 2.0001f, (col +row) * TILE_HEIGHT / 2f));
        selectedTile.render(batch);*/
    }

    public void fillMap() throws IOException {
        prepareMap();

        String rute = new File("").getAbsolutePath() + "/assets/map.txt";

        //String map of Layer0
        BufferedReader br = new BufferedReader(new FileReader(rute));
        String s;
        int count = 0;
        while ((s = br.readLine()) != null) {
            mapLayer0[count] = s.split(" ");
            count++;
        }
        br.close();

        //Position of the map tiles generator (1 time execute)
        int cont = 0;
        for (int row = WORLD_MAP_SIZE; row >= 0; row--) {
            for (int col = WORLD_MAP_SIZE; col >= 0; col--) {
                float x = (row - col) * Tile.TILE_WIDTH / 2.0001f;
                float y = (col + row) * Tile.TILE_HEIGHT / 2f;

                /**Layer0*/
                Random r = new Random();
                //Fill map(cordsToIndexEquivalence) of mapCords-tileArray equivalences
                cordsToIndexEquivalence.put(row + "," + col, cont);

                //Choose the appropriate tile as indicated in map.txt and insert it
                switch (mapLayer0[row][col]) {
                    case "g":
                        if (r.nextInt(10) < 5) {
                            tileLinkedList.add(new Tile(
                                    true,
                                    RegionGiver.getRegion(false, "flowers"),
                                    RegionGiver.getRegion(true, "flowers"),
                                    new Vector2(row, col), new Vector2(x, y)));
                        } else {
                            tileLinkedList.add(new Tile(
                                    true,
                                    RegionGiver.getRegion(false, "grass"),
                                    RegionGiver.getRegion(true, "grass"),
                                    new Vector2(row, col), new Vector2(x, y)));
                        }
                        break;
                    case "w":
                        tileLinkedList.add(new Tile(
                                false,
                                RegionGiver.getRegion(false, "water"),
                                RegionGiver.getRegion(true, "water"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "l":
                        tileLinkedList.add(new Tile(
                                false,
                                RegionGiver.getRegion(false, "lava"),
                                RegionGiver.getRegion(true, "lava"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "y":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "darkLava"),
                                RegionGiver.getRegion(true, "darkLava"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "m":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "mud"),
                                RegionGiver.getRegion(true, "mud"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "n":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "mudGrass"),
                                RegionGiver.getRegion(true, "mudGrass"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "h":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "herbs"),
                                RegionGiver.getRegion(true, "herbs"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "v":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "weedy"),
                                RegionGiver.getRegion(true, "weedy"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "s":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "sand"),
                                RegionGiver.getRegion(true, "sand"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "p":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "stonePath"),
                                RegionGiver.getRegion(true, "stonePath"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "r":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "rocks"),
                                RegionGiver.getRegion(true, "rocks"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "b":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "bricks"),
                                RegionGiver.getRegion(true, "bricks"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "f":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "snow"),
                                RegionGiver.getRegion(true, "snow"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                    case "i":
                        tileLinkedList.add(new Tile(
                                true,
                                RegionGiver.getRegion(false, "snowMud"),
                                RegionGiver.getRegion(true, "snowMud"),
                                new Vector2(row, col), new Vector2(x, y)));
                        break;
                }
                cont++;
            }
        }
    }

    public void prepareMap() {
        cartographer.writeMap();
    }

    public LinkedList<Tile> getTileLinkedList() {
        return tileLinkedList;
    }

}
