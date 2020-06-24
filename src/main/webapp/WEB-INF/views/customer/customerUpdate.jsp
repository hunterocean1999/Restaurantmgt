<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Customer Update</title>
</head>

<body>
	<c:set var="saveUrl" value="${pageContext.request.contextPath}/updateCustomer" />
	<div class="container">
		<form:form enctype="multipart/form-data" action="${saveUrl}" method="post" modelAttribute="customer">
			<div class="card" style="width: 40rem;">
				<div class="card-body">
					<h2 class="card-title border-bottom border-dark text-center pb-2">Update Customer Information</h2>
					
					<div class="form-group">
						<label for="firstName">First Name</label>
						<form:input path="firstName" type="text" class="form-control"
							id="firstName" />
					</div>

					<div class="form-group">
						<label for="lastName">Last Name</label>
						<form:input path="lastName" type="text" class="form-control"
							id="lastName" />
					</div>

					<div class="form-group">
						<label for="email">Email</label>
						<form:input path="email" type="text" class="form-control"
							id="email" />
					</div>

					<div class="form-group">
						<label for="phone">Phone</label>
						<form:input path="phone" type="text" class="form-control"
							id="phone" />
					</div>

					<div class="form-group">
						<label for="street">Street</label>
						<form:input path="street" type="text" class="form-control"
							id="street" />
					</div>

					<div class="form-group">
						<label for="district">District</label>
						<form:input path="district" type="text" class="form-control"
							id="district" />
					</div>

					<div class="form-group">
						<label for="city">City</label>
						<form:input path="city" type="text" class="form-control" id="city" />
					</div>
					
					<div class="form-group alignRight">
						<button type="submit" class="btn btn-success">
							<i class="fas fa-check-square"></i>
						</button> | 
						<a href="<c:url value="/customerList"/> ">
							<i class="fas fa-undo-alt btn btn-dark"></i>
						</a>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>

</html>