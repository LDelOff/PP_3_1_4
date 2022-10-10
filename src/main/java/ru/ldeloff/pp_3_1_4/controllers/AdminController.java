package ru.ldeloff.pp_3_1_4.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ldeloff.pp_3_1_4.models.User;
import ru.ldeloff.pp_3_1_4.service.RoleService;
import ru.ldeloff.pp_3_1_4.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String AdminPage(Principal principal, Model model) {
        model.addAttribute("person", userService.getByEmail(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("newUser", new User());
        model.addAttribute("userUp", new User());
        return "panelAdmin";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("userUp") User user,
                      @RequestParam(name = "roleName") String roleName) {
        user.addRole(roleService.getByName(roleName));
        userService.add(user);
        return "redirect:/admin/";
    }


    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("userUp") User user,
                             @RequestParam(name = "roleName") String roleName) {
        user.addRole(roleService.getByName(roleName));
        userService.edit(user);

        return "redirect:/admin/";
    }
}
