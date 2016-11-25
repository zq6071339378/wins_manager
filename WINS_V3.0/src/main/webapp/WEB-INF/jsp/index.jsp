<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 request.setAttribute("basePath",basePath);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>WINS</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">
<!--CSS-->
<link rel="stylesheet" type="text/css" href="js/jquery-ui-1.10.3.custom/css/custom-theme/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" type="text/css" href="css/cloud-admin.css"/>
<link rel="stylesheet" type="text/css" href="css/themes/default.css" id="skin-switcher"/>
<link rel="stylesheet" type="text/css" href="css/responsive.css"/>
<link rel="stylesheet" type="text/css" href="css/toolbar_global.css">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css" href="js/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" href="css/inbox.css"/>
<link href="css/titatoggle-dist.css" rel="stylesheet" type="text/css"/>
<!--CSS END-->
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
		function json2TimeStamp(milliseconds){
			var datetime = new Date();
			datetime.setTime(milliseconds);
			var year=datetime.getFullYear();
			var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
			var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
			var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
			var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
			var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
			return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
		}
		
		function json2DateStamp(milliseconds) {
			var datetime = new Date();
			datetime.setTime(milliseconds);
			var year=datetime.getFullYear();
			var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
			var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
			return year + "-" + month + "-" + date;
		}
	
		function showCitys() {
			var docum = document.getElementById("PoPy");
			if(docum.style.display == "block"){
				docum.style.display = "none";
			}else{
				docum.style.display = "block";
			}
		}

		$(function(){
			$("#PoPy").find('a').each(function () {
				$(this).click(addCityIdtoSession);
			});
			$("#_citysheng").click(deleteCityIdFromSession);
		});

		function addCityIdtoSession () {
			var urlInfo = window.location.href; //网址
			var cityName = $(this).text();
			var cityInfo = $("#city_Name").text();
			if (cityInfo.indexOf("-") > 0) {
				cityInfo = cityInfo.substring(0, cityInfo.indexOf("-"));
			}
			cityInfo += "-" + cityName;
			$("#city_Name").html(cityInfo);
			var cityId = $(this).attr("c_id");
			var provinceId = $(this).attr("p_id");
			document.getElementById("PoPy").style.display = "none";
			$.ajax({
				url:"user/addCityId",
				type:"post",
				data:{"cityId":cityId,"provinceId":provinceId},
				dataType:"json",
				success:function(){
					//alert("successs");
					urlInfo = urlInfo.substring(urlInfo.indexOf("#") + 1);
					load_page(urlInfo);
				}
			});
		}

		function deleteCityIdFromSession() {
			var cityInfo = $("#city_Name").text();
			if (cityInfo.indexOf("-") > 0) {
				cityInfo = cityInfo.substring(0, cityInfo.indexOf("-"));
			}
			$("#city_Name").html(cityInfo);
			document.getElementById("PoPy").style.display = "none";
			var urlInfo = window.location.href; //网址
			$.ajax({
				url:"user/clearCityId",
				type:"post",
				success:function(){
					urlInfo = urlInfo.substring(urlInfo.indexOf("#") + 1);
					load_page(urlInfo);
				}
			});
		}

	</script>

