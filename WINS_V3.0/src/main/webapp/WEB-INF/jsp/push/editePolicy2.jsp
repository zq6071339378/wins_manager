<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%><%@taglib prefix="myEl" uri="http://hotdata.com/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<li><a href="#"><%=Config.message.get("POLICY_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("PLOICY_EDIT_PUSH_PLAN")%></h3>
							</div>
							<div class="description">Simple Tables with exclusive UI
								experience</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<!--Add groupdialog-->
				<div class="modal fade" id="box-policyGroup" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
										type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("CREATE_POLICY_ADD_GROUP")%></h4>
							</div>
							<div class="panel-body">
								<form action="${basePath}group/addGroup" method="post"
									  enctype="multipart/form-data" class="form-horizontal"
									  id="jForm">
									<div class="col-md-8 pull-left">
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("CREATE_POLICY_GROUP_NAME")%>：</label>
											<div class="col-sm-7 row_left">
												<input type="text" class="form-control" name="groupName"
													   placeholder="Group name" id="groupName">
											</div>
										</div>
										<div class="form-group has-error">
											<label class="col-md-4"></label> <label class="control-label"
																					for="inputWarning" id="groupNameMsg"></label>
										</div>
										<div class="form-group msg_font form_group">
											<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("CREATE_POLICY_GROUP_IMAGE")%>：</label>
											<input type="file" style="display: none" id="img"
												   onchange="uploadImg();" name="pic">
											<div class="col-md-1 row_left">
												<div class="input-append">
													<a class="btn btn-info"
													   onclick="$('input[id=img]').click();"><%=Config.message.get("CREATE_POLICY_UPLOAD")%>&nbsp;&nbsp;</a>
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
												<input type="file" style="display: none" id="lefile" onchange="Mimport();" name="multiImport"> <input
													type="hidden" name="path" id="path">
												<div class="input-append">
													<a onclick="$('input[id=lefile]').click();" class="btn btn-info"><%=Config.message.get("CREATE_POLICY_SCAN")%>&nbsp;&nbsp;</a><span id="import"></span>
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
				<!--Add groupdialog END-->
				<!--创建策略-->
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
						<div id="formWizard" class="box">
							<div class="box-body form">
								<form class="form-horizontal" onsubmit="return false;"
									id="account_create_policy_id_1" novalidate="novalidate">
									<div class="wizard-form">
										<div class="wizard-content">
											<div class="divide-20"></div>
											<div class="tab-content">
												<!--创建策略step2-->
												<div id="payment" class="tab-pane active" style="">
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"><span
															class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_TIME")%>：</label>
														<div class="col-md-4 row_left">
															<div class="controls">
																<div class="input-group date" id="reservation">
																	<input type="text" placeholder="Start time   -   End time" required="required" id="doubleDate_id" class="form-control" style="padding-left: 30px !important;" value="${myEl:subStr(policyinfo.startTime,10)}-${myEl:subStr(policyinfo.endTime,10)}">
																		<span class="input-group-addon">
																			<span class="fa fa-calendar"></span>
																		</span>
																	<input type="hidden" name="policyEndTime" id="pushpolicyEndtime_id" value="${myEl:subStr(policyinfo.endTime,10)}">
																	<input type="hidden" name="policyStartTime" id="pushpolicyStarttime_id" value="${myEl:subStr(policyinfo.startTime,10)}">
																</div>
															</div>
														</div>
														<div class="col-md-5 row_left">
															<span class="span_font"> <a class="collapsed"
																data-parent="#accordion" href="#collapse2"
																data-toggle="collapse"><%=Config.message.get("POLICY_TIME_ADVANCED_SETUP")%><i
																	class="fa fa-angle-double-down"></i>
															</a>
															</span>
														</div>
													</div>
													<!--高级设置TABLE-->
													<div class="form-group collapse msg_font form_group"
														class="panel-body col-md-12" id="collapse2">
														<label class="control-label col-md-3 label1"><%=Config.message.get("PUSH_TIME_PERIOD")%>：</label>
														<div class="form_inputsTime col-md-8 row_left">
															<div class="box-body" id="box-body">
																<table class="table table-bordered time_table">
																	<thead>
																		<tr>
																			<th><%=Config.message.get("CREATE_POLICY_PUSH_TIME")%></th>
																			<th>00:00<br /> 02:00
																			</th>
																			<th>02:00<br /> 04:00
																			</th>
																			<th>04:00<br /> 06:00
																			</th>
																			<th>06:00<br /> 08:00
																			</th>
																			<th>08:00<br /> 10:00
																			</th>
																			<th>10:00<br /> 12:00
																			</th>
																			<th>12:00<br /> 14:00
																			</th>
																			<th>14:00<br /> 16:00
																			</th>
																			<th>16:00<br /> 18:00
																			</th>
																			<th>18:00<br /> 20:00
																			</th>
																			<th>20:00<br /> 22:00
																			</th>
																			<th>22:00<br /> 24:00
																			</th>
																		</tr>
																	</thead>
																	<tbody>
																	<tr>
																	 	<tr>
																		<td><%=Config.message.get("MONDAY")%></td>
																			<c:forEach items="${policyinfoTimeList.LIST }" var="timeBean">
																				<c:if test="${myEl:yunSuan(timeBean.cycleWeek,0) eq true}">
																					<c:forEach begin="1" end="24" var="hourTemp" step="2">
																						<c:set var="setChangeWeek" value="1"></c:set>
																						<c:if test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																							<td class="changeweektime" rel="1-${hourTemp-1},1-${hourTemp},">
																								<span class="checke_click"></span></td>
																						</c:if>
																						
																							<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																							<td class="changeweektime"
																								rel="2-${hourTemp-1},2-${hourTemp},"></td>
																						</c:if>
																					
																					</c:forEach>
																					</c:if>
																			</c:forEach>
																		</tr>
																		<tr>
																			<c:forEach items="${policyinfoTimeList.LIST }" var="timeBean">
																				<c:if
																					test="${myEl:yunSuan(timeBean.cycleWeek,1) eq true}">
																					<td><%=Config.message.get("TUESDAY")%></td>
																					<c:forEach begin="1" end="24" var="hourTemp"
																						step="2">
																						<c:set var="setChangeWeek" value="1"></c:set>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																							<td class="changeweektime"
																								rel="2-${hourTemp-1},2-${hourTemp},"><span
																								class="checke_click"></span></td>
																						</c:if>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																							<td class="changeweektime"
																								rel="2-${hourTemp-1},2-${hourTemp},"></td>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</c:forEach>
																		</tr>
																		<tr>
																			<c:forEach items="${policyinfoTimeList.LIST }"
																				var="timeBean">
																				<c:if
																					test="${myEl:yunSuan(timeBean.cycleWeek,2) eq true}">
																					<td><%=Config.message.get("WEDNESDAY")%></td>
																					<c:forEach begin="1" end="24" var="hourTemp"
																						step="2">
																						<c:set var="setChangeWeek" value="1"></c:set>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																							<td class="changeweektime"
																								rel="3-${hourTemp-1},3-${hourTemp},"><span
																								class="checke_click"></span></td>
																						</c:if>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																							<td class="changeweektime"
																								rel="3-${hourTemp-1},3-${hourTemp},"></td>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</c:forEach>
																		</tr>

																		<tr>
																			<c:forEach items="${policyinfoTimeList.LIST }"
																				var="timeBean">
																				<c:if
																					test="${myEl:yunSuan(timeBean.cycleWeek,3) eq true}">
																					<td><%=Config.message.get("THURSDAY")%></td>
																					<c:forEach begin="1" end="24" var="hourTemp"
																						step="2">
																						<c:set var="setChangeWeek" value="1"></c:set>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																							<td class="changeweektime"
																								rel="4-${hourTemp-1},4-${hourTemp},"><span
																								class="checke_click"></span></td>
																						</c:if>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																							<td class="changeweektime"
																								rel="4-${hourTemp-1},4-${hourTemp},"></td>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</c:forEach>
																		</tr>

																		<tr>
																			<c:forEach items="${policyinfoTimeList.LIST }"
																				var="timeBean">
																				<c:if
																					test="${myEl:yunSuan(timeBean.cycleWeek,4) eq true}">
																					<td><%=Config.message.get("FRIDAY")%></td>
																					<c:forEach begin="1" end="24" var="hourTemp"
																						step="2">
																						<c:set var="setChangeWeek" value="1"></c:set>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																							<td class="changeweektime"
																								rel="5-${hourTemp-1},5-${hourTemp},"><span
																								class="checke_click"></span></td>
																						</c:if>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																							<td class="changeweektime"
																								rel="5-${hourTemp-1},5-${hourTemp},"></td>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</c:forEach>
																		</tr>
																		<tr>
																			<c:forEach items="${policyinfoTimeList.LIST }"
																				var="timeBean">
																				<c:if
																					test="${myEl:yunSuan(timeBean.cycleWeek,5) eq true}">
																					<td><%=Config.message.get("SATURDAY")%></td>
																					<c:forEach begin="1" end="24" var="hourTemp"
																						step="2">
																						<c:set var="setChangeWeek" value="1"></c:set>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																							<td class="changeweektime"
																								rel="6-${hourTemp-1},6-${hourTemp},"><span
																								class="checke_click"></span></td>
																						</c:if>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																							<td class="changeweektime"
																								rel="6-${hourTemp-1},6-${hourTemp},"></td>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</c:forEach>
																		</tr>
																		<tr>
																			<c:forEach items="${policyinfoTimeList.LIST }"
																				var="timeBean">
																				<c:if
																					test="${myEl:yunSuan(timeBean.cycleWeek,6) eq true}">
																					<td><%=Config.message.get("SUNDAY")%></td>
																					<c:forEach begin="1" end="24" var="hourTemp"
																						step="2">
																						<c:set var="setChangeWeek" value="1"></c:set>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																							<td class="changeweektime"
																								rel="7-${hourTemp-1},7-${hourTemp},"><span
																								class="checke_click"></span></td>
																						</c:if>
																						<c:if
																							test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																							<td class="changeweektime"
																								rel="7-${hourTemp-1},7-${hourTemp},"></td>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</c:forEach>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
													<!--高级设置TABLE-->

													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label">
															<span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_USER_TYPE")%>：
														</label>
														<div class="col-sm-4" style="padding-left: 0px">
															<select id="push_user_type" class="form-control" required="required" name="pushUserType">
																<option  <c:if test="${policyinfo.roamType eq 0}" >selected="selected"</c:if> value="0"><%=Config.message.get("NO_LIMIT")%></option>
																<c:if test="${isEnglish ne true}">
																	<option  <c:if test="${policyinfo.roamType eq 1}" >selected="selected"</c:if> value="1"><%=Config.message.get("LOCAL_USERS")%></option>
																	<option  <c:if test="${policyinfo.roamType eq 2}" >selected="selected"</c:if> value="2"><%=Config.message.get("ROAM_USERS")%></option>
																	<option  <c:if test="${policyinfo.roamType eq 3}" >selected="selected"</c:if> value="3"><%=Config.message.get("3A_AUTHENTICATION_USER")%></option>
																</c:if>
																<option  <c:if test="${policyinfo.roamType eq 4}" >selected="selected"</c:if> value="4"><%=Config.message.get("NETWORK_TRAFFIC_USER")%></option>
															</select>
														</div>
													</div>

													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"><span
															class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_GROUP")%>：</label>
														<div class="col-md-4 form_input login-box">
															<div class="form-group col-md-12  search_icon">
																<input type="search" placeholder="Please input group name" class="form-control" id="searchGroupinput"
																	value="<c:forEach items="${policyinfoGroup.LIST}" var="groupBean"> ${groupBean.groupName},</c:forEach>	">
															</div>
														</div>
														<div class="col-md-2 pull-right">
															<span class="span_font"> <a class="config"
																href="#box-policyGroup" data-toggle="modal"><%=Config.message.get("CREATE_POLICY_CREATE_GROUP")%></a>
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3"></label>
														<div class="col-md-8 row_left group_box">
															<%--<div>
																<input type="radio" required="required" name="roamType" value="4" onclick="$('.groupalllist').hide();"/>all<input type="radio" style="margin-left: 3px;" required="required" name="roamType" value="0" checked="checked"  onclick="$('.groupalllist').show();"/>change group
															</div>--%>
															<div id="" class="row tab-pane fade in active">
																<!-- BOX -->
																<div class="box">
																	<div
																		class="form-group msg_font form_group groupalllist">

																		<c:forEach items="${groupList.groupLists }"
																			var="groupBean">
																			<div class="col-md-3 grouplist_change">
																				<c:set var="groupTemp" value="0" />
																				<c:forEach items="${policyinfoGroup.LIST}"
																					var="singleBean">
																					<c:if
																						test="${groupBean.groupId eq singleBean.groupId }">
																						<c:set var="groupTemp" value="1" />
																					</c:if>
																				</c:forEach>
																				<a href="javascript:;"
																					class="thumbnail clickImg <c:if test ='${groupTemp eq 1 }'>selected</c:if>"
																					rel="${groupBean.groupId }"> <img
																					src="img/gallery/Mac.png"
																					alt="${groupBean.groupName}">
																				</a>

																				<div class="caption" style="margin-left: 20px;">
																					<h5>${groupBean.groupName}</h5>
																					<p><%=Config.message.get("CREATE_POLICY_TOTAL")%> ${groupBean.memberNum } <%=Config.message.get("CREATE_POLICY_NUMBER_OF_PEOPLE")%></p>
																				</div>
																			</div>
																		</c:forEach>
																		<input type="hidden" id="changegroupName_id" required="required" name="groupIds" value="" />
																		<input type="hidden" id="changeTime_id" required="required" name="timeIds" value="" />
																	</div>
																</div>
																<!-- /BOX -->
															</div>
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
																	Choose&nbsp;<i class="fa fa-angle-down"></i>
																</button>
																<button class="btn btn-default btn-block"
																		id="undo_redo_rightSelected" type="button">
																	<i class="fa fa-angle-double-right"></i>
																</button>
															</div>
															<div class="col-xs-5">
																<div class="choices">
																	<span style="float: left"><%=Config.message.get("THE_PROJECTS_WERE_CHOSE")%></span>
																	<a style="float: right" onclick="$('#activity-ips-selected').html('');$('#priceUrl').val('');$('#priceofUrl').html('')" href="javascript:;"><%=Config.message.get("CLEAN_ALL")%></a>
																	<div style="clear: both; height: 5px;"></div>
																</div>
																<div class="box-body likelist" style="overflow: auto;">
																	<ul class="ullist" id="activity-ips-selected">
																		<c:forEach items="${policyinfoIPS.LIST}" var="ipsBean">
																			<li onclick="$(this).remove();" class="col-md-6" style="width: 105px;">
																				<a class="button changearealist" role="button">${ipsBean.ipsName}</a>
																				<span class="icon">
																					<i class="fa fa-times"></i>
																					<input required="required" name="ips" value="${ipsBean.ipsId}" type="hidden">
																				</span>
																			</li>
																		</c:forEach>
																	</ul>
																</div>
															</div>
															<div class="col-md-5 pull-right"></div>
														</div>
													</div>
													<!--  Ips  -->

													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label">
															<span class="star"></span>&nbsp;&nbsp;<%=Config.message.get("PUSH_AREA")%>：</label>
														<div class="col-md-5">
															<label style="padding-left: 0px;" class="radio-inline">
																<input type="radio" checked="checked" class="uniform activity-has" onclick="$('#changeCity_id').hide();" name="isCity" value="0"><%=Config.message.get("NO_LIMIT")%>
															</label>
															<label style="margin-left: 3px;" class="radio-inline">
																<input type="radio" class="uniform activity-has" onclick="$('#changeCity_id').show();" name="isCity" value="1"><%=Config.message.get("CHOOSE_PUSH_AREA")%>
															</label>
														</div>
													</div>
													<div class="form-group msg_font form_group"
														id="changeCity_id" style="display: none;">
														<label for="password-input" class="col-md-3 control-label"></label>
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
																	chose&nbsp;<i class="fa fa-angle-down"></i>
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
																<div class="box-body likelist row" style="overflow: auto;">
																	<ul class="ullist" id="activity-interest-selected">
																		<c:forEach items="${policyinfoArea.LIST}" var="areaBean">
																			<li onclick="$(this).remove();" class="col-md-6" style="width: 105px;">
																				<a class="button changearealist" role="button">${areaBean.cityName }</a>
																				<span class="icon">
																					<i class="fa fa-times"></i>
																					<input required="required" name="areas" value="${areaBean.cityId }" type="hidden">
																				</span>
																			</li>
																		</c:forEach>
																	</ul>
																</div>
															</div>
															<div class="col-md-5 pull-right"></div>
														</div>
													</div>
													<!-- 媒体标签 -->
												<c:if test="${isEnglish eq true}">
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
																	value="1"><%=Config.message.get("CHOOSE_THE_WEBSITE")%> </label>
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
																		onclick="$('#activity-industry-selected').html('');"
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

													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("BID")%>：</label>
														<div class="col-sm-4 row_left">
														<input type="text"  id="priceUrl"   placeholder="change the price here" name="unitPrice" class="form-control" value="${policyinfo.unitPrice}"    onblur="getunitPrice()">
														<input type="hidden" id="newunitPrice" name="newunitPrice">
													</div>
														<div class="col-md-5 row_left">
														<span class="span_fonts"><%=Config.message.get("REFERENCE_PRICE")%></span>&nbsp;<span class="span_fonts" id="priceofUrl"></span>
														</div>
													</div>
													<!-- 媒体标签 -->
												</c:if>
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"><span
															class="star"></span>&nbsp;&nbsp;<%=Config.message.get("INPUT_WEB_SITE")%>：</label>
														<div class="col-md-4 row_left">
															<input type="text" placeholder="please input url"
																id="urlDomainInput_id" class="form-control" value="<c:forEach items="${policyinfoUrlDomain.LIST}" var="urlBean">${urlBean.urlDomain},</c:forEach>">
														</div>
														<div class="col-md-2">
															<button class="btn btn-primary" id="addUrlDomainbutton"><%=Config.message.get("CREATE_POLICY_ADD")%></button>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"></label>
														<div class="col-md-9 colleft">
															<ul class="ullist urlDomainlist">
																<c:forEach items="${policyinfoUrlDomain.LIST }"
																	var="urlBean">
																	<li onclick="$(this).remove();"><a role="button"
																		class="button">${urlBean.urlDomain }</a> <span
																		class="icon"><i class="fa fa-times"></i> <input
																			type="hidden" required="required"
																			value="${urlBean.urlDomain }" name="urlDomain">
																	</span></li>
																</c:forEach>
															</ul>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label">
															<span class="star"></span>&nbsp;&nbsp;<%=Config.message.get("SEARCH_THE_KEY_WORDS")%>：
														</label>
														<div class="col-md-4 row_left">
															<input type="text" placeholder="Please input key words" id="keywordInput_id" class="form-control" value=" <c:forEach items="${policyinfoKeyWord.LIST}" var="keywordBean">${keywordBean.keyWordName},</c:forEach>">
														</div>
														<div class="col-md-2">
															<button class="btn btn-primary" id="addkeywordbutton"><%=Config.message.get("CREATE_POLICY_ADD")%></button>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"></label>
														<div class="col-md-6 colleft">
															<ul class="ullist keywordlist">
																<c:forEach items="${policyinfoKeyWord.LIST }" var="keyWordBean">
																	<li onclick="$(this).remove();">
																		<a role="button" class="button">${keyWordBean.keyWordName }</a>
																		<span class="icon">
																			<i class="fa fa-times"></i>
																			<input type="hidden" required="required" value="${keyWordBean.keyWordName }" name="kewWord">
																		</span>
																	</li>
																</c:forEach>
															</ul>
														</div>
													</div>

													<!-- 关联广告主 -->
												<c:if test="${isEnglish eq true}">
													<div class="form-group msg_font form_group" style="margin-bottom: 0px">
														<label for="password-input" class="col-md-3 control-label">
															<span class="star"></span>&nbsp;&nbsp;<%=Config.message.get("ADVERTISER_CUSTOMERS")%>：
														</label>
														<div class="col-md-4 row_left">
															<div class="form-group" style="margin-left: 0px;margin-right: 0px">
																<input type="text" placeholder="Please input the key words to search customers" id="customerInput_id" class="form-control" onclick="showSelectCustomer(this)" oninput="selectCustomer(this);">
																<select id="select_customer" multiple class="form-control" style="display: none">
																</select>
															</div>
														</div>
														<div class="col-md-2">
															<button class="btn btn-primary" id="addcustomerbutton"><%=Config.message.get("CREATE_POLICY_ADD")%></button>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"></label>
														<div class="col-md-6 colleft">
															<ul class="ullist customerlist">
																<c:forEach items="${policyinfoCustomer.LIST }" var="cuBean">
																	<li onclick="$(this).remove();">
																		<a role="button" class="button">${cuBean.customerName}</a>
																		<span class="icon">
																			<i class="fa fa-times"></i>
																			<input type="hidden" required="required" value="${cuBean.customerId}" name="customerId">
																		</span>
																	</li>
																</c:forEach>
															</ul>
														</div>
													</div>
												</c:if>
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-3 control-label"></label>
														<div class="col-md-6 row_left">
															<span class="span_fonts"><%=Config.message.get("SUPPORTING_BROWSERS")%></span>
														</div>
													</div>
												</div>
												<!--创建策略step2 END-->
											</div>
										</div>
										<div class="row">
											<div class="col-md-10">
												<div class="col-md-offset-4" id="btn_group">
													<a href="#policy/toPolicyEditeInfo?policyId=${policyinfo.policyId}" onclick="load_page('policy/toPolicyEditeInfo?policyId=${policyinfo.policyId}');"
														class="btn btn-md btn-default"> <%=Config.message.get("CREATE_POLICY_CANCEL")%> </a>
													<a href="javascript:;" onclick="editeTwo();" class="btn btn-md btn-primary btn_left"> <%=Config.message.get("SAVE")%> </a>
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" id="policyId_edite_id" name="policyId" value="${policyinfo.policyId}" />
								</form>
							</div>
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
<script type="text/javascript">
	var data_cu = [];
	$(function(){
		$.ajax({
			url: "policy/searchCustomer",
			type: "post",
			dataType:"json",
			success: function(data){
				console.log(data);
				var customer =data.result.list;
				if(customer.length<1){
					$("#customerInput_id").val("No selection of advertisers");
				}
				for(var i=0;i<customer.length;i++){
					var obje={};
					obje.name = customer[i].customerName;
					obje.Id = customer[i].customerId;
					data_cu.push(obje);
					var option="<option value='"+customer[i].customerId+"'>"+customer[i].customerName+"</option>";
					var $option = $(option);
					$("#select_customer").append($option);
				}
				$("#select_customer").find("option").each(function(){
					$(this).dblclick(function(){
						var p_Id = $(this).val();
						var p_name = $(this).html();
						$("#customerInput_id").val("");
						$("#customerInput_id").attr("status",p_Id);
						$("#customerInput_id").val(p_name);
					});
				});

			},
			error:function(){
				alert("load customer faild");
			}
		});
	});
	var isEnglish = <%=Config.message.get("isEnglish")%>;
	//alert("isEnglish:" +isEnglish);
	function showSelectCustomer(obj){
		$(obj).val("");
		$(obj).attr("status","");
		$(obj).next().html("");
		$.each(data_cu,function(index, content){
			var option="<option value='"+content.Id+"'>"+content.name+"</option>";
			var $option = $(option);
			$(obj).next().append($option);
		});
		$(obj).next().find("option").each(function(){
			$(this).dblclick(function(){
				var p_Id = $(this).val();
				var p_name = $(this).html();
				$(obj).val("");
				$(obj).attr("status",p_Id);
				$(obj).val(p_name);
				$(obj).prev().val(p_Id);
				$(obj).next().hide();
			});
		});
		$(obj).next().show();
	}

	function selectCustomer(obj){
		$(obj).next().show();
		$(obj).attr("status","");
		var reg = $(obj).val().trim();
		$(obj).next().html("");
		$.each(data_cu,function(index, content){
			if((content.name.indexOf(reg))!=-1){
				var option="<option value='"+content.Id+"'>"+content.name+"</option>";
				var $option = $(option);
				$(obj).next().append($option);
			}
		});
		$(obj).next().find("option").each(function(){
			$(this).dblclick(function(){
				var p_Id = $(this).val();
				var p_name = $(this).html();
				$(obj).val("");
				$(obj).attr("status",p_Id);
				$(obj).val(p_name);
				$(obj).next().hide();
			});
		});
	}

	$('#addcustomerbutton').click(function(){
		var customerName=$('#customerInput_id').val();
		var customerId = $("#customerInput_id").attr("status");
		if(customerId=="" || customerId==undefined){
			$("#customerInput_id").val("");
			return;
		}
		var Ids=[];
		$(".customerlist").find("input").each(function(){
			var Id = $(this).val();
			Ids.push(Id);
		});
		for(var i=0;i<Ids.length;i++){
			if(customerId==Ids[i]){
				//alert("Can not add duplicate advertiser");
				return;
			}
		}
		if(customerId!=""){
			var customer_html='<li onclick="$(this).remove();">' +
					'<a role="button" class="button">'+customerName+'</a>' +
					'<span class="icon"><i class="fa fa-times"></i>' +
					'<input type="hidden" required="required" value="'+customerId+'" name="customerId">' +
					'</span></li>';
			$('.customerlist').append(customer_html);
			$('#customerInput_id').val('').focus();
			$("#customerInput_id").attr("status","");
		}else{
			$('#customerInput_id').val('').focus();
		}
	});

	function getunitPrice(){
		var priceUrl=$("#priceUrl").val();
		var priceofUrl=$("#priceofUrl").html();
		if(priceUrl==0||priceUrl==""){
			$("#newunitPrice").val(priceofUrl);
		}else{
			$("#newunitPrice").val(priceUrl);
		}
	}

	$(document).ready(function() {
		$('#doubleDate_id').daterangepicker(
				{},
				function(start, end) {
					var starttime = start.format('YYYY-MM-DD');
					var endtime = end.format('YYYY-MM-DD');
					var dateN = new Date();
					var dateMonth = parseInt(dateN.getMonth()) + 1;
					var _nowDate = dateN.getFullYear() + '-'
							+ dateMonth + '-' + dateN.getDate(); //获取当前月份(0-11,0代表1月)
					if (start < dateN) {
						starttime = _nowDate;
					}
					if (end < dateN) {
						endtime = _nowDate;
					}
					$("#doubleDate_id").val(starttime + '-' + endtime);
					$('#pushpolicyEndtime_id').val(endtime);
					$('#pushpolicyStarttime_id').val(starttime);
				});

			});

	$(function() {
		$("#saveGroup").click(function(){
			var groupName = $("#groupName").val();
			if (groupName == "") {
				$("#groupNameMsg").show();
				$("#groupNameMsg").html("<%=Config.message.get("PLEASE_INPUT_GROUP_NAME")%>");
				return false;
			} else {
				$("#groupNameMsg").hide();
			}
			if (groupName.trim().length > 20) {
				$("#groupNameMsg").show();
				$("#groupNameMsg").html("<%=Config.message.get("NO_MORE_THAN_WORDS")%>");
				return false;
			} else {
				$("#groupNameMsg").hide();
			}
			$("#userAccount").attr("value",$("#mulUser").val());
			var filepath = $("#lefile").val().trim();
			var s = filepath.substr(filepath.length - 4);
			if ($("#mulUser").val().trim().length == 0&& s.length == 0){
				$("#mulUserMsg").show();
				$("#mulUserMsg").html("<%=Config.message.get("PLEASE_INPUT_USER_ACCOUNT")%>");
				return false;
			} else {
				$("#mulUserMsg").hide();
			}
			if (s != ".txt"){
				alert("<%=Config.message.get("THE_FILE_ERROR_TXT")%>");
				return false;
			}
			$("#jForm").ajaxSubmit(function(data) {
				if (data.result == -1) {
					alert("error");
				}
				if (data.code == 1) {
					alert('Succeed!');
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
							'<h5 >'+groupName.trim()+'</h5>'+'<p>'+total+' '+data.version+' '+num_Of_P+'</p>'+
							'</div>'+'</div>';
					$('.groupalllist').prepend(_addGrouphtml);
					//load_page('policyGroup.html');
				}
			});
		});
	});

	function editeTwo() {
		var target = $('#account_create_policy_id_1');
		//选择群组
		var _changegroup = '', _timeChange = '';
		var _oroamTypeTemp=$("#push_user_type").val();
		$('.groupalllist').find('.selected').each(function(i, n) {
			var _groupid = $(this).attr('rel');
			_changegroup += _groupid + ',';
		});
		if(_oroamTypeTemp==0&&_changegroup==''){
			alert('Please select group');
			return false;
		}
		$('.checke_click').each(function(i, n) {
			var _timeTemp = $(n).parent().attr('rel');
			_timeChange += _timeTemp;
		});
		$('#changeTime_id').val(_timeChange);
		$('#changegroupName_id').val(_changegroup);
		var valateflag = true;
		if (valateflag) {
			var _policyId = $("#policyId_edite_id").val();
			var params = [];
			var target1 = $('#account_create_policy_id_1');
			params[0] = target1.serialize();
			$.ajax({
				url : 'policy/editePolicyTwo',
				type : "post",
				data : params.join("&"),
				success : function(data) {
					alert("Succeed!");
					load_page('policy/toPolicyEditeInfo?policyId='+ _policyId);
				}
			});

		}
	}

	function Mimport(){
		var v=$("#lefile").val();
		$("#import").text(v);
	}
</script>