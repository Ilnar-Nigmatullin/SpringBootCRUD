package net.usermanager.controller;

import net.usermanager.model.Role;
import net.usermanager.model.User;
import net.usermanager.service.RoleService;
import net.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    /*Таблица Юзеров*/
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView readUsers(ModelAndView modelAndView) {
        List<User> users = userService.readUsers();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", users);

        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable("id") int id, ModelAndView modelAndView) {

        User user = userService.getUserById(id);
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView updateUser(@ModelAttribute("user") User user,
                                   @RequestParam(value = "role", defaultValue = "ROLE_USER") String[] roleArr,
                                   ModelAndView modelAndView) {
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        Set<Role> roles = new HashSet<>();
        for (String s : roleArr) {
            Role roleUser = roleService.getRoleByName(s);
            roles.add(roleUser);
        }
        user.setRoles(roles);
        modelAndView.setViewName("redirect:/admin");
        userService.updateUser(user);

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView addPage(ModelAndView modelAndView) {
        modelAndView.setViewName("create");

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUser(@ModelAttribute("user") User user,
                                @RequestParam(value = "role", defaultValue = "ROLE_USER") String[] roleArr,
                                ModelAndView modelAndView) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        for (String s : roleArr) {
            Role roleUser = roleService.getRoleByName(s);
            roles.add(roleUser);
        }
        user.setRoles(roles);
        userService.addUser(user);
        modelAndView.setViewName("redirect:/admin");

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(int id, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/admin");
        User user = userService.getUserById(id);
        userService.deleteUser(user);

        return modelAndView;
    }
}
