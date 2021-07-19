package view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GraphicalGameMenu extends GraphicalMenu{
    JPanel instruction_panel, ground_panel;
    JTable gameMap;
    int selectedRow,selectedCol;

    public GraphicalGameMenu(GraphicalMenu parentMenu) {
        super("Game", parentMenu);
        instruction_panel = new JPanel();
        ground_panel = new JPanel();
        gameMap = new JTable(6,6);
        gameMap.setRowHeight(50);
//        gameMap.setPreferredSize(new Dimension(50,50));
        gameMap.setCellSelectionEnabled(true);
        ListSelectionModel select= gameMap.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        select.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                selectedRow = gameMap.getSelectedRow();
                selectedCol = gameMap.getSelectedColumn();
            }
        });
        ground_panel.add(gameMap,BorderLayout.CENTER);
        menu.add(ground_panel,BorderLayout.WEST);
    }

    @Override
    public void show() {
        menu.setSize(800,600);
        ground_panel.setSize(600,500);
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
        else if (e.getSource().equals(back)){
            hide();
            parent.show();
            menu.dispose();
        }
    }
}
