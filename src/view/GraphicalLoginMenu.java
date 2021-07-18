package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GraphicalLoginMenu extends GraphicalMenu{
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JTextField textUsername;
    private JPasswordField fieldPassword;
    private JButton buttonLogin,buttonSignUp;

    public GraphicalLoginMenu(GraphicalMenu parentMenu) {
        super("Login Menu", parentMenu);
        menu.setBackground(new Color(78, 32, 32, 255));
        /*try {
            BufferedImage bg = ImageIO.read(new File("./data/cover.jpeg"));
            menu.setContentPane(new ImagePanel(bg));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        labelPassword = new JLabel("Enter Password");
        labelUsername = new JLabel("Enter Username");
        textUsername = new JTextField(16);
        fieldPassword = new JPasswordField(16);
        Icon loginIcon = new ImageIcon("./data/login.png");
        buttonLogin = new JButton(loginIcon);
        Icon signUpIcon = new ImageIcon("./data/add.png");
        buttonSignUp = new JButton(signUpIcon);
        setButtonBackgroundTransparent(buttonLogin);
        setButtonBackgroundTransparent(buttonSignUp);
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(198, 118, 118, 255));
        buttonLogin.addActionListener(this::actionPerformed);
        buttonSignUp.addActionListener(this::actionPerformed);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10,10,10,10);
        constraints.gridwidth = 0;
//        constraints.gridx = 0;
        constraints.gridy = 0;
//        constraints.fill = 1;
        mainPanel.add(labelUsername, constraints);

        constraints.gridy = 1;
        mainPanel.add(textUsername, constraints);

//        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(labelPassword, constraints);

        constraints.gridy = 3;
        mainPanel.add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonLogin, constraints);
        constraints.gridx = 1;
        mainPanel.add(buttonSignUp, constraints);
        mainPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));
        menu.add(mainPanel);
        menu.pack();
        menu.setLocationRelativeTo(null);
    }

    @Override
    public void show() {

        menu.setSize(290,400);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
        mainPanel = new JPanel();
        menu.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setSize(260,300);
        mainPanel.setVisible(true);

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
        else if(e.getSource().equals(buttonLogin)){
            //todo
        }
        else if(e.getSource().equals(buttonSignUp)){
            //todo
        }
    }
}
