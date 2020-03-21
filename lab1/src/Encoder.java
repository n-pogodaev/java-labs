public final class Encoder extends Coder {
    public Encoder(String in) {
        super(in);
    }
    private boolean AppendEncodedValue(Alphabet abc, String value, StringBuilder result) {
        String encodedValue = abc.getEncodedValue(value);
        if (encodedValue.isEmpty()) {
            return false;
        }
        result.append(encodedValue);
        return true;
    }
    @Override
    public String translate(Alphabet abc, StatisticsCounter stat) {
        String in = GetInString();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < in.length(); ++i) {
            char nextChar = in.charAt(i);
            if (Character.isSpaceChar(nextChar)) {
                result.append(nextChar);
            }
            else if (nextChar == '\n' || nextChar == '\r') {
                result.append(nextChar);
                continue;
            }
            else {
                String charStringValue = Character.toString(Character.toUpperCase(nextChar));
                stat.CountChar(charStringValue);
                if (!AppendEncodedValue(abc, charStringValue, result)) {
                    continue;
                }
            }
            result.append(' ');
        }
        return result.toString();
    }
}
