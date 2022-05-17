package utilities;

import javax.swing.*;
import java.awt.*;

public class MenuJFrame extends JFrame {
    public MenuJFrame() throws HeadlessException {
        Toolkit display = Toolkit.getDefaultToolkit();
        Dimension displaySize = display.getScreenSize();
        //Este metodo le da el tamaño y la posicion a la ventana
        setBounds(displaySize.width / 4, displaySize.height / 4, displaySize.width / 2, displaySize.height / 2);
        //Este metodo permite o no que se pueda maximizar o no la ventana
        setResizable(false);
        setTitle("Athena");

        LaminateJPanel laminate = new LaminateJPanel();
        add(laminate);

    }

}
