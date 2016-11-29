<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("HOME")%></a></li>
								<li><a href="#"><%=Config.message.get("ROLE_SYSTEM_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("ROLE_CREATE_ROLE")%></h3>
							</div>
							<div class="description"><%=Config.message.get("CREATE_ROLE_CREATE")%></div>
						</div>
					</div>
				</div>
				<!--创建角色 start-->
				<div class="divide-20"></div>
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box">
							<div class="box-body msg_font">
								<div class="tabbable header-tabs user-profile">
									<div class="tab-content">
										<!-- OVERVIEW -->
										<div class="tab-pane fade in active" id="pro_overview">
											<div class="box-body form">
												<form id="wizForm" action="#" class="form-horizontal">
													<div class="wizard-form">
														<div class="wizard-content">
															<div class="tab-content">
																<div class="tab-pane active" id="account">
																	<div class="divide-20"></div>
																	<div class="form-group msg_font">
																		<label class="control-label col-md-3 lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ROLE_NAME")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" id="roleName"
																				name="roleName" onchange="checkRoleName(this)">
																			<span class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group msg_font">
																		<label class="control-label col-md-3 lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ROLE_DESCRIPTION")%>：</label>
																		<div class="col-md-4">
																			<textarea placeholder="" class="form-control"
																				id="roleDesc" name="roleDesc" cols="8" rows="5"
																				onchange="checkRoleDesc(this)"></textarea>
																			<span class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group msg_font">
																		<label class="control-label col-md-3 lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ROLE_SELECT_PERMISSIONS")%>：</label>
																		<!--设置权限tab-->
																		<div class="col-md-8">
																			<!-- BOX1 -->
																			<div class="box purple tables">
																				<table
																					class="datatable table   table-bordered table-hover data_table">
																					<thead>
																						<tr>
																							<th><input type="checkbox" checked="checked"></th>
																							<th><%=Config.message.get("ROLE_TEMPLATE_NAME")%></th>
																							<th><%=Config.message.get("ROLE_TEMPLATE_CHILD")%></th>
																							<th><%=Config.message.get("ROLE_SEARCH_VIEW_EXPORT")%></th>
																							<th><%=Config.message.get("ROLE_ADD")%></th>
																							<th><%=Config.message.get("ROLE_EDIT")%></th>
																							<th><%=Config.message.get("ROLE_DELETE")%></th>
																							<th><%=Config.message.get("ROLE_AUDIT")%></th>
																							<th><%=Config.message.get("ROLE_GLOBAL")%></th>
																						</tr>
																					</thead>
																					<tbody>
																						<tr>
																							<td><input type="checkbox" checked="checked"></td>
																							<td><%=Config.message.get("PUSH_VIEW")%></td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked" name="pushView" value="1"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="5"><input type="checkbox" checked="checked"></td>
																							<td rowspan="5"><%=Config.message.get("PUSH_MANAGE")%></td>
																							<td><%=Config.message.get("POLICY_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="policyManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="policyManage" value="2"></td>
																							<td><input type="checkbox" checked="checked"
																								name="policyManage" value="4"></td>
																							<td><input type="checkbox" checked="checked"
																								name="policyManage" value="8"></td>
																							<td><input type="checkbox" checked="checked"
																								name="policyManage" value="16">
																							</td>
																							<td><input type="checkbox" checked="checked"
																								name="policyManage" value="32"></td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("TEMPLATE_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="templateManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="templateManage" value="2"></td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked"
																								name="templateManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("PAGE_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="pageManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="pageManage" value="2"></td>
																							<td><input type="checkbox" checked="checked"
																								name="pageManage" value="4"></td>
																							<td><input type="checkbox" checked="checked"
																								name="pageManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("GROUP_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="groupManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="groupManage" value="2"></td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked"
																								name="groupManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("AREA_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="areaManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="areaManage" value="2"></td>
																							<td><input type="checkbox" checked="checked"
																								name="areaManage" value="4"></td>
																							<td><input type="checkbox" checked="checked"
																								name="areaManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="4"><input type="checkbox" checked="checked"></td>
																							<td rowspan="4"><%=Config.message.get("OPERATE_MANAGE")%></td>
																							<td><%=Config.message.get("PUSH_REPORT_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="pushReportManage" value="1"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("USER_COMPLAINT_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="userComplaintManage" value="1"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("USER_BLACK_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="userBlackManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="userBlackManage" value="2"></td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked"
																								name="userBlackManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("URL_BLACK_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="urlBlackManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="urlBlackManage" value="2"></td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked"
																								name="urlBlackManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="5"><input type="checkbox" checked="checked"></td>
																							<td rowspan="5"><%=Config.message.get("ROLE_SYSTEM_MANAGEMENT")%></td>
																							<td><%=Config.message.get("ROLE_MANAGE")%></td>
																							<td><input type="checkbox" name="roleManage"
																								value="1"></td>
																							<td><input type="checkbox" name="roleManage"
																								value="2"></td>
																							<td><input type="checkbox" name="roleManage"
																								value="4"></td>
																							<td><input type="checkbox" name="roleManage"
																								value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("ACCOUNT_MANAGE")%></td>
																							<td><input type="checkbox" name="accountManage" value="1"></td>
																							<td><input type="checkbox" name="accountManage" value="2"></td>
																							<td><input type="checkbox" name="accountManage" value="4"></td>
																							<td><input type="checkbox" name="accountManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("MONITOR_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked" name="monitorManage" value="1"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<%--<tr>
																							<td><%=Config.message.get("CUSTOMER_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked" name="customerManage" value="1"></td>
																							<td><input type="checkbox" checked="checked" name="customerManage" value="2"></td>
																							<td><input type="checkbox" checked="checked" name="customerManage" value="4"></td>
																							<td><input type="checkbox" checked="checked" name="customerManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>--%>
																						<tr>
																							<td><%=Config.message.get("LOG_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked" name="logManage" value="1"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("MESSAGE_MANAGE")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="newsManage" value="1"></td>
																							<td><input type="checkbox" checked="checked"
																								name="newsManage" value="2"></td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked"
																								name="newsManage" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="3"><input type="checkbox" checked="checked"></td>
																							<td rowspan="3"><%=Config.message.get("ACCOUNT_SET")%></td>
																							<td><%=Config.message.get("ACCOUNT_INFO")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="accountInfo" value="1"></td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked"
																								name="accountInfo" value="4"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("SYS_MESSAGES")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="sysNews" value="1"></td>
																							<td>--</td>
																							<td>--</td>
																							<td><input type="checkbox" checked="checked"
																								name="sysNews" value="8"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<%--<tr>
                                                                                        <td>账户安全</td>
                                                                                        <td><input type="checkbox" checked="checked" name="accountSafe" value="1"></td>
                                                                                        <td>--</td>
                                                                                        <td><input type="checkbox" checked="checked" name="accountSafe" value="4"></td>
                                                                                        <td>--</td>
                                                                                        <td>--</td>
                                                                                        <td>--</td>
                                                                                    </tr>--%>
																						<tr>
																							<td><%=Config.message.get("OPERATE_RECORD")%></td>
																							<td><input type="checkbox" checked="checked"
																								name="operateRecord" value="1"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																					</tbody>
																				</table>
																			</div>
																			<!-- BOX1 END -->
																		</div>
																		<!--设置权限tab-->
																	</div>
																</div>
																<div class="row">
																	<div class="col-md-10">
																		<div class="col-md-offset-6" id="btn_group">
																			<a href="javascript:;" class="btn btn-md btn-default"
																				onclick="load_page('roleManage.html')"><%=Config.message.get("CANCEL")%></a> <a
																				href="javascript:;"
																				class="btn btn-md btn-primary btn_left"
																				onclick="createRole()"><%=Config.message.get("SAVE")%></a>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
										<!-- /CONTENT-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--创建角色 end-->
				<!-- /PAGE HEADER -->
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

$(function(){
    $("table").find("tr:first input").click(function(){
        if ($(this).is(':checked')) {
            $(this).parents("table").find("tr").each(function () {
                $(this).find("td:first input").prop("checked", "checked");
                var rowspan = $(this).find("td:first").attr("rowspan");
                if(rowspan==undefined){
                    $(this).find("input").prop("checked","checked");
                }else{
                    $(this).find("input").prop("checked","checked");
                    $(this).nextAll('tr').each(function(i){
                        $(this).find("input").each(function(){
                            $(this).prop("checked","checked");
                        });
                        if(i==(Number(rowspan)-2)){
                            return false;
                        }
                    });
                }
            });
        }else {
            $(this).parents("table").find("tr").each(function () {
                $(this).find("td:first input").prop("checked", false);
                var rowspan = $(this).find("td:first").attr("rowspan");
                if(rowspan==undefined){
                    $(this).find("input").prop("checked",false);
                }else{
                    $(this).find("input").prop("checked",false);
                    $(this).nextAll('tr').each(function(i){
                        $(this).find("input").each(function(){
                            $(this).prop("checked",false);
                        });
                        if(i==(Number(rowspan)-2)){
                            return false;
                        }
                    });
                }
            });
        }
    });

    $("table").find("tbody tr").each(function(){
        $(this).find("td:first input").on('click',checkedBoxss);
    });
});

//全选/全不选该模块下子模块的checkbox
function checkedBoxss(){
    if($(this).is(':checked')){
        var rowspan = $(this).parent().attr("rowspan");
        if(rowspan==undefined){
            $(this).parents("tr").each(function(){
                $(this).find('input').prop("checked","checked");
            });
        }else{
            $(this).parents("tr").each(function(){
                $(this).find('input').prop("checked","checked");
            });
            $(this).parents('tr').nextAll('tr').each(function(i){
                $(this).find("input").each(function(){
                    $(this).prop("checked","checked");
                });
                if(i==(Number(rowspan)-2)){
                    return false;
                }
            });
        }
    }else{
        var rowspan = $(this).parent().attr("rowspan");
        if(rowspan==undefined){
            $(this).parents("tr").each(function(){
                $(this).find('input').prop("checked",false);
            });
        }else{
            $(this).parents("tr").each(function(){
                $(this).find('input').prop("checked",false);
            });
            $(this).parents('tr').nextAll('tr').each(function(i){
                $(this).find("input").each(function(){
                    $(this).prop("checked",false);
                });
                if(i==(Number(rowspan)-2)){
                    return false;
                }
            });
        }
    }
}

    //校验角色名称唯一
    function checkRoleName(obj){
        var roleName = $(obj).val().trim();
        if(roleName.length<3 || roleName.length>10){
            $(obj).next().show();
            $(obj).next().html("<%=Config.message.get("ROLE_PLEASE_C")%>");
            return;
        }else{
            $(obj).next().hide();
        }
        $.ajax({
            url:"role/check",
            type:"post",
            data:{"roleName":roleName},
            dataType:"json",
            success:function(data){
                if (data.code == 1){
                    $(obj).next().hide();
                    $(obj).next().html("");
                }else if(data.code == 0){
                    $(obj).next().show();
                    $(obj).next().html("<%=Config.message.get("ROLE_THE_ROLE_NAME")%>");
                }else{
                    $(obj).next().show();
                    $(obj).next().html("error");
                }
            }
        });
    }

    function checkRoleDesc(obj){
        var roleDesc = $("#roleDesc").val().trim();
        if(roleDesc.length > 30){
            $(obj).next().show();
            $(obj).next().html("<%=Config.message.get("ROLE_THE_ROLE_DESCRIPTION")%>");
            return;
        }else{
            $(obj).next().hide();
        }
    }

    //创建角色
    function createRole(){
        var roleName = $("#roleName").val().trim();
        var roleDesc = $("#roleDesc").val().trim();
        if(roleName==""){
            $("#roleName").next().html("<%=Config.message.get("THE_ROLE_NAME_NOT")%>");
            $("#roleName").next().show();
            return;
        }
        if(roleDesc==""){
            $("#roleDesc").next().html("<%=Config.message.get("THE_ROLE_DESC_NOT")%>");
            $("#roleDesc").next().show();
            return;
        }
        if($("#roleName").next().is(':visible') || $("#roleDesc").next().is(':visible')){
            return;
        }
        var params = [];
        params = $("form").serialize();
        params = decodeURIComponent(params,true);
        $.ajax({
            url:"role/create",
            type:"post",
            data:params,
            success:function(data){
                if(data.code==0){
                    window.confirm(data.desc);
                }else{
                    window.confirm("<%=Config.message.get("ROLE_CREATE_SUCCESS")%>!");
                }
                load_page('roleManage.html');
            },
            error:function(){
                window.confirm("false");
                load_page('roleManage.html');
            }
        });
    }

</script>