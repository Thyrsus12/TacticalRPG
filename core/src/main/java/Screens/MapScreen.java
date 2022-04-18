package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class MapScreen extends ScreenAdapter {

    public static final int WORLD_WIDTH = 20;
    public static final int WORLD_HEIGHT = 14;
    private static final float BLOCK_SIZE = 1;


    private SpriteBatch batch;
    private OrthographicCamera camera;

    private TiledMap tiledMap;
    private float unitScale;
    private IsometricTiledMapRenderer mapRenderer;

    private Texture texture;
    TiledMapTileLayer.Cell cell;
    TiledMapTile tile;

    public MapScreen(SpriteBatch batch) {
        this.batch = batch;

        tiledMap = new TmxMapLoader().load("testmap.tmx");
        unitScale = BLOCK_SIZE / 32f;
        mapRenderer = new IsometricTiledMapRenderer(tiledMap, unitScale);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);

        //what a apaño is this
        camera.position.set(WORLD_WIDTH / 2 - 2, WORLD_HEIGHT / 2 - 6, 0);
        camera.update();

        TiledMapTileLayer layer0 = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        //System.out.println("Layer Width-Height: " + layer0.getWidth() + "-" + layer0.getHeight());
        //System.out.println("Tile Width-Height: " + layer0.getTileWidth() + "-" + layer0.getTileHeight());

        cell = layer0.getCell(1, 1);
        tile = cell.getTile();
        //System.out.println("Tile ID: " + tile.getId());
        //System.out.println("Pixeles tile: " + tile.getOffsetY());

        texture = new Texture("alex.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        int[] layer1 = {0};
        int[] layer2 = {1};

        mapRenderer.setView(camera);
        mapRenderer.render(layer1);
        mapRenderer.render(layer2);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture, tile.getOffsetX() + 0.32f, tile.getOffsetY() + 0.64f, 0.5f, 0.5f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(+0.5f, 0, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.translate(+0.5f, 0, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.translate(-0.5f, 0, 0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.translate(-0.5f, 0, 0);
        }
    }
}
