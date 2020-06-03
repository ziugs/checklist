package ee.aviationgroup.checklist.repository;


import ee.aviationgroup.checklist.model.Checklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChecklistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Checklist> getAllChecklists() {
        return jdbcTemplate.query("SELECT * FROM checklist INNER JOIN checklist_element ON checklist_element.checklist_id = checklist.id_log_day",
                (row, number) -> {
                    return new Checklist(
                            row.getInt("id_log_day"),
                            row.getString("log_day"),
                            row.getString("element_name"),
                            row.getInt("quantity"),
                            row.getString("element_group"),
                            row.getBoolean("day_check"),
                            row.getBoolean("night_check"),
                            row.getString("comment"),
                            row.getString("crew_member"),
                            row.getString("pressure")
                    );
                });

    }

    public int addChecklist(Checklist checklist) {
        return jdbcTemplate.update("insert into checklist_element (`element_name`,`checklist_id`,`quantity`, `day_check`, `night_check`, `comment`) values (?, ?, ?, ?, ?, ?)",
             checklist.getElement_name(),
                checklist.checklist_id, checklist.getQuantity(), checklist.isDay_check(), checklist.isNight_check(), checklist.getComment());

    }

    public void updateCompany(Checklist checklist) {
        jdbcTemplate.update(
                "update checklist set quantity = ?, day_check = ?, night_check = ?, comment = ?",
                checklist.getQuantity(), checklist.isDay_check(), checklist.isNight_check(), checklist.getComment());
    }
    public boolean checlistyExists(Checklist checklist) {
        Integer count = jdbcTemplate.queryForObject("select count(id_log_day) from checklist where log_day = ?", new Object[]{checklist.getLog_day()}, Integer.class);
        return count != null && count > 0;
    }

    public List<Checklist> getChecklistsByDay(String checklistDay) {
        List<Checklist> checklists = jdbcTemplate.query("select * from checklist where `log_day` like ?",
                new Object[]{"%" + checklistDay + "%"},
                (row, number) -> {
                    return new Checklist(
                            row.getInt("id_log_day"),
                            row.getString("log_day"),
                            row.getString("element_name"),
                            row.getInt("quantity"),
                            row.getString("element_group"),
                            row.getBoolean("day_check"),
                            row.getBoolean("night_check"),
                            row.getString("comment"),
                            row.getString("crew_member"),
                            row.getString("pressure")
                    );
                });
        return checklists.size() > 0 ? checklists : null;
    }

}


