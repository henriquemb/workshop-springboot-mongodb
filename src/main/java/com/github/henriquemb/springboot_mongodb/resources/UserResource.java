package com.github.henriquemb.springboot_mongodb.resources;

import com.github.henriquemb.springboot_mongodb.domain.User;
import com.github.henriquemb.springboot_mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.GET) // or @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
