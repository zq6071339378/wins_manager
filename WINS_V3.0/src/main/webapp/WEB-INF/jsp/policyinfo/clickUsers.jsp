<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<!-- <script type="text/javascript">
$(document).ready(function() {
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	if(month >= 1 && month <= 9){
		month = "0" + month;
	}
	var day = date.getDate();
	if(day >= 0 && day <= 9){
		day = "0" + day;
	}
	var fullYear=year+"-"+month+"-"+day;
	var birthday=$('#birthday').val();
	if(birthday!=""){
		fullYear=birthday;
	}
  $('#birthday').daterangepicker(
	{
		singleDatePicker: true,
		startDate:fullYear
	});
}); 


</script> -->
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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("OPERATE_MANAGE")%></a></li>
								<li><a href="#"><%=Config.message.get("PUSH_REPORT_MANAGE")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("CLICK_USER_DETAIL")%></h3>
							</div>
							<div class="row col-md-12">
								<div class="description col-md-11 row_left"><%=Config.message.get("CLICK_USER_DETAIL_DESCRIBE")%></div>
								<div style="padding-left: 0px;" class="pull-right">
									<a href="#" style="text-decoration: none"
										onclick="fanhui('${result.startTime}','${result.endTime}')">
										<i class="fa fa-reply-all" style="font-size: 18px;"></i>&nbsp;&nbsp;<span
										style="font-weight: bold;"><%=Config.message.get("POLICYRCPORT_BACK")%></span>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-8 row_left">
							<div class="col-md-6 form_input login-box row_left">
								<form>
									<div class="form-group col-md-10 row_left search_icon">
										<a href="javascript:;" onclick="find()"><i
											class="fa fa-search"></i></a> <input type="search"
											id="exampleInputSearch" class="form-control"
											placeholder="<%=Config.message.get("PLEASE_INPUT_USER_NUMBER")%>">
									</div>
								</form>
							</div>
						</div>

						<%-- <div class="col-md-3">
									        <div class="input-prepend input-group col-md-3">
                            				<input type="text" value="${result.create_time}"  class="form-control active" id="birthday" style="width: 238px;" readonly="readonly"><span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
             				           		</div>
										</div> --%>
						<div class="col-md-4 pull-right">
							<div class="from-group col-md-6 pull-right">
								<button type="button" class="btn btn-primary"
									onclick="excelUsers()"><%=Config.message.get("EXPORT_USERS")%></button>
							</div>
							<div class="from-group col-md-6">
								<button type="button" class="btn btn-danger" onclick="excel()"><%=Config.message.get("EXPORT_DETAIL")%></button>
							</div>
						</div>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--群租管理TABLE-->
				<div class="tables" class="row tab-pane fade in active">
					<div class="col-md-12 row_left">
						<!-- BOX -->
						<div class="box border purple">
							<table
								class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("SORT")%></th>
										<th><%=Config.message.get("USER_NUMBER")%></th>
										<th><%=Config.message.get("CLICK_TIME")%></th>
										<th><%=Config.message.get("TERMAINAL_TYPE")%></th>
										<th><%=Config.message.get("PAGE_URL")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach items="${result.LIST}" var="list" varStatus="v">
											<tr>
												<td>${v.count}</td>
												<td>${list.PushUser}</td>
												<td>${list.ClickTime}</td>
												<td>	
										          	<c:set var="theString" value="${list.PushUa}"/>
														<c:if test="${fn:containsIgnoreCase(theString, 'Mobile')}">
														   <span>phone</span>
														</c:if>
														<c:if test="${fn:containsIgnoreCase(theString, 'windows')}">
														   <span>pc/pad</span>
														</c:if>
											   </td>
												<td>${list.ClickUrl}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="policyinfo/clickDetails?policyId=${result.policyId}&startTime=${result.startTime}&endTime=${result.endTime}&pushUser=${result.pushUser}"
											name="actionName"/></jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<input type="hidden" value="${result.policyId}" id="PolicyId">
				<input type="hidden" value="${result.endTime}" id="endTime">
				<input type="hidden" value="${result.startTime}" id="startTime">
				<input type="hidden" value="${result.pushUser}" id="pushUser">
				<input type="hidden" value="${result.backType}" id="backType">
				<input type="hidden" value="${result.policyIds}" id="policyIds">
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->


<script type="text/javascript">
function find(){
	var policyId=$("#PolicyId").val();
	var pushUser = $("#exampleInputSearch").val().trim();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	 load_page('${basePath}policyinfo/clickDetails?pushUser='+pushUser+'&policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime);
}

function excelUsers(){
	var policyId=$("#PolicyId").val();
	var pushUser = $("#exampleInputSearch").val().trim();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	 var excel=1;
	 window.location.href='${basePath}policyinfo/clickExcel?pushUser='+pushUser+'&policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&excel='+excel;
}
function excel(){
	var policyId=$("#PolicyId").val();
	var pushUser = $("#exampleInputSearch").val().trim();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var excel=2;
	window.location.href='${basePath}policyinfo/clickExcel?pushUser='+pushUser+'&policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&excel='+excel;
}
</script>


<script type="text/javascript">
function fanhui(startTime,endTime){
	var backType=$("#backType").val();
	if(backType==1){
		load_page('${basePath}policyinfo/showReport?startTime='+startTime+'&endTime='+endTime);
	}else if(backType==2){
		var policyIds=$("#policyIds").val();
		load_page('${basePath}policyinfo/dataCompare?policyIds='+policyIds+'&startTime='+startTime+'&endTime='+endTime);
	}
}

</script>