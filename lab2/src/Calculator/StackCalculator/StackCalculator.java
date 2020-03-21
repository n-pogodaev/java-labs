package Calculator.StackCalculator;

import Calculator.Calculator;
import Calculator.Exception.CalculatorException;
import Calculator.StackCalculator.Commands.Command;
import Calculator.StackCalculator.Commands.CommandFactory;

import java.util.*;

public class StackCalculator extends Calculator {
    private RuntimeContext runtimeContext = new RuntimeContext();
    public StackCalculator(String[][] commands) {
        super(commands);
    }
    private String[] GetArguments(String[] commandAndArguments) {
        String[] result;
        result = Arrays.copyOfRange(commandAndArguments, 1, commandAndArguments.length);
        return result;
    }
    @Override
    protected void ExecuteCommand(String[] commandAndArguments) throws CalculatorException {
        if (commandAndArguments == null || commandAndArguments.length < 1) {
            return;
        }
        String[] arguments = GetArguments(commandAndArguments);
        Command command = CommandFactory.CreateCommand(commandAndArguments[0]);
        command.Execute(arguments, runtimeContext);
    }
}
