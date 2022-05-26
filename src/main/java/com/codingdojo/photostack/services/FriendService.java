package com.codingdojo.photostack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.photostack.models.Friends;
import com.codingdojo.photostack.repositories.FriendRepositorry;

@Service
public class FriendService {

	@Autowired
	private FriendRepositorry friendRepositorry;
	public FriendService(FriendRepositorry friendRepositorry) {
			this.friendRepositorry = friendRepositorry;
	    }
	public void save(Friends f) {
		friendRepositorry.save(f);
	}

	public List<Friends> findAll(){
		return friendRepositorry.findAll();
	}
	public List<Friends> findAllFriendsById(Long id) {
		return friendRepositorry.findByUser(id);
	}
	public Optional <Friends> findFriends(Long id) {
		return friendRepositorry.findFriend(id);
	}
}
