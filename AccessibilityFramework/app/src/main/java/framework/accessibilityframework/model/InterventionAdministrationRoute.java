package framework.accessibilityframework.model;

/**
 * Created by Olibario
 * This schema defines the list of permissible values describing the route of administration of an intervention (e.g., a drug).
 */
public class InterventionAdministrationRoute {
        private static final String DESCRIPTION = "The route of administration of a therapeutic agent or other intervention (e.g., a device).";
        private static final String[] POSSIBLE_UNITS = {"arteriovenous fistula", "arteriovenous graft", "body cavity", "auricular", "buccal", "caudal", "colostomy", "conjunctival", "cutaneous", "dental", "digestive tract", "electro-osmosis", "endocervical",
                "endosinusial", "endotracheopulmonary", "enteral", "epidural", "esophagostomy", "extra-amniotic", "extracorporeal", "fistula", "gastro-intestinal stoma", "gastroenteral", "gastrostomy", "gingival",
                "ileostomy", "hemodialysis", "infiltration", "interstitial", "intra-abdominal", "intra-amniotic", "intra-arterial", "intra-articular", "intrabiliary", "intrabronchial", "intrabursal", "intracameral",
                "intracardiac", "intracartilaginous", "intracaudal", "intracavernous", "intracavitary", "intracerebral", "intracerebroventricular", "intracisternal", "intracolonic", "intracorneal", "intracoronal",
                "intracoronary", "intracorporus cavernosum", "intracranial", "intradermal", "intradiscal", "intraductal", "intraduodenal", "intradural", "intraepicardial", "intraepidermal", "intraesophageal",
                "intragastric", "intragingival", "intrahepatic", "intraileal", "intrajejunal", "intralesional", "intralingual", "intraluminal", "intralymphatic", "intramammary", "intramedullary", "intrameningeal",
                "intramural", "intramuscular", "intramyometrial", "intraneural", "intranodal", "intraocular", "intraomentum", "intraosseous", "intraovarian", "intrapericardial", "intraperitoneal", "intrapleural",
                "intraprostatic", "intrapulmonary", "intraruminal", "intrasinal", "intraspinal", "intrasternal", "intrasynovial", "intratendinous", "intratesticular", "intrathecal", "intrathoracic", "intratubular",
                "intratumor", "intratympanic", "intrauterine", "intravascular", "intravenous", "intravenous central", "intravenous peripheral", "intraventricular - cardiac", "intravesical", "intravitreal",
                "iontophoresis", "irrigation", "jejunostomy", "laryngeal", "lower respiratory tract", "mucous fistula", "nasal", "nasoduodenal", "nasogastric", "nasojejunal", "not applicable", "occlusive dressing technique",
                "ophthalmic", "oral", "orogastric", "oromucosal", "oropharyngeal", "otic", "parenteral", "paracervical", "paravertebral", "percutaneous", "periarticular", "peribulbar", "peridural", "perineural",
                "periodontal", "periosteal", "peritendinous", "periurethral", "rectal", "respiratory tract", "retrobulbar", "soft tissue", "subarachnoid", "subconjunctival", "subcutaneous", "subgingival",
                "sublesional", "sublingual", "submucosal", "suborbital", "subretinal", "subtendinous", "surgical cavity", "surgical drain", "topical", "transcervical", "transdermal", "transendocardial", "transmucosal",
                "transplacental", "transtracheal", "transtympanic", "transurethral", "tumor cavity", "ureteral", "urethral", "urostomy", "vaginal"};

        private String unit;

        public static String getDESCRIPTION() {
                return DESCRIPTION;
        }

        public String getUnit() {
                return unit;
        }

        public void setUnit(String unit) throws Exception {
                boolean found = false;
                String aux = "";
                for (String s : POSSIBLE_UNITS) {
                        aux = aux + s + " ,";
                        if (s.equals(unit)) {
                                this.unit = unit;
                                found = true;
                        }
                }
                if (!found) {
                        throw new Exception("Expected one of the following administration routes: " + aux + " but found: " + unit);
                }
        }
}
