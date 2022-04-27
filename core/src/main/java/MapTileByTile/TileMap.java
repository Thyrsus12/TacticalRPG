package MapTileByTile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class TileMap {

    private LinkedList<Tile> layer0;
    private Texture grass;
    private Texture water;
    private String[][] map;

    public TileMap() {
        grass = new Texture("grass.png");
        water = new Texture("water.png");
        layer0 = new LinkedList<Tile>();
        map = new String[7][7];

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
    }

    public void fillMap() throws IOException {
        FileHandle fh = Gdx.files.internal("C:\\Users\\Familia\\IdeaProjects\\TacticalRPG\\assets\\map.txt");
        System.out.println("ARCHIVO: " + fh.path());
        BufferedReader br = new BufferedReader(new FileReader(fh.path()));
        String s = "";
        int count = 0;

        while ((s = br.readLine()) != null) {
            System.out.println(s);
            map[count] = s.split(" ");
            count++;
        }
        br.close();

        for (int row = 6; row >=0; row--){
            for (int col = 6; col >=0; col--) {
                float x = (row - col) * 64/2.0001f;
                float y = (col +row) * 32 / 2f;

                if (map[row][col].equals("g")) {
                    layer0.add(new Tile(grass, new Vector2(row, col), new Vector2(x, y)));
                } else if (map[row][col].equals("w")) {
                    layer0.add(new Tile(water, new Vector2(row, col), new Vector2(x, y)));

                }
            }
        }
    }
}
