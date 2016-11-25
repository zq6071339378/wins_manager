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
								<h3 class="content-title pull-left"><%=Config.message.get("ACTIVITY_MANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("CUSTOMER_DETAIL_INFORMATION")%></div>
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
																		<label class="control-label col-md-3 old lable_height"><%=Config.message.get("CUSTOMER_NAME")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.customerName}</span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><%=Config.message.get("CUSTOMER_CONTACT_NUMBER")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.customerPhone}</span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><%=Config.message.get("CUSTOMER_EMAIL")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.customerEmail}</span>
																		</div>
																	</div>

																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><%=Config.message.get("CUSTOMER_COMPANY_NAME")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.customerCompany}</span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><%=Config.message.get("CUSTOMER_OFFICE")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.customerOffice}</span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><%=Config.message.get("CUSTOMER_COMPANY_ADDRESS")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.customerAddress}</span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><%=Config.message.get("CUSTOMER_REMARKS")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.remarks}</span>
																		</div>
																	</div>
																</div>
																<div class="divide-20"></div>
															</div>
														</div>
													</div>
												</form>
											</div>
										</div><!-- /CONTENT-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--创建账户end-->
			</div><!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->