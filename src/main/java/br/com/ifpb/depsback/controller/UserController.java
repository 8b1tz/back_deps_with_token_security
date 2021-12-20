package br.com.ifpb.depsback.controller;

import br.com.ifpb.depsback.model.User;
import br.com.ifpb.depsback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/user/readall")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = { "/user/read/{id}" })
    public User findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PutMapping(value = "/user/update/{id}")
    public User update(@PathVariable long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping(path = { "/user/delete/{id}" })
    public String delete(@PathVariable long id) {
        return userService.delete(id);
    }

    @DeleteMapping(path = { "/user/deleteall" })
    public void deleteAll() {
        userService.deleteAll();
    }


}

