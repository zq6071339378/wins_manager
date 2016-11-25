<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
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
        	if(birthday==null){
			$('#birthday').val(fullYear);
        	}
           $('#birthday').daterangepicker(
        	{
        		singleDatePicker: true,
        		startDate:birthday,
        	},
        	function (){
        		var ReportDate=$('#birthday').val();
        		var PolicyId=$('#PolicyId_parm_id').val();
        		loadSinglePolicyDate(ReportDate,PolicyId);
        	}
        	);

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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("POLICREPORT_SYSTEMMANAGEMENT")%></a></li>
								<li><i class="fa fa-home"></i> <%=Config.message.get("POLICREPORT_MONITIRINGMANAGEMENT")%><a href="#"> </a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("POLICREPORT_POLICYTABLE")%></h3>
							</div>
							<div class="row">
								<div class="description col-md-7"><%=Config.message.get("POLICREPORT_POLICYREPORTATTENTION")%></div>
								<div style="padding-left: 0px; bottom: 8px;"
									class="pull-right col-md-1">
									<a href="#" style="text-decoration: none"
										onclick="fanhui('${ReportDate}','${PolicyName}','${temp}')">
										<i class="fa fa-reply-all" style="font-size: 18px;"></i>&nbsp;&nbsp;<span
										style="font-weight: bold;"><%=Config.message.get("POLICYRCPORT_BACK")%></span>
									</a>
								</div>
								<div class="pull-right col-md-3">
									<div id="datetimepicker5" class="input-group date global_date">
										<input type="text" value="${ReportDate }" class="form-control"
											id="birthday" readonly="readonly"> <span
											class="input-group-addon"><span class="fa fa-calendar"></span>
										</span>
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
									<!-- <div class="box-body col-md-2" style="overflow:scroll;height: 600px;">
														<form class="navbar-search tree_search" action="http://wenda.bootcss.com/search/" id="global_search_form" method="post">
															<input type="text" class="form-control search-query" placeholder="输入策略名称.." autocomplete="off" name="q" id="aw-search-query">
															<span title="搜索" id="global_search_btns"><i class="fa fa-search"></i></span>
															<div style="display: none;" class="aw-dropdown">
																<div class="mod-body">
																	<p class="title">输入关键字进行搜索</p>
																	<ul class="aw-dropdown-list hide"></ul>
																	<p class="search"><span>搜索:</span><a onclick="$('#global_search_form').submit();"></a></p>
																</div>
																<div class="mod-footer">
																	<a href="" onclick="$('#header_publish').click();" class="pull-right btn btn-mini btn-success publish">发起问题</a>
																</div>
															</div>
														</form>
														<div class="box-body"> 
														   <div id="tree2" class="tree tree-unselectable">
															   <div style="display:none;" class="tree-folder">				
																   <div class="tree-folder-header">					
																   <i class="fa-folder fa"></i>					
																	   <div class="tree-folder-name">
																	   </div>				
																   </div>				
																   <div class="tree-folder-content">
																   </div>				
																   <div style="display: none;" class="tree-loader">
																   </div>			
															   </div>			
															   <div style="display:none;" class="tree-item">								
															  		<div class="tree-item-name">
															  		</div>			
															   </div>
															   <div style="display: block;" class="tree-folder">				
																   <div class="tree-folder-header">					
																   		<i class="fa-folder red fa"></i>					
																   		<div class="tree-folder-name">
																   		我的活动
																   		</div>				
																   </div>				
																   <div class="tree-folder-content">
																   </div>				
																   <div style="display: none;" class="tree-loader">
																	   <div class="tree-loading">
																	   <i class="fa fa-spinner fa-2x fa-spin"></i>
																	   </div>
																   </div>			
															   </div>
															   </div> 
															   <div id="policy-name-list" class="tree-folder-name">
										 						</div>
														</div>
													</div> -->
									<div class="col-md-12">
										<div class="box border blue">
											<div class="box-title">
												<h4><%=Config.message.get("POLICREPORT_DETAILDATA")%></h4>
												<div class="tools">
													<a class="config" data-toggle="modal" href="#box-config"
														onclick="importTableData()">
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
				<input type="hidden" id="ReportDate_parm_id" value="${ReportDate }" />
				<input type="hidden" id="PolicyId_parm_id" value="${PolicyId }" />
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
function changeTwoDecimal(floatvar)
{
var f_x = parseFloat(floatvar);
if (isNaN(f_x))
{
alert('function:changeTwoDecimal->parameter error');
return false;
}
var f_x = Math.round(floatvar*100)/100;
return f_x;
}
</script>

