package com.github.henriquemb.springboot_mongodb.services;

import com.github.henriquemb.springboot_mongodb.domain.Post;
import com.github.henriquemb.springboot_mongodb.repository.PostRepository;
import com.github.henriquemb.springboot_mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            throw new ObjectNotFoundException("Post not found");
        }

        return post.get();
    }

    public List<Post> findByTitle(String title) {
        return postRepository.findByTitleContaining(title);
    }
}
