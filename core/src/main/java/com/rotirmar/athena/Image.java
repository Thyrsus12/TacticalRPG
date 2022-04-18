package com.rotirmar.athena;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Image {
    private Texture t;
    private Sprite s;
    private SpriteBatch batch;

    public Image(String ruta) {
        this.t = new Texture(ruta);
        this.s = new Sprite(t);
    }

    public void draw() {
        s.draw(batch);
    }

    public void setTransparent(float a) {
        s.setAlpha(a);
    }
}
