package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * This schema represent the name of the physical activity in which the person is engaged.
 * It is recommended that the activity name be drawn from this extensive list based on CDC
 * guidelines to facilitate mapping to standard energy expenditure values (METs)
 */
public class ActivityName {
    private static final String DESCRIPTION = "The name(s) of the physical activity(ies) in which the person is engaged. " +
            "It is recommended that the activity name be drawn from the CDC guidelines " +
            "to facilitate mapping to standard energy expenditure values (METs)";
    private static final String URL = "http://purl.bioontology.org/ontology/SNOMEDCT/257733005";

    private String activity_name;

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getURL() {
        return URL;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        //TODO incluir os nomes de todas as atividades possíveis, com base na lista no website oficial
        this.activity_name = activity_name;
    }
}
