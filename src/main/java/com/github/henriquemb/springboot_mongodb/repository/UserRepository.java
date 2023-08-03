package com.github.henriquemb.springboot_mongodb.repository;

import com.github.henriquemb.springboot_mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
