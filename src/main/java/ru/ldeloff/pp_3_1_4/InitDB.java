package ru.ldeloff.pp_3_1_4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ldeloff.pp_3_1_4.models.Role;
import ru.ldeloff.pp_3_1_4.models.User;
import ru.ldeloff.pp_3_1_4.service.RoleService;
import ru.ldeloff.pp_3_1_4.service.UserService;

@Component
public class InitDB implements ApplicationRunner {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.add(roleUser);

        User user = new User();
        user.setAge(30);
        user.setEmail("user@mail.ru");
        user.setFirstName("User");
        user.setLastName("User");
        user.setPassword("user");
        user.addRole(roleUser);
        userService.add(user);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.add(roleAdmin);

        User admin = new User();
        admin.setAge(32);
        admin.setEmail("admin@mail.ru");
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setPassword("admin");
        admin.addRole(roleAdmin);
        userService.add(admin);
    }
}
