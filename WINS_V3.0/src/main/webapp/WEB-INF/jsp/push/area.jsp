<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${basePath }js/jquery.form.js"></script>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
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
													<i class="fa fa-home"></i><a href="index.html"><%=Config.message.get("HOME")%></a>
												</li>
												<li>
													<a href="#"><%=Config.message.get("PUSH_MANAGEMENT")%></a>
												</li>
											</ul>
											<!-- /BREADCRUMBS -->
											<div class="clearfix">
												<h3 class="content-title pull-left"><%=Config.message.get("AREA_MANAGEMENT")%></h3>
											</div>
											<div class="description"><%=Config.message.get("AREA_REGION_MANAGEMENT")%></div>
										</div>
									</div>
								</div>
								<!-- /PAGE HEADER -->
								<div class="row">
								  <div class="col-md-12">
									<div class="col-md-8 form_input login-box">
										<form>
								            <div class="col-md-12 row_left">
								            	<div class="col-md-3 row_left"> 
									              <select class="form-control province" id="province3" onchange="changeProvience3();" name="provinceId" >
									              <c:if test="${provinceName ==null}">
									              <option value=""><%=Config.message.get("AREA_PLEASE_SELECT_THE_PROVINCE")%></option>
									              </c:if>
									               <c:if test="${provinceName !=null}">
									              <option value="${provinceId}">${provinceName }</option>
									              </c:if>
									              	
												 </select> 
								                </div>
								                <div class="col-md-3 row_left"> 
									              <select class="form-control" id="city3" onchange="changeCity3();" name="cityId">
									              <c:if test="${cityName !=null}">
									              <option>${cityName }</option>
									              </c:if>
												 </select> 
								                </div>
								                <div class="col-md-3 row_left"> 
									              <select class="form-control" id="district3" onchange="changeDistrict3();" name="districtId">
									              <c:if test="${aName !=null}">
									              <option value="${areaId}">${aName }</option>
									              </c:if>
												 </select> 
								                </div>
								                <div class="col-md-3 row_left" > 
									              <select class="form-control" id="hotpoint3" name="hotpointId" onchange="changeHotpoont3();">
									               <c:if test="${hotpointName !=null}">
									              <option value="${hotpointId}">${hotpointName }</option>
									              </c:if>
												 </select> 
								                </div>
								            </div>
										</form>
									</div>
									
									<div class="col-md-4 form_input login-box pull-right">
										<form>
										    <div class="form-group col-md-9  row_left search_icon">
											  	<a href="#" onclick="searchArea();"><i class="fa fa-search"></i></a>
												<input type="search" id="areaName" class="form-control" placeholder="<%=Config.message.get("PLEASE_INPUT_THE_AREA_NAME")%>">
										    </div>
											    <a class="btn btn-danger config" type="button" data-toggle="modal" href="#box-area" id="areaAdd"><%=Config.message.get("AREA_ADD")%></a>
										</form>
									</div>
									
								  </div>
								</div>
								<div class="divide-10"></div>
								<!--添加dialog-->
								<div class="modal fade" id="box-area" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" >
								<div class="modal-dialog" style="width:750px;">
								  <div class="modal-content">
									<div class="modal-header">
									  <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
									  <h4 class="modal-title"><%=Config.message.get("AREA_ADD")%></h4>
									</div>
									<div class="panel-body">
							          <form action="" method="post"  class="form-horizontal" id="areaForm">
							          <div class="col-md-12">
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input"><span class="star">*</span>&nbsp;<%=Config.message.get("AREA_AREA")%>：</label>
								            <div class="col-md-10 row_left">
								            	<div class="col-md-3 row_left"> 
									              <select class="form-control province" id="province" onchange="changeProvience();" name="provinceId">
												 </select> 
								                </div>
								                <div class="col-md-3 row_left"> 
									              <select class="form-control" id="city2" onchange="changeCity();" name="cityId">
												 </select> 
								                </div>
								                <div class="col-md-3 row_left"> 
									              <select class="form-control" id="district" onchange="changeDistrict();" name="districtId">
												 </select> 
								                </div>
								                <div class="col-md-3 row_left" > 
									              <select class="form-control" id="hotpoint" name="hotpointId">
												 </select> 
								                </div>
								            </div>
							            </div>
							            <div class="divide-10"></div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input">&nbsp;<%=Config.message.get("AREA_THE_AREA")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="areaName" placeholder="<%=Config.message.get("AREA_PLEASE_INPUT_THE_DETAIL")%>">
								            </div>
							            </div>
							            <div class="divide-10"></div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input">&nbsp;<%=Config.message.get("AREA_DETIAL_ADDRESS")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="areaAddr" placeholder="<%=Config.message.get("AREA_INPUT_HOUSE_NUMBER")%>" id="detailAddr">
								            </div>
							            </div>
							            <div class="form-group has-error">
							            	<label class="col-md-2"></label>
							            	<label class="control-label" for="inputWarning"	id="detailAddrMsg"></label>
							            </div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input"><span class="star">*</span>&nbsp;<%=Config.message.get("AREA_START_IP")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="startIp" placeholder="" id="startIp"> 
								            </div>
							            </div>
							            <div class="form-group has-error">
							            	<label class="col-md-2"></label>
							            	<label class="control-label" for="inputWarning"	id="startIpMsg"></label>
							            </div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input"><span class="star">*</span>&nbsp;<%=Config.message.get("AREA_END_IP")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="endIp" placeholder="" id="endIp"> 
								            </div>
							            </div>
							            <div class="form-group has-error">
							            	<label class="col-md-2"></label>
							            	<label class="control-label" for="inputWarning"	id="endIpMsg"></label>
							            </div>
						               </div>
							          </form> 
							         </div>
									<div class="modal-footer">
									  <button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
									  <button type="button" class="btn btn-primary" data-dismiss="modal" id="saveArea"><%=Config.message.get("SAVE")%></button>
									</div>
								  </div>
								</div>
							  </div>
							  <!--添加dialog end-->
							  <!--修改dialog-->
								<div class="modal fade" id="box-areaModify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog" style="width:750px;">
								  <div class="modal-content">
									<div class="modal-header">
									  <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
									  <h4 class="modal-title"><%=Config.message.get("EDIT_AREA")%></h4>
									</div>
									<div class="panel-body">
							          <form action="" method="post"  class="form-horizontal" id="uForm">
							          <div class="col-md-12">
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input"><span class="star">*</span>&nbsp;<%=Config.message.get("AREA_AREA")%>：</label>
								            <div class="col-md-10 row_left">
								            	<div class="col-md-3 row_left"> 
									              <select class="form-control province" id="province1" onchange="changeProvience1();" name="provinceId">
												 </select> 
								                </div>
								                <div class="col-md-3 row_left"> 
									              <select class="form-control" id="city1" onchange="changeCity1();" name="cityId">
												 </select> 
								                </div>
								                <div class="col-md-3 row_left"> 
									              <select class="form-control" id="district1" onchange="changeDistrict1();" name="districtId">
												 </select> 
								                </div>
								                <div class="col-md-3 row_left" > 
									              <select class="form-control" id="hotpoint1" name="hotpointId">
												 </select> 
								                </div>
								            </div>
							            </div>
							            <div class="divide-10"></div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input">&nbsp;<%=Config.message.get("AREA_THE_AREA")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="areaName" placeholder="<%=Config.message.get("AREA_PLEASE_INPUT_THE_DETAIL")%>" id="uareaName">
								            </div>
							            </div>
							            <div class="divide-10"></div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input"><span class="star">*</span>&nbsp;<%=Config.message.get("AREA_DETIAL_ADDRESS")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="areaAddr" placeholder="<%=Config.message.get("AREA_INPUT_HOUSE_NUMBER")%>" id="uareaAddr">
								            </div>
							            </div>
							            <div class="form-group has-error">
							            	<label class="col-md-2"></label>
							            	<label class="control-label" for="inputWarning"	id="udetailAddrMsg"></label>
							            </div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input"><span class="star">*</span>&nbsp;<%=Config.message.get("AREA_START_IP")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="startIp" placeholder="" id="ustartIp"> 
								            </div>
							            </div>
							             <div class="form-group has-error">
							            	<label class="col-md-2"></label>
							            	<label class="control-label" for="inputWarning"	id="ustartIpMsg"></label>
							            </div>
							            <div class="form-group msg_font form_group"> 
								            <label class="col-md-2 control-label" for="password-input"><span class="star">*</span>&nbsp;<%=Config.message.get("AREA_END_IP")%>：</label>
								            <div class="col-sm-7 row_left"> 
								             <input type="text" class="form-control" name="endIp" placeholder="" id="uendIp"> 
								            </div>
							            </div>
							            <div class="form-group has-error">
							            	<label class="col-md-2"></label>
							            	<label class="control-label" for="inputWarning"	id="uendIpMsg"></label>
							            </div>
						               </div>
						               <input type="hidden" value="" name="areaId" id="uareaId"/>
							          </form> 
							         </div>
									<div class="modal-footer">
									  <button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
									  <button type="button" class="btn btn-primary" data-dismiss="modal" id="updatesArea"><%=Config.message.get("SAVE")%></button>
									</div>
								  </div>
								</div>
							  </div>
							  <!--修改dialog end-->
							<!--删除dialog-->
								<div class="modal fade" id="box-config-sysnews" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
												<h4 class="modal_title"><%=Config.message.get("ARE_YOU_SURE_DELETE")%></h4>
											</div>
											<div class="modal-footer modal_line">
											<input id="newsLog_delete_id" type="hidden" value="0"/>
												<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
												<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="del()"><%=Config.message.get("SURE")%></button>
											</div>
										</div>
									</div>
								 </div>
							   <!--删除dialog end-->
								<!--群租管理TABLE-->
								<div class="row tab-pane fade in active tables">
										<div class="col-md-12">
										<!-- BOX -->
										<div class="box border purple">
											<table class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
											  <tr>
												<th><%=Config.message.get("AREA_SORT")%></th>
												<th><%=Config.message.get("THE_AREA")%></th>
												<th><%=Config.message.get("AREA_START_IP")%></th>
												<th><%=Config.message.get("AREA_END_IP")%></th>
												<th><%=Config.message.get("AREA_CREATE_USER")%></th>
												<th><%=Config.message.get("AREA_CREATE_TIME")%></th>
												<th><%=Config.message.get("GROUP_OPERATION")%></th>
											  </tr>
											</thead>
											<tbody>
											
											<c:if test="${!empty result}">
											<c:forEach items="${result.areaLists}" var="obj" varStatus="vs">
												<tr>
												<td>${vs.count}</td>
												<td>${obj.areaName}</td>
												<td>${obj.startIp}</td>
												<td>${obj.endIp}</td>
												<td>${obj.createUser}</td>
												<td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td>
													<a class="btn btn-default config" type="button" data-toggle="modal" href="#box-areaModify"  title="<%=Config.message.get("EDIT")%>" onclick="updateArea('${obj.areaId}')"><i class="fa fa-pencil-square"></i></a>
													<a class="btn btn-default config" type="button" data-toggle="modal" href="#box-config-sysnews" title="<%=Config.message.get("DELETE")%>"  onclick="var newsId=$(this).attr('rel');$('#newsLog_delete_id').val(newsId);"  rel="${obj.areaId}"><i class="fa fa-trash-o"></i></a>
												</td>
											  </tr>
											</c:forEach>
											</c:if>  	
											
											</tbody>
										  </table>
										 <div class="row">
										   <div class="dataTables_footer clearfix">
									    	<jsp:include page="../layout/pagination.jsp"><jsp:param value="area.html?areaName=${obj.areaName}" name="actionName"/></jsp:include>
									   		</div>
			 							</div>
										</div>
										<!-- /BOX -->
									</div>
									</div>
								<!--群组管理TABLE END-->
								
								
							</div><!-- /CONTENT END-->
						</div>
					</div>
				</div>
				<!--main_container-->
