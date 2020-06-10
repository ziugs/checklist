package ee.aviationgroup.checklist.service;

import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.model.ChecklistElements;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

        } else {
            // Add...
            checklistRepository.addChecklist(checklist);

        }

        Checklist savedChecklist = checklistRepository.getChecklistByDay(checklist.getLog_day());
        addOrUpdateChecklistItems(checklist.getChecklistElementsList(), savedChecklist.getId_log_day());
    }


    public void addOrUpdateChecklistItems(List<ChecklistElements> items, int savedChecklistId) {
        for (ChecklistElements checklistItem : items) {
            if (checklistRepository.checklistItemExists(checklistItem, savedChecklistId)) {
                checklistRepository.updateChecklisItem(checklistItem, savedChecklistId);
            } else {
                checklistRepository.addChecklistItem(checklistItem, savedChecklistId);
            }

        }

    }

}







