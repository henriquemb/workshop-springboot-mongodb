package com.github.henriquemb.springboot_mongodb.repository;

import com.github.henriquemb.springboot_mongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByTitleContaining(String text);

    @Query("{ content: { $regex: ?0, $options: 'i' } }")
    List<Post> findContentByText(String text);
}
