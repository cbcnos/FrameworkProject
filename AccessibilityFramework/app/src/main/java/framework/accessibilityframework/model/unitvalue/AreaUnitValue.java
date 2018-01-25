package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario on 16/03/2016.
 */
public class AreaUnitValue {
    private static final String[] POSSIBLE_UNITS = {"mm^2","cm^2","m^2","km^2","in^2","ft^2","yd^2","mi^2"};
    private final String DESCRIPTION = "The unit of measure of the element. " +
            "Basic unit is meter (m). Allowed values are drawn from the SI Area Units and English " +
            "Area Units Common Synonyms (non-UCUM). " +
            "The valid UCUM code is different for square inch ([sin_i]), square foot ([sft_i]), square yard ([syd_i]).";
    private final String URL = "http://download.hl7.de/documents/ucum/ucumdata.html";

    private Float value;
    private String unit;

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getURL() {
        return URL;
    }

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
