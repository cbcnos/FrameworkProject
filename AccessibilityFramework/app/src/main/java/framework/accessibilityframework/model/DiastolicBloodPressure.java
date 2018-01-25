package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * This schema is part of the blood pressure schema, and should be combined with systolic blood pressure to create the full schema.
 */
public class DiastolicBloodPressure {
    private static final String DESCRIPTION = "This schema represents a person's diastolic blood pressure.";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/271650006";

    private String value;
    private static final String UNIT = "mmHg";

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws Exception {
        if (value != null){
            this.value = value;
        }
        else throw new NullPointerException("The value cannot be null");
    }

    public static String getUNIT() {
        return UNIT;
    }
}
