package com.github.henriquemb.springboot_mongodb.resources;

import com.github.henriquemb.springboot_mongodb.domain.Post;
import com.github.henriquemb.springboot_mongodb.resources.util.URL;
import com.github.henriquemb.springboot_mongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {
    @Autowired
    private PostService postService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping(value="/search")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="title", defaultValue="") String title) {
        title = URL.decodeParams(title);
        List<Post> posts = postService.findByTitle(title);

        return ResponseEntity.ok(posts);
    }
}
