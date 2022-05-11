package mapTileByTile;

import characters.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import utilities.RegionGiver;
import utilities.TilesOperations;

public class Screen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;

    private TileMap map;

    private Character character;

    public int mx, my;
    private TilesOperations tilesOps = new TilesOperations();

    public Screen(SpriteBatch batch) {
        this.batch = batch;
        this.cam = new OrthographicCamera(1280, 720);
        cam.zoom = 0.25f;
        cam.position.y += 80;
        this.map = new TileMap();
        this.character = new Character();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

        camInput();
        cam.update();
        mouseInput();
        //keyMovementInput(map.getSelector());

        batch.begin();
        map.render(batch);
        character.render(batch);

        /*Draw a lava cube in layer1 for testing
        float x = (4 - 5) * Tile.TILE_WIDTH / 2.0001f;
        float y = (5 + 4) * Tile.TILE_HEIGHT / 2f;
        Tile test = new Tile(
                false,
                RegionGiver.getRegion(false, "lava"),
                RegionGiver.getRegion(true, "lava"),
                new Vector2(4, 5), new Vector2(x, y));
        test.render(batch);*/

        batch.end();
    }

    private void camInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.position.x -= 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.position.y += 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            cam.position.y -= 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            cam.position.x += 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom += 0.01;
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            if (cam.zoom > 0.1)
                cam.zoom -= 0.01;
        }
    }

    public void mouseInput() {
        if (Gdx.input.justTouched()) {
            /**get mouse coordinates*/
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mousePos);
            //System.out.println("MPosX: " + mousePos.x + " MPosY: " + mousePos.y);

            /**Convert mouse coordinates into map tile (0,0 - 0,1...)*/
            float mapx = ((mousePos.x - 16) / Tile.TILE_WIDTH + (mousePos.y - 16) / Tile.TILE_HEIGHT);
            float mapy = ((mousePos.y - 16) / Tile.TILE_HEIGHT - (mousePos.x - 16) / Tile.TILE_WIDTH);
            mx = (int) mapx;
            my = (int) mapy;
            //System.out.println("-------------------------------------------");
            //System.out.println("Map X=" + mx + " Map Y=" + my);

            tilesOps.modifyTile(map, mx, my, character);
        }
    }

    /*public void keyMovementInput(int[] position) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            position[0]++;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            position[0]--;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            position[1]++;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            position[1]--;
        }
        map.setSelector(position);
    }*/

}