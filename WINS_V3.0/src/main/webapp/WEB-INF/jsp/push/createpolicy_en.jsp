<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
		 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${basePath}css/createpolicy.css"/>
<script src="${basePath}js/jquery.form.js"></script>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("HOME")%></a>
								</li>
								<li><a href="strategy.html"><%=Config.message.get("POLICY_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("POLICY_MANAGER_CREATE_POLICY")%></h3>
							</div>
							<div class="description"><%=Config.message.get("CREATE_POLICY_ATTENTIONS12")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="modal fade" id="box-add-activity" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
									  <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
									  <h4 class="modal-title"><%=Config.message.get("CREATEPOLICY_ADDACTIVITIES")%></h4>
									</div>
									<div class="panel-body"> 
							          <form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
							           <div class="form-group msg_font form_group"> 
							            <label class="col-md-3 control-label"><span style="color:red;">*</span><%=Config.message.get("CREATEPOLICY_ACTIVITYNAME")%>：</label> 
							            <div class="col-md-8"> 
							             <input type="text" class="form-control" name="activity_name"> 
							            </div> 
							           </div> 
							           <div class="form-group msg_font form_group"> 
							            <label class="col-md-3 control-label"><span style="color:red;">*</span><%=Config.message.get("CREATEPOLICY_ADVERTISER")%>：</label> 
							            <div class="col-md-8"> 
							             <input type="text" class="form-control" name="customer_name"> 
							            </div>
							           </div>
							           <div class="form-group msg_font form_group"> 
							            <label class="col-md-3 control-label"><%=Config.message.get("CREATEPOLICY_INTRODUCE")%>：</label> 
							            <div class="col-md-8"> 
							             <textarea class="form-control" name="activity_desc"></textarea> 
							            </div>
							           </div> 
							          </form> 
							         </div>
									<div class="modal-footer">
									  <button data-dismiss="modal" class="btn btn-default"  type="button"><%=Config.message.get("CREATEPOLICY_CLOSE")%></button>
									  <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addActivityCreart();"><%=Config.message.get("CREATEPOLICY_SAVE")%></button>
									</div>
								  </div>
								</div>
							  </div>
				<!--添加群组dialog-->
				<div class="modal fade" id="box-policyGroup" tabindex="-1"
					 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
					 data-backdrop="static">
					<div class="modal-dialog">

						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
										type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("CREATE_POLICY_ADD_GROUP")%></h4>
							</div>
							<div class="panel-body">
								<form action="${basePath}group/addGroup" method="post" enctype="multipart/form-data" class="form-horizontal" id="jForm">
									<div class="col-md-8 pull-left">
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("CREATE_POLICY_GROUP_NAME")%>：</label>
											<div class="col-sm-7 row_left">
												<input type="text" class="form-control" name="groupName" placeholder="Group name" id="groupName">
											</div>
										</div>
										<div class="form-group has-error">
											<label class="col-md-4"></label> <label class="control-label" for="inputWarning" id="groupNameMsg"></label>
										</div>
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("CREATE_POLICY_GROUP_IMAGE")%>：</label>
											<input type="file" style="display: none" id="img"
												   onchange="uploadImg();" name="pic">
											<div class="col-md-1 row_left">
												<div class="input-append">
													<a class="btn btn-info" onclick="$('input[id=img]').click();"><%=Config.message.get("CREATE_POLICY_UPLOAD")%>&nbsp;&nbsp;</a>
												</div>
											</div>
										</div>
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("CREATE_POLICY_USER_NAME")%>：</label>
											<div class="col-md-5 row_left" style="width: 60%;">
												<textarea rows="5" cols="10" name="mulUser"
														  class="form-control" placeholder="Separated by a comma" id="mulUser"></textarea>
											</div>
											<input name="userAccount" id="userAccount" type="hidden" />
										</div>
										<div class="form-group">
											<label for="password-input" class="col-md-4 control-label"><%=Config.message.get("CREATE_POLICY_IMPORT")%>：</label>
											<div class="col-md-2 row_left">
												<input type="file" style="display: none" id="lefile"
													   onchange="Mimport();" name="multiImport"> <input
													type="hidden" name="path" id="path">
												<div class="input-append">
													<a onclick="$('input[id=lefile]').click();"
													   class="btn btn-info"><%=Config.message.get("CREATE_POLICY_SCAN")%>&nbsp;&nbsp;</a> <span id="import"></span>
												</div>
											</div>
											<!--  <span id="mulUserMsg" style="margin-left: 10px;color: red"></span> -->
											<div class="col-md-1">
												<span class="span_fonts"><i
														class="fa fa-question-circle" title="<%=Config.message.get("THIS_OPERATE_WILL_OVERRIDE")%>"></i></span>
											</div>
										</div>
										<div class="form-group">
											<label for="password-input" class="col-md-4 control-label"></label>
											<div class="col-md-5 row_left">
												<span class="span_fonts"><%=Config.message.get("CREATE_POLICY_ONLY_SUPPORT_TXT")%></span>
											</div>
											<div class="form-group has-error">
												<label class="col-md-4"></label> <label
													class="control-label" for="inputWarning" id="mulUserMsg"></label>
											</div>
										</div>
									</div>
									<div class="col-md-4 pull-right">
										<img id="preview" src="img/erweima.png"
											 style="display: block; width: 100px; height: 100px;">
									</div>
									<div class="col-md-4 pull-right">
										<span class="span_fonts"><%=Config.message.get("CREATE_POLICY_SIZE")%>：100*100</span>
									</div>
									<div class="col-md-4 pull-rightSSSSSSS">
										<span class="span_fonts"><%=Config.message.get("CREATE_POLICY_SUPPORT")%>：png、jpg</span>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
								<button type="button" class="btn btn-primary" data-dismiss="modal" id="saveGroup"><%=Config.message.get("SAVE")%></button>
							</div>
						</div>
					</div>
				</div>
				<!--添加外部路径dialog-->
				<div class="modal fade" id="box-policyUrl" tabindex="-1"
					 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("CREATE_POLICY_ADD_EXTERNAL_PATHS")%></h4>
							</div>
							<div class="panel-body">
								<form action="" method="post" enctype="multipart/form-data"
									  class="form-horizontal">
									<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("CREATE_POLICY_ADD_PATH_ADDRESS")%>：</label>
										<div class="col-sm-4 row_left">
											<input type="text" class="form-control" name="password" placeholder="www.apple.com">
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
								<button type="button" class="btn btn-primary" data-dismiss="modal"><%=Config.message.get("SAVE")%></button>
							</div>
						</div>
					</div>
				</div>

				<!--创建策略-->
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
						<div id="formWizard" class="box">
							<div class="box-body form">
								<div class="form-horizontal" id="wizForm"
									 novalidate="novalidate">
									<div class="wizard-form">
										<div class="wizard-content">
											<ul class="nav nav-pills nav-justified steps policy_steps">
														 <li class="active myli01">
															<a class="wiz-step" href="#ggmanager">
															<span class="step-number">1</span>
															<span class="step-name"><%=Config.message.get("CREATEPOLICY_CHOOSE_ADVERTISER")%></span>   
															</a>
														 </li>
		
														 <li>
															<a class="wiz-step active" href="#hdmanager">
															<span class="step-number">2</span>
															<span class="step-name"><%=Config.message.get("CREATEPOLICY_CHOOSE_ACTIVITY")%></span>   
															</a>
														 </li>
														 <li>
															<a class="wiz-step" href="#confirm">
															<span class="step-number">3</span>
															<span class="step-name"><%=Config.message.get("CREATEPOLICY_POLICY_INFORMATION")%> </span>   
															</a> 
														 </li>
														 <li>
															<a class="wiz-step" href="#policy_submit">
															<span class="step-number">4</span>
															<span class="step-name"><%=Config.message.get("CREATEPOLICY_PUSH_PLAN")%> </span>   
															</a> 
														 </li>
														 <li>
															<a class="wiz-step" href="#sucai">
															<span class="step-number">5</span>
															<span class="step-name"><%=Config.message.get("CREATEPOLICY_PUSH_MATERIAL")%> </span>   
															</a> 
														 </li>
														 <li class="myli02">
															<a class="wiz-step" href="#tijiao">
															<span class="step-number">6</span>
															<span class="step-name">Complete </span>   
															</a> 
														 </li>
													  </ul>
											<input type="hidden" value="0" id="create_step_Id"/>
											<div class="divide-40"></div>
											<div class="tab-content">
											<!-- 选择广告主 -->
<div id="ggmanager" class="tab-pane active">
	<div class="col-md-8">
		<div class="col-md-4 form_input login-box">
				<div class="form-group col-md-12 row_left search_icon" style="position:relative;">
					<input type="search" id="exampleInputSearchCutomor"  style="width:100%" class="form-control" placeholder="<%=Config.message.get("CREATEPOLICY_PLEASEINPUT_ADVERTISER_NAME")%>">
				</div>
		</div>
		<div class="from-group col-md-2 row_left">
			<!--a class="btn btn-danger config" type="button" data-toggle="modal" href="createPolicyGgz.html">添加广告主</a-->
			<button onclick="load_page('customer/createCustomer.html?temp=1')" class="btn btn-danger" type="button"><%=Config.message.get("CUSTOMER_ADD")%></button>
						
		</div>
	</div>
	<div class="divide-10">
	</div>
	<div class="box tables">
		<table class="datatable table table-striped table-bordered table-hover dataTable">
			<thead>
				<tr>
					<th><%=Config.message.get("CREATEPOLICY_SORT")%></th>
					<th><%=Config.message.get("CREATEPOLICY_COMPANYNAME")%></th>
					<th><%=Config.message.get("CREATEPOLICY_ADVERTISER_NAME")%></th>
					<th><%=Config.message.get("CREATEPOLICY_POSITION")%></th>
					<th><%=Config.message.get("CREATEPOLICY_CREATETIME")%></th>
					<th><%=Config.message.get("CREATEPOLICY_CREATEACCOUNT")%></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="customer" items="${customerList.list}" varStatus="vs">
				<tr>
					<td>
						<input type="radio" name="customerName"  value="${customer.customerId}_${customer.customerName}"  <c:if test="${vs.count eq 1}">checked="true"</c:if> />${vs.count}
					</td>
						<td>${customer.customerCompany}</td>
						<td class="customerlistclass">${customer.customerName}</td>
						<td>${customer.customerOffice}</td>
						<td>${customer.createName}</td>
						<td><fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
	<!-- 选择活动 -->
	<div id="hdmanager" class="tab-pane" style="display: none;">
							<div class="col-md-8">
								<div class="col-md-4 form_input login-box">
										<div class="form-group col-md-12 row_left search_icon" style="position:relative;">
											<input type="search" id="exampleInputSearchactivity"  style="width:100%" class="form-control" placeholder="<%=Config.message.get("CREATEPOLICY_PLEASEINPUT_ADVERTISER_NAME")%>">
										</div>
								</div>
								<div class="from-group col-md-2 row_left">
									<a  href="#box-add-activity" data-toggle="modal" class="btn btn-danger" type="button">add</a>
								</div>
							</div>
							<div class="divide-10">
							</div>
							<div class="box tables">
								<table class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("CUSTOMER_SORT")%></th>
										<th><%=Config.message.get("CREATEPOLICY_ADVERTISER_NAME")%></th>
										<th><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></th>
										<th><%=Config.message.get("CREATEPOLICY_POLICYCOUNTS")%></th>
										<th><%=Config.message.get("CUSTOMER_CREATE_USER")%></th>
										<th><%=Config.message.get("CUSTOMER_CREATE_TIME")%></th>
									</tr>
								</thead>
								<tbody id="activitylist_Id">
								<c:if test="${!empty activitylist}">
									<c:forEach var="ac" items="${activitylist.list}" varStatus="vs">
									<tr>
										<td><input type="radio" value="${ac.activityId}_${ac.activityName}" name="activityNamekey" <c:if test="${vs.count eq 1}">checked="true"</c:if>/>${vs.count}</td>
										<td class="activitylistclass">${ac.activityName}</td>
										<td>${ac.customerName}</td>
										<td><c:if test="${empty ac.policyNum}">0</c:if><c:if test="${!empty ac.policyNum}">${ac.policyNum}</c:if></td>
										<td>${ac.createName}</td>
										<td><fmt:formatDate value="${ac.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									</tr>
									</c:forEach>
								</c:if>
								</tbody>
							</table>
							</div>
						</div>
												<!--创建策略step1-->
												<div id="account" class="tab-pane" style="display:none;">
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-1 control-label"></label>
														<div class="col-sm-9">
															<div class="box-title">
																<i class="fa fa-bars"></i>
																<span class="menu-text">
																	<%=Config.message.get("CREATEPOLICY_POLICY_INFORMATION")%>
																</span>
															</div>
														</div>
													</div>
													<form onsubmit="return false;"
														  id="account_create_policy_id_0">
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_POLICY_NAME")%>：</label>
															<div class="col-sm-4">
																<input type="text" name="policyName"  onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"
																	   id ="policyName" required="required" class="form-control" placeholder="<%=Config.message.get("PLEASEINPUTPOLICYNAME")%>">
															</div>
															<div class="col-md-2">
																<div class="form-policy has-error">
																	<label class="col-md-3"></label> <label
																		class="control-label" for="inputWarning"  style="font-weight:bold;"
																		id="policyNameMsg"></label>
																</div>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label class="col-md-3 control-label" for="password-input"><span class="star"></span>&nbsp;<%=Config.message.get("CREATE_POLICY_INDUSTRY_ATTRIBUTES")%>：</label>
															<div class="col-md-2" style="margin-top:-15px;">
												          <select id="selid" onchange="selectChild(this);" class="form-control"  name="industry-bear"></select>
															</div>
															<div id="second_industry" class="col-md-2" style="margin-top:-15px;display: none;">
												          <select id="subselid" onchange="" class="form-control" name=""></select>
															</div>
															<%--<div class="col-md-2" style="margin-top:-15px;">
                                                          <select id="threadselid" onchange="" class="form-control" name="industry-bear"></select>
                                                            </div>--%>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label">
																<span class="star"></span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_AD_TYPE")%>：</label>
															<div class="col-md-2">
																<select class="form-control" required="required" name="adType">
																	<option value="1"><%=Config.message.get("POLICY_MANAGEMENT_CORPORATE_BRANDING")%></option>
																	<option value="2"><%=Config.message.get("POLICY_MANAGEMENT_DIRECT_TRAFFIC")%></option>
																	<option value="3"><%=Config.message.get("POLICY_MANAGEMTNT_EVENTSW1")%></option>
																	<option value="4"><%=Config.message.get("POLICY_MANAGEMTNT_GOVERNMENT1")%></option>
																	<option value="5"><%=Config.message.get("POLICY_MANAGEMENT_BRAND_EVENT")%></option>
																	<option value="6"><%=Config.message.get("POLICY_MANAGEMENT_PRICE_PROMOTION")%></option>
																	<option value="7"><%=Config.message.get("POLICY_MANAGEMENT_PRODUCT_BRANDING")%></option>
																	<option value="8"><%=Config.message.get("POLICY_MANAGEMENT_PUBLIC_SERCIVE_ADVERTISMENT")%></option>
																	<option value="9"><%=Config.message.get("POLICY_MANAGEMENT_OTHERS0")%></option>
																</select>
															</div>
														</div>
															<div class="form-group msg_font form_group">
																<label for="password-input"
																	   class="col-md-3 control-label"><span
																		class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_WAYS_TO_BUY")%>：</label>
																<div class="col-md-7 row_left">
																	<label class="checkbox-inline"> 
																		<input type="radio"  name="buyType" required="required" value="1" onclick="$('#hitLimit').attr('readOnly','true').val(0);$('#showLimit').removeAttr('readOnly');"  checked>&nbsp;&nbsp;CPM
																	</label> 
																	<label class="checkbox-inline">
																		<input type="radio"  name="buyType" required="required" value="2"  onclick="$('#showLimit').attr('readOnly','true').val(0);$('#hitLimit').removeAttr('readOnly');">&nbsp;&nbsp;CPC
																	</label>
																</div>
															</div>
															<div class="form-group msg_font form_group">
																<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("BID")%>：</label>
																<div class="col-sm-4 ">
																	<input type="text"  id="priceUrl"   placeholder="<%=Config.message.get("CHANGE_THE_PRICE_HERE")%>" name="unitPrice" class="form-control"  value="0"  onkeyup='this.value=this.value.replace(/\D/g,"")'  onblur="getunitPrice()">
																	<input type="hidden" id="newunitPrice" name="newunitPrice">
																</div>
																<div class="col-md-2 row_left">
																	<span class="span_fonts">&nbsp;<%=Config.message.get("REFERENCE_PRICE")%></span>&nbsp;<span class="span_fonts" id="priceofUrl"></span>
																</div>
															</div>
															<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label">
																<span class="star" id="showLimitSpan"></span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_POLICY_TOTAL_SHOW")%>：</label>
															<div class="col-sm-2">
																<input type="text" name="showLimit" maxlength="9" class="form-control"  id="showLimit" data-type="number" placeholder=" <%=Config.message.get("FILL_WITH_DIGITAL")%>"  value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
															</div>
															<div class="col-md-1 row_left">
																<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
															</div>
															<div class="col-md-5 row_left">
																<span class="span_fonts"><%=Config.message.get("TOTAL_NUMBER_OF_POLICY_SHOW")%></span>&nbsp;&nbsp;&nbsp;&nbsp;
																<label class="control-label" style="color: #b94a48; font-weight:bold; "  for="inputWarning"   id="policyLimitMsg"></label>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_CLICK")%>：</label>
															<div class="col-sm-2">
																<input type="text" name="hitLimit" id="hitLimit" maxlength="9" readOnly="true" class="form-control" data-type="number" placeholder=" <%=Config.message.get("FILL_WITH_DIGITAL")%>"  value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
															</div>
															<div class="col-md-1 row_left">
																<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
															</div>
															<div class="col-md-5 row_left">
																<span class="span_fonts"><%=Config.message.get("TOTAL_NUMBER_OF_POLICY_CLICK")%></span>
															</div>
														</div>
															<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_DESCRIPTION")%>：</label>
															<div class="col-md-4">
																<textarea placeholder="<%=Config.message.get("NO_MORETHAN_100WORDS")%>" class="form-control"
																		  name="Description" cols="5" rows="3" maxlength="100"></textarea>
															</div>
														</div>
														
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-1 control-label"></label>
															<div class="col-sm-9">
																<div class="box-title">
																	<i class="fa fa-bars"></i>
																	<span class="menu-text">
																		<%=Config.message.get("CREATEPOLICY_STRATEGY_ON")%>
																	</span>
																</div>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PRIORITY")%>：</label>
															<div class="col-md-2">
																<select class="form-control" name="policyLevel"
																		required="required">
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																	<option value="4">4</option>
																</select>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_INTERVIAL")%>：</label>
															<div class="col-md-2">
																<input type="text" name="pushInterval" maxlength="9" id="pushInterval" placeholder="<%=Config.message.get("FILL_WITH_DIGITAL")%>"
																	   class="form-control" required="required" onkeyup='this.value=this.value.replace(/\D/g,"")'>
															</div>
															<div class="col-md-2">
																<select class="form-control" name="timeType">
																	<option value="1"><%=Config.message.get("CREATE_POLICY_SECOND")%></option>
																	<option value="2"><%=Config.message.get("CREATE_POLICY_MINUTES")%></option>
																	<option value="3"><%=Config.message.get("CREATE_POLICY_HOUR")%></option>
																</select>
															</div>
															<div class="col-md-2">
																<div class="form-policy has-error">
																	<label class="col-md-3"></label> <label
																		class="control-label" for="inputWarning"  style="font-weight:bold;"
																		id="policcPushMsg"></label>
																</div>
															</div>
														</div>

														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_TERMINAL_TYPE")%>：</label>
															<div class="col-md-7 row_left">
																<label class="checkbox-inline"> <input
																		type="radio" id="optionsRadios3" name="terminalType" tType="1"
																		required="required" value="1" checked>&nbsp;&nbsp;Phone
																</label> <label class="checkbox-inline"> <input
																	type="radio" id="optionsRadios4" name="terminalType" tType="2"
																	required="required" value="0">&nbsp;&nbsp;PC
															</label> <label class="checkbox-inline"> <input
																	type="radio" id="optionsRadios4" name="terminalType" tType="3"
																	required="required" value="0">&nbsp;&nbsp;PAD
															</label>
															</div>
														</div>
														
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_SPEED")%>：</label>
															<div class="col-md-7 row_left">
																<label class="checkbox-inline">
																	<input type="radio" id="optionsSpeed" name="speedType" onclick="changeLimit(this)" required="required" value="2"  checked>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_QUICKLY_PUSH")%>
																	<%--<input type="hidden" id="optionsSpeedval" value="1">--%>
																</label>
																<label class="checkbox-inline">
																	<input  onclick="changeLimit(this)" type="radio" id="optionsSpeed1" name="speedType" required="required" value="1">&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_UNIFORM_SPEED")%>
																	<%--<input type="hidden" id="optionsSpeedval" value="2">--%>
																</label>
															</div>
														</div>
														<div class="form-group msg_font form_group" style="display: none">
															<label for="password-input" class="col-md-3 control-label">
																<span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_POLICY_DAY_LIMIT")%>：</label>
															<div class="col-md-2">
																<input type="text" name="policyDayLimit" id="policyDayLimit" class="form-control" value="0" maxlength="9"  data-type="number"/>
															</div>
															<div class="col-md-2">
																<span class="span_font"><%=Config.message.get("TIMES_POLICY_DAY")%></span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label">
																<span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_USER_SHOW_LIMIT")%>：</label>
															<div class="col-md-2">
																<input type="text" name="pushLimit" id="pushLimit" class="form-control" maxlength="9" data-type="number"  onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"   placeholder=" <%=Config.message.get("FILL_WITH_DIGITAL")%>"   />
															</div>
															<div class="col-md-2">
																<span class="span_font"><%=Config.message.get("TIMES_PERSON_DAY")%></span>
															</div>
															<div class="col-md-2">
																<div class="form-policy has-error">
																	<label class="col-md-3"></label> <label
																		class="control-label" for="inputWarning"  style="font-weight:bold;"
																		id="pushLimitMsg"></label>
																</div>
															</div>
														</div>
														<div class="form-group msg_font form_group"
															 style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-7">
																<label class="checkbox-inline"> <input
																		type="checkbox" name="checkedAll" required="required"
																		value="0" id="checkedAll">&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_ALL")%>
																</label> <label class="checkbox-inline"> <input
																	type="checkbox" name="checkbox_name"
																	required="required" value="1" id="checkbox_name_1">&nbsp;&nbsp;Andriod
															</label> <label class="checkbox-inline"> <input
																	type="checkbox" name="checkbox_name"
																	required="required" value="2" id="checkbox_name_1">&nbsp;&nbsp;ios
															</label> <label class="checkbox-inline"> <input
																	type="checkbox" name="checkbox_name"
																	required="required" value="3" id="checkbox_name_2">&nbsp;&nbsp;Symbian
															</label> <label class="checkbox-inline"> <input
																	type="checkbox" name="checkbox_name"
																	required="required" value="4" id="checkbox_name_3">&nbsp;&nbsp;Windows
																Phone
															</label> <label class="checkbox-inline"> <input
																	type="checkbox" name="checkbox_name"
																	required="required" value="5" id="checkbox_name_4">&nbsp;&nbsp;Blackberry
																OS
															</label>
															</div>
														</div>
														<div class="divide-20" style="display: none"></div>
														<!-- 在线离线 -->
													<c:if test="${isEnglish ne true}">
														<div class="form-group msg_font form_group" >
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("PUSH_TYPE")%>：</label>
															<div class="col-md-7 row_left">
																<label class="checkbox-inline">
																	<a href="javascript:;" style="text-decoration: none;" tabindex="-1" class="radio_font">
																		<input type="radio" name="policyType" onclick="$('#offline').hide();" id="optionsRadios3" required="required" value="1" checked="checked">&nbsp;&nbsp;<%=Config.message.get("PUSH_ONLINE")%>
																	</a>
																</label> <label class="checkbox-inline">
																<a href="javascript:;" style="text-decoration: none;" tabindex="-1" class="radio_font">
																	<input type="radio" name="policyType" id="optionsRadios4" onclick="$('#offline').show();" required="required" value="4">&nbsp;&nbsp;<%=Config.message.get("PUSH_OUTLINE")%>
																</a>
															</label>
															</div>
														</div>

														<div class="tab-pane fade in active" id="offline" style="display: none;">
															<div class="form-group msg_font form_group">
																<label for="password-input" class="col-md-3 control-label">
																	<span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("PUSH_OUTILNE")%>：</label>
																<div class="col-sm-4">
																	<input type="text" name="pushOtherUrl" id="pushOtherUrl" class="form-control"  onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"
																		   placeholder="http://www.apple.com" onblur="check_pushOtherUrl()"/>
																</div>
																<div class="col-md-2">
																	<div class="form-policy has-error">
																		<label class="col-md-3"></label> <label
																			class="control-label" for="inputWarning"
																			id="policyUrlMsg"></label>
																	</div>
																</div>
																<div class="col-md-2">
																	<div class="form-policy has-error">
																		<label class="col-md-3"></label> <label
																			class="control-label" for="inputWarning"
																			id="policyUrl1Msg"></label>
																	</div>
																</div>
															</div>
														</div>
													</c:if>


														<div class="form-group msg_font form_group" style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("POLICY_MANAGEMENT_LIMITOFSHOW1")%>：</label>
															<div class="col-md-2">
																<select class="form-control">
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																</select>
															</div>
															<div class="col-md-2">
																<span class="span_font"><%=Config.message.get("TIMES_PERSON_DAY")%></span>
															</div>
														</div>
														<div class="form-group msg_font form_group"
															 style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("POLICY_MANAGEMENT_POLICYTYPE1")%>：</label>
															<div class="col-md-2">
																<select class="form-control" name="policyShow"
																		required="required">
																	<option value="1">banner</option>
																	<option value="2">ad</option>
																</select>
															</div>
														</div>
														
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_THE_WAY_OF_SHOW")%>：</label>
															<div class="col-md-2">
																<select class="form-control" required="required"
																		name="showType">
																	<option value="1"><%=Config.message.get("CREATE_POLICY_REPLACE")%></option>
																	<option value="2"><%=Config.message.get("CREATE_POLICY_FLOAT")%></option>
																	<option value="3"><%=Config.message.get("CREATE_POLICY_PLACE_ON_BOTH_SIDES")%></option>
																	<option value="4"><%=Config.message.get("HANGING_ON_THE_EDGE")%></option>
																	<option value="5"><%=Config.message.get("PUT_ON_THE_BACK")%></option>
																	<option value="6">toolbar</option>
																</select>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_SHOW_TIME")%>：</label>
															<div class="col-sm-2">
																<input type="text" name="showTime" class="form-control" maxlength="9"
																	   data-type="number" placeholder=" <%=Config.message.get("FILL_WITH_DIGITAL")%>"  value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
															</div>
															<div class="col-md-1 row_left">
																<span class="span_font"><%=Config.message.get("SECOND")%> </span>
															</div>
															<div class="col-md-5 row_left">
																<span class="span_fonts"><%=Config.message.get("CREATE_POLICY_SHOW_TIME")%></span>
															</div>
														</div>
														
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_SHOW")%>：</label>
															<div class="col-sm-2">
																<input type="text" name="showNum" maxlength="9" class="form-control" data-type="number" placeholder=" <%=Config.message.get("FILL_WITH_DIGITAL")%>"  value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
															</div>
															<div class="col-md-1 row_left">
																<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
															</div>
															<div class="col-md-5 row_left">
																<span class="span_fonts"><%=Config.message.get("SINGLE_USER_SHOW_NUMBER")%></span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_CLICK")%>：</label>
															<div class="col-sm-2">
																<input type="text" name="hitNum" class="form-control" maxlength="9"
																	   data-type="number" placeholder=" <%=Config.message.get("FILL_WITH_DIGITAL")%>"  value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
															</div>
															<div class="col-md-1 row_left">
																<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
															</div>
															<div class="col-md-5 row_left">
																<span class="span_fonts"><%=Config.message.get("SINGLE_USER_CLICK_TIMES")%></span>
															</div>
														</div>
														
														<!-- 广告主，活动，传递参数 -->
														<input type="hidden" name ="customerNameList" value="" />
														<input type="hidden" name ="activityNameList" value="" />
													</form>
												</div>
												<!--创建策略step1 END-->
												<!--创建策略step2-->
												<div id="payment" class="tab-pane" style="display: none;">
													<form onsubmit="return false;" id="account_create_policy_id_1">
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label">
																<span class="star">*</span>&nbsp;&nbsp; <%=Config.message.get("CREATE_POLICY_PUSH_TIME")%>：
															</label>
															<div class="col-md-4 row_left">
																<div class="controls">
																	<div class="input-group date" id="reservation">
																		<input type="text" placeholder="<%=Config.message.get("STARTTIME-ENDTIME")%>" required="required" id="doubleDate_id"
																			   class="form-control"  style="padding-left: 30px !important;" onclick="$('#doubleDate_idMag').hide();">
																		<span class="input-group-addon">
																			<span class="fa fa-calendar"></span>
																		</span>
																		<input type="hidden" name="policyEndTime" id="pushpolicyEndtime_id">
																		<input type="hidden" name="policyStartTime" id="pushpolicyStarttime_id">
																		<!-- <input type="text" placeholder="结束时间" required="required" name="policyEndTime" id="pushpolicyEndtime_id" class="form-control" style="margin-left: 8px;">
																	        <span class="input-group-addon"><span class="fa fa-calendar"></span> </span> -->
																		<%--<div class="col-md-2"></div>--%>
																	</div>
																</div>
															</div>
															<div class="col-md-5 row_left" style="width:40%;">
																<span class="span_font col-md-5">
																	<a class="collapsed" data-parent="#accordion" href="#collapse2" data-toggle="collapse"><%=Config.message.get("POLICY_TIME_ADVANCED_SETUP")%>
																		<i class="fa fa-angle-double-down"></i>
																	</a>
																</span>
																<div class="form-policy has-error col-md-5">
																	<label class="control-label" for="inputWarning"  style="font-weight:bold;" id="doubleDate_idMag"></label>
																</div>
															</div>
															
														</div>
														<!--高级设置TABLE-->
														<div class="form-group collapse msg_font form_group"
															 class="panel-body col-md-12" id="collapse2">
															<label class="control-label col-md-3 label1"><%=Config.message.get("PUSH_TIME_PERIOD")%>：</label>
															<div class="form_inputsTime col-md-8 row_left">
																<div class="box-body" id="box-body">
																	<table class="table table-bordered time_table">
																		<thead class="timeChange_tb">
																		<tr>
																			<th><%=Config.message.get("CREATE_POLICY_PUSH_TIME")%><br /><%=Config.message.get("CREATE_POLICY_ALL")%><input type="checkbox" checked="checked"></th>
																			<th>00:00<br /> 02:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>02:00<br /> 04:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>04:00<br /> 06:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>06:00<br /> 08:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>08:00<br /> 10:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>10:00<br /> 12:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>12:00<br /> 14:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>14:00<br /> 16:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>16:00<br /> 18:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>18:00<br /> 20:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>20:00<br /> 22:00 <br /><input type="checkbox" checked="checked"></th>
																			<th>22:00<br /> 24:00 <br /><input type="checkbox" checked="checked"></th>
																		</tr>
																		</thead>
																		<tbody>
																		<tr>
																			<td><%=Config.message.get("MONDAY")%><br /><input type="checkbox" checked="checked"></td>
																			<td class="changeweektime" rel="1-0,1-1,">
																				<span
																						class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="1-2,1-3,">
																				<span
																						class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-4,1-5,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-6,1-7,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-8,1-9,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-10,1-11,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-12,1-13,"><span
																					class="checke_click"></span></td>

																			<td class="changeweektime" rel="1-14,1-15,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-16,1-17,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-18,1-19,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-20,1-21,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="1-22,1-23,"><span
																					class="checke_click"></span></td>
																		</tr>
																		<tr>
																			<td><%=Config.message.get("TUESDAY")%><br /><input type="checkbox" checked="checked"></td>
																			<td class="changeweektime" rel="2-0,2-1,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="2-2,2-3,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-4,2-5,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-6,2-7,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-8,2-9,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-10,2-11,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-12,2-13,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="2-14,2-15,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-16,2-17,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-18,2-19,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-20,2-21,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="2-22,2-23,"><span
																					class="checke_click"></span></td>
																		</tr>
																		<tr>
																			<td><%=Config.message.get("WEDNESDAY")%><br /><input type="checkbox" checked="checked"></td>
																			<td class="changeweektime" rel="3-0,3-1,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="3-2,3-3,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-4,3-5,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-6,3-7,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-8,3-9,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-10,3-11,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-12,3-13,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="3-14,3-15,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-16,3-17,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-18,3-19,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-20,3-21,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="3-22,3-23,"><span
																					class="checke_click"></span></td>
																		</tr>
																		<tr>
																			<td><%=Config.message.get("THURSDAY")%><br /><input type="checkbox" checked="checked"></td>
																			<td class="changeweektime" rel="4-0,4-1,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="4-2,4-3,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-4,4-5,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-6,4-7,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-8,4-9,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-10,4-11,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-12,4-13,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="4-14,4-15,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-16,4-17,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-18,4-19,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-20,4-21,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="4-22,4-23,"><span
																					class="checke_click"></span></td>
																		</tr>
																		<tr>
																			<td><%=Config.message.get("FRIDAY")%><br /><input type="checkbox" checked="checked"></td>
																			<td class="changeweektime" rel="5-0,5-1,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="5-2,5-3,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-4,5-5,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-6,5-7,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-8,5-9,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-10,5-11,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-12,5-13,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="5-14,5-15,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-16,5-17,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-18,5-19,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-20,5-21,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="5-22,5-23,"><span
																					class="checke_click"></span></td>
																		</tr>
																		<tr>
																			<td><%=Config.message.get("SATURDAY")%><br /><input type="checkbox" checked="checked"></td>
																			<td class="changeweektime" rel="6-0,6-1,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-2,6-3,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-4,6-5,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-6,6-7,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-8,6-9,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-10,6-11,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-12,6-13,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="6-14,6-15,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-16,6-17,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-18,6-19,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-20,6-21,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="6-22,6-23,"><span
																					class="checke_click"></span></td>
																		</tr>
																		<tr>
																			<td><%=Config.message.get("SUNDAY")%><br /><input type="checkbox" checked="checked"></td>
																			<td class="changeweektime" rel="7-0,7-1,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="7-2,7-3,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-4,7-5,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-6,7-7,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-8,7-9,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-10,7-11,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-12,7-13,"><span
																					class="checke_click"></span>
																			</td>
																			<td class="changeweektime" rel="7-14,7-15,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-16,7-17,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-18,7-19,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-20,7-21,"><span
																					class="checke_click"></span></td>
																			<td class="changeweektime" rel="7-22,7-23,"><span
																					class="checke_click"></span></td>
																		</tr>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star"></span>&nbsp;&nbsp;<%=Config.message.get("PUSH_AREA")%>：</label>
															<div class="col-md-5">
																<label style="padding-left: 0px;" class="radio-inline"><input
																		type="radio" checked="checked"
																		class="uniform activity-has"
																		onclick="$('#changeCity_id').hide();" name="isCity"
																		value="0"> <%=Config.message.get("NO_LIMIT")%></label>

																<label
																		style="margin-left: 3px;" class="radio-inline"><input
																		type="radio" class="uniform activity-has"
																		onclick="$('#changeCity_id').show();" name="isCity"
																		value="1"> <%=Config.message.get("CHOOSE_PUSH_AREA")%> </label>
															</div>
														</div>

														<div class="form-group msg_font form_group"
															 id="changeCity_id" style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-8 row_left">
																<div class="col-xs-5 row_left">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("PROJECT_TO_CHOSE")%></span>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<div class="tree tree-selectable "
																			 id="activity-areas-tree"></div>
																	</div>
																</div>
																<div class="col-xs-2" style="display: none;">
																	<button class="btn btn-default btn-block"
																			id="undo_redo_undo" type="button"><%=Config.message.get("RESET")%></button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightAll" type="button">
																		<%=Config.message.get("CHOOSE")%>&nbsp;<i class="fa fa-angle-down"></i>
																	</button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightSelected" type="button">
																		<i class="fa fa-angle-double-right"></i>
																	</button>
																</div>
																<div class="col-xs-5">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("THE_PROJECTS_WERE_CHOSE")%></span> <a
																			style="float: right"
																			onclick="$('#activity-interest-selected').html('');"
																			href="javascript:;"><%=Config.message.get("CLEAN_ALL")%></a>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<ul class="ullist" id="activity-interest-selected">
																		</ul>
																	</div>
																</div>
																<div class="col-md-5 pull-right"></div>
															</div>
														</div>
														<!-- ips -->
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_IPS")%>：</label>
															<div class="col-md-5">
																<label style="padding-left: 0px;" class="radio-inline"><input
																		type="radio" checked="checked"
																		class="uniform activity-has"
																		onclick="$('#changeIPS_id').hide();" name="isIPS"
																		value="0"> <%=Config.message.get("NO_LIMIT")%></label>
																<label
																		style="margin-left: 3px;" class="radio-inline"><input
																		type="radio" class="uniform activity-has"
																		onclick="$('#changeIPS_id').show();" name="isIPS"
																		value="1"> <%=Config.message.get("CHOSE_THE_PUSH_IPS")%> </label>
															</div>
														</div>

														<div class="form-group msg_font form_group"
															 id="changeIPS_id" style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-8 row_left">
																<div class="col-xs-5 row_left">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("PROJECT_TO_CHOSE")%></span>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<div class="tree tree-selectable "
																			 id="activity-ips-tree"></div>
																	</div>
																</div>
																<div class="col-xs-2" style="display: none;">
																	<button class="btn btn-default btn-block"
																			id="undo_redo_undo" type="button"><%=Config.message.get("RESET")%></button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightAll" type="button">
																		<%=Config.message.get("CHOOSE")%>&nbsp;<i class="fa fa-angle-down"></i>
																	</button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightSelected" type="button">
																		<i class="fa fa-angle-double-right"></i>
																	</button>
																</div>
																<div class="col-xs-5">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("THE_PROJECTS_WERE_CHOSE")%></span> <a
																			style="float: right"
																			onclick="$('#activity-ips-selected').html('');"
																			href="javascript:;"><%=Config.message.get("CLEAN_ALL")%></a>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<ul class="ullist" id="activity-ips-selected">
																		</ul>
																	</div>
																</div>
																<div class="col-md-5 pull-right"></div>
															</div>
														</div>
														<!--  Ips  -->
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star"></span>&nbsp;&nbsp;<%=Config.message.get("WEB_SITE")%>：</label>
															<div class="col-md-5">
																<label style="padding-left: 0px;" class="radio-inline"><input
																		type="radio" checked="checked"
																		class="uniform activity-has"
																		onclick="$('#changePrice_id').hide();" name="isIndustry"
																		value="0"><%=Config.message.get("NO_LIMIT")%></label>

																<label
																		style="margin-left: 3px;" class="radio-inline"><input
																		type="radio" class="uniform activity-has"
																		onclick="$('#changePrice_id').show();" name="isIndustry"
																		value="1" > <%=Config.message.get("CHOOSE_THE_WEBSITE")%> </label>
															</div>
														</div>
                                         
														<div class="form-group msg_font form_group" id="changePrice_id" style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-8 row_left">
																<div class="col-xs-5 row_left">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("PROJECT_TO_CHOSE")%></span>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<div class="tree tree-selectable "
																			 id="activity-industry-tree"></div>
																	</div>
																</div>

																<div class="col-xs-2" style="display: none;">
																	<button class="btn btn-default btn-block"
																			id="undo_redo_undo" type="button"><%=Config.message.get("RESET")%></button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightAll" type="button">
																		<%=Config.message.get("CHOOSE")%>&nbsp;<i class="fa fa-angle-down"></i>
																	</button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightSelected" type="button">
																		<i class="fa fa-angle-double-right"></i>
																	</button>
																</div>
																<div class="col-xs-5">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("THE_PROJECTS_WERE_CHOSE")%></span> <a
																			style="float: right"
																			onclick="$('#activity-industry-selected').html('');$('#priceUrl').val('');$('#priceofUrl').html('')"
																			href="javascript:;"><%=Config.message.get("CLEAN_ALL")%></a>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<ul class="ullist" id="activity-industry-selected">
																		</ul>
																	</div>
																</div>
																<div class="col-md-5 pull-right"></div>
															</div>
														</div>
														<!--高级设置TABLE-->
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label">
																<span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_USER_TYPE")%>：
															</label>
															<div class="col-sm-4" style="padding-left: 0px">
																<select id="push_user_type" class="form-control" required="required" name="pushUserType">
																	<option value="0"><%=Config.message.get("NO_LIMIT")%></option>
																	<c:if test="${isEnglish ne true}">
																		<option value="1"><%=Config.message.get("LOCAL_USERS")%></option>
																		<option value="2"><%=Config.message.get("ROAM_USERS")%></option>
																		<option value="3"><%=Config.message.get("3A_AUTHENTICATION_USER")%></option>
																	</c:if>
																	<option value="4"><%=Config.message.get("NETWORK_TRAFFIC_USER")%></option>
																</select>
															</div>
														</div>
														<input type="hidden" id="changeTime_id" required="required" name="timeIds" value="" />
														<input type="hidden" id="changegroupName_id" name="groupIds" value="" />
																<!-- INTERESTS -->
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><span
																	class="star"></span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_GROUP")%>：</label>
															<div class="col-md-5">
																<label style="padding-left: 0px;" class="radio-inline"><input
																		type="radio" checked="checked"
																		class="uniform activity-has"
																		onclick="$('#changeGroup_id').hide();" name="isGroup"
																		value="0"> <%=Config.message.get("NO_LIMIT")%></label>

																<label
																		style="margin-left: 3px;" class="radio-inline"><input
																		type="radio" class="uniform activity-has"
																		onclick="$('#changeGroup_id').show();" name="isGroup"
																		value="1"> <%=Config.message.get("CHOOSE_PUSH_AREA")%> </label>
															</div>
														</div>

														<div class="form-group msg_font form_group"
															 id="changeGroup_id" style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-8 row_left">
																<div class="col-xs-5 row_left">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("PROJECT_TO_CHOSE")%></span>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<div class="tree tree-selectable "
																			 id="activity-group-tree"></div>
																	</div>
																</div>
																<div class="col-xs-2" style="display: none;">
																	<button class="btn btn-default btn-block"
																			id="undo_redo_undo" type="button"><%=Config.message.get("RESET")%></button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightAll" type="button">
																		<%=Config.message.get("CHOOSE")%>&nbsp;<i class="fa fa-angle-down"></i>
																	</button>
																	<button class="btn btn-default btn-block"
																			id="undo_redo_rightSelected" type="button">
																		<i class="fa fa-angle-double-right"></i>
																	</button>
																</div>
																<div class="col-xs-5">
																	<div class="choices">
																		<span style="float: left"><%=Config.message.get("THE_PROJECTS_WERE_CHOSE")%></span> <a
																			style="float: right"
																			onclick="$('#activity-groups-selected').html('');"
																			href="javascript:;"><%=Config.message.get("CLEAN_ALL")%></a>
																		<div style="clear: both; height: 5px;"></div>
																	</div>
																	<div class="box-body likelist" style="overflow: auto;">
																		<ul class="ullist" id="activity-groups-selected">
																		</ul>
																	</div>
																</div>
																<div class="col-md-5 pull-right"></div>
															</div>
														</div>
														<!-- end INTERESTS -->
														
														

														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label"><span
																	class="star"></span>&nbsp;&nbsp;<%=Config.message.get("SEARCH_THE_KEY_WORDS")%>：</label>
															<div class="col-md-4 row_left">
																<input type="text" placeholder="<%=Config.message.get("PLEASE_INPUT_KEY_WORDS")%>" id="keywordInput_id" class="form-control">
															</div>
															<div class="col-md-2">
																<button class="btn btn-primary" id="addkeywordbutton"><%=Config.message.get("CREATE_POLICY_ADD")%></button>
															</div>
														</div>

														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-6 colleft">
																<ul class="ullist keywordlist">
																</ul>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-6 colleft">
																<ul class="ullist customerlist">
																</ul>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"></label>
															<div class="col-md-6 row_left">
																<span class="span_fonts"><%=Config.message.get("SUPPORTING_BROWSERS")%></span>
															</div>
														</div>
														
													</form>
												</div>
												<!--创建策略step2 END-->
												<!--预览dialog-->
												<div class="modal fade" id="box-pageImg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog modal-preview">
														<div class="modal-content">
															<div class="modal-header">
																<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
																<div class="col-md-4">
																	<div class="form-group msg_font form_group">
																		<label for="password-input" class="col-md-8 control-label row_left"><%=Config.message.get("CHECK_IN_EQUIPMENT")%></label>
																	</div>
																	<div class="form-group msg_font form_group">
																		<div for="password-input"
																			 class="col-md-12 row_left control-label">
																			<div class="hover-content animated fadeOut"
																				 style="display: block; background: url(img/erweima.jpg) no-repeat; height: 160px;">
																			</div>
																		</div>
																	</div>
																</div>
																<div class="col-md-8">
																	<div class="form-group msg_font form_group">
																		<div for="password-input"
																			 class="col-md-12 control-label">
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
												<!--创建策略step3-->
												<div id="confirm" class="tab-pane active"
													 style="display: none;">
													<form onsubmit="return false;" id="account_create_policy_id_2">
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-2 control-label"></label>
															<div class="col-md-2 row_left">
																<label class="checkbox-inline">
																	<input type="radio" id="radio_page_1" value="option2" onclick="$('#existedPage').show();$('#route').hide();"
																		value="1" checked="checked" name="optionsRadiosinline">&nbsp;&nbsp;<%=Config.message.get("EXISTING_PAGES")%>
																</label>
															</div>
															<div class="col-md-3 row_left">
																<label class="checkbox-inline">
																	<input type="radio" id="radio_page_2" value="option2" onclick="$('#existedPage').hide();$('#route').show();"
																		value="2" name="optionsRadiosinline">&nbsp;&nbsp;<%=Config.message.get("EXISTING_PAGE_PATH")%>
																</label>
															</div>
														</div>
														<div id="myTabContent" class="tab-content">
															<!--已有页面-->
															<div class="tab-pane fade in active" id="existedPage">
																<div class="form-group msg_font form_group">
																	<label for="password-input"
																		   class="col-md-2 control-label"></label>
																	<div class="col-md-3 row_left">
																		<input type="text" name="url" class="form-control"
																			   placeholder="<%=Config.message.get("PLEASE_INPUT_THE_PAGE_NAME")%>">
																	</div>
																	<div class="col-md-2">
																		<button type="button" class="btn btn-danger"
																				onclick="createNewPage();$('#account_create_policy_id_2').hide();$('#createPage').show();$('#nextPage').hide();$('#pageSuc').show();"><%=Config.message.get("ADD_PAGE")%></button>
																	</div>
																</div>
																<div class="form-group msg_font form_group">
																	<label for="password-input"
																		   class="col-md-2 control-label"></label>
																	<div class="col-md-8">
																		<div id="" class="row tab-pane fade in active">
																			<!-- BOX -->
																			<div class="box">
																				<div class="form-group msg_font form_group">
																					<div class="col-md-12 row_left" style="height:300px; border:1px #cdd2d2 solid; overflow:hidden; overflow-y:scroll; overflow-x:none;">
																						<c:forEach var="page" items="${result.materials}"
																								   varStatus="status">
																							<div class="col-md-6 row_left">
																								<label class="checkbox-inline">
																									<input  type="radio" id="pagename" name="pagename" onclick="$('#pagenameMag').hide();" value="${page.pageURL}">&nbsp;&nbsp; ${page.pageName} ${page.terminalType==1?"phone":page.terminalType==2?"PC":"PAD"}
																										<%-- <td>${page.pageURL}</td> --%>
																								</label>
																							</div>
																						</c:forEach>
																					</div>
																				</div>
																			</div>
																			<div class="form-policy has-error">
																				<label class="col-md-3"></label> <label class="control-label" for="inputWarning"  style="font-weight:bold;"  id="pagenameMag"></label>
																			</div>

																			<!-- /BOX -->
																</div>
																</div>
																</div>
															</div>
															<!--已有页面-->
															<!--外部路径-->
															<div class="tab-pane fade in active" id="route"
																 style="display: none;">
																<div class="form-group msg_font form_group">
																	<label for="password-input"
																		   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PAGE_PATH")%>：</label>
																	<div class="col-md-3">
																		<input type="text" name="pushUrl" class="form-control" id="pushUrl" placeholder="/tb/gzdx/20161025165956" onclick="$('#pagenameMag_2').hide();" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
																	</div>
																	<div class="col-md-4">
																		<div class="form-policy has-error">
																			<label class="col-md-3"></label> <label class="control-label" for="inputWarning" id="pagenameMag_2"></label>
																		</div>
																	</div>
																</div>
																<div class="form-group msg_font form_group"
																	 style="display: none;">
																	<label for="password-input" class="col-md-2 control-label"></label>
																	<div class="col-md-6 col_left row_left">
																		<div id="" class="row tab-pane fade in active">
																			<!-- BOX -->
																			<div class="box">
																				<div class="form-group msg_font form_group">
																					<div class="col-md-12 row_left">
																						<a class="thumbnail" href="#">
																							<img alt="" src="img/gallery/Mac.png">
																						</a>
																					</div>
																				</div>
																			</div>
																			<!-- /BOX -->
																		</div>
																	</div>
																</div>
															</div>
															<!--外部路径-->
														</div>
													</form>
													<div class="row" id="createPage" style="display: none;">
														<div class="col-md-6">
															<a href="#" class="thumbnail" >
																<iframe style="width: 100%;max-width:430px;height:500px;border: 1px solid #ddd;" id="html_temp_frame" allowtransparency="true" class="mv-thumb" tabindex="-1" aria-hidden="true"></iframe>
															</a>
														</div>
														<div class="col-md-6">
															<!-- BOX -->
															<div class="box border box_padding">
																<div class="box_title">
																	<ul id="myTab" class="nav nav-tabs" style="text-align: center;">
																		<li class="col-md-4 active">
																	   	<a href="#home" data-toggle="tab"   onclick="createNewPage();"><%=Config.message.get("PAGE_CREATE_TEMPLATE")%></a>
																	   </li>
																	   <li class="col-md-4" id="setMetrial">
																	   	<a href="#ios" data-toggle="tab" onclick="setClick()" >
																			<%=Config.message.get("PAGE_CREATE_SET")%>
																	   	</a>
																	   </li>
																	   <li class="col-md-4" id="setContent">
																	   	<a href="#content_page" data-toggle="tab" onclick="setContent()"><%=Config.message.get("PAGE_CREATE_CONTENT")%></a>
																	   </li>
																	</ul>
																</div>
																<div class="box_body font-400">
																	<div id="myTabContent" class="tab-content" style="heigth:45px;">
																	   <div class="tab-pane fade in active" id="home">
																	   		<!-- BOX -->
																			<div class="box">
																				<div class="box_title1">
																					<ul id="templete" class="nav" style="text-align: center;">
																					  <li class="col-md-4" id="phoneLabel">
																					  <a href="javascript:;"  data-toggle="tab"><%=Config.message.get("PAGE_PHONE")%></a>
																					   </li>
																					  <li class="col-md-4" id="pcLabel">
																					   <a href="javascript:;" data-toggle="tab">PC</a>
																					   </li>
																					   <li class="col-md-4" id="padLabel">
																					   	<a href="javascript:;" data-toggle="tab">PAD</a>
																					   </li>
																					</ul>
																				</div>
																				<div class="box-body font-400"  style="height:400px; overflow-x:hidden; overflow-y:scroll;" >
																				<div id="myTabContent" class="tab-content">
																				   <!--手机-->
																				   <div class="tab-pane active" id="templateList">
																				      <form class="form-horizontal" enctype="multipart/form-data" method="post" action="">
																				   			<div class="form-group msg_font form_group" id="list-page">
																				   			
																							</div>
																				   		</form>
																				   </div>
																				   <!--手机 END-->
																				   <%-- <div class="dataTables_footer clearfix">
																				    <jsp:include page="../layout/pagination.jsp"><jsp:param value="material/page/createPage?tType=${condition.tType}" name="actionName"/></jsp:include>
																				   </div> --%>
																				</div>
																			</div>
																			</div>
																	   </div>
																	   <div class="tab-pane fade" id="ios">
																	     <!-- BOX -->
																			<div class="box">
																				<div class="box_title1" id="box_set">
																					<ul id="setTab" class="nav" style="text-align: center;">
																						<li class="col-md-6">
																					   	<a href="#aa" data-toggle="tab" id="taba"><%=Config.message.get("PAGE_CREATE_BASE_SET")%></a>
																					   </li>
																					   <li class="col-md-6 ">
																					   	<a href="#bb" data-toggle="tab" id="tabb">
																							<%=Config.message.get("PAGE_CREATE_CUSTOM_SET")%>
																					   	</a>
																					   </li>
																					</ul>
																				</div>
																				<div class="box-body font-400" style="height:400px; overflow-x:hidden; overflow-y:scroll;">
																					<div id="myTabContent" class="tab-content">
																					<!--基本设置-->
																					   <div class="tab-pane fade in" id="aa">
																					      <form class="form-horizontal" enctype="multipart/form-data" method="post" action=""> 
																				           <div class="form-group msg_font form_group" style="display: none">
																				            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PAGE_NAME")%>：</label>
																				            <div class="col-md-4"> 
																				             <input type="text" name="preMoney" class="form-control"> 
																				            </div> 
																				           </div> 
																				           <div class="form-group msg_font form_group"> 
																				            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PAGE_CREATE_WIDTH")%>：</label>
																				            <div class="col-md-3"> 
																				             <input type="text" name="width" class="form-control" maxlength="4" adModel="width" id="width"
																				             onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
																								onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
																								onblur='changeWidth($(this))'> 
																				            </div>
																				            <div class="col-md-1"> 
																				             <span class="span_font">px</span> 
																				            </div>
																				           </div> 
																				           <div class="form-group msg_font form_group"> 
																				            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PAGE_CREATE_HEIGHT")%>：</label>
																				            <div class="col-md-3"> 
																				             <input type="text" name="heigth" class="form-control" maxlength="4" adModel="heigth" id="heigth"
																				             onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
																								onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
																								onblur='changeHeigth($(this))'> 
																				            </div>
																				            <div class="col-md-2">
																				             <span class="span_font">px</span>
																				            </div>
																				           </div>
																				           <div class="form-group msg_font form_group" style="display: none;"> 
																				            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PAGE_CREATE_SHOW_TIME")%>：</label>
																				            <div class="col-md-3"> 
																				             <input type="text" class="form-control" maxlength="4" adModel="showTime" id="showTime"
																				             onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
																								onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
																								onblur='changeShowTime($(this))'> 
																				            	
																				            </div>
																				            <div class="col-md-2"> 
																				             <span class="span_font"><%=Config.message.get("PAGE_CREATE_SECOND")%></span>
																				            </div>
																				           </div>
																				           <div class="form-group msg_font form_group" style="display: none"> 
																				            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PAGE_BACKGROUND_WEB_SITE")%>：</label>
																				            <div class="col-md-4"> 
																				             <input type="text" name="preMoney" class="form-control"> 
																				            </div> 
																				           </div>
																				           <div class="form-group msg_font form_group">
																							 <label class="col-md-3 control-label"><%=Config.message.get("PAGE_CLOSE_BTN")%>：</label>
																							 <div class="col-md-5"> 
																								 <label class="radio-inline"><input type="radio"  value="1" name="optionsRadios" class="uniform"> <%=Config.message.get("CLOSE_BTN_SHOW")%> </label>
																								 <label class="radio-inline"> <input type="radio"  value="2" name="optionsRadios"class="uniform"> <%=Config.message.get("CLOSE_BTN_HIDE")%> </label>
																							 </div>
																						  </div>
																						  <div class="form-group msg_font form_group">
																							 <label class="col-md-3 control-label"><%=Config.message.get("PAGE_CREATE_BLUR")%>：</label>
																							 <div class="col-md-5"> 
																								 <label class="radio-inline"><input type="radio" value="1" name="optionsRadios2" class="uniform"> <%=Config.message.get("YES")%> </label>
																								 <label class="radio-inline"><input type="radio" value="2" name="optionsRadios2" class="uniform"> <%=Config.message.get("NO")%> </label>
																							 </div>
																						  </div>
																				          </form>
																					   </div>
																					   <!--基本设置END-->
																					  
																					   <!--自定义设置-->
																					   <div class="tab-pane fade" id="bb">
																					      <form class="form-horizontal" enctype="multipart/form-data" method="post" action=""> 
																				           <div class="form-group msg_font form_group">
																			                    <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("INTRODUCING_STYLE")%>：</label>
																			                    <div class="col-md-5 row_left">
																			                        <textarea placeholder="" class="form-control" name="textarea" cols="5" rows="3" id="addCs"></textarea>
																			                    </div>
																			                </div>
																			                <div class="form-group msg_font form_group">
																			                    <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("INTRODUCING_SCRIPT")%>：</label>
																			                    <div class="col-md-5 row_left">
																			                        <textarea placeholder="" class="form-control" name="textarea" cols="5" rows="3" onblur="add_js($(this))" id="addJs"></textarea>
																			                    </div>
																			                </div>
																			                <div class="form-group msg_font form_group">
																			                    <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PAGE_INSTRUCTION")%>:</label>
																			                    <div class="col-md-5 row_left">
																			                        <textarea placeholder="" class="form-control" name="textarea" cols="5" rows="3"></textarea>
																			                    </div>
																			                </div>
																				          </form>
																					   </div>
																					   <!--自定义设置END-->
																					</div>
																				</div>
																			</div>
																		<!-- /BOX -->
																	   </div>
																	   <div class="tab-pane fade in" id="content_page">
																	     	<!-- BOX -->
																			<div class="box" id="templete_box">
																				<div class="box_title1 col-md-12 img-scroll" id="box_content">
																				<span class="prev col-md-1"><</span>
																				<div class="menu row img-list">
																					<ul id="group" class="nav" style="text-align: center;">
																					  <!-- <li class="col-md-4 active">
																					  <a href="#content_a"  data-toggle="tab">按钮配置</a>
																					   </li>
																					  <li class="col-md-4">
																					   <a href="#content_b" data-toggle="tab">查询页面配置</a>
																					   </li>-->
																					</ul>
																					<span class="next pull-right col-md-1">></span>
																				</div>
																			</div>
																				 <!-- 内容按钮配置 -->
																				 <div class="box-body font-400" style="height:368px; overflow-x:hidden; overflow-y:scroll;">
																					<div class="tab-content" id="content_set">
																					    
																					   <!-- 加载group按钮内容-->
																					 </div>
																				</div>
																				
																			</div>
																		<!-- /BOX -->
												  					 </div>
																    </div>
															    </div>
															</div>
														</div>
													</div>
													<!-- 创建页面END -->
												</div>
												<!--创建策略step3 END-->
												<!--创建策略step4-->
												<div id="policy_submit" class="tab-pane active"
													 style="display: none;">
													<form onsubmit="return false;"
														  id="account_create_policy_id_3">
														  <div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-1 control-label"></label>
															<div class="col-sm-12">
																<div class="box-title title_account">
																	<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></span>
																	<div class="tools">
																		<a href="javascript:;" class="reload"
																		   onclick="editeBusiness(0,'accountclass');"><i
																				class="fa fa-edit"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATEPOLICY_ADVERTISER_NAME")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="customerName">---</span>
															</div>
														</div>
														  <div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-1 control-label"></label>
															<div class="col-sm-12">
																<div class="box-title title_account">
																	<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("CREATEPOLICY_CHOOSE_ACTIVITY")%></span>
																	<div class="tools">
																		<a href="javascript:;" class="reload"
																		   onclick="editeBusiness(0,'accountclass');"> <i
																				class="fa fa-edit"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATEPOLICY_ADVERTISER_NAME")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="activityName">---</span>
															</div>
														</div>
														  
														  
														  
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-1 control-label"></label>
															<div class="col-sm-12">
																<div class="box-title title_account">
																	<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("CREATE_POLICY_POLICY_INFORMATION")%></span>
																	<div class="tools">
																		<a href="javascript:;" class="reload"
																		   onclick="editeBusiness(0,'accountclass');"> <i
																				class="fa fa-edit"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_NAME")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="policyName">---</span>
															</div>
														</div>

														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"> <%=Config.message.get("CREATE_POLICY_PRIORITY")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="policyLevel">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_INTERVIAL")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="pushInterval">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_SHOW_LIMIT")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="pushLimit" id="pushLimitSpeed">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group" style="display: none">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_DAY_LIMIT")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="policyDayLimit" id="policy_day_limit：">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_TERMINAL_TYPE")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="terminalType">----</span>
															</div>
														</div>
													<c:if test="${isEnglish eq true}">
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_WAYS_TO_BUY")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="buyType">----</span>
															</div>
														</div>
													</c:if>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_SPEED")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="speedType">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group" style="display: none;">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("PUSH_TYPE")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="policyType">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_THE_WAY_OF_SHOW")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="showType">----</span>
															</div>
														</div>
													<c:if test="${isEnglish eq true}">
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_AD_TYPE")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="adType">----</span>
															</div>
														</div>

														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_INDUSTRY_ATTRIBUTES")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="industry-bear">----</span>
															</div>
														</div>
													</c:if>
														<!-- <div class="form-group msg_font form_group">
                                                                <label for="password-input"
                                                                    class="col-md-3 control-label"><span
                                                                    class="star">*</span>&nbsp;&nbsp;means of purchase：</label>
                                                                <div class="col-md-2">
                                                                    <input type="text" name="pushLimit" id="pushLimit" class="form-control"
                                                                        data-type="number" required="required" placeholder="Fill with digital" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
                                                                </div>
                                                                <div class="col-md-2">
                                                                    <span class="span_font">times/person/day</span>
                                                                </div>
                                                                <div class="col-md-2">
                                                                        <div class="form-policy has-error">
                                                                            <label class="col-md-3"></label> <label
                                                                                class="control-label" for="inputWarning"
                                                                                id="policyLimitMsg"></label>
                                                                        </div>
                                                                    </div>
                                                            </div> -->
														<div class="form-group msg_font form_group" style="display: none">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("POLICY_TYPE")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="policyShow">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("POLICY_TOTAL_SHOW")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="showLimit">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_SHOW_TIME")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="showTime">----</span>
															</div>
														</div>

														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_CLICK")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="hitLimit">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_SHOW")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="showNum">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_CLICK")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="hitNum">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_DESCRIPTION")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="Description">----</span>
															</div>
														</div>
														<div class="divide-20"></div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-1 control-label"></label>
															<div class="col-sm-12">
																<div class="box-title title_account">
																	<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("POLICY_PUSH_PLAN")%>:</span>
																	<div class="tools">
																		<a href="javascript:;" class="reload"
																		   onclick="editeBusiness(1,'paymentclass');"> <i
																				class="fa fa-edit"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
														<div class="divide-20"></div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_TIME")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="policyStartTime">----</span>--<span
																	class="span_font proshowdata" data-name="policyEndTime">----</span>
															</div>
														</div>

														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PUSH_TIME_PERIOD")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="activityNamedddd">----</span>
															</div>
														</div>
													<c:if test="${isEnglish ne true}">
														<div class="form-group msg_font form_group">
															<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_USER_TYPE")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="pushUserType">----</span>
															</div>
														</div>
													</c:if>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_GROUP")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="groupIds">----</span>
															</div>
														</div>
														<!-- ipses -->
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_IPS")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="ipses">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("PUSH_AREA")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="areas">----</span>
															</div>
														</div>
														
													<c:if test="${isEnglish eq true}">
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("DIRECTED_WEB_SITE")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="Urllist">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("BID")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="newunitPrice">----</span>
															</div>
														</div>
													</c:if>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("WEB_SITE")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata"
																	  data-name="urlDomain">----</span>
															</div>
														</div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("KEY_WORDS")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="kewWord">----</span>
															</div>
														</div>
													<c:if test="${isEnglish eq true}">
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-3 control-label"><%=Config.message.get("ADVERTISER_CUSTOMERS")%>：</label>
															<div class="col-sm-4">
																<span class="span_font proshowdata" data-name="customerName">----</span>
															</div>
														</div>
													</c:if>
														<!-- <div class="form-group msg_font form_group">
															<label for="password-input"
																class="col-md-3 control-label"><span
																class="star"></span>&nbsp;&nbsp;unitPrice：</label>
															<div class="col-sm-4">
																<input type="text"
															class="form-control" value=""  id="priceUrl"  class="span_font proshowdata" data-name="unitPrice">
															</div>
															</div>
														 -->
														<!-- 	<div class="form-group msg_font form_group">
															<label for="password-input"
																class="col-md-3 control-label">Url Price：</label>
															<div class="col-sm-4 row_left">
															<input type="text"  id="priceUrl"  name="unitPrice" lass="form-control">
														</div>

														</div> -->

														<div class="divide-20"></div>
														<div class="form-group msg_font form_group">
															<label for="password-input"
																   class="col-md-1 control-label"></label>
															<div class="col-sm-12">
																<div class="box-title title_account">
																	<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("POLICY_PUSH_MATERIAL")%></span>
																	<div class="tools">
																		<a href="javascript:;" class="reload"
																		   onclick="editeBusiness(2,'confirmclass');"> <i
																				class="fa fa-edit"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
														<div class="divide-20"></div>
														<div class="form-group msg_font form_group row_left">
															<label for="password-input"
																   class="col-md-2 control-label"></label>
															<div class="col-sm-4">
																<a class=" thumbnail config" type="button"
																   style="display: none;" data-toggle="modal"
																   href="#box-pageImg" title="view"> <img
																		src="img/gallery/Mac.png" alt="">
																</a>
																<div class="caption">
																	<p class=" proshowdata" data-name="pushUrl"><%=Config.message.get("CREATE_POLICY_METRAIL_NAME")%></p>
																</div>
															</div>
														</div>
													</form>
												</div>
												<!--创建策略step4 END-->
											</div>
										</div>
										<div class="wizard-buttons">
											<div class="row" id="nextPage">
												<div class="col-md-12">
													<div class="col-md-offset-3 col-md-9">
														<a class="btn btn-primary nextBtn btn_left createPrev"
														   href="javascript:;" rel="0" style="display: none;"><i
																class="fa fa-arrow-circle-left"></i> <%=Config.message.get("CREATE_POLICY_BACK")%>
														</a>
														<a class="btn btn-primary nextBtn" href="javascript:;"><%=Config.message.get("CREATE_POLICY_CANCEL")%></a>
														<a class="btn btn-primary nextBtn btn_left createNext" rel="1" href="javascript:;"><%=Config.message.get("CREATE_POLICY_NEXT")%></a>
														<a class="btn btn-success submitBtn createPolicySumit" href="javascript:;" style="display: none;"> <%=Config.message.get("CREATE_POLICY_OK")%> </a>
													</div>
												</div>
											</div>
											<div class="row" id="pageSuc" style="display: none;">
												<div class="col-md-8">
													<div id="btn_group" class="col-md-offset-7">
														<a class="btn btn-md btn-default" href="javascript:;" onclick="$('#account_create_policy_id_2').show();$('#createPage').hide();$('#nextPage').show();$('#pageSuc').hide();"><%=Config.message.get("CANCEL")%></a>
														<a class="btn btn-md btn-primary btn_left" href="javascript:;" onclick="createPageByTemplate(this);"><%=Config.message.get("PAGE_ADD")%></a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
										<!--创建dialog-->
										<div class="modal fade" id="box-dialog-page" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="false" data-backdrop="static">
										<div class="modal-dialog">
										  <div class="modal-content">
											<div class="modal-header">
											  <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
											  <h4 class="modal-title"><%=Config.message.get("PAGE_ADD_PAGE")%></h4>
											</div>
											<div class="panel-body"> 
									          <form action="material/page/create" method="post" class="form-horizontal" id="form-create-page"> 
									          	<div class="form-group msg_font form_group"> 
										            <label class="col-md-3 control-label" for="password-input"><span class="star">*</span><%=Config.message.get("PAGE_NAME")%>：</label>
										            <div class="col-sm-5 row_left"> 
										             <input type="text" id="pageName" class="form-control" name="pageName" placeholder="<%=Config.message.get("PAGE_INPUT_PAGE_NAME")%>" required='required' maxlength="20"/>
										            </div>
										            <div class="has-error" style="display: none;"
																			id="errordiv">
																			<label class="col-md-4"></label> <label class="control-label"
																				for="inputWarning" id="errorMsg" style="line-height:9px;"></label>
																		</div>
									           </div> 
									           <input type="hidden" name="templateId"  value="" id="page-templateId"/>
									           <div class="form-group msg_font form_group">
								                    <label class="col-md-3 control-label" for="password-input"><%=Config.message.get("PAGE_DESCRIPTION")%>：</label>
								                    <div class="col-md-7 row_left">
								                        <textarea placeholder="" class="form-control" name="pageRemark" cols="5" rows="3" maxlength="200"></textarea>
								                    </div>
								                </div>
									          </form> 
									         </div>
											<div class="modal-footer">
											  <button data-dismiss="modal" class="btn btn-default" type="button" id="button-create-template-cancel"><%=Config.message.get("CANCEL")%></button>
											  <button type="button" class="btn btn-primary" id="button-create-page-ok"><%=Config.message.get("SAVE")%></button>
											</div>
										  </div>
										  <input type="hidden" id="htmlpath">
										</div>
									  </div>
									  <!--创建dialog end-->
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<!--创建策略-->
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<script type="text/javascript" src="js/bootstrap-treeview.js"></script>
<script type="text/javascript" src="${basePath }js/createpolicy.js"></script>
<%-- <script type="text/javascript" src="${basePath }js/jquery-1.8.3.min.js"></script>   --%>

