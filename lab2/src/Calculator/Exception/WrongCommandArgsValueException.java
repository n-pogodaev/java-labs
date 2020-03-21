package Calculator.Exception;

public class WrongCommandArgsValueException extends CalculatorException {
    public WrongCommandArgsValueException(String commandName, int invalidArgumentIndex) {
        super("Wrong value in " + commandName + " command (" +
                (invalidArgumentIndex + 1) + "th argument)");
    }
}
