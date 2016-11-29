<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
								<li>
									<i class="fa fa-home"></i>
									<a href="index.html"><%=Config.message.get("HOME")%></a>
								</li>
								<li>
									<a href="#"><%=Config.message.get("CUSTOMER_MANAGEMENT")%></a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left">财务管理</h3>
							</div>
							<div class="description">Simple Tables with exclusive UI experience</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-3 form_input login-box">
							<form>
								<div class="form-group col-md-12 row_left search_icon">
									<a href="javascript:void(0);" onclick="searchCustomer()"><i class="fa fa-search"></i></a>
									<input type="search" id="exampleInputSearch" class="form-control" placeholder="请输入合同编号">
								</div>
							</form>
						</div>
						<div class="from-group col-md-2 row_left">
							<!--<button onclick="javascript:window.location.href='createAccount.html'" class="btn btn-danger" type="button">添加</button>-->
						</div>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--群租管理TABLE-->
				<div class="tables" class="row tab-pane fade in active">
					<div class="col-md-12 row_left">
						<!-- BOX -->
						<div class="box border purple">
							<table class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
								<tr>
									<th><%=Config.message.get("CUSTOMER_SORT")%></th>
									<th><%=Config.message.get("CONTRACT_NO")%></th>
									<th><%=Config.message.get("CUSTOMER_COMPANY_NAME")%></th>
									<th><%=Config.message.get("CUSTOMER_NAME")%></th>
									<th><%=Config.message.get("CLINET_BALANCE")%></th>
									<th><%=Config.message.get("CUSTOMER_CREATE_USER")%></th>
									<th><%=Config.message.get("CUSTOMER_CREATE_TIME")%></th>
									<th><%=Config.message.get("CUSTOMER_OPERATION")%></th>
								</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result.list}">
										<c:forEach var="customer" items="${result.list}" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>${customer.contractCode}</td>
												<td>${customer.customerCompany}</td>
												<td>${customer.customerName}</td>
												<td>${customer.accountBalance}</td>
												<td>${customer.createName}</td>
												<td><fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>
													<a class="btn btn-default" href="javascript:void(0);" onclick="load_page('customer/transactionRecord.html?customerId='+${customer.customerId})" title="查看记录"><i class="fa fa-eye"></i></a>
													<a class="btn btn-default" href="javascript:void(0)" onclick="load_page('customer/rechargeCustomer.html?customerId='+${customer.customerId});" title="Credit" >
														<i class="fa fa-credit-card"></i>
													</a>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="financialManage.html?contractCode=${result.contractCode}" name="actionName" />
									</jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
			</div><!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript">
    //查找账号信息
    function searchCustomer(){
        var contractCode=$("#exampleInputSearch").val();
        var reqUrl = 'financialManage.html?contractCode='+contractCode;
        addCityIdToUrl(document.getElementById("menu_29"),reqUrl);
    };

    function addCityIdToUrl(obj,url){
        var menuUrl = $(obj).attr("href");
        if(menuUrl.indexOf("?")>0){
            var cityIdInfo = menuUrl.substring(menuUrl.indexOf("?")+1);
            url += "&"+cityIdInfo;
        }
        load_page(url);
    }

</script>