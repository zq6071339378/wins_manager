<%@page import="com.datacomo.wins.controller.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="myEl"
	uri="http://hotdata.com/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<script src="${basePath }js/jquery.form.js"></script>
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("HOME")%></a></li>
								<li><a href="#policy/policyList" onclick="load_page('policy/policyList');"><%=Config.message.get("POLICY_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("POLICY_INFORMATION")%></h3>
							</div>
							<div class="description">Policy details Check the basic
								info, push plans and material info</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box">
							<div class="row ">
								<div class="box-body col-md-2" style="display: none;">
									<form method="post" id="global_search_form" action="#"
										class="navbar-search tree_search">
										<input type="text" id="aw-search-query" name="q"
											autocomplete="off" placeholder="Please input policyname"
											class="form-control search-query"> <span
											onclick="$('#global_search_form').submit();"
											id="global_search_btns" title="Search" class="login-box"><i
											class="fa fa-search"></i></span>
										<div class="aw-dropdown" style="display: none;">
											<div class="mod-body">
												<p class="title">Search in key words</p>
												<ul class="aw-dropdown-list hide"></ul>
												<p class="search">
													<span>Search:</span><a
														onclick="$('#global_search_form').submit();"></a>
												</p>
											</div>
											<div class="mod-footer">
												<a class="pull-right btn btn-mini btn-success publish"
													onclick="$('#header_publish').click();" href="">Question</a>
											</div>
										</div>
									</form>
									<div class="box-body">
										<div id="tree2" class="tree tree-unselectable">
											<div style="display: none;" class="tree-folder">
												<div class="tree-folder-header">
													<i class="fa-folder fa"></i>
													<div class="tree-folder-name"></div>
												</div>
												<div class="tree-folder-content"></div>
												<div style="display: none;" class="tree-loader"></div>
											</div>
											<div style="display: none;" class="tree-item">
												<div class="tree-item-name"></div>
											</div>
											<div style="display: block;" class="tree-folder">
												<div class="tree-folder-header">
													<i class="fa-folder red fa"></i>
													<div class="tree-folder-name">My activities</div>
												</div>
												<div class="tree-folder-content"></div>
												<div style="display: none;" class="tree-loader">
													<div class="tree-loading">
														<i class="fa fa-spinner fa-2x fa-spin"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--策略详情-->
								<div class="col-md-10"
									style="margin-top: -30px; padding-top: 30px;">
									
									<c:if test="${isEnglish eq true}">
										<div class="form-group msg_font form_group row">
											<div class="col-sm-12">
												<div class="box-title title_account">
													<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("CREATEPOLICY_ADVERTISER_INFORMATION")%></span>
	
												</div>
											</div>
										</div>
										<div class="form-group msg_font form_group row">
											<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATEPOLICY_ADVERTISER")%>：</label>
											<div class="col-md-9">
												<c:forEach items="${policyCustomer.LIST }" var="customerBean" varStatus="i">
													<c:if test="${i.count eq 1 }"><span class="span_font">${customerBean.customerName }</span></c:if>
												</c:forEach>
											</div>
										</div>
										
										<div class="form-group msg_font form_group row">
											<div class="col-sm-12">
												<div class="box-title title_account">
													<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("CREATEPOLICY_INTRODUCE")%></span>
	
												</div>
											</div>
										</div>
										<div class="form-group msg_font form_group row">
											<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATEPOLICY_ACTIVITYNAME")%>：</label>
											<div class="col-md-9">
												<c:forEach items="${policyCustomer.LIST }" var="customerBean" varStatus="i">
													<span class="span_font">${customerBean.activityName },</span>
												</c:forEach>
											</div>
										</div>
									</c:if>
									
									<div class="form-group msg_font form_group row">
										<div class="col-sm-12">
											<div class="box-title title_account">
												<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("POLICY_INFORMATION")%></span>

											</div>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_NAME")%>：</label>
										<div class="col-md-9">
											<span class="span_font">${policyinfo.policyName }</span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PRIORITY")%>：</label>
										<div class="col-sm-9">
											<span class="span_font">${policyinfo.policyLevel }</span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_INTERVIAL")%>：</label>
										<div class="col-sm-9">
											<span class="span_font">${policyinfo.pushInterval }s</span>
										</div>
									</div>
									<div class="form-group msg_font form_group row" style="display: none;">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_SHOW_LIMIT")%>：</label>
										<div class="col-sm-9">
											<span class="span_font">${policyinfo.pushLimit } <%=Config.message.get("TIMES_PERSON_DAY")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_TOTAL_SHOW")%> ：</label>
										<div class="col-sm-7">
											<span class="span_font">${policyinfo.showLimit } <%=Config.message.get("TIMES_DAY")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_THE_WAY_OF_SHOW")%>：</label>
										<div class="col-sm-7">
											<span class="span_font"> 
											<c:if test="${policyinfo.showType eq 1}"><%=Config.message.get("CREATE_POLICY_REPLACE")%></c:if> 
											<c:if test="${policyinfo.showType eq 2}"><%=Config.message.get("CREATE_POLICY_FLOAT")%></c:if> 
											<c:if test="${policyinfo.showType eq 3}"><%=Config.message.get("CREATE_POLICY_PLACE_ON_BOTH_SIDES")%></c:if> 
											<c:if test="${policyinfo.showType eq 4}"><%=Config.message.get("HANGING_ON_THE_EDGE")%></c:if> 
											<c:if test="${policyinfo.showType eq 5}"><%=Config.message.get("PUT_ON_THE_BACK")%></c:if> 
											<c:if test="${policyinfo.showType eq 6}">toolbar</c:if>
											</span>
										</div>
									</div>
								<c:if test="${isEnglish ne true}">
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PUSH_TYPE")%>：</label>
										<div class="col-sm-7">
											<span class="span_font"><c:if
													test="${policyinfo.policyType eq 1}"><%=Config.message.get("PUSH_ONLINE")%></c:if>
												<c:if test="${policyinfo.policyType eq 4}"><%=Config.message.get("PUSH_OUTLINE")%>
												</c:if></span>
										</div>
									</div>
									<div class="form-group msg_font form_group row" style="display: ">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PUSH_OUTILNE")%>:</label>
											<div class="col-sm-7">
													<c:forEach   items="${policyinfoWebList.LIST}" var="WebList">
														<span >	${WebList.WebUrl} </span>
													</c:forEach>
											</div>
									</div>
								</c:if>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_TERMINAL_TYPE")%>：</label>
										<div class="col-sm-7">
											<span class="span_font"><c:if
													test="${policyinfo.terminalType eq 1}">phone</c:if>
												<c:if test="${policyinfo.terminalType eq 0}">pc/pad</c:if></span>
										</div>
									</div>
								<c:if test="${isEnglish eq true}">
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_WAYS_TO_BUY")%>：</label>
										<div class="col-sm-7">
											<span class="span_font"><c:if
													test="${policyinfo.buy_type eq 1}">CPM</c:if>
												<c:if test="${policyinfo.buy_type eq 2}">CPC</c:if></span>
										</div>
									</div>
								</c:if>
									<div class="form-group msg_font form_group row" style="display: none">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_DAY_LIMIT")%>：</label>
										<div class="col-sm-7">
											<c:if test="${policyinfo.showLimit eq 0}">
												<span class="span_font">${policyinfo.pushLimit } <%=Config.message.get("CREATE_POLICY_TIMES")%></span>
											</c:if>
											<c:if test="${policyinfo.showLimit > 0}">
												<span class="span_font">${policyinfo.showLimit } <%=Config.message.get("CREATE_POLICY_TIMES")%></span>
											</c:if>
										</div>
									</div>
									<div class="form-group msg_font form_group row" style="display: block">
										<label for="password-input" class="col-md-3 control-label" style="padding-top:6px;"><%=Config.message.get("CREATE_POLICY_USER_SHOW_LIMIT")%>：</label>
										<div class="col-sm-9">
											<span class="span_font">
												${policyinfo.pushLimit}
											<%--<c:if test="${policyinfo.pushSpeed eq 2 }"> ${policyinfo.pushLimit }</c:if>
											<c:if test="${policyinfo.pushSpeed eq 1 }"> ${policyinfo.pushShow/policyinfo.pushDay }</c:if> <%=Config.message.get("TIMES_PERSON_DAY")%>--%>
											</span>
										</div>
									</div>
								<c:if test="${isEnglish eq true}">
								  	<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label" style="padding-top:6px;"><%=Config.message.get("CREATE_POLICY_INDUSTRY_ATTRIBUTES")%> ：</label>
										<div class="col-sm-9">
											<span class="span_font">${policyinfo.industryName}</span>
										</div>
									</div>
								  	<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label" style="padding-top:6px;"><%=Config.message.get("CREATE_POLICY_AD_TYPE")%>：</label>
										<div class="col-sm-9">
											<span class="span_font">
												<c:if test="${policyinfo.objectid eq 9 }"> <span>others</span> </c:if>
												<c:if test="${policyinfo.objectid eq 1 }"> <span>corporate branding</span> </c:if>
												<c:if test="${policyinfo.objectid eq 2 }"> <span>direct traffic(internet)</span> </c:if>
												<c:if test="${policyinfo.objectid eq 3 }"> <span>event</span> </c:if>
												<c:if test="${policyinfo.objectid eq 4 }"> <span>government</span> </c:if>
												<c:if test="${policyinfo.objectid eq 5 }"> <span>brand event</span> </c:if>
												<c:if test="${policyinfo.objectid eq 6 }"> <span>price promotion</span> </c:if>
												<c:if test="${policyinfo.objectid eq 7 }"> <span>product branding/intro</span> </c:if>
												<c:if test="${policyinfo.objectid eq 8 }"> <span>public sercive adcertisment</span> </c:if>
										</span>
										</div>
									</div>
								</c:if>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_CLICK")%>：</label>
										<div class="col-sm-7">
											<span class="span_font">${policyinfo.hitLimit } <%=Config.message.get("CREATE_POLICY_TIMES")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_SHOW")%> ：</label>
										<div class="col-sm-7">
											<span class="span_font">${policyinfo.showNum } <%=Config.message.get("CREATE_POLICY_TIMES")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_SHOW_TIME")%> ：</label>
										<div class="col-sm-7">
											<span class="span_font">${policyinfo.showTime } <%=Config.message.get("SECOND")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_CLICK")%>：</label>
										<div class="col-sm-7">
											<span class="span_font">${policyinfo.hitNum } <%=Config.message.get("CREATE_POLICY_TIMES")%></span>
										</div>
									</div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_DESCRIPTION")%>：</label>
										<div class="col-sm-7">
											<span class="span_font">${policyinfo.description }</span>
										</div>
									</div>
									<div class="divide-20"></div>
									<div class="form-group msg_font form_group row">
										<div class="col-sm-12">
											<div class="box-title title_account">
												<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("POLICY_PUSH_PLAN")%></span>

											</div>
										</div>
									</div>
									<div class="divide-20"></div>
									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_TIME")%>：</label>
										<div class="col-sm-7">
											<span class="span_font">${myEl:subStr(policyinfo.startTime,10)}--${myEl:subStr(policyinfo.endTime,10)}</span>
										</div>
									</div>

									<div class="form-group msg_font form_group row">
										<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PUSH_TIME_PERIOD")%>：</label>
										<div class="form_inputsTime col-md-8 ">
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
											<c:forEach begin="0" end="6" var="weekTemp" step="1">
												<tr>
													<c:if test="${weekTemp+1==1}">
														<td><%=Config.message.get("MONDAY")%></td>
													</c:if>
													<c:if test="${weekTemp+1==2}">
														<td><%=Config.message.get("TUESDAY")%></td>
													</c:if>
													<c:if test="${weekTemp+1==3}">
														<td><%=Config.message.get("WEDNESDAY")%></td>
													</c:if>
													<c:if test="${weekTemp+1==4}">
														<td><%=Config.message.get("THURSDAY")%></td>
													</c:if>
													<c:if test="${weekTemp+1==5}">
														<td><%=Config.message.get("FRIDAY")%></td>
													</c:if>
													<c:if test="${weekTemp+1==6}">
														<td><%=Config.message.get("SATURDAY")%></td>
													</c:if>
													<c:if test="${weekTemp+1==7}">
														<td><%=Config.message.get("SUNDAY")%></td>
													</c:if>
													<c:forEach items="${policyinfoTimeList.LIST }"
														var="timeBean">
														<c:if
															test="${myEl:yunSuan(timeBean.cycleWeek,weekTemp) eq true}">
															<c:set var="setChangeWeek" value="0"></c:set>
															<c:forEach begin="1" end="24" var="hourTemp" step="2">
																<c:set var="setChangeWeek" value="1"></c:set>
																<c:if
																	test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) eq true}">
																	<td><span class="checke_click"></span></td>
																</c:if>
																<c:if
																	test="${myEl:yunSuan(timeBean.cycleHour,hourTemp) ne true}">
																	<td></td>
																</c:if>
															</c:forEach>
														</c:if>
														<c:if test="${setChangeWeek eq 0}">
															<c:forEach begin="1" end="24" var="hourTemp" step="2">
																<td>--</td>
															</c:forEach>
														</c:if>
													</c:forEach>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
									</div>
								</div>
							<c:if test="${isEnglish ne true}">
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label" style="padding-top:6px;"><%=Config.message.get("CREATE_POLICY_PUSH_USER_TYPE")%>：</label>
									<div class="col-sm-7">
										<span class="span_font">
											<c:if test="${policyinfo.roamType eq 0}"><%=Config.message.get("NO_LIMIT")%></c:if>
											<c:if test="${policyinfo.roamType eq 1}"><%=Config.message.get("LOCAL_USERS")%></c:if>
											<c:if test="${policyinfo.roamType eq 2}"><%=Config.message.get("ROAM_USERS")%></c:if>
											<c:if test="${policyinfo.roamType eq 3}"><%=Config.message.get("3A_AUTHENTICATION_USER")%></c:if>
											<c:if test="${policyinfo.roamType eq 4}"><%=Config.message.get("NETWORK_TRAFFIC_USER")%></c:if>
										</span>
									</div>
								</div>
							</c:if>
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label">
										<%=Config.message.get("CREATE_POLICY_PUSH_GROUP")%>：</label>
									<div class="col-sm-7">
										<span class="span_font"> 
										<c:forEach items="${policyinfoGroup.LIST}" var="groupBean">
											${groupBean.groupName},
									   </c:forEach>
										</span>
									</div>
								</div>
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PUSH_IPS")%>：</label>
									<div class="col-sm-7">
										<span class="span_font">
											<c:forEach items="${policyinfoIPS.LIST}" var="ipsBean">${ipsBean.ipsName}, </c:forEach>
										</span>
									</div>
								</div>
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("PUSH_AREA")%>：</label>
									<div class="col-sm-7">
										<span class="span_font">
											<c:forEach items="${policyinfoArea.LIST}" var="areaBean">${areaBean.cityName}, </c:forEach>
										</span>
									</div>
								</div>
							<c:if test="${isEnglish eq true}">
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("BID")%>：</label>
									<div class="col-sm-7">
										<span class="span_font">
											${policyinfo.unitPrice }
										</span>
									</div>
								</div>
							</c:if>
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("WEB_SITE")%>：</label>
									<div class="col-sm-7">
										<span class="span_font"> <c:forEach
												items="${policyinfoUrlDomain.LIST}" var="urlBean">
													              		${urlBean.urlDomain},
													              	</c:forEach>
										</span>
									</div>
								</div>
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label"><%=Config.message.get("KEY_WORDS")%>：</label>
									<div class="col-sm-7">
										<span class="span_font">
											<c:forEach items="${policyinfoKeyWord.LIST}" var="keywordBean">
												${keywordBean.keyWordName},
											</c:forEach>
										</span>
									</div>
								</div>
								<div class="divide-20"></div>
								<div class="form-group msg_font form_group row">
									<div class="col-sm-12">
										<div class="box-title title_account">
											<i class="fa fa-bars"></i> <span class="menu-text"><%=Config.message.get("POLICY_PUSH_MATERIAL")%></span>
										</div>
									</div>
								</div>
								<div class="divide-20"></div>
								<div class="form-group msg_font form_group row">
									<label for="password-input" class="col-md-3 control-label">
										<%=Config.message.get("MATERIAL_PATH")%>：</label>
									<div class="col-sm-7">
										<span class="span_font">${policyinfo.pushURL }</span>
									&nbsp;&nbsp;&nbsp;
									<c:if test="${policyinfo.policyType eq 1}">
										<a class="btn btn-info config" type="button" data-toggle="modal" href="javascript:;" onclick="showPage(this)" rel="<%=Config.message.get("POLICYSERVICE")%>_${policyinfo.pushURL}" title="<%=Config.message.get("CREATE_POLICY_SCAN")%>">
											<%=Config.message.get("CREATE_POLICY_SCAN")%>
										</a>
									</c:if>
									<c:if test="${policyinfo.policyType eq 4}">
										<a class="btn btn-info config" type="button" data-toggle="modal" href="javascript:;" onclick="showPage(this)" rel="<%=Config.message.get("POLICY21CNSERVICE")%>_${policyinfo.pushURL}" title="<%=Config.message.get("CREATE_POLICY_SCAN")%>">
											<%=Config.message.get("CREATE_POLICY_SCAN")%>
										</a>
									</c:if>
									</div>
								</div>
								<div class="form-group msg_font form_group row" style="display:none;">
									<label for="password-input" class="col-md-2 control-label"></label>
									<div class="col-sm-4">
										<span class="span_font">PC</span>
									</div>
									<div class="col-sm-2">
										<span class="span_font">PAD</span>
									</div>
									<div class="col-sm-2">
										<span class="span_font">phone</span>
									</div>
								</div>
								<div class="form-group msg_font form_group  row" style="display:none;">
									<div class="col-sm-4">
										<div class="hover-content animated fadeOut"
											style="display: block; background: url(img/gallery/Mac.png) no-repeat; height: 200px; background-size: 100% 100%;">
										</div>
									</div>
									<div class="col-sm-3">
										<div class="hover-content animated fadeOut"
											style="display: block; background: url(img/gallery/Mac.png) no-repeat; height: 200px; background-size: 100% 100%;">
										</div>
									</div>
									<div class="col-sm-2">
										<div class="hover-content animated fadeOut"
											style="display: block; background: url(img/gallery/Mac.png) no-repeat; height: 200px; background-size: 100% 100%;">
										</div>
									</div>
								</div>
							</div>
							<!--策略详情-->
						</div>
					</div>
					<!-- /BOX -->
				</div>
			</div>
		</div>
		<!-- /CONTENT-->
	</div>
</div>
  <script>
  function showPage(_obj){
		var _url=$(_obj).attr("rel");
		//_url='http://125.94.0.178:8888/policy_show.html?url='+_url;
		window.open(_url,'','height=500,width=430,scrollbars=yes,status =yes');
	}
  </script>