<script type="text/javascript">
	$(function(){
		$(".timeChange_tb").find("th:first input").click(function(){  //全选/全不选
			if($(this).is(":checked")){   //全选
				$(this).parents("table").find("input").each(function(){
					$(this).prop("checked","checked");
				});
				$(this).parents("table").find("td.changeweektime").each(function(){
					$(this).empty();
					var str = '<span class="checke_click"></span>';
					var $span = $(str);
					$(this).append($span);
				});
			}else{ //全不选
				$(this).parents("table").find("input").each(function(){
					$(this).prop("checked", false);
				});
				$(this).parents("table").find("td.changeweektime").each(function(){
					$(this).children().remove();
				});
			}
		});

		$(".timeChange_tb").find("input").each(function(){
			$(this).on('click',selectCol);
		});

		$(".timeChange_tb").find("tr").each(function(){
			$(this).find("td:first input").on('click',selectRow);
		});
		//搜索广告主
		$("#exampleInputSearchCutomor").bind("keyup",function(){
			var _cutomerP=$(this).val();
			if(_cutomerP.trim()!=''){
				$(".customerlistclass").parent().hide();
				$.each($(".customerlistclass"),function(i,n){
					if($(n).html().match(_cutomerP)){
						$(n).parent().show();
					}
				});
			}else{
				$(".customerlistclass").parent().show();
			}
		});
		
		//搜索广告主
		$("#exampleInputSearchactivity").bind("keyup",function(){
			var _cutomerP=$(this).val();
			if(_cutomerP.trim()!=''){
				$(".activitylistclass").parent().hide();
				$.each($(".activitylistclass"),function(i,n){
					if($(n).html().match(_cutomerP)){
						$(n).parent().show();
					}
				});
			}else{
				$(".activitylistclass").parent().show();
			}
		});
	});

	function selectRow(){  //选择行
		var bool = $(this).is(":checked");
		if(bool){
			$(this).parents("tr").find("td:gt(0)").each(function(){
				$(this).empty();
				var str = '<span class="checke_click"></span>';
				var $span = $(str);
				$(this).append($span);
			});
			//$(this).parents("tr").children("td").append($span);
		}else{
			$(this).parents("tr").find("td:gt(0)").each(function(){
				$(this).children().remove();
			});
		}
	}

	function selectCol(){   //选择列
		var index = Number($(".timeChange_tb input").index($(this)));
		var bool = $(this).is(":checked");
		if(index!=0){
			if(bool){
				$("tbody").find("tr").each(function(){
					$(this).children("td").eq(index).empty();
					var str = '<span class="checke_click"></span>';
					var $span = $(str);
					$(this).children("td").eq(index).append($span);
				});
			}else{
				$("tbody").find("tr").each(function(){
					$(this).children("td").eq(index).empty();
				});
			}
		}
	}
