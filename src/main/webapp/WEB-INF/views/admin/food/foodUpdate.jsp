<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Food Update</title>
</head>

<body>					
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>
					
	<c:set var="saveUrl" value="${pageContext.request.contextPath}/admin/updateFood" />
	<div class="container">
		<form:form enctype="multipart/form-data" action="${saveUrl}"
			method="post" modelAttribute="food">
			<form:input type="hidden" path="id" />
			<div class="card" style="width: 40rem;">
				<div class="card-body">
	
					<h2 class="card-title border-bottom border-dark text-center pb-2">
						<c:if test="${mode == 'EDIT'}">
							Update Food Information
						</c:if>
						<c:if test="${mode == 'ADD'}">
							ADD Food Information
						</c:if>
					</h2>

					<div class="row">
						<div class="col">Food Type:</div>
						<div class="col">${food.foodType.typeName}</div>
						<div class="col alignRight">
							<button type="submit" class="btn btn-success">
								<i class="fas fa-check-square"></i>
							</button> | 
							<a href="<c:url value="/admin/foods"/> ">
								<i class="fas fa-undo-alt btn btn-dark"></i>
							</a>
						</div>
						<form:input class="form-control" type="hidden" path="foodType" />
					</div>

					<br>
					
					<div class="form-group">
						<label for="foodName">Food Name</label>
						<form:input path="foodName" type="text" class="form-control"
							id="foodName" />
					</div>
					

					<div class="form-group">
						<label for="description">Description</label>
						<form:input path="description" type="text" class="form-control"
							id="description" />
					</div>

					<div class="form-group">
						<label for="image">Image</label>
						<c:if test="${mode == 'EDIT'}">
							<img src="<c:url value="/uploads/${food.image}" />" height="200"
								width="200" />
						</c:if>
						<form:input path="imageFile" type="file" class="form-control"
							id="image" />
					</div>

					<div class="form-group">
						<label for="unitPrice">Unit Price</label>
						<form:input path="unitPrice" type="number" class="form-control"
							id="unitPrice" />
					</div>
					
					<div class="form-group">
						<label for="amount">Amount</label>
						<form:input path="amount" type="number" class="form-control"
							id="amount" />
					</div>

					<div class="form-group">
						<label for="menuType.typeName">Menu Type</label>
						<form:select path="menuType">
							<c:forEach items="${allMenuType}" var="t">
								<form:option value="${t.id}">${t.typeName}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>

</html>