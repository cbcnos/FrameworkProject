package framework.accessibilityframework.model;

import java.util.List;

import framework.accessibilityframework.model.unitvalue.TemperatureUnitValue;


/**
 * Created by Olibario
 * represents the ambient temperature (room or outdoor). The schema can be used either for a single temperature measurement, or for basic descriptive statistics
 */
public class AmbientTemperature {
    private final String DESCRIPTION = "This schema represents the ambient temperature, either a single measurement, " +
                                    "or the result of aggregating several measurements made over time (see Descriptive" +
                                    " schema for a list of aggregate measures)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/250825003";

    private List<Value> values;

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    class Value{
        private TemperatureUnitValue temperature;
        private TimeFrame time;
        private DescriptiveStatistic statistic;

        public TemperatureUnitValue getTemperature() {
            return temperature;
        }

        public void setTemperature(TemperatureUnitValue temperature) {
            this.temperature = temperature;
        }

        public TimeFrame getTime() {
            return time;
        }

        public void setTime(TimeFrame time) {
            this.time = time;
        }

        public DescriptiveStatistic getStatistic() {
            return statistic;
        }

        public void setStatistic(DescriptiveStatistic statistic) {
            this.statistic = statistic;
        }
    }
}
