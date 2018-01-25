package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * represents body fat percentage. It can be used for a single measurement, or for basic descriptive statistics
 */
public class BodyFatPercentage {
    private static final String DESCRIPTION = "The LOINC code represents Body fat percentage:Mass Fraction:Point " +
            "in time:^Patient:Quantitative:Measured";
    private static final String URL = "http://purl.bioontology.org/ontology/LNC/41982-0";
    private static final String unit = "%";

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
        this.value = value;
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
