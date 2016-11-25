<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		var day = date.getDate();
		if (day >= 0 && day <= 9) {
			day = "0" + day;
		}
		var fullYear = year + "-" + month + "-" + day;
		var birthday = $('#birthday').val();
		if (birthday == null) {
			$('#birthday').val(fullYear);
			birthday = fullday;
		}
		$('#birthday').daterangepicker({
			singleDatePicker : true,
			startDate : birthday,
		}, function() {
			dateSelect();
		});

	});
</script>
<script src="${basePath}js/jquery.form.js"></script>
<script
	src="${basePath}js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${basePath}js/jQuery-Knob/js/jquery.knob.min.js"></script>
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("ROLEMANAGE_HOME")%></a></li>
								<li><a href="#"><%=Config.message.get("ROLEMANAGE_SYSTEMMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("MONITORLIST_MONNITORMANAGE")%></h3>
							</div>
							<div class="description"><%=Config.message.get("MONITORLIST_MONITORMANAGEMENTCHECK")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-2" style="padding-left: 0px;">
							<div class="col-md-6" style="padding-left: 0px;">
								<div class="input-group date">
									<input type="text" style="width: 200px;" value="${Report_Date}"
										class="form-control" id="birthday" readonly="readonly">
									<span class="input-group-addon"><span
										class="fa fa-calendar"></span> </span>
								</div>
							</div>
						</div>
						<div class="col-md-1 pull-right ">
							<div class="input-append">
								<a class="btn btn-primary"
									onclick="reportExcel('${result.PolicyName}')">Export&nbsp;</a>
							</div>
						</div>
						<div class="col-md-1 pull-right" style="left: -100px;">
							<button id="monitorAlarm" href="#box-config" data-toggle="modal"
								class="btn btn-danger" type="button" onclick="getAlarmInfo();"><%=Config.message.get("MONITORLIST_ALARMSETTING")%></button>
						</div>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--群租管理TABLE-->
				<div class="row tab-pane fade in active  tables">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box border purple">
							<table
								class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("MONITORLIST_SORT")%></th>
										<th><%=Config.message.get("MONITORLIST_EQUIPMENTNAME")%></th>
										<th><%=Config.message.get("MONITORLIST_IP")%></th>
										<th><%=Config.message.get("MONITORLIST_CPUUSER")%></th>
										<th><%=Config.message.get("MONITORLIST_MEMORYUSERATIO")%></th>
										<th><%=Config.message.get("MONITORLIST_HARDDISKUSERRATIO")%></th>
										<th><%=Config.message.get("MONITORLIST_NETWORK")%></th>
										<th><%=Config.message.get("MONITORLIST_MONITORING")%></th>
										<th><%=Config.message.get("MONITORLIST_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach items="${result.list}" var="list" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>${list.Server_Name}</td>
												<td>${list.Server_Ip}</td>
												<td>${list.Cpu_Usage}%</td>
												<td>${list.Memory_Usage}%</td>
												<td>${list.Harddisk_Usage}%</td>
												<td>${list.Network_Uplink}kbps/${list.Network_Downlink}kbps</td>
												<c:set var="idAsString">${list.Report_Period}</c:set>
												<td><fmt:formatDate value="${list.Report_Date}"
														pattern="yyyy-MM-dd" />&nbsp;&nbsp;<c:if
														test="${fn:length(idAsString)<2}">0</c:if>${list.Report_Period}:00:00</td>
												<td><a class="btn btn-default" href="javascript:;"
													title="table"
													onclick="load_page('systasks/reportshow.html?Report_Date=${list.Report_Date}&Create_Time=<fmt:formatDate value="${list.Create_Time}" pattern="yyyyMMddHHmmss"/>&Server_Ip=${list.Server_Ip}&Server_Name=${list.Server_Name}&Cpu_Usage=${list.Cpu_Usage}&Memory_Usage=${list.Memory_Usage}&Harddisk_Usage=${list.Harddisk_Usage}&Network_Uplink=${list.Network_Uplink}&Network_Downlink=${list.Network_Downlink}');"><i
														class="fa fa-bar-chart-o"></i></a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp"><jsp:param
											value="monitorList.html?Report_Date=${Report_Date}"
											name="actionName" /></jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
						<div class="modal fade" id="box-config" tabindex="-1"
							role="dialog" data-backdrop="static"
							aria-labelledby="myModalLabel" aria-hidden="false">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="">
										<div class="panel panel-default">
											<div class="panel-heading">
												<button class="close" aria-hidden="true"
													data-dismiss="modal" type="button">×</button>
												<h4> <%=Config.message.get("MONITORLIST_ALARMSETTING")%></h4>
											</div>
											<div class="panel-body">
												<form action="" method="post" enctype="multipart/form-data"
													class="form-horizontal">
													<div class="form-group msg_font form_group">
														<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("MONITORLIST_CPUUSER")%>：</label>
														<div class="col-sm-5">
															<input id="cpuSlider" style="width: 220px;" type="range"
																min="0" max="100" />
														</div>
														<div class="col-md-1">
															<div class="percent">
																<span id="cpuValue">0</span>%
															</div>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("MEMORY_ALARMING")%>：</label>
														<div class="col-sm-5">
															<input id="memSlider" style="width: 220px;" type="range"
																min="0" max="100" />
														</div>
														<div class="col-md-1">
															<div class="percent">
																<span id="memValue">0</span>%
															</div>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("MONITORLIST_HARDDISKUSERRATIO")%>：</label>
														<div class="col-sm-5">
															<input id="diskSlider" style="width: 220px;" type="range"
																min="0" max="100" />
														</div>
														<div class="col-md-1">
															<div class="percent">
																<span id="diskValue">0</span>%
															</div>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("MONITORLIST_RECEPTIONACCOUNT")%>：</label>
														<div class="input-append">
															<div class="col-md-8">
																<textarea class="form-control" name="textarea"
																	id="receiveName" cols="5" rows="1"></textarea>
															</div>
														</div>
													</div>
												</form>
											</div>
											<input type="hidden" id="sure_delete" value="" /> <input
												type="hidden" id="alarmId" value="" />
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
												<button type="button" class="btn btn-primary"
													data-dismiss="modal" onclick="saveAlarm();"><%=Config.message.get("MONITORLIST_SAVE")%></button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript">
	function dateSelect() {
		var Report_Date = $("#birthday").val();
		load_page('monitorList.html?Report_Date=' + Report_Date);
	}

	function reportExcel(PolicyName) {
		var Report_Date = $("#birthday").val();
		window.location.href = 'systasks/reportExport?Report_Date='
				+ Report_Date;
	}

	$("#cpuSlider").change(function() {
		$("#cpuValue").html(this.value);
	});
	$("#memSlider").change(function() {
		$("#memValue").html(this.value);
	});
	$("#diskSlider").change(function() {
		$("#diskValue").html(this.value);
	});

	function getAlarmInfo() {
		$.ajax({
			url : "systasks/monitorAlarmJson",
			type : "post",
			success : function(data) {
				$("#cpuSlider").val(data.result.Cpu_Limit);
				$("#cpuValue").html(data.result.Cpu_Limit);
				$("#memSlider").val(data.result.Memory_Limit);
				$("#memValue").html(data.result.Memory_Limit);
				$("#diskSlider").val(data.result.Harddisk_Limit);
				$("#diskValue").html(data.result.Harddisk_Limit);
				$("#receiveName").val(data.result.Receive_Name);
				$("#alarmId").val(data.result.Alarm_Id);
			}
		});
	}

	function saveAlarm() {
		var Cpu_Limit = $("#cpuSlider").val();
		var Memory_Limit = $("#memSlider").val();
		var Harddisk_Limit = $("#diskSlider").val();
		var Receive_Name = $("#receiveName").val();
		var User_Id = $("#alarmId").val();
		$.ajax({
			url : "systasks/updateAlarm",
			type : "post",
			data : {
				"Cpu_Limit" : Cpu_Limit,
				"Memory_Limit" : Memory_Limit,
				"Harddisk_Limit" : Harddisk_Limit,
				"Receive_Name" : Receive_Name,
				"User_Id" : User_Id
			},
			success : function(data) {
				if (data.code == 1) {
					alert(data.desc);
					load_page("monitorList.html");
				} else {
					alert(data.desc);
					load_page("monitorList.html");
				}
			}
		});
	}
</script>
