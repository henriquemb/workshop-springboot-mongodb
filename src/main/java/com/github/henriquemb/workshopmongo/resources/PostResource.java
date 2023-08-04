package com.github.henriquemb.workshopmongo.resources;

import com.github.henriquemb.workshopmongo.domain.Post;
import com.github.henriquemb.workshopmongo.resources.util.URL;
import com.github.henriquemb.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping(value="/searchTitle")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String title) {
        title = URL.decodeParams(title);
        List<Post> posts = postService.findByTitle(title);

        return ResponseEntity.ok(posts);
    }

    @GetMapping(value="/searchContent")
    public ResponseEntity<List<Post>> findByContent(@RequestParam(value="text", defaultValue="") String text) {
        text = URL.decodeParams(text);
        List<Post> posts = postService.findContentByText(text);

        return ResponseEntity.ok(posts);
    }

    @GetMapping(value="/search")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue="") String text,
            @RequestParam(value="minDate", defaultValue="") String minDate,
            @RequestParam(value="maxDate", defaultValue="") String maxDate
    ) {
        text = URL.decodeParams(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());

        List<Post> posts = postService.fullSearch(text, min, max);
        return ResponseEntity.ok(posts);
    }
}
