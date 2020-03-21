abstract public class Coder {
    private String in;
    protected Coder(String in) {
        this.in = in;
    }
    public void ChangeSource(String newStr) {
        in = newStr;
    }
    public String GetInString() {
        return in;
    }
    abstract public String translate(Alphabet abc, StatisticsCounter stat);
}
