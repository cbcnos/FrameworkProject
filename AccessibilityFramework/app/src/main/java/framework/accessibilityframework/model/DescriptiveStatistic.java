package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * defines the set of allowable values of descriptive statistic applied to aggregate data.
 */
public class DescriptiveStatistic{
    private static final String[] POSSIBLE_STATISTICS = {"average","maximum","minimum","standard deviation","variance","sum","median","count",
            "quartile deviation","80th percentile","lower quartile","upper quartile","1st quintile",
            "2nd quintile","3rd quintile","4th quintile"};

    private String descriptive_statistic;

    public String getDescriptive_statistic() {
        return descriptive_statistic;
    }

    public void setDescriptive_statistic(String descriptive_statistic) throws Exception {
        boolean found = false;
        String aux = "";
        for (String s : POSSIBLE_STATISTICS) {
            aux = aux + s +" ,";
            if (s.equals(descriptive_statistic)) {
                this.descriptive_statistic = descriptive_statistic;
                found = true;
            }
        }
        if (!found){
            throw new Exception("Expected one of the descriptive statistics: "+aux+ " but found: "+descriptive_statistic);
        }
    }
}
