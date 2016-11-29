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
								<h3 class="content-title pull-left"><%=Config.message.get("CUSTOMER_ADD_CUSTOMER")%></h3>
							</div>
							<div class="description"><%=Config.message.get("CUSTOMER_CREATE_CUSTOMER_DETAIL")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<!--创建账户start-->
				<div class="row">
					<div class="col-md-12">
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
																	<div class="divide-40"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("CUSTOMER_NAME")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="customer_name" onchange="hideErrorSpan(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("CUSTOMER_COMPANY_NAME")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="customer_company" onchange="hideErrorSpan(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("CUSTOMER_COMPANY_ADDRESS")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="customer_address" onchange="hideErrorSpan(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																		<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("CONTRACT_NO")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="contract_no" onchange="hideErrorSpan(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																
																	
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("CUSTOMER_CONTACT_NUMBER")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="customer_phone" onchange="checkPhoneNum(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>

																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span class="star"></span>&nbsp;<%=Config.message.get("CUSTOMER_EMAIL")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="customer_email" onchange="checkEmail(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span class="star"></span>&nbsp;<%=Config.message.get("CUSTOMER_REMARKS")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="remarks">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																</div>
																<div class="divide-20"></div>
															</div>
														</div>
													</div>
												</form>
												<div class="row">
													<div class="col-md-6">
														<div id="btn_group" class="col-md-offset-7">
														<input type="hidden" id="pageTemp" value="${pagetemp }" /> 
															<c:if test="${pagetemp eq 1}"><a class="btn btn-md btn-default" href="javascript:void(0);" onclick="load_page('policy/createPolicy')"><%=Config.message.get("CANCEL")%></a></c:if>
															<c:if test="${pagetemp ne 1}"><a class="btn btn-md btn-default" href="javascript:void(0);" onclick="load_page('customer.html')"><%=Config.message.get("CANCEL")%></a></c:if>
															<a class="btn btn-md btn-primary btn_left" href="javascript:void(0);" onclick="addCustomer()"><%=Config.message.get("CUSTOMER_ADD")%></a>
														</div>
													</div>
												</div>
											</div>
										</div><!-- /CONTENT-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript">
    function checkName(obj) {
		var realName = $(obj).val().trim();
		if (realName.length < 2||  realName.length > 20) {
			$(obj).next().html("<%=Config.message.get("CUSTOMER_YOU_CAN_NOT_ENTER")%>");
			$(obj).next().show();
			return;
		} else {
			$(obj).next().hide();
		}
	}

	function checkPhoneNum(obj){
		var phoneNum = $(obj).val();
		var reg_phone =/^1\d{10}$/;
		if(!reg_phone.test(phoneNum)){
			$(obj).next().html("<%=Config.message.get("CUSTOMER_YOU_ENT_THE_PHONE")%>");
			$(obj).next().show();
			return;
		}else{
			$(obj).next().hide();
		}
	}

	function checkEmail(obj){
		var email = $(obj).val().trim();
		var reg =/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		if(!reg.test(email)){
			$(obj).next().show();
			$(obj).next().html("<%=Config.message.get("CUSTOMER_YOUR_EMAIL_ERROR")%>");
		}else{
			$(obj).next().hide();
			$(obj).next().html("");
		}
	}

	function hideErrorSpan(obj){
		$(obj).next().hide();
	}

    function addCustomer() {
		var contractNo=$("input[name='contract_no']").val().trim();
		var customerName = $("input[name='customer_name']").val().trim();
		var customerPhone = $("input[name='customer_phone']").val().trim();
		var customerEmail = $("input[name='customer_email']").val().trim();
		var customerCompany = $("input[name='customer_company']").val().trim();
		var customerOffice = $("input[name='customer_office']").val().trim();
		var customerAddress = $("input[name='customer_address']").val().trim();
		var remarks = $("input[name='remarks']").val().trim();
		if(contractNo==""){
			$("input[name= 'contract_no']").next().html("Please enter the contract number");
			$("input[name='contract_no']").next().show();
			return;
		}
		if(customerCompany==""){
			$("input[name= 'customer_company']").next().html("<%=Config.message.get("CUSTOMER_INPUT_COMPANY")%>");
			$("input[name='customer_company']").next().show();
			return;
		}
		if(customerAddress==""){
			$("input[name= 'customer_address']").next().html("<%=Config.message.get("CUSTOMER_INPUT_COMPANY_ADDRESS")%>");
			$("input[name='customer_address']").next().show();
			return;
		}
		if (customerName == "") {
			$("input[name='customer_name']").next().html("<%=Config.message.get("CUSTOMER_INPUT_CUSTOMER_NAME")%>");
			$("input[name='customer_name']").next().show();
			return;
		}
		if (customerPhone == "") {
			$("input[name='customer_phone']").next().html("<%=Config.message.get("CUSTOMER_PLEASE_INPUT_PHONE")%>");
			$("input[name='customer_phone']").next().show();
			return;
		}

        if($("input[name='customer_name']").next().is(':visible') || $("input[name='customer_phone']").next().is(':visible') || $("input[name='customer_email']").next().is(':visible')
				|| $("input[name='customer_company']").next().is(':visible') || $("input[name='customer_office']").next().is(':visible') || $(" input[name='customer_address']").next().is(':visible')){
			return;
        }
		$.ajax({
            url: "customer/add",
			method: "post",
            data:{"contractCode":contractNo,"customerName":customerName,"customerPhone": customerPhone,"customerEmail":customerEmail,"customerCompany":customerCompany,"customerOffice":customerOffice,"customerAddress":customerAddress,"remarks":remarks},
            dataType:"json",
            success:function(data){
                window.confirm(data.desc);
                var pageTemp=$("#pageTemp").val();
                if(pageTemp==1){
                	 load_page('policy/createPolicy');
                }else{
                	 load_page('customer.html');
                }
               
            }
        });
	}

</script>