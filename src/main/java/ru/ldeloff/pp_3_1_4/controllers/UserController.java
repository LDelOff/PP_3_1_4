package ru.ldeloff.pp_3_1_4.controllers;

import ru.ldeloff.pp_3_1_4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String show(Principal principal, Model model) {
        model.addAttribute("person", userService.getByEmail(principal.getName()));
        return "user";
    }
}
