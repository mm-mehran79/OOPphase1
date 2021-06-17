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

    private void processBuy(String inputSplit) {

    }

    private void processPickUp(String inputSplit) {

    }

    private void processWell(String inputSplit) {

    }

    private void processPlant(String inputSplit) {

    }

    private void processWork(String inputSplit) {

    }

    private void processCage(String inputSplit) {

    }

    private void processTurn(String inputSplit) {

    }

    private void processTruckLoad(String inputSplit) {

    }

    private void processTruckUnload(String inputSplit) {

    }

    private void processTruckGo(String inputSplit) {

    }

    private void processInquiry(String inputSplit) {

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

            levelFinished = processCheckFinished();
        }

        return levelFinished;
    }

    public boolean isLevelFinished() {
        return levelFinished;
    }

}
