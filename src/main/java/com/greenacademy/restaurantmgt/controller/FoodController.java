package com.greenacademy.restaurantmgt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.restaurantmgt.entities.Food;
import com.greenacademy.restaurantmgt.entities.FoodType;
import com.greenacademy.restaurantmgt.entities.MenuType;
import com.greenacademy.restaurantmgt.repository.FoodTypeRepository;
import com.greenacademy.restaurantmgt.service.FoodService;
import com.greenacademy.restaurantmgt.service.ImageService;
import com.greenacademy.restaurantmgt.service.MenuTypeService;

@Controller
@RequestMapping(value = "/admin")
public class FoodController {
	
	@Autowired
	private FoodService foodService; 
	
	@Autowired
	private ImageService imageService; 
	
	@Autowired
	private FoodTypeRepository foodTypeRepository;
	
	@Autowired
	private MenuTypeService menuTypeService;
	
	
	@RequestMapping(value="/foods",method = RequestMethod.GET)
	public ModelAndView getAllFood(HttpServletRequest request, String type, 
									@RequestParam (defaultValue ="0") Integer page,
									@RequestParam (defaultValue ="5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("admin-food");
		
		Page<Food> foodList = null;
		if (type != null && type.isEmpty() == false)
		{
			foodList = foodService.getAllByFoodTypeWithPagination(page, size, "id", type);
		} else 
		{
			foodList = foodService.getAllWithPagination(page, size, "id");
		}
		
		modelAndView.addObject("food", new Food());
		modelAndView.addObject("foodList", foodList);
		modelAndView.addObject("currentPage",page);
		modelAndView.addObject("type", type);
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/searchFood", method = RequestMethod.POST)
	public ModelAndView searchFood(HttpServletRequest request, @RequestParam String search, 
									@RequestParam (defaultValue ="0") Integer page,
									@RequestParam (defaultValue ="5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("admin-food");
		
		String foodName = search;
		
		Page<Food> foodList = this.foodService.getAllByFoodNameWithPagination(page, size, "id", foodName);
		modelAndView.addObject("foodList", foodList);
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/addFood",method = RequestMethod.GET)
	public ModelAndView addFood(String mode, String type) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-food");
		
		FoodType foodType = new FoodType();
		Food food = new Food();
		
		if ("Food".equalsIgnoreCase(type)) {
			foodType = foodTypeRepository.getOne(1L);
			food.setFoodType(foodType);
		}
		else if ("Drink".equalsIgnoreCase(type)) {
			foodType = foodTypeRepository.getOne(2L);
			food.setFoodType(foodType);
		}
		
		List<MenuType> allMenuType = menuTypeService.getAll();
		modelAndView.addObject("allMenuType", allMenuType);
		modelAndView.addObject("food", food);
		modelAndView.addObject("mode", mode);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/editFood",method = RequestMethod.GET)
	public ModelAndView editFood(Long id, String mode) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-food");
		
		List<MenuType> allMenuType = menuTypeService.getAll();
		
		Food food = this.foodService.get(id);
		modelAndView.addObject("allMenuType", allMenuType);
		modelAndView.addObject("food", food);
		modelAndView.addObject("mode",mode);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/updateFood",method = RequestMethod.POST)
	public ModelAndView updateFood(@ModelAttribute Food food, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		
		String uploadRootPath = request.getServletContext().getRealPath("uploads");
		String imageName = imageService.uploadFile(uploadRootPath,food.getImageFile());
		String error = foodService.castError(food);
		
		modelAndView.setViewName("redirect:/admin/foods");

		if(imageName != null && imageName.isEmpty() == false) {
			food.setImage(imageName);
		} else {
			food.setImage("2.png");
		}
		
		if (food.getId() == null) {
			if(error != null) {
				modelAndView.setViewName("redirect:/admin/addFood?mode=ADD&type=" + food.getFoodType().getTypeName());	
			} else {
				food.setNotify(true);
				foodService.add(food);
			}
		} else {
			if(error != null) {
				modelAndView.setViewName("redirect:/admin/editFood?mode=EDIT&id=" + food.getId());	
			} else {
				foodService.update(food);
			}
		}
		
		session.setAttribute("errorMessage", error);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/removeFood",method = RequestMethod.GET)
	public ModelAndView deleteFood(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/foods");	

		foodService.delete(id);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/getFood",method = RequestMethod.GET)
	public ModelAndView getFood(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-view-food");
		
		Food food = foodService.get(id);
		
		modelAndView.addObject("food", food);
		return modelAndView;
	}
	
	
//	@RequestMapping(value="/getProduct",method = RequestMethod.GET)
//	public ModelAndView getProduct(Long id) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("admin-view-product");
//		
//		Food food = foodService.get(id);
//		
//		modelAndView.addObject("food", food);
//		return modelAndView;
//	}
}
