package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery(
                "from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }


    @Override
    public User findByIdUser(long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.id = :userId", User.class);
        query.setParameter("userId", id);
        List<User> users = query.getResultList();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("Не найден пользователь с Id = " + id);
        } else if (users.size() > 1) {
            throw new IllegalStateException("Найдено более одного пользователя с Id = " + id);
        }
        return users.get(0);
    }


    @Override
    public void removeUser(long id) {
        User user = findByIdUser(id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        findByIdUser(user.getId());
        entityManager.merge(user);
    }

    @Override
    public User getUserByUsername(String email) {
        Query query = entityManager.createQuery("select u from  User u where  u.email= :email", User.class);
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }
}


