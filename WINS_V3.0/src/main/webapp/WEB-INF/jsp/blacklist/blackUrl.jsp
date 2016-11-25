<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<script src="${basePath}js/jquery.form.js"></script>
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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("BLACK_OPERATION_MANAGEMENT")%></a></li>
								<li><a href="#"><%=Config.message.get("BLACK_USER_BLACK_LIST")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("WEB_BLICK_LIST_MANAGMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("WEBSITE_BLACKLIST_MANAGERMENT_CHECK")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12 row_left">
						<div class="col-md-4 form_input login-box">
							<form>
								<div class="form-group col-md-12 row_left search_icon">
									<a href="javascript:;" onclick="find()"><i
										class="fa fa-search"></i></a> <input type="search"
										id="exampleInputSearch" class="form-control"
										placeholder="Please input the website">
								</div>
							</form>
						</div>
						<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
							<c:if test="${rv.menuId==14 && rv.add_permission!=0}">
								<div class="from-group col-md-2 row_left">
									<a class="btn btn-danger config" type="button" data-toggle="modal" href="#box-blackUrl"><%=Config.message.get("CREATE_POLICY_ADD")%></a>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--添加dialog-->
				<div class="modal fade" id="box-blackUrl" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
					data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">×</button>
								<h4 class="modal-title"><%=Config.message.get("ADD_WEBSITE_TO_BLACK_LIST")%></h4>
							</div>
							<div class="divide-10"></div>
							<div class="row">
								<div class="col-md-12">
									<ul class="nav nav-tabs" role="tablist">
										<li class="init_li active" style="width: 50%;text-align: center;font-size: 15px"><a href="#web_site_site" role="tab" data-toggle="tab"><%=Config.message.get("BLACK_URL_ADD_URL")%></a></li>
										<li class="init_li" style="width: 50%;text-align: center;font-size: 15px"><a href="#web_site_IP" role="tab" data-toggle="tab" ><%=Config.message.get("BLACK_URL_ADD_URL_IP")%></a></li>
									</ul>
								</div>
							</div>
							<div class="divide-10"></div>
							<div class="tab-content">
								<!-- 网站 -->
								<div id="web_site_site" class="tab-pane active">
									<div class="panel-body">
										<form id="form" action="${basePath}blacklist/createBlackUrls"
											method="post" enctype="multipart/form-data"
											class="form-horizontal">
											<div class="col-md-12 pull-left">
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input">
														<span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_LIST_WEB_SITE")%>：</label>
													<div class="col-sm-7 row_left">
														<input type="text" class="form-control" name="password" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: www.jd.com ,jd.com , www.jd or jd" id="UrlDomain" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\.]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9\.]/g,'')" >
													</div>
												</div>

												<div class="form-group has-error" style="display: none;"
													id="domaindiv">
													<label class="col-md-4"></label> <label class="control-label"
														for="inputWarning" id="urlDomainMsg"></label>
												</div>

												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input"><span
														class="star"></span>&nbsp;<%=Config.message.get("BLACK_LIST_WEB_SITE_PATH")%>：</label>
													<div class="col-sm-7 row_left">
														<input type="text" class="form-control" name="password" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: /news/tl2/xxx" id="UrlPath" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\\]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9\\]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9\\]/g,'')" />
													</div>
												</div>

												<div class="form-group has-error" style="display: none;"
													id="pathdiv">
													<label class="col-md-4"></label> <label class="control-label"
														for="inputWarning" id="urlPathMsg"></label>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"><%=Config.message.get("BLACK_IMPORT")%> ：</label>
													<div class="col-md-1 row_left">
														<input type="file" style="display: none" id="lefile"
															name="blackfile" onchange="showFileName()">
														<div class="input-append">
															<!-- <input id="photoCover" type="text" placeholder="文件名...." style="height:32px;width: 150px" class="search-query"> -->
															<a onclick="$('input[id=lefile]').click();"
																class="btn btn-primary"><%=Config.message.get("BLACK_SCAN")%>&nbsp;&nbsp;</a>
														</div>
													</div>
													<div class="col-md-4 col_left">
														<span class="span_fonts"><i
															class="fa fa-question-circle"
															title="<%=Config.message.get("BLACK_IMPORT_TIPS")%>"></i></span>
													</div>
													<%--<div class="col-md-1"></div>--%>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"></label>
													<span id="import" class="span_fonts col-sm-7 row_left"
															style="margin-top: -15px;"><%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%></span>
													</div>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
											<button type="button" class="btn btn-primary" data-dismiss="modal" id="createBlack"><%=Config.message.get("SAVE")%></button>
										</div>
								</div>
								<div id="web_site_IP" class="tab-pane">
									<div class="panel-body">
										<form id="formIP" action="${basePath}blacklist/createBlackUrlIPs"
											  method="post" enctype="multipart/form-data"
											  class="form-horizontal">
											<div class="col-md-12 pull-left">
												<div class="form-group msg_font form_group">
													<label class="col-md-4 control-label" for="password-input">
														<span class="star">*</span>&nbsp;<%=Config.message.get("BLACK_WEBSITE_IP")%>：</label>
													<div class="col-sm-7 row_left">
														<input type="text" class="form-control" oninput="checkIp(this);" placeholder="<%=Config.message.get("BLACK_EXAMPLE")%>: 115.61.31.220" id="UrlDomainIP" onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')" oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')" >
													</div>
												</div>

												<div class="form-group has-error" style="display: none;" id="domaindivIP">
													<label class="col-md-4"></label> <label class="control-label" id="urlDomainIPMsg"></label>
												</div>

												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"><%=Config.message.get("BLACK_IMPORT")%> ：</label>
													<div class="col-md-1 row_left">
														<input type="file" style="display: none" id="lefileIP" name="urlIPfile" onchange="showFileNameIP()">
														<div class="input-append">
															<!-- <input id="photoCover" type="text" placeholder="文件名...." style="height:32px;width: 150px" class="search-query"> -->
															<a onclick="$('input[id=lefileIP]').click();" class="btn btn-primary"><%=Config.message.get("BLACK_SCAN")%>&nbsp;&nbsp;</a>
														</div>
													</div>
													<div class="col-md-4 col_left">
														<span class="span_fonts"><i class="fa fa-question-circle" title="<%=Config.message.get("BLACK_IMPORT_TIPS")%>"></i></span>
													</div>
													<%--<div class="col-md-1"></div>--%>
												</div>
												<div class="form-group msg_font form_group">
													<label for="password-input" class="col-md-4 control-label"></label>
													<span id="importIP" class="span_fonts col-sm-7 row_left" style="margin-top: -15px;"><%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%></span>
												</div>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
										<button type="button" class="btn btn-primary" data-dismiss="modal" id="createBlackIP"><%=Config.message.get("SAVE")%></button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--添加dialog end-->
				<!--deletedialog-->
				<div class="modal fade" id="box-config-sysnews" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
					data-backdrop="static">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" data-dismiss="modal" class="close"
									type="button">×</button>
								<h4 class="modal_title"><%=Config.message.get("ARE_YOU_SURE_DELETE")%></h4>
							</div>
							<div class="modal-footer modal_line">
								<input id="newsLog_delete_id" type="hidden" value="0" />
								<button data-dismiss="modal" class="btn btn-default"
									type="button"><%=Config.message.get("CANCEL")%> </button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal" onclick="del()"><%=Config.message.get("SURE")%></button>
							</div>
						</div>
					</div>
				</div>
				<!--deletedialog end-->
				<div class="tables" class="row tab-pane fade in active">
					<div class="col-md-12 row_left">
						<!-- BOX -->
						<div class="box border purple">
							<table
								class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("BLACK_USER_SORT")%></th>
										<th><%=Config.message.get("BLACK_LIST_WEB_SITE")%></th>
										<th><%=Config.message.get("BLACK_LIST_WEB_SITE_PATH")%></th>
										<th><%=Config.message.get("BLACK_USER_CREATE_USER")%></th>
										<th><%=Config.message.get("BLACK_USER_CREATE_TIME")%></th>
										<th><%=Config.message.get("BLACK_USER_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach items="${result.LIST}" var="list" varStatus="v">
											<tr>
												<td>${v.count}</td>
												<td>${list.UrlDomain}</td>
												<td>${list.UrlPath}</td>
												<td>${list.UserName}</td>
												<td>${list.CreateTime}</td>
												<td><c:forEach var="rv"
														items="${MenuRelation.MenuRelation}">
														<c:if
															test="${rv.menuId == 14 and rv.delete_permission != 0}">
															<a class="btn btn-default config" type="button"
																data-toggle="modal" href="#box-config-sysnews"
																title="delete" onclick="deleteBlackUrl(this)"
																rel="${list.UrlId}" param1="${list.UrlDomain}"
																param2="${list.UrlPath}" param3="${list.UrlStatus}"><i
																class="fa fa-trash-o"></i></a>
														</c:if>
													</c:forEach></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp"><jsp:param
											value="blacklist/urlBlacklist?UrlDomain=${result.UrlDomain}&showStatus=${result.showStatus}"
											name="actionName" /></jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>

			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>
<!--main_container-->
<script type="text/javascript">
 function find(){
	 var UrlDomain = $("#exampleInputSearch").val().trim();
	 load_page('${basePath}blacklist/urlBlacklist?UrlDomain='+encodeURIComponent(UrlDomain));
 }

 function deleteBlackUrl(obj){
	 var urlDomain = $(obj).attr("param1");
	 var urlPath = $(obj).attr("param2");
	 var urlStatus = $(obj).attr("param3");
	 var newsId=$(obj).attr('rel');
	 $('#newsLog_delete_id').val(newsId);
	 $('#newsLog_delete_id').attr("urlDomain",urlDomain);
	 $('#newsLog_delete_id').attr("urlPath",urlPath);
	 $('#newsLog_delete_id').attr("urlStatus",urlStatus);
 }

 function del(){
	 var urlId=$("#newsLog_delete_id").val();
	 var urlDomain=$("#newsLog_delete_id").attr("urlDomain");
	 var urlPath=$("#newsLog_delete_id").attr("urlPath");
	 var urlStatus=$("#newsLog_delete_id").attr("urlStatus");
	 $("#box-delete").hide();
	 $.ajax({
		   url: "blacklist/delUrl",
		   type: "post",
		   data: {"urlId":urlId,"urlDomain":urlDomain,"urlPath":urlPath,"urlStatus":urlStatus},
		   dataType:"json",
		   success: function(data){
			   window.confirm(data.desc);
			   setTimeout(function(){load_page('blacklist/urlBlacklist')},300);
			   //load_page('blacklist/urlBlacklist');
		   }
	 });
 }
 function showFileNameIP(){
	 var filepath = $("#lefileIP").val().trim();
	 $("#importIP").empty();
	 $("#importIP").text(filepath);
	 $("#domaindivIP").hide();
	 $("#UrlDomainIP").val("");
 }
 
 function showFileName(){
	 var filepath = $("#lefile").val().trim();
	 $("#import").empty();
 	 $("#import").text(filepath);
	 $("#domaindiv").hide();
	 $("#UrlDomain").val("");
	 $("#UrlPath").val("");
}
 function checkIp(obj){
	 var IP = $(obj).val();
	 var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	 if(!reg.test(IP)){
		 $("#urlDomainIPMsg").html("<%=Config.message.get("IP_NOT_CORRECT")%>");
		 $("#domaindivIP").show();
		 return;
	 }else{
		 $("#domaindivIP").hide();
		 var ipArry = new Array();
		 ipArry = IP.split(".");
		 if(ipArry[0]>255 || ipArry[0]<0 || ipArry[1]>255 || ipArry[1]<0 || ipArry[2]>255 || ipArry[2]<0 || ipArry[3]>255 || ipArry[3]<0){
			 $("#urlDomainIPMsg").html("<%=Config.message.get("IP_NOT_CORRECT_BETWEEN")%>");
			 $("#domaindivIP").show();
			 return ;
		 }else{
			 $("#domaindivIP").hide();
		 }
	 }
 }

 //创建网址IP黑名单
 $("#createBlackIP").click(function(){
	 var UrlIP=$("#UrlDomainIP").val();
	 var filePath=$("#lefileIP").val();
	 if($("#domaindivIP").is(":visible")){
		 return false;
	 }
	 if(filePath==""){
		 if (UrlIP==""){
			 $("#domaindivIP").show();
			 $("#urlDomainIPMsg").html("<%=Config.message.get("BLACK_PLEASE_INPUT_IP_OR")%>");
			 return false;
		 }else{
			 $("#domaindivIP").hide();
			 $.ajax({
				 url: "blacklist/createBlackUrlIP",
				 type: "post",
				 data:{"UrlIP":UrlIP},
				 dataType:"json",
				 success: function(data){
					 window.confirm(data.desc);
					 setTimeout(function(){load_page('blacklist/urlBlacklist')},300);
					 //load_page('blacklist/urlBlacklist');
				 },
				 error:function(){
					 window.confirm("operation error");
				 }
			 });

		 }
	 }else{
		 var index=filePath.lastIndexOf(".");
		 var txt=filePath.substring(index+1,filePath.length).toUpperCase();
		 if (txt!="TXT"){
			 window.confirm("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
			 //$("#photoCover").val("");
			 $("#lefileIP").val("");
			 $("#importIP").text("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
			 return false;
		 }
		 $("#formIP").ajaxSubmit(function(data){
			 window.confirm(data.desc);
			 setTimeout(function(){load_page('blacklist/urlBlacklist')},300);
			 //load_page('blacklist/urlBlacklist');
		 });
	 }
 });


//创建黑名单
 $("#createBlack").click(function (){
 	 var UrlDomain=$("#UrlDomain").val().trim();
 	 var UrlPath=$("#UrlPath").val().trim();
 	 var filepath = $("#lefile").val().trim();
	 //var reg=/^([\w\d]+\.)+\w{2,}(\/[\d\w\.\?%=&]+)*/;
     if(filepath ==""){
         //var reg = /^([a-z]([a-z0-9\-]*[\.。])+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&]*)?)?(#[a-z][a-z0-9_]*)?$/;
		 if (UrlDomain==""){
			 $("#domaindiv").show();
			 $("#urlDomainMsg").html("<%=Config.message.get("PLEASE_INPUT_WEB_SITE_OR")%>");
			 return false;
		 }/*else{
			 $("#domaindiv").hide();
			 /!* 验证网址黑名单格式 *!/
			 if (!reg.test(UrlDomain)){
				 $("#domaindiv").show();
				 $("#urlDomainMsg").html("web sitr error,example： www.jd.com ,jd.com or www.jd");
				 $("#UrlDomain").html("");
				 return false;
			 }
		 }*/
		var index = UrlPath.indexOf("/");
		 if(UrlPath!="" && index!=0){
			 UrlPath = "/"+UrlPath;
		 }
		 UrlPath = encodeURIComponent(UrlPath);
		$.ajax({
			   url: "blacklist/createBlackUrl",
			   type: "post",
			   data:"UrlDomain="+UrlDomain+"&UrlPath="+UrlPath,
			   success: function(data){
				   window.confirm(data.desc);
				   setTimeout(function(){load_page('blacklist/urlBlacklist')},300);
				   //load_page('blacklist/urlBlacklist');
			   },
			   error:function(){
					window.confirm("operation error");
				}
			});
     }else{
         var index=filepath.lastIndexOf(".");
         var txt=filepath.substring(index+1,filepath.length).toUpperCase();
         if (txt!="TXT"){
             window.confirm("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
             //$("#photoCover").val("");
             $("#lefile").val("");
			 $("#import").text("<%=Config.message.get("BLACK_ONLY_SUPPORT_TXT")%>");
             return false;
         }
         $("#form").ajaxSubmit(function(data){
             window.confirm(data.desc);
             setTimeout(function(){load_page('blacklist/urlBlacklist')},300);
             //load_page('blacklist/urlBlacklist');
         });
     }
 });
	function clearInfo(){
		$("#lefile").val("");
		$("#lefileIP").val("");
		$("#UrlDomain").val("");
		$("#UrlDomainIP").val("");
		$("#UrlPath").val("");
		$("#domaindiv").hide();
		$("#domaindivIP").hide();
	}
</script>