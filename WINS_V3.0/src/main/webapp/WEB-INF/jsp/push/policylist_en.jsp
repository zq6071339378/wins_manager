<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${basePath }js/jquery.form.js"></script>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<head>
<%response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
</head> 
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("POLICY_MANAGEMENT_HOME")%></a>
								</li>
								<li><a href="#"><%=Config.message.get("PUSH_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("POLICY_MANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("POLICY_MANAGEMENT_CONTENT")%></div>
						</div>
					</div>
				</div>
				<c:set var="seetemp" value="0"></c:set>
				<c:set var="updatetemp" value="0"></c:set>
				<c:set var="addtemp" value="0"></c:set>
				<c:set var="deletetemp" value="0"></c:set>
				<c:set var="checktemp" value="0"></c:set>
				<c:set var="globaltemp" value="0"></c:set>
				<c:forEach items="${roleresult.MenuRelation}" var="c">
					<c:if test="${c.menuId==3 && c.see_permission!=0}">
						<c:set var="seetemp" value="1"></c:set>
					</c:if>
					<c:if test="${c.menuId==3 && c.update_permission!=0}">
						<c:set var="updatetemp" value="1"></c:set>
					</c:if>
					<c:if test="${c.menuId==3 && c.add_permission!=0}">
						<c:set var="addtemp" value="1"></c:set>
					</c:if>
					<c:if test="${c.menuId==3 && c.delete_permission!=0}">
						<c:set var="deletetemp" value="1"></c:set>
					</c:if>
					<c:if test="${c.menuId==3 && c.review_permission!=0}">
						<c:set var="checktemp" value="1"></c:set>
					</c:if>
					<c:if test="${c.menuId==3 && c.global_permission!=0}">
						<c:set var="globaltemp" value="1"></c:set>
					</c:if>
				</c:forEach>
				<!-- /PAGE HEADER -->
					<div class="col-md-12 row_left">
						<div class="col-md-11 row_left">
							<div class=" btn-group col-md-6">
								<a href="#policy/policyListEn?policyType=0"
									onclick="load_page('policy/policyListEn?policyType=0');"
									class="btn btn-default"
									<c:if test="${policyType eq 0 }"> style="background-color:#EBEBEB;"</c:if>><%=Config.message.get("POLICY_MANAGEMENT_ALL")%></a>
								<!-- 待审核 -->
								<a href="#policy/policyListEn?policyType=3"
									onclick="load_page('policy/policyListEn?policyType=3');"
									class="btn btn-default"
									<c:if test="${policyType eq 3 }"> style="background-color:#EBEBEB;"</c:if>><%=Config.message.get("POLICY_MANAGEMENT_AUDIT")%></a>
								<!-- 待运行 -->
								<a href="#policy/policyListEn?policyType=2"
									onclick="load_page('policy/policyListEn?policyType=2');"
									class="btn btn-default"
									<c:if test="${policyType eq 2 }"> style="background-color:#EBEBEB;"</c:if>><%=Config.message.get("POLICY_MANAGEMENT_TORUN")%></a>
								<!-- 暂停 -->
								<a href="#policy/policyListEn?policyType=5"
									onclick="load_page('policy/policyListEn?policyType=5');"
									class="btn btn-default"
									<c:if test="${policyType eq 5 }"> style="background-color:#EBEBEB;"</c:if>><%=Config.message.get("POLICY_MANAGEMENT_STOPEED")%></a>
									<!-- 已停止 -->
								<a href="#policy/policyListEn?policyType=4"
									onclick="load_page('policy/policyListEn?policyType=4');"
									class="btn btn-default"
									<c:if test="${policyType eq 4 }"> style="background-color:#EBEBEB;"</c:if>><%=Config.message.get("POLICY_MANAGEMENT_COMPLETE")%></a>
									<!-- 运行中 -->
								<a href="#policy/policyListEn?policyType=1"
									onclick="load_page('policy/policyListEn?policyType=1');"
									class="btn btn-default"
									<c:if test="${policyType eq 1 }"> style="background-color:#EBEBEB;"</c:if>><%=Config.message.get("POLICY_MANAGEMENT_RUNNING")%></a>
							</div>
							<!-- 按广告主搜索策略 -->
							<c:if test="${isEnglish eq true }">
								<div class="col-md-1 row_left">
									<select class="form-control" id="searchePolicyType">
										<option value="0"><%=Config.message.get("POLICY_MANAGEMENT_ALL")%></option>
										<option value="0" <c:if test="${searchType eq 0}">selected="selected"</c:if>><%=Config.message.get("POLICY_MANAGEMENT_POLICYNAME")%></option>
										<option value="1" <c:if test="${searchType eq 1}">selected="selected"</c:if>><%=Config.message.get("POLICY_VIEW_CUSTOMERNAME")%></option>
										<option value="2" <c:if test="${searchType eq 2}">selected="selected"</c:if>><%=Config.message.get("CREATEPOLICY_ACTIVITYNAME")%></option>
									</select>
								</div>
							</c:if>
							<div class="col-md-6  login-box" style="width:40%">
								<form action="index.html#policy/policyListEn?policyType=${policyType}" method="post" id="searchPolicyForm">
									<div class="form-group col-md-8 search_icon">
										<a href="javascript:;" onclick="searchPolicy()">
											<i class="fa fa-search"></i>
										</a>
										<input type="hidden" name="policyType" value="${policyType}" id="policyType_ID" />
										<input type="search" name="searchPolicyName" style="padding-left:5px;width:82%" id="exampleInputSearch" class="form-control"   placeholder="<%=Config.message.get("POLICYVIEW_PLEASEINPUTPOLICYNAME")%>">
									</div>
									<c:if test="${addtemp eq 1}">
										<div class="from-group col-md-4">
											<button type="button" class="btn btn-danger"
												onclick="load_page('policy/createPolicy');"><%=Config.message.get("POLICY_MANAGER_CREATE_POLICY")%></button>
										</div>
									</c:if>
								</form>
							</div>
						</div>
						<c:if test="${globaltemp eq 1}">
							<div class="col-md-1 login-box">
								<form>
									<div class="from-group col-md-12" style="margin-right:20px;">
										<a class="btn btn-primary config" type="button"
											data-toggle="modal" href="#box-config"><%=Config.message.get("POLICY_GLOBAL_CONFIGURATION")%></a>
									</div>
								</form>
							</div>
						</c:if>
					</div>
				<!--全局配置dialog-->
				<div class="modal fade" id="box-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("POLICY_GLOBAL_CONFIGURATION")%></h4>
							</div>
							<div class="panel-body">
								<form action="" method="post" enctype="multipart/form-data"
									class="form-horizontal">
										<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("POLICY_MANAGEMENT_SINGLEUSERPUSH")%>：</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="global_pushLimit_i" id="global_pushLimit_id" value="${resultGlobal.pushLimit }" required="required" onkeyup="value=value.replace(/[^\d]/g,'') ">
										</div>
										<div class="col-sm-2">
											<span class="span_font"><%=Config.message.get("POLICY_MANAGEMENT_TIMES")%> </span>
										</div>
										<div class="col-md-5 row_left">
											<span class="span_fonts"><%=Config.message.get("POLICY_MANAGEMENT_TIMEOFSINGLEUSERTOPUSH")%></span>
										</div>
									   <div class="col-md-2" style="display: none;">
											<div class="form-policy has-error">
												<label class="col-md-3"></label>
												<label class="control-label" for="inputWarning" id=global_pushLimit_idMag></label>
											</div>
										</div>
									</div>
									<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("POLICY_MANAGEMENT_USERSHOW")%>：</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" id="global_showNum_id" name="preMoney" value="${resultGlobal.showNum }" 	onkeyup="value=value.replace(/[^\d]/g,'') ">
										</div>
										<div class="col-md-2 row_left">
											<select class="form-control" id="global_showType_id">
												<option
													<c:if test="${resultGlobal.showType eq 1}">selected="true" </c:if>
													value="1"><span><%=Config.message.get("POLICY_MANAGEMENT_DAY")%></span></option>
												<option
													<c:if test="${resultGlobal.showType eq 2}">selected="true" </c:if>
													value="2"><span> <%=Config.message.get("POLICY_MANAGEMENT_WEEK")%></span></option>
												<option
													<c:if test="${resultGlobal.showType eq 3}">selected="true" </c:if>
													value="3"><span><%=Config.message.get("POLICY_MANAGEMENT_MONTH")%></span></option>
											</select>
										</div>
										<div class="col-md-5 row_left">
											<span class="span_fonts"><%=Config.message.get("POLICY_MANAGEMENT_TIMEOFSINGLEUSERTOSHOW")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("POLICY_MANAGEMENT_THEMINMUMINTERVAL")%>：</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="preMoney" id="global_pushInterval_id"
												value="${resultGlobal.pushInterval }" onkeyup="value=value.replace(/[^\d]/g,'') ">
										</div>
										<div class="col-md-2 row_left">
											<select class="form-control">
												<option><%=Config.message.get("POLICY_MANAGEMENT_TIME_SECOND")%></option>
												<option><%=Config.message.get("POLICY_MANAGEMENT_TIME_MIN")%></option>
												<option><%=Config.message.get("POLICY_MANAGEMENT_TIME_HOUR")%></option>
											</select>
										</div>
									</div>
									<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("POLICY_MANAGEMENT_SHOWTIME")%>：</label>
										<div class="col-sm-2">
											<input type="text" class="form-control" name="password"
												id="global_showTime_id" value="${resultGlobal.showTime }" onkeyup="value=value.replace(/[^\d]/g,'') "  >
										</div>
										<div class="col-sm-2">
											<span class="span_font"><%=Config.message.get("POLICY_MANAGEMENT_TIME_SECOND")%> </span>
										</div>
									</div>
									<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("POLICY_MANAGEMENT_STATEMENT")%>：</label>
										<div class="col-md-2">
											<div class="checkbox checkbox-slider--b checkbox-slider-default check_radio">
												<label style="padding-top: 1px">
													<input type="checkbox" id="global_pushStatus_id" onclick="changeGlobalStatus();" <c:if test="${resultGlobal.pushStatus eq 1}">checked="checked"</c:if>><span></span>
												</label>
											</div>
										</div>
										<div class="col-md-4 row_left" style="display: none">
											<span class="span_font" id="runningTime">当前系统已经运行325天15小时</span>
										</div>
									</div>
									<div class="form-group msg_font form_group">
										<div class="col-md-2" id="changeTemp_id"
											style="width: 100%; margin-left: 20%; color: red;"></div>
									</div>
										 <div class="col-md-7">
													            	<div class="form-policy has-error">
																		<label class="col-md-3"></label> <label
																			class="control-label" for="inputWarning"
																			id="policyNameMsg"></label>
																	</div>
													            </div>
								</form>
							</div>
							<div class="modal-footer">
								<button data-dismiss="modal" class="btn btn-default"
									type="button"><%=Config.message.get("POLICY_MANAGEMENT_CLOSE")%></button>
								<a class="btn btn-primary config"
									onclick="$(this).siblings().click();" 
									onclick="editeOne();"
									type="button" 
									data-toggle="modal" href="#box-conserve"><%=Config.message.get("POLICY_MANAGEMENT_SAVE")%></a>
							</div>
						</div>
					</div>
				</div>
				<!--全局配置dialog-->
				<!--OK身份信息dialog-->
				<div class="modal fade" id="box-conserve" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("POLICY_MANAGEMENT_IDENTIFICATION")%></h4>
							</div>
							<div class="panel-body">
								<form action="" method="post" enctype="multipart/form-data"
									class="form-horizontal">
									<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"></label>
										<div class="col-md-5">
											<span class="span_font"><%=Config.message.get("POLICY_MANAGEMENT_PASSWORD_INPUT")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group">
										<label class="col-md-3 control-label" for="password-input"><%=Config.message.get("POLICY_MANAGEMENT_PASSWORDSW")%></label>
										<div class="col-sm-4">
											<input type="password" class="form-control" name="preMoney"
												id="check_passwod_id" placeholder="">
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button data-dismiss="modal" class="btn btn-default"
									type="button"><%=Config.message.get("CANCEL")%></button>
								<button data-dismiss="modal" class="btn btn-primary"
									id="global_edite_id" type="button"><%=Config.message.get("POLICY_MANAGEMENT_OK")%></button>
							</div>
						</div>
					</div>
				</div>
				<!--OK身份信息dialog-->
				<!--deletedialog-->
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
								<input type="hidden" id="delete_policy_Id" value="0" />
								<button data-dismiss="modal" class="btn btn-default"
									type="button"><%=Config.message.get("CANCEL")%></button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" onclick="deletePolicy()"><%=Config.message.get("SURE")%></button>
							</div>
						</div>
					</div>
				</div>
				<!--deletedialog end-->
				<div class="divide-12"></div>
				<div id="myTabContent" class="tab-content">
					<div class="row tab-pane fade in active" id="whole_tab1">
						<div class="col-md-12">
							<!-- BOX -->
							<div class="box border purple">
								<c:choose>
									<c:when test="${policyType eq 0 }">
										<!--策略TABLE1  全部-->

										<table
											class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th><%=Config.message.get("POLICY_MANAGEMENT_SORT")%></th>
													<th><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></th>
													<th><%=Config.message.get("CREATEPOLICY_AFFILIATEDACTIVITIES")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_POLICYNAME")%></th>
													<th><%=Config.message.get("POLICY_CREATE_USER")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_PUSHTERMINAL")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STARTTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_ENDTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_REVIEWSTATUS")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_RUNNINGSTATUS")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_OPERATION")%></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${result.POLICYLIST}" var="policyBean" varStatus="i" step="1">
													<tr>
														<td>${i.count+(result.page.currentPage)*10-10}</td>
														<td>${policyBean.customerName }</td>
														<td>${policyBean.activityName }</td>
														<td>${policyBean.policyName }</td>
														<td>${policyBean.createName }</td>
														<td><c:if test="${policyBean.terminalType eq 0}"><%=Config.message.get("POLICY_MANAGEMENT_PC/PAD")%></c:if>
															<c:if test="${policyBean.terminalType eq 1}"><%=Config.message.get("POLICY_MANAGEMENT_PHONE")%></c:if>
															<c:if test="${policyBean.terminalType eq 2}"><%=Config.message.get("POLICY_MANAGEMENT_IOS")%></c:if>
															<c:if test="${policyBean.terminalType eq 3}"><%=Config.message.get("POLICY_MANAGEMENT_ANDROID")%></c:if></td>
														<td>${policyBean.startTime}</td>
														<td>${policyBean.endTime}</td>
														<td><c:if test="${policyBean.checkStatus eq 0}"><%=Config.message.get("POLICY_MANAGEMENT_TOAUDIT")%></c:if>
															<c:if test="${policyBean.checkStatus eq 1}"><%=Config.message.get("POLICY_MANAGEMETN_AUDITED")%></c:if>
														</td>
														<td><c:if test="${policyBean.policyStatus ne 2 }">
																<c:if test="${policyBean.syncStatus eq 0}"><%=Config.message.get("POLICY_MANAGEMENT_TOBESYNCHROINIZING")%></c:if>
																<c:if test="${policyBean.syncStatus eq 1}"><%=Config.message.get("POLICY_MANAGEMENT_SYNCHRONIZING")%></c:if>
																<c:if test="${policyBean.syncStatus eq 2}"><%=Config.message.get("POLICY_MANAGEMENT_INOPERATION")%></c:if>
															</c:if> <c:if test="${policyBean.policyStatus eq 2 }">
																	<%=Config.message.get("POLICY_MANAGEMENT_STOPEED")%>
																</c:if></td>
														<td><a class="btn btn-default"
															href="#policy/singlePolicyInfo?policyId=${policyBean.policyId }"
															onclick="load_page('policy/singlePolicyInfo?policyId=${policyBean.policyId }')"
															title="check"><i class="fa fa-eye"></i></a> <a
															class="btn btn-default"
															href="#policyinfo/reportDetails?PolicyId=${policyBean.policyId }&temp=1"
															onclick="load_page('policyinfo/reportDetails?PolicyId=${policyBean.policyId}&temp=1');"
															title="table"><i class="fa fa-bar-chart-o"></i></a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
									<c:when test="${policyType eq 1 }">
										<table
											class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th><%=Config.message.get("POLICY_MANAGEMENT_SORT")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STATEMENT")%></th>
													<th><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></th>
                                                    <th><%=Config.message.get("CREATEPOLICY_AFFILIATEDACTIVITIES")%></th>
                                                    <th><%=Config.message.get("POLICY_MANAGEMENT_POLICYNAME")%></th>
													<th><%=Config.message.get("POLICY_CREATE_USER")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_PUSHTERMINAL")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STARTTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_ENDTIME")%></th>
													<c:if test="${seetemp eq 1 }">
													<th><%=Config.message.get("POLICY_MANAGEMENT_OPERATION")%></th>
													</c:if>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${result.POLICYLIST}" var="policyBean"
													varStatus="i" step="1">
													<tr>
														<td>${i.count+(result.page.currentPage)*10-10}</td>
														<td>
															<div
																class="checkbox checkbox-slider--b checkbox-slider-default check_radio">
																<label> <input type="checkbox" checked="checked"
																	<c:if test="${updatetemp ne 1 }">disabled="disabled" </c:if>
																	rel="statusType=1&policyId=${policyBean.policyId }&policyName=${policyBean.policyName }"
																	onclick="updateStatus(this)"><span></span>
																</label>
															</div>
														</td>
														<td>${policyBean.customerName }</td>
														<td>${policyBean.activityName }</td>
														<td>${policyBean.policyName }</td>
														<td>${policyBean.createName }</td>
														<td><c:if test="${policyBean.terminalType eq 0}"><%=Config.message.get("POLICY_MANAGEMENT_PC/PAD")%></c:if>
															<c:if test="${policyBean.terminalType eq 1}"><%=Config.message.get("POLICY_MANAGEMENT_PHONE")%></c:if>
															<c:if test="${policyBean.terminalType eq 2}"><%=Config.message.get("POLICY_MANAGEMENT_IOS")%></c:if>
															<c:if test="${policyBean.terminalType eq 3}"><%=Config.message.get("POLICY_MANAGEMENT_ANDROID")%></c:if>
														</td>
														<td>${policyBean.startTime}</td>
														<td>${policyBean.endTime}</td>
														<c:if test="${seetemp eq 1 }">
															<td><a class="btn btn-default"
																href="#policy/singlePolicyInfo?policyId=${policyBean.policyId }"
																onclick="load_page('policy/singlePolicyInfo?policyId=${policyBean.policyId }')"
																title="check"><i class="fa fa-eye"></i></a> <a
																class="btn btn-default"
																href="#policyinfo/reportDetails?PolicyId=${policyBean.policyId }&temp=1"
																onclick="load_page('policyinfo/reportDetails?PolicyId=${policyBean.policyId}&temp=1');"
																title="table"><i class="fa fa-bar-chart-o"></i></a></td>
														</c:if>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
									<c:when test="${policyType eq 2 }">
										<table
											class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th><%=Config.message.get("POLICY_MANAGEMENT_SORT")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STATEMENT")%></th>
													<th><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></th>
													<th><%=Config.message.get("CREATEPOLICY_AFFILIATEDACTIVITIES")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_POLICYNAME")%></th>
													<th><%=Config.message.get("POLICY_CREATE_USER")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_PUSHTERMINAL")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STARTTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_ENDTIME")%></th>
													<c:if test="${seetemp eq 1 }">
													<th><%=Config.message.get("POLICY_MANAGEMENT_OPERATION")%></th>
												
													</c:if>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${result.POLICYLIST}" var="policyBean"
													varStatus="i" step="1">
													<tr>
														<td>${i.count+(result.page.currentPage)*10-10}</td>
														<td>
															<div
																class="checkbox checkbox-slider--b checkbox-slider-default check_radio">
																<label> <input type="checkbox" checked="checked"
																	<c:if test="${updatetemp ne 1 }">disabled="disabled" </c:if>
																	rel="statusType=1&policyId=${policyBean.policyId }&policyName=${policyBean.policyName }"
																	onclick="updateStatus(this)"><span></span>
																</label>
															</div>
														</td>
														<td>${policyBean.customerName }</td>
														<td>${policyBean.activityName }</td>
														<td>${policyBean.policyName }</td>
														<td>${policyBean.createName }</td>
														<td><c:if test="${policyBean.terminalType eq 0}"><%=Config.message.get("POLICY_MANAGEMENT_PC/PAD")%></c:if>
															<c:if test="${policyBean.terminalType eq 1}"><%=Config.message.get("POLICY_MANAGEMENT_PHONE")%></c:if>
															<c:if test="${policyBean.terminalType eq 2}"><%=Config.message.get("POLICY_MANAGEMENT_IOS")%></c:if>
															<c:if test="${policyBean.terminalType eq 3}"><%=Config.message.get("POLICY_MANAGEMENT_ANDROID")%></c:if></td>
														<td>${policyBean.startTime}</td>
														<td>${policyBean.endTime}</td>
														<c:if test="${seetemp eq 1 }">
															<td><a class="btn btn-default"
																href="#policy/singlePolicyInfo?policyId=${policyBean.policyId }"
																onclick="load_page('policy/singlePolicyInfo?policyId=${policyBean.policyId }')"
																title="check"><i class="fa fa-eye"></i></a> <a
																class="btn btn-default"
																href="#policyinfo/reportDetails?PolicyId=${policyBean.policyId }&temp=1"
																onclick="load_page('policyinfo/reportDetails?PolicyId=${policyBean.policyId}&temp=1');"
																title="table"><i class="fa fa-bar-chart-o"></i></a></td>
														</c:if>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
									<c:when test="${policyType eq 3 }">
										<table
											class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
												<th><%=Config.message.get("POLICY_MANAGEMENT_SORT")%></th>
													<th><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></th>
													<th><%=Config.message.get("CREATEPOLICY_AFFILIATEDACTIVITIES")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_POLICYNAME")%></th>
													<th><%=Config.message.get("POLICY_CREATE_USER")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_PUSHTERMINAL")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STARTTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_ENDTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_OPERATION")%></th>
													
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${result.POLICYLIST}" var="policyBean"
													varStatus="i" step="1">
													<tr>
														<td>${i.count+(result.page.currentPage)*10-10}</td>
														<td>${policyBean.customerName }</td>
														<td>${policyBean.activityName }</td>
														<td>${policyBean.policyName }</td>
														<td>${policyBean.createName }</td>
														<td><c:if test="${policyBean.terminalType eq 0}"><%=Config.message.get("POLICY_MANAGEMENT_PC/PAD")%></c:if>
															<c:if test="${policyBean.terminalType eq 1}"><%=Config.message.get("POLICY_MANAGEMENT_PHONE")%></c:if>
															<c:if test="${policyBean.terminalType eq 2}"><%=Config.message.get("POLICY_MANAGEMENT_IOS")%></c:if>
															<c:if test="${policyBean.terminalType eq 3}"><%=Config.message.get("POLICY_MANAGEMENT_ANDROID")%></c:if></td>
														<td>${policyBean.startTime}</td>
														<td>${policyBean.endTime}</td>
														<td><c:if test="${seetemp eq 1 }">
																<a class="btn btn-default"
																	href="#policy/singlePolicyInfo?policyId=${policyBean.policyId }"
																	onclick="load_page('policy/singlePolicyInfo?policyId=${policyBean.policyId }')"
																	title="check"><i class="fa fa-eye"></i></a>
															</c:if> <c:if test="${updatetemp eq 1 }">
																<a class="btn btn-default"
																	href="#policy/toPolicyEditeInfo?policyId=${policyBean.policyId }"
																	onclick="load_page('policy/toPolicyEditeInfo?policyId=${policyBean.policyId }')"
																	title="Modification"><i class="fa fa-pencil-square"></i></a>
															</c:if> <c:if test="${checktemp eq 1 }">
																<a class="btn btn-default" href="javascript:;"
																	title="check"
																	rel="statusType=2&policyId=${policyBean.policyId }"
																	onclick="cheackPolicyStatus(this)"><i
																	class="fa fa-check-square-o"></i></a>
															</c:if> <c:if test="${deletetemp eq 1 }">
																<a class="btn btn-default config" type="button"
																	data-toggle="modal" href="#box-delete"
																	rel="${policyBean.policyId}"
																	onclick="$('#delete_policy_Id').val($(this).attr('rel'));"
																	title="delete"><i class="fa fa-trash-o"></i></a>
															</c:if></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
									<c:when test="${(policyType eq 4)or(policyType eq 5)}">
										<table
											class="datatable table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
												<th><%=Config.message.get("POLICY_MANAGEMENT_SORT")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STATEMENT")%></th>
													<th><%=Config.message.get("CREATEPOLICY_ADVERTISER")%></th>
													<th><%=Config.message.get("CREATEPOLICY_AFFILIATEDACTIVITIES")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_POLICYNAME")%></th>
													<th><%=Config.message.get("POLICY_CREATE_USER")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_PUSHTERMINAL")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_STARTTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_ENDTIME")%></th>
													<th><%=Config.message.get("POLICY_MANAGEMENT_OPERATION")%></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${result.POLICYLIST}" var="policyBean"
													varStatus="i" step="1">
													<tr>
														<td>${i.count+(result.page.currentPage)*10-10}</td>
														<td>
															<div
																class="checkbox checkbox-slider--b checkbox-slider-default check_radio">
																<label> <input type="checkbox"
																	<c:if test="${updatetemp ne 1 }">disabled="disabled" </c:if>
																	rel="statusType=1&policyId=${policyBean.policyId }&policyName=${policyBean.policyName }"
																	onclick="updateStatus(this)"><span></span>
																</label>
															</div>
														</td>
														<td>${policyBean.customerName }</td>
														<td>${policyBean.activityName }</td>
														<td>${policyBean.policyName }</td>
														<td>${policyBean.createName }</td>
														<td><c:if test="${policyBean.terminalType eq 0}"><%=Config.message.get("POLICY_MANAGEMENT_PC/PAD")%></c:if>
															<c:if test="${policyBean.terminalType eq 1}"><%=Config.message.get("POLICY_MANAGEMENT_PHONE")%></c:if>
															<c:if test="${policyBean.terminalType eq 2}"><%=Config.message.get("POLICY_MANAGEMENT_IOS")%></c:if>
															<c:if test="${policyBean.terminalType eq 3}"><%=Config.message.get("POLICY_MANAGEMENT_ANDROID")%></c:if></td>
														<td>${policyBean.startTime}</td>
														<td>${policyBean.endTime}</td>
														<td><c:if test="${seetemp eq 1 }">
																<a class="btn btn-default"
																	href="#policy/singlePolicyInfo?policyId=${policyBean.policyId }"
																	onclick="load_page('policy/singlePolicyInfo?policyId=${policyBean.policyId }')"
																	title="check"><i class="fa fa-eye"></i></a>
															</c:if> <c:if test="${updatetemp eq 1 }">
																<a class="btn btn-default"
																	href="#policy/toPolicyEditeInfo?policyId=${policyBean.policyId }"
																	onclick="load_page('policy/toPolicyEditeInfo?policyId=${policyBean.policyId }')"
																	title="Modification"><i class="fa fa-pencil-square"></i></a>
															</c:if> <c:if test="${deletetemp eq 1 }">
																<a class="btn btn-default config" type="button"
																	data-toggle="modal" href="#box-delete"
																	rel="${policyBean.policyId}"
																	onclick="$('#delete_policy_Id').val($(this).attr('rel'));"
																	title="delete"><i class="fa fa-trash-o"></i></a>
															</c:if></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
								</c:choose>

								<div class="row">
									<div class="dataTables_footer clearfix">
										<jsp:include page="../layout/pagination.jsp"><jsp:param
												value="policy/policyListEn?policyType=${policyType }"
												name="actionName" /><jsp:param value="${policyType}"
												name="policyType" /></jsp:include>
									</div>
								</div>
							</div>
							<!-- /BOX -->
						</div>
					</div>
				</div>
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->

