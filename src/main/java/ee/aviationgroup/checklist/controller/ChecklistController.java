package ee.aviationgroup.checklist.controller;


import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.model.ChecklistElements;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import ee.aviationgroup.checklist.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/checklists")
public class ChecklistController {

    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private ChecklistService checklistService;

    @GetMapping("/all")
    public List<ChecklistElements> getAllChecklists(){
        return checklistRepository.getAllChecklists();
    }


    @PostMapping("/edit")
    public void editChecklist(@RequestBody Checklist checklist) {
        checklistService.saveChecklist(checklist);
       //checklistService.addChecklistItems(items, savedChecklistId);
    }
//    @PostMapping("/add")
//    public void addChecklistItem(@RequestBody ChecklistElements checklistElements) {
//
//        checklistRepository.addChecklistItem(checklistElements);
//
//        //checklistService.addChecklistItems(items, savedChecklistId);
//    }
    //addChecklistItem(ChecklistElements item, int savedChecklistId)

}
