package utilities;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class ButtonPlay extends Button {

    public ButtonPlay(int x, int y) {
        super(x, y);
        texture = new Texture("buttonPlay.png");
    }

    @Override
    public void operation() {

    }
}
