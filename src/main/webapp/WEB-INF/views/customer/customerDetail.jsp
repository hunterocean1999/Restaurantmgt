<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>UPDATE FOOD</title>
</head>

<body>
	<c:set var="saveUrl" value="${pageContext.request.contextPath}/admin/updateFood" />
	<div class="container">
		<div class="card" style="width: 40rem;">
			<div class="card-body">

				<h2 class="card-title border-bottom border-dark text-center pb-2">Customer Information</h2>

				<div class="form-group">
					<div class="row">
						<div class="col"><label> Customer Name</label></div>
						<div class="col">${customer.firstName} ${customer.lastName}</div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col"><label> Email</label></div>
						<div class="col">${customer.email}</div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col"><label> Phone</label></div> 
						<div class="col">${customer.phone}</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="row">
						<div class="col"><label> Address</label></div>
						<div class="col">${customer.street}, ${customer.district}, ${customer.city}</div>
					</div>
				</div>
				
				<div class="form-group alignRight">
					<a href="<c:url value="/customerList"/> ">
						<i class="fas fa-undo-alt btn btn-dark"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>