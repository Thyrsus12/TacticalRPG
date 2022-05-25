package com.rotirmar.athena;

import characters.Character;
import characters.CharactersOperations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import jFrame.FrameworkBackMenu;
import jFrame.FrameworkMenu;
import mapTileByTile.Tile;
import mapTileByTile.TileMap;
import utilities.TilesOperations;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final SpriteBatch batch;
    private final Game game;
    private final OrthographicCamera cam;

    private TileMap map;

    private CharactersOperations charactersOps;
    private TilesOperations tilesOps;

    private FrameworkBackMenu frameworkBackMenu;

    public GameScreen(SpriteBatch batch, Game game, ArrayList<Integer> numCharacters, ArrayList<Integer> numCharacters2, int mapSize, String mapType, int screenWidthThird, int screenHeightThird) {
        this.game = game;
        this.batch = batch;
        this.cam = new OrthographicCamera(screenWidthThird, screenHeightThird);

        camParamCalculator(mapSize);

        this.map = new TileMap(mapSize, mapType);

        this.charactersOps = new CharactersOperations(numCharacters, numCharacters2, mapSize);
        this.tilesOps = new TilesOperations(map, charactersOps);
    }

    private void camParamCalculator(int mapSize) {
        float zoom = 0.3f;
        float x = 15f;
        float y = 70f;

        cam.zoom = (float) (zoom + 0.04 * (mapSize - 8));
        cam.position.x += x + 0.5 * (mapSize - 8);
        cam.position.y += y + 8 * (mapSize - 8);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

        keyboardInput();
        cam.update();
        mouseInput();

        batch.begin();
        map.render(batch);
        for (Character character : charactersOps.getCharacters()) {
            character.render(batch);
        }
        batch.end();
    }

    private void keyboardInput() {
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
        } else if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            //TODO
        }
    }

    public void mouseInput() {
        if (Gdx.input.justTouched()) {
            //Get mouse coordinates
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mousePos);

            //Convert mouse coordinates into map tile (0,0 - 0,1...)
            int mapX = (int) ((mousePos.x - 16) / Tile.TILE_WIDTH + (mousePos.y - 16) / Tile.TILE_HEIGHT);
            int mapY = (int) ((mousePos.y - 16) / Tile.TILE_HEIGHT - (mousePos.x - 16) / Tile.TILE_WIDTH);

            tilesOps.modifyTile(mapX, mapY);
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

}