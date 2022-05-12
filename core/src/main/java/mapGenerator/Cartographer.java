package mapGenerator;

import java.io.*;

public class Cartographer {
    String map;
    public void writeMap(){
        try {
            String ruta = new File("").getAbsolutePath() + "/assets/map.txt";
            FileWriter fileWriter = new FileWriter(ruta);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(freeze());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String freeze(){
        return map = "w g g g g g g w\n" +
                "m w w w w g g w\n" +
                "m g w g g g g w\n" +
                "m g g w l g g w\n" +
                "m g g w w g g w\n" +
                "m g g w g w g w\n" +
                "m g g g g g w w\n" +
                "m g g g g g w w";
    }
}
