package ru.ldeloff.pp_3_1_4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ldeloff.pp_3_1_4.models.Role;
import ru.ldeloff.pp_3_1_4.models.User;
import ru.ldeloff.pp_3_1_4.service.RoleService;
import ru.ldeloff.pp_3_1_4.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final UserService userService;
    private final RoleService roleService;

    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public List<User> allUsers() {
        return userService.getAllUser();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }

    @GetMapping("/info")
    public User information(Principal principal) {
        User user = userService.getByEmail(principal.getName());
        return user;
    }

    @GetMapping("users/{id}")
    public User informationById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody Map<String, Object> payload) {
        User user = new User(Integer.parseInt((String) payload.get("id")),
                (String) payload.get("firstName"),
                (String) payload.get("lastName"),
                Integer.parseInt((String) payload.get("age")),
                (String) payload.get("email"), (String) payload.get("password"));
        user.addRole(roleService.getByName((String) payload.get("roles")));
        userService.edit(user);
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody Map<String, Object> payload) {
        User user = new User(
                (String) payload.get("firstName"),
                (String) payload.get("lastName"),
                Integer.parseInt((String) payload.get("age")),
                (String) payload.get("email"), (String) payload.get("password"));
        user.addRole(roleService.getByName((String) payload.get("roles")));
        userService.add(user);
        return user;
    }
}
