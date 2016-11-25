<%@ page language="java" pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix='fmt'
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<c:if test="${!empty result}">
	<c:set var="page" value="${result.page}"></c:set>
	<fmt:parseNumber integerOnly="true"
		value="${(page.totalCount-1)/page.limit}" var="pageSize"></fmt:parseNumber>
</c:if>
<%
	String actionName=request.getParameter("actionName");
	actionName = actionName.contains("?")?actionName+"&":actionName+"?";
	String target=request.getParameter("target");
	pageContext.setAttribute("target",target);
%>
<c:if test="${!empty page && page.totalCount!=0}">
	<div class="col-md-12">
		<div class="pull-right">
			<div class="dataTables_paginate paging_bs_full"
				id="datatable1_paginate">
				<ul class="pagination">
					<c:choose>
						<c:when test="${page.currentPage le 1}">
							<li class="disabled"><a href="javascript:;" tabindex="0"
								id="datatable1_first">«</a></li>
							<li class="disabled"><a href="javascript:;" tabindex="0"
								id="datatable1_previous">‹</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:;"
								onclick="load_page('<%=actionName%>start=0&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);"
								tabindex="0" id="datatable1_first">«</a></li>
							<li><a href="javascript:;"
								onclick="load_page('<%=actionName%>start=${(page.currentPage-2)*page.limit}&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);"
								tabindex="0" id="datatable1_previous">‹</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${pageSize gt 5}">
							<c:if test="${page.currentPage+2 le 5}">
								<c:forEach begin="1" end="5" var="index">
									<li
										<c:if test="${page.currentPage eq index}">class="active"</c:if>><a
										href="javascript:;"
										onclick="load_page('<%=actionName%>start=${(index-1)*page.limit}&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);"
										tabindex="0">${index}</a></li>
								</c:forEach>
							</c:if>
							<c:if test="${page.currentPage+2 gt 5}">
								<c:if test="${page.currentPage+2 le pageSize}">
									<c:forEach begin="${page.currentPage-2}"
										end="${page.currentPage+2}" var="index">
										<li
											<c:if test="${page.currentPage eq index}">class="active"</c:if>><a
											href="javascript:;"
											onclick="load_page('<%=actionName%>start=${(index-1)*page.limit}&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);"
											tabindex="0">${index}</a></li>
									</c:forEach>
								</c:if>
								<c:if test="${page.currentPage+2 gt pageSize}">
									<c:forEach begin="${pageSize-4}" end="${pageSize}" var="index">
										<li
											<c:if test="${page.currentPage eq index}">class="active"</c:if>><a
											tabindex="0" href="javascript:;"
											onclick="load_page('<%=actionName%>start=${(index-1)*page.limit}&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);">${index}</a></li>
									</c:forEach>
								</c:if>
							</c:if>
						</c:when>
						<c:when test="${pageSize le 5}">
							<c:forEach begin="1" end="${pageSize+1}" var="index">
								<li
									<c:if test="${page.currentPage eq index}">class="active"</c:if>><a
									href="javascript:;"
									onclick="load_page('<%=actionName%>start=${(index-1)*page.limit}&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);"
									tabindex="0">${index}</a></li>
							</c:forEach>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${(pageSize+1) le page.currentPage}">
							<li class="disabled"><a href="javascript:;" tabindex="0"
								id="datatable1_next">›</a></li>
							<li class="disabled"><a href="javascript:;" tabindex="0"
								id="datatable1_last">»</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:;"
								onclick="load_page('<%=actionName%>start=${(page.currentPage)*page.limit}&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);"
								class="paginate_button next" tabindex="0" id="datatable1_next">›</a></li>
							<li><a href="javascript:;"
								onclick="load_page('<%=actionName%>start=${(pageSize)*page.limit}&limit=${page.limit}'<c:if test="${!empty target}">,'${target}'</c:if>);"
								class="paginate_button last" tabindex="0" id="datatable1_last">»</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>
</c:if>