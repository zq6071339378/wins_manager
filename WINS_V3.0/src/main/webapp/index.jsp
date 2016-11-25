<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 request.setAttribute("basePath",basePath);
 %>
<%response.sendRedirect("login");%>