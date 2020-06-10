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
                            row.getString("log_day"),
                            row.getInt("quantity"),
                            row.getString("element_group"),
                            row.getBoolean("day_check"),
                            row.getBoolean("night_check"),
                            row.getString("comment"),
                            row.getString("pressure"),
                            row.getBoolean("helo_a"),
                            row.getBoolean("helo_b"),
                            row.getBoolean("helo_c")
                    );
                });

    }

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
                "insert into checklist_element (checklist_id, element_name, quantity, element_group, day_check, night_check, comment, pressure, helo_a, helo_b, helo_c) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                checklistId, item.getElement_name(), item.getQuantity(), item.getElement_group(), item.isDay_check(), item.isNight_check(),
                item.getComment(), item.getPressure(), item.isHelo_a(), item.isHelo_b(), item.isHelo_c());
    }


    public void updateChecklisItem(ChecklistElements item, int checklistId) {
        jdbcTemplate.update(
                "update checklist_element set quantity = ?, element_group = ?, day_check = ?, night_check = ?, comment = ?, pressure = ?, helo_a = ?, helo_b = ?, helo_c = ? where checklist_id = ? and element_name = ?",
                item.getQuantity(), item.getElement_group(), item.isDay_check(), item.isNight_check(),
                item.getComment(), item.getPressure(), item.isHelo_a(), item.isHelo_b(), item.isHelo_c(), checklistId, item.getElement_name()
        );
    }

    public boolean checklistExists(Checklist checklist) {
        Integer count = jdbcTemplate.queryForObject("select count(id_log_day) from checklist where log_day = ?", new Object[]{checklist.getLog_day()}, Integer.class);
        return count != null && count > 0;

    }

    public boolean checklistItemExists(ChecklistElements item, int checklistId) {
        Integer count = jdbcTemplate.queryForObject("select count(id_checklist_element) from checklist_element where checklist_id = ? and element_name = ?",
                new Object[]{checklistId, item.getElement_name()}, Integer.class);
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


}


