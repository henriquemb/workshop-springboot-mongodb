package com.github.henriquemb.workshopmongo.resources;

import com.github.henriquemb.workshopmongo.domain.Post;
import com.github.henriquemb.workshopmongo.domain.User;
import com.github.henriquemb.workshopmongo.dto.UserDTO;
import com.github.henriquemb.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
        User user = User.fromDTO(userDTO);

        user.setId(null);

        user = userService.insert(user);

        userDTO.setId(user.getId());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody User user) {
        user.setId(id);

        UserDTO userDTO = new UserDTO(userService.update(user));

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(value="/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user.getPosts());
    }
}
