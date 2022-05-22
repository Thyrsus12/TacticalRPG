package jFrame;

import javax.swing.*;

public class LaminateMapType extends JPanel {
    private ButtonGroup group;

    public LaminateMapType(String titulo, String[] options) {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        group = new ButtonGroup();

        for (int i = 0; i < options.length; i++) {
            JRadioButton radioButton = new JRadioButton(options[i]);
            radioButton.setActionCommand(options[i]);
            add(radioButton);
            group.add(radioButton);
            radioButton.setSelected(i == 0);
        }
    }

    public String giveSelectionMap() {
        return group.getSelection().getActionCommand();
    }
}
