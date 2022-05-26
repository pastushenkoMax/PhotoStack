package com.codingdojo.photostack.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.photostack.models.User;


@Repository
public interface UserRepositorry extends CrudRepository<User, Long> {
 
 Optional<User> findByEmail(String email);
 
}
