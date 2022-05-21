package com.rotirmar.athena;

import characters.Character;
import characters.CharactersOperations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import mapTileByTile.Tile;
import mapTileByTile.TileMap;
import utilities.TilesOperations;

public class Screen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final OrthographicCamera cam;

    private TileMap map;

    private CharactersOperations charactersOps;
    private TilesOperations tilesOps;

    public Screen(SpriteBatch batch) {
        this.batch = batch;
        this.cam = new OrthographicCamera(1280, 720);
        cam.zoom = 0.25f;
        cam.position.y += 80;

        this.map = new TileMap();

        this.charactersOps = new CharactersOperations();
        this.tilesOps = new TilesOperations(map, charactersOps);

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

        camInput();
        cam.update();
        mouseInput();

        batch.begin();
        map.render(batch);
        for (Character character : charactersOps.getCharacters()) {
            character.render(batch);
        }

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
            //Get mouse coordinates
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mousePos);

            //Convert mouse coordinates into map tile (0,0 - 0,1...)
            int mapX = (int) ((mousePos.x - 16) / Tile.TILE_WIDTH + (mousePos.y - 16) / Tile.TILE_HEIGHT);
            int mapY = (int) ((mousePos.y - 16) / Tile.TILE_HEIGHT - (mousePos.x - 16) / Tile.TILE_WIDTH);

            tilesOps.modifyTile(mapX, mapY);
        }
    }
}