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
            case "blue":
                return new TextureRegion(blocks, 256, 192, SIDE_SIZE, SIDE_SIZE);
            case "darkLava":
                return new TextureRegion(blocks, 224, 160, SIDE_SIZE, SIDE_SIZE);
            case "mud":
                return new TextureRegion(blocks, 160, 96, SIDE_SIZE, SIDE_SIZE);
            case "mudGrass":
                return new TextureRegion(blocks, 192, 96, SIDE_SIZE, SIDE_SIZE);
            case "weedy":
                return new TextureRegion(blocks, 256, 96, SIDE_SIZE, SIDE_SIZE);
            case "herbs":
                return new TextureRegion(blocks, 288, 96, SIDE_SIZE, SIDE_SIZE);
            case "sand":
                return new TextureRegion(blocks, 160, 160, SIDE_SIZE, SIDE_SIZE);
            case "stonePath":
                return new TextureRegion(blocks, 288, 160, SIDE_SIZE, SIDE_SIZE);
            case "rocks":
                return new TextureRegion(blocks, 192, 192, SIDE_SIZE, SIDE_SIZE);
            case "bricks":
                return new TextureRegion(blocks, 224, 192, SIDE_SIZE, SIDE_SIZE);
            default:
                break;
        }
        return null;
    }
}