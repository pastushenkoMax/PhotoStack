package com.codingdojo.photostack.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codingdojo.photostack.models.UserProfile;
import com.codingdojo.photostack.repositories.UserProfileRepositorry;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepositorry uProfileRepositorry;
	public UserProfileService(UserProfileRepositorry userProfileRepositorry) {
			this.uProfileRepositorry = userProfileRepositorry;
	    }
	public void save(UserProfile uProfile) {
		uProfileRepositorry.save(uProfile);
	}
	
	public void saveImg(MultipartFile imgFile, UserProfile userProfile) throws IOException {
    	Path curentPath = Paths.get(".");
    	Path absolutePaths = curentPath.toAbsolutePath();
    	userProfile.setPath(absolutePaths + "/src/main/resources/static/photos/");
    	byte[] bytes = imgFile.getBytes();
    	Path path = Paths.get(userProfile.getPath() + imgFile.getOriginalFilename());
    	Files.write(path, bytes);
    	
    	
    }
	
	public List<UserProfile> findAll() {
		return uProfileRepositorry.findAll();
	}
	public List<UserProfile> findByUserID(Long id) {
		return uProfileRepositorry.findByUser(id);
	}
}
