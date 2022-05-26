package com.codingdojo.photostack.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codingdojo.photostack.models.Friends;
import com.codingdojo.photostack.models.Photo;
import com.codingdojo.photostack.models.Post;
import com.codingdojo.photostack.models.User;
import com.codingdojo.photostack.services.FriendService;
import com.codingdojo.photostack.services.PhotoService;
import com.codingdojo.photostack.services.PostService;
import com.codingdojo.photostack.services.UserProfileService;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	@Autowired
	private PostService postService;
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private FriendService uFriendService;
	
	@GetMapping("/profile/{id}")
	public String profilePage(Model model,@PathVariable("id") Long id,@ModelAttribute("createFriend") Friends createFriend, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/";
		}
		model.addAttribute("profilePage", userProfileService.findByUserID(id));
		model.addAttribute("posts", postService.findAllPostByID(id));
		model.addAttribute("user_id", (Long)session.getAttribute("id"));
		Long user_id = (Long) session.getAttribute("id");
		model.addAttribute("ID",user_id);
		
		if(id != user_id) {
			return "otherUserPage.jsp";
		}else {
			return "userPage.jsp";	
		}
	}

	@GetMapping("/homepage")
	public String homePage(Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/";
		}
		Long id = (Long) session.getAttribute("id");
		model.addAttribute("ID",id);
		model.addAttribute("profilePage", userProfileService.findByUserID(id));
		model.addAttribute("posts", postService.findAll());
	    return "homepage.jsp";
	    }
	
	@GetMapping("/addPost")
	public String addPost(
			Model model,
			@ModelAttribute("createPost")Post createPost,
			HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/login";
			}else {
				model.addAttribute("user_id", (Long)session.getAttribute("id"));
				return "addPhoto.jsp";
				}
		}
				
	@PostMapping("/newPost")
	public String newPost(Model model,@Valid @ModelAttribute("createPost") Post createPost,
			@RequestParam ("imageFile")MultipartFile imageFile, 
			BindingResult result, HttpSession session) {
		String returnValue = "redirect:/homepage";
		if(result.hasErrors()) {
	        return "redirect:/addPhoto";
	        }
		
		try {
			Photo photo = new Photo();
			photo.setFileName(imageFile.getOriginalFilename());
			photo.setPost(createPost);
			photoService.saveImg(imageFile, photo);
			postService.createNewPost(createPost, photo, result);
			photoService.save(photo);
			returnValue = "redirect:/homepage";
		} catch (IOException e) {
			
			e.printStackTrace();
			returnValue = "redirect:/addPhoto";
		}
		return returnValue;
	}
	
	@GetMapping("/post/{id}")
	public String openPost(@PathVariable("id") Long id, Model model,
			@ModelAttribute("post")Post post, HttpSession session) {
		
		Post p = postService.findPostByID(id);
		List<Photo> photo = photoService.listAll(id);
		model.addAttribute("photo", photo);
		model.addAttribute("post", p);
		Long user_id = (Long) session.getAttribute("id");
		model.addAttribute("ID",user_id);
		if (session.getAttribute("id") == p.getUser().getId()) {
			return "userPhotoDescription.jsp";
			
		}else {
			return "photoDescription.jsp";
			}
		}
	
	@GetMapping("/post/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model,
			@ModelAttribute("editPost")Post editPost, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/post/{id}";
			} else {
				Post p = postService.findPostByID(id);
				model.addAttribute("user_id", (Long)session.getAttribute("id"));
				model.addAttribute("post", p);
				return "editPost.jsp";
				}
		}
	
	@PutMapping("/post/{id}/update")
	public String updatePost(@PathVariable("id") Long id,Model model,
			@Valid @ModelAttribute("editPost") Post editPost,
			@RequestParam ("imageFile")MultipartFile imageFile,
			BindingResult result, HttpSession session) {
		String returnValue = "redirect:/homepage";
		if(result.hasErrors()) {
			return "redirect:/post/{id}/edit";
	        }
		
		try {
			Photo photo = new Photo();
			photo.setFileName(imageFile.getOriginalFilename());
			photo.setPost(editPost);
			photoService.saveImg(imageFile, photo);
			postService.createNewPost(editPost, photo, result);
			photoService.save(photo);
			returnValue = "redirect:/homepage";
		} catch (IOException e) {
			
			e.printStackTrace();
			returnValue = "redirect:/post/{id}/edit";
		}
		return returnValue;
	}
	
	@RequestMapping(value="/post/delete/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect://post/{id}";
			} else {
				postService.deletePost(id);
				return "redirect:/homepage";
			}	
	}
	
	
	@PostMapping("/newFriend")
	public String newFriend(Model model,@Valid @ModelAttribute("createFriend") Friends createFriend,
			BindingResult result, HttpSession session) {
		uFriendService.save(createFriend);
		return "redirect:/homepage";
	}
	
	@GetMapping("/all_friends/{id}")
	public String allFrinds(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/";
		}
		model.addAttribute("profilePage", userProfileService.findByUserID(id));
		model.addAttribute("posts", postService.findAllPostByID(id));
		model.addAttribute("users", uFriendService.findAllFriendsById(id));
		Long uid = (Long) session.getAttribute("id");
		model.addAttribute("ID",uid);
	    return "friendsPage.jsp";
	}
	
	@GetMapping("/chat/{user_id}/{friend_id}")
	public String chat(@PathVariable("user_id")User user_id, @PathVariable("friend_id")User friend_id, Model model, HttpSession session) {
		
		return null;
	}
}
