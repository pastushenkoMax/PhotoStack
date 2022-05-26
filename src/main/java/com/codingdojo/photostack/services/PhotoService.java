package com.codingdojo.photostack.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codingdojo.photostack.models.Photo;
import com.codingdojo.photostack.repositories.PhotoRepositorry;


@Service
public class PhotoService {

	@Autowired
	private PhotoRepositorry pRepositorry;
	public PhotoService(PhotoRepositorry pRepositorry) {
			this.pRepositorry = pRepositorry;
	    }
	public void save(Photo photo) {
		pRepositorry.save(photo);
	}
	
	public void saveImg(MultipartFile imgFile, Photo photo) throws IOException {
    	Path curentPath = Paths.get(".");
    	Path absolutePaths = curentPath.toAbsolutePath();
    	photo.setPath(absolutePaths + "/src/main/resources/static/photos/");
    	byte[] bytes = imgFile.getBytes();
    	Path path = Paths.get(photo.getPath() + imgFile.getOriginalFilename());
    	Files.write(path, bytes);
    	
    	
    }
	public List<Photo> listAll(Long id) {
		if (id != null) {
			return pRepositorry.findByID(id);
			}
		return pRepositorry.findAll();
		}
}