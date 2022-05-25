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
    private MenuScreen menu;

    public GameScreen(SpriteBatch batch, Game game, MenuScreen menu, ArrayList<Integer> numCharacters, int mapSize, String mapType, int screenWidthThird, int screenHeightThird) {
        this.game = game;
        this.batch = batch;
        this.menu = menu;
        this.cam = new OrthographicCamera(screenWidthThird, screenHeightThird);
        cam.zoom = 0.3f;
        cam.position.y += 85;
        cam.position.x += 10;

        this.map = new TileMap(mapSize, mapType);

        this.charactersOps = new CharactersOperations(numCharacters);
        this.tilesOps = new TilesOperations(map, charactersOps);

        //frameworkBackMenu = new FrameworkBackMenu();
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

        //checkJFrame();
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
            game.setScreen(menu);
            //dispose();
            //frameworkBackMenu.setVisible(true);
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

    private void checkJFrame() {
        if (!frameworkBackMenu.getVisible()) {
            game.setScreen(new MenuScreen(batch, game));
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
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