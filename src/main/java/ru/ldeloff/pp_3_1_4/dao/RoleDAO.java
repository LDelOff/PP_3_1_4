package ru.ldeloff.pp_3_1_4.dao;

import ru.ldeloff.pp_3_1_4.models.Role;

import java.util.List;
public interface RoleDAO {

    List<Role> getAllRoles();

    void add(Role role);

    void delete(long id);

    void edit(Role role);

    Role getById(long id);

    Role getByName(String roleName);
}
