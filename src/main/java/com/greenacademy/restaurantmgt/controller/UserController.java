package com.greenacademy.restaurantmgt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.restaurantmgt.entities.User;
import com.greenacademy.restaurantmgt.entities.UserType;
import com.greenacademy.restaurantmgt.repository.UserTypeRepository;
import com.greenacademy.restaurantmgt.service.ImageService;
import com.greenacademy.restaurantmgt.service.UserService;
import com.greenacademy.restaurantmgt.service.UserTypeService;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ModelAndView getUserList(HttpServletRequest request, String filter,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("admin-userList");

		Page<User> userList = null;

		if (filter != null && filter.isEmpty() == false) {
			userList = userService.getAllByUserTypeWithPagination(page, size, "id", filter);
		} else {
			userList = userService.getAllWithPagination(page, size, "id");
		}

		modelAndView.addObject("userList", userList);
		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("filter", filter);
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.GET)
	public ModelAndView searchUserList(HttpServletRequest request, @RequestParam String filter,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("admin-user-search");

		String userName = filter;
		Page<User> userList = userService.getAllByUserNameWithPagination(page, size, "id", userName);

		modelAndView.addObject("userList", userList);
		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("filter", filter);
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(String mode, String filter) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-user");

		UserType userType = new UserType();
		User user = new User();

		if ("admin".equalsIgnoreCase(filter)) {
			userType = userTypeRepository.getOne(1L);
			user.setUserType(userType);
		} else if ("manager".equalsIgnoreCase(filter)) {
			userType = userTypeRepository.getOne(2L);
			user.setUserType(userType);
		} else if ("employee".equalsIgnoreCase(filter)) {
			userType = userTypeRepository.getOne(3L);
			user.setUserType(userType);
		}

		modelAndView.addObject("user", user);
		modelAndView.addObject("mode", mode);
		return modelAndView;
	}

	@RequestMapping(value = "/editUser/{userId}", method = RequestMethod.GET)
	public ModelAndView editUser(@PathVariable Long userId, String mode) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-user");

		User user = userService.get(userId);
		List<UserType> allUserType = userTypeService.getAll();

		modelAndView.addObject("user", user);
		modelAndView.addObject("mode", mode);
		modelAndView.addObject("allUserType", allUserType);
		return modelAndView;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, @ModelAttribute User user) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();

		String uploadRootPath = request.getServletContext().getRealPath("uploads");
		String imageName = imageService.uploadFile(uploadRootPath, user.getAvatarFile());
		String error = userService.castError(user);

		UserType userType = user.getUserType();

		modelAndView.setViewName("redirect:/admin/userList");

		if (imageName != null && imageName.isEmpty() == false) {
			user.setAvatarName(imageName);
		} else {
			user.setAvatarName("2.png");
		}

		if (user.getId() == null) {
			if (error != null) {
				modelAndView.setViewName("admin-update-user");
				user.setUserType(userType);
				modelAndView.addObject("user", user);
				modelAndView.addObject("mode", "ADD");
			} else {
				user.setIsActive(true);
				if ("admin".equalsIgnoreCase(userType.getTypeName())) {
					userService.addAdmin(user);
				} else if ("manager".equalsIgnoreCase(userType.getTypeName())) {
					userService.addManager(user);
				} else if ("employee".equalsIgnoreCase(userType.getTypeName())) {
					userService.addEmployee(user);
				} else {
					userService.add(user);
				}
			}
		} else {
			if (error != null) {
				modelAndView.setViewName(
						"redirect:/admin/editUser/" + user.getId() + "?mode=EDIT&type=" + userType.getTypeName());
//				modelAndView.setViewName("admin-update-user");
//				user.setUserType(userType);
//				modelAndView.addObject("user", user);
//				modelAndView.addObject("mode", "UPDATE");
			} else {
				userService.update(user);
			}
		}

		session.setAttribute("errorMessage", error);
		return modelAndView;
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public ModelAndView removeUser(@PathVariable Long userId, String filter) {
		ModelAndView modelAndView = new ModelAndView();

		if (filter != null || filter.isEmpty() == false) {
			modelAndView.setViewName("redirect:/admin/userList?filter=" + filter);
		} else {
			modelAndView.setViewName("redirect:/admin/userList");
		}

		userService.delete(userId);

		return modelAndView;
	}

	@RequestMapping(value = "/userDetails/{userId}", method = RequestMethod.GET)
	public ModelAndView studentDetail(@PathVariable Long userId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-user-details");

		User user = userService.get(userId);

		modelAndView.addObject("user", user);
		return modelAndView;
	}
}
