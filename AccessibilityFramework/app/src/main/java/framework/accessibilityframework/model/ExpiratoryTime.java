package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * represents the duration of a person’s expiration measured in seconds. The schema can be used either for a single measurement, or for basic descriptive statistics
 */
public class ExpiratoryTime {
    private static final String DESCRIPTION = "This schema represents the duration of a person's expiration (the time interval between the initiation of " +
            "exhalation and the time at which no further lung volume reduction occurs), either a single measurement, or the result of aggregating several measurements made " +
            "over time (see Descriptive statistic schema for a list of aggregate measures)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/250820008";
    private static final String POSSIBLE_UNITS = "sec";

    private Float value;
    private String unit;

    private TimeFrame timeFrame;
    private DescriptiveStatistic descriptiveStatistic;
    private TempRelationshipPhysicalActivity relationshipToPhysicalActivity;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
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
        if (!unit.equals(POSSIBLE_UNITS)){
            throw new Exception("Expected one of the units: "+POSSIBLE_UNITS+". Found: "+unit);
        }
        else {
            this.unit = unit;
        }
    }
}