</script>    

<script type="text/JavaScript">
	function selectCity(){
		var  collCities = [['----option----']
			,['E-COMMERCE','ONLINE GAMES','OTHERS']
			,['CORPORATE & PUBLIC SERV ADV','EDUCATION','FINANCIAL','MEDIA & PROMOTION','PERSONAL SERVICES','PROPERTY','RETAIL','TRANST, TRAVEL, RECREATION']
			,[' EQUIPMENT & APPLIANCES','PRODUCTS/SUPPLIES']];
//                var arr = {"****":['***','***','***','***']};
		//获取两个下拉菜单对象。
		var oSelNode = document.getElementById("selid");
		var oSubSelNode = document.getElementById("subselid");
		//获取到底选择的是哪个。
		var index = oSelNode.selectedIndex;
		//通过角标到容器去获取对应的数组。
		var arrCities = collCities[index];
		//将子菜单中的内容清空一下。
//                for(var x=0;x<oSubSelNode.options.length; ){
//                    oSubSelNode.removeChild(oSubSelNode.options[x]);
//                }
		//清除动作
		oSubSelNode.length = 0;
		//遍历这个数组。并将这个数组的元素封装成option对象，添加到子菜单中
		for(var x=0; x<arrCities.length; x++){
			var oOptNode = document.createElement("option");
			oOptNode.innerHTML = arrCities[x];

			oSubSelNode.appendChild(oOptNode);
		}

	}
