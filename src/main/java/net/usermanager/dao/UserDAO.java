package net.usermanager.dao;

import net.usermanager.model.User;

import java.util.List;

public interface UserDAO {

    List<User> readUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(int id);

    User findByUserName(String username);

}
