package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * This schema represents the temporal relationship of a clinical measure or assessment to physical activity (e.g., at rest, during exercise).
 */
public class TempRelationshipPhysicalActivity {
    private static final String[] POSSIBLE_VALUES = {"at rest","active","before exercise", "after exercise", "during exercise"};

    private final String description = "The temporal relationship of a clinical measure or assessment to physical activity.";
    private String value;

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

    public String getDescription() {
        return description;
    }
}
