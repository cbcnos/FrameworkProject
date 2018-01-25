package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * represents a person’s heart rate and its relationship to physical activity (resting, or after exercise, etc).
 * The schema can be used either for a single heart rate measurement, or for basic descriptive statistics
 */
public class HeartRate {
    private static final String DESCRIPTION = "This schema represents a person's heart rate, either a single heart rate measurement, " +
            "or the result of aggregating several measurements made over time (see Numeric descriptor schema for a list of aggregate measures)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/78564009";
    private static final String POSSIBLE_UNITS = "beats/min";

    private Float value;
    private String unit;
    private String user_note;

    private TimeFrame timeFrame;
    private DescriptiveStatistic descriptiveStatistic;
    private TempRelationshipPhysicalActivity relationshipToPhysicalActivity;

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
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) throws Exception {
        if (!unit.equals(POSSIBLE_UNITS)){
            throw new Exception("Expected one of the units: "+POSSIBLE_UNITS+". Found: "+unit);
        }
        else {
            this.unit = unit;
        }
    }

    public String getUser_note() {
        return user_note;
    }

    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public DescriptiveStatistic getDescriptiveStatistic() {
        return descriptiveStatistic;
    }

    public void setDescriptiveStatistic(DescriptiveStatistic descriptiveStatistic) {
        this.descriptiveStatistic = descriptiveStatistic;
    }

    public TempRelationshipPhysicalActivity getRelationshipToPhysicalActivity() {
        return relationshipToPhysicalActivity;
    }

    public void setRelationshipToPhysicalActivity(TempRelationshipPhysicalActivity relationshipToPhysicalActivity) {
        this.relationshipToPhysicalActivity = relationshipToPhysicalActivity;
    }
}
