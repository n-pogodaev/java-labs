package Calculator;
import Calculator.Exception.CalculatorException;

abstract public class Calculator {
    private final String[][] commands;
    protected Calculator(String[][] commands) {
        this.commands = commands;
    }
    abstract protected void ExecuteCommand(String[] command) throws CalculatorException;
    public final void Calculate() {
        if (commands == null) {
            return;
        }
        for (int i = 0; i < commands.length; ++i) {
            try {
                ExecuteCommand(commands[i]);
            } catch (CalculatorException e) {
                System.err.println("Calculator error: line " + i + "\n");
                e.printStackTrace();
            }
        }
    }
}
