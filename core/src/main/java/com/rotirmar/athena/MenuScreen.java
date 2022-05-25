package com.rotirmar.athena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import jFrame.FrameworkMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuScreen implements Screen {

    private final SpriteBatch batch;
    private final Game game;

    private int screenWidthThird;
    private int screenHeightThird;

    private Texture btnNewMap;
    private Texture btnResume;
    private Texture btnCredits;
    public static int buttonWidth;
    public static int buttonHeight;

    private FrameworkMenu frameworkMenu;

    private Animation animation;
    private float time = 0f;

    public MenuScreen(SpriteBatch batch, Game game) {
        this.batch = batch;
        this.game = game;

        screenWidthThird = getWindowSize("width");
        screenHeightThird = getWindowSize("height");

        btnNewMap = new Texture("b1.png");
        btnResume = new Texture("b2.png");
        btnCredits = new Texture("b3.png");
        //Each button occupies 6.8% of the screen height and 33.4% of the screen width. A rule of three is made to calculate its size according to the actual screen size
        buttonHeight = (int) (screenHeightThird * 0.068f);
        buttonWidth = (int) (screenWidthThird * 0.334f);

        frameworkMenu = new FrameworkMenu();

        Texture backImage = new Texture("menu-background.png");
        makeAnimation(backImage);
    }

    private int getWindowSize(String option) {
        Toolkit display = Toolkit.getDefaultToolkit();
        Dimension screenSize = display.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int result = 0;
        switch (option) {
            case "width":
                result = (screenWidth / 3) * 2;
                break;
            case "height":
                result = (screenHeight / 3) * 2;
                break;
        }
        return result;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(getCurrentFrame(), 0, 0, screenWidthThird, screenHeightThird);
        batch.draw(btnNewMap, screenWidthThird / 3f, screenHeightThird / 1.55f, buttonWidth, buttonHeight);
        batch.draw(btnResume, screenWidthThird / 3f, screenHeightThird / 1.8f, buttonWidth, buttonHeight);
        batch.draw(btnCredits, screenWidthThird / 3f, screenHeightThird / 2.15f, buttonWidth, buttonHeight);
        batch.end();

        mouseInput();

        checkJFrame();
    }

    private TextureRegion getCurrentFrame() {
        time += Gdx.graphics.getDeltaTime(); //Time until the last render
        return (TextureRegion) animation.getKeyFrame(time, true);
    }

    private void checkJFrame() {
        if (frameworkMenu.getGenerated()) {
            ArrayList<Integer> numCharacters = frameworkMenu.getNumCharacters();
            ArrayList<Integer> numCharacters2 = frameworkMenu.getNumCharacters2();
            int mapSize = frameworkMenu.getSizeMap();
            String mapType = frameworkMenu.getTypeMap();
            frameworkMenu.setGenerated(false);
            game.setScreen(new GameScreen(batch, game, numCharacters, numCharacters2, mapSize, mapType, screenWidthThird, screenHeightThird));
        }
    }

    //The 0,0 of the mouse coordinates are at the top left
    private void mouseInput() {
        int clickX = Gdx.input.getX();
        int clickY = Gdx.input.getY();
        if (Gdx.input.justTouched()) {
            //The coordinates of the X-axis of the button start at its left and end at its left plus the width of the button
            if (clickX > screenWidthThird / 3f && clickX < screenWidthThird / 3f + buttonWidth) {
                /*As the positions are the ones used for painting, they start at the bottom-left and the mouse positions start at the top-left
                to collect clicks you have to invert them subtracting to the total (screen height) where you want to put them (position where they are painted)*/
                if (clickY > (screenHeightThird - (screenHeightThird / 1.55f + buttonHeight)) && clickY < (screenHeightThird - (screenHeightThird / 1.55f))) {
                    frameworkMenu.setVisible(true);
                    frameworkMenu.setResizable(false);
                    frameworkMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } else if (clickY > (screenHeightThird - (screenHeightThird / 1.8f + buttonHeight)) && clickY < (screenHeightThird - (screenHeightThird / 1.8f))) {

                } else if (clickY > (screenHeightThird - (screenHeightThird / 2.15f + buttonHeight)) && clickY < (screenHeightThird - (screenHeightThird / 2.15f))) {
                    game.setScreen(new CreditsScreen(batch, screenWidthThird, screenHeightThird, game, this));
                }
            }
        }
    }

    private void makeAnimation(Texture t) {
        int frames = t.getWidth() / 1920;
        TextureRegion[][] tmp = TextureRegion.split(t, t.getWidth() / frames, t.getHeight());
        TextureRegion[] regionMovement = new TextureRegion[frames];
        for (int i = 0; i < frames; i++) {
            regionMovement[i] = tmp[0][i];
        }
        animation = new Animation(0.8f, regionMovement);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void show() {
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
}
