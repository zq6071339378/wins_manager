<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<script src="${basePath}js/jquery.form.js"></script>
<!--main_container-->
<div id="main-content" class="margin-top-50">
	<div class="container">
		<div class="row">
			<div id="content" class="col-lg-12">
				<!-- PAGE HEADER-->
				<div class="row">
					<div class="col-sm-12">
						<div class="page-header">
							<!-- STYLER -->

							<!-- /STYLER -->
							<!-- BREADCRUMBS -->
							<ul class="breadcrumb">
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("BLACK_OPERATION_MANAGEMENT")%></a></li>
								<li><a href="#"><%=Config.message.get("COMPLAINT_CUSTOMERCOMPLAINTMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("COMPLAINT_CUSTOMERCOMPLAINTMANAGEMENT")%></h3>
							</div>
							<div class="row col-md-12 row_left">
								<div class="description col-md-11 row_left"><%=Config.message.get("COMPLAIN_MENEGAE_CHECK")%></div>
								<div style="padding-left: 0px;" class="pull-right">
									<a href="#" style="text-decoration: none" onclick="fanhui('${result.PushUser}','${result.time}')">
										<i class="fa fa-reply-all" style="font-size: 18px;"></i>
										&nbsp;&nbsp;<span style="font-weight: bold;"><%=Config.message.get("COMPLAIN_MANAGE_BACK")%></span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-4 form_input login-box row_left"></div>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--群租管理TABLE-->
				<div class="row tab-pane fade in active tables">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box border purple">
							<table
								class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("SORT")%></th>
										<th><%=Config.message.get("COMPLAIN_USER_NUMBER")%></th>
										<th><%=Config.message.get("POLICY_ID")%></th>
										<th><%=Config.message.get("PUSH_TIME")%></th>
										<th><%=Config.message.get("COMPLAIN_USER_CODE")%></th>
										<th><%=Config.message.get("COMPLAIN_STATE_ID")%></th>
										<th><%=Config.message.get("COMPLAIN_CLIENT_IP")%></th>
										<th><%=Config.message.get("COMPLAIN_SHOW_TIME")%></th>
										<th><%=Config.message.get("COMPLAIN_IPS_SEQ")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach items="${result.LIST}" var="list" varStatus="v">
											<tr>
												<td>${v.count}</td>
												<td>${list.PushUser}</td>
												<td>${list.PolicyId}</td>
												<td>${list.PushTime}</td>
												<td>${list.PushImsi}</td>
												<td>${list.PushBsid}</td>
												<td>${list.PushIp}</td>
												<td>${list.ShowTime}</td>
												<td>${list.IpsSeq}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp"><jsp:param
											value="complaint/showPush?PushUser=${result.PushUser}&time=${result.time}&policyId=${result.policyId}"
											name="actionName" /></jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->

<script type="text/javascript">
function fanhui(PushUser,time){
	load_page('${basePath}complaint/showComplaint?PushUser='+encodeURIComponent(PushUser)+'&time='+time);
}

</script>