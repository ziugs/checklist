package ee.aviationgroup.checklist.repository;


import ee.aviationgroup.checklist.model.Checklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ChecklistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Checklist> getAllChecklists(){
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

    public void addChecklist(Checklist checklist){
        jdbcTemplate.update("insert into checklist_element(`quantity`, `day_check`, `night_check`, `comment`) values (?, ?, ?, ?)",
                checklist.getQuantity(), checklist.isDay_check(), checklist.isNight_check(), checklist.getComment());

    }
}

