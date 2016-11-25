<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.datacomo.wins.controller.Config"%>
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
				/* if (start < dateN) {
				 startTime = _nowDate;
				 }*/
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
				var policyId=$("#PolicyId_parm_id").val();
				loadSinglePolicyDate(startTime,endTime,policyId);
			});
       });
    </script>
<script src="${basePath}js/jquery.form.js"></script>
<!-- /SIDEBAR -->
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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("REPORTMANAGE_OPERATIONMANAGEMENT")%></a></li>
								<li><i class="fa fa-home"></i> <%=Config.message.get("REPORTMANAGE_TABLEMANAGEMENT")%><a href="#"> </a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("POLICREPORT_POLICYTABLE")%></h3>
							</div>
							<div class="row">
								<div class="description col-md-7"><%=Config.message.get("POLICREPORT_POLICYREPORTATTENTION")%></div>
								<div style="padding-left: 0px; padding-top: 5px;"
									class="pull-right col-md-1">
									<a href="#" style="text-decoration: none" onclick="fanhui('${result.startTime}','${result.endTime}')">
										<i class="fa fa-reply-all" style="font-size: 18px;"></i>&nbsp;&nbsp;
										<span style="font-weight: bold;"><%=Config.message.get("POLICYRCPORT_BACK")%></span>
									</a>
								</div>
								<div class="col-md-3">
									<div class="controls">
										<div class="input-group date" id="reservation">
											<input type="text" placeholder="Start time - End time" required="required" id="doubleDate_id" class="form-control" style="padding-left: 30px !important;" value="${result.startTime}-${result.endTime}">
											<span class="input-group-addon">
												<span class="fa fa-calendar"></span>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /PAGE HEADER -->
					<div class="row">
						<div class="col-md-12">
							<!-- BOX -->
							<div class="box">
								<div class="">
									<div class="col-md-12">
										<div class="box border blue">
											<div class="box-title">
												<h4><%=Config.message.get("POLICREPORT_DETAILDATA")%></h4>
												<div class="tools">
													<a class="config" data-toggle="modal" href="#box-config" onclick="importTableData()">
														<h4><%=Config.message.get("POLICREPORT_EXPORT")%></h4>
													</a>
												</div>
											</div>
											<!-- <div class="box-title report_title">
													<h5>详细数据</h5>
													<div class="tools">
														<a href="#box-config" data-toggle="modal" class="config" onclick="importTableData()">
															<h4>导出</h4>
														</a>
													</div>
												</div> -->
											<div class="box_report">
												<table class="table table-striped table-striped1">
													<thead>
														<tr>
															<th><%=Config.message.get("POLICREPORT_PUSHTIME")%></th>
															<th><%=Config.message.get("POLICREPORT_THETARGETUSER")%></th>
															<th><%=Config.message.get("POLICREPORT_THETARGETOFINTERNETUSERS")%></th>
															<th><%=Config.message.get("POLICREPORT_PUSHUSERS/NUMBER")%></th>
															<th><%=Config.message.get("POLICREPORT_TOREACHUSERS/NUMBER")%></th>
															<th><%=Config.message.get("POLICREPORT_SHOWUSERS/TIMES")%></th>
															<th><%=Config.message.get("POLICREPORT_CLICKUSERS/TIMES")%></th>
															<th><%=Config.message.get("POLICREPORT_CLOSEUSERS/TIMES")%></th>
															<th><%=Config.message.get("POLICREPORT_ARRIVALRATE")%></th>
															<th><%=Config.message.get("POLICREPORT_SHOWRATE")%></th>
															<th><%=Config.message.get("POLICREPORT_CLICKRATE")%></th>
															<th><%=Config.message.get("POLICREPORT_CLOSERATE")%></th>
														</tr>
													</thead>
													<tbody id="policy_single_id_html">
														<!-- <tr>
																			<td>2016.10.1</td>
																			<td>10000</td>
																			<td>90000</td>
																			<td>8000/8200</td>
																			<td>6000/6500</td>
																			<td>4000/4100</td>
																			<td>2000/2100</td>
																			<td>1000/1100</td>
																			<td>75%</td>
																			<td>67%</td>
																			<td>50%</td>
																			<td>25%</td>
																		  </tr> -->
													</tbody>
												</table>
											</div>
										</div>
										<div class="box border blue">
											<div class="box-title">
												<h4><%=Config.message.get("POLICY_PUSH_TREND_CHART")%></h4>
												<div class="tools">
													<!-- <a href="javascript:;" class="reload">
																			<i class="fa fa-refresh"></i>
																		</a>
																		<a href="javascript:;" class="collapse">
																			<i class="fa fa-chevron-up"></i>
																		</a> -->
												</div>
											</div>
											<div class="box-body">
												<div style="padding: 0px; position: relative;" id="chart"
													class="chart">
													<canvas height="268" width="1077"
														style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1203px; height: 300px;"
														class="flot-base">
																		</canvas>
													<div class="flot-text"
														style="position: absolute; top: 0px; left: -2px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);">
														<div class="flot-x-axis flot-x1-axis xAxis x1Axis"
															style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 57px; text-align: center;"
																class="flot-tick-label tickLabel">2</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 138px; text-align: center;"
																class="flot-tick-label tickLabel">4</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 219px; text-align: center;"
																class="flot-tick-label tickLabel">6</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 300px; text-align: center;"
																class="flot-tick-label tickLabel">8</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 377px; text-align: center;"
																class="flot-tick-label tickLabel">10</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 458px; text-align: center;"
																class="flot-tick-label tickLabel">12</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 539px; text-align: center;"
																class="flot-tick-label tickLabel">14</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 620px; text-align: center;"
																class="flot-tick-label tickLabel">16</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 701px; text-align: center;"
																class="flot-tick-label tickLabel">18</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 782px; text-align: center;"
																class="flot-tick-label tickLabel">20</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 863px; text-align: center;"
																class="flot-tick-label tickLabel">22</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 944px; text-align: center;"
																class="flot-tick-label tickLabel">24</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 1025px; text-align: center;"
																class="flot-tick-label tickLabel">26</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 1106px; text-align: center;"
																class="flot-tick-label tickLabel">28</div>
															<div
																style="position: absolute; max-width: 113px; top: 281px; left: 1187px; text-align: center;"
																class="flot-tick-label tickLabel">30</div>
														</div>
														<div class="flot-y-axis flot-y1-axis yAxis y1Axis"
															style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;">
															<div
																style="position: absolute; top: 267px; left: 8px; text-align: right;"
																class="flot-tick-label tickLabel">0</div>
															<div
																style="position: absolute; top: 249px; left: 8px; text-align: right;"
																class="flot-tick-label tickLabel">5</div>
															<div
																style="position: absolute; top: 231px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">10</div>
															<div
																style="position: absolute; top: 213px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">15</div>
															<div
																style="position: absolute; top: 196px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">20</div>
															<div
																style="position: absolute; top: 178px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">25</div>
															<div
																style="position: absolute; top: 160px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">30</div>
															<div
																style="position: absolute; top: 142px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">35</div>
															<div
																style="position: absolute; top: 125px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">40</div>
															<div
																style="position: absolute; top: 107px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">45</div>
															<div
																style="position: absolute; top: 89px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">50</div>
															<div
																style="position: absolute; top: 71px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">55</div>
															<div
																style="position: absolute; top: 54px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">60</div>
															<div
																style="position: absolute; top: 36px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">65</div>
															<div
																style="position: absolute; top: 18px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">70</div>
															<div
																style="position: absolute; top: 1px; left: 0px; text-align: right;"
																class="flot-tick-label tickLabel">75</div>
														</div>
													</div>
													<canvas height="268" width="1077"
														style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 1203px; height: 300px;"
														class="flot-overlay">
																		</canvas>
													<div class="legend">
														<div
															style="position: absolute; width: 56px; height: 41px; top: 15px; right: 13px; background-color: rgb(255, 255, 255); opacity: 0.85;">
														</div>
														<table
															style="position: absolute; top: 15px; right: 13px;; font-size: smaller; color: #545454">
															<tbody>
																<tr>
																	<td class="legendColorBox"><div
																			style="border: 1px solid #ccc; padding: 1px">
																			<div
																				style="width: 4px; height: 0; border: 5px solid rgb(219, 94, 140); overflow: hidden"></div>
																		</div></td>
																	<td class="legendLabel"><%=Config.message.get("INDEX_EXPOSURE")%></td>
																</tr>
																<tr>
																	<td class="legendColorBox">
																		<div style="border: 1px solid #ccc; padding: 1px">
																			<div
																				style="width: 4px; height: 0; border: 5px solid rgb(240, 173, 78); overflow: hidden">
																			</div>
																		</div>
																	</td>
																	<td class="legendLabel"><%=Config.message.get("Cost")%></td>
																</tr>
															</tbody>
														</table>
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
				</div>
				<!-- /CONTENT-->
				<%-- <input type="hidden" id="ReportId_parm_id" value="${ReportId }" /> --%>
				<input type="hidden" id="start_Time" value="${result.startTime}">
				<input type="hidden" id="end_Time" value="${result.endTime}">
				<input type="hidden" id="PolicyId_parm_id" value="${result.policyId}"/>
			</div>
		</div>
	</div>
