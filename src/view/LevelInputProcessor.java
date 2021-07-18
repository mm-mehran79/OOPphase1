package view;

import controller.LevelManager;
import log.Log;

import java.util.Scanner;

public class LevelInputProcessor {
    boolean levelFinished = false;
    LevelManager levelManager;
    Scanner scanner;

    public LevelInputProcessor(LevelManager levelManager, Scanner scanner) {
        this.levelManager = levelManager;
        this.scanner = scanner;
        Log.log(Log.INFO, "LevelInputProcessor constructor");
        levelFinished = false;
    }

    private void processBuy(String input) {
        if (input.startsWith("buy chick")) {
            levelManager.addInstruction("buy chicken");
            Log.log(Log.INFO, "instructionQueue buy chicken successfully");
            return;
        }
        else if (input.startsWith("buy turk")) {
            levelManager.addInstruction("buy turkey");
            Log.log(Log.INFO, "instructionQueue buy turkey successfully");
            return;
        }
        else if (input.startsWith("buy buf")) {
            levelManager.addInstruction("buy buffalo");
            Log.log(Log.INFO, "instructionQueue buy buffalo successfully");
            return;
        }
        else if (input.startsWith("buy dog")) {
            levelManager.addInstruction("buy dog");
            Log.log(Log.INFO, "instructionQueue buy dog successfully");
            return;
        }
        else if (input.startsWith("buy cat")) {
            levelManager.addInstruction("buy cat");
            Log.log(Log.INFO, "instructionQueue buy cat successfully");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (buy regex)");
        Log.log(Log.INFO, "instructionQueue buy unsuccessfully");
    }

    private void processBuild(String input) {
        if (input.startsWith("build fl")) {
            levelManager.addInstruction("build flour");
            return;
        }
        else if (input.startsWith("build cl")) {
            levelManager.addInstruction("build cloth");
            return;
        }
        else if (input.startsWith("build pa")) {
            levelManager.addInstruction("build packetmilk");
            return;
        }
        else if (input.startsWith("build br")) {
            levelManager.addInstruction("build bread");
            return;
        }
        else if (input.startsWith("build sh")) {
            levelManager.addInstruction("build shirt");
            return;
        }
        else if (input.startsWith("build ic")) {
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
            if (0 <= x && x < 7 && 0 <= y && y < 7) {
                //x--;
                //y--;
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
            if (0 <= x && x < 7 && 0 <= y && y < 7) {
                //x--;
                //y--;
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
        if (input.startsWith("work fl")) {
            levelManager.addInstruction("work flour");
            return;
        }
        else if (input.startsWith("work cl")) {
            levelManager.addInstruction("work cloth");
            return;
        }
        else if (input.startsWith("work pa")) {
            levelManager.addInstruction("work packetmilk");
            return;
        }
        else if (input.startsWith("work br")) {
            levelManager.addInstruction("work bread");
            return;
        }
        else if (input.startsWith("work sh")) {
            levelManager.addInstruction("work shirt");
            return;
        }
        else if (input.startsWith("work ic")) {
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
            if (0 <= x && x < 7 && 0 <= y && y < 7) {
                //x--;
                //y--;
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

    // old
    /*private void processTurn(String input) {
        if (input.matches("turn \\d+")) {
            String[] inputSplit = input.split(" ");
            int turn = Integer.parseInt(inputSplit[1]);
            levelManager.addInstruction("turn " + turn);
            return;
        }
        else if (input.matches("turn")) {
            levelManager.addInstruction("turn ");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (turn regex)");
    }*/

    // new
    private void processTurn(String input) {// processTurn(inputToLowerCase);
        if (input.matches("turn \\d+")) {
            String[] inputSplit = input.split(" ");
            int turn = Integer.parseInt(inputSplit[1]);
            levelManager.turnN(turn);
            /*System.out.println(levelManager.getInstructionQueue());
            levelManager.getInstructionQueue().clear();*/
        }
        else if (input.matches("turn")) {
            levelManager.turnN(1);
            /*System.out.println(levelManager.getInstructionQueue());
            levelManager.getInstructionQueue().clear();*/
        }
        else
            System.err.println("Invalid input, Please try again" + " (turn regex)");
    }

    private void processTruckLoad(String input) {
        if (input.startsWith("truck load fl")) {
            levelManager.addInstruction("truck load flour");
            return;
        }
        else if (input.startsWith("truck load cl")) {
            levelManager.addInstruction("truck load cloth");
            return;
        }
        else if (input.startsWith("truck load pa")) {
            levelManager.addInstruction("truck load packetmilk");
            return;
        }
        else if (input.startsWith("truck load br")) {
            levelManager.addInstruction("truck load bread");
            return;
        }
        else if (input.startsWith("truck load sh")) {
            levelManager.addInstruction("truck load shirt");
            return;
        }
        else if (input.startsWith("truck load ic")) {
            levelManager.addInstruction("truck load icecream");
            return;
        }
        else if (input.startsWith("truck load mi")) {
            levelManager.addInstruction("truck load milk");
            return;
        }
        else if (input.startsWith("truck load fe")) {
            levelManager.addInstruction("truck load feather");
            return;
        }
        else if (input.startsWith("truck load eg")) {
            levelManager.addInstruction("truck load egg");
            return;
        }
        else if (input.startsWith("truck load li")) {
            levelManager.addInstruction("truck load lion");
            return;
        }
        else if (input.startsWith("truck load be")) {
            levelManager.addInstruction("truck load bear");
            return;
        }
        else if (input.startsWith("truck load ti")) {
            levelManager.addInstruction("truck load tiger");
            return;
        }
        System.err.println("Invalid input, Please try again" + " (truck load regex)");
    }

    private void processTruckUnload(String input) {
        if (input.startsWith("truck unload fl")) {
            levelManager.addInstruction("truck unload flour");
            return;
        }
        else if (input.startsWith("truck unload cl")) {
            levelManager.addInstruction("truck unload cloth");
            return;
        }
        else if (input.startsWith("truck unload pa")) {
            levelManager.addInstruction("truck unload packetmilk");
            return;
        }
        else if (input.startsWith("truck unload br")) {
            levelManager.addInstruction("truck unload bread");
            return;
        }
        else if (input.startsWith("truck unload sh")) {
            levelManager.addInstruction("truck unload shirt");
            return;
        }
        else if (input.startsWith("truck unload ic")) {
            levelManager.addInstruction("truck unload icecream");
            return;
        }
        else if (input.startsWith("truck unload mi")) {
            levelManager.addInstruction("truck unload milk");
            return;
        }
        else if (input.startsWith("truck unload fe")) {
            levelManager.addInstruction("truck unload feather");
            return;
        }
        else if (input.startsWith("truck unload eg")) {
            levelManager.addInstruction("truck unload egg");
            return;
        }
        else if (input.startsWith("truck unload li")) {
            levelManager.addInstruction("truck unload lion");
            return;
        }
        else if (input.startsWith("truck unload be")) {
            levelManager.addInstruction("truck unload bear");
            return;
        }
        else if (input.startsWith("truck unload ti")) {
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

    public int run () {
        // needed vars
        String input;
        Log.log(Log.INFO, "LevelInputProcessor run");

        // case handling
        boolean getInput = true;
        while (getInput) {
            // handling inputs
            while ( !(input = scanner.nextLine()).startsWith("exit level") ) {
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
                else if ( inputToLowerCase.startsWith("truck load") )
                    processTruckLoad(inputToLowerCase);
                else if ( inputToLowerCase.startsWith("truck unload") )
                    processTruckUnload(inputToLowerCase);
                else if ( inputToLowerCase.startsWith("truck go") )
                    processTruckGo(inputToLowerCase);
                else if ( inputToLowerCase.startsWith("inquiry") )
                    processInquiry(inputToLowerCase);
                else if ( inputToLowerCase.startsWith("turn") ) {
                    processTurn(input);
                    break;
                }
                else if ( input.equalsIgnoreCase("exit level") ) {
                    System.out.println("you want to exit level !(2nd confirmation)");
                    getInput = false;
                    break;
                }
                else
                    System.err.println("Invalid input, Please try again");
            }


            if ( input.equalsIgnoreCase("exit level") ) {
                System.err.println("exiting level");
                Log.log(Log.ERROR, "exiting level");
                getInput = false;
                break;
            }


            if (levelManager.isFinished()) {
                break;
            }
        }

        System.err.println("GOING BACK TO MENU BECAUCE EXIT LEVEL OR FINISHED LEVEL");
        Log.log(Log.ALARM, "GOING BACK TO MENU BECAUCE EXIT LEVEL OR FINISHED LEVEL");

        //System.out.println(levelManager.getInstructionQueue());
        //levelManager.dequeueInstruction();

        int runReturnInt;
        if (levelManager.isFinished()) {
            if (levelManager.isPrized())
                runReturnInt = levelManager.getReward();
            else
                runReturnInt = 0;
        }
        else
            runReturnInt = -1;

        System.err.println("GOING BACK TO MENU CODE = " + runReturnInt);
        Log.log(Log.ALARM, "GOING BACK TO MENU CODE = " + runReturnInt);

        return runReturnInt;
    } /// i. think it's done

    public boolean isLevelFinished() {
        return levelFinished;
    }

    public void badRun() {
        // case handling bad
        /*while ( !(input = scanner.nextLine()).equalsIgnoreCase("exit level") && !levelFinished ) {
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
        }*/
    }

}
