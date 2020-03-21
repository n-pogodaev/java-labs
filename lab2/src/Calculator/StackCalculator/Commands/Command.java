package Calculator.StackCalculator.Commands;

import Calculator.Exception.StackNotEnoughArgumentsException;
import Calculator.Exception.WrongCommandArgsCountException;
import Calculator.Exception.WrongCommandArgsValueException;
import Calculator.StackCalculator.RuntimeContext;

public interface Command {
    void Execute(String[] arguments, RuntimeContext runtimeContext)
            throws WrongCommandArgsValueException, WrongCommandArgsCountException, StackNotEnoughArgumentsException;
}
