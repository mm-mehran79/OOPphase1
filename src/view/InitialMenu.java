package view;

public class InitialMenu extends Menu {
    public InitialMenu(){
        super("Initial Menu");
        submenus.put(1,new LoginMenu(this));
        submenus.put(2,new SignUp(this));
    }

}
