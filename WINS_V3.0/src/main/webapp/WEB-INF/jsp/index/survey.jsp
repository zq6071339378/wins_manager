<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<!--main_container-->
<script type="text/javascript">
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
	if(birthday.length==0){
		$('#birthday').val(fullYear);
		//birthday=fullday;
	}
    $('#birthday').daterangepicker({
    	  singleDatePicker:true,
           startDate:fullYear
    },
    function (){
		dateSelect();
	});
 }); 
     
</script>
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("POLICY_VIEW_POLICYVIEW")%></a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("POLICY_VIEW_POLICYVIEW")%></h3>
							</div>
							<div class="row">
								<div class="description col-md-7 row_left"><%=Config.message.get("POLICY_VIEW_PUSHATTENTION")%></div>
								<div class="pull-right col-md-3">
									<div class='input-group date global_date'>
										<input type='text' class="form-control" id="birthday" style="cursor:pointer"
											readonly="readonly" value="" /> <span
											class="input-group-addon"><span class="fa fa-calendar"></span>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<!--Basic indicators-->
				<div class="row">
					<div class="col-md-16">
						<!-- BOX -->
						<div class="box border green">
							<div class="box-title  title_font">
								<h5>
									<i class="fa fa-bars"></i> <span class="hidden-inline-mobile"><%=Config.message.get("POLICY_VIEW_BASICINDICATORS")%></span>
								</h5>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="mysurvey">
										<div class="index_titles">
											<ul style="text-align: center;" class="nav nav_uls" id="myTab">
												<li>
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_INTERNNETUSERS")%></h5>
													<p id="onlineUsers"></p>
													</p>
												</li>
												<li class=" active">
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_ONLINEUSERS")%></h5>
													<p id="radiusUsers"></p>
													</p>
												</li>
												<li>
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_PUSHUSERNUMBER")%></h5>
													<p id="pushUserMsg"></p>
													</p>
												</li>
												<li>
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_SHOWUSERNUMBER")%></h5>
													<p id="showUserMsg"></p>
													</p>
												</li>
												<li>
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_CLICKUSERTIMES")%></h5>
													<p id="clickUserMsg"></p>
													</p>
												</li>
												<li>
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_ARRIVALRATE")%></h5>
													<p id="receiveRatioMsg"></p>
													</p>
												</li>
												<li>
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_SHOWRATE")%></h5>
													<p id="showRatioMsg"></p>
													</p>
												</li>
												<li>
													<p data-toggle="tab" href="#home">
													<h5><%=Config.message.get("POLICY_VIEW_CLICKRATE")%></h5>
													<p id="clickRatioMsg"></p>
													</p>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<!--Basic indicators-->
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
															<canvas class="flot-overlay"
																style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 100px; height: 100px;"
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
				<!--上网走势图start-->
				<!-- <div class="row">
					<div class="col-md-12">
						BOX
						<div class="box border green">
							<div class="box-title title_font">
								<h5><i class="fa fa-signal"></i>上网走势图</h5>
								
							</div>
							<div class="box-body">
								<div class="chart" id="chart_2" style="padding: 0px; position: relative;"><canvas class="flot-base" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1046px; height: 300px;" width="1046" height="300"></canvas><div class="flot-text" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);"><div class="flot-x-axis flot-x1-axis xAxis x1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div style="position: absolute; max-width: 65px; top: 286px; left: 48px; text-align: center;" class="flot-tick-label tickLabel">2</div><div style="position: absolute; max-width: 65px; top: 286px; left: 119px; text-align: center;" class="flot-tick-label tickLabel">4</div><div style="position: absolute; max-width: 65px; top: 286px; left: 189px; text-align: center;" class="flot-tick-label tickLabel">6</div><div style="position: absolute; max-width: 65px; top: 286px; left: 260px; text-align: center;" class="flot-tick-label tickLabel">8</div><div style="position: absolute; max-width: 65px; top: 286px; left: 328px; text-align: center;" class="flot-tick-label tickLabel">10</div><div style="position: absolute; max-width: 65px; top: 286px; left: 398px; text-align: center;" class="flot-tick-label tickLabel">12</div><div style="position: absolute; max-width: 65px; top: 286px; left: 469px; text-align: center;" class="flot-tick-label tickLabel">14</div><div style="position: absolute; max-width: 65px; top: 286px; left: 539px; text-align: center;" class="flot-tick-label tickLabel">16</div><div style="position: absolute; max-width: 65px; top: 286px; left: 610px; text-align: center;" class="flot-tick-label tickLabel">18</div><div style="position: absolute; max-width: 65px; top: 286px; left: 680px; text-align: center;" class="flot-tick-label tickLabel">20</div><div style="position: absolute; max-width: 65px; top: 286px; left: 751px; text-align: center;" class="flot-tick-label tickLabel">22</div><div style="position: absolute; max-width: 65px; top: 286px; left: 821px; text-align: center;" class="flot-tick-label tickLabel">24</div><div style="position: absolute; max-width: 65px; top: 286px; left: 892px; text-align: center;" class="flot-tick-label tickLabel">26</div><div style="position: absolute; max-width: 65px; top: 286px; left: 962px; text-align: center;" class="flot-tick-label tickLabel">28</div><div style="position: absolute; max-width: 65px; top: 286px; left: 1033px; text-align: center;" class="flot-tick-label tickLabel">30</div></div><div class="flot-y-axis flot-y1-axis yAxis y1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div style="position: absolute; top: 274px; left: 5px; text-align: right;" class="flot-tick-label tickLabel">0</div><div style="position: absolute; top: 256px; left: 5px; text-align: right;" class="flot-tick-label tickLabel">5</div><div style="position: absolute; top: 238px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">10</div><div style="position: absolute; top: 219px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">15</div><div style="position: absolute; top: 201px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">20</div><div style="position: absolute; top: 183px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">25</div><div style="position: absolute; top: 165px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">30</div><div style="position: absolute; top: 147px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">35</div><div style="position: absolute; top: 128px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">40</div><div style="position: absolute; top: 110px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">45</div><div style="position: absolute; top: 92px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">50</div><div style="position: absolute; top: 74px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">55</div><div style="position: absolute; top: 56px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">60</div><div style="position: absolute; top: 37px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">65</div><div style="position: absolute; top: 19px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">70</div><div style="position: absolute; top: 1px; left: 0px; text-align: right;" class="flot-tick-label tickLabel">75</div></div></div><canvas class="flot-overlay" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1046px; height: 300px;" width="1046" height="300"></canvas><div class="legend"><div style="position: absolute; width: 79px; height: 33px; top: 13px; right: 13px; background-color: rgb(255, 255, 255); opacity: 0.85;"> </div><table style="position:absolute;top:13px;right:13px;;font-size:smaller;color:#545454"><tbody><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(219,94,140);overflow:hidden"></div></div></td><td class="legendLabel">Unique Visits</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(240,173,78);overflow:hidden"></div></div></td><td class="legendLabel">Page Views</td></tr></tbody></table></div></div>
							</div>
						</div>
						/BOX
					</div>
				</div> -->
				<!--上网走势图 end-->

			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript">
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
	var v=year+"-"+month+"-"+day;
	
	function changeTwoDecimal(floatvar)
	{
	var f_x = parseFloat(floatvar);
	if (isNaN(f_x))
	{
	//alert('function:changeTwoDecimal->parameter error');
	return false;
	}
	var f_x = Math.round(floatvar*100)/100;
	return f_x;
	}
	function getData(day){
		$.ajax({
			url:"${basePath}summary/getBaseIndex",
			method:"post",
			data:{day:day},
			success:function(data){
				if(data.code==1){
					if(data.result==null){
						$("#radiusUsers").html(0);
						$("#onlineUsers").html(0);
						$("#pushUserMsg").html(0+"/"+0);
						$("#showUserMsg").html(0+"/"+0);
						$("#clickUserMsg").html(0+"/"+0);
						$("#receiveRatioMsg").html(0);
						$("#showRatioMsg").html(0);
						$("#clickRatioMsg").html(0);
					}
					else{
						$("#radiusUsers").html(data.result.radiusUsers);
						$("#onlineUsers").html(data.result.onlineUsers);
						$("#pushUserMsg").html(data.result.pushUsers+"/"+data.result.pushNum);
						$("#showUserMsg").html(data.result.showUsers+"/"+data.result.showNum);
						$("#clickUserMsg").html(data.result.clickUsers+"/"+data.result.clickNum);
						$("#receiveRatioMsg").html(changeTwoDecimal(data.result.receiveRatio*100)+'%');
						$("#showRatioMsg").html(changeTwoDecimal(data.result.showRatio*100)+'%');
						$("#clickRatioMsg").html(changeTwoDecimal(data.result.clickRatio*100)+'%');
					}
					
				}
			}
		});
		$.ajax({
			url:"${basePath}summary/fundReport",
			method:"post",
			data:{day:day},
			success:function(data){
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
				if(data.code==1){
					var policy=data.result.pushLists;
					for(var i=0;i<policy.length;i++){
						 time[i]=policy[i].Report_Period;
						 type[i]=policy[i].policyName;
						 pushUser[i]=policy[i].pushUsers;
						 showUser[i]=policy[i].showUsers;
						 clickUser[i]=policy[i].clickUsers;
					}
					
					//将重复的策略名去掉
					   for(var i=0;i<type.length;i++) {
					   	var isRepeated = false;
					   	if(type[i] == type[i+1]){
					   		isRepeated = true;
					   		continue;
					   	}
					   	if(!isRepeated){
					   		types[m] = type[i];
					   		m++;
					   	}
					   }
					
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
			}
		});
}
	$(function(){
		getData(v);
	});
	function dateSelect(){
		var bir=$("#birthday").val();
		getData(bir);
	};
	
	
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
	function loadReportType(_id){
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
</script>