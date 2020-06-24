<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<body>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<tr>
				<td><b>ID</b></td>
				<td><b>MENU TPYE</b></td>
				<sec:authorize access="hasAnyRole('admin','manager')">
					<td><b>EDIT</b></td>
					<td><b>REMOVE</b></td>
				</sec:authorize>
			</tr>

			<c:forEach items="${menuList}" var="type">
				<tr>
					<td>${type.id}</td>
					<td>${type.typeName }</td>
					<sec:authorize access="hasAnyRole('admin','manager')">
						<td><a href="/admin/editMenu?id=${type.id}&mode=EDIT">
								<button type="submit" class="btn btn-success">
									<i class="far fa-edit"></i>
								</button>
						</a></td>
						<td><a href="/admin/removeMenu?id=${type.id}">
								<button type="submit" class="btn btn-secondary">
									<i class="fas fa-trash"></i>
								</button>
						</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>
		<sec:authorize access="hasAnyRole('admin','manager')">
			<a href="/admin/addMenu?mode=ADD">
				<button type="submit" class="btn btn-primary">
					<i class="fas fa-plus-circle"></i>
				</button>
			</a>
		</sec:authorize>
	</div>
</body>

</html>