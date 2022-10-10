package ru.ldeloff.pp_3_1_4.dao;

import org.springframework.stereotype.Repository;
import ru.ldeloff.pp_3_1_4.models.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class)
                .getResultList();
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public void edit(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getByName(String roleName) {
        return (Role) entityManager.createQuery("FROM Role where name = :name").setParameter("name", roleName).getSingleResult();
    }
    @Override
    public Role getById(long id) {
        return entityManager.find(Role.class, id);
    }
}
