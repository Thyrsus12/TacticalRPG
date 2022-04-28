package MapTileByTile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class TileMap {

    private LinkedList<Tile> layer0;
    private LinkedList<Tile> layer1;
    private Texture grass;
    private Texture water;
    private Texture flowers;
    private Texture lava;
    private Texture selected;
    private String[][] mapLayer0;
    private String[][] mapLayer1;
    int[] selector = {5, 3};

    public TileMap() {
        grass = new Texture("grass.png");
        water = new Texture("water.png");
        flowers = new Texture("flowers.png");
        lava = new Texture("lava.png");
        selected = new Texture("selected.png");
        layer0 = new LinkedList<Tile>();
        layer1 = new LinkedList<Tile>();
        mapLayer0 = new String[7][7];
        mapLayer1 = new String[7][7];

        try {
            fillMap();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fillMap call failed");
        }
    }

    public void render(SpriteBatch batch) {
        for(Tile t : layer0) {
            t.render(batch);
        }

        //for(Tile t : layer1) {
        //    t.render(batch);
        //}
        int row = getSelector()[0];
        int col = getSelector()[1];
        Tile selectedTile = new Tile(selected, new Vector2(row, col),
                new Vector2((row - col) * 32 / 2.0001f, (col +row) * 16 / 2f));
        selectedTile.render(batch);
    }

    public void fillMap() throws IOException {
        Random r = new Random();
        String rute = new File("").getAbsolutePath();
        //System.out.println(rute);
        rute += "/assets/map.txt";
        FileHandle fh = Gdx.files.internal(rute);
        System.out.println("ARCHIVO: " + fh.path());
        BufferedReader br = new BufferedReader(new FileReader(fh.path()));
        String s = "";
        int count = 0;

        while ((s = br.readLine()) != null) {
            System.out.println(s);
            mapLayer0[count] = s.split(" ");
            count++;
        }
        br.close();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                mapLayer1[i][j] = "nothing";
            }
        }

        mapLayer1[selector[0]][selector[1]] = "s";

        //Generador de mapa (se ejecuta 1 vez)
        for (int row = 6; row >=0; row--){
            for (int col = 6; col >=0; col--) {
                float x = (row - col) * 32 / 2.0001f;
                float y = (col +row) * 16 / 2f;

                //Layer0
                if (mapLayer0[row][col].equals("g")) {
                    if (r.nextInt(10)<1){
                        layer0.add(new Tile(flowers, new Vector2(row, col), new Vector2(x, y)));
                    } else {
                        layer0.add(new Tile(grass, new Vector2(row, col), new Vector2(x, y)));
                    }
                } else if (mapLayer0[row][col].equals("w")) {
                    layer0.add(new Tile(water, new Vector2(row, col), new Vector2(x, y)));
                }

                //Layer1
                //if (mapLayer1[row][col].equals("s")){
                //    layer1.add(new Tile(selected,  new Vector2(row, col), new Vector2(x, y)));
                //}
            }
        }
    }

    public void selectTile(){

    }

    public int[] getSelector() {
        return selector;
    }

    public void setSelector(int[] selector) {
        this.selector = selector;
    }
}