</script>
<script>

	$(function(){
		$("input[name=pagename]").click(function(){
			var pagename=$('input:radio[name="pagename"]:checked').val();
			$("#pushUrl").val(pagename);
		});
	});
</script>
<script>
	var policyStepArr= ["#ggmanager","#hdmanager","#account","#payment", "#confirm", "#policy_submit"];
	var _pushSpeed='';
	//步骤4时加载数据
	function policyPreview(paramsObj){
		$("#account_create_policy_id_3").find(".proshowdata").each(function(){
			var key = $(this).attr('data-name');
			if(paramsObj[key]!=null && paramsObj[key]==1){
				$(this).show();
			}else{
				$(this).show();
			}
		});
		var timeType=$("select[name='timeType']").val();
		//var obj= initp();
		$("#account_create_policy_id_3").find(".proshowdata").each(function(){
		 	//选择群组
			var _groupList='';
			$('.changegrouplist').each(function(i,n){
				_groupList+=$(n).html()+',';
			});
		 	
		 	
			//选择IPS
			var _ipsList='';
			$('.changeipslist').each(function(i,n){
				_ipsList+=$(n).html()+',';
			});
			if(_ipsList==''){
				_ipsList='<%=Config.message.get("NO_LIMIT")%>';
			}
			//选择地区
			var _areList='';
			$('.changearealist').each(function(i,n){
				_areList+=$(n).html()+',';
			});
			if(_areList==''){
				_areList='<%=Config.message.get("NO_LIMIT")%>';
			}
			//选择行业标签对应的URL
			var _urlList='';
			$('.changeurllist').each(function(i,n){
				_urlList+=$(n).html()+',';
			});
			if(_urlList==''){
				_urlList='<%=Config.message.get("NO_LIMIT")%>';
			}
			var _pushUserType = "";
			var _type_value = $("#push_user_type").val();
			if(_type_value==1){
				_pushUserType="<%=Config.message.get("LOCAL_USERS")%>";
			}else if(_type_value==2){
				_pushUserType="<%=Config.message.get("ROAM_USERS")%>";
			}else if(_type_value==3){
				_pushUserType="<%=Config.message.get("3A_AUTHENTICATION_USER")%>";
			}else if(_type_value==4){
				_pushUserType="<%=Config.message.get("NETWORK_TRAFFIC_USER")%>";
			}else{
				_pushUserType="<%=Config.message.get("NO_LIMIT")%>";
			}
			var key=$(this).attr("data-name");
			switch(key){
				case "policyName":$(this).html(decodeURIComponent(paramsObj[key]));break;
				case "Ctime":$(this).html(decodeURIComponent(paramsObj[key]));break;
				case "newunitPrice":$(this).html(decodeURIComponent(paramsObj[key]));break;
				case "policyLevel":$(this).html(paramsObj[key]);break;
				case "pushInterval":timeType==2?$(this).html(paramsObj[key]+"<%=Config.message.get("CREATE_POLICY_MINUTES")%>"):timeType==3?$(this).html(paramsObj[key]+"<%=Config.message.get("CREATE_POLICY_HOUR")%>"):$(this).html(paramsObj[key]+"<%=Config.message.get("CREATE_POLICY_SECOND")%>");break;
				case "policyDayLimit":$(this).html(paramsObj[key]+"<%=Config.message.get("TIMES_POLICY_DAY")%>");break;
				case "pushLimit":$(this).html(paramsObj[key]+" <%=Config.message.get("TIMES_PERSON_DAY")%>");break;
				case "terminalType":paramsObj[key]==1?$(this).html("phone"):$(this).html("PC/PAD");break;
				case "buyType":paramsObj[key]==1?$(this).html("CPM"):$(this).html("CPC");break;
				case "speedType":paramsObj[key]==1?$(this).html("<%=Config.message.get("CREATE_POLICY_UNIFORM_SPEED")%>"):$(this).html("<%=Config.message.get("CREATE_POLICY_QUICKLY_PUSH")%>");break;
				//case "policyType":paramsObj[key]==1?$(this).html("ad"):$(this).html("banner");break;
				case "policyShow":paramsObj[key]==1?$(this).html("<%=Config.message.get("PUSH_ONLINE")%>"):$(this).html("<%=Config.message.get("PUSH_OUTLINE")%>");break;
				case "showType":paramsObj[key]==1?$(this).html("<%=Config.message.get("CREATE_POLICY_REPLACE")%>"):paramsObj[key]==2?$(this).html("<%=Config.message.get("CREATE_POLICY_FLOAT")%>"):paramsObj[key]==3?$(this).html("<%=Config.message.get("CREATE_POLICY_PLACE_ON_BOTH_SIDES")%>"):paramsObj[key]==4?$(this).html("<%=Config.message.get("HANGING_ON_THE_EDGE")%>"):paramsObj[key]==3?$(this).html("<%=Config.message.get("PUT_ON_THE_BACK")%>"):$(this).html("toolbar");break;
				case "showLimit":$(this).html(paramsObj[key]);break;
				case "adType":paramsObj[key]==1?$(this).html("corporate branding"):paramsObj[key]==2?$(this).html("direct traffic(internet)"):paramsObj[key]==3?$(this).html("event"):paramsObj[key]==4?$(this).html("government"):paramsObj[key]==5?$(this).html("brand event"):paramsObj[key]==6?$(this).html("price promotion"):paramsObj[key]==8?$(this).html("product branding/intro"):paramsObj[key]==8?$(this).html("public sercive adcertisment"):$(this).html("others");break;
				//case "industry-bear":paramsObj[key]==10?$(this).html("internet"):paramsObj[key]==16?$(this).html("services"):paramsObj[key]==7?$(this).html("household"):paramsObj[key]==1?$(this).html("apparel/personal"):paramsObj[key]==2?$(this).html("automotive&accessories"):paramsObj[key]==3?$(this).html("baby&maternity products"):paramsObj[key]==4?$(this).html("beverages"):paramsObj[key]==5?$(this).html("food"):paramsObj[key]==9?$(this).html("industrial products"):paramsObj[key]==14?$(this).html("medicines/pharmaceuticals"):paramsObj[key]==15?$(this).html("offfice eqpt,computer,communications"):paramsObj[key]==25?$(this).html("amoking&accessories"):paramsObj[key]==25?$(this).html("toileries&cosmetics"):paramsObj[key]==6?$(this).html("government"):$(this).html("others");break;
				case "hitLimit":$(this).html(paramsObj[key]);break;
				case "showTime":$(this).html(paramsObj[key]);break;
				case "showNum":$(this).html(paramsObj[key]);break;
				case "hitNum":$(this).html(paramsObj[key]);break;
				case "Description":$(this).html(decodeURIComponent(paramsObj[key]));break;
				case "policyStartTime":$(this).html(paramsObj[key]);break;
				case "policyEndTime":$(this).html(paramsObj[key]);break;
				case "groupIds":$(this).html(_groupList);break;
				case "ipses":$(this).html(_ipsList);break;
				case "areas":$(this).html(_areList);break;
				case "Urllist":$(this).html(_urlList);break;
				case "urlDomain":$(this).html(decodeURIComponent(paramsObj[key]));break;
				case "kewWord":$(this).html(decodeURIComponent(paramsObj[key]));break;
				case "pushUrl":$(this).html('<%=Config.message.get("MATERIAL_PATH")%>：'+decodeURIComponent(paramsObj[key]));break;
				case "pushUserType":$(this).html(_pushUserType);break;
				case "customerName":$(this).html($("input[name='customerName']:checked").val().split("_")[1]);$("input[name='customerNameList']").val($("input[name='customerName']:checked").val().split("_")[0]);break;//广告主
				case "activityName":
					var _activitParm="",_activitIds='';
					$.each($("input[name='activityNamekey']:checked"),function(i,n){
						_activitIds+=$(n).val().split("_")[0]+",";
						_activitParm+=$(n).val().split("_")[1]+" ";
					});
					$("input[name='activityNameList']").val(_activitIds);
					$(this).html(_activitParm);break;//活动名
			}
		});
	}
	var formatDateTime = function (date) {  
	    var y = date.getFullYear();  
	    var m = date.getMonth() + 1;  
	    m = m < 10 ? ('0' + m) : m;  
	    var d = date.getDate();  
	    d = d < 10 ? ('0' + d) : d;  
	    var h = date.getHours();  
	    var minute = date.getMinutes();  
	    minute = minute < 10 ? ('0' + minute) : minute;  
	    return y + '-' + m + '-' + d+' '+h+':'+minute;  
	}; 
	 function addActivityCreart() {
			var activityName = $("input[name='activity_name']").val().trim();
			$("input[name='customer_name']").val($("input[name='customerName']:checked").val().split("_")[1]);
			
			var data_Id = $("input[name='customerName']:checked").val().split("_")[0];
			var activityDesc=$("#activity_desc").val();
			if (activityName == "") {
				$("input[name='activity_name']").next().html("<%=Config.message.get("CREATEPOLICY_PLEASEINPUT_ADVERTISER_NAME")%>");
				$("input[name='activity_name']").next().show();
				return;
			}
			$.ajax({
	            url: "activity/add",
				method: "post",
	            data:{"activityName":activityName,"data_Id":data_Id,"activityDesc":activityDesc},
	            dataType:"json",
	            success:function(data){
	            	//
	            	var date = new Date();        
	            	time1=formatDateTime(date); 
	            	
	            	var _html='<tr><td><input type="checkbox" value="'+data.result+'_'+activityName+'" name="activityNamekey" checked="checked" />0</td>\
						<td class="activitylistclass">'+activityName+'</td>\
						<td>'+$("input[name='customerName']:checked").val().split("_")[1]+'</td>\
						<td>0</td>\
						<td>${userName}</td>\
						<td>'+time1+':00</td></tr>';
						alert('success');
					$("#activitylist_Id").prepend(_html);
	            }
	        });
		}
	//点击每步进行编辑
	function editeBusiness(_temp,_obj){
		$('.'+_obj).siblings().removeClass('active');
		$('.'+_obj).addClass('active');
		var _tempP=_temp;
		$('#create_step_Id').val(_tempP);
		if(_tempP==0){
			$('.createPrev').hide();
		}else {
			$('.createPrev').show();
		}
		if(_tempP==3){
			$('.createNext').hide();
			$('.createPolicySumit').show();
		}else {
			$('.createNext').show();
			$('.createPolicySumit').hide();
		}
		for(i=0;i<4;i++){
			$(policyStepArr[i]).hide();
		}
		$(policyStepArr[_tempP]).show();
	}

	$(document).ready(function() {
		$('#doubleDate_id').daterangepicker( {},function(start,end){
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
				}
		);
	});

	$(function(){
		 //上一步
		 $('.createPrev').click(function(){
			 var _step=$('#create_step_Id').val();
			_step--;
			 for(i=0;i<=5;i++){
				$(policyStepArr[i]).hide();
			}
			 $(policyStepArr[_step]).show();
			 if(_step==0){
					$('.createPrev').hide();
				}else {
					$('.createPrev').show();
				}
				if(_step==5){
					$('.createNext').hide();
					$('.createPolicySumit').show();
				}else {
					$('.createNext').show();
					$('.createPolicySumit').hide();
				}
				$('.nav-justified li').removeClass('active');
				$('.nav-justified li').eq(_step).addClass('active');
				$('#create_step_Id').val(_step);
		 });
		//下一步
		$('.createNext').click(function(){
			var _step=$('#create_step_Id').val();
			var _fromStep=_step;
			var _upDown=$(this).attr('rel');
			var valateflag=true;
			//选择活动
			if(_fromStep==0){
				$("input[name='customer_name']").val($("input[name='customerName']:checked").val().split("_")[1]);
			}
			if(_fromStep==1){
				//activityNamekey
				
				var _actiTemp=0;
				$.each($("input[name='activityNamekey']:checked"),function(i,n){
					
					_actiTemp=1;
				});
				if(_actiTemp==0){
					valateflag=false;
					alert('Please choose activity');
					return false;
				}
				
			}
			//策略基本信息
			if(_fromStep==2){
				var policyName=$("#policyName").val();
				if(policyName.trim().length==0){
					$("#policyNameMsg").show();
					$("#policyNameMsg").html("<%=Config.message.get("PLEASE_INPUT_POLICYNAME")%>");
					return false;
				}
				else{
					$("#policyNameMsg").hide();
				}
				if(policyName.trim().length>20){
					$("#policyNameMsg").show();
					$("#policyNameMsg").html("<%=Config.message.get("NO_MORETHAN_20WORDS")%>");
					return false;
				}
				else{
					$("#policyNameMsg").hide();
				}
				var pushInterval=$("#pushInterval").val();
				if(pushInterval.trim().length==0){
					$("#policcPushMsg").show();
					$("#policcPushMsg").html("<%=Config.message.get("PLEASE_SET_PUSH_INTERVAL")%>");
					return false;
				}
				else{
					$("#policcPushMsg").hide();
				}
				var _push_limit_ = $("#pushLimit").val().trim();
				if(_push_limit_==""){
					$("#pushLimitMsg").html("<%=Config.message.get("PLEASE_SET_USER_SHOWLIMIT")%>");
					$("#pushLimitMsg").show();
					return ;
				}else{
					$("#pushLimitMsg").hide();
				}

				var _policy_show = $("#showLimit").val().trim();
				var radio_2 = document.getElementById("optionsSpeed1");
				if((radio_2.checked == true) && (_policy_show=="")){
					$("#policyLimitMsg").html("<%=Config.message.get("PLEASE_SET_USER_SHOWLIMIT")%>");
					$("#policyLimitMsg").show();
					return ;
				}else{
					$("#policyLimitMsg").hide();
				}
              
				var target=$('#account_create_policy_id_'+_fromStep);
				target.find("input[required='required']").each(function(){
					if($(this).val()==""&&$(this).attr()!='Description'){
						var _inName=$(this).attr('name');
						if(_onlineTemp!=2&&_inName!='pushOtherUrl'){
							valateflag=false;
							$(this).focus();
							return false;
						}
					}
				});


				if($("input[name='policyType']:checked").val()==4){

					var pushOtherUrl=$("#pushOtherUrl").val();
					if(pushOtherUrl.trim().length==0){
						$("#policyUrlMsg").show();
						$("#policyUrlMsg").html("<%=Config.message.get("PLEASE_INPUT_WEBSITE")%>");
						return false;
					}
					else{
						$("#policyUrlMsg").hide();
					}
					
					
					var pushOtherUrl=$("#pushOtherUrl").val();
					var e=/^(?!http)/;
					if(e.test(pushOtherUrl) ){
						$("#policyUrl1Msg").show();
						$("#policyUrl1Msg").html("<%=Config.message.get("START_WITH_HTTP")%>");
						return false;
					}
					else {
						$("#policyUrl1Msg").hide();
					}

				}
               
				var target=$('#account_create_policy_id_'+_fromStep);
				target.find("input[required='required']").each(function(){
					if($(this).val()==""&&$(this).attr()!='Description'){
						var _inName=$(this).attr('name');
						if(_onlineTemp!=2&&_inName!='pushOtherUrl'){
							valateflag=false;
							$(this).focus();
							return false;
						}
					}
				});
				target.find("select[required='required']").each(function(){
					if($(this).val()==""||$(this).val()==0){valateflag=false;$(this).focus();return false;}
				});
			}
			//推送计划
			if(_fromStep==3){//第二部群组，和地区做特殊处理
				//选择群组
				var doubleDate_id=$("#doubleDate_id").val();
				if(doubleDate_id.trim().length==0){
					$("#doubleDate_idMag").show();
					$("#doubleDate_idMag").html("<%=Config.message.get("POLICY_MANAGEMENT_TIMEATTENTION2")%>");
					return false;
				}else{
					$("#doubleDate_idMag").hide();
				}
				var _changegroup='';
				$('.changegrouplist').each(function(i,n){
					_changegroup+=$(n).html()+',';
				});
				$('#changegroupName_id').val(_changegroup);
				var _timeChange='';
				$('.checke_click').each(function(i,n){
					var _timeTemp=$(n).parent().attr('rel');
					_timeChange+=_timeTemp;
				});
				$('#changeTime_id').val(_timeChange);

				var _oroamTypeTemp=$("#push_user_type").val();
				//alert("_oroamTypeTemp="+_oroamTypeTemp+"  _changegroup= "+_changegroup);
		<%-- 		if(_oroamTypeTemp==0&&_changegroup==""){
					alert('<%=Config.message.get("PLEASE_SELECT_GROUP")%>');
					return ;
				} --%>
				var radio_2 = document.getElementById("optionsSpeed1");
				if(radio_2.checked == true){
					var _endtime=$('#pushpolicyEndtime_id').val();
					var _starttime=$('#pushpolicyStarttime_id').val();
					var showLimit=$("#showLimit").val();
					var s=showLimit.trim();
					var Ctime=GetDateDiff(_starttime, _endtime, "day")+1;
					var _pushSpeed=(s/Ctime).toFixed(0);
					$('#policyDayLimit').val(_pushSpeed);
					$('#policy_day_limit').html(_pushSpeed);
				}
			}
			//选择素材
			if(_fromStep==4){
				if($('#radio_page_1').is(":checked")){
					var pagename=$('input:radio[name="pagename"]:checked').val();
					if( pagename==undefined || pagename=='undefined'){
						$("#pagenameMag").html("<%=Config.message.get("PLEASE_SELECT_A_PAGE")%>");
						$("#pagenameMag").show();
						return false;
					}else{
						$("#pagenameMag").hide();
					}
				}
				if($('#radio_page_2').is(":checked")){
					var page_url=$("#pushUrl").val().trim();
					if(page_url==""){
						$("#pagenameMag_2").html("<%=Config.message.get("PLEASE_INPUT_PAGE_PATH")%>");
						$("#pagenameMag_2").show();
						return false;
					}
				}

				var paramsObj={};
				for(var j=0;j<3;j++){
					var params=[];
					var _target=$('#account_create_policy_id_'+j);
					params[j]=_target.serialize();
					var strs=params.join("&").split("&");
					for(var i=0;i<strs.length;i++){
						var kvs=strs[i];
						if(kvs!=null && kvs!=""){
							var kv=	kvs.split("=");
							if(paramsObj[kv[0]]!=null){
								if(paramsObj[kv[0]].constructor==Array){
									paramsObj[kv[0]].push(kv[1]);
								}else{
									var array=new Array();
									array.push(paramsObj[kv[0]]);
									array.push(kv[1]);
									paramsObj[kv[0]]=array;
								}
							}else{
								paramsObj[kv[0]]=kv[1];
							}
						}
					}
				}
				policyPreview(paramsObj);
			}
			if(valateflag){
				$('.createPrev').show();
				if(_step==4){
					$('.createNext').hide();
					$('.createPolicySumit').show();
				}else {
					$('.createNext').show();
					$('.createPolicySumit').hide();
				}
				_step++;
				for(i=0;i<=5;i++){
					$(policyStepArr[i]).hide();
				}
				$(policyStepArr[_step]).show();
				$('.nav-justified li').removeClass('active');
				$('.nav-justified li').eq(_step).addClass('active');
				
				$('#create_step_Id').val(_step);
			}

		});
		$('.createPolicySumit').click(function(){
			var params=[];
			var target1=$('#account_create_policy_id_0');
			var target2=$('#account_create_policy_id_1');
			var target3=$('#account_create_policy_id_2');
			var _fromStep=0,_fromStep1=1,_fromStep2=2;
			params[0]=target1.serialize();
			params[1]=target2.serialize();
			params[2]=target3.serialize();

			var target1=$('#account_create_policy_id_'+_fromStep);
			var valateflag=true;
			target1.find("input[required='required']").each(function(){
				if($(this).val()==""&&$(this).attr()!='Description'){
					var _inName=$(this).attr('name');
					if(_onlineTemp!=2&&_inName!='pushOtherUrl'){
						alert("<%=Config.message.get("CHECK_AND_FILL_NECESSARY_BANK")%>");
					}
				}
			});
			$.ajax({
				url:'policy/addPolicy',
				type:"post",
				data:params.join("&"),
				success:function(data){
					load_page('policy/policyList?policyType=3');
					alert('<%=Config.message.get("SUCCED")%>!');
				}
			});
		});


		var isEnglish = <%=Config.message.get("isEnglish")%>;
		$("#saveGroup").click(function(){
			var groupName=$("#groupName").val();
			if(groupName.trim().length==0){
				$("#groupNameMsg").show();
				$("#groupNameMsg").html("<%=Config.message.get("PLEASEINPUTPOLICYNAME")%>");
				return false;
			}
			else{
				$("#groupNameMsg").hide();
			}
			if(groupName.trim().length>20){
				$("#groupNameMsg").show();
				$("#groupNameMsg").html("<%=Config.message.get("NO_MORETHAN_20WORDS")%>");
				return false;
			}
			else{
				$("#groupNameMsg").hide();
			}
			$("#userAccount").attr("value",$("#mulUser").val());
			var filepath = $("#lefile").val().trim();
			var s = filepath.substr(filepath.length - 4);
			if($("#mulUser").val().trim().length==0 && s.length==0){
				$("#mulUserMsg").show();
				$("#mulUserMsg").html("<%=Config.message.get("FILL_THEACCOUNT_ORINMPORT_BATCHES")%>");
				return false;
			}else{
				$("#mulUserMsg").hide();
			}
			if(s !=".txt"){
				alert("error");
				return false;
			}
			$("#jForm").ajaxSubmit(function(data){
				if(data.result== -1){
					alert("File format error");
				}
				if(data.code==1){
					alert('Succeed');
					var total = "";
					var num_Of_P = "";
					if(isEnglish=="true"){
						total = "Total";
						num_Of_P = "numbers of people";
					}else{
						total = "共";
						num_Of_P = "人";
					}
					var _addGrouphtml='<div class="col-md-3 grouplist_change" >'+
							'<a href="javascript:;" class="thumbnail clickImg" rel="'+data.result+'">'+
							'<img src="img/gallery/Mac.png"  alt="'+groupName.trim()+'">'+
							' </a>'+
							' <div class="caption" style="margin-left: 20px;">'+
							'<h5 >'+groupName.trim()+'</h5>'+'<p>'+total+' '+data.version+num_Of_P+'</p>'+
							'</div>'+'</div>';
					$('.groupalllist').prepend(_addGrouphtml);
					//load_page('policyGroup.html');
				}
			});
		});
	});



