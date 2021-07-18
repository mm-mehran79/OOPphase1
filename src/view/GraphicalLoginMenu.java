package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GraphicalLoginMenu extends GraphicalMenu{
    private TextField usernameTextField , passTextField;

    public GraphicalLoginMenu(GraphicalMenu parentMenu) {
        super("Login Menu", parentMenu);

    }

    @Override
    public void show() {
        menu.setSize(400,600);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }

    @Override
    public void hide() {
        menu.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(exit)){
            System.exit(0);
        }

    }
}
