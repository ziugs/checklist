package ee.aviationgroup.checklist.controller;

import ee.aviationgroup.checklist.dto.JwtRequestDto;
import ee.aviationgroup.checklist.dto.JwtResponseDto;
import ee.aviationgroup.checklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public JwtResponseDto authenticate(@RequestBody JwtRequestDto request) throws Exception {
        return userService.authenticate(request);
    }
}