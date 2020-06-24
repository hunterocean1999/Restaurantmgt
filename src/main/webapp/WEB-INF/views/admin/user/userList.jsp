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
<title>User List</title>
</head>

<body>

	<!-- search bar -->
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<c:set var="saveUrl"
					value="${pageContext.request.contextPath}/admin/searchUser" />
				<form action="${saveUrl}" method="post">
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
				<sec:authorize access="hasRole('admin')">
					<c:if test="${filter == 'admin'}">
						<a href="<c:url value="/admin/addUser?mode=ADD&filter=admin"/>">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-plus-circle"></i>
							</button>
						</a>
					</c:if>
					<c:if test="${filter == 'manager'}">
						<a href="<c:url value="/admin/addUser?mode=ADD&filter=manager"/>">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-plus-circle"></i>
							</button>
						</a>
					</c:if>
					<c:if test="${filter == 'employee'}">
						<a href="<c:url value="/admin/addUser?mode=ADD&filter=employee"/>">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-plus-circle"></i>
							</button>
						</a>
					</c:if>
				</sec:authorize>
			</div>
		</div>
	</div>

	<!-- error message -->
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>

	<!-- classify list -->
	<ul class="nav nav-tabs nav-pills">
		<li class="nav-item"><c:if test="${filter == 'admin'}">
				<a class="nav-link active"
					href="${pageContext.request.contextPath}/admin/userList?filter=admin">
					Admin List</a>
			</c:if> <c:if test="${filter != 'admin'}">
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/userList?filter=admin">
					Admin List</a>
			</c:if></li>
		<li class="nav-item"><c:if test="${filter == 'manager'}">
				<a class="nav-link active"
					href="${pageContext.request.contextPath}/admin/userList?filter=manager">
					Manager List</a>
			</c:if> <c:if test="${filter != 'manager'}">
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/userList?filter=manager">
					Manager List</a>
			</c:if></li>
		<li class="nav-item"><c:if test="${filter == 'employee'}">
				<a class="nav-link active"
					href="${pageContext.request.contextPath}/admin/userList?filter=employee">
					Employee List</a>
			</c:if> <c:if test="${filter != 'employee'}">
				<a class="nav-link"
					href="${pageContext.request.contextPath}/admin/userList?filter=employee">
					Employee List</a>
			</c:if></li>
	</ul>

	<!-- table list -->
	<div class="table-responsive">
		<table
			class="table table-striped table-bordered text-center align-middle">
			<thead class="text-white bg-primary">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">USERNAME</th>
					<th scope="col">NAME</th>
					<c:if
						test="${filter != 'admin' && filter != 'manager' && filter != 'employee'}">
						<th scope="col">TYPE</th>
					</c:if>
					<th scope="col">STATUS</th>
					<th scope="col">DETAIL</th>
					<sec:authorize access="hasRole('admin')">
						<th scope="col">UPDATE</th>
						<th scope="col">DELETE</th>
					</sec:authorize>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${userList.getContent()}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.userName}</td>
						<td>${user.firstName}${user.lastName}</td>
						<c:if
							test="${filter != 'admin' && filter != 'manager' && filter != 'employee'}">
							<td>${user.userType.typeName}</td>
						</c:if>
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

							<c:if test="${filter == 'admin'}">
								<td><a
									href="<c:url value="/admin/deleteUser/${user.id}?filter=admin"/>">
										<button type="submit" class="btn btn-secondary">
											<i class="fas fa-trash"></i>
										</button>
								</a></td>
							</c:if>
							<c:if test="${filter == 'manager'}">
								<td><a
									href="<c:url value="/admin/deleteUser/${user.id}?filter=manager"/>">
										<button type="submit" class="btn btn-secondary">
											<i class="fas fa-trash"></i>
										</button>
								</a></td>
							</c:if>
							<c:if test="${filter == 'employee'}">
								<td><a
									href="<c:url value="/admin/deleteUser/${user.id}?filter=employee"/>">
										<button type="submit" class="btn btn-secondary">
											<i class="fas fa-trash"></i>
										</button>
								</a></td>
							</c:if>
							<c:if
								test="${filter != 'admin' && filter != 'manager' && filter != 'employee'}">
								<td><a
									href="<c:url value="/admin/deleteUser/${user.id}?filter="/>">
										<button type="submit" class="btn btn-secondary">
											<i class="fas fa-trash"></i>
										</button>
								</a></td>
							</c:if>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- paging -->
		<c:if test="${userList.totalPages > 0}">
			<util:pagination currentPage="${currentPage}" filter="${filter}"
				thispage="${userList}"></util:pagination>
		</c:if>
	</div>
</body>

</html>