</script>

<script>
	function getunitPrice(){
		var priceUrl=$("#priceUrl").val();
		var priceofUrl=$("#priceofUrl").html();
		if(priceUrl==0||priceUrl==""){
			$("#newunitPrice").val(priceofUrl);
		}else{
			$("#newunitPrice").val(priceUrl);
		}
	}
	function changeLimit(obj){
		$("#pushLimit").val("");
		var _value = $(obj).val();
		/*$("#optionsSpeed").attr("name","");
		 $("#optionsSpeed1").attr("name","");*/
		if(_value==2){ //加速
			$("#showLimitSpan").html("");
			//$("#optionsSpeed").attr("name","speedType");
		}else{ //匀速
			//$("#optionsSpeed1").attr("name","speedType");
			$("#showLimitSpan").html("*");
			//$("#pushLimit").val("50");
		}
	}

	function GetDateDiff(startTime, endTime, diffType) {
		//将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式
		startTime = startTime.replace(/\-/g, "/");
		endTime = endTime.replace(/\-/g, "/");
		//将计算间隔类性字符转换为小写
		diffType = diffType.toLowerCase();
		var sTime = new Date(startTime);      //开始时间
		var eTime = new Date(endTime);  //结束时间
		//作为除数的数字
		var divNum = 1;
		switch (diffType) {
			case "second":
				divNum = 1000;
				break;
			case "minute":
				divNum = 1000 * 60;
				break;
			case "hour":
				divNum = 1000 * 3600;
				break;
			case "day":
				divNum = 1000 * 3600 * 24;
				break;
			default:
				break;
		}
		return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));
	}
