package Calculator.StackCalculator.Commands;

import Calculator.Exception.StackNotEnoughArgumentsException;
import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.StackCalculator.RuntimeContext;

import java.util.EmptyStackException;

public class PopCommand implements Command {
    @Override
    public void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsCountException, StackNotEnoughArgumentsException {
        if (arguments == null) {
            throw new WrongCommandArgsCountException("pop", 0, null);
        }
        if (arguments.length > 0) {
            throw new WrongCommandArgsCountException("pop", 0, arguments.length);
        }
        try {
            runtimeContext.getStack().pop();
        }
        catch (EmptyStackException e) {
            throw new StackNotEnoughArgumentsException("pop", 1);
        }
    }
}
