package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public abstract class  GraphicalMenu implements ActionListener {
    JFrame menu;
    JMenu exit,back;
    JMenuBar menuBar;
    JPanel northPanel;
    GraphicalMenu parent;



    public GraphicalMenu (String menuName,GraphicalMenu parentMenu) {
        menu = new JFrame(menuName);
        this.parent = parentMenu;
        exit = new JMenu("exit");
        if (parentMenu != null) {
            back = new JMenu("back");
        }
        this.northPanel = new JPanel();
        menu.add(northPanel,BorderLayout.NORTH);


        Image loginIco;
        try {
            loginIco = ImageIO.read(new File("./data/icon.png"));
            menu.setIconImage(loginIco);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
