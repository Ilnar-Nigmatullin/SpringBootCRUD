package net.usermanager.service;

import net.usermanager.dao.UserDAO;
import net.usermanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> readUsers() {
        return userDAO.readUsers();
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

}
