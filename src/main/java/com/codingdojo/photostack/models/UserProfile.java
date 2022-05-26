package com.codingdojo.photostack.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="profile")
public class UserProfile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String fileName;
	private String path;
	@NotNull(message="Required!")
	@Column(columnDefinition="TEXT")
    private String user_description;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUser_description() {
		return user_description;
	}

	public void setUser_description(String user_description) {
		this.user_description = user_description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserProfile() {}
	public UserProfile(int id, String fileName, String path, String user_description, User user) {
		this.id = id;
		this.fileName = fileName;
		this.path = path;
		this.user_description = user_description;
		this.user = user;
	}
}