</div>
<!-- <script>
var _activityId=$("#activityId_parm_id").val();
var _dateType=1;
	jQuery(document).ready(function() {
		//App.setPage("mini_sidebar");  //Set current page
		//App.init(); //Initialise plugins and elements
		//$("#sidebar").addClass('sidebar_report');
	});
</script> -->
<!-- TREE -->
<script type="text/javascript" src="js/bootstrap-treeview.js"></script>

<!-- 只显示小数点后两位 -->
<script type="text/javascript">
	function changeTwoDecimal(floatvar) {
		var f_x = parseFloat(floatvar);
		if (isNaN(f_x)){
			alert('function:changeTwoDecimal->parameter error');
			return false;
		}
		var f_x = Math.round(floatvar*100)/100;
		return f_x;
	}
</script>

<script type="text/javascript">
 $(function () {
	 var startTime=$('#start_Time').val();
	 var endTime=$("#end_Time").val();
	 var policyId=$('#PolicyId_parm_id').val();
	 loadSinglePolicyDate(startTime,endTime,policyId);
}); 
//加载详细数据
function addMe(startTime,endTime,policyId){
	   var _html='';
	   $.ajax({
			url:'policyinfo/policyTotalData.json',
			type:'post',
			data:'startTime='+startTime+'&endTime='+endTime+'&policyId='+policyId,
			success:function(data){
				console.log(data);
				var results=data.result.LIST;
				for(var i=0;i<results.length;i++){
				_html='<tr><td>'+(results[i].startTime).substring(0,10) +'-'+(results[i].endTime).substring(0,10)+'</td><td>'+results[i].TargetUsers+'</td><td>'+'--'+'</td><td>'+results[i].PushUsers+'/'+results[i].PushNum+'</td><td>'+results[i].ReceiveUsers+'/'+results[i].ReceiveNum+'</td><td>'+results[i].ShowUsers+'/'+results[i].ShowNum+'</td><td>'+results[i].ClickUsers+'/'+results[i].ClickNum+'</td><td>'+results[i].CloseUsers+'/'+results[i].CloseNum+'</td><td>'+changeTwoDecimal(results[i].ReceiveRatio*100)+'%</td><td>'+changeTwoDecimal(results[i].ShowRatio*100)+'%</td><td>'+changeTwoDecimal(results[i].ClickRatio*100)+'%</td><td>'+changeTwoDecimal(results[i].CloseRatio*100)+'%</td> </tr>';
				}
				$("#policy_single_id_html").html(_html);
			}
	   });
}

