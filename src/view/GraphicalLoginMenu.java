package view;

import controller.LevelManager;
import controller.Manager;
import log.Log;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;

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
    public static void showLevelInformation(){
        String s;
        switch (Manager.getLevel()){
            case 1:
                s = "Initial Coin: 100 unit\n" +
                        "TASK:\n1. get 115 unit money\n2. product 1 egg\n3. own 1 chicken" +
                        "\nTurn limit is 20\n" +
                        "your prize if you win in specified turn : 50";
            break;
            case 2:
                s = "Initial Coin 450\n" +
                        "TASK:\n1. product 1 cloth\n2. product 1 feather\n3. own 1 turkey" +
                        "\nTurn limit is 20\n" +
                        "your prize if you win in specified turn : 50";
                break;
            case 3:
                s = "Initial Coin 100\n" +
                        "TASK:\n1. get 400 unit money\n2. defend your asset against lion" +
                        "\nTurn limit is 12\n" +
                        "your prize if you win in specified turn : 50";
                break;
            case 4:
                s = "Initial Coin 200\n" +
                        "TASK:\n1. defend your asset against different types of animals" +
                        "\nTurn limit is 1000\n" +
                        "your prize if you win in specified turn : 50";
                break;
            case 5:
                s = "Initial Coin 3800\n" +
                        "TASK:\n1. product 1 ice cream\n2. product 1 packet milk" +
                        "\nTurn limit is 1000\n" +
                        "your prize if you win in specified turn : 50";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + Manager.getLevel());
        }
        JOptionPane.showMessageDialog(new JFrame(),s,"level Task",JOptionPane.INFORMATION_MESSAGE,DialogIcons.INFORMATION);
    }

    @Override
    public void show() {

        menu.setSize(290,400);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
//        mainPanel = new JPanel();
//        menu.add(mainPanel,BorderLayout.CENTER);
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
//            GraphicalGameMenu graphicalGameMenu = new GraphicalGameMenu(this);
//            graphicalGameMenu.show();
            String username,password;
            User user;
            username = textUsername.getText();
            password = new String(fieldPassword.getPassword());
            if (User.available(username)){
                if((user = User.loadUser(username,password)) == null){
                    JOptionPane.showMessageDialog(new JFrame(),"login failed. Incorrect Password","Incorrect Password",JOptionPane.ERROR_MESSAGE,DialogIcons.ERROR_ICON);
                    fieldPassword.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(new JFrame(),"Welcome"+ username +"\nYour initial coin : "+user.getCoins()+"\nGo to play level "+user.getLastLevel(),"welcome!!",JOptionPane.INFORMATION_MESSAGE,DialogIcons.INFORMATION);
                    Manager.setPlayer(user);
                    Log.log(Log.LOG, user.userName + " signed in");
                    LevelManager levelManager = new LevelManager(Manager.getLevel(),Manager.getCoins());
                    hide();
                    showLevelInformation();
                    // input command
                    LevelInputProcessor levelInputProcessor = new LevelInputProcessor(levelManager, new Scanner(System.in));
                    int k = levelInputProcessor.run();
                    if (k >= 0){
                        user.giveReward(k);
                        User.saveUser(user);
                        JOptionPane.showMessageDialog(new JFrame(),"you have completed this level","Level Completed",JOptionPane.INFORMATION_MESSAGE,buttonLogin.getIcon());
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(new JFrame(),"login failed. Incorrect username","user doesn't exist",JOptionPane.ERROR_MESSAGE,DialogIcons.ERROR_ICON);
                textUsername.setText("");fieldPassword.setText("");
            }
        }
        else if(e.getSource().equals(buttonSignUp)){
            String username,password;
            User user;
            username = textUsername.getText();
            password = new String(fieldPassword.getPassword());

            if (User.available(username)){
                JOptionPane.showMessageDialog(new JFrame(),"This username is already taken, please enter another username","Username error",JOptionPane.ERROR_MESSAGE,DialogIcons.ERROR_ICON);
                textUsername.setText("");fieldPassword.setText("");
            }
            else {
                user = new User(username,password);
                JOptionPane.showMessageDialog(new JFrame(),"wellcome "+username+"\nPlay 1st level...","wellcome!!",JOptionPane.INFORMATION_MESSAGE,buttonLogin.getIcon());
                textUsername.setText("");fieldPassword.setText("");
                Manager.setPlayer(user);
                Log.log(Log.LOG, user.userName + " signed in");
                hide();
                showLevelInformation();
                LevelManager levelManager = new LevelManager(Manager.getLevel(),Manager.getCoins());
                LevelInputProcessor levelInputProcessor = new LevelInputProcessor(levelManager, new Scanner(System.in));
                int k = levelInputProcessor.run();
                if (k >= 0){
                    user.giveReward(k);
                    User.saveUser(user);
                    JOptionPane.showMessageDialog(new JFrame(),"you have completed this level","Level Completed",JOptionPane.INFORMATION_MESSAGE,buttonLogin.getIcon());
                }
            }
        }
    }
}
