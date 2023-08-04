package com.github.henriquemb.workshopmongo.config;

import com.github.henriquemb.workshopmongo.domain.Post;
import com.github.henriquemb.workshopmongo.domain.User;
import com.github.henriquemb.workshopmongo.dto.AuthorDTO;
import com.github.henriquemb.workshopmongo.dto.CommentDTO;
import com.github.henriquemb.workshopmongo.repository.PostRepository;
import com.github.henriquemb.workshopmongo.repository.UserRepository;
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

        Post post1 = new Post(null, sdf.parse("2023-01-22"), "Partiu viagem", "Vou viajar para São Paulo.", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("2023-03-02"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
        Post post3 = new Post(null, sdf.parse("2023-06-18"), "#formei", "Minha formatura será hoje.", new AuthorDTO(alex));

        CommentDTO comment1 = new CommentDTO(sdf.parse("2023-01-22"), "Boa viagem!", new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO(sdf.parse("2023-01-23"), "Aproveite!", new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO(sdf.parse("2023-06-18"), "Parabéns!", new AuthorDTO(maria));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post3.getComments().add(comment3);

        postRepository.saveAll(Arrays.asList(post1, post2, post3));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        alex.getPosts().add(post3);

        userRepository.saveAll(Arrays.asList(maria, alex));
    }
}
