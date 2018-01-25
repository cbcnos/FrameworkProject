package framework.accessibilityframework.model.unitvalue;

/**
 * Created by Olibario on 16/03/2016.
 * This schema represents a length or distance. Allowed units are drawn from the HL7 UCUM Common Synonyms code set.
 */
public class LenghtUnitValue {
    private static final String DESCRIPTION = "This schema represents a length or a distance.";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/410668003";

    private static final String[] POSSIBLE_UNITS = {"fm","pm","nm","um","mm","cm","m","km","in","ft","yd","mi"};

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
        else throw new NullPointerException("Body heigh value cannot be null");
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
