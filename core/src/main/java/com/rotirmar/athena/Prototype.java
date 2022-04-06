package com.rotirmar.athena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricStaggeredTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Prototype extends Game {

    private SpriteBatch batch;

    private static final float WORLD_WIDTH = 20;
    private static final float WORLD_HEIGHT = 20;
    private static final float BLOCK_SIZE = 1;

    private OrthographicCamera cam;
    private Viewport viewport;

    private TiledMap tiledMap;
    private float unitScale;
    private TiledMapRenderer mapRenderer;

    public Prototype() {
        cam = new OrthographicCamera();
        viewport = new ExtendViewport(10, 10, cam);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        tiledMap = new TmxMapLoader().load("city.tmx");
        // 1 World Unit = 32 pixels in map file
        unitScale = BLOCK_SIZE / 32f;
        mapRenderer = new IsometricTiledMapRenderer(tiledMap, unitScale);
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    @Override
    public void render () {
        super.render();
        mapRenderer.render();
    }

    @Override
    public void dispose () {

    }
}
