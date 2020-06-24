<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<div class="entry-header-area ptb-40">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="entry-header">
						<h1 class="entry-title border-bottom border-dark text-center pb-2 mb-2">Review your order</h1>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="cart-main-area ptb-40">
		<div class="container">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="table-content table-responsive">
					<table id="mainCartTable" class="table table-hover table-condensed">
						<thead>
							<tr>
								<th>Image</th>
								<th>Code</th>
								<th>Name</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Subtotal</th>
								<th>Remove</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="saveUrl" value="${pageContext.request.contextPath}/shop/shoppingCart" />
							<c:if test="${cartForm != null && cartForm.cartLines != null && !empty cartForm.cartLines}">
								<form:form method="POST" modelAttribute="cartForm" action="${saveUrl}">
									<div class="product-preview-container">
										<c:forEach items="${cartForm.cartLines}" var="cartLineInfo" varStatus="status">
											<tr>
												<td data-th="Image">
													<div class="row-cart">
														<div class="col-sm-4 hidden-xs">
															<img class="product-image" width="60" height="60"
																src=<c:url value="/uploads/${cartLineInfo.productInfo.hinhanhsp}" /> />
														</div>
													</div>
												</td>
												<td width="15%">${cartLineInfo.productInfo.masp}<form:input
														type="hidden" class="input-small quantity text-center"
														path="cartLines[${status.index}].productInfo.masp"
														value="${cartLineInfo.productInfo.masp}" />
												</td>
												<td>${cartLineInfo.productInfo.tensp}<form:input
														type="hidden"
														path="cartLines[${status.index}].productInfo.tensp"
														value="${cartLineInfo.productInfo.tensp}" />
												</td>
												<td><strong> <fmt:formatNumber
															value="${cartLineInfo.productInfo.giasp}" type="number"
															maxFractionDigits="0" /> VNĐ
												</strong></td>
												<td><form:input
														path="cartLines[${status.index}].quantity"
														value="${cartLineInfo.quantity}" /></td>
												<td><fmt:formatNumber value="${cartLineInfo.amount}"
														type="number" maxFractionDigits="0" /> VNĐ</td>
												<td><a
													href="<c:url value='/shop/shoppingCartRemoveProduct?id=${cartLineInfo.productInfo.masp}' />">
														<i class="fas fa-trash"></i>
												</a></td>
											</tr>
										</c:forEach>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-success btn-lg">
											<i class="far fa-edit"></i>
										</button> |
										<a href="<c:url value='/' />">
											<i class="fas fa-cart-arrow-down btn btn-primary btn-lg"></i>
										</a> |
										<a href="<c:url value='/shop/shoppingCartCustomer' />">
											<i class="fas fa-cash-register btn btn-primary btn-lg"></i>
										</a>
									</div>
								</form:form>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>