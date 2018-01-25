package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * Represents a person’s blood pressure as a combination of a systolic blood pressure
 * and diastolic blood pressure, and whether the patient was lying down, sitting, or
 * standing when the blood pressure was obtained. This schema
 * can be used for either a single blood pressure measurement, or for basic descriptive statistics
 */
public class BloodPressure {
    private static final String DESCRIPTION = "This schema represents a person's " +
            "blood pressure as a combination of systolic and diastolic blood pressure.";

    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/75367002";

    private SystolicBloodPressure systolicBloodPressure;
    private DiastolicBloodPressure diastolicBloodPressure;
    private TimeFrame timeFrame;
    private DescriptiveStatistic statistic;
    private BodyPosture bodyPosture;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public SystolicBloodPressure getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(SystolicBloodPressure systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public DiastolicBloodPressure getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(DiastolicBloodPressure diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
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

    public BodyPosture getBodyPosture() {
        return bodyPosture;
    }

    public void setBodyPosture(BodyPosture bodyPosture) {
        this.bodyPosture = bodyPosture;
    }
}
