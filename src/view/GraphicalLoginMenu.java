package view;

import java.awt.event.ActionEvent;

public class GraphicalLoginMenu extends GraphicalMenu{
    public GraphicalLoginMenu(GraphicalMenu parentMenu) {
        super("Login Menu", parentMenu);
    }

    @Override
    public void show() {
        menu.setSize(400,600);
        menu.setVisible(true);
    }

    @Override
    public void hide() {
        menu.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit){
            System.exit(0);
        }
    }
}
