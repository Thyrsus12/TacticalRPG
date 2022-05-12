package mapGenerator;

import java.io.*;

public class Cartographer {
    String map;
    public void writeMap(){
        try {
            String ruta = new File("").getAbsolutePath() + "/assets/map.txt";
            FileWriter fileWriter = new FileWriter(ruta);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(writeSnowMap());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String writeMapString(){
        return map = "w g g g g g g w\n" +
                "m w w w w g g w\n" +
                "m g w g g g g w\n" +
                "m g g w l g g w\n" +
                "m g g w w g g w\n" +
                "m g g w g w g w\n" +
                "m g g g g g w w\n" +
                "m g g f i g w w";
    }

    public String writeSnowMap(){
        String snowMap = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                snowMap.concat("f");
                snowMap.concat(" ");
            }
            snowMap.concat("\n");
        }
        return snowMap;
    }
}
