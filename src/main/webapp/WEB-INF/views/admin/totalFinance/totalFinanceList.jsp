<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Total Finance List</title>
</head>

<body>
	<div class="container">
		<c:set var="saveUrl" value="${pageContext.request.contextPath}/admin/searchTotalFinance"></c:set>
		<form action="${saveUrl}" method="POST">
			<div class="form-group row">
				<label for="Date1" class="col-xs-2 col-form-label"> From </label>
				<div class="col-sm-2">
					<input type="date" class="form-control" name="fromDate" />
				</div>
				<label for="Date2" class="col-xs-2 col-form-label"> To </label>
				<div class="col-sm-2">
					<input type="date" class="form-control" name="toDate" />
				</div>
				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary btn-lg">
					<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>

	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<tr>
				<th>ID</th>
				<th>DATE</th>
				<th>REVENUE</th>
				<th>EXPENSE</th>
				<th>PROFIT</th>
				<sec:authorize access="hasAnyRole('admin','manager')">
					<th>EDIT</th>
					<th>REMOVE</th>
				</sec:authorize>
			</tr>
			<c:forEach items="${totalFinanceList.getContent()}" var="TFList">
				<tr>
					<td>${TFList.id}</td>
					<td>${TFList.date}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0" 
						value="${TFList.revenue}" /> VND</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0" 
						value="${TFList.expense}" /> VND</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="0" 
						value="${TFList.profit}" /> VND</td>
					<sec:authorize access="hasAnyRole('admin','manager')">
						<td><a href="<c:url value="/admin/editTotalFinance?id=${TFList.id}"/>">
							<button type="submit" class="btn btn-success">
								<i class="fas far fa-edit"></i>
							</button>
						</a></td>
						<td><a
							href="<c:url value="/admin/removeTotalFinance?id=${TFList.id}"/>">
								<button type="submit" class="btn btn-secondary">
									<i class="fas fa-trash"></i>
								</button> 
						</a></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</table>

		<c:if test="${totalFinanceList.totalPages > 0}">
			<util:pagination currentPage="${currentPage}" thispage="${totalFinanceList}"></util:pagination>
		</c:if>
	</div>
</body>

</html>