</script>

<script type="text/javascript">
	var data_cu=[];
	var data_indu = [];

	$(function(){
		$.ajax({
			url:'policy/searchIndustry',
			type:'post',
			dataType:'json',
			success: function (data) {
				var industry=data.result.LIST;
				industry = eval(industry);
				if(industry.length<0){
					return ;
				}
				for(var i=0;i<industry.length;i++){
					var object = {};
					object.Id = industry[i].industryId;
					object.level = industry[i].industryLevel;
					object.name = industry[i].industryName;
					object.pId = industry[i].parentId;
					data_indu.push(object);
					if(industry[i].parentId==0){
						var option = '<option value="'+industry[i].industryId+'">'+industry[i].industryName+'</option>';
						var $option = $(option);
						$("#selid").append($option);
						var num = 0;
						for(var j=0;j<data_indu.length;j++){
							if(data_indu[j].pId==industry[0].industryId){
								var option1 = '<option value="'+data_indu[j].Id+'">'+data_indu[j].name+'</option>';
								var $option1 = $(option1);
								$("#subselid").append($option1);
								num ++;
							}
						}
						if(num>0){
							$("#selid").attr("name","");
							$("#subselid").attr("name","industry-bear");
							$("#second_industry").show();
						}
					}
				}
			}
		});
	});

	function selectChild(obj){
		var Id = $(obj).val();
		$("#subselid").html("");
		$("#selid").attr("name","industry-bear");
		$("#subselid").attr("name","");
		$("#second_industry").hide();
		var sum_ = 0;
		$.each(data_indu,function(index, content){
			if(content.pId==Id){
				var option1 = '<option value="'+content.Id+'">'+content.name+'</option>';
				var $option1 = $(option1);
				$("#subselid").append($option1);
				sum_ ++;
			}
		});
		if(sum_>0){
			$("#selid").attr("name","");
			$("#subselid").attr("name","industry-bear");
			$("#second_industry").show();
		}
	}

	function Mimport(){
		var v=$("#lefile").val();
		$("#import").text(v);
	}
	function createNewPage(){
		var terminalType=$('input:radio[name="terminalType"]:checked').attr("tType");
		if(terminalType==1){
			$("#phoneLabel").addClass('active');
			$.ajax({
				url:'material/page/createNewPage',
				data:"tType="+terminalType,
				type:'post',
				success:function(data){
					var _html='';
					$.each(data.result.materials,function(i,n){
						_html+="<div class='col-md-4'><a href='javascript:;' onclick='previewTemplate(`"+n.templateUrl+"`,`"+n.templateId+"`)'  class='thumbnail'>"+
						"<img src='"+n.templateImage+"'/><div class='caption'><h5>"+n.templateName+"</h5><div></div></div></div>";
					}); 
					$("#list-page").append(_html);
					$("#templateList").find('.active').removeClass('active');
					setTimeout(function(){$('#templateList').addClass('active')},300);
				}
			});
		}else if(terminalType==2){
			$("#pcLabel").addClass('active');
			$.ajax({
				url:'material/page/createNewPage',
				data:"tType="+terminalType,
				type:'post',
				success:function(data){
					var _html='';
					$.each(data.result.materials,function(i,n){
						_html+="<div class='col-md-4'><a href='javascript:;' onclick='previewTemplate(`"+n.templateUrl+"`,`"+n.templateId+"`)'  class='thumbnail'>"+
						"<img src='"+n.templateImage+"'/><div class='caption'><h5>"+n.templateName+"</h5><div></div></div></div>";
					}); 
					$("#list-page").append(_html);
					$("#templateList").find('.active').removeClass('active');
					setTimeout(function(){$('#templateList').addClass('active')},300);
				}
			});
		}else if(terminalType==3){
			$("#padLabel").addClass('active');
			$.ajax({
				url:'material/page/createNewPage',
				data:"tType="+terminalType,
				type:'post',
				success:function(data){
					var _html='';
					$.each(data.result.materials,function(i,n){
						_html+="<div class='col-md-4'><a href='javascript:;' onclick='previewTemplate(`"+n.templateUrl+"`,`"+n.templateId+"`)'  class='thumbnail'>"+
						"<img src='"+n.templateImage+"'/><div class='caption'><h5>"+n.templateName+"</h5><div></div></div></div>";
					}); 
					$("#list-page").append(_html);
					$("#templateList").find('.active').removeClass('active');
					setTimeout(function(){$('#templateList').addClass('active')},300);
				}
			});
		}
		
	}
	
