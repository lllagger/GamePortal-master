package com.GamePortal.Controller;

import com.GamePortal.Entity.User;
import com.GamePortal.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add/user")
    public String addUserInformation(@RequestBody User user) {
        log.info("User added to data base.");
        return userService.addUserInformation(user);
    }

    @PutMapping("/update/user")
    public String updateUserInformation(@RequestBody User user) {
        log.info("User updated from data base.");
        return userService.updateUserInformation(user);
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteUserInformation(@PathVariable Long id) {
        log.info("User deleted from data base.");
        return userService.deleteUserInformation(id);
    }

    @RequestMapping("/get/user/{id}")
    public User getUserInformation(@PathVariable Long id) {
        log.info("User has showing.");
        return userService.getUserInformation(id);
    }
}