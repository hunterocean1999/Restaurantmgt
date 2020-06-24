package com.greenacademy.restaurantmgt.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.restaurantmgt.entities.User;
import com.greenacademy.restaurantmgt.service.ImageService;
import com.greenacademy.restaurantmgt.service.UserService;

@Controller
@RequestMapping(value = "/profile")
public class UserProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ModelAndView getProfile(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("profile");

		String userName = principal.getName();
		User user = userService.getByUserName(userName);

		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/profileUpdate/{userId}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable Long userId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("profile-update");

		User user = userService.get(userId);

		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/profileUpdate", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();

		String uploadRootPath = request.getServletContext().getRealPath("uploads");
		String imageName = imageService.uploadFile(uploadRootPath, user.getAvatarFile());
		String error = userService.castError(user);

		modelAndView.setViewName("redirect:/profile/myProfile");

		if (imageName != null && imageName.isEmpty() == false) {
			user.setAvatarName(imageName);
		} else {
			user.setAvatarName("2.png");
		}

		if (error != null) {
			modelAndView.setViewName("redirect:/profile/profileUpdate/" + user.getId());
		} else {
			userService.update(user);
		}

		session.setAttribute("errorMessage", error);
		return modelAndView;
	}
}
