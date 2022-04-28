package Utilies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Image {
    private Texture t;
    private Sprite s;

    public Image(String ruta) {
        t = new Texture(ruta);
        s = new Sprite(t);
    }

    public void draw() {
        s.draw(Render.batch);

    }

    public void setTransparent(float a) {
        s.setAlpha(a);
    }

    public void setSize(int width, int height) {
        s.setSize(width, height);
    }
}
