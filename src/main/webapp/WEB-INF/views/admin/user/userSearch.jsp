<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Search</title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<c:set var="saveUrl" value="${pageContext.request.contextPath}/admin/searchUser" />
				<form action="${ saveUrl }" method="post">
					<div class="row">
						<div class="col-md-8">
							<input class="form-control" type="text" name="filter"
								placeholder="search by userName" />
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
				<a href="<c:url value="/admin/userList"/>">
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

	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<tr>
				<td><b>ID</b></td>
				<td><b>USERNAME</b></td>
				<td><b>NAME</b></td>
				<td><b>TYPE</b></td>
				<td><b>STATUS</b></td>
				<td><b>DETAIL</b></td>
				<sec:authorize access="hasRole('admin')">
					<td><b>UPDATE</b></td>
					<td><b>DELETE</b></td>
				</sec:authorize>
			</tr>
			<c:forEach items="${userList.getContent()}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.userName}</td>
					<td>${user.firstName}${user.lastName}</td>
					<td>${user.userType.typeName}</td>
					<td>${user.isActive}</td>
					<td><a href="<c:url value="/admin/userDetails/${user.id}"/>">
							<button type="submit" class="btn btn-info">
								<i class="fas fa-eye"></i>
							</button>
					</a></td>
					<sec:authorize access="hasRole('admin')">
						<td><a
							href="<c:url value="/admin/editUser/${user.id}?mode=EDIT"/>">
								<button type="submit" class="btn btn-success">
									<i class="far fa-edit"></i>
								</button>
						</a></td>
						<td><a href="<c:url value="/admin/deleteUser/${user.id}"/>">
								<button type="submit" class="btn btn-secondary">
									<i class="fas fa-trash"></i>
								</button>
						</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${userList.totalPages > 0}">
			<util:pagination currentPage="${currentPage}" filter="${filter}" thispage="${userList}"></util:pagination>
		</c:if>
	</div>

</body>

</html>