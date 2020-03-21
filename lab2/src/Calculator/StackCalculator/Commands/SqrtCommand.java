package Calculator.StackCalculator.Commands;

import Calculator.Exception.StackNotEnoughArgumentsException;
import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.StackCalculator.RuntimeContext;

import java.util.EmptyStackException;
import java.util.Stack;

public class SqrtCommand implements Command {
    @Override
    public void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsCountException, StackNotEnoughArgumentsException {
        if (arguments == null) {
            throw new WrongCommandArgsCountException("sqrt", 0, null);
        }
        if (arguments.length > 0) {
            throw new WrongCommandArgsCountException("sqrt", 0, arguments.length);
        }
        double arg = 0;
        Stack<Double> stack = runtimeContext.getStack();
        try {
            arg = stack.pop();
        }
        catch (EmptyStackException e) {
            throw new StackNotEnoughArgumentsException("sqrt", 1);
        }
        stack.push(Math.sqrt(arg));
    }
}
