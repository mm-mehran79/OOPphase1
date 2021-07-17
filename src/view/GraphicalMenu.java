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
    JPanel northPanel,bodyPanel;
    GraphicalMenu parent;



    public GraphicalMenu (String menuName,GraphicalMenu parentMenu) {
        menu = new JFrame(menuName);
        menu.setLayout(new BorderLayout());

        this.northPanel = new JPanel();
        this.bodyPanel = new JPanel();
        this.parent = parentMenu;

        Icon exitIcon = new ImageIcon("./data/exit.png");
        exit = new JButton(exitIcon);
        exit.addActionListener(this::actionPerformed);
        exit.setOpaque(false);// must be checked
        exit.setSize(70,20);
        northPanel.add(exit);
        if (parentMenu == null) {
            Icon backIcon = new ImageIcon("./data/back.png");
            back = new JButton(backIcon);
            back.addActionListener(this :: actionPerformed);
            back.setSize(20,20);
            northPanel.add(back);
        }

        menu.add(northPanel,BorderLayout.NORTH);
        menu.add(bodyPanel,BorderLayout.CENTER);


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
