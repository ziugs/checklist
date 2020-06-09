package ee.aviationgroup.checklist.model;



import java.util.ArrayList;
import java.util.List;

public class Checklist {
    private int id_log_day;
    private String crew_member;
    private String log_day;
    private List<ChecklistElements> checklistElementsList = new ArrayList<>();



    public Checklist() {
    }

    public Checklist(int id_log_day, String crew_member, String log_day, List<ChecklistElements> checklistElementsList) {
        this.id_log_day = id_log_day;
        this.crew_member = crew_member;
        this.log_day = log_day;
        this.checklistElementsList = checklistElementsList;
    }

    public int getId_log_day() {
        return id_log_day;
    }

    public void setId_log_day(int id_log_day) {
        this.id_log_day = id_log_day;
    }

    public String getCrew_member() {
        return crew_member;
    }

    public void setCrew_member(String crew_member) {
        this.crew_member = crew_member;
    }

    public String getLog_day() {
        return log_day;
    }

    public void setLog_day(String log_day) {
        this.log_day = log_day;
    }


    public List<ChecklistElements> getChecklistElementsList() {
        //checklistElementsList.add(checklistElements);
        return checklistElementsList;
    }

    public void setChecklistElementsList(List<ChecklistElements> checklistElementsList) {
        this.checklistElementsList = checklistElementsList;
    }
}