<script type="text/javascript">
             //非空校验
          /*    function editeOne();{
             	if($("input[name='global_pushLimit_i']:checked").val()==4){
					var pushOtherUrl=$("#global_pushLimit_id").val();
					if(pushOtherUrl.trim().length==0){
						$("#global_pushLimit_idMag").show();
						$("#global_pushLimit_idMag").html("离线网址不能为空");
						return false;
					}
					else{
						$("#global_pushLimit_idMag").hide();
					}
             	} */
				//停止启动策略状态
				function updateStatus(_obj){
					var _policyType=$('#policyType_ID').val();
					var _parm=$(_obj).attr('rel');
					//var _pushLimit=$('#global_pushLimit_id').val();
					var _policyStatus=0;
					if($(_obj).is(":checked")){
						_policyStatus=1;
					}
					_parm+="&policyStatus="+_policyStatus;
					$.ajax({
						   url: "${basePath}policy/updateStatus",
						   type: "post",
						   data: _parm,
						   success: function(data){
							   if(data.result>0){
								   load_page('policy/policyListEn?policyType='+_policyType);
							   }else{
								   alert("Failed!");
							   }
						   }
						});
				}
				//check策略
				function cheackPolicyStatus(_obj){
					var _policyType=$('#policyType_ID').val();
					//var _pushLimit=$('#global_pushLimit_id').val();
					var _parm=$(_obj).attr('rel');
					//_parm+="&pushLimit="+_pushLimit;
					$.ajax({
						   url: "${basePath}policy/updateStatus",
						   type: "post",
						   data: _parm,
						   success: function(data){
							   if(data.result>0){
								   alert("Succeed!");
								   load_page('policy/policyListEn?policyType='+_policyType);
							   }else{
								   alert("Failed!");
							   }
						   }
						});
				}
				//搜索
				   function searchPolicy(){
					   var _policyType=$('#policyType_ID').val();
					   var _searcheType=$('#searchePolicyType').val();
					   var _searchPolicyName=$('#exampleInputSearch').val();
					   load_page('policy/policyListEn?policyType='+_policyType+'&searchType='+_searcheType+'&searchPolicyName='+decodeURIComponent(_searchPolicyName));
				   }
				
				//delete策略
					function deletePolicy(){
						var _policyId=$('#delete_policy_Id').val();
						 var _policyType=$('#policyType_ID').val();
						$.ajax({
							   url: "${basePath}policy/deletePolicy",
							   type: "post",
							   data: 'policyId='+_policyId,
							   success: function(data){
								   if(data.result>0){
									   alert("Succeed!");
									   load_page('policy/policyListEn?policyType='+_policyType);
								   }else{
									   alert("Failed!");
								   }
							   }
							});
					}
				function changeGlobalStatus(){
					if($('#global_pushStatus_id').is(":checked")){
						 $('#changeTemp_id').html(''); 
						 $('#runningTime').show(); 
						/*  $('#runningTime').hide();  */
					}else{
						$('#changeTemp_id').html('<%=Config.message.get("POLICY_MANAGEMENT_ATTENTION")%>');
						 $('#runningTime').hide(); 
					/* 	$("#policyNameMsg").html("<%=Config.message.get("POLICY_MANAGEMENT_ATTENTION")%>"); */
					}
						
					//	
					
				}
				function showdate(){
					var date1=new Date(2016,5,01);
					var date=new Date();
					var diff=date.getTime()-date1.getTime();
					var thisDay=Math.floor(diff/(1000 * 60 * 60 * 24));
					diff=diff-thisDay*(1000*60*60*24);
					var thisHour = Math.floor(diff/(1000 * 60 * 60));
					diff=diff-thisHour*(1000*60*60);
					var thisMin=Math.floor(diff/(1000*60));
					diff=diff-thisMin*(1000*60);
					var thisSen=Math.floor(diff/(1000));
					$('#runningTime').html('<%=Config.message.get("POLICY_MANAGEMENT_ATTENTION1")%>  '+thisDay+' <%=Config.message.get("POLICY_MANAGEMENT_DAY")%>  '+thisHour+'  <%=Config.message.get("POLICY_MANAGEMENT_HOURS")%>');
					}
				$(function(){
					showdate();
					//Modification全局策略配置
					$('#global_edite_id').bind('click',function(){
						var _showNum=$('#global_showNum_id').val();
						var _showType=$('#global_showType_id').val();
						var _pushInterval=$('#global_pushInterval_id').val();
						var _showTime=$('#global_showTime_id').val();
						var _checkPass=$('#check_passwod_id').val().trim();
						var _pushLimit=$('#global_pushLimit_id').val();
						var _pushStatus=0;
						if($('#global_pushStatus_id').is(":checked")){
							_pushStatus=1;
						}
						if(_checkPass==""){
							window.confirm("Please enter your password");
							return;
						}
						var _parm='showNum='+_showNum+'&pushLimit='+_pushLimit+'&showType='+_showType+'&pushInterval='+_pushInterval+'&showTime='+_showTime+'&pushStatus='+_pushStatus+'&checkPass='+_checkPass;
						$.ajax({
							   url: "${basePath}policy/updateGlobalInfo",
							   type: "post",
							   data: _parm,
							   success: function(data){
								   if(data.result==1){
									   alert("<%=Config.message.get("POLICY_MANAGEMENT_SUCCEED")%>!");
								   }else if(data.result==2){
									   alert("<%=Config.message.get("POLICY_MANAGEMENT_PASSWORD_ATTENTION_CORRECT")%>");
								   }else{
									   alert("<%=Config.message.get("POLICY_MANAGEMENT_SYSTEM_EXECEPTIONS")%>");
								   }
							   }
							});
						
					});
					
					
				});
				</script>
