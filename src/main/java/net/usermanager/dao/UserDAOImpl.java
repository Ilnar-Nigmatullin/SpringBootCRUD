package net.usermanager.dao;

import net.usermanager.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> readUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public User findByUserName(String userName) {
        User users = (User) sessionFactory.getCurrentSession().createQuery("from User where userName= :userName")
                .setParameter("userName", userName).getSingleResult();

        return users;
    }
}
