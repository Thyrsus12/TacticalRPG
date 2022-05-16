package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public abstract class Button {

    protected Texture texture;
    protected Rectangle border;
    protected float xMin, yMin, xMax, yMax;


    public Button(int x, int y) {
        Texture texture = new Texture("buttonExit.png");
        border = new Rectangle(x, y, texture.getWidth(), texture.getHeight());

        xMin = x;
        yMin = Gdx.graphics.getHeight() - y;
        xMax = (float) (x + border.width);
        yMin = Gdx.graphics.getHeight() - y + border.height;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, border.x, border.y, border.width, border.height);
    }

    public void update() {
        if (ifYouPress()) {
            operation();
        }
    }

    private boolean ifYouPress() {
        return Gdx.input.isTouched() && Gdx.input.getX() >= xMin && Gdx.input.getX() <= xMax && Gdx.input.getY() >= yMin && Gdx.input.getY() <= yMax;
    }

    public abstract void operation();

    public Rectangle getBorder() {
        return border;
    }
}
