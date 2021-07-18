package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public abstract class  GraphicalMenu implements ActionListener {
    JFrame menu;
    JButton exit,back;
    JPanel northPanel;
    JPanel mainPanel;
    GraphicalMenu parent;



    public GraphicalMenu (String menuName,GraphicalMenu parentMenu) {
        menu = new JFrame(menuName);
        menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.northPanel = new JPanel();
        northPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.parent = parentMenu;
        Icon exitIcon = new ImageIcon("./data/exit.png");
        exit = new JButton(exitIcon);//LayoutManager layoutManager =new BoxLayout()
        exit.setSize(30,30);
        exit.addActionListener(this::actionPerformed);
        setButtonBackgroundTransparent(exit);
        northPanel.add(exit,FlowLayout.LEFT);
        if (parentMenu != null) {
            Icon backIcon = new ImageIcon("./data/back.png");
            back = new JButton(backIcon);
            back.setSize(30,30);
            setButtonBackgroundTransparent(back);
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
    public static void setButtonBackgroundTransparent(JButton button){
        if (button == null) return;
        button.setBorder(new LineBorder(Color.BLACK,0));
        button.setOpaque(false);// must be checked
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }
}
