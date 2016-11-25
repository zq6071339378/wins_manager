<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
				<div id="main-content"  class="margin-top-50">
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
													<a href="#"><%=Config.message.get("MATERIAL_MATERIAL_MANAGEMENT")%></a>
												</li>
												<li>
													<a href="#"><%=Config.message.get("PAGE_PAGE_MANAGEMENT")%></a>
												</li>
											</ul>
											<!-- /BREADCRUMBS -->
											<div class="clearfix">
												<h3 class="content-title pull-left"><%=Config.message.get("PAGE_ADD_PAGE")%></h3>
											</div>
											<div class="description"><%=Config.message.get("PAGE_SIMPLE_TABLES")%></div>
										</div>
									</div>
								</div>
								<div class="row">
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
												   	<a href="#home" data-toggle="tab"  onclick="load_page('material/page/createPage?tType=1')"><%=Config.message.get("PAGE_CREATE_TEMPLATE")%></a>
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
																  <li class="col-md-4 active">
																  <a href="javascript:;" onclick="load_page('material/page/createPage?tType=1')"  data-toggle="tab"><%=Config.message.get("PAGE_PHONE")%></a>
																   </li>
																  <li class="col-md-4">
																   <a href="javascript:;" onclick="load_page('material/page/createPage?tType=2')"  data-toggle="tab">PC</a>
																   </li>
																   <li class="col-md-4 ">
																   	<a href="javascript:;" onclick="load_page('material/page/createPage?tType=3')"  data-toggle="tab">PAD</a>
																   </li>
																</ul>
															</div>
															<div class="box-body font-400"  style="height:400px; overflow-x:hidden; overflow-y:scroll;" >
																<div id="myTabContent" class="tab-content">
																   <!--手机-->
																   <div class="tab-pane active" id="templateList">
																      <form class="form-horizontal" enctype="multipart/form-data" method="post" action="">
																   			<div class="form-group msg_font form_group" id="list-page">
																   			<c:forEach var="template"  items="${result.materials}" varStatus="status">
																	   			<div class="col-md-4">
																					<a href="javascript:;" onclick="previewTemplate('${template.templateUrl}','${template.templateId}')"  class="thumbnail">
																				         <img src="${template.templateImage}" />
																				    </a>
																				    <div class="caption">
																				    	<h5>${template.templateName}</h5>
	            																		<div>
	            																			<%-- <p class="pull-left col-md-5">${template.userName}</p>
	            																			<p class="">${template.createTime}</p> --%>
	            																		</div>
																				    </div>
																				</div>
																			</c:forEach>	
																			</div>
																   		</form>
																   </div>
																   <!--手机 END-->
																   <div class="dataTables_footer clearfix">
																    <jsp:include page="../layout/pagination.jsp"><jsp:param value="material/page/createPage?tType=${condition.tType}" name="actionName"/></jsp:include>
																   </div>
																</div>
															</div>
														</div>
													<!-- /BOX -->
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
									<!-- /BOX -->
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div id="btn_group" class="col-md-offset-7">
											<a class="btn btn-md btn-default" href="javascript:;"><%=Config.message.get("CANCEL")%></a>
											<a class="btn btn-md btn-primary btn_left" href="javascript:;" onclick="createPageByTemplate(this);"><%=Config.message.get("PAGE_ADD")%></a>
										</div>
									</div>
								</div>
								<!-- /PAGE HEADER -->
							</div><!-- /CONTENT-->
						</div>
					</div>
				</div>
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
<script src="${basePath}js/jquery.form.js"></script>	
<script>
$(function(){
	
	
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
										window.setTimeout(function(){
											load_page('material/page/listView');
										}, 500);
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
	
});

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

//获取随机英文字符当做group分组id 
function randomString(len){
	
 var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz';
 var maxPos = $chars.length;
 var pwd = '';
 for (i = 0; i < len; i++) {
	pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
 }
 return pwd;
}
</script>
<script>
window.onload = function() {
    $("#largeimg").attr("src","img/Mac.png");
    $(".box_title1").show();

   };
$("#setMetrial").on("click",function(){
	var target=$("#htmlpath").val();
	if(target==""){
		$("#width").attr("readonly",true);
		$("#heigth").attr("readonly",true);
		$("#showTime").attr("readonly",true);
		$("#addCs").attr("readonly",true);
		$("#addJs").attr("readonly",true);
		$("input[name=optionsRadios]").attr("disabled",true);
		$("input[name=optionsRadios2]").attr("disabled",true);
	}else{
		$("#width").attr("readonly",false);
		$("#heigth").attr("readonly",false);
		$("#showTime").attr("readonly",false);
		$("#addCs").attr("readonly",false);
		$("#addJs").attr("readonly",false);
		$("input[name=optionsRadios]").attr("disabled",false);
		$("input[name=optionsRadios2]").attr("disabled",false);
	}
    $("#aa").show();
    $(".box_title1").show();
  });
/* $("#setContent").on("click",function(){
	$("#content_a").show();
    $(".box_title1").show();
  }); */
  
</script>



<script type="text/javascript">
//基本设置 

