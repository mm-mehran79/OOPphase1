package level;

import java.util.Scanner;

public class LevelInputProccessor {
    LevelManager levelManager;
    Scanner scanner;

    public LevelInputProccessor(LevelManager levelManager, Scanner scanner) {
        this.levelManager = levelManager;
        this.scanner = scanner;
    }

    public void run () {
        String input;
        String[] inputSplit;
        while ( !(input = scanner.nextLine()).equalsIgnoreCase("exit") ) {

        }
    }
}