<script type="text/javascript">
		function logout(){
			$.ajax({
				url:"${basePath}user/logout",
				method:"post",
				success:function(data){
					if(data.code==1){
						window.location.href="${basePath}login"
					}
				}
			});
		}
		
		function refNewsSts() {
			$.ajax({
				url:"${basePath}sysnews/refNewsSts",
				method:"post",
				dataType:'json',
				success:function(data){
					if(data.code==1){
						$("#noReadNewsContent").html("");
						$("#notReadNum").html(data.result.count);
						$.each(data.result.news,function(key,val){
							if(val.News_Type==1) {
								var html ="<li>";
									html+="<a href='javascript:void(0)' onclick=\"load_page('accountNews.html?news_id="+val.News_Id+"&Create_Date="+json2DateStamp(val.Create_Time)+"');refTimeOut()\">";	
									html+="<span class='label label-primary'><i class='fa fa-comment'></i></span>";
									html+="<span class='body'>";
									html+="<span class='message'>"+val.News_Content+"</span>";
									html+="<span class='time'>";
									html+="<i class='fa fa-clock-o'></i>";
									html+="<span>"+json2TimeStamp(val.Create_Time)+"</span>";
									html+="</span>";
									html+="</span>";
									html+="</a>";
									html+="</li>";
								$("#noReadNewsContent").append(html);
							}
							if(val.News_Type==2) {
								var html ="<li>";
									html+="<a href='javascript:void(0)' onclick=\"load_page('accountNews.html?news_id="+val.News_Id+"&Create_Date="+json2DateStamp(val.Create_Time)+"');refTimeOut()\">";	
									html+="<span class='label label-primary'><i class='fa fa-gift'></i></span>";
									html+="<span class='body'>";
									html+="<span class='message'>"+val.News_Content+"</span>";
									html+="<span class='time'>";
									html+="<i class='fa fa-clock-o'></i>";
									html+="<span>"+json2TimeStamp(val.Create_Time)+"</span>";
									html+="</span>";
									html+="</span>";
									html+="</a>";
									html+="</li>";
								$("#noReadNewsContent").append(html);
							}
							if(val.News_Type==3) {
								var html ="<li>";
									html+="<a href='javascript:void(0)' onclick=\"load_page('accountNews.html?news_id="+val.News_Id+"&Create_Date="+json2DateStamp(val.Create_Time)+"');refTimeOut()\">";	
									html+="<span class='label label-primary'><i class='fa fa-heart'></i></span>";
									html+="<span class='body'>";
									html+="<span class='message'>"+val.News_Content+"</span>";
									html+="<span class='time'>";
									html+="<i class='fa fa-clock-o'></i>";
									html+="<span>"+json2TimeStamp(val.Create_Time)+"</span>";
									html+="</span>";
									html+="</span>";
									html+="</a>";
									html+="</li>";
								$("#noReadNewsContent").append(html);
							}
							if(val.News_Type==4) {
								var html ="<li>";
									html+="<a href='javascript:void(0)' onclick=\"load_page('accountNews.html?news_id="+val.News_Id+"&Create_Date="+json2DateStamp(val.Create_Time)+"');refTimeOut()\">";	
									html+="<span class='label label-primary'><i class='fa fa-exclamation-triangle'></i></span>";
									html+="<span class='body'>";
									html+="<span class='message'>"+val.News_Content+"</span>";
									html+="<span class='time'>";
									html+="<i class='fa fa-clock-o'></i>";
									html+="<span>"+json2TimeStamp(val.Create_Time)+"</span>";
									html+="</span>";
									html+="</span>";
									html+="</a>";
									html+="</li>";
								$("#noReadNewsContent").append(html);
							}
						});
					}
				}
			});
		}
		
		
		$(document).ready(function() {
			refNewsSts();
		});
		
		function refTimeOut() {
			setTimeout(refNewsSts(),500);
		}
	</script>
