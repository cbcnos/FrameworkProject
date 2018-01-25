package framework.accessibilityframework.model;

import java.util.List;

/**
 * Created by Olibario.
 * defines the set of allowable values describing the posture of the body (e.g., during a measurement)
 */
public class BodyPosturePostural {
    private static final String[] POSSIBLE_POSITIONS = {"sitting","lying down","standing","semi-recumbent"};

    private final String DESCRIPTION = "The posture of the subject (for example, during a clinical measurement).";
    private final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/9851009";

    private List<Value> values;

    public static String[] getPossiblePositions() {
        return POSSIBLE_POSITIONS;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public String getURL() {
        return URL;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    class Value{
        private TimeFrame timeFrame;
        private String position; //cannot be NULL

        public TimeFrame getTimeFrame() {
            return timeFrame;
        }

        public void setTimeFrame(TimeFrame timeFrame) {
            this.timeFrame = timeFrame;
        }

        public String getPosition() {

            return position;
        }

        public void setPosition(String position) throws Exception {
            boolean found = false;
            for (String s: POSSIBLE_POSITIONS){
                if (position.toLowerCase().equals(s)){ //it's a valid position
                    found = true;
                    this.position = position;
                }
            }
            if (!found){
                throw new Exception("Expected one of the following positions: \"sitting\",\"lying down\",\"standing\",\"semi-recumbent\"; but found: "+position);
            }
        }
    }

}
