<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<!--deletedialog-->
<div class="modal fade" id="box-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				<h4 class="modal_title"><%=Config.message.get("ARE_YOU_SURE_DELETE")%></h4>
			</div>
			<div class="modal-footer modal_line">
				<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="sureDelete()"><%=Config.message.get("SURE")%></button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="box-editIPS" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				<h4 class="modal-title"><%=Config.message.get("IPS_MODIFY_IPS_CONFIGURATION")%></h4>
			</div>
			<div class="divide-10"></div>
			<!-- 网站 -->
			<div class="tab-pane">
				<div class="panel-body">
					<form id="formEdit" action="" method="" enctype="multipart/form-data" class="form-horizontal">
						<div class="col-md-12 pull-left">
							<div class="form-group msg_font form_group" style="margin-bottom:2px">
								<label class="col-md-3 control-label" for="password-input">
									<span class="star">*</span>&nbsp;<%=Config.message.get("IPS_NAME")%>：</label>
								<div class="col-sm-7 row_left">
									<input type="text" class="form-control" placeholder="<%=Config.message.get("IPS_PLEASE_INPUT_IPSNAME")%>" id="IPSName_edit" oninput="$('#ipsName_error_span_edit').html('')">
								</div>
							</div>
							<div class="form-group has-error">
								<label class="col-md-3"></label> <label class="control-label"  id="ipsName_error_span_edit"></label>
							</div>
							<div class="divide-20"></div>
							<div class="form-group msg_font form_group" style="margin-bottom:2px">
								<label class="col-md-3 control-label" for="password-input">
									<span class="star">*</span>&nbsp;<%=Config.message.get("IPS_PROVENCE")%>：</label>
								<div class="col-sm-7 row_left">
									<select id="province_edit" class="form-control"  name="provinceId"></select>
								</div>
							</div>
							<div class="form-group has-error" >
								<label class="col-md-3"></label> <label class="control-label"  id="province_error_span_edit"></label>
							</div>
							<div class="divide-20"></div>
							<div class="form-group msg_font form_group" style="margin-bottom:2px">
								<label class="col-md-3 control-label" for="password-input">
									<span class="star">*</span>&nbsp;<%=Config.message.get("DATAS_CITY_CITY")%>：</label>
								<div class="col-sm-7 row_left">
									<select id="city_edit" class="form-control"  id="city_edit" name="cityId"></select>
								</div>
							</div>
							<div class="form-group has-error" >
								<label class="col-md-3"></label> <label class="control-label"  id="city_error_span_edit"></label>
							</div>
							<div class="divide-20"></div>
                            <input type="hidden" id="ips_Id" value="">
							<div class="form-group msg_font form_group" style="margin-bottom:2px">
								<label class="col-md-3 control-label" for="password-input">
									<span class="star">*</span>&nbsp;IPS IP：</label>
								<div class="col-sm-7 row_left">
									<input type="text" id="ips_Id_edit" class="form-control" oninput="checkIpEdit(this);" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: 115.61.31.220" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')">
								</div>
							</div>
							<div class="form-group has-error" >
								<label class="col-md-3"></label> <label class="control-label"  id="ipsIP_error_span_edit"></label>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="editIPS"><%=Config.message.get("SAVE")%></button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="box-addIPS" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				<h4 class="modal-title"><%=Config.message.get("IPS_ADD_IPS_CONFIGURATIION")%></h4>
			</div>
			<div class="divide-10"></div>
				<!-- 网站 -->
				<div class="tab-pane">
					<div class="panel-body">
						<form id="formAdd" action="" method="" enctype="multipart/form-data" class="form-horizontal">
							<div class="col-md-12 pull-left">
								<div class="form-group msg_font form_group" style="margin-bottom:2px">
									<label class="col-md-3 control-label" for="password-input">
										<span class="star">*</span>&nbsp;<%=Config.message.get("IPS_NAME")%>：</label>
									<div class="col-sm-7 row_left">
										<input type="text" class="form-control" placeholder="<%=Config.message.get("IPS_PLEASE_INPUT_IPSNAME")%>" id="IPSName" oninput="$('#ipsName_error_span_add').html('')">
									</div>
								</div>
								<div class="form-group has-error" >
									<label class="col-md-3"></label> <label class="control-label"  id="ipsName_error_span_add"></label>
								</div>
								<div class="divide-20"></div>
								<div class="form-group msg_font form_group" style="margin-bottom:2px">
									<label class="col-md-3 control-label" for="password-input">
										<span class="star">*</span>&nbsp;<%=Config.message.get("IPS_PROVENCE")%>：</label>
									<div class="col-sm-7 row_left">
										<select id="province_add" class="form-control"  name="provinceId"></select>
									</div>
								</div>
								<div class="form-group has-error" >
									<label class="col-md-3"></label> <label class="control-label"  id="province_error_span_add"></label>
								</div>
								<div class="divide-20"></div>
								<div class="form-group msg_font form_group" style="margin-bottom:2px">
									<label class="col-md-3 control-label" for="password-input">
										<span class="star">*</span>&nbsp;<%=Config.message.get("DATAS_CITY_CITY")%>：</label>
									<div class="col-sm-7 row_left">
										<select id="city_add" class="form-control"  name="cityId"></select>
									</div>
								</div>
								<div class="form-group has-error" >
									<label class="col-md-3"></label> <label class="control-label"  id="city_error_span"></label>
								</div>
								<div class="divide-20"></div>
								<div class="form-group msg_font form_group" style="margin-bottom:2px">
									<label class="col-md-3 control-label" for="password-input">
										<span class="star">*</span>&nbsp;IPS IP：</label>
									<div class="col-sm-7 row_left">
										<input type="text" class="form-control" oninput="checkIpAdd(this);" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: 115.61.31.220" id="ips_Ip" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')">
									</div>
								</div>
								<div class="form-group has-error" >
									<label class="col-md-3"></label> <label class="control-label"  id="ipsIP_error_span_add"></label>
								</div>
								<%--<div class="form-group msg_font form_group" style="margin-bottom:2px">
									<label class="col-md-3 control-label" for="password-input">
										<span class="star">*</span>&nbsp;IPS 状态：</label>
									<div class="col-sm-7 row_left">
										<label class="radio-inline">
											<input type="radio" name="ipsStatus" value="1" style="margin: 0px" checked="checked">启用
										</label>
										<label class="radio-inline" style="margin-left: 15px;">
											<input type="radio" name="ipsStatus" value="0" style="margin: 0px">停用
										</label>
									</div>
								</div>--%>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
						<button type="button" class="btn btn-primary" data-dismiss="modal" id="addIPS"><%=Config.message.get("SAVE")%></button>
					</div>
				</div>
		</div>
	</div>
