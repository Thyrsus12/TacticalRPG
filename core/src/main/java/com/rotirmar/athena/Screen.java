package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class Screen extends ScreenAdapter {

    public static final int WORLD_WIDTH = 20;
    public static final int WORLD_HEIGHT = 20;
    private static final float BLOCK_SIZE = 1;


    private SpriteBatch batch;
    private OrthographicCamera camera;

    private TiledMap tiledMap;
    private float unitScale;
    private IsometricTiledMapRenderer mapRenderer;

    public Screen(SpriteBatch batch) {
        this.batch = batch;

        tiledMap = new TmxMapLoader().load("testmap.tmx");
        unitScale = BLOCK_SIZE / 32f;
        mapRenderer = new IsometricTiledMapRenderer(tiledMap, unitScale);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 10);
        camera.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.end();*/

        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        super.dispose();
    }
}
