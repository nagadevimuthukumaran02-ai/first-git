import java.util.HashMap;
import java.util.Map;

public class DialingCodes {

    private final Map<Integer, String> codes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setDialingCode(int code, String country) {
        codes.put(code, country);
    }

    public String getCountry(int code) {
        return codes.get(code);
    }

    public void addNewDialingCode(int code, String country) {
        if (!codes.containsKey(code) && !codes.containsValue(country)) {
            codes.put(code, country);
        }
    }

    public Integer findDialingCode(String country) {
        for (Map.Entry<Integer, String> entry : codes.entrySet()) {
            if (entry.getValue().equals(country)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateCountryDialingCode(int newCode, String country) {
        Integer oldCode = findDialingCode(country);

        if (oldCode != null) {
            codes.remove(oldCode);
            codes.put(newCode, country);
        }
    }
}