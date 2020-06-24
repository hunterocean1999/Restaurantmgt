<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Update Order Information</title>
</head>
<body>


	<c:set var="saveUrl"
		value="${pageContext.request.contextPath}/admin/updateOrder" />

	<form:form action="${saveUrl }" method="post" modelAttribute="order">
		<form:input type="hidden" path="id" />
		<div class="card" style="width: 40rem;">

			<div class="card-body">
				<h2 class="card-title">
					<c:if test="${mode=='EDIT' }"> 
						UPDATE ORDER
					</c:if>
					<c:if test="${mode=='ADD' }"> 
						ADD ORDER
					</c:if>
				</h2>
				<div class="form-group text-center">
					<label for="status">Status</label>
					<form:select path="status">
						<form:option value="0">Waiting to be process</form:option>
						<form:option value="1">Processing</form:option>
						<form:option value="2">Processed</form:option>
					</form:select>
					<button type="submit" class="btn btn-primary">SUBMIT</button>
				</div>
			</div>
		</div>
	</form:form>

</body>
</html>

