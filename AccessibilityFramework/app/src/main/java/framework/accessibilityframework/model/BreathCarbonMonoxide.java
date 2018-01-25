package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * represents body weight: it allows expression in either metric (kg, g) or imperial (lbs, oz.) units.
 * It can be used for a single body weight data point, or for basic descriptive statistics
 */
public class BreathCarbonMonoxide {
    private static final String DESCRIPTION = "This schema represents the concentration of carbon monoxide (CO) in a person's expired breath, either a single measurement, " +
            "or the result of aggregating several measurements made over time (see Descriptive statistic schema for a list of aggregate measures)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/251900003";
    private static final String UNIT = "ppm";

    private Float value; //cannot be null
    private TimeFrame timeFrame;
    private DescriptiveStatistic descriptiveStatistic;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUNIT() {
        return UNIT;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        if (value != null) {
            this.value = value;
        }
        else throw new NullPointerException("Breath carbon monoxide value cannot be null");
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
