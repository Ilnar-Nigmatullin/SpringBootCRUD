package net.usermanager.service;

import net.usermanager.model.Role;
import net.usermanager.model.User;

import java.util.List;

public interface UserService {

    List<User> readUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(int id);

    User findByUserName(String userName);
}
