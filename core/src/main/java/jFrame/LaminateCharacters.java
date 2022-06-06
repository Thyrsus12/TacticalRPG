package jFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LaminateCharacters extends JPanel {
    private JLabel labelIcon;
    private ArrayList<JSpinner> spinner;
    private Image image;

    public LaminateCharacters(String titulo, String[] options) {
        spinner = new ArrayList<>();
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        labelIcon = new JLabel();

        for (int i = 0; i < options.length; i++) {
            labelIcon = new JLabel(new ImageIcon(options[i]));
            add(labelIcon);
            spinner.add(new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)));
            add(spinner.get(i));
        }
    }

    public Boolean checkNumCharacters() {
        int totalCharacters = 0;
        for (int i = 0; i < spinner.size(); i++) {
            totalCharacters += (int) spinner.get(i).getValue();
        }
        if (totalCharacters > 10) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Integer> giveSelectionCharacter() {
        ArrayList<Integer> numCharacters = new ArrayList<>();
        for (int i = 0; i < spinner.size(); i++) {
            numCharacters.add((int) spinner.get(i).getValue());
        }
        return numCharacters;
    }
}
