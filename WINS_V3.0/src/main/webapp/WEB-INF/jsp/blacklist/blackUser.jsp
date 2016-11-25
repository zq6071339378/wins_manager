<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("BLACK_OPERATION_MANAGEMENT")%></a></li>
								<li><a href="#"><%=Config.message.get("BLACK_USER_BLACK_LIST")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("BLACK_USER_BLACK_LIST")%></h3>
							</div>
							<div class="description"><%=Config.message.get("USER_BLACKLIST_MANAGERMENT_CHECK")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12 row_left">
						<div class="col-md-2 row_left">
							<select class="form-control" id="UserStatus" onchange="find()">
								<option value="0"><%=Config.message.get("BLACK_LIST_ALL")%></option>
								<option value="1"
									<c:if test="${result.UserStatus eq 1}">selected="selected"</c:if>><%=Config.message.get("BLACK_USER_SHIELDING_ONE_DAY")%></option>
								<option value="2"
									<c:if test="${result.UserStatus eq 2}">selected="selected"</c:if>><%=Config.message.get("BLACK_USER_SHIELDING_ONE_WEEK")%></option>
								<option value="3"
									<c:if test="${result.UserStatus eq 3}">selected="selected"</c:if>><%=Config.message.get("BLACK_USER_SHIELDING_ONE_MONTH")%></option>
								<option value="4"
									<c:if test="${result.UserStatus eq 4}">selected="selected"</c:if>><%=Config.message.get("BLACK_USER_SHIELDING_PERMANENT")%></option>
							</select>
						</div>
						<div class="col-md-4 form_input login-box">
							<form>
								<div class="form-group col-md-12 row_left search_icon">
									<a href="javascript:;" onclick="find()"><i
										class="fa fa-search"></i></a> <input type="search"
										id="exampleInputSearch" class="form-control"
										placeholder="<%=Config.message.get("PLEASE_INPUT_USER_NUM_OR_IP")%>">
								</div>
							</form>
						</div>
						<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
							<c:if test="${rv.menuId==13 && rv.add_permission!=0}">
								<div class="from-group col-md-2 row_left">
									<a class="btn btn-danger config" type="button"
										data-toggle="modal" href="#box-blackUser"><%=Config.message.get("CREATE_POLICY_ADD")%></a>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="divide-10"></div>
				<div class="modal fade" id="box-blackUser" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
					data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("ADD_USER_TO_BLACK_LIST")%></h4>
							</div>
							<div class="divide-10"></div>
							<div class="row">
								<div class="col-md-12">
									<ul class="nav nav-tabs" role="tablist">
										<li class="init_li active" style="width: 33.333%;text-align: center;font-size: 15px"><a href="#user_number_" role="tab" data-toggle="tab"><%=Config.message.get("ADD_USER_NUMBER")%></a></li>
										<li class="init_li" style="width: 33.333%;text-align: center;font-size: 15px"><a href="#user_IP" role="tab" data-toggle="tab" ><%=Config.message.get("ADD_USER_IP")%></a></li>
										<li class="init_li" style="width: 33.333%;text-align: center;font-size: 15px"><a href="#IP_paragraph" role="tab" data-toggle="tab" ><%=Config.message.get("ADD_IP_PARAGRAPH")%></a></li>
									</ul>
								</div>
							</div>
							<div class="divide-10"></div>
							<div class="tab-content">
								<!-- 添加用户号码 -->
								<div id="user_number_" class="tab-pane active">
									<div class="panel-body">
										<form id="form" action="${basePath}blacklist/createBlackUsers" method="post" enctype="multipart/form-data" class="form-horizontal">
											<div class="col-md-11 pull-left">
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input">
														<span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_USER_USER_NUMBER")%>：</label>
													<div class="col-sm-7 row_left">
														<input type="text" class="form-control" name="password" placeholder="" id="userName" oninput="clearError()" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\.\@]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9\.\@]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9\.\@]/g,'')" />
													</div>
												</div>
												<div class="form-group has-error" style="display: none;" id="errordiv">
													<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="errorMsg"></label>
												</div>
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("SHIELDING_TIME")%>：</label>
													<div class="col-md-7 row_left">
														<select class="form-control" id="user_status" name="UserStatus">
															<option value="1"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_DAY")%></option>
															<option value="2"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_WEEK")%></option>
															<option value="3"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_MONTH")%></option>
															<option value="4"><%=Config.message.get("BLACK_USER_SHIELDING_PERMANENT")%></option>
														</select>
													</div>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"><span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_SELECT_POLICY")%>：</label>
													<div class="col-md-7 row_left">
														<form role="form">
															<div class="form-group" style="margin-left: 0px;margin-right: 0px">
																<input type="hidden" value="" name="PolicyId"/>
																<input class="form-control" id="policy_select" onclick="showSelectPolicy(this)" oninput="search_policy(this)"/>
																<select id="policyinfo" multiple class="form-control"></select>
															</div>
														</form>
														<%--<select class="form-control" id="policyinfo" name="PolicyId">
														</select>--%>
													</div>
												</div>
												<div class="form-group has-error" style="display: none;" id="error_policy">
													<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="errorMsgPolicy"><%=Config.message.get("BLACK_PLEASE_SELECT_POLICY")%></label>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"><%=Config.message.get("BLACK_IMPORT")%> ：</label>
													<div class="col-md-1 row_left">
														<input type="file" style="display: none" id="lefile" name="blackfile" onchange="showFileName()">
														<div class="input-append">
															<a onclick="$('#lefile').click();" class="btn btn-primary"><%=Config.message.get("BLACK_SCAN")%>&nbsp;&nbsp;</a>
														</div>
													</div>
													<div class="col-md-4 col_left">
												<span class="span_fonts"><i class="fa fa-question-circle" title="Please select a TXT file, this operation will override manually entered blacklist"></i>
												</span>
													</div>
													<div class="col-md-1"></div>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"></label>
													<span id="import" class="span_fonts col-sm-7 row_left" style="margin-top: -15px;"><%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%></span>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button data-dismiss="modal" class="btn btn-default" type="button" onclick="clearInfo()"><%=Config.message.get("CANCEL")%></button>
										<button type="button" class="btn btn-primary" data-dismiss="modal" id="createBlack"><%=Config.message.get("SAVE")%></button>
									</div>
								</div>
								<!-- 添加用户IP -->
								<div class="tab-pane" id="user_IP">
									<div class="panel-body">
										<form id="form1" action="${basePath}blacklist/createBlackUsers" method="post" enctype="multipart/form-data" class="form-horizontal">
											<div class="col-md-11 pull-left">
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input">
														<span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_USER_IP")%>：</label>
													<div class="col-sm-7 row_left">
														<input type="text" class="form-control" name="password" id="userIP" onchange="checkIp(this)" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: 182.111.111.234" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')" />
													</div>
												</div>
												<div class="form-group has-error" style="display: none;" id="errordiv1">
													<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="errorMsgIP"></label>
												</div>
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("SHIELDING_TIME")%>：</label>
													<div class="col-md-7 row_left">
														<select class="form-control" id="user_status1" name="UserStatus">
															<option value="1"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_DAY")%></option>
															<option value="2"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_WEEK")%></option>
															<option value="3"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_MONTH")%></option>
															<option value="4"><%=Config.message.get("BLACK_USER_SHIELDING_PERMANENT")%></option>
														</select>
													</div>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"><span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_SELECT_POLICY")%> ：</label>
													<div class="col-md-7 row_left">
														<form role="form">
															<div class="form-group" style="margin-left: 0px;margin-right: 0px">
																<input type="hidden" value="" name="PolicyId"/>
																<input class="form-control" id="policy_select1" onclick="showSelectPolicy(this)" oninput="search_policy(this)"/>
																<select id="policyinfo1" multiple class="form-control">
																</select>
															</div>
														</form>
													</div>
												</div>
												<div class="form-group has-error" style="display: none;" id="error_policy1">
													<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="errorMsgPolicy1"><%=Config.message.get("BLACK_PLEASE_SELECT_POLICY")%></label>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"><%=Config.message.get("BLACK_IMPORT")%> ：</label>
													<div class="col-md-1 row_left">
														<input type="file" style="display: none" id="lefile1" name="blackfile" onchange="showFileNameIP()">
														<div class="input-append">
															<!-- <input id="photoCover" type="text" placeholder="文件名...." style="height:32px;width: 150px" class="search-query"> -->
															<a onclick="$('#lefile1').click();" class="btn btn-primary"><%=Config.message.get("BLACK_SCAN")%>&nbsp;&nbsp;</a>
														</div>
													</div>
													<div class="col-md-4 col_left">
												<span class="span_fonts"><i class="fa fa-question-circle" title="Please select a TXT file, this operation will override manually entered blacklist"></i>
												</span>
													</div>
													<div class="col-md-1"></div>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"></label>
													<span id="import1" class="span_fonts col-sm-7 row_left" style="margin-top: -15px;"><%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%></span>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button data-dismiss="modal" class="btn btn-default" type="button" onclick="clearInfo()"><%=Config.message.get("CANCEL")%></button>
										<button type="button" class="btn btn-primary" data-dismiss="modal" id="createBlackIP"><%=Config.message.get("SAVE")%></button>
									</div>
								</div>
								<!-- 添加IP段 -->
								<div class="tab-pane" id="IP_paragraph">
									<div class="panel-body">
										<form id="form2" action="" method="post" enctype="multipart/form-data" class="form-horizontal">
											<div class="col-md-11 pull-left">
												<!-- start IP -->
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input">
														<span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_START_IP")%>：</label>
													<div class="col-sm-7 row_left">
														<input type="text" class="form-control" name="password" onchange="checkIpStart(this)" id="startIP" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: 182.111.111.234" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')" >
													</div>
												</div>
												<div class="form-group has-error" style="display: none;" id="startIPDiv">
													<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="StartIPError"></label>
												</div>
												<!-- end IP -->
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input">
														<span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_END_IP")%>：</label>
													<div class="col-sm-7 row_left">
														<input type="text" class="form-control" name="password" oninput="checkIpEnd(this)" id="endIP" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: 182.111.111.234" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')" >
													</div>
												</div>
												<div class="form-group has-error" style="display: none;" id="endIPDiv">
													<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="endIPError"></label>
												</div>
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("SHIELDING_TIME")%>：</label>
													<div class="col-md-7 row_left">
														<select class="form-control" id="user_status2" name="UserStatus">
															<option value="1"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_DAY")%></option>
															<option value="2"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_WEEK")%></option>
															<option value="3"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_MONTH")%></option>
															<option value="4"><%=Config.message.get("BLACK_USER_SHIELDING_PERMANENT")%></option>
														</select>
													</div>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"><span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_SELECT_POLICY")%>：</label>
													<div class="col-md-7 row_left">
														<form role="form">
															<div class="form-group" style="margin-left: 0px;margin-right: 0px">
																<input type="hidden" value="" name="PolicyId"/>
																<input class="form-control" id="policy_select2" onclick="showSelectPolicy(this)" oninput="search_policy(this)"/>
																<select id="policyinfo2" multiple class="form-control">
																</select>
															</div>
														</form>
													</div>
												</div>
												<div class="form-group has-error" style="display: none;" id="error_policy2">
													<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="errorMsgPolicy2"><%=Config.message.get("BLACK_PLEASE_SELECT_POLICY")%></label>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button data-dismiss="modal" class="btn btn-default" type="button" onclick="clearInfo()"><%=Config.message.get("CANCEL")%></button>
										<button type="button" class="btn btn-primary" data-dismiss="modal" id="createBlackIPs"><%=Config.message.get("SAVE")%></button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--添加dialog end-->
				<!--deletedialog-->
				<div class="modal fade" id="box-config-sysnews" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
					data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">×</button>
								<h4 class="modal_title"><%=Config.message.get("ARE_YOU_SURE_DELETE")%></h4>
							</div>
							<div class="modal-footer modal_line">
								<input id="newsLog_delete_id" type="hidden" value="0"/>
								<button data-dismiss="modal" class="btn btn-default"
									type="button"><%=Config.message.get("CANCEL")%></button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" onclick="del()"><%=Config.message.get("SURE")%></button>
							</div>
						</div>
					</div>
				</div>
				<!--deletedialog end-->
				<div class="tables" class="row tab-pane fade in active">
					<div class="col-md-12 row_left">
						<!-- BOX -->
						<div class="box border purple">
							<table
								class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("BLACK_USER_SORT")%></th>
										<th><%=Config.message.get("BLACK_USER_USER_NUMBER_OR_IP")%></th>
										<th><%=Config.message.get("BLACK_USER_SHIELDING_POLICY")%></th>
										<th><%=Config.message.get("BLACK_USER_SHIELDING_CYCLE")%></th>
										<th><%=Config.message.get("BLACK_USER_CREATE_USER")%></th>
										<th><%=Config.message.get("BLACK_USER_CREATE_TIME")%></th>
										<th><%=Config.message.get("BLACK_USER_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach items="${result.LIST}" var="list" varStatus="v">
											<tr>
												<td>${v.count}</td>
												<td>${list.FilterUser}</td>
												<td>${list.PolicyName}</td>
												<td><c:if test="${list.UserStatus eq 1}"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_DAY")%></c:if> <c:if
														test="${list.UserStatus eq 2}"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_WEEK")%></c:if> <c:if
														test="${list.UserStatus eq 3}"><%=Config.message.get("BLACK_USER_SHIELDING_ONE_MONTH")%></c:if> <c:if
														test="${list.UserStatus eq 4}"><%=Config.message.get("BLACK_USER_SHIELDING_PERMANENT")%></c:if></td>
												<td><c:if test="${list.UserName eq ''}"><%=Config.message.get("BLACK_LISL_SYSTEM")%></c:if>
													<c:if test="${list.UserName ne null}">${list.UserName}</c:if>
												</td>
												<td>${list.CreateTime}</td>
												<td><c:forEach var="rv"
														items="${MenuRelation.MenuRelation}">
														<c:if
															test="${rv.menuId == 13 and rv.delete_permission != 0}">
															<a class="btn btn-default config" type="button"
																data-toggle="modal" href="#box-config-sysnews"
																title="delete" onclick="deleteBlackUser(this)"
																rel="${list.UserId}" param1="${list.FilterUser}"
																param2="${list.PolicyId}" param3="${list.UserStatus}"><i
																class="fa fa-trash-o"></i></a>
														</c:if>
													</c:forEach></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp"><jsp:param
											value="blacklist/userBlacklist?UserStatus=${result.UserStatus}&FilterUser=${result.FilterUser}&showStatus=${result.showStatus}"
											name="actionName" /></jsp:include>
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
	var data_po = [];
	$(function(){
		$("#policyinfo").hide();
		$("#policyinfo1").hide();
		$("#policyinfo2").hide();
		$.ajax({
			url: "blacklist/showPolicy",
			type: "post",
			success: function(data){
				var list=data.LIST;
				var obje = {};
				obje.Id = '-1';
				obje.name = '<%=Config.message.get("BLACK_USER_ALL_POLICY")%>';
				data_po.push(obje);
				$("#policy_select").attr("status","-1");
				$("#policy_select").val("<%=Config.message.get("BLACK_USER_ALL_POLICY")%>");
				$("#policy_select1").attr("status","-1");
				$("#policy_select1").val("<%=Config.message.get("BLACK_USER_ALL_POLICY")%>");
				$("#policy_select2").attr("status","-1");
				$("#policy_select2").val("<%=Config.message.get("BLACK_USER_ALL_POLICY")%>");
				$("#policyinfo").empty();
				$("#policyinfo1").empty();
				$("#policyinfo2").empty();
				var op="<option value='-1'><%=Config.message.get("BLACK_USER_ALL_POLICY")%></option>";
				$("#policyinfo").append(op);
				$("#policyinfo1").append(op);
				$("#policyinfo2").append(op);
				for(var i=0;i<list.length;i++){
					var objf = {};
					objf.Id = list[i].PolicyId;
					objf.name = list[i].PolicyName;
					data_po.push(objf);
					var option="<option value='"+list[i].PolicyId+"'>"+list[i].PolicyName+"</option>";
					$("#policyinfo").append(option);
					$("#policyinfo1").append(option);
					$("#policyinfo2").append(option);
				}
				$("#policy_select").next().find("option").each(function(){
					$(this).dblclick(function(){
						var p_Id = $(this).val();
						var p_name = $(this).html();
						$("#policy_select").val("");
						$("#policy_select").attr("status",p_Id);
						$("#policy_select").val(p_name);
						$("#policy_select").prev().val(p_Id);
					});
				});
				$("#policy_select1").next().find("option").each(function(){
					$(this).dblclick(function(){
						var p_Id = $(this).val();
						var p_name = $(this).html();
						$("#policy_select1").val("");
						$("#policy_select1").attr("status",p_Id);
						$("#policy_select1").val(p_name);
						$("#policy_select1").prev().val(p_Id);
					});
				});
				$("#policy_select2").next().find("option").each(function(){
					$(this).dblclick(function(){
						var p_Id = $(this).val();
						var p_name = $(this).html();
						$("#policy_select2").val("");
						$("#policy_select2").attr("status",p_Id);
						$("#policy_select2").val(p_name);
						$("#policy_select2").prev().val(p_Id);
					});
				});
			}
		});
	});

	function showSelectPolicy(obj){
		$(obj).val("");
		$(obj).attr("status","");
		$(obj).next().html("");
		$.each(data_po,function(index, content){
			var option="<option value='"+content.Id+"'>"+content.name+"</option>";
			var $option = $(option);
			$(obj).next().append($option);
		});
		$(obj).next().find("option").each(function(){
			$(this).dblclick(function(){
				var p_Id = $(this).val();
				var p_name = $(this).html();
				$(obj).val("");
				$(obj).attr("status",p_Id);
				$(obj).val(p_name);
				$(obj).prev().val(p_Id);
				$(obj).next().hide();
			});
		});
		$(obj).next().show();
	}

	function find(){
		 var UserStatus = $("#UserStatus").val();
		 var FilterUser = $("#exampleInputSearch").val().trim();
		 if(UserStatus==0){
			 load_page('${basePath}blacklist/userBlacklist?FilterUser='+encodeURIComponent(FilterUser));
		 }else{
			 load_page('${basePath}blacklist/userBlacklist?FilterUser='+encodeURIComponent(FilterUser)+'&UserStatus='+UserStatus);
		 }
	}

function deleteBlackUser(obj){
	var newsId=$(obj).attr('rel');
	var filterUser = $(obj).attr("param1");
	var policyId = $(obj).attr("param2");
	var userStatus = $(obj).attr("param3");
	$('#newsLog_delete_id').val(newsId);
	$('#newsLog_delete_id').attr("arg1",filterUser);
	$('#newsLog_delete_id').attr("arg2",policyId);
	$('#newsLog_delete_id').attr("arg3",userStatus);
}

function del(rel){
	var UserId=$("#newsLog_delete_id").val();
	var filterUser=$("#newsLog_delete_id").attr("arg1");
	var policyId=$("#newsLog_delete_id").attr("arg2");
	var userStatus=$("#newsLog_delete_id").attr("arg3");
	//alert(UserId+'---'+filterUser+'---'+policyId+'---'+userStatus);
	$.ajax({
	   url: "blacklist/delUser",
	   type: "post",
	   data: {"UserId":UserId,"filterUser":filterUser,"policyId":policyId,"userStatus":userStatus},
	   dataType:"json",
	   success: function(data){
		   window.confirm(data.desc);
		   setTimeout(function(){load_page('blacklist/userBlacklist')},300);
		   //load_page('blacklist/userBlacklist');
	   }
	});
}

	function search_policy(obj){
		$(obj).next().show();
		$(obj).prev().val("");
		$(obj).attr("status","");
		$("#error_policy").hide();
		$("#error_policy1").hide();
		$("#error_policy2").hide();
		var reg = $(obj).val().trim();
		$(obj).next().html("");
		$.each(data_po,function(index, content){
			if((content.name.indexOf(reg))!=-1){
				var option="<option value='"+content.Id+"'>"+content.name+"</option>";
				var $option = $(option);
				$(obj).next().append($option);
			}
		});
		$(obj).next().find("option").each(function(){
			$(this).dblclick(function(){
				var p_Id = $(this).val();
				var p_name = $(this).html();
				$(obj).val("");
				$(obj).attr("status",p_Id);
				$(obj).val(p_name);
				$(obj).prev().val(p_Id);
				$(obj).next().hide();
			});
		});
	}


/*function showPolicy(){
	$.ajax({
	   url: "blacklist/showPolicy",
	   type: "post",
	   success: function(data){
		   var list=data.LIST;
		   var obje = {};
		   obje.Id = '-1';
		   obje.name = 'All policies';
		   data_po.push(obje);
		   $("#policyinfo").empty();
		   $("#policyinfo1").empty();
		   $("#policyinfo2").empty();
		   var op="<option value='-1'>All policies </option>";
		   $("#policyinfo").append(op);
		   $("#policyinfo1").append(op);
		   $("#policyinfo2").append(op);
		   for(var i=0;i<list.length;i++){
			   var objf = {};
			   objf.Id = list[i].PolicyId;
			   objf.name = list[i].PolicyName;
			   data_po.push(objf);
			   var option="<option value='"+list[i].PolicyId+"'>"+list[i].PolicyName+"</option>";
			   $("#policyinfo").append(option);
			   $("#policyinfo1").append(option);
			   $("#policyinfo2").append(option);
		   }
		   console.log(data_po);
	   }
	});
}*/

function showFileName(){
    var filepath = $("#lefile").val().trim();
	$("#import").empty();
	$("#import").text(filepath);
	$("#errordiv").hide();
    $("#userName").val("");
}

function showFileNameIP(){
	var filepath = $("#lefile1").val().trim();
	$("#import1").empty();
	$("#import1").text(filepath);
	$("#errordiv1").hide();
	$("#userIP").val("");
}

//根据IP段 加黑名单
$("#createBlackIPs").click(function(){
	if($("#startIPDiv").is(':visible') || $("#endIPDiv").is(':visible')){
		return false;
	}
	var startIP = $("#startIP").val().trim();
	var endIP = $("#endIP").val().trim();
	var UserStatus = $("#user_status2").val();
	var PolicyId=$("#policy_select2").attr("status");
	if(startIP==""){
		$("#StartIPError").html("<%=Config.message.get("PLEASE_INPUT_START_IP")%>");
		$("#startIPDiv").show();
		return;
	}else{
		$("#startIPDiv").hide();
	}
	if(endIP==""){
		$("#endIPError").html("<%=Config.message.get("PLEASE_INPUT_END_IP")%>");
		$("#endIPDiv").show();
		return;
	}else{
		$("#endIPDiv").hide();
	}
	if(PolicyId==undefined || PolicyId==""){
		$("#error_policy2").show();
		return false;
	}
	if($("#startIPDiv").is(':visible') || $("#endIPDiv").is(':visible')){
		return false;
	}
	$.ajax({
		url: "blacklist/createBlackIPs",
		type: "post",
		data:"startIP="+startIP+"&endIP="+endIP+"&UserStatus="+UserStatus+"&PolicyId="+PolicyId,
		success: function(data){
			window.confirm(data.desc);
			setTimeout(function(){load_page('blacklist/userBlacklist')},300);
		},
		error:function(){
			window.confirm("error!");
		}
	});
});

//Add用户IP黑名单
$("#createBlackIP").click(function(){
	if($("#errordiv1").is(':visible')){
		return false;
	}
	var FilterUser = $("#userIP").val().trim();
	var UserStatus = $("#user_status1").val();
	var PolicyId=$("#policy_select1").attr("status");
	var filepath = $("#lefile1").val().trim();
	if(filepath==""){
		if (FilterUser==""){
			$("#errordiv1").show();
			$("#errorMsgIP").html("<%=Config.message.get("PLEASE_INPUT_USER_IP")%>");
			return false;
		}else{
			$("#errordiv1").hide();
		}
		if(PolicyId==undefined || PolicyId==""){
			$("#error_policy1").show();
			return false;
		}
		if($("#errordiv1").is(':visible')){
			return false;
		}
		$.ajax({
			url: "blacklist/createBlackUser",
			type: "post",
			data:"FilterUser="+FilterUser+"&UserStatus="+UserStatus+"&PolicyId="+PolicyId,
			success: function(data){
				window.confirm(data.desc);
				setTimeout(function(){load_page('blacklist/userBlacklist')},300);
			},
			error:function(){
				window.confirm("error!");
			}
		});
	}else{
		var index=filepath.lastIndexOf(".");
		var txt=filepath.substring(index+1,filepath.length).toUpperCase();
		if (txt!="TXT"){
			//window.confirm("only support txt file!");
			$("#lefile1").val("");
			$("#import1").text("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
			return;
		}
		if(PolicyId==undefined || PolicyId==""){
			$("#error_policy1").show();
			return false;
		}
		$("#form1").ajaxSubmit(function(data){
			window.confirm(data.desc);
			setTimeout(function(){load_page('blacklist/userBlacklist')},300);
		});
	}
});

//创建黑名单 --用户号码
$("#createBlack").click(function(){
	var FilterUser=$("#userName").val().trim();
	var UserStatus=$("#user_status").val();
	var PolicyId=$("#policy_select").attr("status");
	var filepath = $("#lefile").val().trim();
	if(filepath==""){
		if (FilterUser==""){
			$("#errordiv").show();
			$("#errorMsg").html("<%=Config.message.get("PLEASE_INPUT_USER_NUMBER")%>");
			return false;
		}else{
			$("#errordiv").hide();
		}
		if(PolicyId==undefined || PolicyId==""){
			$("#error_policy").show();
			return false;
		}
		if(FilterUser!=""){
			$.ajax({
				url: "blacklist/createBlackUser",
				type: "post",
				data:"FilterUser="+FilterUser+"&UserStatus="+UserStatus+"&PolicyId="+PolicyId,
				success: function(data){
					window.confirm(data.desc);
					setTimeout(function(){load_page('blacklist/userBlacklist')},300);
				},
				error:function(){
					window.confirm("error!");
				}
			});
		}
	}else{
		var index=filepath.lastIndexOf(".");
		var txt=filepath.substring(index+1,filepath.length).toUpperCase();
		if (txt!="TXT"){
			//window.confirm("only support txt file!");
			$("#lefile").val("");
			$("#import").text("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
			return false;
		}
		if(PolicyId==undefined || PolicyId==""){
			$("#error_policy").show();
			return false;
		}
		$("#form").ajaxSubmit(function(data){
			window.confirm(data.desc);
			setTimeout(function(){load_page('blacklist/userBlacklist')},300);
		});
	}
});
function clearInfo(){
	$("#lefile").val("");
	$("#userName").val("");
	$("#errordiv").hide();
	$("#error_policy").hide();
	$("#import").text("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
	$("#lefile1").val("");
	$("#userIP").val("");
	$("#errordiv1").hide();
	$("#error_policy1").hide();
	$("#import1").text("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
	$("#startIP").val("");
	$("#endIP").val("");
	$("#endIPDiv").hide();
	$("#error_policy2").hide();
}
function checkIp(obj){
	var IP = $(obj).val();
	var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	if(!reg.test(IP)){
		$("#errorMsgIP").html("<%=Config.message.get("IP_NOT_CORRECT")%>");
		$("#errordiv1").show();
		return;
	}else{
		$("#errordiv1").hide();
		var ipArry = new Array();
		ipArry = IP.split(".");
		if(ipArry[2]>255 || ipArry[2]<0 || ipArry[3]>255 || ipArry[3]<0){
			$("#errorMsgIP").html("<%=Config.message.get("IP_NOT_CORRECT_BETWEEN")%>");
			$("#errordiv1").show();
			return ;
		}else{
			$("#errordiv1").hide();
		}
	}
}
function clearError(){
	$("#errordiv").hide();
}

function checkIpStart(obj){
	var IP = $(obj).val();
	var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	if(!reg.test(IP)){
		$("#StartIPError").html("<%=Config.message.get("IP_NOT_CORRECT")%>");
		$("#startIPDiv").show();
		return;
	}else{
		$("#startIPDiv").hide();
		ipArry = IP.split(".");
		if(ipArry[2]>255 || ipArry[2]<0 || ipArry[3]>255 || ipArry[3]<0){
			$("#StartIPError").html("<%=Config.message.get("IP_NOT_CORRECT_BETWEEN")%>");
			$("#startIPDiv").show();
			return;
		}else{
			$("#startIPDiv").hide();
		}
	}
}
function checkIpEnd(obj){
	var startIp = $("#startIP").val().trim();
	var IP = $(obj).val();
	var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	if(!reg.test(IP)){
		$("#endIPError").html("<%=Config.message.get("IP_NOT_CORRECT")%>");
		$("#endIPDiv").show();
		return false;
	}else {
		$("#endIPDiv").hide();
		ipArry = IP.split(".");
		if (ipArry[2] > 255 || ipArry[2] < 0 || ipArry[3] > 255 || ipArry[3] < 0) {
			$("#endIPError").html("<%=Config.message.get("IP_NOT_CORRECT_BETWEEN")%>");
			$("#endIPDiv").show();
			return;
		} else {
			$("#endIPDiv").hide();
			if (startIp != "") {
				var startIpSplit = new Array()
				startIpSplit = startIp.split(".");
				var endIpSplit = new Array();
				endIpSplit = IP.split(".");
				console.log(startIpSplit);
				console.log(endIpSplit);
				if (endIpSplit[0] != startIpSplit[0]) {
					$("#endIPError").html("<%=Config.message.get("END_FIRST_START_FIRST")%>"); //结束IP的第一和第二段位必须与起始IP的一致
					$("#endIPDiv").show();
					return;
				} else {
					$("#endIPDiv").hide();
					if (endIpSplit[1] != startIpSplit[1]) {
						$("#endIPError").html("<%=Config.message.get("END_SECOND_START_SECOND")%>"); //结束IP的第一和第二段位必须与起始IP的一致
						$("#endIPDiv").show();
						return;
					} else {
						$("#endIPDiv").hide();
						if (endIpSplit[2] < startIpSplit[2]) {
							$("#endIPError").html("<%=Config.message.get("END_THIRD_START_THIRD")%>"); //结束IP的第三段位不能小于起始IP的第三段位
							$("#endIPDiv").show();
							return;
						} else {
							$("#endIPDiv").hide();
							if (endIpSplit[2] == startIpSplit[2]) {
								if (endIpSplit[3] <= startIpSplit[3]) {
									$("#endIPError").html("<%=Config.message.get("END_FORTH_START_FORTH")%>"); //结束IP的第四段位必须大于起始IP的第四段位
									$("#endIPDiv").show();
									return;
								} else {
									$("#endIPDiv").hide();
								}
							} else {
								$("#endIPDiv").hide();
							}
						}
					}
				}
			}
		}
	}
}

</script>