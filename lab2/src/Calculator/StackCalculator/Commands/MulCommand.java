package Calculator.StackCalculator.Commands;

import Calculator.Exception.StackNotEnoughArgumentsException;
import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.StackCalculator.RuntimeContext;

import java.util.EmptyStackException;
import java.util.Stack;

public class MulCommand implements Command {
    @Override
    public void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsCountException, StackNotEnoughArgumentsException {
        if (arguments == null) {
            throw new WrongCommandArgsCountException("multi", 0, null);
        }
        if (arguments.length > 0) {
            throw new WrongCommandArgsCountException("multi", 0, arguments.length);
        }
        double firstArg = 0, secondArg = 0;
        Stack<Double> stack = runtimeContext.getStack();
        try {
            firstArg = stack.pop();
            secondArg = stack.pop();
        }
        catch (EmptyStackException e) {
            throw new StackNotEnoughArgumentsException("multi", 2);
        }
        stack.push(firstArg * secondArg);
    }
}
