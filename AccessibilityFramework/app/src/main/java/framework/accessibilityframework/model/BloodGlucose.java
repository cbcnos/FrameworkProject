package framework.accessibilityframework.model;

import java.util.List;

import framework.accessibilityframework.model.unitvalue.BloodGlucoseUnitValue;

/**
 * Created by Olibario
 * This class represents a person’s blood glucose level, on what type of specimen the measurement was made,
 * and the relationship of the measure to meals or physical activity.
 * This schema can be used for either a single blood glucose measurement, or for basic descriptive statistics
 */
public class BloodGlucose {
    private final String DESCRIPTION = "This schema represents a person's blood glucose level.";
    private final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/365812005";

    private static final String[] POSSIBLE_SPECIMEN_VALUES = {"interstitial fluid","capillary blood","plasma","serum","tears","whole blood"};
    private String specimen_value;
    private List<Value> values;

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getURL() {
        return URL;
    }

    public String getSpecimen_value() {
        return specimen_value;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void setSpecimen_value(String specimen_value) throws Exception {
        boolean found = false;
        String aux = "";

        for (String s: POSSIBLE_SPECIMEN_VALUES){
            aux = aux + s + ", ";
            if (specimen_value.toLowerCase().equals(s)){
                this.specimen_value = specimen_value;
                found = true;
            }
        }
        if (!found){
            throw new Exception("Expected one of the specimen values: "+aux);
        }
    }

    class Value{
        private BloodGlucoseUnitValue bloodGlucoseUnitValue;
        private SpecimenSource specimenSource;
        private TimeFrame timeFrame;
        private DescriptiveStatistic statistic;

        public BloodGlucoseUnitValue getBloodGlucoseUnitValue() {
            return bloodGlucoseUnitValue;
        }

        public void setBloodGlucoseUnitValue(BloodGlucoseUnitValue bloodGlucoseUnitValue) {
            this.bloodGlucoseUnitValue = bloodGlucoseUnitValue;
        }

        public SpecimenSource getSpecimenSource() {
            return specimenSource;
        }

        public void setSpecimenSource(SpecimenSource specimenSource) {
            this.specimenSource = specimenSource;
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
}
