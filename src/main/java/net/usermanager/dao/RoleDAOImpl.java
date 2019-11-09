package net.usermanager.dao;

import net.usermanager.model.Role;
import net.usermanager.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Role getRoleByName(String roleName) {
        Role roles = (Role) sessionFactory.getCurrentSession().createQuery("from Role where name= :name")
                .setParameter("name", roleName).getSingleResult();
        return roles;
    }
}
