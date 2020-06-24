package com.greenacademy.restaurantmgt.service.impl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.Customer;
import com.greenacademy.restaurantmgt.entities.Food;
import com.greenacademy.restaurantmgt.entities.Order;
import com.greenacademy.restaurantmgt.entities.OrderDetail;
import com.greenacademy.restaurantmgt.entities.TotalFinance;
import com.greenacademy.restaurantmgt.model.CartInfo;
import com.greenacademy.restaurantmgt.model.CartLineInfo;
import com.greenacademy.restaurantmgt.model.CustomerInfo;
import com.greenacademy.restaurantmgt.repository.CustomerRepository;
import com.greenacademy.restaurantmgt.repository.OrderDetailRepository;
import com.greenacademy.restaurantmgt.repository.OrderPagingAndSortingRepository;
import com.greenacademy.restaurantmgt.repository.OrderRepository;
import com.greenacademy.restaurantmgt.service.CustomerService;
import com.greenacademy.restaurantmgt.service.FoodService;
import com.greenacademy.restaurantmgt.service.OrderService;
import com.greenacademy.restaurantmgt.service.TotalFinanceService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private FoodService foodService;

	@Autowired
	private TotalFinanceService totalFinanceService;

	@Autowired
	private OrderPagingAndSortingRepository orderPagingAndSortingRepository;

	
	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order get(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.getOne(id);
	}

	@Override
	public Order addOrder(CartInfo cartInfo) {
		Integer customerExist = 0;
		Integer sameDate = 0;
		Integer count = 0;
		Long customerId = null;

		Order order = new Order();
		Customer customer = new Customer();

		/* Check customerList: if customer existed, do not add customer info */
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		List<Customer> customerList = customerService.getAll();
		for (Customer cus : customerList) {
			if ((cus.getPhone().equals(customerInfo.getNumberPhone()))
					&& (cus.getFirstName().equalsIgnoreCase(customerInfo.getFirstName()))) {
				customerExist = 1;
				customerId = cus.getId();
				break;
			}
		}
		if (customerExist == 0) {
			customer.setFirstName(customerInfo.getFirstName());
			customer.setLastName(customerInfo.getLastName());
			customer.setEmail(customerInfo.getEmail());
			customer.setPhone(customerInfo.getNumberPhone());
			customer.setStreet(customerInfo.getStreet());
			customer.setDistrict(customerInfo.getDistrict());
			customer.setCity(customerInfo.getCity());
		} else if (customerExist == 1) {
			customer = customerRepository.getOne(customerId);
		}
		/* ----------------------------------------------------------------- */

		/* Cáº­p nháº­t Order */
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		order.setOrderDate(today);

		order.setAmount(cartInfo.getAmountTotal());
		order.setCustomer(customer);
		orderRepository.save(order);
		/* -------------- */

		/* Cáº­p nháº­t Order detail */
		List<CartLineInfo> lines = cartInfo.getCartLines();
		for (CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setAmount(line.getAmount());
			detail.setPrice(line.getProductInfo().getGiasp());
			detail.setQuanity(line.getQuantity());
			Long code = line.getProductInfo().getMasp();
			Food product = this.foodService.get(code);
			detail.setFood(product);

			orderDetailRepository.save(detail);
		}
		/* --------------------- */
		
		/* Cáº­p nháº­t Finance */
		List<TotalFinance> financeList = totalFinanceService.getAll();
		for (TotalFinance finance : financeList) {
			if (finance.getDate().equals(order.getOrderDate())) {
				sameDate = 1;
				totalFinanceService.addOrderAmountToRevenue(finance, order.getAmount());
				totalFinanceService.calculateProfit(finance);
				totalFinanceService.update(finance);
				break;
			}
		}
		if (sameDate == 0) {
			TotalFinance totalFinance = new TotalFinance();
			totalFinance.setDate(order.getOrderDate());
			totalFinance.setRevenue(order.getAmount());
			totalFinanceService.calculateProfit(totalFinance);
			totalFinanceService.update(totalFinance);
		}
		/* ---------------- */

//		Cap nha so Luong
//		List<OrderDetail> detail = orderDetailRepository.findAll();
//		List<Food> foodList = foodService.getAll();
		Long foodId = null;
		for (CartLineInfo line1 : lines) {
			List<Food> foodList = foodService.getAll();
			for (Food food : foodList) {
				if (line1.getProductInfo().getTensp().equals(food.getFoodName())) {
					count = 1;
					food.setAmount((food.getAmount() - line1.getQuantity()));
					foodService.update(food);
					foodId = food.getId();
				}
			}
		}
		if (count == 1) {
			Food food = foodService.get(foodId);
			if (food.getAmount() == 0) {
				food.setNotify(false);
				foodService.update(food);
			}
		}

		// Set OrderNum Ä‘á»ƒ thÃ´ng bÃ¡o cho ngÆ°á»�i dÃ¹ng.
		cartInfo.setOrderNum(order.getId());
		return order;
	}

	@Override
	public Order update(Order u) {
		return orderRepository.save(u);
	}

	@Override
	public void delete(Order u) {
		orderRepository.delete(u);
	}

	@Override
	public void delete(Long id) {
		Order u = orderRepository.getOne(id);
		orderRepository.delete(u);
	}

	@Override
	public Page<Order> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return orderPagingAndSortingRepository.findAll(paging);
	}

	@Override
	public List<Order> search(java.sql.Date fromDate, java.sql.Date toDate) {
		return orderRepository.searchByDates(fromDate, toDate);
	}

}