<script type="text/javascript">
 $(function () {
	 //var ReportId=$('#ReportId_parm_id').val();
	 var ReportDate=$('#ReportDate_parm_id').val();
	 var PolicyId=$('#PolicyId_parm_id').val();
	 loadSinglePolicyDate(ReportDate,PolicyId);
}); 
//加载详细数据
function addMe(ReportDate,PolicyId){
	   var _html='';
	   $.ajax({
			url:'policyinfo/nameList.json',
			type:'post',
			data:'time='+ReportDate+'&PolicyId='+PolicyId,
			success:function(data){			
				var results=data.result.LIST;
				for(var i=0;i<results.length;i++){
				_html='<tr><td>'+results[i].ReportDate+'</td><td>'+results[i].TargetUsers+'</td><td>'+'--'+'</td><td>'+results[i].PushUsers+'/'+results[i].PushNum+'</td><td>'+results[i].ReceiveUsers+'/'+results[i].ReceiveNum+'</td><td>'+results[i].ShowUsers+'/'+results[i].ShowNum+'</td><td>'+results[i].ClickUsers+'/'+results[i].ClickNum+'</td><td>'+results[i].CloseUsers+'/'+results[i].CloseNum+'</td><td>'+changeTwoDecimal(results[i].ReceiveRatio*100)+'%</td><td>'+changeTwoDecimal(results[i].ShowRatio*100)+'%</td><td>'+changeTwoDecimal(results[i].ClickRatio*100)+'%</td><td>'+changeTwoDecimal(results[i].CloseRatio*100)+'%</td> </tr>';	
				}
				$("#policy_single_id_html").html(_html);
			}
	   });
	   
}

//加载对应策略推送记录报表
 function loadSinglePolicyDate(ReportDate,PolicyId){
	   //$("#ReportId_parm_id").val(ReportId);
	   $("#ReportDate_parm_id").val(ReportDate);
	   $("#PolicyId_parm_id").val(PolicyId);
	   var TargetUsersArray=[],PushUsersArray=[],ReceiveUsersArray=[],ShowUsersArray=[],ClickUsersArray=[],ReportPeriodArray=[];
	   $.ajax({
		   url: "policyinfo/singlePolicyShow.json",
		   type: "post",
		   data: "ReportDate="+ReportDate+"&PolicyId="+PolicyId,
		   success: function(data){
			     addMe(ReportDate,PolicyId);
  			 $.each(data.result.LIST,function(i,n){
  				   TargetUsersArray.push(n.TargetUsers);
				   PushUsersArray.push(n.PushUsers);
				   ReceiveUsersArray.push(n.ReceiveUsers);
				   ShowUsersArray.push(n.ShowUsers);
				   ClickUsersArray.push(n.ClickUsers);
				   ReportPeriodArray.push(n.ReportPeriod);
			   }); 
  			completionHours(TargetUsersArray,PushUsersArray,ReceiveUsersArray,ShowUsersArray,ClickUsersArray,ReportPeriodArray);
			   
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
	   var time=$("#ReportDate_parm_id").val();
	   var PolicyId=$("#PolicyId_parm_id").val();
	   window.location.href="${basepath}policyinfo/importTable.json?PolicyId="+PolicyId+"&time="+time;
   }
</script>


<script type="text/javascript">
function fanhui(time,PolicyName,temp){
	if(temp==0){
	load_page('${basePath}policyinfo/showReport?PolicyName='+encodeURIComponent(PolicyName)+'&create_time='+time);
	}
	if(temp==1){
	load_page('${basePath}policy/policyList');	
	}
}
</script>