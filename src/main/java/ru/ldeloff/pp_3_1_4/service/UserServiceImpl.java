package ru.ldeloff.pp_3_1_4.service;

import ru.ldeloff.pp_3_1_4.dao.UserDAO;
import ru.ldeloff.pp_3_1_4.models.Role;
import ru.ldeloff.pp_3_1_4.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Transactional
    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Transactional
    @Override
    public void addRole(long userID, Role role) {
        userDAO.addRole(userID, role);
    }

    @Override
    public Set<Role> getRoles(long userID) {
        return userDAO.getRoles(userID);
    }

    @Override
    public User getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public User findByName(String userName) {
        return userDAO.findByName(userName);
    }
}
