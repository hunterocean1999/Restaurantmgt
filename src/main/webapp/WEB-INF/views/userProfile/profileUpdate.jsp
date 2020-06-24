<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Profile</title>
</head>

<body>
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>

	<div class="container">
		<c:set var="saveUrl" value="${pageContext.request.contextPath}/profile/profileUpdate" />
		<form:form enctype="multipart/form-data" action="${saveUrl}" method="post" modelAttribute="user">
			<div class="card" style="width: 40rem;">
				<div class="card-body">

					<h2 class="card-title border-bottom border-dark text-center pb-2">Update Profile</h2>

					<div class="row">
						<div class="col">USER TYPE:</div>
						<div class="col">${user.userType.typeName}</div>
						<form:input class="form-control" type="hidden" path="userType" />
					</div>
					<div class="row">
						<div class="col">ID:</div>
						<div class="col">${user.id}</div>
						<form:input class="form-control" type="hidden" path="id" />
					</div>
					<div class="row">
						<div class="col">USERNAME:</div>
						<div class="col">${user.userName}</div>
						<form:input class="form-control" type="hidden" path="userName" />
					</div>
					<div class="row">
						<div class="col">STATUS:</div>
						<div class="col">${user.isActive}</div>
						<form:input class="form-control" type="hidden" path="isActive" />
					</div>
					
					<form:input class="form-control" type="hidden" path="password" />

					<div class="form-group">
						<label for="firstName">FIRST NAME:</label>
						<form:input class="form-control" type="text" path="firstName" />
					</div>
					<div class="form-group">
						<label for="lastName">LAST NAME:</label>
						<form:input class="form-control" type="text" path="lastName" />
					</div>
					<div class="form-group">
						<label for="age">AGE:</label>
						<form:input class="form-control" type="number" path="age" />
					</div>
					<div class="form-group">
						<label for="phoneNumber">PHONE NUMBER:</label>
						<form:input class="form-control" type="number" path="phoneNumber" />
					</div>
					<div class="form-group">
						<label for="email">EMAIL:</label>
						<form:input class="form-control" type="text" path="email" />
					</div>
					<div class="form-group">
						<label for="avatarName">AVATAR:</label> 
						<img src="<c:url value="/uploads/userAvatar/${user.avatarName}" />" height="250" width="200">
						<form:input class="form-control" type="file" path="avatarFile" />
					</div>
					
					<div class="form-group alignRight">
						<button type="submit" class="btn btn-success">
							<i class="fas fa-check-square"></i>
						</button> | 
						<a href="<c:url value="/profile/myProfile" />" >
							<i class="fas fa-undo-alt btn btn-dark"></i>
						</a>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	
</body>

</html>