package com.codingdojo.photostack.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.photostack.models.Friends;
@Repository
public interface FriendRepositorry extends CrudRepository<Friends, Integer>{
	
	List<Friends> findAll();

	@Query(value = "SELECT * FROM friends WHERE user_id = ?1", nativeQuery = true)
	List<Friends> findByUser(Long id);
	
	@Query(value = "SELECT user_friend FROM friends where user_id = 6", nativeQuery = true)
	Optional<Friends> findFriend(Long id);
}
