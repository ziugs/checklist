package ee.aviationgroup.checklist.repository.service;

import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ChecklistService {

    @Autowired
    ChecklistRepository checklistRepository;

    public void saveChecklist(Checklist checklist) {
//        Assert.notNull(checklist, "Checklist not specified");
//        Assert.hasText(checklist.getLog_day(), "Checklist name not specified");
        if (checklist.getLog_day() != null && checklist.getId_log_day() > 0) {
            checklistRepository.updateCompany(checklist);
        } else {
            Assert.isTrue(!checklistRepository.checlistyExists(checklist), "Checklist with the specified name already exists");
            checklistRepository.addChecklist(checklist);
        }
    }


    public int editChecklist(Checklist checklist) {
        List<Checklist> allChecklistsByDate = checklistRepository.getChecklistsByDay(checklist.getLog_day());
        Assert.isTrue(allChecklistsByDate.size() == 0, "Checklist with the specified name already exists!");
        return checklistRepository.addChecklist(checklist);
    }

}







