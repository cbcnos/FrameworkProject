package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * represents the temporal relationship of a clinical measure or assessment to sleep (e.g., before sleeping, on waking).
 */
public class RelationshipToSleep {

    private static final String[] POSSIBLE_VALUES = {"before sleeping","during sleep","on waking"};
    private final String description = "The temporal relationship of a clinical measure or assessment to sleep. " +
            "(Will add phases of sleep later, depending on use case.)";
    private String value;

    public String getDescription() {
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
