package com.codingdojo.photostack.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.photostack.models.Photo;
import com.codingdojo.photostack.models.Post;
import com.codingdojo.photostack.repositories.PostRepositorry;



@Service
public class PostService {
    
	@Autowired
    private PostRepositorry pRepository;
    public  PostService(PostRepositorry pRepository) {
		this.pRepository = pRepository;
    }
    
    public Post createNewPost(@Valid Post post, Photo photo, BindingResult result) {
    	if(result.hasErrors()) {
    		return null;
    		}else {
    			post.setPhoto(photo);
    			return pRepository.save(post);
    		}
		}

	public Post findPostByID(Long id) {
		Optional<Post> post = pRepository.findById(id);
    	if(post.isPresent()) {
    		return post.get();
    		} else {
    			return null;
    			}
	}

	public void deletePost(Long id) {
		pRepository.deleteById(id);
	}

	public List<Post> findAll() {
		return pRepository.findAll();
	}
	
	public List<Post> findAllPostByID(Long id) {
		return pRepository.findAllById(id);
	}
}
