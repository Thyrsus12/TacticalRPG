package jFrame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rotirmar.athena.Game;
import com.rotirmar.athena.GameScreen;
import com.rotirmar.athena.MenuScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Marco extends JFrame {
    private LaminateMapType laminateTypeMap;
    private LaminateMapSize laminateSizeMap;
    private LaminateCharacters laminateCharacters;
    private String typeMap;
    private int sizeMap;
    private ArrayList<Integer> numCharacters;
    private Boolean visible = true;

    public Marco() throws HeadlessException {
        setTitle("Arcadia");
        setBounds(500, 300, 600, 330);
        //Construccion de lamina central
        JPanel backgroundSheet = new JPanel();
        backgroundSheet.setLayout(new GridLayout(1, 1));
        String typeMap[] = {"Aleatorio", "Pradera", "Lava", "Nieve", "Desierto"};
        String sizeMap[] = {"8", "16", "24"};
        String characters[] = {"Mago", "Arquero", "Hoplita", "Medusa", "Quimera", "Hidra"};
        laminateTypeMap = new LaminateMapType("Tipo mapa", typeMap);
        laminateSizeMap = new LaminateMapSize("Tamaño mapa", sizeMap);
        laminateCharacters = new LaminateCharacters("Personajes", characters);

        setLayout(new BorderLayout());

        backgroundSheet.add(laminateTypeMap);
        backgroundSheet.add(laminateSizeMap);
        backgroundSheet.add(laminateCharacters);
        //backgroundSheet.add(laminateMonster);

        //Contruccion de lamina inferior
        JPanel generateMapSheet = new JPanel();

        JButton buttonGenerate = new JButton("Generate");

        buttonGenerate.addActionListener(new ActionGenerate(this));

        generateMapSheet.add(buttonGenerate);

        add(generateMapSheet, BorderLayout.SOUTH);

        add(backgroundSheet, BorderLayout.CENTER);
    }

    public class ActionGenerate implements ActionListener {

        Marco marco;

        public ActionGenerate(Marco marco) {
            this.marco = marco;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            typeMap = laminateTypeMap.giveSelectionMap();
            sizeMap = laminateSizeMap.giveSelectionMap();
            if (laminateCharacters.checkNumCharacters()) {
                numCharacters = laminateCharacters.giveSelectionCharacter();
                if (visible) {
                    marco.setVisible(false);
                    visible = false;
                }
                marco.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Máximo de personajes 10");
            }
        }
    }

    public String getTypeMap() {
        return typeMap;
    }

    public Boolean getVisible() {
        return visible;
    }

    public int getSizeMap() {
        return sizeMap;
    }

    public ArrayList<Integer> getNumCharacters() {
        return numCharacters;
    }
}

