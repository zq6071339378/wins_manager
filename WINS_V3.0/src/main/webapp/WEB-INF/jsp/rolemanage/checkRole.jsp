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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("HOME")%></a></li>
								<li><a href="#"><%=Config.message.get("ROLE_SYSTEM_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("ROLE_CHECK_ROLE")%></h3>
							</div>
							<div class="description"><%=Config.message.get("ROLE_CHECK_ROLE_DETAIL")%></div>
						</div>
					</div>
				</div>
				<!--创建角色 start-->
				<div class="divide-20"></div>
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
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
																	<div class="divide-20"></div>
																	<div class="form-group msg_font">
																		<label class="control-label col-md-3 lable_height"><%=Config.message.get("ROLE_NAME")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.roleName}</span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group msg_font">
																		<label class="control-label col-md-3 lable_height"><%=Config.message.get("ROLE_DESCRIPTION")%>：</label>
																		<div class="col-md-4">
																			<span class="span_font">${result.roleDesc}</span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group msg_font">
																		<label class="control-label col-md-3 lable_height"><%=Config.message.get("ROLE_PERMISSIONS")%>：</label>
																		<!--设置权限tab-->
																		<div class="col-md-8">
																			<!-- BOX1 -->
																			<div class="box purple tables">
																				<table
																					class="datatable table   table-bordered table-hover data_table">
																					<thead>
																						<tr>
																							<th><input type="checkbox"
																								disabled="disabled"></th>
																							<th><%=Config.message.get("ROLE_TEMPLATE_NAME")%></th>
																							<th><%=Config.message.get("ROLE_TEMPLATE_CHILD")%></th>
																							<th><%=Config.message.get("ROLE_SEARCH_VIEW_EXPORT")%></th>
																							<th><%=Config.message.get("ROLE_ADD")%></th>
																							<th><%=Config.message.get("ROLE_EDIT")%></th>
																							<th><%=Config.message.get("ROLE_DELETE")%></th>
																							<th><%=Config.message.get("ROLE_AUDIT")%></th>
																							<th><%=Config.message.get("ROLE_GLOBAL")%></th>
																						</tr>
																					</thead>
																					<tbody>
																						<tr>
																							<td><input type="checkbox" disabled="disabled"></td>
																							<td><%=Config.message.get("PUSH_VIEW")%></td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==1 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="5"><input type="checkbox"
																								disabled="disabled"></td>
																							<td rowspan="5"><%=Config.message.get("PUSH_MANAGE")%></td>
																							<td><%=Config.message.get("POLICY_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==3 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==3 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==3 && c.update_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==3 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==3 && c.review_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==3 && c.global_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("TEMPLATE_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                        <c:if test="${c.menuId==7 && c.see_permission!=0}" > checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                        <c:if test="${c.menuId==7 && c.add_permission!=0}" > checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==7 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("PAGE_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==8 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==8 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==8 && c.update_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==8 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("GROUP_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==5 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==5 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==5 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("AREA_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==6 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==6 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==6 && c.update_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==6 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="4"><input type="checkbox"
																								disabled="disabled"></td>
																							<td rowspan="4"><%=Config.message.get("OPERATE_MANAGE")%></td>
																							<td><%=Config.message.get("PUSH_REPORT_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==10 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("USER_COMPLAINT_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==11 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("USER_BLACK_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==13 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==13 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==13 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("URL_BLACK_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==14 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==14 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==14 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disablede"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="5"><input type="checkbox"
																								disabled="disabled"></td>
																							<td rowspan="5"><%=Config.message.get("SYSTEM_MANAGE")%></td>
																							<td><%=Config.message.get("ROLE_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==16 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==16 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==16 && c.update_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==16 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("ACCOUNT_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==17 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==17 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==17 && c.update_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==17 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("MONITOR_MANAGE")%></td>
																							<td>
																								<input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==18 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        		</c:forEach>
																								disabled="disabled">
																							</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("LOG_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==20 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("MESSAGE_MANAGE")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==21 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==21 && c.add_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==21 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td rowspan="3"><input type="checkbox"
																								disabled="disabled"></td>
																							<td rowspan="3"><%=Config.message.get("ACCOUNT_SET")%></td>
																							<td><%=Config.message.get("ACCOUNT_INFO")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==23 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==23 && c.update_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<tr>
																							<td><%=Config.message.get("SYS_MESSAGES")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==24 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==24 && c.delete_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																						<%--<tr>
                                                                                        <td>账户安全</td>
                                                                                        <td><input type="checkbox" <c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==24 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach> disabled="disabled"></td>
                                                                                        <td>--</td>
                                                                                        <td><input type="checkbox" <c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==22 && c.update_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach> disabled="disabled"></td>
                                                                                        <td>--</td>
                                                                                        <td>--</td>
                                                                                        <td>--</td>
                                                                                    </tr>--%>
																						<tr>
																							<td><%=Config.message.get("OPERATE_RECORD")%></td>
																							<td><input type="checkbox"
																								<c:forEach items="${result.MenuRelation}" var="c">
                                                                                                   <c:if test="${c.menuId==25 && c.see_permission!=0}" >checked="checked" </c:if>
                                                                                        </c:forEach>
																								disabled="disabled"></td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																							<td>--</td>
																						</tr>
																					</tbody>
																				</table>
																			</div>
																			<!-- BOX1 END -->
																		</div>
																		<!--设置权限tab-->
																	</div>
																</div>
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
										<!-- /CONTENT-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--创建角色 end-->
				<!-- /PAGE HEADER -->
				<%--<div class="footer-tools">
                    <span class="go-top">
                        <i class="fa fa-chevron-up"></i> Top
                    </span>
                </div>--%>
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->