package mapGenerator;

import java.io.*;
import java.util.Random;

public class Cartographer {
    Random r = new Random();
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
        int river = -1;
        river = r.nextInt(8)-1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (river==i && r.nextInt(10)<4){
                    snowMap = snowMap.concat("w");
                } else {
                    if (r.nextInt(10)<4){
                        snowMap = snowMap.concat("i");
                    } else {
                        if (r.nextInt(10)<2) {
                            snowMap = snowMap.concat("n");
                        } else {
                            snowMap = snowMap.concat("f");
                        }
                    }
                }

                if (j<7)
                    snowMap = snowMap.concat(" ");
            }
            if (i<7)
                snowMap = snowMap.concat("\n");
        }
        return snowMap;
    }
}
