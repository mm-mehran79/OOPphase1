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
        this.northPanel = new JPanel();
        this.parent = parentMenu;
        exit = new JMenu("exit");
        exit.addActionListener(this::actionPerformed);
        Icon exitIcon = new ImageIcon("./data/exit.png");
        exit.setIcon(exitIcon);
        exit.setOpaque(false);// must
        if (parentMenu != null) {
            back = new JMenu("back");
            back.addActionListener(this :: actionPerformed);
            Icon backIcon = new ImageIcon("./data/back.png");
            back.setIcon(backIcon);
            northPanel.add(back);
        }

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
