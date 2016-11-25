<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config"%>
<%
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 request.setAttribute("basePath",basePath);
 
 %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title><%=Config.message.get("LOGIN_LOGIN")%></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- STYLESHEETS -->
<!--[if lt IE 9]><script src="js/flot/excanvas.min.js"></script><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script><![endif]-->
<link rel="stylesheet" type="text/css" href="css/cloud-admin.css">

<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- DATE RANGE PICKER -->
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- UNIFORM -->
<link rel="stylesheet" type="text/css"
	href="js/uniform/css/uniform.default.min.css" />
<!-- ANIMATE -->
<link rel="stylesheet" type="text/css"
	href="css/animatecss/animate.min.css" />
<!-- FONTS -->
<!-- <link href='http://fonts.useso.com/css?family=Open+Sans:300,400,600,700' rel='stylesheet' type='text/css'> -->
<script src="js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	
	
		$(function(){
			var phone;
			$("#loginbtn").click(function(){
				if(validateLogin()){
					$.ajax({
						url:"${basePath}user/login",
						type:"post",
						data:{
							username:$("input[name='username']").val(),
							password:$("input[name='password']").val(),
							msgcode:$("input[name='msgcode']").val(),
							logoTemp:$("input[name='logoTemp']").val(),
							remerberPwd:$("#remerberPwd").is(":checked")
						},
						success:function(data){
							if(data.code==1){
									window.location.href="${basePath}index.html";
							}
							else if(data.code==0){
								$("#userMsg").html("<%=Config.message.get("LOGIN_IDOR_PASSWORD_ERROR")%>");
								return false;
							}
							else if(data.code==-1){
								$("#userMsg").html("<%=Config.message.get("VERIFICATION_CODE_ERROR2")%>");
								return false;
							}
							else if(data.code==2){
								$("#phone_login").css("display","block");
								$("#ipMsg").css("display","block");
								phone=data.result
								return false;
							}
							else if(data.code==3){
								$("#userMsg").html("<%=Config.message.get("THE_USER_NAME_OR_PASSWORD_ERROR")%>");
								$("#exampleInputPassword1").val("");
								$("#exampleInputPassword1").focus();
								return false;
							}
						}
					});
				}
				else{
				}
			});
			
			var wait=60;  
			  function time(o) {
			          if (wait == 0) {  
			              o.removeAttribute("disabled");            
			              o.innerHTML ="<%=Config.message.get("GET_VERIFICATION_CODE")%>";  
			              wait = 60;  
			          } else {  
			              o.setAttribute("disabled", true);  
			              o.innerHTML ="Resend(" + wait + ")";  
			              wait--;  
			              setTimeout(function() {  
			                  time(o)  
			              },  
			              1000)  
			          }  
			      } ;
			      
			      $("#genDynMsg").click(function(){
						time(this);	
						$.ajax({
							url:"${basePath}user/genDynMsg",
							data:{phone:phone},
							type:"post",
							success:function(data){
								$("#msg").html("<font color='grey'><%=Config.message.get("VERIFICATION_CODE_HAS_BEEN_SENT")%></font>")
							}
						});
					});  
			
			$(document).keydown(function(event){
				if(event.keyCode==13){
					if(validateLogin()){
						$.ajax({
							url:"${basePath}user/login",
							type:"post",
							data:{
								username:$("input[name='username']").val(),
								password:$("input[name='password']").val(),
								remerberPwd:$("#remerberPwd").is(":checked"),
								msgcode:$("input[name='msgcode']").val(),
							},
							success:function(data){
								if(data.code==1){
									window.location.href="${basePath}index.html";
								}
								else if(data.code==-1){
									$("#userMsg").html("<%=Config.message.get("VERIFICATION_CODE_ERROR2")%>");
									return false;
								}
								else if(data.code==2){
									$("#phone_login").css("display","block");
									$("#ipMsg").css("display","block");
									phone=data.result
									return false;
								}
								else if(data.code==3){
									$("#userMsg").html("<%=Config.message.get("THE_USER_NAME_OR_PASSWORD_ERROR")%>");
									$("#exampleInputPassword1").val("");
									$("#exampleInputPassword1").focus();
									return false;
								}
							}
						});
					}
				}
			});
			
			function validateLogin(){
				var username=$("input[name='username']").val();
				var password=$("input[name='password']").val();
				if(username.trim().length<=0){
					$("#userMsg").html("<%=Config.message.get("PLEASE_INPUT_USER_NAME")%>");
					return false;
				}
				if(password.trim().length<=0){
					$("#userMsg").html("<%=Config.message.get("PLEASE_INPUT_PASSWORD3")%>");
					return false;
				}
				return true;
			}
			
			var wait=60;  
			  function time(o) {
			          if (wait == 0) {  
			              o.removeAttribute("disabled");            
			              o.innerHTML ="<%=Config.message.get("GET_VERIFICATION_CODE")%>";  
			              wait = 60;  
			          } else {  
			              o.setAttribute("disabled", true);  
			              o.innerHTML ="Resend(" + wait + ")";  
			              wait--;  
			              setTimeout(function() {  
			                  time(o)  
			              },  
			              1000)  
			          }  
			      } ;
		});
		
		
	</script>
