package com.sandu.cashcompass.repository;

import com.sandu.cashcompass.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User , String> {
    User findByEmail(String email);
    User findByUsername(String username);
}