<script type="text/javascript">
	var proId,cityId,districtId,hotpointId,areaName="";
	$("#areaAdd").click(function(){
		$("#province").html("");
		$("#city2").html("");
		$("#district").html("");
		$("#hotpoint").html("");
		$.ajax({
			url:"${basePath}area/province",
			method:"post",
			success:function(data){
				if(data.code==1){
					var proList=data.result.pro.provinces;
					var cityList=data.result.city.citys;
					var districtList=data.result.district.districts;
					var hotpointList=data.result.hotpoint.hotpoints;
					for(var i=0;i<proList.length;i++){
						$("#province").append("<option value='"+proList[i].provinceId+"'>"+proList[i].provinceName+"</option>");
					}
					for(var i=0;i<cityList.length;i++){
						$("#city2").append("<option value='"+cityList[i].cityId+"'>"+cityList[i].cityName+"</option>");
					}
					for(var i=0;i<districtList.length;i++){
						$("#district").append("<option value='"+districtList[i].districtId+"'>"+districtList[i].districtName+"</option>");
					}
					for(var i=0;i<hotpointList.length;i++){
						$("#hotpoint").append("<option value='"+hotpointList[i].hotpointId+"'>"+hotpointList[i].hotPointName+"</option>");
					} 
				}
			}
		});
	});
	
	
	function changeProvience(){
		$("#province").each(function(){
			var proId=$(this).val();
			$("#city2").html("");
			$("#district").html("");
			$("#hotpoint").html("");
			$("#city2").append("<option value='' selected><%=Config.message.get("CITY")%></option>");
			$("#district").append("<option value='' selected><%=Config.message.get("REGION")%></option>");
			$("#hotpoint").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
			
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{provinceId:proId},
				success:function(data){
					if(data.code==1){
						var cityList=data.result.city.citys;
						for(var i=0;i<cityList.length;i++){
							$("#city2").append("<option value='"+cityList[i].cityId+"'>"+cityList[i].cityName+"</option>");
						}
					}
				}
			});
			
		});
	}
	function changeCity(){
		$("#city2").each(function(){
			var cityId=$(this).val();
			$("#district").html("");
			$("#hotpoint").html("");
			$("#district").append("<option value='' selected><%=Config.message.get("REGION")%></option>");
			$("#hotpoint").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
			
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{cityId:cityId},
				success:function(data){
					if(data.code==1){
						var districtList=data.result.district.districts;
						for(var i=0;i<districtList.length;i++){
							$("#district").append("<option value='"+districtList[i].districtId+"'>"+districtList[i].districtName+"</option>");
						}					}
				}
			});
			
		});
	}

	function changeDistrict(){
		$("#district").each(function(){
			var districtId=$(this).val();
			$("#hotpoint").html("");
			var html="<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>";
			$("#hotpoint").append(html);
			
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{districtId:districtId},
				success:function(data){
					if(data.code==1){
						var hotpointList=data.result.hotpoint.hotpoints;
						for(var i=0;i<hotpointList.length;i++){
							$("#hotpoint").append("<option value='"+hotpointList[i].hotpointId+"'>"+hotpointList[i].hotPointName+"</option>");
						} 
					}
				}
			});
			
		});
	}
	
	$("#saveArea").click(function(){
		var startIp=$("#startIp").val();
		var endIp=$("#endIp").val();
		var reg=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
		if(!reg.test(startIp)){
			$("#startIpMsg").show();
			$("#startIpMsg").html("<%=Config.message.get("AREATE_S_IP_ERROR")%>");
			return false;
		}
		else{
			$("#startIpMsg").hide();
		}
		if(!reg.test(endIp)){
			$("#endIpMsg").show();
			$("#endIpMsg").html("<%=Config.message.get("AREATE_E_IP_ERROR")%>");
			return false;
		}
		else{
			$("#endIpMsg").hide();
		}
		var options={
				url:"${basePath}area/add",
				method:"post",
				dataType:"json",
				success:function(data){
					if(data.code==1){
						$("#box-area").modal("hide");
						load_page('area.html');
					}
				}
		};
		$("#areaForm").ajaxSubmit(options);
	}
	);
	
