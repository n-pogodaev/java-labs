import java.util.HashMap;
import java.util.Map;

abstract public class Alphabet {
    private final Map<String, String> codeMap = new HashMap<>();
    private final Map<String, String> decodeMap = new HashMap<>();
    final public String getEncodedValue(String val) {
        return codeMap.getOrDefault(val, "");
    }
    final public String getDecodedValue(String val) {
        return decodeMap.getOrDefault(val, "");
    }
    // *(source, code)
    final protected void putToAlphabet(String decodedValue, String encodedValue) {
        codeMap.put(decodedValue, encodedValue);
        decodeMap.put(encodedValue, decodedValue);
    }
}
