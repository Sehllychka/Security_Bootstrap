package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public List<Role> allRole() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findRoleById(Long id) {
        Role role = entityManager.find(Role.class, id);
        if (role == null) {
            throw new EntityNotFoundException(String.format("Роль с ID %s не найдена", id));
        }
        return role;
    }

}
