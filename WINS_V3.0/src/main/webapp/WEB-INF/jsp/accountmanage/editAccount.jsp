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
							<!-- BREADCRUMBS -->
							<ul class="breadcrumb">
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("HOME")%></a></li>
								<li><a href="#"><%=Config.message.get("ROLE_SYSTEM_MANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("EDIT_ACCOUNT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("EDIT_ACCOUNT_DETAIL")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<!--修改账户start-->
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
																		<label class="control-label col-md-3 old lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNT_REALNAME")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control"
																				name="realName" value="${result.realName}"
																				onchange="checkName(this)"> <span
																				class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNTSMANAGE_ACCOUNTNAME")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control"
																				name="userName" value="${result.userName}"
																				onchange="checkAccount(this)"> <span
																				class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span class="star"></span>&nbsp;<%=Config.message.get("ACCOUNT_EMAIL")%>：</label>
																		<div class="col-md-4">
																			<input name="email_hide" type="hidden" value="${result.userEmail}">
																			<input type="text" class="form-control" name="email" value="${result.userEmail}" oninput="checkEmail(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																		<div id="change_mail_btn" style="display: none">
																			<button id="verifyEmail" class="btn btn-md btn-success" type="button" ><%=Config.message.get("ACCOUNT_SET_VERIFY")%></button>
																		</div>
																	</div>
																	<div id="change_mail_20" class="divide-20" style="display: none"></div>
																	<div id="change_mail_verify" class="form-group create_font" style="display: none">
																		<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNT_VERY_CODE")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="verifyCode" oninput="checkVerify(this)">
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNTSMANAGE_ACCOUNTCHARACTER")%>：</label>
																		<div class="col-md-4">
																			<select class="form-control" id="roleId">
																				<option value="${result.roleId}">${result.roleName}</option>
																				<c:if test="${!empty result.roles}">
																					<c:forEach var="role" items="${result.roles}">
																						<c:if
																							test="${role.roleStatus == 2 && role.roleId != result.roleId}">
																							<option value="${role.roleId}">${role.roleName}</option>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</select> <span class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNT_BELONG_CITY")%>：</label>
																		<div class="col-md-4">
																			<select class="form-control" id="cityId"
																				onchange="delFirOption()">
																				<option status="${result.provinceId}"
																					value="${result.cityId}" id="firstOption">${result.cityName}</option>
																				<option status="${result.provinceId}" value=" ">${result.provinceName}</option>
																				<c:if test="${!empty result.citys}">
																					<c:forEach var="city" items="${result.citys}">
																						<c:if test="${city.cityId != result.cityId}">
																							<option status="${city.provinceId}"
																								value="${city.cityId}">${city.cityName}</option>
																						</c:if>
																					</c:forEach>
																				</c:if>
																			</select> <span class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNT_PASSWORD")%>：</label>
																		<div class="col-md-4">
																			<input id="userPwd1" type="password"
																				class="form-control" name="userPwd1"
																				value="${result.userPwd}" onchange="checkPwd1(this)">
																			<span class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNT_CONFIRM_PASSWORD")%>：</label>
																		<div class="col-md-4">
																			<input type="password" class="form-control"
																				name="userPwd2" value="${result.userPwd}"
																				onchange="checkPwd2(this)"> <span
																				class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNT_BINDING_PHONE")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control"
																				name="userPhone" value="${result.userPhone}"
																				onchange="checkPhoneNum(this);"> <span
																				class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span
																			class="star">*</span>&nbsp;<%=Config.message.get("ACCOUNT_BINDING_IP")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control"
																				name="loginIp" value="${result.loginIp}"
																				onchange="checkIp(this)"> <span
																				class="error-span"
																				style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																		<input id="hiddenUserId" type="hidden"
																			value="${result.userId}"> <input
																			id="hiddenPwd" type="hidden"
																			value="${result.userPwd}"> <input
																			id="hiddenUserName" type="hidden"
																			value="${result.userName}">
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height"><span
																				class="star"></span>&nbsp;<%=Config.message.get("ACCOUNT_USER_OFFICES")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="userOffice" value="${result.userOffice}" >
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																	<div class="divide-20"></div>
																	<div class="form-group create_font">
																		<label class="control-label col-md-3 old lable_height">
																			<span class="star"></span>&nbsp;<%=Config.message.get("ACCOUNT_ADDRESS")%>：</label>
																		<div class="col-md-4">
																			<input type="text" class="form-control" name="userAddr" value="${result.userAddr}" >
																			<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
																		</div>
																	</div>
																</div>
																<div class="divide-20"></div>
															</div>
														</div>
													</div>
												</form>
												<div class="row">
													<div class="col-md-7">
														<div id="btn_group" class="col-md-offset-6">
															<a class="btn btn-md btn-default"
																href="javascript:void(0);"
																onclick="load_page('accountsManage.html')"><%=Config.message.get("CANCEL")%></a> <a
																class="btn btn-md btn-primary btn_left"
																href="javascript:void(0);"
																onclick="updateAccountInfo();"><%=Config.message.get("SAVE")%></a>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- /CONTENT-->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--创建账户end-->
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
<script type="text/javascript">
	$(function () {
		$("#verifyEmail").bind("click",function(){
			verifyEmail(this);
		});
	});
	var globalEmail = $("input[name='email_hide']").val();  //全局变量： 原来的email
	var timeOut=0; //计时
	var verifyCode; //全局 定义验证码

	function verifyEmail(obj){
		var userEmail = $("input[name='email']").val().trim();
		if(userEmail==""){
			$("input[name='email']").next().html("<%=Config.message.get("ACCOUNT_PLEASE_INPUT_EMAIL")%>");
			$("input[name='email']").next().show();
			return ;
		}else{
			createCode(); //创建验证能码
			$("input[name='email']").next().hide();
		}
		var code = verifyCode;
		if(code==""){
			$("input[name='email']").next().html('<%=Config.message.get("PLEASE_CLICK_VERIFY")%>');
			$("input[name='email']").next().show();
			return ;
		}else{
			$("input[name='email']").next().hide();
		}
		$.ajax({
			url:"account/sendCode",
			method:"post",
			data:{"userEmail":userEmail,"verifyCode":code},
			dataType:"json",
			success:function(data){
				if (data.code==1){ //验证码发送成功

				}else if(data.code==0){ //验证码发送失败

				}else{
					window.confirm("<%=Config.message.get("SYSTEM_EXCEPYTION_TRY_AGAIN")%>");
				}
			},
			error:function(){
				//window.confirm("error");
			}
		});
		timeOut = 60;
		var stop =  window.setInterval("changeBtn(stop);",1000);
		//清空全局验证码
		//verifyCode="";
	}

	function changeBtn(stop){
		timeOut --;
		if(timeOut>0){
			$("#verifyEmail").html("<%=Config.message.get("VARIFY_AGAIN_LATER")%> ("+timeOut+"s)");
			$("#verifyEmail").unbind("click");
		}else if(timeOut==0){
			$("#verifyEmail").bind("click", function () { verifyEmail(this); });
			$("#verifyEmail").html("verify");
			timeOut = 0;
			//clearInterval(changeBtn); //清除定时任务
			clearInterval(stop);
		}
	}

    function delFirOption(){
        $("#cityId option[id='firstOption']").remove();
    }

    function checkName(obj){
        var realName = $(obj).val().trim();
        if (realName.length<2 || realName.length >4 ){
            $(obj).next().html("<%=Config.message.get("PLEASE_INPUT_REAL_NAME")%>");
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
    }
	function checkEmail(obj){
		var email = $(obj).val().trim();
		var oldEmail = $("input[name='email_hide']").val();
		if(email=="" || email==oldEmail){
			$("#change_mail_btn").hide();
			$("#change_mail_20").hide();
			$("#change_mail_verify").hide();
			$(obj).next().hide();
		}else{
			$("#change_mail_btn").show();
			$("#change_mail_20").show();
			$("#change_mail_verify").show();
			var reg =/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
			if(!reg.test(email)){
				$(obj).next().show();
				$(obj).next().html("<%=Config.message.get("YOUR_EMAIL_ERROR_TRY_AGAIN")%>");
			}else{
				$(obj).next().hide();
				$(obj).next().html("");
			}
			//当邮箱变化时，清空全局变量验证码
			verifyCode="";
		}
	}

	//验证：验证码是否正确
	function checkVerify(obj){
		var newCode = $(obj).val().trim();
		if(!(newCode.toLocaleLowerCase() == verifyCode.toLocaleLowerCase())) { //错
			$("input[name='verifyCode']").next().html("<%=Config.message.get("VERIFICATION_CODE_ERROR_TRY_AGAIN")%>");
			$("input[name='verifyCode']").next().show();
		}else{  //对
			$("input[name='verifyCode']").next().hide();
		}
	}

	//生成验证码
	function createCode(){
		verifyCode = "";
		var codeLength = 6;//验证码的长度
		var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符
		for(var i=0;i<codeLength;i++){
			var charIndex = Math.floor(Math.random()*36);
			verifyCode +=selectChar[charIndex];
		}
	}

    function checkAccount (obj){
        var userName = $(obj).val();
        var oldUserName = $("#hiddenUserName").val();
        var reg=/^\w{6,20}$/;
        if(!reg.test(userName)){
            $(obj).next().show();
            $(obj).next().html("<%=Config.message.get("YOUR_ACCOUNT_ERROR")%>")
            return;
        }else{
            $(obj).next().hide();
            $(obj).next().html("");
        }
        if(!(userName == oldUserName)){
            $.ajax({
                url:"account/check",
                method:"post",
                data:{"userName":userName},
                dataType:"json",
                success:function(data){
                    if (data.code==0){
                        $(obj).next().html("<%=Config.message.get("THIS_ACCOUNT_USED")%>");
                        $(obj).next().show();
                    }else{
                        $(obj).next().hide();
                        $(obj).next().html("");
                    }
                }
            });
        }
    }

    function checkPwd1(obj){
        var userId = $("#hiddenUserId").val();
        var pwd1 = $(obj).val().trim();
        var oldPwd = $("#hiddenPwd").val();
        var reg=/^\w{6,20}$/;
        if(!reg.test(pwd1)){
            $(obj).next().html("<%=Config.message.get("YOUR_PASSWORD_ERROR")%>")
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
        $.ajax({
            url:"account/checkUserPwd",
            type:"post",
            data:{"userPwd":pwd1,"userId":userId},
            dataType:"json",
            success:function(data){
                if (data.code==0){
                    $(obj).next().html("<%=Config.message.get("ACCOUNT_NEW_PWD_NOT_OLD_PWD")%>");
                    $("input[name='userPwd2']").val("");
                    $(obj).next().show();
                }else{
                    $("input[name='userPwd2']").val("");
                    $(obj).next().hide();
                    /*if(oldPwd != pwd1){
                        $("input[name='userPwd2']").next().html("请再次输入密码");
                        $("input[name='userPwd2']").next().show();
                    }*/
                }
            }
        });

        /*if(oldPwd != pwd1){
            $("input[name='userPwd2']").next().html("请再次输入密码");
            $("input[name='userPwd2']").next().show();
        }else{
            $(obj).next().hide();
        }*/
    }

    function checkPwd2(obj){
        var pwd1 = $("#userPwd1").val();
        var pwd2 = $(obj).val();
        if(pwd2 != pwd1){
            $(obj).next().html("<%=Config.message.get("YOUR_PASSWORD_NOT_MATCH")%>")
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
    }

    function checkPhoneNum(obj){
        var phoneNum = $(obj).val();
        var reg_phone =/^1\d{10}$/;
        if(!reg_phone.test(phoneNum)){
            $(obj).next().html("<%=Config.message.get("YOUR_PHONE_NUMBER_ERROR")%>");
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
    }

    function checkIp(obj){
        var IP = $(obj).val();
        var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        if(!reg.test(IP)){
            $(obj).next().html("<%=Config.message.get("ROUR_IP_ERROR")%>");
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
    }

    function updateAccountInfo(){
			var userId = $("#hiddenUserId").val();
			var userName = $("input[name='userName']").val().trim();
			var email = $("input[name='email']").val().trim();
			var oldEmail = $("input[name='email_hide']").val();
			var verification = $("input[name='verifyCode']").val().trim();
			var realName = $("input[name='realName']").val().trim();
			var roleId = $("#roleId").val();
			var cityId = $("#cityId").val();
			var provinceId = $("#cityId option:selected").attr("status");
			var userPwd1 = $("input[name='userPwd1']").val().trim();
			var userPwd2 = $("input[name='userPwd2']").val().trim();
			var userPhone = $("input[name='userPhone']").val().trim();
			var loginIp = $("input[name='loginIp']").val().trim();
			var oldPwd = $("#hiddenPwd").val();
			var userOffice = $("input[name='userOffice']").val().trim();
			var userAddr = $("input[name='userAddr']").val().trim();
			if(realName==""){
				$("input[name='realName']").next().html("<%=Config.message.get("PLEASE_INPUT_REAL_NAME")%>");
				$("input[name='realName']").next().show();
				return;
			}
			if(userName==""){
				$("input[name='userName']").next().html("<%=Config.message.get("ACCOUNTSMANAGE_PLEASEATTENTION")%>");
				$("input[name='userName']").next().show();
				return;
			}
			/*if(email==""){
				$("input[name='email']").next().html("Please input your email");
				$("input[name='email']").next().show();
				return ;
			}*/
			if(email!=oldEmail){
				if(email!="" && verification==""){
					$("input[name='verifyCode']").next().html("<%=Config.message.get("PLEASE_INPUT_VERIFCATION_CODE")%>");
					$("input[name='verifyCode']").next().show();
					return ;
				}
			}
			if(roleId==0){
				$("#roleId").next().html("<%=Config.message.get("PLEASE_CHOOSE_ROLE")%>");
				$("#roleId").next().show();
				return;
			}
			if(userPwd1==""){
				$("input[name='userPwd1']").next().html("<%=Config.message.get("PLEASE_INPUT_PASD")%>");
				$("input[name='userPwd1']").next().show();
				return;
			}
			if(userPwd2==""){
				$("input[name='userPwd2']").next().html("<%=Config.message.get("YOUR_PASSWORD_NOT_MATCH")%>");
				$("input[name='userPwd2']").next().show();
				return;
			}
			if(userPhone==""){
				$("input[name='userPhone']").next().html("<%=Config.message.get("PLEASE_INPUT_BINDING_PHONE")%>");
				$("input[name='userPhone']").next().show();
				return;
			}
			if(loginIp==""){
				$("input[name='loginIp']").next().html("<%=Config.message.get("PLEASE_INPUT_BINDING_IP")%>");
				$("input[name='loginIp']").next().show();
				return;
			}
			if($("input[name='realName']").next().is(':visible') || $("input[name='userName']").next().is(':visible') || $("input[name='email']").next().is(':visible') || $("input[name='verifyCode']").next().is(':visible') || $("input[name='userPwd1']").next().is(':visible')
					|| $("input[name='userPwd2']").next().is(':visible') || $("input[name='userPhone']").next().is(':visible') || $("input[name='loginIp']").next().is(':visible')){
				return;
			}
			$.ajax({
				url:"account/update",
				method:"post",
				data:{"userId":userId,"userName":userName,"realName":realName,"userEmail":email,"roleId":roleId,"cityId":cityId,"provinceId":provinceId,"newPwd":userPwd2,"userPhone":userPhone,"loginIp":loginIp,"oldPwd":oldPwd,"userOffice":userOffice,"userAddr":userAddr},
				dataType:"json",
				success:function(data){
					window.confirm(data.desc);
					load_page('accountsManage.html');
				}
			});

        /*var userId = $("#hiddenUserId").val();
        var userName = $("input[name='userName']").val().trim();
		var email = $("input[name='email']").val().trim();
        var realName = $("input[name='realName']").val().trim();
        var roleId = $("#roleId").val();
        var cityId = $("#cityId").val().trim();
        var provinceId = $("#cityId option:selected").attr("status");
        var userPwd1 = $("input[name='userPwd1']").val().trim();
        var userPwd2 = $("input[name='userPwd2']").val().trim();
        var userPhone = $("input[name='userPhone']").val().trim();
        var loginIp = $("input[name='loginIp']").val().trim();
        var oldPwd = $("#hiddenPwd").val();
        if(realName==""){
            $("input[name='realName']").next().html("Real name不能为空");
            $("input[name='realName']").next().show();
            return;
        }
        if(userName==""){
            $("input[name='userName']").next().html("账户名不能为空");
            $("input[name='userName']").next().show();
            return;
        }
		if(email==""){
			$("input[name='email']").next().html("Please input your email");
			$("input[name='email']").next().show();
			return ;
		}
        if(roleId==0){
            $("#roleId").next().html("请选择角色");
            $("#roleId").next().show();
            return;
        }
        if(userPwd1==""){
            $("input[name='userPwd1']").next().html("密码不能为空");
            $("input[name='userPwd1']").next().show();
            return;
        }
        if(userPwd2==""){
            $("input[name='userPwd2']").next().html("您两次输入的密码不一致，请重新输入");
            $("input[name='userPwd2']").next().show();
            return;
        }
        if(userPhone==""){
            $("input[name='userPhone']").next().html("Binding phone number不能为空");
            $("input[name='userPhone']").next().show();
            return;
        }
        if(loginIp==""){
            $("input[name='loginIp']").next().html("绑定IP不能为空");
            $("input[name='loginIp']").next().show();
            return;
        }
        if($("input[name='realName']").next().is(':visible') || $("input[name='userName']").next().is(':visible') || $("input[name='email']").next().is(':visible') || $("input[name='userPwd1']").next().is(':visible')
                || $("input[name='userPwd2']").next().is(':visible') || $("input[name='userPhone']").next().is(':visible') || $("input[name='loginIp']").next().is(':visible')){
            return;
        }
        $.ajax({
            url:"account/update",
            method:"post",
            data:{"userId":userId,"userName":userName,"realName":realName,"userEmail":email,"roleId":roleId,"cityId":cityId,"provinceId":provinceId,"newPwd":userPwd2,"userPhone":userPhone,"loginIp":loginIp,"oldPwd":oldPwd},
            dataType:"json",
            success:function(data){
                window.confirm(data.desc);
                load_page('accountsManage.html');
            }
        });*/
    }


</script>