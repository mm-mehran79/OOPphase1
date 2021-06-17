package view;

import controller.LevelManager;

import java.util.Scanner;

public class LevelInputProcessor {
    boolean levelFinished = false;
    LevelManager levelManager;
    Scanner scanner;

    public LevelInputProcessor(LevelManager levelManager, Scanner scanner) {
        this.levelManager = levelManager;
        this.scanner = scanner;
    }

    private void processBuy(String input) {
        if (input.matches("buy chick")) {
            levelManager.addInstruction("buy chicken");
            return;
        }
        else if (input.matches("buy turk")) {
            levelManager.addInstruction("buy turkey");
            return;
        }
        else if (input.matches("buy buf")) {
            levelManager.addInstruction("buy buffalo");
            return;
        }
        else if (input.matches("buy dog")) {
            levelManager.addInstruction("buy dog");
            return;
        }
        else if (input.matches("buy cat")) {
            levelManager.addInstruction("buy cat");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (buy regex)");
    }

    private void processBuild(String input) {
        if (input.matches("build fl")) {
            levelManager.addInstruction("build flour");
            return;
        }
        else if (input.matches("build cl")) {
            levelManager.addInstruction("build cloth");
            return;
        }
        else if (input.matches("build pa")) {
            levelManager.addInstruction("build packetmilk");
            return;
        }
        else if (input.matches("build br")) {
            levelManager.addInstruction("build bread");
            return;
        }
        else if (input.matches("build sh")) {
            levelManager.addInstruction("build shirt");
            return;
        }
        else if (input.matches("build ic")) {
            levelManager.addInstruction("build icecream");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (build regex)");
    }

    private void processPickUp(String input) {
        if (input.matches("pickup \\d \\d")) {
            String[] inputSplit = input.split("\\s");
            int x = Integer.parseInt(inputSplit[1]);
            int y = Integer.parseInt(inputSplit[2]);
            if (0 < x && x < 7 && 0 < y && y < 7) {
                x--;
                y--;
                String instruction = "pickup " + x + " " + y;
                levelManager.addInstruction(instruction);
                return;
            }
            else {
                System.err.println("Invalid input, Please try again" + " (pickup bounds)");
                return;
            }
        }
        System.err.println("Invalid input, Please try again" + " (pickup)");
    }

    private void processWell(String input) {
        levelManager.addInstruction("well");
    }

    private void processPlant(String input) {
        if (input.matches("plant \\d \\d")) {
            String[] inputSplit = input.split("\\s");
            int x = Integer.parseInt(inputSplit[1]);
            int y = Integer.parseInt(inputSplit[2]);
            if (0 < x && x < 7 && 0 < y && y < 7) {
                x--;
                y--;
                String instruction = "plant " + x + " " + y;
                levelManager.addInstruction(instruction);
                return;
            }
            else {
                System.err.println("Invalid input, Please try again" + " (plant bounds)");
                return;
            }
        }
        System.err.println("Invalid input, Please try again" + " (plant regex)");
    }

    private void processWork(String input) {
        if (input.matches("work fl")) {
            levelManager.addInstruction("work flour");
            return;
        }
        else if (input.matches("work cl")) {
            levelManager.addInstruction("work cloth");
            return;
        }
        else if (input.matches("work pa")) {
            levelManager.addInstruction("work packetmilk");
            return;
        }
        else if (input.matches("work br")) {
            levelManager.addInstruction("work bread");
            return;
        }
        else if (input.matches("work sh")) {
            levelManager.addInstruction("work shirt");
            return;
        }
        else if (input.matches("work ic")) {
            levelManager.addInstruction("work icecream");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (work regex)");
    }

    private void processCage(String input) {
        if (input.matches("cage \\d \\d")) {
            String[] inputSplit = input.split("\\s");
            int x = Integer.parseInt(inputSplit[1]);
            int y = Integer.parseInt(inputSplit[2]);
            if (0 < x && x < 7 && 0 < y && y < 7) {
                x--;
                y--;
                String instruction = "cage " + x + " " + y;
                levelManager.addInstruction(instruction);
                return;
            }
            else {
                System.err.println("Invalid input, Please try again" + " (cage bounds)");
                return;
            }
        }
        System.err.println("Invalid input, Please try again" + " (pickup regex)");
    }

    private void processTurn(String input) {
        if (input.matches("turn \\d+")) {
            String[] inputSplit = input.split("\\s");
            int turn = Integer.parseInt(inputSplit[1]);
            levelManager.addInstruction("turn " + turn);
            return;
        }
        System.err.println("Invalid input, Please try again" + " (turn regex)");
    }

    private void processTruckLoad(String input) {
        if (input.matches("truck load fl")) {
            levelManager.addInstruction("truck load flour");
            return;
        }
        else if (input.matches("truck load cl")) {
            levelManager.addInstruction("truck load cloth");
            return;
        }
        else if (input.matches("truck load pa")) {
            levelManager.addInstruction("truck load packetmilk");
            return;
        }
        else if (input.matches("truck load br")) {
            levelManager.addInstruction("truck load bread");
            return;
        }
        else if (input.matches("truck load sh")) {
            levelManager.addInstruction("truck load shirt");
            return;
        }
        else if (input.matches("truck load ic")) {
            levelManager.addInstruction("truck load icecream");
            return;
        }
        else if (input.matches("truck load mi")) {
            levelManager.addInstruction("truck load milk");
            return;
        }
        else if (input.matches("truck load fe")) {
            levelManager.addInstruction("truck load feather");
            return;
        }
        else if (input.matches("truck load eg")) {
            levelManager.addInstruction("truck load egg");
            return;
        }
        else if (input.matches("truck load li")) {
            levelManager.addInstruction("truck load lion");
            return;
        }
        else if (input.matches("truck load be")) {
            levelManager.addInstruction("truck load bear");
            return;
        }
        else if (input.matches("truck load ti")) {
            levelManager.addInstruction("truck load tiger");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (truck load regex)");
    }

    private void processTruckUnload(String input) {
        if (input.matches("truck unload fl")) {
            levelManager.addInstruction("truck unload flour");
            return;
        }
        else if (input.matches("truck unload cl")) {
            levelManager.addInstruction("truck unload cloth");
            return;
        }
        else if (input.matches("truck unload pa")) {
            levelManager.addInstruction("truck unload packetmilk");
            return;
        }
        else if (input.matches("truck unload br")) {
            levelManager.addInstruction("truck unload bread");
            return;
        }
        else if (input.matches("truck unload sh")) {
            levelManager.addInstruction("truck unload shirt");
            return;
        }
        else if (input.matches("truck unload ic")) {
            levelManager.addInstruction("truck unload icecream");
            return;
        }
        else if (input.matches("truck unload mi")) {
            levelManager.addInstruction("truck unload milk");
            return;
        }
        else if (input.matches("truck unload fe")) {
            levelManager.addInstruction("truck unload feather");
            return;
        }
        else if (input.matches("truck unload eg")) {
            levelManager.addInstruction("truck unload egg");
            return;
        }
        else if (input.matches("truck unload li")) {
            levelManager.addInstruction("truck unload lion");
            return;
        }
        else if (input.matches("truck unload be")) {
            levelManager.addInstruction("truck unload bear");
            return;
        }
        else if (input.matches("truck unload ti")) {
            levelManager.addInstruction("truck unload tiger");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (truck unload regex)");
    }

    private void processTruckGo(String input) {
        levelManager.addInstruction("truck go");
    }

    private void processInquiry(String input) {
        levelManager.inquiry();
    }

    private boolean processCheckFinished() {
        return false;
    }

    public boolean run () {
        // needed vars
        String input;
        String[] inputSplit;

        // case handling
        while ( !(input = scanner.nextLine()).equalsIgnoreCase("exit level") && !levelFinished ) {
            String inputToLowerCase = input.toLowerCase();
            if ( inputToLowerCase.startsWith("buy") )
                processBuy(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("build") )
                processBuild(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("pick") )
                processPickUp(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("well") )
                processWell(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("plant") )
                processPlant(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("work") )
                processWork(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("cage") )
                processCage(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("turn") )
                processTurn(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("truck load") )
                processTruckLoad(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("truck unload") )
                processTruckUnload(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("truck go") )
                processTruckGo(inputToLowerCase);
            else if ( inputToLowerCase.startsWith("inquiry") )
                processInquiry(inputToLowerCase);
            else
                System.err.println("Invalid input, Please try again");

            levelFinished = processCheckFinished();
        }

        return levelFinished;
    }

    public boolean isLevelFinished() {
        return levelFinished;
    }

}
