<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>My Profile</title>
</head>

<body>
	<div class="card-body">
		<h2 class="card-title">User Profile</h2>

		<div class="form-group">
			<div class="row">
				<div class="col-sm-4">
					<img src="<c:url value="/uploads/userAvatar/${user.avatarName}" />" height="300" width="250">
				</div>
				<div class="col-sm-8">
					<div class="row">
						<div class="col-sm-3">
							<label for="userName">USERNAME</label>
						</div>
						<div class="col">: ${user.userName}</div>
					</div>

					<div class="row">
						<div class="col-sm-3">
							<label for="firstName">FIRST NAME</label>
						</div>
						<div class="col">: ${user.firstName}</div>
					</div>

					<div class="row">
						<div class="col-sm-3">
							<label for="lastName">LAST NAME</label>
						</div>
						<div class="col">: ${user.lastName}</div>
					</div>

					<div class="row">
						<div class="col-sm-3">
							<label for="age">AGE</label>
						</div>
						<div class="col">: ${user.age}</div>
					</div>

					<div class="row">
						<div class="col-sm-3">
							<label for="phoneNumber">PHONE NUMBER</label>
						</div>
						<div class="col">: ${user.phoneNumber}</div>
					</div>

					<div class="row">
						<div class="col-sm-3">
							<label for="email">EMAIL</label>
						</div>
						<div class="col">: ${user.email}</div>
					</div>

					<div class="row">
						<div class="col-sm-3">
							<label for="typeName">USER TYPE</label>
						</div>
						<div class="col">: ${user.userType.typeName}</div>
					</div>
				</div>
			</div>
		</div>
		
		<a href="<c:url value="/profile/profileUpdate/${user.id}" />" class="btn btn-primary">Edit Profile</a>
		<a href="<c:url value="/" />" class="btn btn-dark"> GO BACK</a>	
	</div>
</body>

</html>