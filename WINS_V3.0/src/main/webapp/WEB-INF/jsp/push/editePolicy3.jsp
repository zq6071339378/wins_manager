<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
								<h3 class="content-title pull-left"><%=Config.message.get("EDIT_PUSH_MATERIAL")%></h3>
							</div>
							<div class="description">Simple Tables with exclusive UI
								experience</div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<!--创建策略-->
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
						<div id="formWizard" class="box">
							<div class="box-body form">
									<div class="wizard-form">
										<div class="wizard-content">
											<div class="divide-20"></div>
											<div class="tab-scontent">
												<!--预览dialog-->
												<div class="modal fade" id="box-pageImg" tabindex="-1"
													role="dialog" aria-labelledby="myModalLabel"
													aria-hidden="true">
													<div class="modal-dialog modal-preview">
														<div class="modal-content">
															<div class="modal-header">
																<button aria-hidden="true" data-dismiss="modal"
																	class="close" type="button">×</button>
																<div class="col-md-4">
																	<div class="form-group msg_font form_group">
																		<label for="password-input"
																			class="col-md-8 control-label row_left"><%=Config.message.get("CHECK_IN_EQUIPMENT")%></label>
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
												<div id="confirm" class="tab-pane active" style="">
												<form class="form-horizontal" onsubmit="return false;"
													id="account_create_policy_id_2" novalidate="novalidate">
													<div class="form-group msg_font form_group">
														<label for="password-input" class="col-md-2 control-label"></label>
														<div class="col-md-2 row_left">
															<label class="checkbox-inline"> <input
																type="radio" value="option2"
																onclick="$('#existedPage').show();$('#route').hide();"
																value="1" checked="checked" name="optionsRadiosinline">&nbsp;&nbsp;<%=Config.message.get("EXISTING_PAGES")%>
															</label>
														</div>
														<div class="col-md-3 row_left">
															<label class="checkbox-inline"> <input
																type="radio" value="option2"
																onclick="$('#existedPage').hide();$('#route').show();"
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
																			<div class="col-md-6 row_left" style="height:300px; border:1px #cdd2d2 solid; overflow:hidden; overflow-y:scroll; overflow-x:none;">
																					<c:forEach var="page" items="${result.materials}"
																					varStatus="status">
																					<div class="col-md-6 row_left">
																						<label class="checkbox-inline"> 
																						<input  type="radio" name="pagename" value="${page.pageURL}">&nbsp;&nbsp;
																						${page.pageName} ${page.terminalType==1?"phone":page.terminalType==2?"PC":"PAD"}
																						<%-- <td>${page.pageURL}</td> --%>
																						</label>
																					</div>
																					</c:forEach>
																				</div>
																					
																				</div>
																			</div>
																		</div>
																		<!-- /BOX -->
																	</div>
																</div>
															</div>
														
														<!--已有页面-->
														<!--外部路径-->
														<div class="tab-pane fade in active" id="route" style="display: none;">
															<div class="form-group msg_font form_group">
																<label for="password-input"
																	class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_PAGE_PATH")%>：</label>
																<div class="col-md-3">
																	<input type="text" name="pushUrl" class="form-control" id="pushUrl"
																		value="${policyinfo.pushURL }"
																		placeholder="http://www.baidu.com">
																</div>
															</div>
															<div class="form-group msg_font form_group"
																style="display: none;">
																<label for="password-input"
																	class="col-md-2 control-label"></label>
																<div class="col-md-6 col_left row_left">
																	<div id="" class="row tab-pane fade in active">
																		<!-- BOX -->
																		<div class="box">
																			<div class="form-group msg_font form_group">
																				<div class="col-md-12 row_left">
																					<a class="thumbnail" href="#"> <img alt=""
																						src="img/gallery/Mac.png">
																					</a>
																				</div>
																			</div>
																		</div>
																		<!-- /BOX -->
																	</div>
																</div>
															</div>
														</div>
														<input type="hidden" id="policyId_edite_id" name="policyId"
											value="${policyinfo.policyId}" />
														<!--外部路径-->
														</div>
														</form>
														<!-- 创建页面 -->
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
																	   	<a href="#home" data-toggle="tab" onclick="createNewPage();"><%=Config.message.get("PAGE_CREATE_TEMPLATE")%></a>
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
												</div>
												<!--创建策略step3 END-->
											</div>
										</div>
										<div class="row" id="nextPage">
											<div class="col-md-10">
												<div class="col-md-offset-4" id="btn_group">
													<a
														href="#policy/toPolicyEditeInfo?policyId=${policyinfo.policyId}"
														onclick="load_page('policy/toPolicyEditeInfo?policyId=${policyinfo.policyId}');"
														class="btn btn-md btn-default"> <%=Config.message.get("CANCEL")%></a> <a
														href="javascript:;" onclick="editeThree();"
														class="btn btn-md btn-primary btn_left"><%=Config.message.get("SAVE")%> </a>
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
										<input type="hidden" id="terminalTypeId" name="terminalType"
											value="${policyinfo.terminalType}" />
									</div>
							</div>
						</div>
						<!-- /BOX -->
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
				</div>
				<!--创建策略-->
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<script>
$(function(){
$("input[name=pagename]").click(function(){
	var pagename=$('input:radio[name="pagename"]:checked').val();
	$("#pushUrl").val(pagename);
	
	 });
});
</script>
<script>
				function editeThree(){
					var target=$('#account_create_policy_id_2');
					var valateflag=true;
					if(valateflag){
						var _policyId=$("#policyId_edite_id").val();
						var params=[];
						var target1=$('#account_create_policy_id_2');
						params[0]=target1.serialize();
						$.ajax({
							url:'policy/editePolicyThree',
							type:"post",
							data:params.join("&"),
							success:function(data){
								alert("Succeed!");
								load_page('policy/toPolicyEditeInfo?policyId='+_policyId);
							}
						});
						
					}
				}
				function createNewPage(){
					var terminalTypeId=$('#terminalTypeId').val();
					if(terminalTypeId==0){
						$("#phoneLabel").addClass('active');
						var tType=5;
						$.ajax({
							url:'material/page/createNewPage',
							data:"tType="+tType,
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
					}else if(terminalTypeId==1){
						$("#pcLabel").addClass('active');
						var tType=1;
						$.ajax({
							url:'material/page/createNewPage',
							data:"tType="+tType,
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