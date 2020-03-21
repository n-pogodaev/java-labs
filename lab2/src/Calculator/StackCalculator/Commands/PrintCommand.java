package Calculator.StackCalculator.Commands;

import Calculator.Exception.StackNotEnoughArgumentsException;
import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.Exception.WrongCommandArgsValueException;
import Calculator.StackCalculator.RuntimeContext;

import java.util.EmptyStackException;
import java.util.Stack;

public class PrintCommand implements Command {
    @Override
    public void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsValueException, WrongCommandArgsCountException, StackNotEnoughArgumentsException {
        if (arguments == null) {
            throw new WrongCommandArgsCountException("print", 0, null);
        }
        if (arguments.length > 0) {
            throw new WrongCommandArgsCountException("print", 0, arguments.length);
        }
        double arg = 0;
        try {
            arg = runtimeContext.getStack().peek();
        }
        catch (EmptyStackException e) {
            throw new StackNotEnoughArgumentsException("print", 1);
        }
        System.out.println(arg);
    }
}
