package com.greenacademy.restaurantmgt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.restaurantmgt.entities.TotalFinance;
import com.greenacademy.restaurantmgt.service.TotalFinanceService;

@Controller
@RequestMapping(value = "/admin")
public class TotalFinanceController {
	
	@Autowired
	private TotalFinanceService totalFinanceService;
	
	
	@RequestMapping(value = "/totalFinanceList", method = RequestMethod.GET)
	public ModelAndView getTotalFinanceList(HttpServletRequest request, 
									@RequestParam(defaultValue="0") Integer page, 
									@RequestParam(defaultValue="5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("admin-totalFinanceList");
		
		Page<TotalFinance> totalFinanceList = totalFinanceService.getAllWithPagination(page, size, "id");;
				
		modelAndView.addObject("totalFinanceList", totalFinanceList);
		modelAndView.addObject("currentPage", page);
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/removeTotalFinance", method = RequestMethod.GET)
	public ModelAndView removeTotalFinance(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/totalFinanceList");	

		totalFinanceService.delete(id);
		
		return modelAndView;
	}
}	
