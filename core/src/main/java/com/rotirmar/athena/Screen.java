package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

import java.util.ArrayList;

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

        //what a apaño is this
        camera.position.set(WORLD_WIDTH / 2 - 2, WORLD_HEIGHT / 2 - 8, 0);
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
        handleInput();

        int[] layer1 = {0};
        int[] layer2 = {1};

        mapRenderer.setView(camera);
        mapRenderer.render(layer1);
        mapRenderer.render(layer2);
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
