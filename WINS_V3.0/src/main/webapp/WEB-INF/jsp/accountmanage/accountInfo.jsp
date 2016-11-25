<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("POLICY_MANAGEMENT_HOME")%></a></li>
								<li><a href="#"><%=Config.message.get("ACCOUNT_SET_ACCOUNTMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"></h3>
							</div>
							<div class="description"><%=Config.message.get("ACCOUNT_SET_ACCOUNTDETIAL")%></div>
						</div>
					</div>
				</div>
				<!---->
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box">
							<div class="divide-40"></div>
							<div class="box-body">
								<div class="tabbable header-tabs user-profile">
									<div class="tab-content">
										<!-- OVERVIEW -->
										<div id="pro_overview" class="tab-pane fade in active">
											<div class="row">
												<!-- PROFILE PIC -->
												<div class="col-md-3">
													<div class="list-group">
														<li class="list-group-item zero-padding"><img
															src="img/profile/avatar.jpg" class="img-responsive"
															alt=""></li>
														<div class="list-group-item profile-details">
															<h2>${result.realName}</h2>
															<%--<p>上次登录时间 :&nbsp;2015-02-12&nbsp; 18:20</p>--%>
															<p>
																<%=Config.message.get("ACCOUNT_SET_THELATLOGINTIME")%> :&nbsp;
																<fmt:formatDate value="${result.loginTime}"
																	pattern="yyyy-MM-dd  HH:mm"></fmt:formatDate>
															</p>
														</div>
													</div>
												</div>
												<!-- PROFILE DETAILS -->
												<div class="col-md-8" id="account_info_div">
													<div class="row">
														<div class="col-md-11">
															<!-- BOX -->
															<div class="box inverse">
																<div class="box-title title_account">
																	<i class="fa fa-bars"></i> <span><%=Config.message.get("ACCOUNT_SET_MESSAGE")%></span>
																	<c:forEach var="rv"
																		items="${MenuRelation.MenuRelation}">
																		<c:if
																			test="${rv.menuId==23 && rv.update_permission!=0}">
																			<div class="tools">
																				<a href="javascript:;" class="reload editor_account"
																					onclick="$('#account_info_div').hide();$('#account_edit_div').show();">
																					<i class="fa fa-edit"></i>
																				</a>
																			</div>
																		</c:if>
																	</c:forEach>
																</div>
																<div class="divide-20"></div>
																<div class="box-body big sparkline-stats">
																	<table class="table table-hover">
																		<tbody id="my_td" class="info_editor">
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_ACCOUNTNAME")%>：</td>
																				<td>${result.userName}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_REALNAME")%>：</td>
																				<td>${result.realName}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_EMAIL")%>：</td>
																				<td>${result.userEmail}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_USEROFFICE")%>：</td>
																				<td>${result.userOffice}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_USERADDERSS")%>：</td>
																				<td>${result.userAddr}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_BINDINGPHONENUMBER")%>：</td>
																				<td>${result.userPhone}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_ACCOUNTCHARACTER")%>：</td>
																				<td>${result.roleName}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_ACCOUNT_REGION")%>：</td>
																				<td>${result.cityName}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_BINDINGIP")%>：</td>
																				<td>${result.loginIp}</td>
																			</tr>
																		</tbody>
																	</table>
																</div>
															</div>
															<!-- /BOX -->
															<!-- /SAMPLE -->
														</div>
													</div>
												</div>
												<div class="col-md-9" id="account_edit_div"
													style="display: none">
													<div class="row">
														<div class="col-md-12">
															<!-- BOX -->
															<div class="box inverse">
																<div class="box-title title_account">
																	<i class="fa fa-bars"></i> <span><%=Config.message.get("ACCOUNT_SET_MESSAGE")%></span>
																	<%--<div class="tools">
                                                                        <a href="javascript:;" class="reload editor_account"  id="editor_click" onclick="toEditor()"> <i class="fa fa-edit"></i> </a>
                                                                    </div>--%>
																</div>
																<div class="divide-20"></div>
																<div class="box-body big sparkline-stats">
																	<input type="hidden" id="user_ID"
																		value="${result.userId}">
																	<table class="table table-hover">
																		<tbody id="my_td" class="info_editor">
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_ACCOUNTNAME")%>：</td>
																				<td>
																					<%--<input type='text' class="col-md-3" id="account_name" name="userName" value="${result.userName}" onchange="checkedAccount(this)" />
                                                                                <label class="control-label" style="color:red;font: 8;margin-left: 0px" ></label>--%>
																					${result.userName}
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_REALNAME")%>：</td>
																				<td><input type='text' class="col-md-3"
																					id="real_name" name="realName"
																					value="${result.realName}"
																					onchange="checkedRealName(this)" /> <label
																					class="control-label"
																					style="color: red; font: 8; margin-left: 0px"></label>
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_EMAIL")%>：</td>
																				<td>
																					<input name="email_hide" type="hidden" value="${result.userEmail}">
																					<input type='text' class="col-md-3" id="email" name="email" value="${result.userEmail}" oninput="checkEmail(this)" />
																					<label class="control-label" style="color: red; font: 8; margin-left: 0px"></label>
																					<button id="verifyEmail" style="padding: 0px 12px;display: none" class="btn btn-md btn-success" type="button" ><%=Config.message.get("ACCOUNT_SET_VERIFY")%></button>
																				</td>
																			</tr>
																			<tr id="change_mail_verify" style="display: none">
																				<td><%=Config.message.get("ACCOUNT_SET_ERRIFICATIONCODE")%>：</td>
																				<td><input type='text' class="col-md-3" id="" name="verifyCode" value="" oninput="checkVerify(this)" />
																					<label class="control-label" style="color: red; font: 8; margin-left: 0px"></label>
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_USEROFFICE")%>：</td>
																				<td>
																					<input type='text' class="col-md-3" id="userOffice" name="userOffice" value="${result.userOffice}" />
																					<label class="control-label" style="color: red; font: 8; margin-left: 0px"></label>
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_USERADDERSS")%>：</td>
																				<td>
																					<input type='text' class="col-md-3" id="userAddr" name="userAddr" value="${result.userAddr}" />
																					<label class="control-label" style="color: red; font: 8; margin-left: 0px"></label>
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_BINDINGPHONENUMBER")%>：</td>
																				<td>
																					<input type='text' class="col-md-3" id="user_phone" name="userPhone" value="${result.userPhone}" onchange="checkPhoneNum(this)" />
																					<label class="control-label" style="color: red; font: 8; margin-left: 0px"></label>
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("LOGIN_PASSWORD")%>：</td>
																				<td><input type='password' class="col-md-3"
																					id="userPwd1" name="userPwd1"
																					value="${result.userPwd}"
																					onchange="checkPwd1(this)" /> <label
																					class="control-label"
																					style="color: red; font: 8; margin-left: 0px"></label>
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_CONFIRMTHEPASSWORD")%>：</td>
																				<td><input type='password' class="col-md-3"
																					id="userPwd2" name="userPwd2"
																					value="${result.userPwd}"
																					onchange="checkPwd2(this)" /> <label
																					class="control-label"
																					style="color: red; font: 8; margin-left: 0px"></label>
																				</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_ACCOUNTCHARACTER")%>：</td>
																				<td>${result.roleName}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_ACCOUNT_REGION")%>：</td>
																				<td>${result.cityName}</td>
																			</tr>
																			<tr>
																				<td><%=Config.message.get("ACCOUNT_SET_BINDINGIP")%>：</td>
																				<td>${result.loginIp}</td>
																			</tr>
																		</tbody>
																	</table>
																	<input type="hidden" id="hide_account"
																		value="${result.userName}"> <input
																		id="hiddenUserId" type="hidden"
																		value="${result.userId}"> <input
																		id="hiddenPwd" type="hidden" value="${result.userPwd}">
																	<label id="tab_editor" style="float: right;">
																		<button type="button"
																			class="btn btn-default btn-md active"
																			onclick="$('#account_info_div').show();$('#account_edit_div').hide();">
																			<%=Config.message.get("ACCOUNT_SET_CANCEL")%></button>
																		<button type="button" class="btn btn-primary btn-md"
																			onclick="editAccountInfo()"><%=Config.message.get("ACCOUNT_SET_SAVE")%></button>
																	</label>
																</div>
															</div>
															<!-- /BOX -->
															<!-- /SAMPLE -->
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
				<!--结束-->
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function () {
		$("#verifyEmail").bind("click",function(){
			verifyEmail(this);
		});
	});
	var timeOut=0; //计时
	var verifyCode; //全局 定义验证码

    function checkedRealName(obj){
        var realName = $(obj).val().trim();
        if (realName.length<2 || realName.length >4 ){
            $(obj).next().html("<%=Config.message.get("ACCOUNT_SET_PLEASEINPUTTHEREALNAME")%>");
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
    }

	function changeBtn(stop){
		timeOut --;
		if(timeOut>0){
			$("#verifyEmail").html("verify again later ("+timeOut+"s)");
			$("#verifyEmail").unbind("click");
		}else if(timeOut==0){
			$("#verifyEmail").bind("click", function () { verifyEmail(this); });
			$("#verifyEmail").html("verify");
			timeOut = 0;
			//clearInterval(changeBtn); //清除定时任务
			clearInterval(stop);
		}
	}

	function verifyEmail(obj){
		var userEmail = $("input[name='email']").val().trim();
		if(userEmail==""){
			$("input[name='email']").next().html("<%=Config.message.get("ACCOUNT_SET_PLEASEINPUTYOUREMAIL")%>");
			$("input[name='email']").next().show();
			return ;
		}else{
			createCode(); //创建验证能码
			$("input[name='email']").next().hide();
		}
		var code = verifyCode;
		if(code==""){
			$("input[name='email']").next().html('<%=Config.message.get("ACCOUNT_SET_PLEASECLICK")%>');
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
					window.confirm("<%=Config.message.get("ACCOUNT_SET_SYSTEMEXECEPTIONS")%>!");
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

	function checkEmail(obj){
		var email = $(obj).val().trim();
		var oldEmail = $("input[name='email_hide']").val();
		if(email=="" || email==oldEmail){
			$(obj).next().hide();
			$("#verifyEmail").hide();
			$("#change_mail_verify").hide();
		}else{
			$("#verifyEmail").show();
			$("#change_mail_verify").show();
			var reg =/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
			if(!reg.test(email)){
				$(obj).next().html("<%=Config.message.get("ACCOUNT_SET_YOUEMAILISNOTCORRECT")%>");
				$(obj).next().show();
				$("#verifyEmail").hide();
				return;
			}else{
				$(obj).next().html("");
				$(obj).next().hide();
			}
			//当邮箱变化时，清空全局变量验证码
			verifyCode="";
		}
	}

	//验证：验证码是否正确
	function checkVerify(obj){
		var newCode = $(obj).val().trim();
		if(!(newCode.toLocaleLowerCase() == verifyCode.toLocaleLowerCase())) { //错
			$("input[name='verifyCode']").next().html("<%=Config.message.get("ACCOUNT_SET_YOURVERIFICATIONCODEISNOTCORRECTE")%>");
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

    function checkPhoneNum(obj){
        var phoneNum = $(obj).val();
        var reg_phone =/^1\d{10}$/;
        if(!reg_phone.test(phoneNum)){
            $(obj).next().html("<%=Config.message.get("ACCOUNT_SET_PLEASEINPUTSTANDARDPHONENUMBER")%>");
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
    }

    function checkPwd1(obj) {
        var userId = $("#hiddenUserId").val();
        var pwd1 = $(obj).val().trim();
        var oldPwd = $("#hiddenPwd").val();
        var reg = /^\w{6,20}$/;
        if (!reg.test(pwd1)) {
            $(obj).next().html("<%=Config.message.get("ACCOUNT_SET_ATTENTIONPASSWORD")%>")
            $(obj).next().show();
            return;
        } else {
            $(obj).next().hide();
        }
        $.ajax({
            url: "account/checkUserPwd",
            type: "post",
            data: {"userPwd": pwd1, "userId": userId},
            dataType: "json",
            success: function (data) {
                if (data.code == 0) {
                    $(obj).next().html("<%=Config.message.get("ACCOUNT_SET_ATTENTIONNEWPASSWORDCANNOT")%>");
                    $("input[name='userPwd2']").val("");
                    $(obj).next().show();
                } else {
                    $("input[name='userPwd2']").val("");
                    $(obj).next().hide();
                    /*if(oldPwd != pwd1){
                     $("input[name='userPwd2']").next().html("请再次输入密码");
                     $("input[name='userPwd2']").next().show();
                     }*/
                }
            }
        });
    }
    function checkPwd2(obj){
        var pwd1 = $("#userPwd1").val();
        var pwd2 = $(obj).val();
        if(pwd2 != pwd1){
            $(obj).next().html("<%=Config.message.get("ACCOUNT_SET_PASSWORDISNOTTHESAME")%>")
            $(obj).next().show();
            return;
        }else{
            $(obj).next().hide();
        }
    }

    function editAccountInfo(){
		var userId = $("#user_ID").val();
		/*var userName = $("input[name='userName']").val().trim();*/
		var realName = $("input[name='realName']").val().trim();
		var userEmail = $("input[name='email']").val().trim();
		var oldEmail = $("input[name='email_hide']").val();
		var verification = $("input[name='verifyCode']").val().trim();
		var userPwd1 = $("input[name='userPwd1']").val().trim();
		var userPwd2 = $("input[name='userPwd2']").val().trim();
		var userPhone = $("input[name='userPhone']").val().trim();
		var oldPwd = $("#hiddenPwd").val();
		var userOffice = $("input[name='userOffice']").val().trim();
		var userAddr = $("input[name='userAddr']").val().trim();
		if(realName==""){
			$("input[name='realName']").next().html("<%=Config.message.get("ACCOUNT_SET_INPUTTHEREALNAME")%>");
			$("input[name='realName']").next().show();
			return;
		}
		if(userEmail!=oldEmail){
			if(userEmail!="" && verification==""){
				$("input[name='verifyCode']").next().html("<%=Config.message.get("ACCOUNT_SET_PLEASEIPUTVERIFICATION")%>");
				$("input[name='verifyCode']").next().show();
				return ;
			}
		}
		if(userPwd1==""){
			$("input[name='userPwd1']").next().html("<%=Config.message.get("ACCOUNT_SET_PLEASEINPUTPASSWORD")%>");
			$("input[name='userPwd1']").next().show();
			return;
		}
		if(userPwd2==""){
			$("input[name='userPwd2']").next().html("<%=Config.message.get("ACCOUNT_SET_PASSWORDISNOTTHESAME")%>");
			$("input[name='userPwd2']").next().show();
			return;
		}else{
			if(userPwd2!=userPwd1){
				$("input[name='userPwd2']").next().html("<%=Config.message.get("ACCOUNT_SET_PASSWORDISNOTTHESAME")%>");
				$("input[name='userPwd2']").next().show();
				return;
			}
		}
		if(userPhone==""){
			$("input[name='userPhone']").next().html("<%=Config.message.get("ACCOUNT_SET_PLEASEINPUTBINDINGPHONE")%>");
			$("input[name='userPhone']").next().show();
			return;
		}
		if($("input[name='realName']").next().is(':visible') || $("input[name='email']").next().is(':visible') || $("input[name='verifyCode']").next().is(':visible') || $("input[name='userPwd1']").next().is(':visible')
				|| $("input[name='userPwd2']").next().is(':visible') || $("input[name='userPhone']").next().is(':visible')){
			return;
		}
		$.ajax({
			url:"account/edit",
			method:"post",
			data:{"userId":userId,"realName":realName,"userEmail":userEmail,"userPwd":userPwd2,"userPhone":userPhone,"oldPwd":oldPwd,"userOffice":userOffice,"userAddr":userAddr},
			dataType:"json",
			success:function(data){
				load_page('accountInfo.html');
				window.confirm(data.desc);
				logout();
			}
		});
    }
</script>
<!--main_container-->