package Calculator.StackCalculator.Commands;

import Calculator.Exception.StackNotEnoughArgumentsException;
import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.StackCalculator.RuntimeContext;

import java.util.EmptyStackException;
import java.util.Stack;

public class SubCommand implements Command {
    @Override
    public void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsCountException, StackNotEnoughArgumentsException {
        if (arguments == null) {
            throw new WrongCommandArgsCountException("sub", 0, null);
        }
        if (arguments.length > 0) {
            throw new WrongCommandArgsCountException("sub", 0, arguments.length);
        }
        double firstArg = 0, secondArg = 0;
        Stack<Double> stack = runtimeContext.getStack();
        try {
            firstArg = stack.pop();
            secondArg = stack.pop();
        }
        catch (EmptyStackException e) {
            throw new StackNotEnoughArgumentsException("sub", 2);
        }
        stack.push(firstArg - secondArg);
    }
}
