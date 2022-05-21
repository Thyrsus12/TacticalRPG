package jFrame;

import javax.swing.*;
import java.util.ArrayList;

public class LaminateCharacters extends JPanel {
    private JLabel name;
    private ArrayList<JSpinner> spinner;

    public LaminateCharacters(String titulo, String[] options) {
        spinner = new ArrayList<>();
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        for (int i = 0; i < options.length; i++) {
            name = new JLabel(options[i]);
            add(name);
            spinner.add(new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)));
            //spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
            add(spinner.get(i));
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
