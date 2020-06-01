package ee.aviationgroup.checklist.controller;


import ee.aviationgroup.checklist.model.Checklist;
import ee.aviationgroup.checklist.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
