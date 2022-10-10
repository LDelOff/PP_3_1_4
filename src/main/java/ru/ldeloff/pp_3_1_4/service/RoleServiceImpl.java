package ru.ldeloff.pp_3_1_4.service;

import org.springframework.stereotype.Service;
import ru.ldeloff.pp_3_1_4.dao.RoleDAO;
import ru.ldeloff.pp_3_1_4.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public void add(Role role) {
        roleDAO.add(role);
    }

    @Override
    public void delete(long id) {
        roleDAO.delete(id);
    }

    @Override
    public void edit(Role role) {
        roleDAO.edit(role);
    }

    @Override
    public Role getById(long id) {
        return roleDAO.getById(id);
    }

    @Override
    public Role getByName(String roleName) {
        return roleDAO.getByName(roleName);
    }
}
