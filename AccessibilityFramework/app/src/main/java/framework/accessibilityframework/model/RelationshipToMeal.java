package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * represents the temporal relationship of a clinical measure or assessment to meals (e.g., fasting, after lunch).
 */
public class RelationshipToMeal {

    private static final String[] POSSIBLE_VALUES = {"fasting", "not fasting","before meal","after meal","before breakfast",
            "after breakfast","before lunch","after lunch","before dinner","after dinner","2 hours postprandial","with meal",
            "with food"};

    private final String description = "The temporal relationship of a clinical measure or assessment to meals. ";
    private String value;

    public String getDescription(){
        return description;
    }

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
