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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>

<body>
	
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>

	<div class="container">
		<div class="col-md-10">
			<c:set var="saveUrl" value="${pageContext.request.contextPath}/search" />
			<form action="${saveUrl}" method="post">
				<div class="row">
					<div class="col-md-8">
						<input class="form-control" type="text" name="foodName" placeholder="search by foodName" />
					</div>
					<div class="col-md-1 alignRight">
						<button type="submit" class="btn btn-primary btn-lg"><i class="fas fa-search"></i></button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<c:forEach items="${allMenuType}" var="t">
			<li class="nav-item"><c:if test="${id == t.id}">
					<a class="nav-link active"
						href="${pageContext.request.contextPath}/foodByMenu?menuId=${t.id}">
						${t.typeName} Menu</a>
				</c:if> <c:if test="${id != t.id}">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/foodByMenu?menuId=${t.id}">
						${t.icon} ${t.typeName} Menu</a>
				</c:if></li>
		</c:forEach>
	</ul>

	<div class="row">
		<c:forEach items="${productList.getContent()}" var="p">
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100">
					<a href="/admin/getFood?id=${p.id}"> <img src="<c:url value="/uploads/${p.image}" />"
						class="mx-auto d-block img-thumbnail" /></a>
					<div class="card-body">
						<h4 class="card-title text-center">
							<a href="/admin/getFood?id=${p.id}">${p.foodName}</a>
						</h4>
						<h5 class="text-center">
							<fmt:formatNumber type="number" maxFractionDigits="0" value="${p.unitPrice}" /> VND
						</h5>
						<p class="card-text text-center">${p.menuType.typeName}</p>
						<p class="card-text text-center"><c:if test="${p.amount <= 0}"> Sold Out </c:if>
							<c:if test="${p.amount > 0}">Amount: ${p.amount}</c:if> </p>
					</div>

					<a href="<c:url value='/shop/buyProduct?id=${p.id}' />" class="btn btn-Success">Order</a>

					<div class="card-footer text-center">
						<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<c:if test="${productList.totalPages > 0}">
		<util:pagination currentPage="${currentPage}" thispage="${productList}"></util:pagination>
	</c:if>

</body>

</html>