public class Statistics {
    private String ch;
    private int usageCount;
    Statistics(String ch) {
        this.ch = ch;
        usageCount = 0;
    }
    public void countIncrement() {
        ++usageCount;
    }
    public String getCharName() {
        return ch;
    }
    public int getValue() {
        return usageCount;
    }
    @Override
    public int hashCode() {
        return ch.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Statistics) {
            Statistics anotherStat = (Statistics) obj;
            return ch.equals(anotherStat.ch);
        }
        return false;
    }
}
