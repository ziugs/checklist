package ee.aviationgroup.checklist.controller;


import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import ee.aviationgroup.checklist.repository.service.ChecklistService;
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
    ChecklistService checklistService;

    @GetMapping("/all")
    public List<Checklist> getAllChecklists(){
        return checklistRepository.getAllChecklists();
    }

    @PostMapping("/add")
    public void addChecklist(@RequestBody Checklist checklist) {
        checklistRepository.addChecklist(checklist);
    }
    @PostMapping("/edit")
    public void editChecklist(@RequestBody Checklist checklist) {
        checklistService.saveChecklist(checklist);
    }

}
