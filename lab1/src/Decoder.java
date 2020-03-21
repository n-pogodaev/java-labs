public final class Decoder extends Coder {
    public Decoder(String in) {
        super(in);
    }
    private void PrintWrongCode() {
        System.err.println("\nWrong code");
    }
    private boolean AppendDecodedValue(Alphabet abc, String encodedValue, StringBuilder result, StatisticsCounter stat) {
        String decodedValue = abc.getDecodedValue(encodedValue);
        if (decodedValue.isEmpty()) {
            PrintWrongCode();
            return false;
        }
        stat.CountChar(decodedValue);
        result.append(decodedValue);
        return true;
    }
    private boolean AppendTokenWithNewLine(Alphabet abc, String token, StringBuilder result, StatisticsCounter stat) {
        StringBuilder letter = new StringBuilder();
        for (int j = 0; j < token.length(); ++j) {
            switch (token.charAt(j)) {
                case '\r':
                    continue;
                case '\n':
                    // if '\n' after code letter then write this letter and after write '\n'
                    if (letter.length() != 0) {
                        if (!AppendDecodedValue(abc, letter.toString(), result, stat)) {
                            return false;
                        }
                        letter.delete(0, letter.length() - 1);
                    }
                    result.append('\n');
                    break;
                default:
                    letter.append(token.charAt(j));
                    break;
            }
        }
        if (letter.length() != 0) {
            return AppendDecodedValue(abc, letter.toString(), result, stat);
        }
        return true;
    }
    @Override
    public String translate(Alphabet abc, StatisticsCounter stat) {
        String[] in = GetInString().split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < in.length; ++i) {
            if (in[i].isEmpty()) {
                // "   " ->(split)-> "" "" -> ' '
                result.append(' ');
                ++i;
            }
            else if (in[i].contains("\n")) {
                if (!AppendTokenWithNewLine(abc, in[i], result, stat)) {
                    break;
                }
            }
            else {
                if (!AppendDecodedValue(abc, in[i], result, stat)) {
                    break;
                }
            }
        }
        return result.toString();
    }
}
