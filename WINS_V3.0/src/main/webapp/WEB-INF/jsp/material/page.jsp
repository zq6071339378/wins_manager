 <%@page import="com.datacomo.wins.controller.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
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
								<li><i class="fa fa-home"></i> <a href="strategy.html"><%=Config.message.get("MATERIAL_MATERIAL_MANAGEMENT")%></a>
								</li>
								<li><a href="pageManage.html"><%=Config.message.get("PAGE_PAGE_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("PAGE_PAGE_MANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("PAGE_MANAGEMENT_CREATE_PC_PAD_PAGE")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-7 row_left">
							<div class="form-group btn-group">
								<a href="javascript:;"
									onclick="load_page('material/page/listView?pageName=${condition.pageName}')"
									class="btn btn-default <c:if test="${empty condition.tType}">active</c:if>"><%=Config.message.get("PAGE_ALL")%></a>
								<a href="javascript:;"
									onclick="load_page('material/page/listView?pageName=${condition.pageName}&tType=1')"
									class="btn btn-default <c:if test="${condition.tType eq 1}">active</c:if>"><%=Config.message.get("PAGE_PHONE")%></a>
								<a href="javascript:;"
									onclick="load_page('material/page/listView?pageName=${condition.pageName}&tType=2')"
									class="btn btn-default <c:if test="${condition.tType eq 2}">active</c:if>">PC</a>
								<a href="javascript:;"
									onclick="load_page('material/page/listView?pageName=${condition.pageName}&tType=3')"
									class="btn btn-default <c:if test="${condition.tType eq 3}">active</c:if>">PAD</a>
							</div>
						</div>
						<div class="col-md-5 form_input login-box">
							<form>
								<div class="form-group col-md-10 row_left search_icon">
									<a href="javascript:;" onclick="searchMaterial(this);"><i
										class="fa fa-search"
										style="position: absolute !important; right: 30px; top: 2px;"></i></a>
									<input type="search" placeholder="<%=Config.message.get("PAGE_INPUT_PAGE_NAME")%>" class="form-control"
										value="" id="materialSearch" />
								</div>
								<div class="from-group col-md-2">
									<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
										<c:if test="${rv.menuId==8 && rv.add_permission!=0}">
											<a onclick="load_page('material/page/createPage')"
												href="#material/page/listView" class="btn btn-danger"
												type="button"><%=Config.message.get("PAGE_ADD")%></a>
										</c:if>
									</c:forEach>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!--预览dialog end-->
				<div class="divide-12"></div>
				<div id="myTabContent" class="tab-content">
					<!--页面管理TABLE1-->
					<div class="row tab-pane fade in active" id="page_tab1">
						<div class="col-md-12">
							<!-- BOX -->
							<div class="box border purple">
										<table
											class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th><%=Config.message.get("PAGE_SORT")%></th>
													<th><%=Config.message.get("PAGE_NAME")%></th>
													<th><%=Config.message.get("PAGE_REGION")%></th>
													<th><%=Config.message.get("PAGE_BELONG_TEMPLATE")%></th>
													<th><%=Config.message.get("PAGE_SHOW_TYPE")%></th>
													<th><%=Config.message.get("PAGE_TYPE")%></th>
													<th><%=Config.message.get("PAGE_PAGE_PATH")%></th>
													<th><%=Config.message.get("PAGE_CREATE_USER")%></th>
													<th><%=Config.message.get("PAGE_CREATE_TIME")%></th>
													<th><%=Config.message.get("PAGE_OPERATION")%></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="page" items="${result.materials}"
													varStatus="status">
													<tr>
														<td>${status.count}</td>
														<td>${page.pageName}</td>
														<td>${page.provinceCity}</td>
														<td>${page.templateName}</td>
														<td>
															<c:if test="${page.showType eq 1}">Toolbar</c:if>
															<c:if test="${page.showType eq 2}"><%=Config.message.get("CREATE_POLICY_FLOAT")%></c:if>
															<c:if test="${page.showType eq 3}"><%=Config.message.get("CREATE_POLICY_PLACE_ON_BOTH_SIDES")%></c:if>
															<c:if test="${page.showType eq 4}"><%=Config.message.get("HANGING_ON_THE_EDGE")%></c:if>
															<c:if test="${page.showType eq 5}"><%=Config.message.get("PUT_ON_THE_BACK")%></c:if>
															<c:if test="${page.showType eq 6}"><%=Config.message.get("CREATE_POLICY_REPLACE")%></c:if>
														 </td>
														<td>
															<c:if test="${page.terminalType eq 1}"><%=Config.message.get("PAGE_PHONE")%></c:if>
															<c:if test="${page.terminalType eq 2}">PC</c:if>
															<c:if test="${page.terminalType eq 3}">PAD</c:if>
														</td>
														<td>${page.pageURL}</td>
														<td>${page.userName}</td>
														<td>${page.createTime}</td>
														<td>
															<a class="btn btn-default config" type="button" data-toggle="modal" href="javascript:;" onclick="showPage(this,${page.terminalType})" rel="<%=Config.message.get("POLICYSERVICE")%>_${page.pageURL}" title="<%=Config.message.get("PAGE_SCAN")%>">
																<i class="fa fa-bullseye"></i>
															</a>
															<!--  
															<a class="btn btn-default" href="pageModify.html" title="修改"><i class="fa fa-pencil-square"></i></a>
															-->
															<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
																<c:if
																	test="${rv.menuId == 8 and rv.delete_permission != 0}">
																	<a class="btn btn-default config" type="button" href="javascript:;" onclick="deletePage(${page.pageId});" title="<%=Config.message.get("PAGE_DELETE")%>">
																		<i class="fa fa-trash-o"></i>
																	</a>
																</c:if>
															</c:forEach>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									<%-- </c:otherwise> --%>
								<%-- </c:choose> --%>
								<div class="row">
									<div class="dataTables_footer clearfix">
										<jsp:include page="../layout/pagination.jsp"><jsp:param
												value="material/page/listView?pageName=${pageName}&tType=${result.tType}"
												name="actionName" /></jsp:include>
									</div>
								</div>
							</div>
							<!-- /BOX -->
						</div>
					</div>
				</div>
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
<!--main_container-->
<!-- /PAGE HEADER -->
<!--删除dialog-->
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
				<input type="hidden" name="pageId" id="delete-page-id" />
				<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
				<button type="button" class="btn btn-primary"
					id="button-delete-page-ok"><%=Config.message.get("SURE")%></button>
			</div>
		</div>
	</div>
</div>
<!--删除dialog end-->
<!--预览dialog-->
<div class="modal fade" id="box-preview" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
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
								style="display: block; background: url(img/erweima.jpg) no-repeat; height: 160px;">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="form-group msg_font form_group">
						<div for="password-input" class="col-md-12 control-label">
							<div class="hover-content animated fadeOut"
								style="display: block; background: url(img/mobile.png) no-repeat; width: 242px; height: 480px; background-size: 100% 100%; margin: 0 auto;">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer modal_line"></div>
		</div>
	</div>
</div>
<!--预览dialog end-->
<script>
  	function searchMaterial(target){
  		var txt = $("#materialSearch").val().trim();
  		load_page("material/page/listView?pageName="+encodeURIComponent(txt));
  	}
  	function deletePage(id){
		$('#box-delete').modal('show');//on('shown',function(){});
		$("#box-delete").find("input[name='pageId']").val(id);
  	}
  	$(function(){
  		$("#materialSearch").keydown(function(evt){
			var txt = $("#materialSearch").val();
			if(evt.keyCode==13 && txt.length>0){
				load_page("material/page/listView?pageName="+txt);
			}
		});
		
		
		$("#button-delete-page-ok").click(function(){
			var tid=$("#box-delete").find("input[name='pageId']").val();
			$.ajax({
				url:'material/page/delete',
				type:'post',
				data:{'pageId':tid},
				success:function(data){
					$('#box-delete').modal('hide');
					var txt = $("#materialSearch").val();
					setTimeout(function(){load_page("material/page/listView?pageName="+txt)},300);
				}
			});
		});
  	})
  </script>
  <script>
  function showPage(_obj,type){
		var _url=$(_obj).attr("rel");
		if(type==1){
			window.open(_url,'','height=500,width=430,scrollbars=yes,status =yes');
		}else{
			window.open(_url,'','scrollbars=yes,status =yes');
		}
		//_url='http://125.94.0.178:8888/policy_show.html?url='+_url; 
		
	}
  </script>