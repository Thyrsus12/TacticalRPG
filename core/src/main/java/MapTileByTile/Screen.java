package MapTileByTile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;

public class Screen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private TileMap map;

    public int mx, my;

    public Screen(SpriteBatch batch) {
        this.batch = batch;
        this.cam = new OrthographicCamera(1280, 720);
        this.map = new TileMap();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

        camInput();
        cam.update();

        //movementInput(map.getSelector());
        mouseInput();

        batch.begin();
        map.render(batch);
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
            cam.zoom += 0.02;
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.zoom -= 0.02;
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            if (cam.zoom > 0.2)
                cam.zoom -= 0.02;
        }
    }

    public void movementInput(int[] position) {
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
    }

    public void mouseInput() {
        if (Gdx.input.justTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mousePos);

            //System.out.println("MPosX: " + mousePos.x + " MPosY: " + mousePos.y);
            float mapx = ((mousePos.x - 16) / TileMap.TILE_WIDTH + (mousePos.y - 16) / TileMap.TILE_HEIGHT);
            float mapy = ((mousePos.y - 16) / TileMap.TILE_HEIGHT - (mousePos.x - 16) / TileMap.TILE_WIDTH);

            mx = (int) mapx;
            my = (int) mapy;
            //System.out.println("-------------------------------------------");
            //System.out.println("Map X=" + mx + " Map Y=" + my);

            int cont = 0;
            LinkedList<Tile> linked = map.getLayer0();
            for (Tile t : linked) {
                Tile taux = linked.get(cont);
                //System.out.println("WorldPos -> X=" + t.getTileWorldPos().x + " Y=" + t.getTileWorldPos().y);
                if (t.getTileMapPos().x == mx && t.getTileMapPos().y == my && !t.isSelected) {
                    taux.isSelected = true;
                    linked.set(cont, taux);
                } else if (t.getTileMapPos().x == mx && t.getTileMapPos().y == my && t.isSelected){
                    taux.isSelected = false;
                    linked.set(cont, taux);
                }
                cont++;
            }
        }
    }
}