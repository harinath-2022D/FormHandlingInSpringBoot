package com.example.brainio.Controller;

import com.example.brainio.Dtos.RequestFromFrontEndDto;
import com.example.brainio.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/submit")
@RestController

public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/save")
    public String saveFile(@ModelAttribute RequestFromFrontEndDto emp){
        try {
            return userService.saveFile(emp);
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
