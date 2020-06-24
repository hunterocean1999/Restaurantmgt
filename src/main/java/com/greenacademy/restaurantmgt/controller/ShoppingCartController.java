package com.greenacademy.restaurantmgt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greenacademy.restaurantmgt.entities.Customer;
import com.greenacademy.restaurantmgt.entities.Food;
import com.greenacademy.restaurantmgt.model.CartInfo;
import com.greenacademy.restaurantmgt.model.CustomerInfo;
import com.greenacademy.restaurantmgt.model.ProductInfo;
import com.greenacademy.restaurantmgt.service.CustomerService;
import com.greenacademy.restaurantmgt.service.FoodService;
import com.greenacademy.restaurantmgt.service.OrderService;
import com.greenacademy.restaurantmgt.untils.Utils;

@Controller
@RequestMapping(value = "/shop")
public class ShoppingCartController {

	@Autowired
	private FoodService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;
	

	@RequestMapping(value = "/buyProduct", method = RequestMethod.GET)
	public ModelAndView listProductHandler(HttpServletRequest request, Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop-shoppingCart");
		
		Food product = new Food();
		CartInfo cartInfo = new CartInfo();
		
		if (id != null) {
			product = productService.get(id);
		}
		if (product != null) {
			cartInfo = Utils.getCartInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
		}

		modelAndView.addObject("cartForm", cartInfo);
		return modelAndView;
	}

	
	@RequestMapping(value = "/shoppingCartRemoveProduct", method = RequestMethod.GET)
	public ModelAndView removeProductHandler(HttpServletRequest request, Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop-shoppingCart");

		Food product = new Food();
		CartInfo cartInfo = new CartInfo();

		if (id != null) {
			product = productService.get(id);
		}
		if (product != null) {
			cartInfo = Utils.getCartInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.removeProduct(productInfo);
		}

		modelAndView.addObject("cartForm", cartInfo);
		return modelAndView;
	}

	
	// POST: Update quantity for product in cart
	@RequestMapping(value = "/shoppingCart", method = RequestMethod.POST)
	public ModelAndView shoppingCartUpdateQty(HttpServletRequest request, @ModelAttribute CartInfo cartForm) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop-shoppingCart");

		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.updateQuantity(cartForm);

		modelAndView.addObject("cartForm", cartInfo);
		return modelAndView;
	}

	
	// GET: Show cart.
	@RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
	public ModelAndView shoppingCartHandler(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop-shoppingCart");

		CartInfo cartInfo = Utils.getCartInSession(request);

		modelAndView.addObject("cartForm", cartInfo);
		return modelAndView;
	}

	
	// POST: search customer phone number
	@RequestMapping(value = "/searchPhoneNumber", method = RequestMethod.POST)
	public ModelAndView searchCustomerPhoneNumber(HttpServletRequest request, @RequestParam String phoneNumber) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("shop-checkOut");
		
		String error = null;
		CustomerInfo customerInfo = new CustomerInfo();

		List<Customer> customerList = customerService.getAll();

		for (Customer customer : customerList) {
			if (customer.getPhone().equals(phoneNumber)) {
				customerInfo.setFirstName(customer.getFirstName());
				customerInfo.setLastName(customer.getLastName());
				customerInfo.setStreet(customer.getStreet());
				customerInfo.setDistrict(customer.getDistrict());
				customerInfo.setCity(customer.getCity());
				customerInfo.setEmail(customer.getEmail());
				customerInfo.setNumberPhone(customer.getPhone());
			}
		}

		if (customerInfo.getNumberPhone() == null) {
			error = "Customer not found";
			modelAndView.setViewName("redirect:/shop/shoppingCartCustomer");
		}

		session.setAttribute("errorMessage", error);
		modelAndView.addObject("customerInfo", customerInfo);
		return modelAndView;
	}

	
	// GET: Enter customer information.
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
	public ModelAndView shoppingCartCustomerForm(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("shop-checkOut");

		CartInfo cartInfo = Utils.getCartInSession(request);
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();

		if (customerInfo == null) {
			customerInfo = new CustomerInfo();
		}

		modelAndView.addObject("customerInfo", customerInfo);
		return modelAndView;
	}

	
	// POST: Save customer information and check out.
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
	public ModelAndView shoppingCartCustomerSave(HttpServletRequest request, @ModelAttribute CustomerInfo customerInfo) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("shoppingCartFinalize");

		String error = customerService.castError(customerInfo);

		if (error != null) {
			modelAndView.setViewName("redirect:/shop/shoppingCartCustomer");
		} else {
			CartInfo cartInfo = Utils.getCartInSession(request);
			cartInfo.setCustomerInfo(customerInfo);

			// Store last cart.
			Utils.storeLastOrderedCartInSession(request, cartInfo);

			// Add order to orderList
			orderService.addOrder(cartInfo);
			
			// Reset cart
			Utils.removeCartInSession(request);
		}

		session.setAttribute("errorMessage", error);
		return modelAndView;
	}

	
	@RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
	public ModelAndView shoppingCartFinalize(HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		modelAndView.setViewName("shoppingCartFinalize");

		CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);

		if (lastOrderedCart == null) {
			modelAndView.setViewName("redirect:/shop/shoppingCart");
		}
		
		modelAndView.addObject("lastOrderedCart", lastOrderedCart);
		modelAndView.addObject("cartForm", new CartInfo());
		session.setAttribute("errorMessage", null);
		return modelAndView;
	}
}