</head>
<body class="login">
	<!-- PAGE -->
	<section id="page">
		<!-- HEADER -->
		<header>
			<!-- NAV-BAR -->
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div id="logo">
							<span class="loginFont1"><%=Config.message.get("LOG_BEFORE_TITE")%></span> 
							<p class="loginFont2"><%=Config.message.get("LOGIN_INTERNET")%></p>
							<br />
							<p id="ipMsg" class="ipmsg"><%=Config.message.get("LOGIN_THEIPISNOTTHEBUNDING")%></p>
						</div>
					</div>
				</div>
			</div>
			<!--/NAV-BAR -->
		</header>
		<!--/HEADER -->
		<!-- LOGIN -->
		<section id="login_bg" class="visible">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="login-box">
							<h2 class="bigintro"><%=Config.message.get("LOGIN_LOGIN")%></h2>
							<div class="divide-40"></div>
							<form role="form">
								<div class="form-group">
									<i class="fa fa-envelope"></i> <input type="text"
										class="form-control" id="exampleInputEmail1" placeholder="<%=Config.message.get("LOGIN_ACCOUNT")%>"
										name="username">
								</div>
								<span id="userMsg"
									style="color: red; font: 12; margin-left: 20px"></span>
								<div class="divide-10"></div>
								<div class="form-group">
									<i class="fa fa-lock"></i> <input type="password"
										class="form-control" id="exampleInputPassword1"
										placeholder="<%=Config.message.get("LOGIN_PASSWORD")%>" name="password">
								</div>
								<input type="hidden" name="logoTemp" value="1"/ >
								<div class="divide-10"></div>

								<div class="divide-10"></div>
								<div class="form-group row" id="phone_login"
									style="display: none">
									<i class="fa fa-mobile-phone"></i> <input type="text"
										class="form-control pull-left" id="msgCode" name="msgcode"
										style="width: 140px"> <span
										class="btn msgs btn-light-grey pull-right" id="genDynMsg"><%=Config.message.get("LOGIN_GETVERIFICATIONCODE")%> </span>
									<span id="msgcodeMsg"
										style="color: red; font: 12; margin-left: 20px"></span>
								</div>

								<div>
									<label class="checkbox"> <input type="checkbox"
										class="uniform" value="" id="remerberPwd"><%=Config.message.get("LOGIN_AUTOMATICLOGIN")%>
									</label>
									<div class="divide-10"></div>
									<button type="button" class="btn btn-danger" id="loginbtn"
										data-toggle="modal"><%=Config.message.get("LOGIN_LOGIN")%></button>
								</div>
							</form>
							<!-- SOCIAL LOGIN -->
							<div class="divide-20"></div>
							<div class="divide-20"></div>
							<!-- /SOCIAL LOGIN -->
							<div class="login-helpers">
								<!-- <a href="javascript:void(0)">忘记密码?</a> <br> -->
							</div>
						</div>
					</div>
				</div>
				<!--删除dialog-->
				<div class="modal fade" id="box-add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
				</div>
				<div class="panel-body">
					<form class="form-horizontal" enctype="multipart/form-data" method="post" action="">
						<div class="col-md-12 pull-left center">
							 <div class="form-group msg_font form_group">
							 		<label for="password-input" class="col-md-2 control-label"></label> 
								    <label for="password-input" class="col-md-8 control-label" style="text-align:center;">
								            监测到此IP为非绑定的IP地址，请输入绑定手机的短信验证码进行验证
								    </label> 
							 </div>
							 <div class="form-group msg_font form_group"> 
								   <label for="password-input" class="col-md-3 control-label">短信验证码：</label> 
								   <div class="col-sm-5 row_left"> 
								     <input type="text" placeholder="" name="password" class="form-control"> 
								   </div>
								   <div class="col-sm-1 "> 
								     <button class="btn btn-default">获取短信验证码</button>
								   </div>
							 </div> 
						 </div>
					</form> 
				</div>
			</div>
		</div>
	 </div>
				<!--删除dialog end-->
			</div>
		</section>
		<!--/LOGIN -->
		<!-- REGISTER -->
		<section id="register_bg" class="font-400">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="login-box">
							<h2 class="bigintro"><%=Config.message.get("LOGIN_REGISTER")%></h2>
							<div class="divide-40"></div>
							<form role="form">
								<div class="form-group">
									<label for="exampleInputName"><%=Config.message.get("LOGIN_FULLNAME")%></label> <i
										class="fa fa-font"></i> <input type="text"
										class="form-control" id="exampleInputName">
								</div>
								<div class="form-group">
									<label for="exampleInputUsername"><%=Config.message.get("LOGIN_USERNAME")%></label> <i
										class="fa fa-user"></i> <input type="text"
										class="form-control" id="exampleInputUsername"
										AUTOCOMPLETE="OFF">
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1"><%=Config.message.get("LOGIN_EMAILADDRESS")%></label> <i
										class="fa fa-envelope"></i> <input type="email"
										class="form-control" id="exampleInputEmail1">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1"><%=Config.message.get("LOGIN_PASSWORD")%></label> <i
										class="fa fa-lock"></i> <input type="password"
										class="form-control" id="exampleInputPassword1">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword2"><%=Config.message.get("LOGIN_REPEATPASSWORD")%></label> <i
										class="fa fa-check-square-o"></i> <input type="password"
										class="form-control" id="exampleInputPassword2">
								</div>
								<div>
									<label class="checkbox"> <input type="checkbox"
										class="uniform" value=""> <%=Config.message.get("LOGIN_AGRESS")%> <a href="#"><%=Config.message.get("LOGIN_TERMSOFSERVICE")%></a><%=Config.message.get("LOGIN_AND")%>  <a href="#"><%=Config.message.get("LOGIN_PRIVACYPOLICY")%></a></label>
									<button type="submit" class="btn btn-success"><%=Config.message.get("LOGIN_SIGNUP")%></button>
								</div>
							</form>
							<!-- SOCIAL REGISTER -->
							<div class="divide-20"></div>
							<div class="center">
								<strong><%=Config.message.get("LOGIN_ORREGISTERUSING")%></strong>
							</div>
							<div class="divide-20"></div>
							<div class="social-login center">
								<a class="btn btn-primary btn-lg"> <i class="fa fa-facebook"></i>
								</a> <a class="btn btn-info btn-lg"> <i class="fa fa-twitter"></i>
								</a> <a class="btn btn-danger btn-lg"> <i
									class="fa fa-google-plus"></i>
								</a>
							</div>
							<!-- /SOCIAL REGISTER -->
							<div class="login-helpers">
								<a href="#" onclick="swapScreen('login_bg');return false;">
									<%=Config.message.get("LOGIN_BACKTOLOGIN")%></a> <br>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!--/REGISTER -->
	</section>
	<!--/PAGE -->
	<!-- JAVASCRIPTS -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- JQUERY -->
	<script src="js/jquery/jquery-2.0.3.min.js"></script>
	<!-- JQUERY UI-->
	<script
		src="js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
	<!-- BOOTSTRAP -->
	<script src="bootstrap-dist/js/bootstrap.min.js"></script>


	<!-- UNIFORM -->
	<script type="text/javascript" src="js/uniform/jquery.uniform.min.js"></script>
	<!-- BACKSTRETCH -->
	<script type="text/javascript"
		src="js/backstretch/jquery.backstretch.min.js"></script>
	<!-- CUSTOM SCRIPT -->
	<script src="js/script.js"></script>
	<script>
		jQuery(document).ready(function() {		
			App.setPage("login_bg");  //Set current page
			App.init(); //Initialise plugins and elements
		});
	</script>
	<script type="text/javascript">
		function swapScreen(id) {
			jQuery('.visible').removeClass('visible animated fadeInUp');
			jQuery('#'+id).addClass('visible animated fadeInUp');
		}
	</script>
	<!-- /JAVASCRIPTS -->
</body>
</html>