function previewTemplate(target,id){
	$("#html_temp_frame").attr("src",target+"/web/tb.html");
	$("#page-templateId").val(id);
	//清空原有的按钮 
	$("#group").find("li").remove();
	$("#content_set").html("");
	
	var modelpath=target+"/web/tb.html";
	
	target=target.substring(target.lastIndexOf('/') + 1);
	$.ajax({
		url:'material/page/pathGroup',
		data:"target="+target,
		type:'post',
		success:function(data){
			var admap=data.result;
			$("#htmlpath").val(target);
			
			for(var group in admap)
			{
				var groupid= randomString(10);
				
				
				var li="<li class='col-md-4'><a href='#"+groupid+"' data-toggle='tab'>"+group+"</a></li>";
				$("#group").append(li);//加载group按钮 
				
				var div="<div class='tab-pane fade in' id='"+groupid+"'></div>";
				$("#content_set").append(div);//加载group按钮对应div 

				var first=admap[group];
				for(var v in first ){
					var updateIMGid=randomString(8);
					//var lefile=randomString(8);
					var formid=randomString(9);
					var lastdiv="";
					var second=first[v];
					var adid=second['adid'];
					var adTitle=second['adTitle'];
					var adModel=second['adModel'];
					var adDesc=second['adDesc'];
					var adValue=second['value'];
					if(adModel=="TEXT"){
						lastdiv=
						"<form class='form-horizontal' enctype='multipart/form-data' method='post' action=''>"+
						"<div class='form-group msg_font form_group'>"+ 
			            "<label class='col-md-3 control-label' for='password-input'>"+adTitle+"</label>"+
			            "<div class='col-md-6 row_left'>"+
			             "<input type='text' class='form-control' name='preMoney' id='"+adid+"' adModel='"+adModel+"' adTitle='"+adTitle+"' value='"+adValue+"' onblur='overText($(this))' onfocus='nowText($(this))'>"+
			            "</div>"+
			           "</div>"+
			           "</form>";
			           //$("#"+groupid).append(lastdiv);//加载group按钮对应div 
					}
					if(adModel=="IMG"){
						lastdiv=
						"<form class='form-horizontal' action='${basePath}material/page/updateImage' id='"+formid+"' enctype='multipart/form-data' method='post'>"+
						"<div class='form-group msg_font form_group'>"+ 
			            "<label for='password-input' class='col-md-3 control-label'>"+adTitle+"</label>"+
			            "<div class='col-md-1 row_left'>"+
						"<img style='display: block; width:80px;height:60px;' src='img/error.png' id='"+updateIMGid+"'>"+
		                "</div>"+
		                "</div>"+
		                "<div class='form-group msg_font form_group'>"+ 
			            "<label for='password-input' class='col-md-3 control-label'></label>"+ 
		                "<div class='col-md-4 row_left'>"+
							"<span class='span_fonts'>尺寸大小："+adDesc+"</span>"+
		                "</div>"+
			           "</div>"+
			           "<div class='form-group msg_font form_group'>"+ 
			            "<label for='password-input' class='col-md-3 control-label'></label>"+ 
			            "<div class='input-append'>"+
			            "<input type='hidden' value='"+target+"' name='targetname'>"+
			            "<input type='file' style='display: none' id='"+adid+"' adModel='"+adModel+"' adTitle='"+adTitle+"' accept='image/*' onchange='showFile(\""+formid+"\",$(this),\""+updateIMGid+"\")' name='upload'>"+
							"<a onclick='$(`input[id="+adid+"]`).click();' class='btn btn-info'>Upload the material</a>"+
						"</div>"+
			           "</div>"+
			           "</form>";
					} 
					if(adModel=="VIDEO"){
						lastdiv=
						"<form class='form-horizontal' action='${basePath}material/page/updateImage' id='"+formid+"' enctype='multipart/form-data' method='post'>"+
						"<div class='form-group msg_font form_group'>"+ 
			            "<label for='password-input' class='col-md-3 control-label'>"+adTitle+"</label>"+
			            "<div class='col-md-1 row_left'>"+
						"<video style='display: block; width:80px;height:60px;' src='img/error.png' id='"+updateIMGid+"'></video> "+
		                "</div>"+
		                "</div>"+
		                "<div class='form-group msg_font form_group'>"+ 
			            "<label for='password-input' class='col-md-3 control-label'></label>"+ 
		                "<div class='col-md-4 row_left'>"+
		                "</div>"+
			           "</div>"+
			           "<div class='form-group msg_font form_group'>"+ 
			            "<label for='password-input' class='col-md-3 control-label'></label>"+ 
			            "<div class='input-append'>"+
			            "<input type='hidden' value='"+target+"' name='targetname'>"+
			            "<input type='file' style='display: none' id='"+adid+"' adModel='"+adModel+"' adTitle='"+adTitle+"' accept='audio/mp4, video/mp4' onchange='showFile(\""+formid+"\",$(this),\""+updateIMGid+"\")' name='upload'>"+
							"<a onclick='$(`input[id="+adid+"]`).click();' class='btn btn-info'>Upload the material</a>"+
						"</div>"+
			           "</div>"+
			           "</form>";
					}
					if(adModel=="LINK"){
						lastdiv=
							"<form class='form-horizontal' enctype='multipart/form-data' method='post' action=''>"+
							"<div class='form-group msg_font form_group'>"+ 
				            "<label class='col-md-3 control-label' for='password-input'>"+adTitle+"</label>"+
				            "<div class='col-md-6 row_left'>"+
				             "<input type='text' class='form-control' name='preMoney' id='"+adid+"' adModel='"+adModel+"' adTitle='"+adTitle+"' value='"+adValue+"' onblur='overText($(this))' onfocus='nowText($(this))'>"+
				            "</div>"+
				           "</div>"+
				           "</form>";
						
					}
					if(adModel=="APP"){
						
					}
					if(adModel=="LIST"){
						
					}
					
		               $("#"+groupid).append(lastdiv);//加载group按钮对应div 
					
					
				} 
			}

			
		}
	});
	
}

