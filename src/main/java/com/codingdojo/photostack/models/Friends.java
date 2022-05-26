package com.codingdojo.photostack.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friends")
public class Friends {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_friend")
	private User friendID;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getFriendID() {
		return friendID;
	}
	public void setFriendID(User friendID) {
		this.friendID = friendID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Friends() {}
	public Friends(Long id, User friendID, User user) {
		this.id = id;
		this.friendID = friendID;
		this.user = user;
	}
	
}
