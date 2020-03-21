package Calculator.Exception;

public class WrongCommandArgsCountException extends CalculatorException {
    public WrongCommandArgsCountException(String commandName, Integer expectedArgsCount, Integer providedArgsCount) {
        super(commandName +  " command's arguments count error: " +
                "expected " + expectedArgsCount
                + ", provided " + (providedArgsCount == null ? "null" : providedArgsCount));
    }
}
