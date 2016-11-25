<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.datacomo.wins.controller.Config" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		var day = date.getDate();
		if (day >= 0 && day <= 9) {
			day = "0" + day;
		}
		var fullYear = year + "-" + month + "-" + day;
		var birthday = $('#birthday').val();
		if (birthday == null) {
			$('#birthday').val(fullYear);
			birthday = fullday;
		}
		$('#birthday').daterangepicker({
			singleDatePicker : true,
			startDate : birthday,
		}, function() {
			dateSelect();
		});

	});
</script>

<!--删除dialog-->
<div class="modal fade" id="box-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal_title"><%=Config.message.get("ACCOUNT_SET_WHETHERTOCANCEL")%></h4>
			</div>
			<div class="modal-footer modal_line">
				<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("ACCOUNT_SET_CANCEL")%></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onclick="sureDelete()"><%=Config.message.get("ACCOUNT_SET_OK")%></button>
			</div>
		</div>
	</div>
</div>
<!--删除dialog end-->
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("POLICY_MANAGEMENT_HOME")%></a>
								</li>
								<li><a href="#"><%=Config.message.get("ACCOUNT_SET_ACCOUNTSET")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("ACCOUNT_SET_SYSTEMMESSAGE")%></h3>
							</div>
							<div class="description"><%=Config.message.get("ACCOUNT_SET_SYSTEMMESSAGECHECK")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12" style="padding-left: 0px;">
						<div class="col-md-2" style="padding-left: 0px;">
							<div class="col-md-6" style="padding-left: 0px;">
								<div class="input-group date">
									<input type="text" style="width: 200px;" value="${Create_Date}"
										class="form-control" id="birthday" readonly="readonly">
									<span class="input-group-addon"><span
										class="fa fa-calendar"></span> </span>
								</div>
							</div>
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
										<th><%=Config.message.get("ACCOUNT_SET_SORT")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_OPERATION_TIME")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_OPERATION_CONTENT")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_TYPE")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_OPERATION_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach var="news" items="${result.list}" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td><fmt:formatDate value="${news.Create_Time}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>${news.News_Content}</td>
												<c:if test="${news.News_Type==1}">
													<td>notification </td>
												</c:if>
												<c:if test="${news.News_Type==2}">
													<td>Sales promotion information</td>
												</c:if>
												<c:if test="${news.News_Type==3}">
													<td>action information</td>
												</c:if>
												<c:if test="${news.News_Type==4}">
													<td>security information</td>
												</c:if>
												<td><a class="btn btn-default config" type="button"
													onclick="deleteNews(this)" data-toggle="modal"
													href="#box-delete" status="${news.News_Id}" title="delete"><i
														class="fa fa-trash-o"></i></a></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<input type="hidden" id="sure_delete" value="" />
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="accountNews.html?Create_Date=${Create_Date}"
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
	function dateSelect() {
		var Create_Date = $("#birthday").val();
		load_page('accountNews.html?Create_Date=' + Create_Date);
	}

	//删除
	function deleteNews(obj) {
		var news_id = $(obj).attr("status");
		$("#sure_delete").val(news_id);
	}
	//确定删除
	function sureDelete() {
		var id = $("#sure_delete").val();
		if (confirm) {
			$.ajax({
				url : "${basePath}sysnews/delete",
				method : "post",
				data : {
					'news_id' : id
				},
				success : function(data) {
					if (data.code == 1) {
						setTimeout(function(){load_page('accountNews.html')},300);
					} else {
						alert("<%=Config.message.get("ACCOUNT_SET_FAILED")%>！")
					}
				}
			});
		}
	}
</script>