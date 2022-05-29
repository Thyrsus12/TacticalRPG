package jFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameworkMenu extends JFrame {
    private LaminateMapType laminateTypeMap;
    private LaminateMapSize laminateSizeMap;
    private LaminateCharacters laminateCharacters1;
    private LaminateCharacters laminateCharacters2;

    private String typeMap;
    private int sizeMap;

    private ArrayList<Integer> numCharacters, numCharacters2;

    public static Boolean generated = false;

    public FrameworkMenu(int screenWidth, int screenHeight) throws HeadlessException {
        setTitle("Arcadia");
        setAlwaysOnTop(true);
        int width = (int) (screenWidth / 1.9);
        int height = (int) (screenHeight / 1.9);
        setBounds(screenWidth / 4, screenHeight / 4, width, height);
        //Construction of central sheeting
        JPanel backgroundSheet = new JPanel();
        backgroundSheet.setLayout(new GridLayout(1, 1));

        String typeMap[] = {"Aleatorio", "Pradera", "Lava", "Nieve", "Desierto"};
        String sizeMap[] = {"8", "16", "24"};
        String characters[] = {"Mago", "Arquero", "Hoplita", "Medusa", "Quimera", "Hidra"};

        laminateTypeMap = new LaminateMapType("Tipo mapa", typeMap);
        laminateSizeMap = new LaminateMapSize("Tamaño mapa", sizeMap);
        laminateCharacters1 = new LaminateCharacters("Equipo1", characters);
        laminateCharacters2 = new LaminateCharacters("Equipo2", characters);

        setLayout(new BorderLayout());

        backgroundSheet.add(laminateTypeMap);
        backgroundSheet.add(laminateSizeMap);
        backgroundSheet.add(laminateCharacters1);
        backgroundSheet.add(laminateCharacters2);

        //Construction of bottom plate
        JPanel generateMapSheet = new JPanel();

        JButton buttonGenerate = new JButton("Generate");

        buttonGenerate.addActionListener(new ActionGenerate(this));

        generateMapSheet.add(buttonGenerate);

        add(generateMapSheet, BorderLayout.SOUTH);

        add(backgroundSheet, BorderLayout.CENTER);
    }

    public class ActionGenerate implements ActionListener {

        FrameworkMenu marco;

        public ActionGenerate(FrameworkMenu marco) {
            this.marco = marco;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            typeMap = laminateTypeMap.giveSelectionMap();
            sizeMap = laminateSizeMap.giveSelectionMap();

            if (laminateCharacters1.checkNumCharacters() && laminateCharacters2.checkNumCharacters()) {
                numCharacters = laminateCharacters1.giveSelectionCharacter();
                numCharacters2 = laminateCharacters2.giveSelectionCharacter();
                marco.setVisible(false);
                generated = true;
                marco.dispose();
            } else {
                JOptionPane.showMessageDialog(marco, "Máximo de personajes 10");
            }
        }
    }

    public String getTypeMap() {
        return typeMap;
    }

    public Boolean getGenerated() {
        return generated;
    }

    public void setGenerated(Boolean jFrameVisible) {
        this.generated = jFrameVisible;
    }

    public int getSizeMap() {
        return sizeMap;
    }

    public ArrayList<Integer> getNumCharacters() {
        return numCharacters;
    }

    public ArrayList<Integer> getNumCharacters2() {
        return numCharacters2;
    }
}

