package ee.aviationgroup.checklist.service;

import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.model.ChecklistElements;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ChecklistService {

    @Autowired
    ChecklistRepository checklistRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void saveChecklist(Checklist checklist) {


        if (checklistRepository.checklistExists(checklist)) {
            // Update...
            checklistRepository.updateChecklist(checklist);
            //updateChecklistItems(checklist.getChecklistElementsList(), savedChecklist.getId_log_day());
        } else {
            // Add...
            checklistRepository.addChecklist(checklist);

        }
        Checklist savedChecklist = checklistRepository.getChecklistByDay(checklist.getLog_day());
        addChecklistItems(checklist.getChecklistElementsList(), savedChecklist.getId_log_day());
        updateChecklistItems(checklist.getChecklistElementsList(), savedChecklist.getId_log_day());



        // Selleks hetkeks siin me oleme kindlad, et eksisteerib checklist.
//        Checklist savedChecklist = checklistRepository.getChecklistByDay(checklist.getLog_day());
//        if (savedChecklist.getId_log_day() > 0) {
//            checklistRepository.updateChecklist(checklist);
//            updateChecklistItems(checklist.getChecklistElementsList(), savedChecklist.getId_log_day());
//        } else {
//
//            checklistRepository.addChecklist(checklist);
//            addChecklistItems(checklist.getChecklistElementsList(), savedChecklist.getId_log_day());
//        }
    }


    public void addChecklistItems(List<ChecklistElements> items, int savedChecklistId) {
        for (ChecklistElements checklistItem : items) {
            checklistRepository.addChecklistItem(checklistItem, savedChecklistId);

        }
    }

    public void updateChecklistItems(List<ChecklistElements> items, int savedChecklistId){
        for (ChecklistElements checklistItem : items) {

                checklistRepository.updateChecklisItem(checklistItem, savedChecklistId);

        }
    }

}







