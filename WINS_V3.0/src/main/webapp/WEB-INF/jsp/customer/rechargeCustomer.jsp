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
									<a href="javascript:void(0);"><%=Config.message.get("SYSTEM_MANAGE")%></a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("RECHARGE_ACCOUNT_RECHARGE")%></h3>
							</div>
							<div class="description"> </div>
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
																		<label class="control-label col-md-3 old lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ACTIVITY_TOPUP")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="accountBalance1"  oninput="hideErrorSpan(this)" placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ACTIVITY_CONFIRM")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="accountBalance2" oninput="hideErrorSpan(this)" placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')" >
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																<div class="divide-20"></div>
																<input type="hidden" id="customerId" value="${result.customerId}">
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
															<c:if test="${pagetemp ne 1}"><a class="btn btn-md btn-default" href="javascript:void(0);" onclick="load_page('financialManage.html')"><%=Config.message.get("CANCEL")%></a></c:if>
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
	function hideErrorSpan(obj){
		$(obj).next().hide();
	}
    function addCustomer() {
    	var customerId = $("#customerId").val();
		var tradeAmount1 = $("input[name='accountBalance1']").val().trim();
    	var tradeAmount2 = $("input[name='accountBalance2']").val().trim();
    	if(tradeAmount1==""){
			$("input[name='accountBalance1']").next().show();
			$("input[name='accountBalance1']").next().html("<%=Config.message.get("RECHARGE_PLEASE")%>");
    		return false;
    	}
		if(tradeAmount2==""){
			$("input[name='accountBalance2']").next().show();
			$("input[name='accountBalance2']").next().html("<%=Config.message.get("RECHARGE_PLEASE")%>");
			return false;
		}
    	if(tradeAmount1!=tradeAmount2){
			$("input[name='accountBalance2']").next().show();
			$("input[name='accountBalance2']").next().html("<%=Config.message.get("RECHARGE_P")%>");
			return false;
		}
    	if($("input[name='accountBalance1']").next().is(':visible') || $("input[name='accountBalance2']").next().is(':visible')){
			return false;
		}
		$.ajax({
            url: "customer/recharge",
			method: "post",
            data:{"tradeAmount":tradeAmount2,"customerId":customerId},
            dataType:"json",
            success:function(data){
                window.confirm(data.desc);
                var pageTemp=$("#pageTemp").val();
                if(pageTemp==1){
                	 load_page('policy/createPolicy');
                }else{
                	 load_page('financialManage.html');
                }
            }
        });
	}

</script>