package framework.accessibilityframework.model;


import framework.accessibilityframework.model.unitvalue.TemperatureUnitValue;

/**
 * Created by Olibario
 * represents the body temperature and the location where the measurement was taken.
 * The schema can be used either for a single temperature measurement, or for basic descriptive statistics
 */
public class BodyTemperature {
    private static final String DESCRIPTION = "This schema represents a person's body temperature.";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/386725007";

    private TemperatureUnitValue temperatureUnitValue;
    private TimeFrame timeFrame;
    private DescriptiveStatistic descriptiveStatistic;
    private RelationshipToSleep relationshipToSleep;
    private MeasurementLocation measurementLocation;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public TemperatureUnitValue getTemperatureUnitValue() {
        return temperatureUnitValue;
    }

    public void setTemperatureUnitValue(TemperatureUnitValue temperatureUnitValue) {
        this.temperatureUnitValue = temperatureUnitValue;
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

    public RelationshipToSleep getRelationshipToSleep() {
        return relationshipToSleep;
    }

    public void setRelationshipToSleep(RelationshipToSleep relationshipToSleep) {
        this.relationshipToSleep = relationshipToSleep;
    }

    public MeasurementLocation getMeasurementLocation() {
        return measurementLocation;
    }

    public void setMeasurementLocation(MeasurementLocation measurementLocation) {
        this.measurementLocation = measurementLocation;
    }
}
