package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario
 * This schema represents a mass. Allowed units are drawn from the HL7 UCUM Common Synonyms code set.
 */
public class MassUnitValue {
    private static final String DESCRIPTION = "This schema represents a mass. The unit of measure of the element. Basic unit is gram (g) " +
            "[ http://unitsofmeasure.org/ucum.html#para-28 ]. Allowed values are drawn from the SI Mass Units and English Mass Units Common Synonyms (non-UCUM) columns of the 'Mass Units' section. " +
            "The valid UCUM code is different for metric ton (t), grain ([gr]), ounce ([oz_av]), pound ([lb_av]) and Ton ([ston_av]).";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/118538004";

    private static final String[] POSSIBLE_UNITS = {"fg","pg","ng","ug","mg","g","kg","Metric Ton","gr","oz","lb","Ton"};

    private Float value; //cannot be null
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
        else throw new NullPointerException("Body mass value cannot be null");
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
