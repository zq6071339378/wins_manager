<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>

<!--deletedialog-->
<div class="modal fade" id="box-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal_title"><%=Config.message.get("ARE_YOU_SURE_DELETE")%></h4>
			</div>
			<div class="modal-footer modal_line">
				<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onclick="sureDeleteRole()"><%=Config.message.get("LOGIN_LOGIN")%><%=Config.message.get("SURE")%></button>
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("ROLEMANAGE_HOME")%></a></li>
								<li><a href="#"><%=Config.message.get("ROLEMANAGE_SYSTEMMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("ROLEMANAGE_CHARACTERMANAGERMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("ROLEMANAGE_ROLMANAGEMENTCHECK")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-5 form_input login-box">
							<form>
								<div class="form-group col-md-12 row_left search_icon">
									<a href="javascript:void(0)" onclick="searchRole()"><i
										class="fa fa-search"></i></a> <input type="search"
										id="exampleInputSearch" value="${result.roleName}"
										class="form-control" placeholder="<%=Config.message.get("ROLE_PLEASE_INPUT_ROLE_NAME")%>">
								</div>
							</form>
						</div>
						<div class="from-group col-md-2 row_left">
							<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
								<c:if test="${rv.menuId==16 && rv.add_permission!=0}">
									<button onclick="load_page('role/createRole.html')"
										class="btn btn-danger" type="button"><%=Config.message.get("ROLEMANAGE_CREATCHARACTER")%></button>
								</c:if>
							</c:forEach>
						</div>
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
										<th><%=Config.message.get("ROLEMANAGE_SORT")%></th>
										<th><%=Config.message.get("ROLEMANAGE_STATE")%></th>
										<th><%=Config.message.get("ROLEMANAGE_CHARACTERNAME")%></th>
										<th><%=Config.message.get("ROLEMANAGE_CHARACTERDESCRIPTION")%></th>
										<th><%=Config.message.get("ROLEMANAGE_CREATACCOUNT")%></th>
										<th><%=Config.message.get("ROLEMANAGE_CREATTIME")%></th>
										<th><%=Config.message.get("ROLEMANAGE_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach var="role" items="${result.list}" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>
													<div class="checkbox checkbox-slider--b checkbox-slider-default check_radio">
														<label>
															<input status="${role.roleId}" type="checkbox"
															<c:if test="${role.roleStatus eq '2'}">checked</c:if>
															onclick="updateRoleStatus(this)"><span></span>
														</label>
													</div>
												</td>
												<td>${role.roleName}</td>
												<td>${role.roleDesc}</td>
												<td>${role.createName}</td>
												<td><fmt:formatDate value="${role.createTime}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td><c:forEach var="rv"
														items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId==16 && rv.see_permission!=0}">
															<a class="btn btn-default" href="javascript:void(0)"
																onclick="load_page('role/checkRole.html?roleId='+${role.roleId});"
																title="<%=Config.message.get("CHECK")%>"><i class="fa fa-eye"></i></a>
														</c:if>
													</c:forEach> <c:forEach var="rv" items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId==16 && rv.update_permission!=0}">
															<a class="btn btn-default" href="javascript:void(0)"
																onclick="load_page('role/editRole.html?roleId='+${role.roleId})"
																title="<%=Config.message.get("EDIT")%>"><i class="fa fa-pencil-square"></i></a>
														</c:if>
													</c:forEach> <c:forEach var="rv" items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId==16 && rv.delete_permission!=0}">
															<a class="btn btn-default config"
																onclick="deleteRole(this)" status="${role.roleId}"
																type="button" data-toggle="modal" href="#box-delete"
																title="<%=Config.message.get("DELETE")%>"><i class="fa fa-trash-o"></i></a>
														</c:if>
													</c:forEach></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<input type="hidden" id="sure_delete" value="" />
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param
											value="roleManage.html?roleName=${result.roleName}&showStatus=${result.showStatus}"
											name="actionName" />
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
    //查找账号信息
    function searchRole(){
        var roleName=$("#exampleInputSearch").val().trim();
        load_page('roleManage.html?roleName='+encodeURIComponent(roleName));
    }

    function deleteRole(obj){
        var roleId = $(obj).attr("status");
        $("#sure_delete").val(roleId);
    }
    //delete账号
    function sureDeleteRole(){
        var roleId = $("#sure_delete").val();
        $.ajax({
            url:"role/delete",
            method:"post",
            data:{'roleId':roleId},
            success:function(data){
                if(data.code==1){
                    window.confirm(data.desc);
                    load_page('roleManage.html');
                }else{
                    window.confirm("error！");
                    load_page('roleManage.html');
                }
            }
        });
    }

    function updateRoleStatus (obj){
        var bool = $(obj).prop("checked");
        var roleStatus = 0;
        var roleId = $(obj).attr("status");
        if(bool == true){
            roleStatus = 2;
        }else{
            roleStatus = 1;
        }
        $.ajax({
            url:"role/updateRoleStatus",
            type:"post",
            data:{"roleId":roleId,"roleStatus":roleStatus},
            dataType:"json",
            success:function(data){
                // window.confirm(data.desc);
            }
        });
    }

</script>