</head>
<body>
	<!-- HEADER -->
	<header class="navbar clearfix navbar-fixed-top" id="header">
	<div class="container">
		<div class="navbar-brand">
			<!-- COMPANY LOGO -->
			<a href="${websocketPath}index.html"> <span
				style="font-size: 24px; color: #ffffff; margin-left: 65px;"><%=Config.message.get("BANBENHAO")%></span>
			</a>
			<!-- /COMPANY LOGO -->
			<!-- SIDEBAR COLLAPSE -->
			<div id="sidebar-collapse" class="sidebar-collapse btn">
				<i class="fa fa-bars" data-icon1="fa fa-bars"
					data-icon2="fa fa-bars"></i>
			</div>
			<!-- /SIDEBAR COLLAPSE -->
		</div>
		<!-- NAVBAR LEFT -->
		<!-- BELONG  -->
		<ul class="nav navbar-nav pull-left hidden-xs" id="navbar-left"
			style="padding-left: 15px;">
			<li class="dropdown"><a href="#"
				<c:if test="${empty belongCityInfo.cityId and !empty belongCityInfo.city}">onclick="showCitys()"</c:if>
				class="dropdown-toggle city_font" data-toggle="dropdown"> <i
					class="fa fa-map-marker"></i> <c:if
						test="${empty belongCityInfo.cityId}">
						<span class="name" id="city_Name">${belongCityInfo.provinceName}</span>
					</c:if> <c:if test="${!empty belongCityInfo.cityId}">
						<span class="name">${belongCityInfo.provinceName}-${belongCityInfo.cityName}</span>
					</c:if> <i class="fa fa-angle-down"></i>
			</a></li>
		</ul>
		<!-- BELONG CITY  -->
		<!-- NAVBAR LEFT -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<ul class="nav navbar-nav pull-right">
			<!-- BEGIN NOTIFICATION DROPDOWN -->
			<li class="dropdown" id="header-notification"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <i id=""
					class="fa fa-bell"></i> <span id="notReadNum" class="badge"></span>
			</a>
				<ul id="news_list" class="dropdown-menu notification">
					<li class="dropdown-title"><span><i class="fa fa-bell"></i>
							<%=Config.message.get("INDEX_SYSTEM_MWSSAGE")%></span></li>
					<c:set var="timeString">
						<fmt:formatDate value="${news.Create_Time}" pattern="yyyy-MM-dd" />
					</c:set>
					<div id="noReadNewsContent"></div>

					<li class="footer"><a href="javascript:void(0)"
						onclick="load_page('accountNews.html?operation=1');refTimeOut()"><%=Config.message.get("INDEX_SEE_UNREAD_MESSAGE")%><i
							class="fa fa-arrow-circle-right"></i></a></li>
				</ul></li>
			<!-- END TODO DROPDOWN -->
			<!-- BEGIN USER LOGIN DROPDOWN -->
			<li class="dropdown user" id="header-user"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <img alt=""
					src="img/avatars/avatar3.jpg" /> <span class="username">${userName }</span>
					<i class="fa fa-angle-down"></i>
			</a>
				<ul class="dropdown-menu">
					<li><a href="javascript:;"
						onclick="load_page('accountInfo.html');"><i class="fa fa-user"></i><%=Config.message.get("INDEX_ACCOUNT_INFO")%></a></li>
					<!-- <li><a href="javascript:;"  onclick="load_page('account/getAccountPassword');"><i class="fa fa-cog"></i> 账户安全</a></li> -->
					<li><a data-toggle="modal" href="javascript:void(0)"
						onclick="logout();"><i class="fa fa-power-off"></i><%=Config.message.get("INDEX_LOG_OUT")%></a></li>
					<!-- <li><a data-toggle="modal" href="#box-config1" onclick="logout();"><i class="fa fa-power-off"></i>退出系统</a></li> -->
				</ul></li>
			<!-- END USER LOGIN DROPDOWN -->
		</ul>
		<!-- END TOP NAVIGATION MENU -->
	</div>

	<iframe id="PoPx" src="about:blank" frameborder="0"
		style="width: 124px; height: 0px; position: absolute; top: 0px; left: 250px;"></iframe>
	<div id="PoPy" class="divClassHide" align="left"
		style="display: none; position: absolute; z-index: 20000; width: 124px; top: 53px; left: 250px; background: transparent;">
		<div class="_citys">
			<ul id="_citysheng" class="_citys0">
				<li class="citySel" style="width: 381px">${belongCityInfo.provinceName}</li>
			</ul>
			<div id="_citys0" class="_citys1" style="display: inline-block;">
				<c:forEach items="${belongCityInfo.city}" var="c">
					<a href="javascript:void(0)" p_Id="${c.provinceId}"
						c_Id="${c.cityId}">${c.cityName}</a>
				</c:forEach>
			</div>
		</div>
	</div>
	<!--delete-->
	<div class="modal fade" id="box-config1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4>You will log out </h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn" data-dismiss="modal"><%=Config.message.get("CANCEL")%></button>
					<button type="button" class="btn btn-danger" data-dismiss="modal"
						onclick="logout();">OK</button>
				</div>
			</div>
		</div>
	</div>
	<!--delete--> </header>
	<!--/HEADER -->
	<!-- PAGE -->
	<section id="page"> <!--侧导航-->
	<div class="sidebar sidebar-fixed" id="sidebar">
		<div class="sidebar-menu nav-collapse">
			<!-- SIDEBAR MENU -->
			<ul id="navMenuList">
				<c:forEach var="nav" items="${navResult.lists}">
					<li class="<c:if test="${!empty nav.subNav}">has-sub</c:if>">
						<a href="${nav.menuUrl}" id="menu_${nav.menuId}"> <i
							class="${nav.menuCSS }"></i> <span class="menu-text">${nav.menuName }</span>
							<c:if test="${!empty nav.subNav}">
								<span class="arrow"></span>
							</c:if>
					</a> <c:if test="${!empty nav.subNav}">
							<ul class="sub">
								<c:forEach var="subnav" items="${nav.subNav}">
									<li
										<c:if test="${!empty subnav.subNav2}">class="has-sub-sub"</c:if>>
										<a class="active" href="${subnav.menuUrl }"
										id="menu_${subnav.menuId}"><span class="sub-menu-text">${subnav.menuName}</span></a>
										<c:if test="${!empty subnav.subNav2}">
											<ul class="sub-sub">
												<c:forEach var="subsubnav" items="${subnav.subNav2}">
													<li><a href="${subsubnav.menuUrl}" class=""><span
															class="sub-sub-menu-text" id="menu_${subsubnav.menuId}">${subsubnav.menuName}</span></a></li>
												</c:forEach>
											</ul>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<!-- /SIDEBAR MENU -->
		</div>
	</div>
	<!--侧导航 --> <!-- /SIDEBAR -->
	<div id="main-div">
		<div>
			<div style="text-align: center; padding-top: 298px;">
				<img src="img/loaders/4.gif">
			</div>
		</div>
	</div>
	</section>
	<jsp:include page="./layout/footer.jsp"></jsp:include>
</body>
</html>
