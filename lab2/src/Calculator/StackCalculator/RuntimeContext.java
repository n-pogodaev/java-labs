package Calculator.StackCalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RuntimeContext {
    private final Stack<Double> stack = new Stack<>();
    private final Map<String, Double> defines = new HashMap<>();
    public Stack<Double> getStack() {
        return stack;
    }
    public Map<String, Double> getDefines() {
        return defines;
    }
}
