package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario
 * This schema represents calories burned in kilocalories (kcal) in a single episode of activity. The ability to represent descriptive statistics (e.g., mean, median) will be added shortly.
 * */
public class KcalUnitValue {
    private static final String DESCRIPTION = "This schema represents an amount (value) of kilocalories (unit of measure). " +
            "The unit of measure of the element. The allowed value is kcal. This is not a standard " +
            "(see http://unitsofmeasure.org/ucum.html#para-43), but it is the one commonly and widely used.";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/258791007";
    private static final String UNIT = "kcal";

    private Float value;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUNIT() {
        return UNIT;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        if (value != null) {
            this.value = value;
        }
        else throw new NullPointerException("The number of kcal burned cannot be null");
    }
}
