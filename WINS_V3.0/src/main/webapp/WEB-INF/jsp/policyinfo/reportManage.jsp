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
				   load_page('policyinfo/showReport?startTime='+startTime+'&endTime='+endTime);
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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("REPORTMANAGE_OPERATIONMANAGEMENT")%></a></li>
								<li><a href="#"><%=Config.message.get("REPORTMANAGE_TABLEMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("REPORTMANAGE_TABLEMANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("REPORTMANAGE_REPORTATTENTION")%></div>
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
									<input type="text" placeholder="Start time - End time" required="required" id="doubleDate_id" class="form-control" style="padding-left: 30px !important;" value="${result.startTime}-${result.endTime}">
									<span class="input-group-addon">
										<span class="fa fa-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-5 form_input login-box row_left">
						<c:if test="${isEnglish eq true }">
							<div class="form-group col-md-4 row_left">
								<select class="form-control" id="selectCondition">
									<option value="0"><%=Config.message.get("ACTIVITY_POLICYNAME")%></option>
									<option value="1"><%=Config.message.get("ACTIVITY_NAME")%></option>
									<option value="2"><%=Config.message.get("ACTIVITY_CLIENTNAME")%></option>
								</select>
							</div>
						</c:if>
							<div class="col-md-8 form_input login-box row_left">
                                <form>
									<div class="form-group col-md-10 row_left search_icon">
										<a href="javascript:;" onclick="find()">
											<i class="fa fa-search"></i>
										</a>
										<input type="search" id="exampleInputSearch" class="form-control"  style=" width:78%" placeholder="<%=Config.message.get("REPORTMANAGE_PLEASEINPUTPOLICYNAME")%>">
									</div>
								</form>
							</div>
						</div>
						<c:if test="${isEnglish eq true }">
							<div class="col-md-1 col-md-offset-1" style="left: 15px;">
								<div class="input-append">
									<a class="btn btn-primary" onclick="reportDataCompare();"><%=Config.message.get("ACTIVITY_DATA")%>&nbsp;</a>
								</div>
							</div>
						</c:if>

						<div class="col-md-1 pull-right" style="left: 15px;">
							<div class="input-append">
								<a class="btn btn-primary" onclick="reportExcel();"><%=Config.message.get("POLICREPORT_EXPORT")%>&nbsp;</a>
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
								<thead>
									<tr>
										<c:if test="${isEnglish eq true }">
											<th><%=Config.message.get("ACTIVITY_CHOOSE")%></th>
										</c:if>
										<th><%=Config.message.get("REPORTMANAGE_SORT")%></th>
										<th><%=Config.message.get("REPORTMANAGE_POLICYNAME")%></th>
										<c:if test="${isEnglish eq true }">
											<th><%=Config.message.get("CREATEPOLICY_AFFILIATEDACTIVITIES")%></th>
											<th><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></th>
										</c:if>
										<th><%=Config.message.get("REPORTMANAGE_AREANAME")%></th>
										<th><%=Config.message.get("REPORTMANAGE_PUSHTIME")%></th>
										<th><%=Config.message.get("REPORTMANAGE_TARGETUSERS")%></th>
										<th><%=Config.message.get("REPORTMANAGE_SHOWUSERS")%></th>
										<th><%=Config.message.get("REPORTMANAGE_CLICKUSERS")%></th>
										<th><%=Config.message.get("REPORTMANAGE_CLOSEUSERS")%></th>
										<th><%=Config.message.get("REPORTMANAGE_SUMOFCOMSUMPTION")%></th>
										<th><%=Config.message.get("REPORTMANAGE_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result.LIST}">
										<c:forEach items="${result.LIST}" var="list" varStatus="v">
											<tr>
												<c:if test="${isEnglish eq true }">
													<td><input type="checkbox" onclick="selectThis(this);" value="${list.PolicyId}"></td>
												</c:if>
												<td>${v.count}</td>
												<td>${list.PolicyName}</td>
												<c:if test="${isEnglish eq true }">
													<td>${list.activityName}</td>
													<td>${list.customerName}</td>
												</c:if>
												<td>${list.ProvinceCity}</td>
												<td>${list.startTime}-${list.endTime}</td>
												<td>${list.TargetUsers}</td>
												<td><a href="javascript:;" onclick="ShowUsers('${list.PolicyId}','${result.startTime}','${result.endTime}','1')">${list.ShowUsers}</a>
												</td>
												<td><a href="javascript:;" onclick="ClickUsers('${list.PolicyId}','${result.startTime}','${result.endTime}','1')">${list.ClickUsers}</a>
												</td>
												<td><a href="javascript:;" onclick="CloseUsers('${list.PolicyId}','${result.startTime}','${result.endTime}','1')">${list.CloseUsers}</a>
												</td>
												<td id="unitPrice">
												<c:if test="${list.buyType eq 1}">
													${list.unitPrice*1000*list.showLimit}
												</c:if>
												<c:if test="${list.buyType eq 2}">
													${list.unitPrice*list.hitLimit}
												</c:if>
												</td>
												<td><a class="btn btn-default" href="javascript:;" title="Check" onclick="showReport('${list.PolicyId}','${result.startTime}','${result.endTime}','1')">
													<i class="fa fa-eye"></i></a>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<input type="hidden" id="policyIds" value="${result.policyIds}">
							<input type="hidden" id="selectIds" value="${result.selectIds}">
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="policyinfo/showReport?selectValue=${result.selectValue}&selectName=encodeURIComponent(${result.selectName})&startTime=${result.startTime}&endTime=${result.endTime}" name="actionName" />
									</jsp:include>
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
	var select_data=[];
	function selectThis(obj){
		if($(obj).is(':checked')){
			var id=$(obj).val();
			select_data.push(id);
		}else{
			var id=$(obj).val();
			for (var i=0;i<select_data.length;i++){
				if(id==select_data[i]){
					if(i==0){
						select_data=[];
					}else{
						select_data.splice(i,i);
					}
				}
			}
		}
	}

	function reportDataCompare(){
		if(select_data.length<1){
			alert('<%=Config.message.get("PLEASE")%>');
			return;
		}
		var policyIds = "";
		for (var i=0;i<select_data.length;i++){
			if(i!=(select_data.length-1)){
				policyIds+=select_data[i]+",";
			}else{
				policyIds+=select_data[i];
			}
		}
		var startTime=$("#start_Time").val();
		var endTime=$("#end_Time").val();
		load_page('${basePath}policyinfo/dataCompare?policyIds='+policyIds+'&startTime='+startTime+'&endTime='+endTime);
	}

   var sumprice=$("#unitPrice").html();
   var n = parseFloat(sumprice).toFixed(2);
   var re = /(\d{1,3})(?=(\d{3})+(?:\.))/g;
   var ma= n.replace(re, "$1,");
   $("#unitPrice").html(ma);
/* 	$.ajax({
		url:'policy/addPolicy',
		type:"post",
		data:{sumprice:sumprice},
		success:function(data){
		}
	})  ; */

	function reportExcel(){
		var policyIds=$("#policyIds").val();
		if(policyIds==""){
			alert("<%=Config.message.get("ACTIVITY_THERE_IS_NO_DATA_TO_EXPORT")%>");
			return;
		}
		var startTime = $("#start_Time").val();
		var endTime = $("#end_Time").val();
		window.location.href='${basePath}policyinfo/reportExcel?policyIds='+policyIds+'&startTime='+startTime+'&endTime='+endTime;
	}
    /*function changeTips(obj){
	    var val=$(obj).val();
	    switch (val){
		    case 0:$("#exampleInputSearch").attr("placeholder",<%=Config.message.get("REPORTMANAGE_PLEASEINPUTPOLICYNAME")%>);break;
		    case 1:$("#exampleInputSearch").attr("placeholder",<%=Config.message.get("REPORT_PLEASE_INPUT_ACTIVITY")%>);break;
		    case 2:$("#exampleInputSearch").attr("placeholder",<%=Config.message.get("REPORT_PLEASE_INPUT_CUSTOMER")%>);break;
	    }
    }*/

	function find(){
		var selectValue = $("#selectCondition option:selected").val();
		if(typeof(selectValue)=="undefined"){
			selectValue=0;
		}
		var selectName = $("#exampleInputSearch").val().trim();
		var startTime = $("#start_Time").val();
		var endTime = $("#end_Time").val();
		load_page('${basePath}policyinfo/showReport?selectValue='+selectValue+'&selectName='+decodeURIComponent(selectName)+'&startTime='+startTime+'&endTime='+endTime);
	}

	function ShowUsers(policyId,startTime,endTime,backType){
		load_page('${basePath}policyinfo/showDetails?policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&backType='+backType);
	}
	function ClickUsers(policyId,startTime,endTime,backType){
		load_page('${basePath}policyinfo/clickDetails?policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&backType='+backType);
	}
	function CloseUsers(policyId,startTime,endTime,backType){
		load_page('${basePath}policyinfo/closeDetails?policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&backType='+backType);
	}
	function showReport(policyId,startTime,endTime,backType){
		load_page('${basePath}policyinfo/toView?policyId='+policyId+'&startTime='+startTime+'&endTime='+endTime+'&backType='+backType);
	}

</script>
