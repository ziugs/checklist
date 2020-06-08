package ee.aviationgroup.checklist.model;


public class ChecklistElements {
    public int checklist_id;
    private int id_log_day;
    private String element_name;
    private int quantity;
    private String element_group;
    private boolean day_check;
    private boolean night_check;
    private String comment;
    private String pressure;

    public ChecklistElements(){

    }

    public ChecklistElements(int checklist_id, int id_log_day, String element_name, int quantity, String element_group, boolean day_check, boolean night_check, String comment, String pressure) {
        this.checklist_id = checklist_id;
        this.id_log_day = id_log_day;
        this.element_name = element_name;
        this.quantity = quantity;
        this.element_group = element_group;
        this.day_check = day_check;
        this.night_check = night_check;
        this.comment = comment;
        this.pressure = pressure;
    }

    public int getChecklist_id() {
        return checklist_id;
    }

    public void setChecklist_id(int checklist_id) {
        this.checklist_id = checklist_id;
    }

    public int getId_log_day() {
        return id_log_day;
    }

    public void setId_log_day(int id_log_day) {
        this.id_log_day = id_log_day;
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getElement_group() {
        return element_group;
    }

    public void setElement_group(String element_group) {
        this.element_group = element_group;
    }

    public boolean isDay_check() {
        return day_check;
    }

    public void setDay_check(boolean day_check) {
        this.day_check = day_check;
    }

    public boolean isNight_check() {
        return night_check;
    }

    public void setNight_check(boolean night_check) {
        this.night_check = night_check;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
