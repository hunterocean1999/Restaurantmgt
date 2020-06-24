<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer List</title>
</head>

<body>
	<div class="container">
		<c:set var="saveUrl"
			value="${pageContext.request.contextPath}/searchCustomer" />
		<form action="${saveUrl}" method="POST">
			<div class="row">
				<div class="col-md-8">
					<input class="form-control" type="number" name="numberPhone"
						placeholder="search phone number of customer" />
				</div>
				<div class="col-md-1 alignRight">
					<button type="submit" class="btn btn-primary btn-lg">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>

	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<tr>
				<td><b>ID</b></td>
				<td><b>NAME</b></td>
				<td><b>EMAIL</b></td>
				<td><b>PHONE</b></td>
				<td><b>DETAIL</b></td>
				<td><b>EDIT</b></td>
				<td><b>REMOVE</b></td>
			</tr>

			<c:forEach items="${customerList.getContent()}" var="customer">
				<tr>
					<td>${customer.id}</td>
					<td>${customer.firstName} ${customer.lastName}</td>
					<td>${customer.email }</td>
					<td>${customer.phone}</td>
					<td><a href="/getCustomer?id=${customer.id}">
							<button type="submit" class="btn btn-info">
								<i class="fas fa-eye"></i>
							</button>
					</a></td>
						<td><a href="/editCustomer?id=${customer.id}">
								<button type="submit" class="btn btn-success">
									<i class="far fa-edit"></i>
								</button>
						</a></td>
						<td><a href="/removeCustomer?id=${customer.id}">
								<button type="submit" class="btn btn-secondary">
									<i class="fas fa-trash"></i> <i class="${menuType.icon}"></i>
								</button>
						</a></td>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${customerList.totalPages > 0}">
			<util:pagination currentPage="${currentPage}" thispage="${customerList}"></util:pagination>
		</c:if>
	</div>
</body>

</html>