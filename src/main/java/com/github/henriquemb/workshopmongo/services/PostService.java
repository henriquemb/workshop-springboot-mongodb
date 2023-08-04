package com.github.henriquemb.workshopmongo.services;

import com.github.henriquemb.workshopmongo.domain.Post;
import com.github.henriquemb.workshopmongo.repository.PostRepository;
import com.github.henriquemb.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Post> findContentByText(String text) {
        return postRepository.findContentByText(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);

        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
