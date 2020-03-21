package Calculator.Exception;

public class StackNotEnoughArgumentsException extends CalculatorException {
    public StackNotEnoughArgumentsException(String command, int expectedArgumentsCount) {
        super("Not enough arguments in stack for command " + command +
                ": expected " + expectedArgumentsCount);
    }
}
