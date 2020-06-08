package ee.aviationgroup.checklist.repository;


import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.model.ChecklistElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ChecklistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ChecklistElements> getAllChecklists() {
        return jdbcTemplate.query("SELECT * FROM checklist INNER JOIN checklist_element ON checklist_element.checklist_id = checklist.id_log_day",
                (row, number) -> {
                    return new ChecklistElements(
                            row.getInt("checklist_id"),
                            row.getInt("id_log_day"),
                            row.getString("element_name"),
                            row.getInt("quantity"),
                            row.getString("element_group"),
                            row.getBoolean("day_check"),
                            row.getBoolean("night_check"),
                            row.getString("comment"),
                            row.getString("pressure")
                    );
                });

    }

//    public int addChecklist(Checklist checklist) {
//        return jdbcTemplate.update("insert into checklist_element (`checklist_id`,`id_log_day`,`quantity`, `element_name`, `quantity`, `element_group`, `day_check`, `night_check`, `comment`, `crew_member`, `pressure` ) values (?, ?, ?, ?, ?, ?)",
//                checklist.getChecklistElementsList().get(0), checklist.getChecklistElementsList().get(1), checklist.getChecklistElementsList().get(2),
//                checklist.getChecklistElementsList().get(3), checklist.getChecklistElementsList().get(4), checklist.getChecklistElementsList().get(5),
//                checklist.getChecklistElementsList().get(6), checklist.getChecklistElementsList().get(7), checklist.getChecklistElementsList().get(8),
//                checklist.getChecklistElementsList().get(9), checklist.getChecklistElementsList().get(10));
//
//
//
//    }

//    public int addChecklist(ChecklistElements checklist) {
//        return jdbcTemplate.update("insert into checklist_element (`element_name`,`checklist_id`,`quantity`, `day_check`, `night_check`, `comment`) values (?, ?, ?, ?, ?, ?)",
//             checklist.getElement_name(),
//                checklist.checklist_id, checklist.getQuantity(), checklist.isDay_check(), checklist.isNight_check(), checklist.getComment());
//
//    }

    public void updateChecklist(Checklist checklist) {
        jdbcTemplate.update(
                "update checklist set crew_member = ? where log_day = ?",
                checklist.getCrew_member(), checklist.getLog_day());
    }

    public void addChecklist(Checklist checklist) {
        jdbcTemplate.update(
                "insert into checklist (log_day, crew_member) values (?, ?)",
                checklist.getLog_day(), checklist.getCrew_member());
    }

    public void addChecklistItem(ChecklistElements item, int checklistId) {
        jdbcTemplate.update(
                "insert into checklist_element (checklist_id, element_name, quantity, element_group, day_check, night_check, comment, pressure) values (?, ?, ?, ?, ?, ?, ?, ?)",
                checklistId, item.getElement_name(), item.getQuantity(), item.getElement_group(), item.isDay_check(), item.isNight_check(),
                item.getComment(), item.getPressure());
    }
    public void updateChecklisItem(ChecklistElements item, int checklistId) {
        jdbcTemplate.update(
                "update checklist_element set checklist_id = ?, element_name = ?, quantity = ?, element_group = ?, day_check = ?, night_check = ?, comment = ?, pressure = ? where checklist_id = ?",
                checklistId, item.getElement_name(), item.getQuantity(), item.getElement_group(), item.isDay_check(), item.isNight_check(),
                item.getComment(), item.getPressure(), checklistId
        );
    }


    public boolean checklistExists(Checklist checklist) {
        Integer count = jdbcTemplate.queryForObject("select count(id_log_day) from checklist where log_day = ?", new Object[]{checklist.getLog_day()}, Integer.class);
        return count != null && count > 0;
    }

    public Checklist getChecklistByDay(String checklistDay) {
        List<Checklist> checklists = jdbcTemplate.query("select * from checklist where `log_day` = ?",
                new Object[]{checklistDay},
                (row, number) -> {
                    return new Checklist(
                            row.getInt("id_log_day"),
                            row.getString("crew_member"),
                            row.getString("log_day"),
                            Collections.emptyList()
                    );
                });
        return checklists.size() > 0 ? checklists.get(0) : null;
    }


//    public List<ChecklistElements> getChecklistsByDay(String checklistDay) {
//        List<ChecklistElements> checklists = jdbcTemplate.query("select * from checklist where `log_day` like ?",
//                new Object[]{"%" + checklistDay + "%"},
//                (row, number) -> {
//                    return new ChecklistElements(
//                            row.getInt("id_log_day"),
//                            row.getString("log_day"),
//                            row.getString("element_name"),
//                            row.getInt("quantity"),
//                            row.getString("element_group"),
//                            row.getBoolean("day_check"),
//                            row.getBoolean("night_check"),
//                            row.getString("comment"),
//                            row.getString("crew_member"),
//                            row.getString("pressure")
//                    );
//                });
//        return checklists.size() > 0 ? checklists : null;
//    }

}


