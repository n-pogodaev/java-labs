import Calculator.Calculator;
import Calculator.StackCalculator.StackCalculator;

public class CalculatorMain {
    public static void main(String[] args) {
        String commands;
        if (args.length < 1) {
            commands = FileReader.ReadFile("");
        }
        else {
            commands = FileReader.ReadFile(args[0]);
        }
        String[][] parsedCommandLines = CommandsParser.ParseCommands(commands);
        Calculator calculator = new StackCalculator(parsedCommandLines);
        calculator.Calculate();
    }
}
