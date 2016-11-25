
<%@ page language="java" pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix='fmt'
	uri="http://java.sun.com/jsp/jstl/fmt"%><%@ taglib prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%><%@taglib prefix="myEl"
	uri="http://hotdata.com/functions"%>
<%
String isEnglish = Config.message.get("isEnglish").toString();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
//request.setAttribute("dspImagePath", "http://192.168.88.195:82/");
request.setAttribute("dspImagePath", "http://121.42.195.190:8080/winsfile/");
if(request.getSession().getAttribute("userId") ==null){
		 response.sendRedirect("login");
}
request.setAttribute("isEnglish",isEnglish);
request.setAttribute("basePath",basePath);
request.setAttribute("version","v1.0.1");
request.setAttribute("develop","true");
%>