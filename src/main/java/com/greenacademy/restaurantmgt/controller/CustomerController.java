package com.greenacademy.restaurantmgt.controller;


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

import com.greenacademy.restaurantmgt.entities.Customer;
import com.greenacademy.restaurantmgt.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	public CustomerService customerService;

	@RequestMapping(value = "/customerList", method = RequestMethod.GET)
	public ModelAndView Customer(
			@RequestParam (defaultValue ="0") Integer page,
			@RequestParam (defaultValue ="6") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		Page<Customer> customerList = customerService.getAllWithPagination(page, size, "id");
		
		modelAndView.setViewName("list-customer");
		modelAndView.addObject("currentPage",page);
		modelAndView.addObject("customerList", customerList);
		modelAndView.addObject("id", null);
		modelAndView.addObject("numberPhone", null);
		return modelAndView;
	}
	

	@RequestMapping(value="/getCustomer",method = RequestMethod.GET)
	public ModelAndView getFood(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view-customer");
		
		Customer customer = customerService.get(id);
		
		modelAndView.addObject("customer", customer);
		return modelAndView;
	}
	
	@RequestMapping(value="/removeCustomer",method = RequestMethod.GET)
	public ModelAndView deleteFood(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/customerList");	

		customerService.delete(id);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/editCustomer",method = RequestMethod.GET)
	public ModelAndView editFood(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-customer");
		
		Customer customer = this.customerService.get(id);
		modelAndView.addObject("customer", customer);
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public ModelAndView updateOrder(@ModelAttribute Customer customer) {
		ModelAndView modelAndView = new ModelAndView();
		if(customer.getId() == null) {
			
		}else {
			customerService.update(customer);
		}
		
		modelAndView.setViewName("redirect:/customerList");
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/searchCustomer", method = RequestMethod.POST)
	public ModelAndView searchFood(HttpServletRequest request, @RequestParam Integer numberPhone, 
									@RequestParam (defaultValue ="0") Integer page,
									@RequestParam (defaultValue ="5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list-customer");
		HttpSession session = request.getSession();
		
		Page<Customer> customerList = this.customerService.getAllByCustomerNumberPhoneWithPagination(page, size, "id", numberPhone);
		modelAndView.addObject("customer", new Customer());
		modelAndView.addObject("customerList", customerList);
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}	
}
