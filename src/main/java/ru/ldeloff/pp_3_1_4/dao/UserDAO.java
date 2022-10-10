package ru.ldeloff.pp_3_1_4.dao;

import ru.ldeloff.pp_3_1_4.models.Role;
import ru.ldeloff.pp_3_1_4.models.User;

import java.util.List;
import java.util.Set;
public interface UserDAO{
    List<User> getAllUser();
    void add(User user);
    void delete(long id);
    void edit(User user);
    User getById(long id);
    void addRole(long userID, Role role);
    Set<Role> getRoles(long userID);
    User getByEmail(String email);
    User findByName(String userName);
}
