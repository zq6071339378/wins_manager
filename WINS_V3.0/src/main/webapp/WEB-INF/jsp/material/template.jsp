<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
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
							<!-- BREADCRUMBS -->
							<ul class="breadcrumb">
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("MATERIAL_MATERIAL_MANAGEMENT")%></a>
								</li>
								<li><a href="template.html"><%=Config.message.get("MATERIAL_TEMPLATE_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("MATERIAL_TEMPLATE_MANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("TEMPLATE_MANAGE_CREATE")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-7 row_left">
							<div class="form-group btn-group">
								<a href="javascript:;"
									onclick="load_page('material/template/listView?templateName=${condition.templateName}')"
									class="btn btn-default <c:if test="${empty condition.tType}">active</c:if> "><%=Config.message.get("TEMPLATE_MANAGE_ALL")%></a>
								<a href="javascript:;"
									onclick="load_page('material/template/listView?templateName=${condition.templateName}&tType=1')"
									class="btn btn-default <c:if test="${condition.tType eq 1}">active</c:if>"><%=Config.message.get("TEMPLATE_MANAGE_PHONE")%></a>
								<a href="javascript:;"
									onclick="load_page('material/template/listView?templateName=${condition.templateName}&tType=2')"
									class="btn btn-default <c:if test="${condition.tType eq 2}">active</c:if>">PC</a>
								<a href="javascript:;"
									onclick="load_page('material/template/listView?templateName=${condition.templateName}&tType=3')"
									class="btn btn-default <c:if test="${condition.tType eq 3}">active</c:if>">PAD</a>
							</div>
						</div>
						<div class="col-md-5 form_input login-box">
							<form>
								<div class="form-group col-md-10 row_left search_icon">
									<a href="javascript:;" onclick="searchMaterial(this);"><i
										class="fa fa-search"
										style="position: absolute !important; right: 30px; top: 2px;"></i></a>
									<input type="search" placeholder="<%=Config.message.get("TEMPLATE_MANAGE_INPUT_TEMPLATE_NAME")%>" class="form-control"
										value="" id="materialSearch" />
								</div>
								<div class="from-group col-md-2">
									<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
										<c:if test="${rv.menuId==7 && rv.add_permission!=0}">
											<a class="btn btn-danger config" type="button"
												data-toggle="modal" href="#box-template"><%=Config.message.get("TEMPLATE_MANAGE_ADD")%></a>
										</c:if>
									</c:forEach>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="divide-12"></div>
				<div id="myTabContent" class="tab-content">
					<!--模板 TABLE1-->
					<div class="row tab-pane fade in active" id="templete_tab1">
						<div class="col-md-12">
							<!-- BOX -->
							<div class="box border purple">
								<c:choose>
									<c:when test="${condition.tType  eq 1}">
										<div class="form-group msg_font form_group">
											<c:forEach var="template" items="${result.materials}"
												varStatus="status">
												<div class="col-md-3">
													<a class="fancybox thumbnail" href="javascript:;"
														onclick="preview(this);" title="Scan"
														data-cover="${template.templateImage}"
														data-martix-path="${template.martixPath}"><img
														src="${template.templateImage}"
														onerror="default_error_img(this,'img/gallery/Mac.png');" /><span>
													</span></a>
													<div class="caption">
														<h5>${template.templateName}</h5>
														<div>
															<p class="pull-left col-md-5">${template.userName}</p>
															<p class="">${template.createTime}</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</c:when>
									<c:when test="${condition.tType eq 2}">
										<div class="form-group msg_font form_group">
											<c:forEach var="template" items="${result.materials}"
												varStatus="status">
												<div class="col-md-3">
													<a class="fancybox thumbnail" href="javascript:;"
														onclick="preview(this);" title="Scan"
														data-cover="${template.templateImage}"
														data-martix-path="${template.martixPath}"><img
														src="${template.templateImage}"
														onerror="default_error_img(this,'img/gallery/Mac.png');" /><span>
													</span></a>
													<div class="caption">
														<h5>${template.templateName}</h5>
														<div>
															<p class="pull-left col-md-5">${template.userName}</p>
															<p class="">${template.createTime}</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</c:when>
									<c:when test="${condition.tType  eq 3}">
										<div class="form-group msg_font form_group">
											<c:forEach var="template" items="${result.materials}"
												varStatus="status">
												<div class="col-md-3">
													<a class="fancybox thumbnail" href="javascript:;"
														onclick="preview(this);" title="Scan"
														data-cover="${template.templateImage}"
														data-martix-path="${template.martixPath}"><img
														src="${template.templateImage}"
														onerror="default_error_img(this,'img/gallery/Mac.png');" /><span>
													</span></a>
													<div class="caption">
														<h5>${template.templateName}</h5>
														<div>
															<p class="pull-left col-md-5">${template.userName}</p>
															<p class="">${template.createTime}</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</c:when>
									<c:otherwise>
										<table
											class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th><%=Config.message.get("TEMPLATE_MANAGE_SORT")%></th>
													<th><%=Config.message.get("TEMPLATE_MANAGE_TEMPLATE_NAME")%></th>
													<th><%=Config.message.get("TEMPLATE_MANAGE_SHOW_TYPE")%></th>
													<th><%=Config.message.get("TEMPLATE_MANAGE_TEMPLATE_TYPE")%></th>
													<th><%=Config.message.get("TEMPLATE_MANAGE_UIPLOAD_USER")%></th>
													<th><%=Config.message.get("TEMPLATE_MANAGE_UPLOAD_TIME")%></th>
													<th><%=Config.message.get("TEMPLATE_MANAGE_OPERATION")%></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="template" items="${result.materials}"
													varStatus="status">
													<tr>
														<td>${status.count}</td>
														<td>${template.templateName}</td>
														<td>
															<c:if test="${template.showType eq 1}">Toolbar</c:if>
															<c:if test="${template.showType eq 2}"><%=Config.message.get("CREATE_POLICY_FLOAT")%></c:if>
															<c:if test="${template.showType eq 3}"><%=Config.message.get("CREATE_POLICY_PLACE_ON_BOTH_SIDES")%></c:if>
															<c:if test="${template.showType eq 4}"><%=Config.message.get("HANGING_ON_THE_EDGE")%></c:if>
															<c:if test="${template.showType eq 5}"><%=Config.message.get("PUT_ON_THE_BACK")%></c:if>
															<c:if test="${template.showType eq 6}"><%=Config.message.get("CREATE_POLICY_REPLACE")%></c:if>
														</td>
														<td>
															<c:if test="${template.terminalType eq 1}"><%=Config.message.get("TEMPLATE_MANAGE_PHONE")%></c:if>
															<c:if test="${template.terminalType eq 2}">PC</c:if>
															<c:if test="${template.terminalType eq 3}">PAD</c:if>
														</td>
														<td>${template.userName}</td>
														<td>${template.createTime}</td>
														<td><c:forEach var="rv" items="${MenuRelation.MenuRelation}">
																<c:if test="${rv.menuId==7 and rv.see_permission!=0}">
																	<a class="btn btn-default config" type="button"
																		href="javascript:;" onclick="preview(this);"
																		title="<%=Config.message.get("CHECK")%>" data-cover="${template.templateImage}"
																		data-martix-path="${template.martixPath}"><i
																		class="fa fa-bullseye"></i></a>
																</c:if>
															</c:forEach> <c:forEach var="rv" items="${MenuRelation.MenuRelation}">
																<c:if
																	test="${rv.menuId == 7 and rv.delete_permission != 0}">
																	<a class="btn btn-default config" type="button"
																		href="javascript:;"
																		onclick="deleteTemplate(${template.templateId});"
																		title="<%=Config.message.get("DELETE")%>"><i class="fa fa-trash-o"></i></a>
																</c:if>
															</c:forEach></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:otherwise>
								</c:choose>
								<div class="row">
									<div class="dataTables_footer clearfix">
										<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="material/template/listView?templateName=${templateName}&tType=${result.tType}" name="actionName" />
										</jsp:include>
									</div>
								</div>
							</div>
							<!-- /BOX -->
						</div>
					</div>
				</div>
				<div class="footer-tools"></div>
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->

<!--创建dialog-->
<div class="modal fade" id="box-template" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal-title"><%=Config.message.get("TEMPLATE_MANAGE_ADD_TEMPLATE")%></h4>
			</div>
			<div class="panel-body">
				<form action="material/template/upload" method="post"
					enctype="multipart/form-data" class="form-horizontal"
					id="form-create-template">
					<div class="form-group msg_font form_group">
						<label class="col-md-3 control-label" for="password-input"><span
							class="star">*</span><%=Config.message.get("TEMPLATE_MANAGE_TEMPLATE_NAME")%>：</label>
						<div class="col-sm-3 row_left">
							<input type="text" class="form-control" name="templateName" id="templateName"
								placeholder="<%=Config.message.get("TEMPLATE_MANAGE_INPUT_TEMPLATE_NAME")%>" required='required' maxlength="20"/>
						</div>
					<div class="has-error" style="display: none;"
											id="errordiv">
											<label class="col-md-4"></label> <label class="control-label"
												for="inputWarning" id="errorMsg" style="line-height:9px;"></label>
										</div>
					<div class="form-group msg_font form_group">
					</div>
						<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("TEMPLATE_MANAGE_TEMPLATE_TYPE")%>：</label>
						<div class="col-md-2 row_left">
							<select class="form-control" name="terminalType">
								<option value="1" selected><%=Config.message.get("TEMPLATE_MANAGE_PHONE")%></option>
								<option value="2">PC</option>
								<option value="3">PAD</option>
							</select>
						</div>
					</div>
					<div class="form-group msg_font form_group">
						<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("TEMPLATE_MANAGE_SHOW_TYPE")%>：</label>
						<div class="col-md-3 row_left">
							<select class="form-control" name="showType">
								<option value="1" selected>toolbar</option>
								<option value="2"><%=Config.message.get("CREATE_POLICY_FLOAT")%></option>
								<option value="3"><%=Config.message.get("CREATE_POLICY_PLACE_ON_BOTH_SIDES")%></option>
								<option value="4"><%=Config.message.get("HANGING_ON_THE_EDGE")%></option>
								<option value="5"><%=Config.message.get("PUT_ON_THE_BACK")%></option>
								<option value="6"><%=Config.message.get("CREATE_POLICY_REPLACE")%></option>
							</select>
						</div>
					</div>
					<div class="form-group msg_font form_group">
						<label class="col-md-3 control-label" for="password-input"><span
							class="star">*</span><%=Config.message.get("TEMPLATE_MANAGE_UPLOAD_TEMP")%>：</label>
						<div class="col-md-7 row_left" style="position: relative;">
							<input type="file" name="upload" id="materialefile"
								onchange="changeMaterialUrl(this);"
								style="position: absolute; height: 30px; width: 180px; opacity: 0;">
							<div class="input-append">
								<input type="hidden" id="materiale-template-path"
									name="templateImage" required='required' /> <input
									type="hidden" id="materiale-martix-path" name="martixPath"
									required='required' /> <input type="hidden"
									id="materiale-template-url" name="templateUrl"
									required='required' /> <input type="text" placeholder="<%=Config.message.get("TEMPLATE_MANAGE_SCAN")%>"
									style="height: 30px;" class="search-query" id="photoCover"
									required='required' /> <a onclick="updateMaterialUrl();"
									class="btn btn-info"><%=Config.message.get("TEMPLATE_MANAGE_UPLOAD")%>&nbsp;&nbsp;</a>
							</div>
						</div>
					</div>
					<div class="form-group msg_font form_group">
						<label class="col-md-3 control-label" for="password-input"></label>
						<div class="col-md-3 row_left">
							<span><%=Config.message.get("ONLY_SUPPORT_ZIP_FILE")%></span>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button data-dismiss="modal" class="btn btn-default" type="button"
					id="button-create-template-cancel"><%=Config.message.get("CANCEL")%></button>
				<button type="button" class="btn btn-primary"
					id="button-create-template-ok"><%=Config.message.get("SAVE")%></button>
			</div>
		</div>
	</div>
</div>
<!--创建dialog end-->
<!--deletedialog-->
<div class="modal fade" id="box-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal_title"><%=Config.message.get("ARE_YOU_SURE_DELETE")%></h4>
			</div>
			<div class="modal-footer modal_line">
				<input type="hidden" name="templateId" id="delete-template-id" />
				<button data-dismiss="modal" class="btn btn-default" type="button"
					id="button-delete-template-cancel"><%=Config.message.get("CANCEL")%></button>
				<button type="button" class="btn btn-primary"
					id="button-delete-template-ok"><%=Config.message.get("SURE")%></button>
			</div>
		</div>
	</div>
</div>
<!--deletedialog end-->
<!--Scandialog-->
<div class="modal fade" id="box-pagePreview" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-preview">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<div class="col-md-4">
					<div class="form-group msg_font form_group">
						<label for="password-input" class="col-md-12 control-label"><%=Config.message.get("VIEW_ON_THE_DEVICE")%></label>
					</div>
					<div class="form-group msg_font form_group">
						<div for="password-input" class="col-md-12 row_left control-label">
							<div class="hover-content animated fadeOut"
								style="display: block; height: 160px;">
								<!-- background: url(img/erweima.jpg) no-repeat; -->
								<img alt="" src="" id="id-dialog-murl"
									onerror="default_error_img(this,'img/erweima.jpg');"
									height="160px" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group msg_font form_group">
						<div for="password-input" class="col-md-12 control-label">
							<div class="hover-content animated"
								style="display: block; width: 242px; height: 480px; margin: 0 auto;">
								<img alt="" src="" id="id-dialog-cover" width="242px" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer modal_line"></div>
		</div>
	</div>
</div>
<script src="${basePath}js/jquery.form.js"></script>
<script>
  	function searchMaterial(target){
  		var txt = $("#materialSearch").val().trim();
  			load_page("material/template/listView?templateName="+encodeURIComponent(txt));
  	}
  	function deleteTemplate(id){
		$('#box-delete').modal('show');//on('shown',function(){});
		$("#box-delete").find("input[name='templateId']").val(id);
  	}
  	$(function(){
  		$("#materialSearch").keydown(function(evt){
			var txt = $("#materialSearch").val().trim();
			if(evt.keyCode==13 && txt.length>0){
				setTimeout(function(){load_page("material/template/listView?templateName="+txt)},300);
			}
		});
		$("#button-create-template-ok").click(function(){
			var templateName=$("#templateName").val();
			var data = $("#form-create-template").serialize();
			var valateflag = true;
			$("#form-create-template").find("input[required='required']").each(function(){
				if($(this).val().trim()==""){valateflag=false;$(this).focus();return false;}
			});
			if(valateflag){
				$.ajax({
					url:'material/template/create',
					type:'post',
					data:data,
					success:function(data){
						$('#box-template').modal('hide');
						var txt = $("#materialSearch").val();
						setTimeout(function(){load_page("material/template/listView?templateName="+txt)},300);
					}
				});
			}else{
				if (templateName==""){
					$("#errordiv").show();
					$("#errorMsg").html("<%=Config.message.get("TEMPLATE_MANAGE_INPUT_TEMPLATE_NAME")%>!");
					return false;
				}else{
					$("#errordiv").hide();
					alert("<%=Config.message.get("PLEASE_UPLOAD_THE_TEMPLATE")%>!");
				}
			}
		});
		
		$("#button-delete-template-ok").click(function(){
			var tid=$("#box-delete").find("input[name='templateId']").val();
			$.ajax({
				url:'material/template/delete',
				type:'post',
				data:{'templateId':tid},
				success:function(data){
					$('#box-delete').modal('hide');
					var txt = $("#materialSearch").val();
					setTimeout(function(){load_page("material/template/listView?templateName="+txt)},300);
					
				}
			});
		});
		
  	});
  </script>

<script type="text/javascript">
	function changeMaterialUrl(_obj){
  		var _filePatha=$("#materialefile").val();
  		$("#photoCover").val(_filePatha);
  	}
   	//updata materiaimage
   	function updateMaterialUrl(){
   		var _filePath=$("#materialefile").val();
   		_filePath=_filePath.substr(_filePath.lastIndexOf('.'),_filePath.length).toLowerCase();
   		if(_filePath==".zip"){
   			$("#form-create-template").ajaxSubmit(function(data){
	   			if(data!=null  && data.code==1){
	   				$('#materiale-template-path').val(data.result.templateImage);
	   				$('#materiale-template-url').val(data.result.templateUrl);
	   				$('#materiale-martix-path').val(data.result.martixPath);
	   				alert("Succeed！");
	   			}else{
	   				alert("<%=Config.message.get("UPLOAD_FAILED")%>");
	   			}
   			});
   		}else{
   			alert("<%=Config.message.get("FILE_FORMAT_ERROE")%>");
   		}
   	}
	function default_error_img(target,src){
		target.src=src;
	}
	
	function preview(target){
		var murl=$(target).data("martix-path");
		var cover = $(target).data("cover");
		$("#id-dialog-murl").attr("src",murl);
		$("#id-dialog-cover").attr("src",cover);
		$('#box-pagePreview').modal('show');
	}
</script>