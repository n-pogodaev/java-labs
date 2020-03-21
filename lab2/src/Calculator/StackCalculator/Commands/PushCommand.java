package Calculator.StackCalculator.Commands;

import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.Exception.WrongCommandArgsValueException;
import Calculator.StackCalculator.RuntimeContext;

public class PushCommand implements Command {
    @Override
    public void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsValueException, WrongCommandArgsCountException {
        if (arguments == null) {
            throw new WrongCommandArgsCountException("push", 1, null);
        }
        if (arguments.length != 1) {
            throw new WrongCommandArgsCountException("push", 1, arguments.length);
        }
        if (arguments[0] == null) {
            throw new WrongCommandArgsValueException("push", 0);
        }
        Double value = runtimeContext.getDefines().get(arguments[0]);
        if (value == null) {
            try {
                value = Double.parseDouble(arguments[0]);
            } catch (NumberFormatException e) {
                throw new WrongCommandArgsValueException("push", 1);
            }
        }
        runtimeContext.getStack().push(value);
    }
}
