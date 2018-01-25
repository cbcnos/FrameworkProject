package framework.accessibilityframework.model;


import framework.accessibilityframework.model.unitvalue.MassUnitValue;

/**
 * Created by Olibario
 * represents body weight: it allows expression in either metric (kg, g) or imperial (lbs, oz.) units.
 * It can be used for a single body weight data point, or for basic descriptive statistics
 */
public class BodyWeight {
    private static final String DESCRIPTION = "This schema represents a person's body weight, either a single body weight measurement, or for the result of aggregating several" +
            " measurements made over time (see Numeric descriptor schema for a list of aggregate measures)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/363808001";

    private MassUnitValue massUnitValue;
    private TimeFrame timeFrame;
    private DescriptiveStatistic descriptiveStatistic;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public MassUnitValue getMassUnitValue() {
        return massUnitValue;
    }

    public void setMassUnitValue(MassUnitValue massUnitValue) {
        this.massUnitValue = massUnitValue;
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
