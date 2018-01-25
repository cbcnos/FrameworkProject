package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * The value set is taken from SNOMED CT and LOINC. For common temperature locations,
 * the adjective is used rather than the actual location name (e.g., axillary rather than armpit).
 * Forehead indicates the use of a disposable strip.
 */
public class MeasurementLocation {
    private static final String[] POSSIBLE_VALUES = {"axillary","finger","forehead","oral","rectal","temporal artery","toe","tympanic","wrist","vagina"};

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws Exception {
        String aux = "";
        boolean found = false;
        for (String s: POSSIBLE_VALUES) {
            aux = aux + s +", ";
            if (value.toLowerCase().equals(s)) {
                found = true;
                this.value = value;
            }
        }
        if (!found) {
            throw new Exception("Expected one of these values: "+aux+ "but found: "+value);
        }
    }
}
