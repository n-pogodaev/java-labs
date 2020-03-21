package Calculator.Exception;

public class CalculatorException extends Exception {
    private String message = null;
    public CalculatorException(String message) {
        super();
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message + "\n";
    }
}