//加载对应策略推送记录报表
 function loadSinglePolicyDate(startTime,endTime,policyId){
	   //$("#ReportId_parm_id").val(ReportId);
	   var TargetUsersArray=[],PushUsersArray=[],ReceiveUsersArray=[],ShowUsersArray=[],ClickUsersArray=[],ReportPeriodArray=[];
	   $.ajax({
		   url: "policyinfo/policyEveryData.json",
		   type: "post",
		   data: "startTime="+startTime+"&endTime="+endTime+"&policyId="+policyId,
		   success: function(data){
			   console.log(data);
			   addMe(startTime,endTime,policyId);
			   $.each(data.result.LIST,function(i,n){
				   TargetUsersArray.push(n.TargetUsers);
				   PushUsersArray.push(n.PushUsers);
				   ReceiveUsersArray.push(n.ReceiveUsers);
				   ShowUsersArray.push(n.ShowUsers);
				   ClickUsersArray.push(n.ClickUsers);
				   ReportPeriodArray.push(n.ReportPeriod);
			   });
			   if(startTime==endTime){//一天的数据
				   completionHours(TargetUsersArray,PushUsersArray,ReceiveUsersArray,ShowUsersArray,ClickUsersArray,ReportPeriodArray);
			   }else{//多天的数据
				   var maxDay=parseInt(data.result.maxDay);
				   completionDays(TargetUsersArray,PushUsersArray,ReceiveUsersArray,ShowUsersArray,ClickUsersArray,ReportPeriodArray,maxDay);
			   }

		   }
		});
}

