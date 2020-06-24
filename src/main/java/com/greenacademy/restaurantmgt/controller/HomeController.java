package com.greenacademy.restaurantmgt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.restaurantmgt.entities.Food;
import com.greenacademy.restaurantmgt.entities.MenuType;
import com.greenacademy.restaurantmgt.service.FoodService;
import com.greenacademy.restaurantmgt.service.MenuTypeService;

@Controller
public class HomeController {

	@Autowired
	private FoodService foodService; 
	
	@Autowired
	private MenuTypeService menuTypeService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView HomeFood(HttpServletRequest request,
								 @RequestParam (defaultValue ="0") Integer page,
								 @RequestParam (defaultValue ="6") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		
		Page<Food> productList = foodService.getAllWithPagination(page, size, "id");
		List<MenuType> allMenuType = menuTypeService.getAll();
		
		modelAndView.setViewName("admin-home-food");
		modelAndView.addObject("productList", productList);
		modelAndView.addObject("allMenuType", allMenuType);
		modelAndView.addObject("currentPage",page);
		session.setAttribute("errorMessage", null);
		modelAndView.addObject("id", null);
		return modelAndView;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProduct(HttpServletRequest request, String foodName,
									  @RequestParam (defaultValue ="0") Integer page,
									  @RequestParam (defaultValue ="6") Integer size) {
		
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		
		Page<Food> productList = this.foodService.getAllByFoodNameWithPagination(page, size, "id", foodName);
		List<MenuType> allMenuType = menuTypeService.getAll();
		
		modelAndView.setViewName("admin-home-food");
		modelAndView.addObject("productSearch", new Food());
		modelAndView.addObject("productList", productList);
		modelAndView.addObject("allMenuType", allMenuType);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/foodByMenu", method = RequestMethod.GET)
	public ModelAndView foodByMenu(Long menuId,
									@RequestParam (defaultValue ="0") Integer page,
									@RequestParam (defaultValue ="6") Integer size) {
		ModelAndView modelAndView = new ModelAndView();	
		
		Page<Food> productList = this.foodService.getAllByMenuTypeWithPagination(page, size, "id", menuId);
		List<MenuType> allMenuType = menuTypeService.getAll();
		
		modelAndView.setViewName("admin-home-food");
		modelAndView.addObject("productSearch", new Food());
		modelAndView.addObject("productList", productList);
		modelAndView.addObject("allMenuType", allMenuType);
		modelAndView.addObject("id", menuId);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/admin/removeCustomer",method = RequestMethod.GET)
	public ModelAndView deleteFood(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/foods");	

		foodService.delete(id);
		
		return modelAndView;
	}	
}
