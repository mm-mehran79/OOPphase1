package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputCommand {
    Exit("^(?i)\\s*exit");

    private final Pattern commandPattern;
    InputCommand(String s) {
        this.commandPattern = Pattern.compile(s);
    }

    public Matcher getMatcher(String input){
        return this.commandPattern.matcher(input);
    }
}