function createPageByTemplate(target){
	$("#box-dialog-page").modal('show');
}

$("#button-create-page-ok").click(function(){
	var pageName=$("#pageName").val().trim();
		var valateflag = true;
	$("#box-dialog-page").find("input[required='required']").each(function(){
		if($(this).val().trim()==""){valateflag=false;$(this).focus();return false;}
	});
	if(valateflag){
		//生成js 保存到 web/jquery.js 
		var target=$("#htmlpath").val();
		$.ajax({
			url:'material/page/tbHtmljs',
			data:"target="+target,
			type:'post',
			success:function(data){
				var result=data.code;
				if(result==1){
					$.ajax({
						url:'material/page/zipFile',
						data:"target="+target,
						type:'post',
						success:function(data){
							var pagePath=data.desc;
							var data = $("#form-create-page").serialize();
							$.ajax({
								url:'material/page/create',
								data:data+"&pagePath="+pagePath,
								type:'post',
								success:function(data){
									alert("succeed！");
									$('#box-dialog-page').modal('hide');
									$('#pushUrl').val(data.desc);
									$('#account_create_policy_id_2').show();
									$('#createPage').hide();
									$('#nextPage').show();
									$('#pageSuc').hide();
									
								}
							});
						}
					});
					
					
				}else{
					$("#errordiv").hide();
					alert("<%=Config.message.get("PAGE_CHOOSE_TEMPLATE")%>");
				}
			}
		});
		
	}else{
		if(pageName==""){
		$("#errordiv").show();
		$("#errorMsg").html("<%=Config.message.get("PAGE_INPUT_PAGE_NAME")%>!");
		return false;
		}else{
			$("#errordiv").hide();
			alert("<%=Config.message.get("PAGE_CHOOSE_TEMPLATE")%>");
		}
	}
});



</script>