function changeWidth(o){
	var target=$("#htmlpath").val();
	var adval=o.val();
	var _adval=$("#heigth").val();
	if(target!=""){
		var adModel=o.attr("adModel");
		$.ajax({
			url:'material/page/changeHtml',
			data:"target="+target+"&adModel="+adModel+"&adval="+adval+"&_adval="+_adval,
			type:'post',
			success:function(data){
				var copy_url=data.desc;
				var copy_path=data.result;
				$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
			}
		}); 
	}
}

function changeHeigth(o){
	var target=$("#htmlpath").val();
	var adval=o.val();
	var _adval=$("#width").val();
	if(target!=""){
		var adModel=o.attr("adModel");
		$.ajax({
			url:'material/page/changeHtml',
			data:"target="+target+"&adModel="+adModel+"&adval="+adval+"&_adval="+_adval,
			type:'post',
			success:function(data){
				var copy_url=data.desc;
				var copy_path=data.result;
				$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
			}
		}); 
	}
}
function changeShowTime(o){
	var target=$("#htmlpath").val();
	var adval=o.val();
	if(target!=""){
		var adModel=o.attr("adModel");
		$.ajax({
			url:'material/page/changeHtml',
			data:"target="+target+"&adModel="+adModel+"&adval="+adval,
			type:'post',
			success:function(data){
				var copy_url=data.desc;
				var copy_path=data.result;
				$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
			}
		}); 
	}
}
function add_js(o){
	var target=$("#htmlpath").val();
	var adval=o.val().trim();
	if(target!=""){
		var adModel="add_js";
		$.ajax({
			url:'material/page/changeHtml',
			data:"target="+target+"&adModel="+adModel+"&adval="+adval,
			type:'post',
			success:function(data){
				var copy_url=data.desc;
				var copy_path=data.result;
				$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
			}
		}); 
	}
}
$("input[name=optionsRadios]").click(function(){
	var optionsRadios=$('input:radio[name="optionsRadios"]:checked').val();
	var target=$("#htmlpath").val();
	if(target!=""){
		var adModel="closeButton";
		$.ajax({
			url:'material/page/changeHtml',
			data:"target="+target+"&adModel="+adModel+"&adval="+optionsRadios,
			type:'post',
			success:function(data){
				var copy_url=data.desc;
				var copy_path=data.result;
				$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
			}
		});
	}
	 });
$("input[name=optionsRadios2]").click(function(){
	var optionsRadios=$('input:radio[name="optionsRadios2"]:checked').val();
	var target=$("#htmlpath").val();
	if(target!=""){
		var adModel="opacity";
		$.ajax({
			url:'material/page/changeHtml',
			data:"target="+target+"&adModel="+adModel+"&adval="+optionsRadios,
			type:'post',
			success:function(data){
				var copy_url=data.desc;
				var copy_path=data.result;
				$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
			}
		});
	}
	 });

//文本框触发事件 
var nowvalue;
function nowText(o){
	nowvalue=o.val();
}
function overText(o){
	if(o.val()!=nowvalue){
		changeHtml(o);
	}
}

//上传图片 
function showFile(formid,o,updateIMGid){
	var adid=o.attr("id");
	var adModel=o.attr("adModel");
	var target=$("#htmlpath").val();
	$("#"+formid).ajaxSubmit(function(data){
		var adval=data.version;
		var imgId=data.desc;
		$("#"+updateIMGid).attr("src",imgId);
		$.ajax({
			url:'material/page/changeHtml',
			data:"adval="+adval+"&adid="+adid+"&target="+target+"&adModel="+adModel,
			type:'post',
			success:function(data){
				var copy_url=data.desc;
				var copy_path=data.result;
				$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
			}
		});
	});
	
}

function changeHtml(o){
	var adval=encodeURIComponent(o.val());
	var adid=o.attr("id");
	var adModel=o.attr("adModel");
	var target=$("#htmlpath").val();
	$.ajax({
		url:'material/page/changeHtml',
		data:"adval="+adval+"&adid="+adid+"&target="+target+"&adModel="+adModel,
		type:'post',
		success:function(data){
			var copy_url=data.desc;
			var copy_path=data.result;
			$("#html_temp_frame").attr("src",copy_url+"/web/tb.html");
		}
	});
	
}
</script>
<!-- 创建页面js-->
<script type="text/javascript">
     function DY_scroll(wraper,prev,next,img,speed,or)
     {
      var wraper = $(wraper);
      var prev = $(prev);
      var next = $(next);
      var img = $(img).find('ul');
      var w = img.find('li').outerWidth(true);
      next.click(function()
           {
            img.animate({'margin-left':-w},function()
                      {
                       img.find('li').eq(0).appendTo(img);
                       img.css({'margin-left':0});
                       });
            });
      prev.click(function()
           {
            img.find('li:last').prependTo(img);
            img.css({'margin-left':-w});
            img.animate({'margin-left':0});
            });
      if (or == true)
      {
       ad = setInterval(function() { next.click();},s*0.1);
       wraper.hover(function(){clearInterval(ad);},function(){ad = setInterval(function() { next.click();},s*0.1);});

      }
     }
     DY_scroll('.img-scroll','.prev','.next','.img-list',0,false);// true为自动播放，不加此参数或false就默认不自动
    </script>
    <script>
    $("#tabb").click(function(){
    	  $("#aa").css('display','none');
    	  $("#bb").css('display','block');
    	});
    $("#taba").click(function(){
  	  $("#bb").css('display','none');
  	  $("#aa").css('display','block');
  	});
    </script>