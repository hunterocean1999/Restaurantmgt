package com.greenacademy.restaurantmgt.controller;

import java.sql.Date;
import java.text.ParseException;
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

import com.greenacademy.restaurantmgt.entities.Order;
import com.greenacademy.restaurantmgt.entities.OrderDetail;
import com.greenacademy.restaurantmgt.service.OrderDetailService;
import com.greenacademy.restaurantmgt.service.OrderService;

@Controller
@RequestMapping(value = "/admin")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView getAllOrder(HttpServletRequest request, String type,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("admin-order");

		Page<Order> orderList = orderService.getAllWithPagination(page, size, "id");

		modelAndView.addObject("order", new Order());
		modelAndView.addObject("orderList", orderList);
		modelAndView.addObject("currentPage", page);
		modelAndView.addObject("type", type);
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}

	 @RequestMapping(value = "/order/search", method = RequestMethod.POST) 
	 public ModelAndView searchOrder(ModelAndView modelAndView, String fromDate, String toDate) throws ParseException {
		modelAndView.setViewName("admin-order-search"); 
		List<Order> orders = null;
	 
		if ((fromDate != null && fromDate.isEmpty() == false) && (toDate != null && toDate.isEmpty() == false)) { 
			Date fromDateSql = Date.valueOf(fromDate);
			Date toDateSql = Date.valueOf(toDate);
			orders = orderService.search(fromDateSql, toDateSql); 
		}
	 
		modelAndView.addObject("orderList", orders); 
		return modelAndView; 
	}
	 

	@RequestMapping(value = "/addOrder", method = RequestMethod.GET)
	public ModelAndView addOrder(String mode) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-order");
		modelAndView.addObject("order", new Order());
		modelAndView.addObject("mode", mode);
		return modelAndView;
	}

	@RequestMapping(value = "/editOrder", method = RequestMethod.GET)
	public ModelAndView editOrder(Long id, String mode) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-update-order");

		Order order = orderService.get(id);

		modelAndView.addObject("order", order);
		modelAndView.addObject("mode", mode);
		return modelAndView;
	}

	@RequestMapping(value = "/removeOrder", method = RequestMethod.GET)
	public ModelAndView removeStudent(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/order");

		orderService.delete(id);

		return modelAndView;
	}

	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	public ModelAndView updateOrder(@ModelAttribute Order order) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/order");

		if (order.getId() != null) {
			Order order2 = orderService.get(order.getId());
			order2.setStatus(order.getStatus());
			orderService.update(order2);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
	public ModelAndView orderDetail(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin-order-details");

		Order order = orderService.get(id);
		List<OrderDetail> orderDetailList = orderDetailService.getAllbyOrderId(id);

		modelAndView.addObject("order", order);
		modelAndView.addObject("orderDetailList", orderDetailList);
		return modelAndView;
	}
}
