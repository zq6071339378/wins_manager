<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../layout/taglib.jsp"%>
<c:forEach var="template" items="${result.materials}" varStatus="status">
	<div class="col-md-4">
		<a href="javascript:;"
			onclick="previewTemplate('${template.templateUrl}')"
			class="thumbnail"> <img src="${template.templateImage}" />
		</a>
		<div class="caption">
			<h5>${template.templateName}</h5>
			<div>
				<p class="pull-left col-md-5">${template.userName}</p>
				<p class="">
					<fmt:formatDate value="${template.createTime}" type="date"
						dateStyle="long" />
				</p>
			</div>
		</div>
	</div>
</c:forEach>