//补全24小时
function completionHours(TargetUsersArray,PushUsersArray,ReceiveUsersArray,ShowUsersArray,ClickUsersArray,ReportPeriodArray){
	var TargetUsersArrays=[];
	var PushUsersArrays=[];
	var ReceiveUsersArrays=[];
	var ShowUsersArrays=[];
	var ClickUsersArrays=[];
	var ReportPeriodArrays=[];
	var n=0;
	var k=0;
	var j=0;
	

		while(n < 24){
			while(n + 1 != ReportPeriodArray[k] && n < 24){
				ReportPeriodArrays[j] = n + 1;
				TargetUsersArrays [j] = 0;
				PushUsersArrays [j] = 0;
				ReceiveUsersArrays [j] = 0;
				ShowUsersArrays [j] = 0;
				ClickUsersArrays [j] = 0;
				j++;
				n++;
			}
			if(n < 24){
				ReportPeriodArrays[j]=ReportPeriodArray[k];
				TargetUsersArrays[j]=TargetUsersArray[k];
				PushUsersArrays[j]=PushUsersArray[k];
				ReceiveUsersArrays[j]=ReceiveUsersArray[k];
				ShowUsersArrays[j]=ShowUsersArray[k];
				ClickUsersArrays[j]=ClickUsersArray[k];
				j++;
				k++;
				n++;
			}
		}
		if(n == 24){
			n = 0;
		}
	showSinglePolicy(TargetUsersArrays,PushUsersArrays,ReceiveUsersArrays,ShowUsersArrays,ClickUsersArrays,ReportPeriodArrays);
}
 //补全天数
 function completionDays(TargetUsersArray,PushUsersArray,ReceiveUsersArray,ShowUsersArray,ClickUsersArray,ReportPeriodArray,maxDay){
	 var TargetUsersArrays=[];
	 var PushUsersArrays=[];
	 var ReceiveUsersArrays=[];
	 var ShowUsersArrays=[];
	 var ClickUsersArrays=[];
	 var ReportPeriodArrays=[];
	 var n=0;
	 var k=0;
	 var j=0;


	 while(n <maxDay){
		 while(n + 1 != ReportPeriodArray[k] && n < maxDay){
			 ReportPeriodArrays[j] = n + 1;
			 TargetUsersArrays [j] = 0;
			 PushUsersArrays [j] = 0;
			 ReceiveUsersArrays [j] = 0;
			 ShowUsersArrays [j] = 0;
			 ClickUsersArrays [j] = 0;
			 j++;
			 n++;
		 }
		 if(n < maxDay){
			 ReportPeriodArrays[j]=ReportPeriodArray[k];
			 TargetUsersArrays[j]=TargetUsersArray[k];
			 PushUsersArrays[j]=PushUsersArray[k];
			 ReceiveUsersArrays[j]=ReceiveUsersArray[k];
			 ShowUsersArrays[j]=ShowUsersArray[k];
			 ClickUsersArrays[j]=ClickUsersArray[k];
			 j++;
			 k++;
			 n++;
		 }
	 }
	 if(n == maxDay){
		 n = 0;
	 }
	 showSinglePolicy(TargetUsersArrays,PushUsersArrays,ReceiveUsersArrays,ShowUsersArrays,ClickUsersArrays,ReportPeriodArrays);
 }

//加载报表
 function showSinglePolicy(TargetUsersArrays,PushUsersArrays,ReceiveUsersArrays,ShowUsersArrays,ClickUsersArrays,ReportPeriodArrays){
	   $('#chart').highcharts({
		    chart:{
		 		type:'spline'
		 	},
	        title: {
	            text: ''
	        },
	        credits: {
	            enabled: false//去除右下插件标志
	        },
	        xAxis: {
	            categories: ReportPeriodArrays
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
	        series: [{
	            name: '<%=Config.message.get("POLICY_TRARGET_USER")%>',
	            data: TargetUsersArrays
	        }, {
	            name: '<%=Config.message.get("POLICY_PUSH_USER")%>',
	            data: PushUsersArrays
	        }, {
	            name: '<%=Config.message.get("POLICY_ARRIVAL_USER")%>',
	            data: ReceiveUsersArrays
	        }, {
	            name: '<%=Config.message.get("POLICY_SHOW_USER")%>',
	            data: ShowUsersArrays
	        }, {
	            name: '<%=Config.message.get("POLICY_CLICK_USER")%>',
	            data: ClickUsersArrays
	        }]
	    });
 }

 
 //导出详细数据
   function importTableData(){
	   var policyId=$("#PolicyId_parm_id").val();
	   var startTime=$("#start_Time").val();
	   var endTime=$("#end_Time").val();
	   window.location.href="${basepath}policyinfo/reportTotalData.json?policyId="+policyId+"&startTime="+startTime+"&endTime="+endTime;
   }
</script>

<script type="text/javascript">
	function fanhui(startTime,endTime){
		load_page('${basePath}policyinfo/showReport?startTime='+startTime+'&endTime='+endTime);
	}
</script>