$("#updatesArea").click(function(){
	
	var uareaAddr=$("#uareaAddr").val();
	var startIp=$("#ustartIp").val();
	var endIp=$("#uendIp").val();
	var reg=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	if(uareaAddr.length<=0){
		$("#udetailAddrMsg").show();
		$("#udetailAddrMsg").html("<%=Config.message.get("AREA_PLEASE_INPUT_THE_DETAIL")%>");
		return false;
	}
	else{
		$("#udetailAddrMsg").hide();
	}
	if(!reg.test(startIp)){
		$("#ustartIpMsg").show();
		$("#ustartIpMsg").html("<%=Config.message.get("AREATE_S_IP_ERROR")%>");
		return false;
	}
	else{
		$("#ustartIpMsg").hide();
	}
	if(!reg.test(endIp)){
		$("#uendIpMsg").show();
		$("#uendIpMsg").html("<%=Config.message.get("AREATE_E_IP_ERROR")%>");
		return false;
	}
	else{
		$("#uendIpMsg").hide();
	}
		
		var options={
				url:"${basePath}area/updateArea",
				method:"post",
				dataType:"json",
				success:function(data){
					if(data.code==1){
						$("#box-areaModify").modal("hide");
						load_page('area.html');
					}
				}
		};
		$("#uForm").ajaxSubmit(options);
	});
	
	function searchArea(){
		areaName=$("#areaName").val();
		if(proId ===undefined){
			proId="";
		}
		if(cityId ===undefined){
			cityId="";
		}
		if(districtId ===undefined){
			districtId="";
		}
		if(hotpointId ===undefined){
			hotpointId="";
		}
		
		load_page('area.html?areaName='+areaName+"&provinceId="+proId+"&cityId="+cityId+"&areaId="+districtId+"&hotpointId="+hotpointId);
	}
	
	function del(rel){
		var areaId=$("#newsLog_delete_id").val();
		$.ajax({
			url:"${basePath}area/delete",
			method:"post",
			data:{areaId:areaId},
			success:function(data){
				if(data.code==1){
					load_page('area.html');
				}
			}
		});
	}
	function updateArea(areaId){
		$.ajax({
			url:"${basePath}area/getAreaById",
			method:"post",
			data:{areaId:areaId},
			success:function(data){
				if(data.code==1){
					//load_page('area.html');
					$("#province1").html("");
					$("#city1").html("");
					$("#district1").html("");
					$("#hotpoint1").html("");
					
					var proList=data.result.pro.provinces;
					var cityList=data.result.citys.citys;
					var districtList=data.result.districts.districts;
					var hotpointList=data.result.hotpoints.hotpoints;
					for(var i=0;i<proList.length;i++){
						var html="";
						html+=""
						
						if(data.result.area.provinceId==proList[i].provinceId){
							$("#province1").append("<option value='"+proList[i].provinceId+"' selected='selected'>"+proList[i].provinceName+"</option>");
						}
						else{
							$("#province1").append("<option value='"+proList[i].provinceId+"'>"+proList[i].provinceName+"</option>");
						} 
						//$("#province1").append("<option value='"+proList[i].provinceId+"'>"+proList[i].provinceName+"</option>");
										//<c:if test="">selected="selected"</c:if>
						//$("#province1").append("<option value='"+proList[i].provinceId+"' <c:if test="+data.result.area.privinceId==proList[i].provinceId+">selected='selected'</c:if> >"+proList[i].provinceName+"</option>");
					}
					
					if(!data.result.area.City_Name){
						$("#city1").append("<option value='' selected>市</option>");
					}
					else{
						
						for(var i=0;i<cityList.length;i++){
							var html="";
							html+=""
							
							if(data.result.area.cityId==cityList[i].cityId){
								$("#city1").append("<option value='"+cityList[i].cityId+"' selected='selected'>"+cityList[i].cityName+"</option>");
							}
							else{
								$("#city1").append("<option value='"+cityList[i].cityId+"'>"+cityList[i].cityName+"</option>");
							} 
						}
					}
					
					if(!data.result.area.District_Name){
						$("#district1").append("<option value='' selected><%=Config.message.get("REGION")%></option>");
					}
					else{
						for(var i=0;i<districtList.length;i++){
							var html="";
							html+=""
							
							if(data.result.area.districtId==districtList[i].districtId){
								$("#district1").append("<option value='"+districtList[i].districtId+"' selected='selected'>"+districtList[i].districtName+"</option>");
							}
							else{
								$("#district1").append("<option value='"+districtList[i].districtId+"'>"+districtList[i].districtName+"</option>");
							} 
						}
					}
					if(!data.result.area.HotPoint_Name){
						$("#hotpoint1").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
					}
					else{
						for(var i=0;i<hotpointList.length;i++){
							var html="";
							html+=""
							
							if(data.result.area.hotpointId==hotpointList[i].hotpointId){
								$("#hotpoint1").append("<option value='"+hotpointList[i].hotpointId+"' selected='selected'>"+hotpointList[i].hotPointName+"</option>");
							}
							else{
								$("#hotpoint1").append("<option value='"+hotpointList[i].hotpointId+"'>"+hotpointList[i].hotPointName+"</option>");
							} 
						}
					}
					
					$("#uareaName").attr("value",data.result.area.areaName);
					$("#uareaAddr").attr("value",data.result.area.areaAddr);
					$("#ustartIp").attr("value",data.result.area.startIp);
					$("#uendIp").attr("value",data.result.area.endIp);
					$("#uareaId").attr("value",data.result.area.areaId);
				}
			}
		});
	}
	
	function changeProvience1(){
		$("#province1").each(function(){
			var proId=$(this).val();
			$("#city1").html("");
			$("#district1").html("");
			$("#hotpoint1").html("");
			$("#city1").append("<option value='' selected><%=Config.message.get("CITY")%></option>");
			$("#district1").append("<option value='' selected><%=Config.message.get("REGION")%></option>");
			$("#hotpoint1").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
			
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{provinceId:proId},
				success:function(data){
					if(data.code==1){
						var cityList=data.result.city.citys;
						for(var i=0;i<cityList.length;i++){
							$("#city1").append("<option value='"+cityList[i].cityId+"'>"+cityList[i].cityName+"</option>");
						}
					}
				}
			});
			
		});
	}
	function changeCity1(){
		$("#city1").each(function(){
			var cityId=$(this).val();
			$("#district1").html("");
			$("#hotpoint1").html("");
			$("#district1").append("<option value='' selected><%=Config.message.get("REGION")%></option>");
			$("#hotpoint1").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
			
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{cityId:cityId},
				success:function(data){
					if(data.code==1){
						var districtList=data.result.district.districts;
						for(var i=0;i<districtList.length;i++){
							$("#district1").append("<option value='"+districtList[i].districtId+"'>"+districtList[i].districtName+"</option>");
						}					}
				}
			});
			
		});
	}
	function changeDistrict1(){
		$("#district1").each(function(){
			var districtId=$(this).val();
			$("#hotpoint1").html("");
			var html="<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>";
			$("#hotpoint1").append(html);
			
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{districtId:districtId},
				success:function(data){
					if(data.code==1){
						var hotpointList=data.result.hotpoint.hotpoints;
						for(var i=0;i<hotpointList.length;i++){
							$("#hotpoint1").append("<option value='"+hotpointList[i].hotpointId+"'>"+hotpointList[i].hotPointName+"</option>");
						} 
					}
				}
			});
			
		});
	}
	
	function changeProvience3(){
		$("#province3").each(function(){
			proId=$(this).val();
			var proName=$(this).find("option:selected").text();
			$("#city3").html("");
			$("#district3").html("");
			$("#hotpoint3").html("");
			$("#city3").append("<option value='' selected><%=Config.message.get("CITY")%></option>");
			$("#district3").append("<option value='' selected><%=Config.message.get("REGION")%></option>");
			$("#hotpoint3").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
			load_page('area.html?provinceId='+proId+"&provinceName="+proName+"&areaName="+areaName);
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{provinceId:proId},
				success:function(data){
					if(data.code==1){
						var cityList=data.result.city.citys;
						for(var i=0;i<cityList.length;i++){
							$("#city3").append("<option value='"+cityList[i].cityId+"'>"+cityList[i].cityName+"</option>");
						}
					}
				}
			});
		
		});
	}
	var cityName;
	function changeCity3(){
		$("#city3").each(function(){
			cityId=$(this).val();
			cityName=$(this).find("option:selected").text();
			$("#district").html("");
			$("#hotpoint").html("");
			$("#district").append("<option value='' selected><%=Config.message.get("CITY")%></option>");
			$("#hotpoint").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
			load_page('area.html?cityId='+cityId+"&cityName="+cityName+"&provinceId="+proId+"&areaName="+areaName);
			
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{cityId:cityId},
				success:function(data){
					if(data.code==1){
						var districtList=data.result.district.districts;
						for(var i=0;i<districtList.length;i++){
							$("#district3").append("<option value='"+districtList[i].districtId+"'>"+districtList[i].districtName+"</option>");
						}					}
				}
			});
			//$("#city3").append("<option value='"+cityId+"'>"+cityName+"</option>");
		});
	}
	function changeDistrict3(){
		$("#district3").each(function(){
			districtId=$(this).val();
			var areaName=$(this).find("option:selected").text();
			$("#hotpoint3").html("");
			var html="<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>";
			$("#hotpoint3").append(html);
			load_page('area.html?cityId='+cityId+"&areaId="+districtId+"&provinceId="+proId+"&areaName"+areaName);
			$.ajax({
				url:"${basePath}area/province",
				method:"post",
				data:{districtId:districtId},
				success:function(data){
					if(data.code==1){
						var hotpointList=data.result.hotpoint.hotpoints;
						for(var i=0;i<hotpointList.length;i++){
							$("#hotpoint3").append("<option value='"+hotpointList[i].hotpointId+"'>"+hotpointList[i].hotPointName+"</option>");
						} 
					}
				}
			});
			
		});
	};
	function changeHotpoont3(){
		$("#hotpoint3").each(function(){
			hotpointId=$(this).val();
			var hotpointName=$(this).find("option:selected").text();
			load_page('area.html?cityId='+cityId+"&areaId="+districtId+"&provinceId="+proId+"&hotpointId="+hotpointId+"&areaName="+areaName);
			//load_page('area.html?hotpointId'+hotpointId);
		});
	}
	
	$(function(){
		 function loadProvince(){
				$("#province3").each(function(){
					$("#city3").html("");
					$("#district3").html("");
					$("#hotpoint3").html("");
					$("#city3").append("<option value='' selected><%=Config.message.get("CITY")%></option>");
					$("#district3").append("<option value='' selected><%=Config.message.get("REGION")%></option>");
					$("#hotpoint3").append("<option value='' selected><%=Config.message.get("TRADING_AREA")%></option>");
					$.ajax({
						url:"${basePath}area/province",
						method:"post",
						data:{},
						success:function(data){
							if(data.code==1){
								var cityList=data.result.pro.provinces;
								for(var i=0;i<cityList.length;i++){
									$("#province3").append("<option value='"+cityList[i].provinceId+"'>"+cityList[i].provinceName+"</option>");
								}
							}
						}
					});
				});
			} 
		 loadProvince();
	})
	</script>
