package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario
 * This schema represents a numerical range with a unit of measure. Allowed units are drawn from HL7’s UCUM (Unified Code for Units of Measure) code set, specifically the Common Synonyms.
 */
public class RangeUnitValue {
    private static final String DESCRIPTION = "This schema represents a range of numerical values with a unit of measure. " +
            "The lower and upper boundaries are included in the interval.";
    private static final String URL = "http://download.hl7.de/documents/ucum/ucumdata.html";

    private Float low_value;
    private Float high_value;
    private static String unit;

    public Float getLow_value() {
        return low_value;
    }

    public void setLow_value(Float low_value) {
        this.low_value = low_value;
    }

    public Float getHigh_value() {
        return high_value;
    }

    public void setHigh_value(Float high_value) {
        this.high_value = high_value;
    }

    public static String getUnit() {
        return unit;
    }

    public static void setUnit(String unit) {
        RangeUnitValue.unit = unit;
    }

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }
}
