package com.codingdojo.photostack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.photostack.models.UserProfile;
@Repository
public interface UserProfileRepositorry extends CrudRepository<UserProfile, Integer>{
	
	List<UserProfile> findAll();
	
	@Query(value = "SELECT * FROM profile WHERE user_id = ?1", nativeQuery = true)
	List<UserProfile> findByUser(Long id);
}
