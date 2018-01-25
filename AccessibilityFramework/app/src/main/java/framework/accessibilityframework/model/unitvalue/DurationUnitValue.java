package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario
 * This schema represents a duration (length of time).
 * Allowed time units are drawn from the HL7 UCUM Common Synonyms code set.
 */
public class DurationUnitValue {
    private static final String DESCRIPTION = "This schema represents a duration or length of time.";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/103335007";
    private static final String[] POSSIBLE_UNITS = { "ps","ns","us","ms","sec","min","h","d","wk","Mo","yr"};

    private Float value;
    private String unit;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        if (value != null) {
            this.value = value;
        }
        else throw new NullPointerException("Duration value cannot be null");
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) throws Exception {
        boolean found = false;
        String aux = "";
        for (String s: POSSIBLE_UNITS) {
            aux = aux + s+ ", ";
            if (unit.toLowerCase().equals(s)){
                found = true;
                this.unit = unit;
            }
        }
        if (!found){
            throw new Exception("Expected the unit to be one of the followings: "+aux+ "but found: "+unit);
        }
    }
}
