<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="col-md-2 d-none d-md-block bg-light sidebar">
	<div class="sidebar-sticky">
		<ul class="nav flex-column nav-pills">
			
			<sec:authorize access="hasAnyRole('admin','manager','employee')">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">
					<i class="fas fa-home"></i> Home
				</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/menuList">
					<i class="fas fa-book-open"></i> MenuType
				</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/order">
					<i class="fas fa-file-alt"></i> Order
				</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/foods"> 
					<i class="fas fa-utensils"></i> Product
				</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/customerList">
					<i class="fas fa-user-secret"></i> Customer
				</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/totalFinanceList">
					<i class="fas fa-dollar-sign"></i> Finance
				</a></li>
			</sec:authorize>
			
			<sec:authorize access="hasAnyRole('admin','manager')">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/userList"> 
					<i class="fas fa-users"></i> User
				</a></li>
			</sec:authorize>

		</ul>
	</div>

</nav>
