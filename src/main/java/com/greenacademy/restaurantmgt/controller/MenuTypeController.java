package com.greenacademy.restaurantmgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.restaurantmgt.entities.MenuType;
import com.greenacademy.restaurantmgt.service.MenuTypeService;

@Controller
@RequestMapping(value = "/admin")
public class MenuTypeController {

	@Autowired
	private MenuTypeService menuTypeService;
	
	
	@RequestMapping(value="/menuList",method = RequestMethod.GET)
	public ModelAndView getAllFood() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-menuType");
		List<MenuType> menuList = menuTypeService.getAll();
		
		modelAndView.addObject("menuList", menuList);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/addMenu",method = RequestMethod.GET)
	public ModelAndView addFood(String mode) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-menu");
		
		MenuType type = new MenuType();
		
		modelAndView.addObject("type", type);
		modelAndView.addObject("mode",mode);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/updateMenu",method = RequestMethod.POST)
	public ModelAndView updateMenu(@ModelAttribute MenuType type) {
		ModelAndView modelAndView = new ModelAndView();
		if(type.getId() == null) {
			menuTypeService.add(type);
		}
		else {
			menuTypeService.update(type);
		}
		
		modelAndView.setViewName("redirect:/admin/menuList");
	
		return modelAndView;
	}
	
	
	@RequestMapping(value="/editMenu",method = RequestMethod.GET)
	public ModelAndView editFood(Long id, String mode) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-menu");
		MenuType type = this.menuTypeService.get(id);
		modelAndView.addObject("type", type);
		modelAndView.addObject("mode",mode);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/removeMenu",method = RequestMethod.GET)
	public ModelAndView deleteFood(Long id) {

		menuTypeService.delete(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/menuList");	
		return modelAndView;
	}
}
