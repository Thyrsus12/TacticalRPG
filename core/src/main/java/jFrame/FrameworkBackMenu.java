package jFrame;

import javax.swing.*;
import java.awt.*;

public class FrameworkBackMenu extends JFrame {
    private LaminateBackMenu laminateBackMenu;

    public FrameworkBackMenu() throws HeadlessException {
        setTitle("Arcadia");
        setBounds(500, 300, 600, 330);
        //Construction of central sheeting
        JPanel backgroundSheet = new JPanel();
        backgroundSheet.setLayout(new GridLayout(2, 1));

        laminateBackMenu = new LaminateBackMenu();

        setLayout(new BorderLayout());

        backgroundSheet.add(laminateBackMenu);

        JPanel exitMenu = new JPanel();

        JButton yes = new JButton("Generate");
        JButton no = new JButton("Generate");

        //yes.addActionListener();

        exitMenu.add(yes);
        exitMenu.add(no);

        add(exitMenu, BorderLayout.SOUTH);

        add(backgroundSheet, BorderLayout.CENTER);
    }
}
