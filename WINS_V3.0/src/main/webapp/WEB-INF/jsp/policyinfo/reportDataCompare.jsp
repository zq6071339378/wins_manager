<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<script type="text/javascript">
	   $(document).ready(function() {
		   $('#doubleDate_id').daterangepicker({},
			   function(start, end) {
				   var startTime = start.format('YYYY-MM-DD');
				   var endTime = end.format('YYYY-MM-DD');
				   var dateN = new Date();
				   var dateMonth = parseInt(dateN.getMonth()) + 1;
				   var _nowDate = dateN.getFullYear() + '-'  + dateMonth + '-' + dateN.getDate(); //获取当前月份(0-11,0代表1月)
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
					   endMaxDate=startYear+'-'+startMonth+'-'+startMaxDay;
				   }else{ //结束年=开始年
					   if(endMonth>startMonth){
						   endMaxDate=startYear+'-'+startMonth+'-'+startMaxDay;
					   }else{
						   endMaxDate=startYear+'-'+startMonth+'-'+endDay;
						   if(endMaxDate>_nowDate){
							   endMaxDate=_nowDate;
						   }
					   }
				   }
				   endTime=endMaxDate;
				   if(startTime>endTime){
					   startTime=_nowDate;
					   endTime=_nowDate;
				   }
				   $("#start_Time").val(startTime);
				   $("#end_Time").val(endTime);
				   $("#doubleDate_id").val(startTime+'-'+endTime);
				   var policyIds=$("#policyIds").val();
				   load_page('policyinfo/dataCompare?startTime='+startTime+'&endTime='+endTime+'&policyIds='+policyIds);
		   });
	   });
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
									<a href="#"><%=Config.message.get("DATAS_OPERATION_MANAGEMENT")%></a>
								</li>
								<li>
									<a href="template.html"><%=Config.message.get("DATAS_REPORTFORMS_MANAGEMENT")%></a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("ACTIVITY_DATA")%></h3>
							</div>
							<div class="description">Simple Tables with exclusive UI experience</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-3">
							<div class="controls">
								<div class="input-group date" id="reservation">
									<input type="hidden" id="start_Time" value="${result.startTime}">
									<input type="hidden" id="end_Time" value="${result.endTime}">
									<input type="hidden" id="policyIds" value="${result.policyIds}">
									<input type="text" placeholder="Start time - End time" required="required" id="doubleDate_id" class="form-control" style="padding-left: 30px !important;" value="${result.startTime}-${result.endTime}">
									<span class="input-group-addon">
										<span class="fa fa-calendar"></span>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--群租管理TABLE-->
				<div class="row tab-pane fade in active  tables">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box border purple">
							<table class="datatable table table-striped table-bordered table-hover dataTable">
								<caption>
									<div class="row" style="background-color:#539FB8; margin: auto;">
										<h4><%=Config.message.get("ACTIVITY_DATA")%></h4>
										<!--<strong style="font-size: 20px;">本行内容是在标签内</strong>-->
									</div>
								</caption>
								<thead>
								<tr>
									<th><%=Config.message.get("ACTIVITY_POLICYNAME")%></th>
									<th><%=Config.message.get("DATAS_CITY_CITY")%></th>
									<th><%=Config.message.get("DATAS_PUSH_TIME")%></th>
									<th><%=Config.message.get("DATAS_TARGET_USER_AMOUT")%></th>
									<th><%=Config.message.get("DATAS_SHOWING_USERS")%></th>
									<th><%=Config.message.get("DATAS_CLICKING_USERS")%></th>
									<th><%=Config.message.get("DATAS_CLOSING_USERS")%></th>
									<th><%=Config.message.get("DATAS_CONSUMPTION_AMOUNT")%></th>
								</tr>
								</thead>
								<tbody>
								<c:if test="${!empty result.LIST}">
									<c:forEach items="${result.LIST}" var="list" varStatus="v">
										<tr>
											<td>${list.PolicyName}</td>
											<td>${list.ProvinceCity}</td>
											<td>${list.startTime}-${list.endTime}</td>
											<td>${list.TargetUsers}</td>
											<td>
												<a href="javascript:;" onclick="ShowUsers('${list.PolicyId}','${result.startTime}','${result.endTime}','2')">${list.ShowUsers}</a>
											</td>
											<td>
												<a href="javascript:;" onclick="ClickUsers('${list.PolicyId}','${result.startTime}','${result.endTime}','2')">${list.ClickUsers}</a>
											</td>
											<td>
												<a href="javascript:;" onclick="CloseUsers('${list.PolicyId}','${result.startTime}','${result.endTime}','2')">${list.CloseUsers}</a>
											</td>
											<td>
												<c:if test="${list.buyType eq 1}">
													${list.unitPrice*1000*list.showLimit}
												</c:if>
												<c:if test="${list.buyType eq 2}">
													${list.unitPrice*list.hitLimit}
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<div class="col-md-12"></div>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<div class="divide-10"></div>
				<div class="row tab-pane fade in active  tables">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box border purple">
							<table class="datatable table table-striped table-bordered table-hover dataTable">
								<caption>
									<div class="row" style="background-color: #539FB8; margin: auto;">
										<h4><%=Config.message.get("DATAS_DATAS_COMPARE")%></h4>
										<!--<strong style="font-size: 20px;">本行内容是在标签内</strong>-->
									</div>
								</caption>
								<thead>
								<tr>
									<th><%=Config.message.get("DATAS_POLICY_NAME")%></th>
									<th><%=Config.message.get("DATAS_CITY_CITY")%></th>
									<th><%=Config.message.get("DATAS_TARGET_USER_AMOUT")%></th>
									<th><%=Config.message.get("DATAS_PUSH_TERMINAL")%></th>
									<th><%=Config.message.get("DATAS_START_TIME")%></th>
									<th><%=Config.message.get("DATAS_END_TIME")%></th>
									<th><%=Config.message.get("DATAS_STATUS_REVIEW")%></th>
								</tr>
								</thead>
								<tbody>
								<c:if test="${!empty result.LIST}">
									<c:forEach items="${result.LIST}" var="list" varStatus="v">
										<tr>
											<td>${list.PolicyName}</td>
											<td>${list.ProvinceCity}</td>
											<td>${list.TargetUsers}</td>
											<td>
												<c:if test="${list.TerminalType eq 0}">
													pc/pad
												</c:if>
												<c:if test="${list.TerminalType eq 1}">
													phone
												</c:if>
											</td>
											<td>
												${list.startTime}
											</td>
											<td>
												${list.endTime}
											</td>
											<td>
												<c:if test="${list.CheckStatus eq 0}">
													<%=Config.message.get("DATAS_TO_BE_APPROVED")%>
												</c:if>
												<c:if test="${list.CheckStatus eq 1}">
													<%=Config.message.get("DATAS_APPROVED")%>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								</tbody>
							</table>
							<div class="divide-10"></div>
							<div class="divide-10"></div>
							<div class="divide-10"></div>
							<!-- 用户数据趋势图start-->
							<div class="row">
								<div class="col-md-16">
									<!-- BOX -->
									<div class="box border green">
										<div class="box-title title_font">
											<h5>
												<i class="fa fa-bars"></i> <span class="hidden-inline-mobile"> <%=Config.message.get("POLICY_VIEW_USERSTRENDDIAGRAM")%></span>
											</h5>
										</div>
										<div class="box-body">
											<div class="tabbable header-tabs">
												<input type="hidden" value="0" id="ctype" />
												<ul class="nav nav-tabs">
													<li>
														<a data-toggle="tab" href="#box_tab3" onclick="$('#box_tab1').hide();$('#box_tab2').hide();$('#box_tab3').show(); $('#ctype').val(2);loadReportType()">
															<i class="fa fa-male"></i>
															<span class="hidden-inline-mobile"><%=Config.message.get("POLICY_VIEW_CLICKUSERS")%></span>
														</a>
													</li>
													<li>
														<a data-toggle="tab" href="#box_tab2" onclick="$('#box_tab1').hide();$('#box_tab3').hide();$('#box_tab2').show(); $('#ctype').val(1);loadReportType()">
															<i class="fa fa-male"></i> <span class="hidden-inline-mobile"><%=Config.message.get("POLICY_VIEW_SHOWUSERS")%></span>
														</a>
													</li>
													<li class="active">
														<a data-toggle="tab" href="#box_tab1" onclick="$('#box_tab2').hide();$('#box_tab3').hide();$('#box_tab1').show(); $('#ctype').val(0);loadReportType()">
															<i class="fa fa-male"></i> <span class="hidden-inline-mobile"><%=Config.message.get("POLICY_VIEW_PUSHUSERS")%></span>
														</a>
													</li>
												</ul>
												<div class="tab-content">
													<div id="box_tab1" class="tab-pane fade in active">
														<!-- TAB 1 -->
														<div class="chart" id="pushUsers"
															 style="padding: 0px; position: relative;">
															<canvas class="flot-base"
																	style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1046px; height: 300px;"
																	width="1046" height="300"></canvas>
															<div class="flot-text"
																 style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);">
																<div class="flot-x-axis flot-x1-axis xAxis x1Axis"
																	 style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
																	<div
																			style="position: absolute; max-width: 116px; top: 286px; left: 24px; text-align: center;"
																			class="flot-tick-label tickLabel">0</div>
																	<div
																			style="position: absolute; max-width: 116px; top: 286px; left: 186px; text-align: center;"
																			class="flot-tick-label tickLabel">5</div>
																	<div
																			style="position: absolute; max-width: 116px; top: 286px; left: 346px; text-align: center;"
																			class="flot-tick-label tickLabel">10</div>
																	<div
																			style="position: absolute; max-width: 116px; top: 286px; left: 508px; text-align: center;"
																			class="flot-tick-label tickLabel">15</div>
																	<div
																			style="position: absolute; max-width: 116px; top: 286px; left: 670px; text-align: center;"
																			class="flot-tick-label tickLabel">20</div>
																	<div
																			style="position: absolute; max-width: 116px; top: 286px; left: 832px; text-align: center;"
																			class="flot-tick-label tickLabel">25</div>
																	<div
																			style="position: absolute; max-width: 116px; top: 286px; left: 994px; text-align: center;"
																			class="flot-tick-label tickLabel">30</div>
																</div>
																<div class="flot-y-axis flot-y1-axis yAxis y1Axis"
																	 style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
																	<div
																			style="position: absolute; top: 269px; left: 5px; text-align: right;"
																			class="flot-tick-label tickLabel">0</div>
																	<div
																			style="position: absolute; top: 202px; left: 5px; text-align: right;"
																			class="flot-tick-label tickLabel">2</div>
																	<div
																			style="position: absolute; top: 135px; left: 5px; text-align: right;"
																			class="flot-tick-label tickLabel">4</div>
																	<div
																			style="position: absolute; top: 68px; left: 5px; text-align: right;"
																			class="flot-tick-label tickLabel">6</div>
																	<div
																			style="position: absolute; top: 1px; left: 5px; text-align: right;"
																			class="flot-tick-label tickLabel">8</div>
																</div>
															</div>
															<canvas class="flot-overlay"
																	style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1046px; height: 300px;"
																	width="1046" height="300"></canvas>
														</div>
														<hr class="margin-bottom-0">
														<!-- /TAB 1 -->
													</div>
													<div id="box_tab2" class="tab-pane fade">
														<div class="row">
															<div class="col-md-12">
																<div class="demo-container">
																	<div class="demo-placeholder" id="showUsers"
																		 style="padding: 0px; position: relative;">
																		<canvas class="flot-base"
																				style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 100px; height: 100px;"
																				width="100" height="100"></canvas>
																		<div class="flot-text"
																			 style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);">
																			<div class="flot-x-axis flot-x1-axis xAxis x1Axis"
																				 style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
																				<div
																						style="position: absolute; max-width: 33px; top: 100px; left: 15px; text-align: center;"
																						class="flot-tick-label tickLabel">0</div>
																				<div
																						style="position: absolute; max-width: 33px; top: 100px; left: 56px; text-align: center;"
																						class="flot-tick-label tickLabel">5</div>
																			</div>
																			<div class="flot-y-axis flot-y1-axis yAxis y1Axis"
																				 style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
																				<div
																						style="position: absolute; top: 90px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">-1.2</div>
																				<div
																						style="position: absolute; top: 83px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">-1.0</div>
																				<div
																						style="position: absolute; top: 76px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">-0.8</div>
																				<div
																						style="position: absolute; top: 70px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">-0.6</div>
																				<div
																						style="position: absolute; top: 63px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">-0.4</div>
																				<div
																						style="position: absolute; top: 56px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">-0.2</div>
																				<div
																						style="position: absolute; top: 49px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">0.0</div>
																				<div
																						style="position: absolute; top: 42px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">0.2</div>
																				<div
																						style="position: absolute; top: 35px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">0.4</div>
																				<div
																						style="position: absolute; top: 29px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">0.6</div>
																				<div
																						style="position: absolute; top: 22px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">0.8</div>
																				<div
																						style="position: absolute; top: 15px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">1.0</div>
																				<div
																						style="position: absolute; top: 8px; left: 5px; text-align: right;"
																						class="flot-tick-label tickLabel">1.2</div>
																			</div>
																		</div>
																		<canvas class="flot-overlay" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 100px; height: 100px;"
																				width="100" height="100"></canvas>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div id="box_tab3" class="tab-pane fade">
														<div class="row">
															<div class="col-md-12">
																<div class="demo-container">
																	<div class="demo-placeholder" id="clickUsers"></div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- /BOX -->
								</div>
							</div>
							<!-- 用户数据趋势图end -->
							<div class="row">
								<div class="dataTables_footer clearfix">
									<div class="col-md-12"></div>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<!--群组管理TABLE END-->
				<div class="footer-tools">
					<span class="go-top">
						<i class="fa fa-chevron-up"></i> Top
					</span>
				</div>
			</div><!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript" src="js/bootstrap-treeview.js"></script>
