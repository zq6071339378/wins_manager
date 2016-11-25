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
												<li>
													<i class="fa fa-home"></i>
													<a href="index.html"><%=Config.message.get("HOME")%></a>
												</li>
												<li>
													<a href="#"><%=Config.message.get("POLICY_MANAGEMENT")%></a>
												</li>
											</ul>
											<!-- /BREADCRUMBS -->
											<div class="clearfix">
												<h3 class="content-title pull-left"><%=Config.message.get("POLICY_EDIT_POLICY_BASE_INFO")%></h3>
											</div>
											<div class="description">Modify the strategy basic information</div>
										</div>
									</div>
								</div>
								<!-- /PAGE HEADER -->
								<!--添加群组dialog-->
								<!--创建策略-->
								<div class="row">
									<div class="col-md-12">
										<!-- BOX -->
										<div id="formWizard" class="box">
											<div class="box-body form">
												<form class="form-horizontal" novalidate="novalidate" onsubmit="return false;" id="account_create_policy_id_0" >
												<div class="wizard-form">
												   <div class="wizard-content">
													  <div class="divide-20"></div>
													  <div class="tab-content">
													  	<!--创建策略step1-->
													  	 <div id="account" class="tab-pane active">
															<div class="form-group msg_font form_group"> 
													            <label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_POLICY_NAME")%>：</label>
													            <div class="col-sm-4" style="margin-left:-8px;"> 
													             <input type="text" placeholder="<%=Config.message.get("PLEASEINPUTPOLICYNAME")%>" name="policyName" id="policyName" value="${policyinfo.policyName}" required="required"  class="form-control"  onkeyup="this.value=this.value.replace(/^ +| +$/g,'')" >
													            </div>
													            <div class="col-md-2">
													            	<div class="form-policy has-error">
																		<label class="col-md-3"></label> <label
																			class="control-label" for="inputWarning"
																			id="policyNameMsg"></label>
																	</div>
													            </div>
												            </div>
															<div class="form-group msg_font form_group">
												            	<%--<label class="col-md-4"></label><label class="control-label" for="inputWarning" id="policyNameMsg"></label>--%>
													            <label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;&nbsp; <%=Config.message.get("CREATE_POLICY_PRIORITY")%>：</label>
													            <div class="col-md-2">
																	<select class="form-control" name="policyLevel"  required="required" >
																		<option <c:if test="${policyinfo.policyLevel eq 1 }">selected="selected"</c:if> value="1">1</option>
																		<option <c:if test="${policyinfo.policyLevel eq 2 }">selected="selected"</c:if> value="2">2</option>
																		<option <c:if test="${policyinfo.policyLevel eq 3 }">selected="selected"</c:if> value="3">3</option>
																		<option <c:if test="${policyinfo.policyLevel eq 4 }">selected="selected"</c:if> value="4">4</option>
																	</select>
																</div>
												            </div>
															<div class="form-group msg_font form_group"> 
													            <label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_INTERVIAL")%>：</label>
													            <div class="col-md-2"> 
													             <input type="text" name="pushInterval"   id="pushInterval"class="form-control" value="${policyinfo.pushInterval }" required="required"placeholder="<%=Config.message.get("FILL_WITH_DIGITAL")%>"   value="" onkeyup='this.value=this.value.replace(/\D/g,"")' /> 
													            </div> 
													             
													            <div class="col-md-2">
																	<select class="form-control" name="timeType" >
																		<option value="1"><%=Config.message.get("CREATE_POLICY_SECOND")%></option>
																		<option value="2"><%=Config.message.get("CREATE_POLICY_MINUTES")%></option>
																		<option value="3"><%=Config.message.get("CREATE_POLICY_HOUR")%></option>
																	</select>
																</div>
																 <div class="col-md-2">
													            	<div class="form-policy has-error">
																		<label class="col-md-3"></label> <label
																			class="control-label" for="inputWarning"
																			id="policcPushMsg"></label>
																	</div>
													            </div>
													        </div>
													        
														 <c:if test="${isEnglish eq true}">
												            <div class="form-group msg_font form_group"> 
												            	<label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_WAYS_TO_BUY")%>：</label>
														        <div class="col-md-7 row_left">
																	<label class="checkbox-inline">
																      <input type="radio" <c:if test="${policyinfo.buy_type eq 1 }">checked="checked" </c:if> id="optionsRadios3" name="buyType" required="required"  value="1" checked>&nbsp;&nbsp;CPM
																    </label>
																    <label class="checkbox-inline">
																      <input type="radio" <c:if test="${policyinfo.buy_type eq 2 }">checked="checked" </c:if> id="optionsRadios4"  name="buyType" required="required" value="2">&nbsp;&nbsp;CPC
																    </label>
																</div>
												            </div>
														 </c:if>
												               <div class="form-group msg_font form_group"> 
												            	<label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_TERMINAL_TYPE")%>：</label>
														        <div class="col-md-7 row_left">
																	<label class="checkbox-inline">
																      <input type="radio" <c:if test="${policyinfo.terminalType eq 1 }">checked="checked" </c:if> id="optionsRadios3" name="terminalType" required="required"  value="1" checked>&nbsp;&nbsp;phone
																    </label>
																    <label class="checkbox-inline">
																      <input type="radio" <c:if test="${policyinfo.terminalType eq 0 }">checked="checked" </c:if> id="optionsRadios4"  name="terminalType" required="required" value="0">&nbsp;&nbsp;PC
																    </label>
																    <label class="checkbox-inline">
																      <input type="radio"  id="optionsRadios4"  name="terminalType" required="required" value="1">&nbsp;&nbsp;PAD
																    </label>
																</div>
												            </div>
												            <div class="form-group msg_font form_group" style="display:none;"> 
												            	<label for="password-input" class="col-md-3 control-label"></label> 
														        <div class="col-md-7">
																	<label class="checkbox-inline">
																      <input type="checkbox"  name="checkedAll"   required="required" value="0" id="checkedAll">&nbsp;&nbsp;all
																    </label>
																    <label class="checkbox-inline">
																      <input type="checkbox"   name="checkbox_name" required="required" value="1" id="checkbox_name_1">&nbsp;&nbsp;Andriod
																    </label>
																     <label class="checkbox-inline">
																      <input type="checkbox"   name="checkbox_name"  required="required" value="2" id="checkbox_name_1">&nbsp;&nbsp;ios
																    </label>
																    <label class="checkbox-inline">
																      <input type="checkbox"   name="checkbox_name" required="required" value="3" id="checkbox_name_2">&nbsp;&nbsp;Symbian
																    </label>
																    <label class="checkbox-inline">
																      <input type="checkbox"  name="checkbox_name" required="required" value="4" id="checkbox_name_3">&nbsp;&nbsp;Windows Phone 
																    </label>
																    <label class="checkbox-inline">
																      <input type="checkbox"   name="checkbox_name" required="required" value="5" id="checkbox_name_4">&nbsp;&nbsp;Blackberry OS
																    </label>
																</div>
												            </div>
															<div class="form-group msg_font form_group">
																<label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_PUSH_SPEED")%>：</label>
																<div class="col-md-7 row_left">
																	<label class="checkbox-inline">
																		<input type="radio" onclick="changeLimit(this);" required="required" <c:if test="${policyinfo.pushSpeed eq 2 }"> checked="checked" </c:if>  id="optionsSpeed" name="speedType"  value="2">&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_QUICKLY_PUSH")%>
																	</label>
																	<label class="checkbox-inline">
																	 <input type="radio" onclick="changeLimit(this);" required="required"  <c:if test="${policyinfo.pushSpeed eq 1 }"> checked="checked" </c:if>  id="optionsSpeed1" name="speedType"  value="1">&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_UNIFORM_SPEED")%>
																	</label>
																</div>
															</div>
															 <div class="form-group msg_font form_group" style="display: none">
																 <label for="password-input" class="col-md-3 control-label">
																	 <span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_POLICY_DAY_LIMIT")%>：</label>
																 <div class="col-md-2">
																	 <input type="text" name="policyDayLimit" id="policyDayLimit" class="form-control" value="${policyinfo.policyDayLimit}" />
																 </div>
																 <div class="col-md-2">
																	 <span class="span_font"><%=Config.message.get("TIMES_POLICY_DAY")%></span>
																 </div>
															 </div>
															<div class="form-group msg_font form_group">
												            	<label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_USER_SHOW_LIMIT")%>：</label>
														        <div class="col-md-2">
														        	<input type="text" name="pushLimit" id="pushLimit" class="form-control" required="required"  value="${policyinfo.pushLimit }" >
																</div>
																<input type="hidden" id="startTime" value="${policyinfo.Start_T}">
																<input type="hidden" id="endTime" value="${policyinfo.End_T}">
																<div class="col-md-5">
																	<span class="span_font"><%=Config.message.get("TIMES_PERSON_DAY")%></span>&nbsp;&nbsp;&nbsp;&nbsp;
																	<label class="control-label" style="color: #b94a48;" for="inputWarning" id="policyLimitMsg"></label>
																</div>
												            </div>
														 </div>
												            <div class="divide-20" style="display: none"></div>
												            <div class="form-group msg_font form_group" style="display: ">
												            	<label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("PUSH_TYPE")%>：</label>
														        <div class="col-md-7 row_left">
																	<label class="checkbox-inline">
																		<a href="javascript:;"  style="text-decoration:none;"  tabindex="-1"  class="radio_font">
																	      <input type="radio" name="policyType"  <c:if test="${policyinfo.policyType eq 0 }">checked="checked" </c:if> onclick="$('#offline').hide();"  id="optionsRadios3"  required="required" value="1" checked="checked">&nbsp;&nbsp;<%=Config.message.get("PUSH_ONLINE")%>
																	    </a>
																    </label>
																    <label class="checkbox-inline">
																	    <a href="javascript:;" style="text-decoration:none;"   tabindex="-1" class="radio_font">	
																	      <input type="radio" name="policyType"  id ="policyType" <c:if test="${policyinfo.policyType eq 4 }">checked="checked" </c:if> id="optionsRadios4" onclick="$('#offline').show();" required="required"   value="4" >&nbsp;&nbsp;<%=Config.message.get("PUSH_OUTLINE")%>
																	    </a>
																    </label>
																</div>
												            </div>
														 <c:if test="${isEnglish ne true}">
												            <div class="tab-pane fade in active" id="offline" <c:if test="${policyinfo.policyType eq  4 }"> style="display:block;" </c:if>>
													            <div class="form-group msg_font form_group"> 
														            <label for="password-input" class="col-md-3 control-label"><span class="star" >*</span>&nbsp;&nbsp;<%=Config.message.get("PUSH_OUTILNE")%>：</label>
														            <div class="col-sm-4">
														             <input type="text" name="pushOtherUrl"  id="pushOtherUrl" class="form-control" placeholder="www.apple.com"
														                <c:forEach items="${policyinfoWebList.LIST}" var="WebList">value="${WebList.WebUrl}"</c:forEach>   onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/> 
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
														  <div class="form-group msg_font form_group" style="display:none;">
												            	<label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;Policy type：</label> 
														        <div class="col-md-2">
																	<select class="form-control" name="policyShow"  required="required" >
																		<option ><c:if test ="${policyinfo.policyShow eq 1}">selected="selected"</c:if> value="1">banner</option>
																		<option <c:if test ="${policyinfo.policyShow eq 2}">selected="selected"</c:if> value="2">ad</option>
																	</select>
																</div>
												            </div>
												            	
															<div class="form-group msg_font form_group"> 
												            	<label for="password-input" class="col-md-3 control-label"><span class="star">*</span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_THE_WAY_OF_SHOW")%>：</label>
														        <div class="col-md-2">
																	<select class="form-control"  required="required" name="showType" >
																		<option <c:if test ="${policyinfo.showType eq 1}">selected="selected"</c:if> value="1"><%=Config.message.get("CREATE_POLICY_REPLACE")%></option>
																		<option <c:if test ="${policyinfo.showType eq 2}">selected="selected"</c:if> value="2"><%=Config.message.get("CREATE_POLICY_FLOAT")%></option>
																		<option <c:if test ="${policyinfo.showType eq 3}">selected="selected"</c:if> value="3"><%=Config.message.get("CREATE_POLICY_PLACE_ON_BOTH_SIDES")%></option>
																		<option <c:if test ="${policyinfo.showType eq 4}">selected="selected"</c:if> value="4"><%=Config.message.get("HANGING_ON_THE_EDGE")%></option>
																		<option <c:if test ="${policyinfo.showType eq 5}">selected="selected"</c:if> value="5"><%=Config.message.get("PUT_ON_THE_BACK")%></option>
																		<option <c:if test ="${policyinfo.showType eq 6}">selected="selected"</c:if> value="6">toolbar</option>
																	</select>
																</div>
												            </div>
													  <c:if test="${isEnglish eq true}">
														  <div class="form-group msg_font form_group">
															  <label class="col-md-3 control-label" for="password-input"><span class="star"></span>&nbsp;<%=Config.message.get("CREATE_POLICY_INDUSTRY_ATTRIBUTES")%>：</label>
															  <div class="col-md-2" style="margin-top:-15px;">
												            <select id="selid" onchange="selectChild(this);" class="form-control"  name="industry-bear">
																       <option value="${policyinfo.industryId}">${policyinfo.industryName}</option>
															  	 </select>
															  </div>
															  <div id="second_industry" class="col-md-2" style="margin-top:-15px;display: none;">
												            <select id="subselid" onchange="" class="form-control" name=""></select>
															  </div>
															  <%--<div class="col-md-2" style="margin-top:-15px;">
												            <select id="threadselid" onchange="" class="form-control" name="industry-bear"></select>
															  </div>--%>
														  </div>

															<div class="form-group msg_font form_group" >
																<label for="password-input" class="col-md-3 control-label"><span class="star"></span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_AD_TYPE")%>：</label>
																<div class="col-md-2">
																	<select class="form-control" name="adType"  required="required" >
																		<option <c:if test="${policyinfo.objectid eq 9 }">selected="selected"</c:if> value="9"> others </option>
																		<option <c:if test="${policyinfo.objectid eq 1 }"> selected="selected"</c:if> value="1"> corporate branding </option>
																		<option <c:if test="${policyinfo.objectid eq 2 }">selected="selected"</c:if> value="2">direct traffic(internet) </option>
																		<option <c:if test="${policyinfo.objectid eq 3 }"> selected="selected"</c:if> value="3">event</option>
																		<option <c:if test="${policyinfo.objectid eq 4 }">selected="selected"</c:if> value="4">government</option>
																		<option <c:if test="${policyinfo.objectid eq 5 }">selected="selected"</c:if> value="5"> brand event</option>
																		<option <c:if test="${policyinfo.objectid eq 6 }">selected="selected"</c:if> value="6">price promotion</option>
																		<option <c:if test="${policyinfo.objectid eq 7 }">selected="selected"</c:if> value="7"> product branding/intro </option>
																		<option <c:if test="${policyinfo.objectid eq 8 }">selected="selected"</c:if> value="8">public sercive adcertisment</option>
																	</select>
																</div>
															</div>
													  </c:if>
														  <div class="form-group msg_font form_group">
													            <label for="password-input" class="col-md-3 control-label"><span id="totalSpan" class="star"></span>&nbsp;&nbsp;<%=Config.message.get("CREATE_POLICY_POLICY_TOTAL_SHOW")%>：</label>
													            <div class="col-sm-2"> 
													             	<input type="text" name="showLimit"  id ="showLimit" class="form-control" value="${policyinfo.showLimit}" placeholder="<%=Config.message.get("FILL_WITH_DIGITAL")%>"  value=""  onkeyup='this.value=this.value.replace(/\D/g,"")'/>
													            </div> 
													            <div class="col-md-1 row_left">
																	<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
																</div>
																<div class="col-md-5 row_left">
																	<span class="span_fonts"><%=Config.message.get("TOTAL_NUMBER_OF_POLICY_SHOW")%></span>&nbsp;&nbsp;&nbsp;&nbsp;
																	<label class="control-label" style="color: #b94a48;" for="inputWarning" id="policyTotalMsg"></label>
																</div>
												            </div>
												              <div class="form-group msg_font form_group"> 
													            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_SHOW_TIME")%>：</label>
													            <div class="col-sm-2"> 
													             <input type="text" name="showTime"  value="${policyinfo.showTime }" class="form-control" placeholder="<%=Config.message.get("FILL_WITH_DIGITAL")%>"  value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
													            </div> 
													            <div class="col-md-1 row_left">
																	<span class="span_font"><%=Config.message.get("SECOND")%></span>
																</div>
																<div class="col-md-5 row_left">
																	<span class="span_fonts"><%=Config.message.get("CREATE_POLICY_SHOW_TIME")%></span>
																</div>
												            </div>
												            <div class="form-group msg_font form_group"> 
													            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_CLICK")%>：</label>
													            <div class="col-sm-2"> 
													             <input type="text" name="hitLimit"  value="${policyinfo.hitLimit }" class="form-control" placeholder="<%=Config.message.get("FILL_WITH_DIGITAL")%>"    value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/>
													            </div> 
													            <div class="col-md-1 row_left">
																	<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
																</div>
																<div class="col-md-5 row_left">
																	<span class="span_fonts"><%=Config.message.get("TOTAL_NUMBER_OF_POLICY_CLICK")%></span>
																</div>
												            </div>
												            <div class="form-group msg_font form_group"> 
													            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_SHOW")%>：</label>
													            <div class="col-sm-2"> 
													             <input type="text" name="showNum" class="form-control"  value="${policyinfo.showNum }" placeholder="<%=Config.message.get("FILL_WITH_DIGITAL")%>"   value=""  onkeyup='this.value=this.value.replace(/\D/g,"")'/> 
													            </div> 
													            <div class="col-md-1 row_left">
																	<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
																</div>
																<div class="col-md-5 row_left">
																	<span class="span_fonts"><%=Config.message.get("SINGLE_USER_SHOW_NUMBER")%></span>
																</div>
												            </div>
												            <div class="form-group msg_font form_group"> 
													            <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_USER_CLICK")%>：</label>
													            <div class="col-sm-2"> 
													             <input type="text" name="hitNum" class="form-control"  value="${policyinfo.hitNum }" placeholder="<%=Config.message.get("FILL_WITH_DIGITAL")%>"  value="" onkeyup='this.value=this.value.replace(/\D/g,"")'/> 
													            </div> 
													            <div class="col-md-1 row_left">
																	<span class="span_font"><%=Config.message.get("CREATE_POLICY_TIMES")%></span>
																</div>
																<div class="col-md-5 row_left">
																	<span class="span_fonts"><%=Config.message.get("SINGLE_USER_CLICK_TIMES")%></span>
																</div>
												            </div>
												            <div class="form-group msg_font form_group">
											                    <label for="password-input" class="col-md-3 control-label"><%=Config.message.get("CREATE_POLICY_POLICY_DESCRIPTION")%>：</label>
											                    <div class="col-md-4">
											                        <textarea placeholder="" class="form-control" name="Description" cols="5"  rows="3" required="required">
											                        ${policyinfo.description }
											                        </textarea>
														</div>
													</div>
												</div>
												<!--编辑策略step1 END-->
											</div>
										</div>
										<div class="divide-20"></div>
										<div class="row">
											<div class="col-md-10">
												<div class="col-md-offset-4" id="btn_group">
													<a
														href="#policy/toPolicyEditeInfo?policyId=${policyinfo.policyId}"
														onclick="load_page('policy/toPolicyEditeInfo?policyId=${policyinfo.policyId}');"
														class="btn btn-md btn-default"><%=Config.message.get("CREATE_POLICY_CANCEL")%></a> <a
														href="javascript:;" onclick="editeOne();"
														class="btn btn-md btn-primary btn_left"> <%=Config.message.get("SAVE")%> </a>
												</div>
											</div>
										</div>
									<input type="hidden" id="policyId_edite_id" name="policyId"
										value="${policyinfo.policyId}" />
								</form>
								</div>
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
<script type="text/javascript">
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
						/*var num = 0;
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
						}*/
					}
				}
			}
		});
	});

	function changeLimit(obj){
		//$("#pushLimit").val("");
		var _value = $(obj).val();
		/*$("#optionsSpeed").attr("name","");
		 $("#optionsSpeed1").attr("name","");*/
		if(_value==2){ //加速
			$("#totalSpan").html("");
			//$("#optionsSpeed").attr("name","speedType");
		}else{ //匀速
			//$("#optionsSpeed1").attr("name","speedType");
			$("#totalSpan").html("*");
			//$("#pushLimit").val("50");
		}
	}

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

	function editeOne(){
				var target=$('#account_create_policy_id_1');
				var valateflag=true;
				if($("input[name='policyType']:checked").val()==4){
					var pushOtherUrl = $("input[name='pushOtherUrl']").val();
				}
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

				var policyName=$("#policyName").val();
				if(policyName.trim().length==0){
					$("#policyNameMsg").show();
					$("#policyNameMsg").html("<%=Config.message.get("PLEASE_INPUT_POLICY_NAME")%>");
					return false;
				}else{
					$("#policyNameMsg").hide();
				}
				if(policyName.trim().length>20){
					$("#policyNameMsg").show();
					$("#policyNameMsg").html("<%=Config.message.get("NO_MORE_THAN_WORDS")%>");
					return false;
				}else{
					$("#policyNameMsg").hide();
				}
				var pushInterval=$("#pushInterval").val();
				if(pushInterval.trim().length==0){
					$("#policcPushMsg").show();
					$("#policcPushMsg").html("<%=Config.message.get("PLEASE_SET_THE_PUSH_INTERVAL")%>");
					return false;
				}else{
					$("#policcPushMsg").hide();
				}

				if($("input[name='policyType']:checked").val()==4){
					var pushOtherUrl=$("#pushOtherUrl").val();
					if(pushOtherUrl.trim().length==0){
						$("#policyUrlMsg").show();
						$("#policyUrlMsg").html("<%=Config.message.get("PLEASE_INPUT_WEB_SITE")%>");
						return false;
					}else{
						$("#policyUrlMsg").hide();
					}
					var pushOtherUrl=$("#pushOtherUrl").val();
					var e=/^(?!http)/;
					if(e.test(pushOtherUrl) ){
						$("#policyUrl1Msg").show();
						$("#policyUrl1Msg").html("Start with http");
						return false;
					}
					else {
						$("#policyUrl1Msg").hide();
					}
				}
				var pushLimit = $("#pushLimit").val().trim();
				if(pushLimit=="" || Number(pushLimit)<1){
					$("#policyLimitMsg").html("<%=Config.message.get("PLEASE_SET_USER_SHOW_LIMIT")%>");
					return ;
				}else{
					$("#policyLimitMsg").html("");
				}
				//总展现次数
				var showLimit=$("#showLimit").val();
				var radio_2 = document.getElementById("optionsSpeed1");
				if((radio_2.checked == true) && (showLimit.trim().length==0 || Number(showLimit)<1)){
					$("#policyTotalMsg").html("<%=Config.message.get("PLEASE_INPUT_THE_TOTAL_SHOW_LIMIT")%>");
					return false;
				}else{
					$("#policyTotalMsg").html("");
				}

				if($("input[name='speedType']:checked").val()==1){
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					var policyDayLimit = (Number(showLimit)/(Number(endTime-startTime+1))).toFixed(0);
					//alert("policyDayLimit: "+policyDayLimit);
					$("#policyDayLimit").val(policyDayLimit);
				}else{
					$("#policyDayLimit").val("0");
				}

				target.find("select[required='required']").each(function(){
					if($(this).val()==""||$(this).val()==0){valateflag=false;$(this).focus();return false;}
				});
				if(valateflag){
					var _policyId=$("#policyId_edite_id").val();
					var params=[];
					var target1=$('#account_create_policy_id_0');
					params[0]=target1.serialize();
					$.ajax({
						url:'policy/editePolicyOne',
						type:"post",
						data:params.join("&"),
						success:function(data){
							if(data.result>0){
								alert("Succeed");
								load_page('policy/toPolicyEditeInfo?policyId='+_policyId);
							}else{
							/* 	$("#bee").show();
								$("#bee").html("名称长度不能超过20字符"); */
							alert('error！');
								/*return false; */
							}

						}
					});

				}
			}
			</script>