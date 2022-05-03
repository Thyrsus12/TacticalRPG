package utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RegionGiver {

    private static final int SIDE_SIZE = 32;

    public static TextureRegion getRegion(Boolean selected, String name) {
        Texture blocks;
        if (!selected)
            blocks = new Texture("blockTextures.png");
        else
            blocks = new Texture("blockTexturesSelected.png");

        switch (name) {
            case "halfDarkMarble":
                return new TextureRegion(blocks, SIDE_SIZE, SIDE_SIZE);
            case "grass":
                return new TextureRegion(blocks, 224, 96, SIDE_SIZE, SIDE_SIZE);
            case "flowers":
                return new TextureRegion(blocks, 192, 128, SIDE_SIZE, SIDE_SIZE);
            case "water":
                return new TextureRegion(blocks, 192, 160, SIDE_SIZE, SIDE_SIZE);
            case "lava":
                return new TextureRegion(blocks, 256, 160, SIDE_SIZE, SIDE_SIZE);
            default:
                break;
        }
        return null;
    }
}