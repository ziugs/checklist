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

//    @PostMapping("/add")
//    public void addChecklist(@RequestBody ChecklistElements checklist) {
//        checklistRepository.addChecklist(checklist);
//    }
    @PostMapping("/edit")
    public void editChecklist(@RequestBody Checklist checklist, ChecklistElements checklistElements) {
        checklistService.saveChecklist(checklist);
    }

}
