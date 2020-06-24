<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

	<c:set var="saveUrl"
		value="${pageContext.request.contextPath}/admin/updateMenu" />
	<div class="container">
		<form:form enctype="multipart/form-data" action="${saveUrl}"
			method="post" modelAttribute="type">
			<form:input type="hidden" path="id" />
			<div class="card" style="width: 40rem;">
				<div class="card-body">
					<h2 class="card-title border-bottom border-dark text-center pb-2">
						<c:if test="${mode == 'EDIT'}">
							Update Menu Information
						</c:if>
						<c:if test="${mode == 'ADD'}">
							ADD New Menu
						</c:if>
					</h2>
					
					<div class="form-group">
						<label for="typeName">Menu Name</label>
						<form:input path="typeName" type="text" class="form-control" id="foodName" />
					</div>
					
					<div class="form-group">
						<label for="icon">Icon</label>
						<form:input path="icon" type="text" class="form-control" id="icon" />
					</div>	
					
					<div class="form-group alignRight">
						<button type="submit" class="btn btn-success">
							<i class="fas fa-check-square"></i>
						</button> | 
						<a href="<c:url value="/admin/menuList"/> ">
							<i class="fas fa-undo-alt btn btn-dark"></i>
						</a>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>