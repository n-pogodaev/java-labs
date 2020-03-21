package Calculator.StackCalculator.Commands;

import Calculator.Exception.StackNotEnoughArgumentsException;
import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.Exception.WrongCommandArgsValueException;
import Calculator.StackCalculator.RuntimeContext;

public class DefineCommand implements Command {
    @Override
    public void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsValueException, WrongCommandArgsCountException, StackNotEnoughArgumentsException {
        if (arguments == null) {
            throw new WrongCommandArgsCountException("define", 2, null);
        }
        if (arguments.length != 2) {
            throw new WrongCommandArgsCountException("define", 2, arguments.length);
        }
        if (arguments[0] == null) {
            throw new WrongCommandArgsValueException("define", 0);
        }
        String var = arguments[0];
        if (arguments[1] == null) {
            throw new WrongCommandArgsValueException("define", 1);
        }
        double value = 0;
        try {
            value = Integer.parseInt(arguments[1]);
        }
        catch (NumberFormatException e) {
            throw new WrongCommandArgsValueException("define", 1);
        }
        runtimeContext.getDefines().put(var, value);
    }
}
