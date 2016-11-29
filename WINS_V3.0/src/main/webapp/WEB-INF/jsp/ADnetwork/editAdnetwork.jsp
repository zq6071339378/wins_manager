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
									<a href="javascript:void(0);"><%=Config.message.get("ADNETWORK_MANAGEMENT")%></a>
								</li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("ADNETWORK_EDITADNETWORK")%></h3>
							</div>
							<div class="description"><%=Config.message.get("ADNETWORK_DETAIL_INTRODUCE")%></div>
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
																		<label class="control-label col-md-3 old lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ADNETWORK_NAME")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" value="${result.networkName}" name="network_name"  placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_NAME")%>" onchange="hideErrorSpan(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<%-- <div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ADNETWORK_EXPORT_FLUX")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" value="${result.exportFlux}" placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>" name="export_flux" onchange="checkPhoneNum(this)" onkeyup='this.value=this.value.replace(/\D/g,"")'>
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div> --%>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span class="star"></span>&nbsp;<%=Config.message.get("ADNETWORK_FLUX_RATIO")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" value="${result.flowRadio}" placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>" name="flow_radio" onchange="checkEmail(this)" onkeyup='this.value=this.value.replace(/\D/g,"")'>
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ADNETWORK_WAYSOFPAYMENTS")%>：</label>
																		 <div class="col-md-7 row_left">
																			 <label class="checkbox-inline">
																				<input type="radio" id="optionsRadios3" name="buy_type" required="required" value="1" checked="checked" > &nbsp;&nbsp;CPM
																			 </label>
																			 <label class="checkbox-inline">
																				 <input type="radio" id="optionsRadios4" name="buy_type" required="required" value="2" > &nbsp;&nbsp;CPC
																			 </label>
																		 </div>
																	
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span class="star"></span>&nbsp;<%=Config.message.get("ADNETWORK_TOTAL_PRICE")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" value="${result.totalPrice}" placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>" name="total_price" onchange="hideErrorSpan(this)" onkeyup='this.value=this.value.replace(/\D/g,"")'>
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
                                                                        <div class="divide-20"></div>
														    <div class="form-group create_font">
															<label for="password-input"  class="control-label col-md-3 lable_height">
																<span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("ADNETWORK_TIME")%> ：
															</label>
															<div class="col-md-4 ">
																<div class="controls">
																	<div class="input-group date" id="reservation">
																	<input type="text" placeholder="Start time - End time" required="required" id="doubleDate_id" class="form-control" style="padding-left: 30px !important;" value="${result.startTime}-${result.endTime}">
																		<span class="input-group-addon">
																			<span class="fa fa-calendar"></span>
																		</span>
																		<input type="hidden" name="EndTime" id="pushpolicyEndtime_id">
																		<input type="hidden" name="StartTime" id="pushpolicyStarttime_id">
																	</div>
																</div>
															</div>
														</div>
																
																</div>
																<div class="divide-20"></div>
																<input type="hidden" id="networkId" value="${result.networkId}">
															</div>
														</div>
													</div>
												</form>
												<div class="row">
													<div class="col-md-7">
														<div id="btn_group" class="col-md-offset-6">
															<a class="btn btn-md btn-default" href="javascript:void(0);" onclick="load_page('Adnetwork.html')"><%=Config.message.get("CANCEL")%></a>
															<a class="btn btn-md btn-primary btn_left" href="javascript:void(0);" onclick="editCustomer()"><%=Config.message.get("SAVE")%></a>
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
	function editCustomer() {
		var networkId = $("#networkId").val();
		var networkName = $("input[name='network_name']").val();
		var exportFlux = $("input[name='export_flux']").val().trim();
		var flowRadio = $("input[name='flow_radio']").val().trim();
		var buyType = $("input[name='buy_type']:checked").val().trim();
		var totalPrice = $("input[name='total_price']").val().trim();
		var startTime = $("input[name='StartTime']").val().trim();
		var endTime = $("input[name='EndTime']").val().trim();
	/* 	var customerAddress = $("input[name='customer_address']").val().trim();
		var remarks = $("input[name='remarks']").val().trim(); */
		$.ajax({
			url: "ADnetwork/edit",
			method: "post",
			data:{"networkId":networkId,"networkName":networkName,"exportFlux": exportFlux,"flowRadio":flowRadio,"buyType":buyType,"totalPrice":totalPrice,"startTime":startTime,"endTime":endTime},
			dataType:"json",
			success:function(data){
				window.confirm(data.desc);
				load_page('Adnetwork.html');
			}
		});
	}
	
	$(document).ready(function() {
		$('#doubleDate_id').daterangepicker(
				{},function(start,end){
					var starttime=start.format('YYYY-MM-DD');
					var endtime=end.format('YYYY-MM-DD');
					var dateN=new Date();
					var dateMonth=parseInt(dateN.getMonth())+1;
					var _nowDate=dateN.getFullYear()+'-'+dateMonth+'-'+dateN.getDate();       //获取当前月份(0-11,0代表1月)
					if(start<dateN){
						starttime=_nowDate;
					}
					if(end<dateN){
						endtime=_nowDate;
					}
					$("#doubleDate_id").val(starttime+'-'+endtime);
					$('#pushpolicyEndtime_id').val(endtime);
					$('#pushpolicyStarttime_id').val(starttime);
				});

	});

</script>