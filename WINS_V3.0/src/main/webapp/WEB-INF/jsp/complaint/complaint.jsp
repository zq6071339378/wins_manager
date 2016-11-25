<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	var date = new Date();
	var year1 = date.getFullYear();
	var month1 = date.getMonth()+1;
	month11=month1;
	if(month1 >= 1 && month1 <= 9){
		month11 = "0" + month1;
	}
	//第一month
	var one=year1+"-"+month11;
	var option1="<option value='"+one+"'>"+year1+"-"+month1+"</option>";
	//第二month
	if(month1==1){
		year2=year1-1;
		month2=12;
		var two=year2+"-"+month2;
	}else{
		year2=year1;
		month2=month1-1;
		month22=month2;
		if(month2 >= 1 && month2 <= 9){
			month22 = "0" + month2;
		}
		var two=year2+"-"+month22;
	}
	var option2="<option value='"+two+"'>"+year2+"-"+month2+"</option>";
	//第三month
	if(month2==1){
		year3=year2-1;
		month3=12;
		var three=year3+"-"+month3;
	}else{
		year3=year2;
		month3=month2-1;
		month33=month3;
		if(month3 >= 1 && month3 <= 9){
			month33 = "0" + month3;
		}
		var three=year3+"-"+month33;
	}
	var option3="<option value='"+three+"'>"+year3+"-"+month3+"</option>";
	//第四month
	if(month3==1){
		year4=year3-1;
		month4=12;
		var four=year4+"-"+month4;
	}else{
		year4=year3;
		month4=month3-1;
		month44=month4;
		if(month4 >= 1 && month4 <= 9){
			month44 = "0" + month4;
		}
		var four=year4+"-"+month44;
	}
	var option4="<option value='"+four+"'>"+year4+"-"+month4+"</option>";
	//第五month
	if(month4==1){
		year5=year4-1;
		month5=12;
		var five=year5+"-"+month5;
	}else{
		year5=year4;
		month5=month4-1;
		month55=month5;
		if(month5 >= 1 && month5 <= 9){
			month55 = "0" + month5;
		}
		var five=year5+"-"+month55;
	}
	var option5="<option value='"+five+"'>"+year5+"-"+month5+"</option>";
	//第六month
	if(month5==1){
		year6=year5-1;
		month6=12;
		var six=year6+"-"+month6;
	}else{
		year6=year5;
		month6=month5-1;
		month66=month6;
		if(month6 >= 1 && month6 <= 9){
			month66 = "0" + month6;
		}
		var six=year6+"-"+month66;
	}
	var option6="<option value='"+six+"'>"+year6+"-"+month6+"</option>";
	
	
	$('#timing').append(option1).append(option2).append(option3).append(option4).append(option5).append(option6);

  
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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("COMPLAINT_OPERATIONMANAGEMENT")%></a></li>
								<li><a href="#"><%=Config.message.get("COMPLAINT_CUSTOMERCOMPLAINTMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("COMPLAINT_COMPLAINTMANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("COMPLAINT_COMPLANINATTENTION")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class='col-sm-2 row_left'>
							<div class="controls">
								<div class="col-md-12 row_left">
									<select class="form-control" id="timing">
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4 form_input login-box row_left">
							<form>
								<div class="form-group col-md-11 row_left search_icon">
									<a href="javascript:;" onclick="find()"><i
										class="fa fa-search"></i></a> <input type="search"
										id="exampleInputSearch" class="form-control"
										placeholder="<%=Config.message.get("COMPLAINT_PLEASEINPUT")%>">
								</div>
							</form>
						</div>
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
										<th><%=Config.message.get("COMPLAINT_ACCOUNT")%></th>
										<th><%=Config.message.get("CREATE_POLICY_POLICY_NAME")%></th>
										<th><%=Config.message.get("COMPLAINT_PUSH")%></th>
										<th><%=Config.message.get("COMPLAINT_CLICK")%></th>
										<th><%=Config.message.get("COMPLAINT_ORDER")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach items="${result.LIST}" var="list" varStatus="v">
											<tr>
												<td>${list.pushUser}</td>
												<td>${list.policyName}</td>
												<td><a href="javascript:;"
													onclick="showPush(this)" pushUser="${list.pushUser}" policyId="${list.policyId}">${list.numPush}</a></td>
												<td><a href="javascript:;"
													onclick="showClick(this)" pushUser="${list.pushUser}" policyId="${list.policyId}">${list.numClick}</a></td>
												<td><a href="javascript:;"
													onclick="showOrder('${list.pushUser}')">${list.numOrder}</a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<c:if test="${!empty result}">
										<jsp:include page="../layout/pagination.jsp"><jsp:param
												value="complaint/showComplaint?PushUser=${result.PushUser}&time=${result.time}"
												name="actionName" /></jsp:include>
									</c:if>
								</div>
							</div>
						</div>
						<!-- /BOX -->
						<input type="hidden" id="timename" value="${result.createTime}">
					</div>
				</div>
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript">
function find(){
	 var time=$('#timing').val().trim();
	 var PushUser = $("#exampleInputSearch").val().trim();
	 load_page('${basePath}complaint/showComplaint?PushUser='+encodeURIComponent(PushUser)+'&time='+time);
}
function showPush(obj){
	var time=$('#timename').val().trim();
	var pushUser = $(obj).attr("pushUser");
	var policyId = $(obj).attr("policyId");
	load_page('${basePath}complaint/showPush?PushUser='+pushUser+'&time='+time+'&policyId='+policyId);
}
function showClick(obj){
	var time=$('#timename').val().trim();
	var pushUser = $(obj).attr("pushUser");
	var policyId = $(obj).attr("policyId");
	load_page('${basePath}complaint/showClick?PushUser='+pushUser+'&time='+time+'&policyId='+policyId);
}
function showOrder(user){
	var time=$('#timename').val().trim();
	var PushUser=user;
	load_page('${basePath}complaint/showOrder?PushUser='+PushUser+'&time='+time);
}
</script>