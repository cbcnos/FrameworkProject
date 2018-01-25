package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario on 10/03/2016.
 * Contains the value of the glucose level, in one of the possible units
 */
public class BloodGlucoseUnitValue {
    private static final String[] POSSIBLE_UNITS = {"mg/dL","mmol/L"};

    private Float value;
    private String unit;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) throws Exception {
        if (value != null) {
            this.value = value;
        }
        else throw new NullPointerException("The value cannot be null");
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) throws Exception {
        if ((!unit.equals(POSSIBLE_UNITS[0])) && (!unit.equals(POSSIBLE_UNITS[1]))){
            throw new Exception("Expected one of the units: mg/dl, nmol/L. Found: "+unit);
        }
        else {
            this.unit = unit;
        }
    }
}
