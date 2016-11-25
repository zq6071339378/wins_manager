<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="${basePath }js/jquery.form.js"></script>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
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
								<li><a href="template.html"><%=Config.message.get("PUSH_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("GROUP_GROUP_MANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("GROUP_MANAGEMENT_ADD_GROUP_BY")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-5 form_input login-box">
							<form>
								<div class="form-group col-md-10 row_left search_icon">
									<a href="javascript:void(0)" onclick="searchGroup();"><i
										class="fa fa-search"></i></a> <input type="search"
										id="groupSearch" class="form-control" placeholder="<%=Config.message.get("GROUP_PLEASE_INPUT_GROUP_NAME")%>"
										value="${groupName}">
								</div>
								<div class="from-group col-md-2 row_left">
									<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
										<c:if test="${rv.menuId==5 && rv.add_permission!=0}">
											<a class="btn btn-danger config" type="button"
												data-toggle="modal" href="#box-group"><%=Config.message.get("GROUP_ADD")%></a>
										</c:if>
									</c:forEach>

								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--添加dialog-->
				<div class="modal fade" id="box-group" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true"
					data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("GROUP_ADD_GROUP")%></h4>
							</div>
							<div class="panel-body">
								<form action="${basePath}group/addGroup" method="post"
									enctype="multipart/form-data" class="form-horizontal"
									id="jForm">
									<div class="col-md-8 pull-left">
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("GROUP_NAME")%>：</label>
											<div class="col-sm-7 row_left">
												<input type="text" class="form-control" name="groupName"
													placeholder="<%=Config.message.get("GROUP_NAME")%>" id="groupName" onclick="preventSubmit();">
											</div>
										</div>
										<div class="form-group has-error">
											<label class="col-md-4"></label> <label class="control-label"
												for="inputWarning" id="groupNameMsg"></label>
										</div>
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("GROUP_IMAGE")%>：</label>
											<input type="file" style="display: none" id="img"
												onchange="uploadImg();" name="pic">
											<div class="col-md-1 row_left">
												<div class="input-append">
													<a class="btn btn-info"
														onclick="$('input[id=img]').click();"><%=Config.message.get("GROUP_UPLOAD")%>&nbsp;&nbsp;</a>
												</div>
											</div>
										</div>
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("GROUP_USER_NUMBER")%>：</label>
											<div class="col-md-5 row_left">
												<textarea rows="5" cols="10" name="mulUser"
													class="form-control" placeholder="<%=Config.message.get("PLEASE_ENTER_A_CARRIAGE")%>"
													id="mulUser"></textarea>
											</div>
											<input name="userAccount" id="userAccount" type="hidden" />
										</div>
										<div class="form-group">
											<label for="password-input" class="col-md-4 control-label"><%=Config.message.get("GROUP_IMPORT")%>：</label>
											<div class="col-md-2 row_left">
												<input type="file" style="display: none" id="lefile"
													onchange="Mimport();" name="multiImport"> <input
													type="hidden" name="path" id="path">
												<div class="input-append">
													<a onclick="$('input[id=lefile]').click();"
														class="btn btn-info"><%=Config.message.get("GROUP_SCAN")%>&nbsp;&nbsp;</a> <span id="import"></span>
												</div>
											</div>
											<!--  <span id="mulUserMsg" style="margin-left: 10px;color: red"></span> -->
											<div class="col-md-1">
												<span class="span_fonts"><i
													class="fa fa-question-circle" title="<%=Config.message.get("GROUP_THIS_OPERATION_WILL")%>"></i></span>
											</div>
										</div>
										<div class="form-group">
											<label for="password-input" class="col-md-4 control-label"></label>
											<div class="col-md-5 row_left">
												<span class="span_fonts"><%=Config.message.get("GROUP_ONLY_SUPPORT_TXT")%></span>
											</div>
											<div class="form-group has-error">
												<label class="col-md-4"></label> <label
													class="control-label" for="inputWarning" id="mulUserMsg"></label>
											</div>
										</div>
									</div>
									<div class="col-md-4 pull-right">
										<img id="preview" src="img/erweima.png"
											style="display: block; width: 100px; height: 100px;">
									</div>
									<div class="col-md-4 pull-right">
										<span class="span_fonts"><%=Config.message.get("GROUP_SIZE")%>：100*100</span>
									</div>
									<div class="col-md-4 pull-rightSSSSSSS">
										<span class="span_fonts"><%=Config.message.get("GROUP_FORM")%>：png、jpg</span>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button data-dismiss="modal" class="btn btn-default"
									type="button"><%=Config.message.get("CANCEL")%></button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" id="saveGroup"><%=Config.message.get("SAVE")%></button>
							</div>
						</div>
					</div>
				</div>
				<!--添加dialog end-->
				<!--Deletedialog-->
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
								<input id="newsLog_delete_id" type="hidden" value="0" />
								<button data-dismiss="modal" class="btn btn-default"
									type="button"><%=Config.message.get("CANCEL")%></button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" onclick="del()"><%=Config.message.get("SURE")%></button>
							</div>
						</div>
					</div>
				</div>
				<!--Deletedialog end-->
				<!--群租管理TABLE-->
				<div class="row tab-pane fade in active  tables">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box border purple">
							<table
								class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("GROUP_SORT")%></th>
										<th><%=Config.message.get("GROUP_NAME")%></th>
										<th><%=Config.message.get("GROUP_NUMBER_OF_USER")%></th>
										<th><%=Config.message.get("GROUP_UPLOAD_USER")%></th>
										<th><%=Config.message.get("GROUP_UPLOAD_TIME")%></th>
										<th><%=Config.message.get("GROUP_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach items="${result.groupLists}" var="obj"
											varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>${obj.groupName}</td>
												<td>${obj.memberNum}</td>
												<td>${obj.userName}</td>
												<td><fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td><c:forEach var="rv" items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId==5 and rv.see_permission!=0}">
															<a class="btn btn-default" href="${basePath }group/exportGroupMem?groupId=${obj.groupId }" title="<%=Config.message.get("GROUP_EXPORT")%>"><i class="fa fa-share-square-o"></i></a>
														</c:if>
													</c:forEach> <c:forEach var="rv" items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId == 5 and rv.delete_permission != 0}">
															<a class="btn btn-default config" type="button" data-toggle="modal" href="#box-config-sysnews" title="<%=Config.message.get("PAGE_DELETE")%>"
																onclick="var newsId=$(this).attr('rel');$('#newsLog_delete_id').val(newsId);"
																rel="${obj.groupId }"><i class="fa fa-trash-o"></i></a>
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
											value="policyGroup.html?groupName=${obj.groupName}"
											name="actionName" /></jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<!--Group managementTABLE END-->
				<!--<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="box-config" class="modal fade in" style="display: block;">
			<div class="modal-dialog">
			  <div class="modal-content">
				<div class="modal-header">
				  <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				  <h4 class="modal-title">Box Settings</h4>
				</div>
				<div class="modal-body">
				  Here goes box setting content.
				</div>
				<div class="modal-footer">
				  <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>
				  <button class="btn btn-primary" type="button">Save changes</button>
				</div>
			  </div>
			</div>
	  	</div>-->
				<!-- <div class="footer-tools">
			<span class="go-top">
				<i class="fa fa-chevron-up"></i> Top
			</span>
		</div> -->
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<script type="text/javascript">
	function preventSubmit(){
		$('#groupName').keypress(function(e){
			if(e.keyCode==13){
                e.preventDefault();
            }
	})
	}
	
	function uploadImg(){
		options={
				url:"${basePath}group/uploadPic",
				method:"post",
				dataType:"json",
				success:function(data){
					$("#preview").attr('src',data.result.url);
					$("#path").val(data.result.path);
				}
		};
		$("#jForm").ajaxSubmit(options);
	};
	function searchGroup(){
		var groupName=$("#groupSearch").val();
		load_page('policyGroup.html?groupName='+encodeURIComponent(groupName));
	}
	function del(rel){
		var groupId=$("#newsLog_delete_id").val();
		$.ajax({
			url:"${basePath}group/delete",
			method:"post",
			data:{groupId:groupId},
			success:function(data){
				if(data.code==1){
					load_page('policyGroup.html');
				}
			}
		});
	}
	$("#saveGroup").click(function(){
		/* var groupName=$("#groupName").val();
		var userAccount=$("#mulUser").val();
		var path=$("#path").val();
		$.ajax({
			url:"${basePath}group/addGroup",
			method:"post",
			data:{groupName:groupName,userAccount:userAccount,path:path},
			success:function(data){
				if(data.code==1){
					load_page('policyGroup.html');
				}
			}
		}); */
		var groupName=$("#groupName").val();
		if(groupName.trim().length==0){
			$("#groupNameMsg").show();
			$("#groupNameMsg").html("<%=Config.message.get("GROUP_PLEASE_INPUT_GROUP_NAME")%>");
			return false;
		}
		else{
			$("#groupNameMsg").hide();
		}
		if(groupName.trim().length>20){
			$("#groupNameMsg").show();
			$("#groupNameMsg").html("<%=Config.message.get("NO_MORE_THAN_WORDS")%>");
			return false;
		}
		else{
			$("#groupNameMsg").hide();
		}
		$("#userAccount").attr("value",$("#mulUser").val());
		var v=$("#import").html();
		var s=v.substr(v.length-4)
		if($("#mulUser").val().trim().length==0 && s.length==0){
			$("#mulUserMsg").show();
			$("#mulUserMsg").html("<%=Config.message.get("GROUP_PLEASE_INPUT_OR_IMPORT")%>");
			return false;
		}
		else{
			$("#mulUserMsg").hide();
		}
		if(s){
			if(s !=".txt"){
				alert("<%=Config.message.get("THE_FILE_ERROR_TXT")%>");
				return false;
			}
		}
		
		$("#jForm").ajaxSubmit(function(data){
			
			if(data.result== -1){
				alert("error");
			}
			if(data.code==1){
				load_page('policyGroup.html');
			}
		});
	});
	function Mimport(){
		var v=$("#lefile").val();
		$("#import").text(v);
	}
</script>