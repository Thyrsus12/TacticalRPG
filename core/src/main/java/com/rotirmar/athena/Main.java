package com.rotirmar.athena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private static final float WORLD_WIDTH = 20;
	private static final float WORLD_HEIGHT = 20;
	private static final float BLOCK_SIZE = 1;

	private OrthographicCamera cam;
	private Viewport viewport;

	private TiledMap tiledMap;
	private float unitScale;
	private IsometricTiledMapRenderer mapRenderer;


	@Override
	public void create() {

		cam = new OrthographicCamera();

		viewport = new ExtendViewport(10, 10, cam);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		tiledMap = new TmxMapLoader().load("testmap.tmx");
		unitScale = BLOCK_SIZE / 32f;
		mapRenderer = new IsometricTiledMapRenderer(tiledMap, unitScale);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mapRenderer.setView(cam);
		mapRenderer.render();

	}

	@Override
	public void dispose() {
		/*batch.dispose();
		image.dispose();*/
	}
}