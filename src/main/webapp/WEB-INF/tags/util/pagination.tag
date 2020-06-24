<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ attribute name="thispage"
              type="org.springframework.data.domain.Page" required="true"
              rtexprvalue="true" description="The current Page"%>
<%@ attribute name="currentPage"
              required="false"
              rtexprvalue="true" description="The current Page Number"%>
<%@ attribute name="filter"
              required="false"
              rtexprvalue="true" description="The filter value"%>
              
<%@ attribute name="showPageSize"
              required="false"
              rtexprvalue="true" description="Show record per page option"%>

<c:set var="pageNumber" value="${thispage.number}" />
<c:set var="maxPages" value="${thispage.totalPages > 0 ? thispage.totalPages: 5}" />
<c:set var="size" value="${thispage.size > 0 ? thispage.size : 5}" />
<c:set var="currentPage" value="${currentPage > 0 ? currentPage : 0}" />
<c:set var="filter" value="${filter}" />
<c:set var="showPageSize" value="${showPageSize != null ? showPageSize : true}" />

<nav class="navbar flex-md-nowrap p-0">
    <ul class="pagination">
        <c:choose>
            <c:when test="${pageNumber ne 0}">
                <spring:url value="" var="first">
                    <spring:param name="page" value="0" />
                    <spring:param name="size" value="${size}" />
                    <spring:param name="filter" value="${filter}" />
                </spring:url>
                <spring:message code="list_first" var="first_label" text="First"
                                htmlEscape="false" />
                <li class="page-item">  <a  class="page-link" href="${first}" title="${fn:escapeXml(first_label)}">First</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled"><a class="page-link" href="#">First</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${pageNumber gt 0}">
                <spring:url value="" var="previous">
                    <spring:param name="page" value="${pageNumber - 1}" />
                    <spring:param name="size" value="${size}" />
                    <spring:param name="filter" value="${filter}" />
                </spring:url>
                <spring:message code="list_previous" var="previous_label"
                                text="Previous" htmlEscape="false" />
                <li class="page-item"> <a class="page-link" href="${previous}"
                       title="${fn:escapeXml(previous_label)}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled"><a class="page-link" href="#"
                                        title="${fn:escapeXml(previous_label)}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <c:out value=" " />


        <c:forEach var="i" begin="0" end="${maxPages-1}" step="1">
            <spring:url value="" var="currentUrl">
                <spring:param name="page" value="${i}" />
                <spring:param name="size" value="${size}" />
                <spring:param name="filter" value="${filter}" />
            </spring:url>
            <c:if test="${i == currentPage}">
                <li class="page-item active"> <a class="page-link" href="${currentUrl}">${i +1 }</a></li>
            </c:if>
            <c:if test="${i != currentPage}">
                <li class="page-item"> <a class="page-link" href="${currentUrl}">${i + 1}</a></li>
            </c:if>
        </c:forEach>

        <c:out value=" " />
        <c:choose>
            <c:when test="${pageNumber lt (maxPages-1)}">
                <spring:url value="" var="next">
                    <spring:param name="page" value="${pageNumber + 1}" />
                    <spring:param name="size" value="${size}" />
                    <spring:param name="filter" value="${filter}" />
                </spring:url>
                <spring:url value="/resources/images/resultset_next.png"
                            var="next_image_url" />
                <spring:message code="list_next" var="next_label" text="Next"
                                htmlEscape="false" />
                <li class="page-item"><a class="page-link" href="${next}" title="${fn:escapeXml(next_label)}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${pageNumber ne (maxPages-1)}">
                <spring:url value="" var="last">
                    <spring:param name="page" value="${maxPages-1}" />
                    <spring:param name="size" value="${size}" />
                    <spring:param name="filter" value="${filter}" />
                </spring:url>
                <spring:message code="list_last" var="last_label" text="Last"
                                htmlEscape="false" />
                <li class="page-item"><a class="page-link" href="${last}" title="${fn:escapeXml(last_label)}">Last</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled"><a class="page-link" href="#"
                                        title="${fn:escapeXml(last_label)}">Last</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

	<c:if test="${showPageSize == true }">
		<spring:message code="list_size" var="list_size"
			text="Records per page" htmlEscape="false" />

		<div class="px-3 dropdown show">
			<label> <c:out value="${list_size} " /></label>
			<button type="button" class="btn btn-primary dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<c:out value="${size}" />
			</button>
			<div class="dropdown-menu">
				<c:forEach var="i" begin="10" end="25" step="5">
					<c:choose>
						<c:when test="${size == i}">
							<a class="dropdown-item" href="#">${i}</a>
						</c:when>
						<c:otherwise>
							<spring:url value="" var="sizeUrl">
								<spring:param name="page" value="0" />
								<spring:param name="size" value="${i}" />
								<spring:param name="filter" value="${filter}" />
							</spring:url>
							<a class="dropdown-item" href="${sizeUrl}">${i}</a>
						</c:otherwise>
					</c:choose>
					<c:out value=" " />
				</c:forEach>
			</div>
		</div>
	</c:if>
</nav>