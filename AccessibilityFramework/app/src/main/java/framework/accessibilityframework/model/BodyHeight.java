package framework.accessibilityframework.model;

import framework.accessibilityframework.model.unitvalue.LenghtUnitValue;

/**
 * Created by Olibario
 * represents body height: it allows expression in either metric (m, cm) or US customary (ft, in) units.
 * It can be used for a single body height data point, or for basic descriptive statistics
 */
public class BodyHeight {
    private static final String DESCRIPTION = "This schema represents a person's body height, either a single body height measurement, " +
            "or the result of aggregating several " +
            "measurements made over time (see Numeric descriptor schema for a list of aggregate measures)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/50373000";

    private LenghtUnitValue bodyHeighUnitValue;
    private TimeFrame timeFrame;
    private DescriptiveStatistic statistic;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public LenghtUnitValue getBodyHeighUnitValue() {
        return bodyHeighUnitValue;
    }

    public void setBodyHeighUnitValue(LenghtUnitValue bodyHeighUnitValue) {
        this.bodyHeighUnitValue = bodyHeighUnitValue;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public DescriptiveStatistic getStatistic() {
        return statistic;
    }

    public void setStatistic(DescriptiveStatistic statistic) {
        this.statistic = statistic;
    }
}
