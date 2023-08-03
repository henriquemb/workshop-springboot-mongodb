package com.github.henriquemb.springboot_mongodb.resources;

import com.github.henriquemb.springboot_mongodb.domain.User;
import com.github.henriquemb.springboot_mongodb.dto.UserDTO;
import com.github.henriquemb.springboot_mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.GET) // or @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll().stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO user = new UserDTO(userService.findById(id));
        return ResponseEntity.ok(user);
    }
}
