package ru.ldeloff.pp_3_1_4.service;

import org.springframework.stereotype.Service;
import ru.ldeloff.pp_3_1_4.models.Role;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getAllRoles();
    void add(Role role);
    void delete(long id);
    void edit(Role role);
    Role getById(long id);
    Role getByName(String roleName);

}







