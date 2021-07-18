package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public abstract class  GraphicalMenu implements ActionListener {
    JFrame menu;
    JButton exit,back;
    JMenuBar menuBar;
    JPanel northPanel;
    GraphicalMenu parent;



    public GraphicalMenu (String menuName,GraphicalMenu parentMenu) {
        menu = new JFrame(menuName);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.northPanel = new JPanel();
        northPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        this.parent = parentMenu;
        Icon exitIcon = new ImageIcon("./data/exit.png");
        exit = new JButton(exitIcon);
        exit.setSize(30,30);
        exit.addActionListener(this::actionPerformed);
//        exit.setOpaque(false);// must be checked
        northPanel.add(exit);
        if (parentMenu != null) {
            Icon backIcon = new ImageIcon("./data/back.png");
            back = new JButton(backIcon);
            back.setSize(30,30);
            back.addActionListener(this :: actionPerformed);
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
    public abstract void show();
    public abstract void hide();
}
