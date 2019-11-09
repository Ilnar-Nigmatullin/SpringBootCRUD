package net.usermanager.dao;

import net.usermanager.model.Role;

public interface RoleDAO {
    Role getRoleByName(String roleName);
}
