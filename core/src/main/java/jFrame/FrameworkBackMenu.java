package jFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameworkBackMenu extends JFrame {
    private LaminateBackMenu laminateBackMenu;

    public FrameworkBackMenu() throws HeadlessException {
        setBounds(850, 400, 200, 120);
        //Construction of central sheeting
        JPanel backgroundSheet = new JPanel();
        backgroundSheet.setLayout(new GridLayout(2, 1));

        laminateBackMenu = new LaminateBackMenu();

        setLayout(new BorderLayout());

        backgroundSheet.add(laminateBackMenu);

        JPanel exitMenu = new JPanel();

        JButton yes = new JButton("SI");
        JButton no = new JButton("NO");

        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        no.addActionListener(new ActionButtonNo(this));

        exitMenu.add(yes);
        exitMenu.add(no);

        add(exitMenu, BorderLayout.SOUTH);

        add(backgroundSheet, BorderLayout.CENTER);
    }

    public class ActionButtonNo implements ActionListener {

        FrameworkBackMenu marco;

        public ActionButtonNo(FrameworkBackMenu marco) {
            this.marco = marco;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            marco.dispose();
        }
    }
}
