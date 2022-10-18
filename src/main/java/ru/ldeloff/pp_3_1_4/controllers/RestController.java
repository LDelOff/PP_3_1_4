package ru.ldeloff.pp_3_1_4.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ldeloff.pp_3_1_4.models.Role;
import ru.ldeloff.pp_3_1_4.models.User;
import ru.ldeloff.pp_3_1_4.service.RoleService;
import ru.ldeloff.pp_3_1_4.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
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
    public ResponseEntity<List<User>> allUsers() {
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }

    @GetMapping("/info")
    public ResponseEntity<User> information(Principal principal) {
        User user = userService.getByEmail(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> informationById(@PathVariable long id) {
        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody Map<String, Object> payload) {
        User user = new User(Integer.parseInt((String) payload.get("id")),
                (String) payload.get("firstName"),
                (String) payload.get("lastName"),
                Integer.parseInt((String) payload.get("age")),
                (String) payload.get("email"), (String) payload.get("password"));
        ArrayList<String> arrayList = (ArrayList<String>) payload.get("roles");
        arrayList.forEach((role) -> user.addRole(roleService.getByName(role)));
        userService.edit(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody Map<String, Object> payload) {
        User user = new User(
                (String) payload.get("firstName"),
                (String) payload.get("lastName"),
                Integer.parseInt((String) payload.get("age")),
                (String) payload.get("email"), (String) payload.get("password"));
        ArrayList<String> arrayList = (ArrayList<String>) payload.get("roles");
        arrayList.forEach((role) -> user.addRole(roleService.getByName(role)));
        userService.add(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
