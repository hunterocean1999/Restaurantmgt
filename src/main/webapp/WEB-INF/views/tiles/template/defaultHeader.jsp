<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<h1>filler</h1>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 fixed-top">
	<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${pageContext.request.contextPath}/">GAUGAU HOUSE</a>
	<div class="px-3 dropdown show">
		<a class="btn white-text dropdown-toggle" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
		   href="${pageContext.request.contextPath}/profile">
			<sec:authentication property="principal.username" />
		</a>

		<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
			<a class="dropdown-item" href="${pageContext.request.contextPath}/profile/myProfile">Profile</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
		</div>
	</div>
</nav>
