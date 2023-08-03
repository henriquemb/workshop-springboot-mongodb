package com.github.henriquemb.springboot_mongodb.resources;

import com.github.henriquemb.springboot_mongodb.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {
    @RequestMapping(method=RequestMethod.GET) // or @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User maria = new User("1", "Maria Silva", "maria@gmail.com");
        User alex = new User("2", "Alex Green", "alex@gmail.com");

        return ResponseEntity.ok(new ArrayList<>(Arrays.asList(maria, alex)));
    }
}