<script type="text/javascript">
	$(function(){
		var policyIds=$("#policyIds").val();
		var startTime=$("#start_Time").val();
		var endTime=$("#end_Time").val();
		getTrendInfo(policyIds,startTime,endTime);
	});
	var maxValue=0;
	var m = 0;
	var j = 0;
	var k = 0;
	var n = 0;
	var c = 0;
	var pushuserarray = [];
	var showuserarray = [];
	var clickuserarray = [];
	var pushuserstr = "";
	var showuserstr = "";
	var clickuserstr = "";
	var xarray=[];
	var time=[];
	var times = [];
	var type=[];
	var types=[];
	var pushUser=[];
	var pushUsers=[];
	var showUser=[];
	var showUsers=[];
	var clickUser=[];
	var clickUsers=[];
	var pushuserdata=[];
	var pushuserdatas=[];
	var showuserdata=[];
	var showuserdatas=[];
	var clickuserdata=[];
	var clickuserdatas=[];
	function getTrendInfo(policyIds,startTime,endTime){
		$.ajax({
			url:"${basePath}policyinfo/trendData",
			method:"post",
			data:{"policyIds":policyIds,"startTime":startTime,"endTime":endTime},
			success:function(data){
				console.log(data);
				m = 0;
				j = 0;
				k = 0;
				n = 0;
				c = 0;
				pushuserarray = [];
				showuserarray = [];
				clickuserarray = [];
				pushuserstr = "";
				showuserstr = "";
				clickuserstr = "";
				time=[];
				times = [];
				type=[];
				types=[];
				xarray=[];
				pushUser=[];
				pushUsers=[];
				showUser=[];
				showUsers=[];
				clickUser=[];
				clickUsers=[];
				pushuserdata=[];
				pushuserdatas=[];
				showuserdata=[];
				showuserdatas=[];
				clickuserdata=[];
				clickuserdatas=[];
				var policy=data.result.LIST;
				for(var i=0;i<policy.length;i++){
					time[i]=policy[i].ReportPeriod;
					type[i]=policy[i].PolicyName;
					pushUser[i]=policy[i].PushUsers;
					showUser[i]=policy[i].ShowUsers;
					clickUser[i]=policy[i].ClickUsers;
				}
				types=type.uniqueVal(); //将重复的策略名去掉
				if(startTime==endTime){ //多策略一天的数据
					//补全24小时
					for(var i = 0;i<types.length;i++){
						while (n < 24) {
							while (n + 1 != time[k] && n < 24) {
								times[j] = n + 1;
								pushUsers[j] = 0;
								clickUsers[j] = 0;
								showUsers[j] = 0;
								j++;
								n++;
							}
							if (n < 24) {
								times[j] = time[k];
								pushUsers[j] = pushUser[k];
								clickUsers[j] = clickUser[k];
								showUsers[j] = showUser[k];
								j++;
								k++;
								n++;
							}
						}
						if(n == 24){
							n = 0;
						}
					}
					for(var i = 0; i<24;i++){
						xarray[i] = times[i] ;
					}
					//将数据处理为json
					for (var i = 0; i < types.length; i++) {
						pushuserarray.push("{name:'"+types[i]+"'");
						showuserarray.push("{name:'"+types[i]+"'");
						clickuserarray.push("{name:'"+types[i]+"'");
						if (n < 24) {
							for (var g = 0; g < 24; g++ && c < times.length) {
								if (g == 0) {
									pushuserarray.push("data:["+pushUsers[c]);
									showuserarray.push("data:["+showUsers[c]);
									clickuserarray.push("data:["+clickUsers[c]);
									c++;
								}else if (g == 23) {
									pushuserarray.push(pushUsers[c]+"]}");
									showuserarray.push(showUsers[c]+"]}");
									clickuserarray.push(clickUsers[c]+"]}");
									c++;
								}else {
									pushuserarray.push(pushUsers[c]);
									showuserarray.push(showUsers[c]);
									clickuserarray.push(clickUsers[c]);
									c++;
								}
							}
						}
						if(n == 24){
							n = 0;
						}
					}
				}else{//多策略多天的数据
					maxValue=parseInt(data.result.maxDay);
					//补全天数
					for(var i = 0;i<types.length;i++){
						while (n < maxValue) {
							while (n + 1 != time[k] && n < maxValue) {
								times[j] = n + 1;
								pushUsers[j] = 0;
								clickUsers[j] = 0;
								showUsers[j] = 0;
								j++;
								n++;
							}
							if (n < maxValue) {
								times[j] = time[k];
								pushUsers[j] = pushUser[k];
								clickUsers[j] = clickUser[k];
								showUsers[j] = showUser[k];
								j++;
								k++;
								n++;
							}
						}
						if(n == maxValue){
							n = 0;
						}
					}
					for(var i = 0; i<maxValue;i++){
						xarray[i] = times[i] ;
					}
					//将数据处理为json
					for (var i = 0; i < types.length; i++) {
						pushuserarray.push("{name:'"+types[i]+"'");
						showuserarray.push("{name:'"+types[i]+"'");
						clickuserarray.push("{name:'"+types[i]+"'");
						if (n < maxValue) {
							for (var g = 0; g < maxValue; g++ && c < times.length) {
								if (g == 0) {
									pushuserarray.push("data:["+pushUsers[c]);
									showuserarray.push("data:["+showUsers[c]);
									clickuserarray.push("data:["+clickUsers[c]);
									c++;
								}else if (g == (maxValue-1)) {
									pushuserarray.push(pushUsers[c]+"]}");
									showuserarray.push(showUsers[c]+"]}");
									clickuserarray.push(clickUsers[c]+"]}");
									c++;
								}else {
									pushuserarray.push(pushUsers[c]);
									showuserarray.push(showUsers[c]);
									clickuserarray.push(clickUsers[c]);
									c++;
								}
							}
						}
						if(n == maxValue){
							n = 0;
						}
					}
				}
				pushuserstr = pushuserarray.toString();
				showuserstr = showuserarray.toString();
				clickuserstr = clickuserarray.toString();
				for(var i = 0;i<types.length;i++){
					if(i != types.length-1){
						pushuserdata[i] = pushuserstr.substring(pushuserstr.indexOf("{"),pushuserstr.indexOf("}")+1);
						showuserdata[i] = showuserstr.substring(showuserstr.indexOf("{"),showuserstr.indexOf("}")+1);
						clickuserdata[i] = clickuserstr.substring(clickuserstr.indexOf("{"),clickuserstr.indexOf("}")+1);
						pushuserstr = pushuserstr.replace(pushuserdata[i]+",","");
						showuserstr = showuserstr.replace(showuserdata[i]+",","");
						clickuserstr = clickuserstr.replace(clickuserdata[i]+",","");
					}else{
						pushuserdata[i] = pushuserstr.substring(pushuserstr.indexOf("{"),pushuserstr.indexOf("}")+1);
						showuserdata[i] = showuserstr.substring(showuserstr.indexOf("{"),showuserstr.indexOf("}")+1);
						clickuserdata[i] = clickuserstr.substring(clickuserstr.indexOf("{"),clickuserstr.indexOf("}")+1);
						pushuserstr = pushuserstr.replace(pushuserdata[i],"");
						showuserstr = showuserstr.replace(showuserdata[i],"");
						clickuserstr = clickuserstr.replace(clickuserdata[i],"");
					}
				}
				for(var i = 0; i<pushuserdata.length;i++){
					pushuserdatas[i] = eval('('+pushuserdata[i]+')');
				}
				for(var i = 0; i<showuserdata.length;i++){
					showuserdatas[i] = eval('('+showuserdata[i]+')');
				}
				for(var i = 0; i<clickuserdata.length;i++){
					clickuserdatas[i] = eval('('+clickuserdata[i]+')');
				}
				loadReportType('pushUsers');
			}
		});
	}
	function pushUserChart(_id){
		$('#'+_id).highcharts({
			chart:{
				type:'spline'
			},
			title: {
				text: '',
				x: -20 //center
			},
			credits:{
				enabled:false
			},
			subtitle: {
				text: '',
				x: -20
			},
			xAxis: {
				categories: xarray
			},
			yAxis: {
				title: {
					text: ''
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {
				valueSuffix:'times'
			},
			legend: {
				align: 'center',
				verticalAlign: 'bottom',
				borderWidth: 0
			},
			series: pushuserdatas
		});
	}
	function showUserChart(_id){
		$('#'+_id).highcharts({
			chart:{
				type:'spline'
			},
			title: {
				text: '',
				x: -20 //center
			},
			credits:{
				enabled:false
			},
			subtitle: {
				text: '',
				x: -20
			},
			xAxis: {
				categories: xarray
			},
			yAxis: {
				title: {
					text: ''
				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {
				valueSuffix: ''
			},
			legend: {
				align: 'center',
				verticalAlign: 'bottom',
				borderWidth: 0
			},
			series:showuserdatas
		});
	}
	function clickUserChart(_id){
		$('#'+_id).highcharts({
			chart:{
				type:'spline'
			},
			title: {
				text: '',
				x: -20 //center
			},
			subtitle: {
				text: '',
				x: -20
			},
			credits: {
				enabled: false
			} ,
			xAxis: {
				categories:xarray
			},
			yAxis: {
				title: {
					text: ''

				},
				plotLines: [{
					value: 0,
					width: 1,
					color: '#808080'
				}]
			},
			tooltip: {
				valueSuffix: 'times'
			},
			legend: {
				align: 'center',
				verticalAlign: 'bottom',
				borderWidth: 0
			},
			series: clickuserdatas
		});
	}
	function loadReportType(){
		var v=$("#ctype").val();
		if(v==0){
			pushUserChart('pushUsers');
		}
		else if(v==1){
			showUserChart('showUsers');
		}
		else{
			clickUserChart('clickUsers');
		}
	}

	Array.prototype.uniqueVal = function(){//js数组去重
		var n = []; //一个新的临时数组
		for(var i = 0; i < this.length; i++) //遍历当前数组
		{
			//如果当前数组的第i已经保存进了临时数组，那么跳过，
			//否则把当前项push到临时数组里面
			if (n.indexOf(this[i]) == -1) n.push(this[i]);
		}
		return n;
	}
</script>
<script type="text/javascript">
   var sumprice=$("#unitPrice").html();
   var n = parseFloat(sumprice).toFixed(2);
   var re = /(\d{1,3})(?=(\d{3})+(?:\.))/g;
   var ma= n.replace(re, "$1,");
   $("#unitPrice").html(ma);

   function ShowUsers(policyId,startTime,endTime,backType){
	   var policyIds=$("#policyIds").val();
	   load_page('${basePath}policyinfo/showDetails?policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&backType='+backType+'&policyIds='+policyIds);
   }
   function ClickUsers(policyId,startTime,endTime,backType){
	   var policyIds=$("#policyIds").val();
	   load_page('${basePath}policyinfo/clickDetails?policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&backType='+backType+'&policyIds='+policyIds);
   }
   function CloseUsers(policyId,startTime,endTime,backType){
	   var policyIds=$("#policyIds").val();
	   load_page('${basePath}policyinfo/closeDetails?policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&backType='+backType+'&policyIds='+policyIds);
   }

	function showReport(PolicyId,ReportDate,PolicyName){
		var temp=0;
		load_page('${basePath}policyinfo/reportDetails?ReportDate='+ReportDate+'&PolicyId='+PolicyId+'&PolicyName='+PolicyName+'&temp='+temp);
	}

</script>
