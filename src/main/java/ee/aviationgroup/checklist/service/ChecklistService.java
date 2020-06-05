package ee.aviationgroup.checklist.service;

import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.model.ChecklistElements;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ChecklistService {

    @Autowired
    ChecklistRepository checklistRepository;

    public void saveChecklist(Checklist checklist) {

        if (checklistRepository.checklistExists(checklist))  {
            // Update...
            checklistRepository.updateChecklist(checklist);
        } else {
            // Add...
            checklistRepository.addChecklist(checklist);
        }

        // Selleks hetkeks siin me oleme kindlad, et eksisteerib checklist.
        Checklist savedChecklist = checklistRepository.getChecklistByDay(checklist.getLog_day());

        for (ChecklistElements checklistItem : checklist.getChecklistElementsList()) {
            // küsi, kas see item on juba olemas sellisel checklistil
            // KUi on, siis muuda
            // Kui ei ole, siis lisa
            if(savedChecklist.getChecklistElementsList().isEmpty()) {
                checklist.getChecklistElementsList().add(checklistItem);
            } else {
                checklist.getChecklistElementsList().remove(checklistItem);
                checklist.getChecklistElementsList().add(checklistItem);
                checklistRepository.updateChecklist(checklist);
            }
        }
//        for (int i = checklist.getChecklistElementsList().size(); i < 0 ; i--) {
//            if(savedChecklist.getChecklistElementsList().isEmpty()) {
//                checklist.getChecklistElementsList().add(checklistItem);
//            } else {
//                checklist.getChecklistElementsList().remove(checklistItem);
//                checklist.getChecklistElementsList().add(checklistItem);
//                checklistRepository.updateChecklist(checklist);
//            }
//        }


        //checkitem exists
        //kontrollida log_day ja item name

//        if (checklist.getLog_day() != null && checklist.getLog_day().length() > 0) {
//            checklistRepository.updateCompany(checklist);
//        } else {
//            Assert.isTrue(!checklistRepository.checlistExists(checklist), "Checklist with the specified name already exists");
//            checklistRepository.addChecklist(checklist);
//        }
    }

//    public int editChecklist(ChecklistElements checklist) {
//        List<ChecklistElements> allChecklistsByDate = checklistRepository.getChecklistsByDay(checklist.getLog_day());
//        Assert.isTrue(allChecklistsByDate.size() == 0, "Checklist with the specified name already exists!");
//        return checklistRepository.addChecklist(checklist);
//    }
}







