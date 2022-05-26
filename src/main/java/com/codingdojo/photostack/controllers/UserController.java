package com.codingdojo.photostack.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codingdojo.photostack.models.LoginUser;
import com.codingdojo.photostack.models.User;
import com.codingdojo.photostack.models.UserProfile;
import com.codingdojo.photostack.services.UserProfileService;
import com.codingdojo.photostack.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService uService;
	@Autowired
	private UserProfileService userProfileService;
	
	@GetMapping("/")
	public String login(Model model, HttpSession session) {
		if (session.getAttribute("id") != null) {
			return "redirect:/homepage";
			}else {
				model.addAttribute("createLogin", new LoginUser());
				return "index.jsp";
				}
		}
	
	@GetMapping("/new_user")
	public String register(Model model, HttpSession session) {
		if (session.getAttribute("id") != null) {
			return "redirect:/";
			}else {
				model.addAttribute("createUser", new User());
				return "register.jsp";
				}
		}
	
	@PostMapping("/register_user")
	public String registerNewUser(@Valid @ModelAttribute("createUser")User createUser,
			BindingResult result, Model model, HttpSession session) {
		User newUser = uService.registerUser(createUser, result);
	    if(result.hasErrors()) {
	    	return "register.jsp";
	        }
	    session.setAttribute("id", newUser.getId());
	    return "redirect:/user_profile";
	    }
	
	
	@GetMapping("/user_profile")
	public String userProfile(@Valid @ModelAttribute("newProfile")UserProfile newProfile,
			BindingResult result, Model model, HttpSession session) {
		
		model.addAttribute("user_id", (Long)session.getAttribute("id"));
		return "newProfile.jsp";
	}
	
	@PostMapping("/create_user_profile")
	public String createProfile(Model model,@Valid @ModelAttribute("createProfile") UserProfile createProfile,
			@RequestParam ("imageFile")MultipartFile imageFile, 
			BindingResult result, HttpSession session) {
		String returnValue = "redirect:/homepage";
		if(result.hasErrors()) {
	        return "newProfile.jsp";
	        }
		
		try {
			createProfile.setFileName(imageFile.getOriginalFilename());
			userProfileService.saveImg(imageFile, createProfile);
			userProfileService.save(createProfile);
			returnValue = "redirect:/profile/";
		} catch (IOException e) {
			
			e.printStackTrace();
			returnValue = "newProfile.jsp";
		}
		Long id = (Long)session.getAttribute("id");
		return returnValue+id;
	}
	
	@PostMapping("/login_user")
	public String loginUser( 
			@Valid @ModelAttribute("createLogin") LoginUser createLogin, BindingResult result, 
			Model model, HttpSession session) {
		User logUser = uService.loginUser(createLogin, result);
		if(result.hasErrors()) {
	        return "index.jsp";
	        }
	    session.setAttribute("id", logUser.getId());
	    Long id = logUser.getId();
	    return "redirect:/profile/"+id;
	    }
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.setAttribute("id", null);
	    return "redirect:/";
	    }

}
