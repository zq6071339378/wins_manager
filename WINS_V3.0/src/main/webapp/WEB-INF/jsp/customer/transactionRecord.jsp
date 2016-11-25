<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		$('#doubleDate_id').daterangepicker({},
				function(start, end) {
					var startTime = start.format('YYYY-MM-DD');
					var endTime = end.format('YYYY-MM-DD');
					var dateN = new Date();
					var dateMonth = parseInt(dateN.getMonth()) + 1;
					var _nowDate = dateN.getFullYear()+'-'+ dateMonth +'-'+dateN.getDate();//获取当前月份(0-11,0代表1月)

					var startDate=new Date(startTime);
					var startYear=startDate.getFullYear();
					var startMonth=parseInt(startDate.getMonth())+1;
					var startMaxDay=new Date(startYear,startMonth,0).getDate();

					var endDate=new Date(endTime);
					var endYear=endDate.getFullYear();
					var endMonth=parseInt(endDate.getMonth())+1;
					var endDay=endDate.getDate();
					if((String(endDay)).length==1){
						endDay="0"+endDay;
					}
					var endMaxDate = "";
					if(endYear>startYear){//结束年>开始年
						endMaxDate=startYear+'-'+endMonth+'-'+startMaxDay;
					}else{ //结束年=开始年
						/*if(endMonth>startMonth){
							endMaxDate=startYear+'-'+startMonth+'-'+startMaxDay;
						}else{
							endMaxDate=startYear+'-'+startMonth+'-'+endDay;
							if(endMaxDate>_nowDate){
								endMaxDate=_nowDate;
							}
						}*/
						endMaxDate=startYear+'-'+endMonth+'-'+endDay;
					}
					endTime=endMaxDate;
					if(startTime>endTime){
						startTime=_nowDate;
						endTime=_nowDate;
					}
					$("#start_Time").val(startTime);
					$("#end_Time").val(endTime);
					$("#doubleDate_id").val(startTime+'-'+endTime);
					var customerId=$("#customer_Id").val();
					load_page('customer/transactionRecord.html?startTime='+startTime+'&endTime='+endTime+'&customerId='+customerId);
				});
	});

	function exportTransactionRecord(){
		var customerId=$("#customer_Id").val();
		var startTime=$("#start_Time").val();
		var endTime=$("#end_Time").val();
		window.location.href='${basePpath}customer/exportRecord?startTime='+startTime+'&endTime='+endTime+'&customerId='+customerId;
		/*$.ajax({
			url:"customer/exportRecord",
			method:"post",
			data:{"startTime":startTime,"endTime":endTime,'customerId':customerId},
			success:function(data){

			},
			error: function () {
				alert("System exception, please try again later!");
			}
		});*/
	}
</script>
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
								<li>
									<i class="fa fa-home"></i>
									<a href="#">首页</a>
								</li>
								<li>
									<a href="template.html">系统管理</a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left">交易记录</h3>
							</div>
							<div class="description">Simple Tables with exclusive UI experience</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12" style="padding-left: 0px;">
						<div class="col-md-3" style="padding-left: 0px;">
							<div class="controls">
								<div class="input-group date" id="reservation">
									<input type="hidden" id="customer_Id" value="${result.customerId}">
									<input type="hidden" id="start_Time" value="${result.startTime}">
									<input type="hidden" id="end_Time" value="${result.endTime}">
									<input type="text" placeholder="Start time - End time" required="required" id="doubleDate_id" class="form-control" style="padding-left: 30px !important;" value="${result.startTime}-${result.endTime}">
									<span class="input-group-addon">
										<span class="fa fa-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="from-group pull-right">
							<div class="col-md-1">
								<div class="input-append" style="margin-right: 20px;">
									<a class="btn btn-primary" onclick="exportTransactionRecord();">导出&nbsp;</a>
								</div>
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
							<table class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
								<tr>
									<th>序号</th>
									<th>合同编号</th>
									<th>公司名称</th>
									<th>交易类型</th>
									<th>交易金额</th>
									<th>交易后余额</th>
									<th>交易说明</th>
									<th>操作账户</th>
									<th>交易日期</th>
								</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result.list}">
										<c:forEach var="customer" items="${result.list}" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>${customer.contractCode}</td>
												<td>${customer.customerCompany}</td>
												<td>
													<c:if test="${customer.tradeType eq 1}">支出</c:if>
													<c:if test="${customer.tradeType eq 2}">收入</c:if>
												</td>
												<td>${customer.tradeAmount}</td>
												<td>${customer.accountBalance}</td>
												<td>${customer.tradeRemark}</td>
												<td>${customer.createName}</td>
												<td><fmt:formatDate value="${customer.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="customer/transactionRecord.html?customerId=${result.customerId}&startTime=${result.startTime}&endTime=${result.endTime}" name="actionName"/>
									</jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
			</div><!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
