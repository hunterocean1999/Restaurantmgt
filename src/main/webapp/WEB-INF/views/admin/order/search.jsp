<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Order List</title>
</head>

<body>
	<div class="container">	
		<div class="row">
			<div class="col-md-10">
				<c:set var="url" value="${pageContext.request.contextPath}/admin/order/search"></c:set>
				<form method="post" action="${url}">
					<div class="form-group row">
						<label for="Date1" class="col-xs-2 col-form-label"> From </label>
						<div class="col-sm-2">
							<input type="date" class="form-control" name="formDate" />
						</div>
						<label for="Date2" class="col-xs-2 col-form-label"> To </label>
						<div class="col-sm-2">
							<input type="date" class="form-control" name="toDate" />
						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-primary btn-lg">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-2 alignRight">
				<a href="<c:url value="/admin/order"/>">
					<button type="submit" class="btn btn-dark btn-lg">
						<i class="fas fa-undo-alt"></i>
					</button>
				</a>
			</div>
		</div>
	</div>
	
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<tr>
				<th>ID</th>
				<th>DATE</th>
				<th>AMOUNT</th>
				<th>STATUS</th>
				<th>CUSTOMER</th>
				<th>DETAIL</th>
				<sec:authorize access="hasAnyRole('admin','manager')">
					<th>EDIT</th>
					<th>REMOVE</th>
				</sec:authorize>
			</tr>
			<c:forEach items="${orderList}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.orderDate}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0"
							value="${order.amount}" /> VND</td>
					<td><c:if test="${order.status == 0}">Đang chờ xử lý</c:if> <c:if
							test="${order.status == 1}">Đang xử lý</c:if> <c:if
							test="${order.status == 2}">Đã xử lý</c:if></td>
					<td>${order.customer.firstName}${order.customer.lastName}</td>
					<td><a
						href="<c:url value="/admin/orderDetail?id=${order.id}"/>">
							<button type="submit" class="btn btn-info">
								<i class="fas fa-eye"></i>
							</button>
					</a></td>
					<sec:authorize access="hasAnyRole('admin','manager')">
						<td><a
							href="<c:url value="/admin/editOrder?id=${order.id}"/>">
								<button type="submit" class="btn btn-success">
									<i class="fas far fa-edit"></i>
								</button> <!-- <button type="submit" class="btn btn-success">EDIT</button> -->
						</a></td>
						<td><a
							href="<c:url value="/admin/removeOrder?id=${order.id}"/>">
								<button type="submit" class="btn btn-secondary">
									<i class="fas fa-trash"></i>
								</button> <!-- <button type="submit" class="btn btn-secondary">REMOVE</button> -->
						</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>