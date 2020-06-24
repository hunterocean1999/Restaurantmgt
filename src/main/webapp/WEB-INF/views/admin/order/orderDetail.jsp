<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Update Order Information</title>
</head>

<body>
	<c:set var="saveUrl"
		value="${pageContext.request.contextPath}/admin/updateOrder" />
	<div class="card" style="width: 40rem;">

		<div class="card-body">
			<h2 class="card-title text-center border-bottom border-dark pb-2">Bill</h2>
			<div class="form-group">
				<div class="row">
					<div class="col-md-3"><label><b>Date</b></label></div>
					<div class="col-md-9">${order.orderDate}</div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><b>Customer</b></label></div>
					<div class="col-md-9">${order.customer.firstName}
								${order.customer.lastName}</div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><b>Email</b></label></div>
					<div class="col-md-9">${order.customer.email}</div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><b>Phone</b></label></div>
					<div class="col-md-9">${order.customer.phone}</div>
				</div>
				<div class="row">
					<div class="col-md-3"><label><b>Address</b></label></div>
					<div class="col-md-9">${order.customer.street},
								${order.customer.district}, ${order.customer.city}</div>
				</div>
			
				<div class="row p-2">
					<table class="table table-striped table-sm border border-dark">
						<tr class="text-center">
							<td>IMAGE</td>
							<td>FOOD NAME</td>
							<td>QUANITY</td>
							<td>PRICE</td>
						</tr>
						<c:forEach items="${orderDetailList}" var="ODList">
							<tr class="text-center">
								<td><img class="product-image" width="60" height="60"
									src=<c:url value="/uploads/${ODList.food.image}" /> /></td>
								<td>${ODList.food.foodName}</td>
								<td>${ODList.quanity}</td>
								<td>${ODList.price}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			
				<div class="row">
					<div class="col-md-3"><label><b>Amount</b></label></div>
					<div class="col-md-9">${order.amount}</div>
				</div>
			</div>
		</div>
		
		<a href="<c:url value='${pageContext.request.contextPath}/admin/order' />" class="btn btn-Success">BACK</a>
		
	</div>
</body>
</html>