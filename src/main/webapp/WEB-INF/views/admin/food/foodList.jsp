<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Food List</title>
</head>

<body>

	<!-- search bar --> 
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<c:set var="saveUrl"
					value="${pageContext.request.contextPath}/admin/searchFood" />
				<form action="${saveUrl}" method="post">
					<div class="row">
						<div class="col-md-8">
							<input class="form-control" type="text" name="search"
								placeholder="search by foodName" />
						</div>
						<div class="col-md-1 alignRight">
							<button type="submit" class="btn btn-primary btn-lg">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-2 alignRight">
				<sec:authorize access="hasAnyRole('admin','manager')">
					<div class="alignRight">
						<c:if test="${type == 'Food'}">
							<a href="<c:url value="/admin/addFood?mode=ADD&type=Food"/>">
								<button type="submit" class="btn btn-primary bth-lg">
									<i class="fas fa-plus-circle"></i>
								</button>
							</a>
						</c:if>
						<c:if test="${type == 'Drink'}">
							<a href="<c:url value="/admin/addFood?mode=ADD&type=Drink"/>">
								<button type="submit" class="btn btn-primary">
									<i class="fas fa-plus-circle"></i>
								</button>
							</a>
						</c:if>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
	
	<!-- error message -->
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>
	
	<!-- classify lists -->
	<ul class="nav nav-tabs nav-pills">
		<li class="nav-item"><c:if test="${ type == 'Food' }">
				<a class="nav-link active"
					href="${pageContext.request.contextPath}/admin/foodstype=Food">
					Food List</a>
			</c:if> <c:if test="${ type != 'Food' }">
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/foods?type=Food">
					Food List</a>
			</c:if></li>
		<li class="nav-item"><c:if test="${ type == 'Drink' }">
				<a class="nav-link active"
					href="${pageContext.request.contextPath}/admin/foods?type=Drink">
					Drink List</a>
			</c:if> <c:if test="${ type != 'Drink' }">
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/foods?type=Drink">
					Drink List</a>
			</c:if></li>
	</ul>
	
	<!-- table list --> 
	<div class="table-responsive">
		<table class="table table-striped table-bordered text-center align-middle">
			<thead class="text-white bg-primary">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">FOOD NAME</th>
					<th scope="col">UNIT PRICE</th>
					<c:if test="${type != 'Drink' && type != 'Food'}">
						<th scope="col">TYPE</th>
					</c:if>
					<th scope="col">AMOUNT</th>
					<th scope="col">MENU TYPE</th>
					<th scope="col">NOTIFY</th>
					<th scope="col">DETAIL</th>
					<sec:authorize access="hasAnyRole('admin','manager')">
						<th scope="col">EDIT</th>
						<th scope="col">REMOVE</th>
					</sec:authorize>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${foodList.getContent()}" var="food">
					<tr>
						<td>${food.id}</td>
						<td>${food.foodName }</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="0"
								value="${food.unitPrice}" /> VNĐ</td>
						<c:if test="${type != 'Drink' && type != 'Food'}">
							<td>${food.foodType.typeName}</td>
						</c:if>
						<td>${food.amount}</td>

						<td>${food.menuType.typeName}</td>
						<td><c:if test="${food.notify == true}">Ready</c:if> <c:if
								test="${food.notify == false}">Sold Out</c:if></td>
						<td><a href="/admin/getFood?id=${food.id }">
								<button type="submit" class="btn btn-info">
									<i class="fas fa-eye"></i>
								</button>
						</a></td>
						<sec:authorize access="hasAnyRole('admin','manager')">
							<td><a href="/admin/editFood?id=${food.id}&mode=EDIT">
									<button type="submit" class="btn btn-success">
										<i class="far fa-edit"></i>
									</button>
							</a></td>
							<td><a href="/admin/removeFood?id=${food.id}">
									<button type="submit" class="btn btn-secondary">
										<i class="fas fa-trash"></i>
									</button>
							</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- paging --> 
		<c:if test="${foodList.totalPages > 0}">
			<util:pagination currentPage="${currentPage}" thispage="${foodList}"></util:pagination>
		</c:if>
	</div>
</body>

</html>