</div>
<!--deletedialog end-->
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("ROLEMANAGE_HOME")%></a>
								</li>
								<li><a href="#"><%=Config.message.get("ROLEMANAGE_SYSTEMMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("EQUIPMENT_MANAGEMENT")%></h3>
							</div>
							<div class="description"></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
							<c:if test="${rv.menuId==28 && rv.add_permission!=0}">
								<div class="from-group col-md-2 pull-right">
									<a class="btn btn-danger config" type="button" data-toggle="modal" href="#box-addIPS"><%=Config.message.get("IPS_ADD_IPS_CONFIGURATIION")%></a>
								</div>
							</c:if>
						</c:forEach>
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
										<th><%=Config.message.get("ACCOUNTSMANAGE_SORT")%></th>
										<th><%=Config.message.get("IPS_STUTAS")%></th>
										<th><%=Config.message.get("EQUIPMENT_NAME")%></th>
										<th><%=Config.message.get("IPS_ADDRESS")%></th>
										<th><%=Config.message.get("IPS_PROVENCE")%></th>
										<th><%=Config.message.get("DATAS_CITY_CITY")%></th>
										<th><%=Config.message.get("ACCOUNTSMANAGE_CREATEACCOUNT")%></th>
										<th><%=Config.message.get("ACCOUNTSMANAGE_CREATETIME")%></th>
										<th><%=Config.message.get("ACCOUNTSMANAGE_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach var="ips" items="${result.list}" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>
													<div class="checkbox checkbox-slider--b checkbox-slider-default check_radio">
														<label> <input status="${ips.ipsId}" type="checkbox"
															<c:if test="${ips.ipsStatus eq '1'}">checked</c:if> onclick="updateIPSStatus(this)"><span></span>
														</label>
													</div>
												</td>
												<td>${ips.ipsName}</td>
												<td>${ips.ipsIp}</td>
												<td>${ips.provinceName}</td>
												<td>${ips.cityName}</td>
												<td>${ips.createName}</td>
												<td><fmt:formatDate value="${ips.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>
													 <c:forEach var="rv" items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId == 28 and rv.update_permission != 0}">
															<a class="btn btn-default" onclick="searchIPSInfo(this)" data-toggle="modal" href="#box-editIPS" status="${ips.ipsId}" title="Edit">
																<i class="fa fa-pencil-square"></i>
															</a>
														</c:if>
													</c:forEach>
													<%--<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId == 28 and rv.delete_permission != 0}">
															<a class="btn btn-default config" type="button" onclick="deleteIPS(this)" data-toggle="modal" href="#box-delete" status="${ips.ipsId}" title="delete">
																<i class="fa fa-trash-o"></i>
															</a>
														</c:if>
													</c:forEach>--%>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<input type="hidden" id="sure_delete" value="" />
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="ipsManage.html?cityId=${result.cityId}&showStatus=${result.showStatus}" name="actionName" />
									</jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<%--<div class="footer-tools">
                    <span class="go-top">
                        <i class="fa fa-chevron-up"></i> Top
                    </span>
                </div>--%>
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript">
	var data_pr=[];
	var data_ci=[];
	$(function () {
		$.ajax({
			url:"ips/getProvinceCityInfo",
			method:"post",
			dataType:"json",
			success:function(data){
				console.log(data);
				var province=data.result.province;
				var city=data.result.city;
				for(var i=0;i<province.length;i++){
					var obj={};
					obj.id=province[i].provinceId;
					obj.text=province[i].provinceName;
					data_pr.push(obj);
					var option='<option value="'+province[i].provinceId+'">'+province[i].provinceName+'</option>';
					var $option=$(option);
					$("#province_add").append($option);
				}
				for (var j=0;j<city.length;j++){
					var obj={};
					obj.id=city[j].cityId;
					obj.text=city[j].cityName;
					data_ci.push(obj);
					var option='<option value="'+city[j].cityId+'">'+city[j].cityName+'</option>';
					var $option=$(option);
					$("#city_add").append($option);
				}
			}
		});
	});


	function searchIPSInfo(obj){
		var ipsId=$(obj).attr("status");
		$.ajax({
			url:"ips/getIPSInfo",
			method:"post",
			data:{'ipsId':ipsId},
			dataType:"json",
			success:function(data){
				console.log(data);
                $("#ips_Id").val(data.result.ipsId);
				$("#IPSName_edit").val(data.result.ipsName);
				$("#ips_Id_edit").val(data.result.ipsIp);
				$("#province_edit").empty();
				$("#city_edit").empty();
				var provinceId=data.result.provinceId;
				var provinceName="";
				for(var i=0;i<data_pr.length;i++){
					if(provinceId==data_pr[i].id){
						provinceName=data_pr[i].text;
					}
				}
				var option1='<option value="'+data.result.provinceId+'">'+provinceName+'</option>';
				var $option1=$(option1);
				$("#province_edit").append($option1);
				var cityId=data.result.cityId;
				var cityName="";
				for(var i=0;i<data_ci.length;i++){
					if(cityId==data_ci[i].id){
						cityName=data_ci[i].text;
					}
				}
				var option2='<option value="'+cityId+'">'+cityName+'</option>';
				var $option2=$(option2);
				$("#city_edit").append($option2);
				for(var i=0;i<data_ci.length;i++){
					if(cityId!=data_ci[i].id){
						var option3='<option value="'+data_ci[i].id+'">'+data_ci[i].text+'</option>';
						var $option3=$(option3);
						$("#city_edit").append($option3);
					}
				}
			}
		});
	}

	//查找账号信息
	function deleteIPS(obj){
		var ipsId = $(obj).attr("status");
		$("#sure_delete").val(ipsId);
	}
	//delete账号
	function sureDelete(){
		var ipsId = $("#sure_delete").val();
		$.ajax({
			url:"ips/delete",
			method:"post",
			data:{'ipsId':ipsId},
			dataType:"json",
			success:function(data){
				window.confirm(data.desc);
				var menuUrl = $("#menu_28").attr("href");
				var reqUrl = 'ipsManage.html';
				if(menuUrl.indexOf("?")>0){
					var cityIdInfo = menuUrl.substring(menuUrl.indexOf("?")+1);
					reqUrl += "&"+cityIdInfo;
				}
				load_page(reqUrl);
			}
		});
	}
	function updateIPSStatus (obj){
		var bool = $(obj).prop("checked");
		var ipsStatus = 0;
		var ipsId = $(obj).attr("status");
		if(bool == true){
			ipsStatus = 1;
		}else{
			ipsStatus = 0;
		}
		$.ajax({
			url:"ips/updateStatus",
			type:"post",
			data:{"ipsId":ipsId,"ipsStatus":ipsStatus},
			dataType:"json",
			success:function(data){
				window.confirm(data.desc);
			}
		});
	}
    $("#editIPS").click(function(){
        var ipsId=$("#ips_Id").val();
        var ipsName=$("#IPSName_edit").val().trim();
        var provinceId=$("#province_edit option:selected").val();
        var cityId=$("#city_edit option:selected").val();
        var ipsIp=$("#ips_Id_edit").val().trim();
        if(ipsName==""){
            $("#ipsName_error_span_edit").html("Please input the IPS's name");
            return;
        }
        if(ipsIp==""){
            $("#ipsIP_error_span_edit").html("Please input the IPS's IP");
            return;
        }
        if($("#ipsIP_error_span_edit").html()!="" || $("#ipsName_error_span_edit").html()!=""){
            return;
        }
        $.ajax({
            url:"ips/edit",
            method:"post",
            data:{"ipsId":ipsId,"ipsName":ipsName,"provinceId":provinceId,"cityId":cityId,"ipsIp":ipsIp},
            dataType:"json",
            success:function(data){
                window.confirm(data.desc);
                load_page("ipsManage.html");
            }
        });

    });

	$("#addIPS").click(function(){
		var ipsName =$("#IPSName").val().trim();
		var provinceId=$("#province_add option:selected").val();
		var cityId=$("#city_add option:selected").val();
		var ipsIp=$("#ips_Ip").val().trim();
		if(ipsName==""){
			$("#ipsName_error_span_add").html("Please input the IPS's name");
			return;
		}
		if(ipsIp==""){
			$("#ipsIP_error_span_add").html("Please input the IPS's IP");
			return;
		}
        if($("#ipsIP_error_span_add").html()!="" || $("#ipsName_error_span_add").html()!=""){
            return;
        }
		$.ajax({
			url:"ips/add",
			method:"post",
			data:{"ipsName":ipsName,"provinceId":provinceId,"cityId":cityId,"ipsIp":ipsIp},
			dataType:"json",
			success:function(data){
				window.confirm(data.desc);
				load_page("ipsManage.html");
			}
		});
	});

	function checkIpEdit(obj){
		$("#ipsIP_error_span_edit").html("");
		var IP = $(obj).val();
		var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		if(!reg.test(IP)){
			$("#ipsIP_error_span_edit").html("<%=Config.message.get("IP_NOT_CORRECT")%>");
			return;
		}else{
			var ipArry = new Array();
			ipArry = IP.split(".");
			if(ipArry[0]>255 || ipArry[0]<0 || ipArry[1]>255 || ipArry[1]<0 || ipArry[2]>255 || ipArry[2]<0 || ipArry[3]>255 || ipArry[3]<0){
				$("#ipsIP_error_span_edit").html("<%=Config.message.get("IP_NOT_CORRECT_BETWEEN")%>");
				return ;
			}
		}
	}

	function checkIpAdd(obj){
		$("#ipsIP_error_span_add").html("");
		var IP = $(obj).val();
		var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		if(!reg.test(IP)){
			$("#ipsIP_error_span_add").html("<%=Config.message.get("IP_NOT_CORRECT")%>");
			return;
		}else{
			var ipArry = new Array();
			ipArry = IP.split(".");
			if(ipArry[0]>255 || ipArry[0]<0 || ipArry[1]>255 || ipArry[1]<0 || ipArry[2]>255 || ipArry[2]<0 || ipArry[3]>255 || ipArry[3]<0){
				$("#ipsIP_error_span_add").html("<%=Config.message.get("IP_NOT_CORRECT_BETWEEN")%>");
				return ;
			}
		}
	}
</script>