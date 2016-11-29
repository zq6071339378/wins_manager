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
									<a href="javascript:void(0);"><%=Config.message.get("CUSTOMER_MANAGEMENT")%></a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("CUSTOMER_MANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("CUSTOMER_MANAGEMENT_DETAIL")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-4 form_input login-box">
							<form>
								<div class="form-group col-md-12 row_left search_icon">
									<a href="javascript:void(0);"  onclick="searchCustomer()"><i class="fa fa-search"></i></a>
									<input type="search" id="exampleInputSearch" class="form-control" placeholder="<%=Config.message.get("CUSTOMER_PLEASE_INPUT_NAME")%>">
								</div>
							</form>
						</div>
						<div class="from-group col-md-2 col-md-offset-1 row_left">
						<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
							<c:if test="${rv.menuId==19 && rv.add_permission!=0}">
							<button onclick="load_page('customer/createCustomer.html')" class="btn btn-danger" type="button"><%=Config.message.get("CUSTOMER_ADD")%></button>
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
							<table class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("CUSTOMER_SORT")%></th>
										<th><%=Config.message.get("CONTRACT_NO")%></th>
										<th><%=Config.message.get("CUSTOMER_COMPANY_NAME")%></th>
										<th><%=Config.message.get("CUSTOMER_COMPANY_ADDRESS")%></th>
										<th><%=Config.message.get("CUSTOMER_NAME")%></th>
										<th><%=Config.message.get("CUSTOMER_OFFICE")%></th>
										<th><%=Config.message.get("CUSTOMER_CREATE_USER")%></th>
										<th><%=Config.message.get("CUSTOMER_CREATE_TIME")%></th>
										<th><%=Config.message.get("CLINET_BALANCE")%></th>
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
										<td>${customer.customerAddress}</td>
										<td>${customer.customerName}</td>
										<td>${customer.customerOffice}</td>
										<td>${customer.createName}</td>
										<td><fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${customer.accountBalance}</td>
										<td>
									<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
										<c:if test="${rv.menuId==19 and rv.see_permission!=0}">
											<a class="btn btn-default" href="javascript:void(0)" onclick="load_page('customer/checkCustomer.html?customerId='+${customer.customerId});" title="Check">
												<i class="fa fa-eye"></i>
											</a>
										</c:if>
									</c:forEach>
									<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
										<c:if test="${rv.menuId == 19 and rv.update_permission != 0}">
										<a class="btn btn-default" href="javascript:void(0)" onclick="load_page('customer/editCustomer.html?customerId='+${customer.customerId});" title="Edit">
											<i class="fa fa-pencil-square"></i>
										</a>
										</c:if>
									</c:forEach>
									<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
										<c:if test="${rv.menuId == 19 and rv.delete_permission != 0}">
										<a class="btn btn-default config" type="button" onclick="deleteCustomer(this)" data-toggle="modal" href="#box-delete" status="${customer.customerId}" title="delete">
											<i class="fa fa-trash-o"></i>
										</a>
										</c:if>
									</c:forEach>
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
										<jsp:param value="customer.html?searchCustomerName=${result.searchCustomerName}" name="actionName" />
									</jsp:include>
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
    //查找账号信息
    function searchCustomer(){
        var searchCustomerName=$("#exampleInputSearch").val();
        var reqUrl = 'customer.html?searchCustomerName='+searchCustomerName;
        addCityIdToUrl(document.getElementById("menu_25"),reqUrl);
    };

    function deleteCustomer(obj){
        var customerId = $(obj).attr("status");
        $("#sure_delete").val(customerId);
    }
    //delete账号
    function sureDelete(){
        var customerId = $("#sure_delete").val();
        $.ajax({
            url:"customer/delete",
            method:"post",
            data:{'customerId':customerId},
            success:function(data){
                window.confirm(data.desc);
                var menuUrl = $("#menu_25").attr("href");
                var reqUrl = 'customer.html';
                if(menuUrl.indexOf("?")>0){
                    var cityIdInfo = menuUrl.substring(menuUrl.indexOf("?")+1);
                    reqUrl += "&"+cityIdInfo;
                }
                load_page(reqUrl);
            }
        });
    }

    function addCityIdToUrl(obj,url){
        var menuUrl = $(obj).attr("href");
        if(menuUrl.indexOf("?")>0){
            var cityIdInfo = menuUrl.substring(menuUrl.indexOf("?")+1);
            url += "&"+cityIdInfo;
        }
        load_page(url);
    }

</script>