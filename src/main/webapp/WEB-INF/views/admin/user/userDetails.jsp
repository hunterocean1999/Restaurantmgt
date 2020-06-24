<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<c:set var="saveUrl" value="${pageContext.request.contextPath}/admin/updateUser" />
		<div class="card-body">
			<h2 class="card-title border-bottom border-dark text-center pb-2">User Details</h2>

			<div class="form-group">
				<div class="row">
					<div class="col-sm-4">
						<img src="<c:url value="/uploads/userAvatar/${user.avatarName}" />" height="300" width="250">
					</div>
					<div class="col-sm-8">
						<div class="row">
							<div class="col-sm-3"><label for="userName">USERNAME</label></div>
							<div class="col">: ${user.userName}</div>
						</div>

						<div class="row">
							<div class="col-sm-3"><label for="firstName">FIRST NAME</label></div>
							<div class="col">: ${user.firstName}</div>
						</div>

						<div class="row">
							<div class="col-sm-3"><label for="lastName">LAST NAME</label></div>
							<div class="col">: ${user.lastName}</div>
						</div>

						<div class="row">
							<div class="col-sm-3"><label for="age">AGE</label></div>
							<div class="col">: ${user.age}</div>
						</div>

						<div class="row">
							<div class="col-sm-3"><label for="phoneNumber">PHONE NUMBER</label></div>
							<div class="col">: ${user.phoneNumber}</div>
						</div>

						<div class="row">
							<div class="col-sm-3"><label for="email">EMAIL</label></div>
							<div class="col">: ${user.email}</div>
						</div>

						<div class="row">
							<div class="col-sm-3"><label for="typeName">USER TYPE</label></div>
							<div class="col">: ${user.userType.typeName}</div>
						</div>
						
						<div class="row alignRight">
							<a href="<c:url value="/admin/userList?filter=${user.userType.typeName}"/> ">
								<i class="fas fa-undo-alt btn btn-dark"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>