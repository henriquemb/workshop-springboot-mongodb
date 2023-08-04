package com.github.henriquemb.springboot_mongodb.config;

import com.github.henriquemb.springboot_mongodb.domain.Post;
import com.github.henriquemb.springboot_mongodb.domain.User;
import com.github.henriquemb.springboot_mongodb.repository.PostRepository;
import com.github.henriquemb.springboot_mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Gray", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("2023-01-22"), "Partiu viagem", "Vou viajar para São Paulo.", maria);
        Post post2 = new Post(null, sdf.parse("2023-03-02"), "Bom dia", "Acordei feliz hoje!", maria);
        Post post3 = new Post(null, sdf.parse("2023-06-18"), "#formei", "Minha formatura será hoje.", alex);

        postRepository.saveAll(Arrays.asList(post1, post2, post3));
    }
}
