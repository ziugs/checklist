package ee.aviationgroup.checklist.controller;


import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/checklists")
public class ChecklistController {

    @Autowired
    private ChecklistRepository checklistRepository;

    @GetMapping("/all")
    public List<Checklist> getAllChecklists(){
        return checklistRepository.getAllChecklists();
    }

    @PostMapping("/add")
    public void addChecklist(@RequestBody Checklist checklist) {
        checklistRepository.addChecklist(checklist);
    }
//    @PostMapping("/add")
//    public int addCompany(@RequestBody Company company) {
//        return companiesService.addCompany(company);
//    }

}
