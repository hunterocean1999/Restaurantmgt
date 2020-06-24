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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>

<body>
	<h1 class="border-bottom border-dark text-center pb-2 mb-2">Checkout</h1>

	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<c:set var="saveUrl"
					value="${pageContext.request.contextPath}/shop/searchPhoneNumber" />
				<form action="${saveUrl}" method="POST">
					<div class="row">
						<div class="col-md-8">
							<input class="form-control" type="number" name="phoneNumber"
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
			<div class="col-md-2 alignRight">
				<a href="<c:url value="/"/>">
					<button type="submit" class="btn btn-dark btn-lg">
						<i class="fas fa-undo-alt"></i>
					</button>
				</a>
			</div>
		</div>
	</div>

	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>


	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="card-body">
					<h2 class="card-title border-bottom border-dark text-center pb-2">Customer Information</h2>

					<c:set var="saveUrl"
						value="${pageContext.request.contextPath}/shop/shoppingCartCustomer" />
					<form:form action="${ saveUrl }" method="POST"
						modelAttribute="customerInfo">
						<div class="row">

							<div class="col-md-6 mb-3">
								<div class="checkout-form-list">
									<label>First name<span class="required">*</span></label>
									<form:input path="firstName" id="customer.firstName"
										name="customer.billing.firstName" class="required"
										title="First name is required" autofocus="autofocus"
										type="text" value="" />
									<span id="error-customer.billing.firstName" class="error"></span>
								</div>
							</div>

							<div class="col-md-6 mb-3">
								<div class="checkout-form-list">
									<label>Last name<span class="required">*</span></label>
									<form:input path="lastName" id="customer.lastName"
										name="customer.billing.lastName" class="required"
										title="Last name is required" type="text" value=""
										maxlength="32" />
									<span id="error-customer.billing.lastName" class="error"></span>
								</div>
							</div>

							<div class="col-md-12 mb-3">
								<div class="checkout-form-list">
									<div class="row">
										<div class="col">
											<label>Street address <span class="required">*</span></label>
										</div>
										<div class="col">
											<form:input path="street" id="customer.billing.address"
												name="customer.billing.address" class="required"
												title="Street address is required" type="text" value="" />
											<span id="error-customer.billing.address" class="error"></span>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12 mb-3">
								<div class="checkout-form-list">
									<div class="row">
										<div class="col">
											<label>District address <span class="required">*</span></label>
										</div>
										<div class="col">
											<form:input path="district" id="customer.billing.address"
												name="customer.billing.address" class="required"
												title="Street address is required" type="text" value="" />
											<span id="error-customer.billing.address" class="error"></span>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-12 mb-3">
								<div class="checkout-form-list">
									<div class="row">
										<div class="col">
											<label>City <span class="required">*</span></label>
										</div>
										<div class="col">
											<form:input path="city" id="customer.billing.city"
												name="customer.billing.city" class="required"
												title="City is required" type="text" />
											<span id="error-customer.billing.city" class="error"></span>
										</div>
									</div>
								</div>
							</div>

							<div class="col-md-6 mb-3">
								<div class="checkout-form-list">
									<label>Email address <span class="required">*</span></label>
									<form:input path="email" id="customer.emailAddress"
										name="customer.emailAddress" class="required"
										title="Email address is required" type="text" value="" />
									<span id="error-customer.emailAddress" class="error"></span>
								</div>
							</div>

							<div class="col-md-6 mb-3">
								<div class="checkout-form-list">
									<label>Phone number <span class="required">*</span></label>
									<form:input path="numberPhone" id="customer.billing.phone"
										name="customer.billing.phone" class="required"
										title="Phone number is required" type="number" value="" />
									<span id="error-customer.billing.phone" class="error"></span>
								</div>
							</div>

						</div>

						<div class="row text-center p-4 m-4">
							<button type="submit" class="btn btn-Success btn-lg btn-block">Submit order</button>
						</div>

					</form:form>
				</div>
			</div>

			<div class="col-lg-6 col-md-6">
				<div class="card-body">
					<h2 class="card-title border-bottom border-dark text-center pb-2">Order Summary</h2>

					<div class="row text-center m-2">
						<div class="col">
							<b>Item</b>
						</div>
						<div class="col">
							<b>Price</b>
						</div>
					</div>

					<div class="card">
						<div class="card-body">
							<c:forEach items="${myCart.cartLines}" var="cartLineInfo">
								<div class="row border-bottom border-dark text-center m-2 pb-2">
									<div class="col">${cartLineInfo.productInfo.tensp}
										<b>x ${cartLineInfo.quantity}</b>
									</div>
									<div class="col">
										<fmt:formatNumber type="number" maxFractionDigits="0"
											value="${cartLineInfo.productInfo.giasp}" />
										VNĐ
									</div>
								</div>
							</c:forEach>
						</div>

						<div class="card-footer text-center">
							<div class="row">
								<div class="col">
									<b>Total</b>
								</div>
								<div class="col">
									<b><fmt:formatNumber type="number" maxFractionDigits="0"
											value="${myCart.amountTotal}" /> VNĐ</b>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>

</html>