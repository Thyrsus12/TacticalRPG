package mapGenerator;

import mapTileByTile.TileMap;

import java.io.*;
import java.util.Random;

public class Cartographer {
    Random r = new Random();
    String map;
    Integer mapSize = TileMap.mapSize;

    public void writeMap(String mapType) {
        try {
            String ruta = new File("").getAbsolutePath() + "/assets/map.txt";
            FileWriter fileWriter = new FileWriter(ruta);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            selectType(mapType, bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String generateRandomMap() {
        switch (r.nextInt(4)) {
            case 0:
                return writeSnowMap();
            case 1:
                return writeDesertMap();
            case 2:
                return writeLavaMap();
            case 3:
                return writeGrassMap();
        }
        return null;
    }

    public String writeSnowMap() {
        String snowMap = "";
        int river = r.nextInt(mapSize) - 1;

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (river == i && r.nextInt(10) < 4) {
                    snowMap = snowMap.concat("w");
                } else {
                    if (r.nextInt(10) < 4) {
                        snowMap = snowMap.concat("i");
                    } else {
                        if (r.nextInt(10) < 2) {
                            snowMap = snowMap.concat("h");
                        } else {
                            snowMap = snowMap.concat("f");
                        }
                    }
                }
                if (j < mapSize - 1)
                    snowMap = snowMap.concat(" ");
            }
            if (i < mapSize - 1)
                snowMap = snowMap.concat("\n");
        }
        return snowMap;
    }

    public String writeLavaMap() {
        String lavaMap = "";
        int river = r.nextInt(mapSize) - 1;

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (river == i && r.nextInt(10) < 4) {
                    lavaMap = lavaMap.concat("l");
                } else {
                    if (r.nextInt(10) < 4) {
                        lavaMap = lavaMap.concat("y");
                    } else {
                        if (r.nextInt(10) < 2) {
                            lavaMap = lavaMap.concat("p");
                        } else {
                            lavaMap = lavaMap.concat("r");
                        }
                    }
                }
                if (j < mapSize - 1)
                    lavaMap = lavaMap.concat(" ");
            }
            if (i < mapSize - 1)
                lavaMap = lavaMap.concat("\n");
        }
        return lavaMap;
    }

    public String writeGrassMap() {
        String grassMap = "";
        int river = r.nextInt(mapSize) - 1;

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (river == i && r.nextInt(10) < 4) {
                    grassMap = grassMap.concat("w");
                } else {
                    if (r.nextInt(10) < 4) {
                        grassMap = grassMap.concat("v");
                    } else {
                        if (r.nextInt(10) < 2) {
                            grassMap = grassMap.concat("h");
                        } else {
                            if (r.nextInt(10) < 2) {
                                grassMap = grassMap.concat("n");
                            } else {
                                grassMap = grassMap.concat("g");
                            }
                        }
                    }
                }
                if (j < mapSize - 1)
                    grassMap = grassMap.concat(" ");
            }
            if (i < mapSize - 1)
                grassMap = grassMap.concat("\n");
        }
        return grassMap;
    }

    public String writeDesertMap() {
        String desertMap = "";
        int river = r.nextInt(mapSize) - 1;

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (river == i && r.nextInt(10) < 4) {
                    if (r.nextInt(10) < 2) {
                        desertMap = desertMap.concat("n");
                    } else {
                        desertMap = desertMap.concat("w");
                    }
                } else {
                    if (r.nextInt(10) < 2) {
                        desertMap = desertMap.concat("m");
                    } else {
                        desertMap = desertMap.concat("s");
                    }
                }
                if (j < mapSize - 1)
                    desertMap = desertMap.concat(" ");
            }
            if (i < mapSize - 1)
                desertMap = desertMap.concat("\n");
        }
        return desertMap;
    }

    private void selectType(String mapType, BufferedWriter bufferedWriter) {
        try {
            switch (mapType) {
                case "Aleatorio":
                    bufferedWriter.write(generateRandomMap());
                    break;
                case "Pradera":
                    bufferedWriter.write(writeGrassMap());
                    break;
                case "Lava":
                    bufferedWriter.write(writeLavaMap());
                    break;
                case "Nieve":
                    bufferedWriter.write(writeSnowMap());
                    break;
                case "Desierto":
                    bufferedWriter.write(writeDesertMap());
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String writeMockMap() {
        String mockMap = "";
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (r.nextInt(10) < 5) {
                    mockMap = mockMap.concat("s");
                } else {
                    mockMap = mockMap.concat("w");
                }
                if (j < mapSize - 1)
                    mockMap = mockMap.concat(" ");
            }
            if (i < mapSize - 1)
                mockMap = mockMap.concat("\n");
        }
        return mockMap;
    }

}
