package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * represents body mass index in kg/m2. It can be used for a single BMI data point, or for basic descriptive statistics
 */
public class BodyMassIndex {
    private static final String DESCRIPTION = "This schema represents a person's body mass index (BMI), either a single BMI measurement, " +
            "or the result of aggregating several measurements made over time (see Numeric descriptor schema for a list of aggregate measures)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/60621009";

    private static final String unit = "kg/m2";
    private Float value;

    private TimeFrame timeFrame;
    private DescriptiveStatistic descriptiveStatistic;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUnit() {
        return unit;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        if (value != null) {
            this.value = value;
        }
        else throw new NullPointerException("Body index value cannot be null");
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
}
