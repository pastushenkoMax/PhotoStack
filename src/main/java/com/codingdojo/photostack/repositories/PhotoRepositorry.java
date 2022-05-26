package com.codingdojo.photostack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.photostack.models.Photo;


@Repository
public interface PhotoRepositorry extends CrudRepository<Photo, Long>{
	
	List<Photo> findAll();
	
	@Query(value = "SELECT * FROM photos WHERE post_id = ?1", nativeQuery = true)
	List<Photo> findByID(Long photo);

}