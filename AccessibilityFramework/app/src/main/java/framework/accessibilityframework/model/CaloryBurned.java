package framework.accessibilityframework.model;

import framework.accessibilityframework.model.unitvalue.KcalUnitValue;

/**
 * Created by Olibario
 * This schema represents a single measurement of the amount of calories burned in kilocalories (kcal).
 */
public class CaloryBurned {
    private static final String DESCRIPTION = "he LOINC code represents Calories burned:Power = Energy/Time:Point in time:^Patient:Quantitative, that is, " +
            "a Quantitative measure of Calories burned by a Patient at a Point in time";
    private static final String URL = "http://purl.bioontology.org/ontology/LNC/41981-2";

    private KcalUnitValue kcal_burned;
    private TimeFrame timeFrame;
    private ActivityName activityName;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public KcalUnitValue getKcal_burned() {
        return kcal_burned;
    }

    public void setKcal_burned(KcalUnitValue kcal_burned) {
        this.kcal_burned = kcal_burned;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public ActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityName activityName) {
        this.activityName = activityName;
    }
}
