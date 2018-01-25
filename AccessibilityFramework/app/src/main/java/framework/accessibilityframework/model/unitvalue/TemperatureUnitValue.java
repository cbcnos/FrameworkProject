package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario
 *  defines a temperature as a number plus one of a permissible set of units. Allowed units are drawn from the HL7 UCUM Common Synonyms code set
 */
public class TemperatureUnitValue {
    private static final char[] POSSIBLE_UNITS = {'F', 'C', 'K'};

    private Float value; //cannot be NULL
    private char unit; //cannot be NULL

    public Float getValue() {
        return value;
    }

    public void setValue(Float value){
        if (value != null){
            this.value = value;
        }
        else throw new NullPointerException("Temperature value cannot be null.");
    }

    public char getUnit() {
        return unit;
    }

    public void setUnit(char unit) throws Exception {
        boolean found = false;
        for (char c : POSSIBLE_UNITS) {
            if (c == unit) {
                this.unit = unit;
                found = true;
            }
        }
        if (!found){
            throw new Exception("Expected temparature unit C, F or K. Found "+unit);
        }
    }
}
