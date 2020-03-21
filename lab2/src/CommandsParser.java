import java.util.ArrayList;

public class CommandsParser {
    public static String[][] ParseCommands(String commands) {
        String[] commandLines = commands.split("\n");
        ArrayList<String> clearedCommandLines = new ArrayList<>();
        for (String nextLine : commandLines) {
            for (int i = 0; i < nextLine.length(); ++i) {
                char nextChar = nextLine.charAt(i);
                if (nextChar != ' ' && nextChar != '\t') {
                    if (nextChar == '#') {
                        break;
                    }
                    else {
                        clearedCommandLines.add(nextLine);
                        break;
                    }
                }
            }
        }
        String[][] parsedCommandLines = new String[clearedCommandLines.size()][];
        for (int i = 0; i < parsedCommandLines.length; ++i) {
            parsedCommandLines[i] = clearedCommandLines.get(i).split(" +");
        }
        return parsedCommandLines;
    }
}
