package jFrame;

import javax.swing.*;

public class LaminateMapSize extends JPanel {
    private JSpinner spinner;

    public LaminateMapSize(String titulo, String[] options) {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        spinner = new JSpinner(new SpinnerNumberModel(10, 8, 24, 1));
        add(spinner);

        for (int i = 0; i < 25; i++) {
            add(new JLabel(" "));
        }
    }

    public int giveSelectionMap() {
        return ((int) spinner.getValue());
    }
}
