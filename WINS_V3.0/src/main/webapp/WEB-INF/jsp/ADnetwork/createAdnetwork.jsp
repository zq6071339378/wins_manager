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
								<h3 class="content-title pull-left"><%=Config.message.get("ADNETWORK_ADD")%></h3>
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
																			<input type="text" class="form-control" name="Adnetwork_name"  placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_NAME")%>"  onchange="hideErrorSpan(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ADNETWORK_EXPORT_FLUX")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="export_flux"   placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>"  onchange="checkPhoneNum(this)" onkeyup='this.value=this.value.replace(/\D/g,"")' />
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ADNETWORK_FLUX_RATIO")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control"  placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>"   name="flow_radio" onchange="checkEmail(this)" onkeyup='this.value=this.value.replace(/\D/g,"")' />
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ADNETWORK_WAYSOFPAYMENTS")%>：</label>
																		<div class="col-md-7 row_left">
																			<label class="checkbox-inline">
																				<input type="radio" id="optionsRadios3" name="buy_type" required="required" value="1" checked="checked">&nbsp;&nbsp;CPM
																			</label>
																			<label class="checkbox-inline">
																				<input type="radio" id="optionsRadios4" name="buy_type" required="required" value="2">&nbsp;&nbsp;CPC
																			</label>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ADNETWORK_TOTAL_PRICE")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control"   placeholder="<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>" name="total_price" onchange="hideErrorSpan(this)" onkeyup='this.value=this.value.replace(/\D/g,"")'  />
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
														    <div class="divide-20"></div>
														    <div class="form-group create_font">
																<label for="password-input"  class="control-label col-md-3 lable_height">
																	<span class="star">*</span>&nbsp;&nbsp; <%=Config.message.get("ADNETWORK_TIME")%>：
																</label>
																<div class="col-md-4 ">
																	<div class="controls">
																		<div class="input-group date" id="reservation">
																			<input type="text" placeholder="<%=Config.message.get("STARTTIME-ENDTIME")%>" required="required" id="doubleDate_id"
																				   class="form-control"  style="padding-left: 30px !important;" onclick="$('#doubleDate_idMag').hide();">
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
															</div>
															<div class="divide-20"></div>
														</div>
													</div>
												</form>
												<div class="row">
													<div class="col-md-6">
														<div id="btn_group" class="col-md-offset-7">
															<a class="btn btn-md btn-default" href="javascript:void(0);" onclick="load_page('Adnetwork.html')"><%=Config.message.get("CANCEL")%></a>
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
		if (realName.length<2  ||  realName.length >10){
			$(obj).next().html("<%=Config.message.get("ADNETWORK_PLEASE_INPUT_NAME")%>" );
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
			$(obj).next().html("<%=Config.message.get("ADNETWORK_PLEASE_INPUT_FIGURE")%>");
			$(obj).next().show();
			return;
		}else{
			$(obj).next().hide();
		}
	} 

	function hideErrorSpan(obj){
		$(obj).next().hide();
	}

    function addCustomer() {
		var AdnetworkName = $("input[name='Adnetwork_name']").val();
		var buyType = $("input[name='buy_type']:checked").val().trim();
		var totalPrice = $("input[name='total_price']").val().trim();
		var exportFlux = $("input[name='export_flux']").val().trim();
		var flowRadio = $("input[name='flow_radio']").val().trim();
		var startTime = $("input[name='StartTime']").val().trim();
		var endTime = $("input[name='EndTime']").val().trim();
		$.ajax({
            url: "ADnetwork/add",
			method: "post",
            data:{"AdnetworkName":AdnetworkName,"buyType": buyType,"totalPrice":totalPrice,"exportFlux":exportFlux,"flowRadio":flowRadio,"startTime":startTime,"endTime":endTime},
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