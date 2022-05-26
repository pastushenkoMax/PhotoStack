package com.codingdojo.photostack.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="photos")
public class Photo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String fileName;
	private String path;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post posts;
	
	
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

	public Post getPost() {
		return posts;
	}

	public void setPost(Post posts) {
		this.posts = posts;
	}

	public Photo() {}
	public Photo(int id, String fileName, String path, Post posts) {
		this.id = id;
		this.fileName = fileName;
		this.path = path;
		this.posts = posts;
	}
	
	
}
