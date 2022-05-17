package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaminateJPanel extends JPanel {

    private JTextField text;
    private int numEnemies;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.drawString("Elige el numero de enemigos", 300, 100);
        //g.drawRect(50, 50, 200, 200);
    }

    public LaminateJPanel() {
        JLabel text1 = new JLabel("Elige el numero de enemigos");
        Font font = new Font("Arial", Font.ITALIC, 12);
        text1.setFont(font);
        add(text1);
        text = new JTextField(5);
        text.setLocation(300, 100);
        add(text);
        JButton button = new JButton("Generate");
        RetrieveText retrieveText = new RetrieveText();
        button.addActionListener(retrieveText);
        add(button);

    }

    private class RetrieveText implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            numEnemies = Integer.parseInt(text.getText().trim());
        }
    }
}
