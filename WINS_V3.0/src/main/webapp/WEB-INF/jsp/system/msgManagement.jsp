<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>

<!--删除dialog-->
<div class="modal fade" id="box-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal" class="close"
					type="button">×</button>
				<h4 class="modal_title"><%=Config.message.get("ARE_YOU_SURE_DELETE")%></h4>
			</div>
			<div class="modal-footer modal_line">
				<button data-dismiss="modal" class="btn btn-default" type="button"><%=Config.message.get("CANCEL")%></button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onclick="sureDelete()"><%=Config.message.get("SURE")%></button>
			</div>
		</div>
	</div>
</div>
<!--删除dialog end-->
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
								<li><i class="fa fa-home"></i> <a href="index.html"><%=Config.message.get("ROLEMANAGE_HOME")%></a>
								</li>
								<li><a href="#"><%=Config.message.get("ROLEMANAGE_SYSTEMMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("MAGMANAGEMENT_MESSAGEMANAGEMENT")%></h3>
							</div>
							<div class="description"><%=Config.message.get("MAGMANAGEMENT_MESSAGEMENTCREAT")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12" style="padding-left: 0px;">
						<div class="col-md-2 row_left" style="padding-left: 0px;">
							<select class="form-control" id="news_type"
								onchange="typeChange();">
								<option><%=Config.message.get("MAGMANAGEMENT_ALL")%></option>
								<option value="1"
									<c:if test="${news_type==1}">selected="true"</c:if>><%=Config.message.get("MAGMANAGEMENT_NOTIFICATION")%></option>
								<option value="2"
									<c:if test="${news_type==2}">selected="true"</c:if>><%=Config.message.get("MAGMANAGEMENT_SALESPROMOTIONINFORMATION")%></option>
								<option value="3"
									<c:if test="${news_type==3}">selected="true"</c:if>><%=Config.message.get("MAGMANAGEMENT_ACTIONINFORMATION")%></option>
								<option value="4"
									<c:if test="${news_type==4}">selected="true"</c:if>><%=Config.message.get("MAGMANAGEMENT_SECURITYINFORMATION")%></option>
							</select>
						</div>
						<div class="col-md-5 form_input login-box">
							<form>
								<div class="form-group col-md-12 row_left search_icon">
									<a href="javascript:void(0)" onclick="searchNews()"><i
										class="fa fa-search"></i></a> <input value="${news_content}"
										type="search" id="exampleInputSearch" class="form-control"
										placeholder="<%=Config.message.get("MAGMANAGEMENT_PLEASEATTENTINOS")%>">
								</div>
							</form>
						</div>
						<div class="from-group col-md-2 row_left">
							<c:forEach var="rv" items="${MenuRelation.MenuRelation}">
								<c:if test="${rv.menuId==21 && rv.add_permission!=0}">
									<button href="#box-config" data-toggle="modal"
										class="btn btn-danger" type="button"><%=Config.message.get("MAGMANAGEMENT_CREATE")%></button>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="divide-10"></div>
				<!--群租管理TABLE-->
				<div class="tables" class="row tab-pane fade in active">
					<div class="col-md-12 row_left">
						<!-- BOX -->
						<div class="box border purple">
							<table
								class="datatable table table-striped table-bordered table-hover dataTable">
								<thead>
									<tr>
										<th><%=Config.message.get("MAGMANAGEMENT_SORT")%></th>
										<th><%=Config.message.get("MAGMANAGEMENT_RCEPTIONACCOUNT")%></th>
										<th><%=Config.message.get("MAGMANAGEMENT_TIME")%></th>
										<th><%=Config.message.get("MAGMANAGEMENT_INFORMATIONCONTENT")%></th>
										<th><%=Config.message.get("MAGMANAGEMENT_INFORMATIONTYPE")%></th>
										<th><%=Config.message.get("MAGMANAGEMENT_OPERATION")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach var="news" items="${result.list}" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>${news.Receive_Name}</td>
												<td><fmt:formatDate value="${news.Create_Time}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>${news.News_Content}</td>
												<c:if test="${news.News_Type==1}">
													<td>notification</td>
												</c:if>
												<c:if test="${news.News_Type==2}">
													<td>Sales promotion information</td>
												</c:if>
												<c:if test="${news.News_Type==3}">
													<td>action information</td>
												</c:if>
												<c:if test="${news.News_Type==4}">
													<td> security information</td>
												</c:if>
												<td><c:forEach var="rv" items="${MenuRelation.MenuRelation}">
														<c:if test="${rv.menuId == 21 and rv.delete_permission != 0}">
															<a class="btn btn-default config" type="button"
																onclick="deleteNews(this)" data-toggle="modal"
																href="#box-delete" status="${news.News_Id}" title="delete"><i
																class="fa fa-trash-o"></i></a>
														</c:if>
													</c:forEach></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<input type="hidden" id="sure_delete" value="" />
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param
											value="sysNews.html?news_type=${news_type}&news_content=${news_content}"
											name="actionName" />
									</jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
						<div class="modal fade" id="box-config" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="">
										<div class="panel panel-default">
											<div class="panel-heading">
												<button class="close" aria-hidden="true"
													data-dismiss="modal" type="button">×</button>
												<h4><%=Config.message.get("MAGMANAGEMENT_SEDTHEMESSAGE")%></h4>
											</div>
											<div class="panel-body">
												<form action="" method="post" enctype="multipart/form-data"
													class="form-horizontal">
													<div class="form-group msg_font form_group">
														<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("MAGMANAGEMENT_RCEPTIONACCOUNT")%>：</label>
														<div class="col-sm-5">
															<select class="form-control" id="receive_name">
																<option value="0"><%=Config.message.get("MAGMANAGEMENT_ALL")%></option>
																<option value="4"><%=Config.message.get("MAGMANAGEMENT_PROVINCIALOPERATOR")%></option>
																<option value="5"><%=Config.message.get("MAGMANAGEMENT_CITYOPEATOR")%></option>
																<option value="1"><%=Config.message.get("MAGMANAGEMENT_CHECKADMINISTRATOR")%></option>
															</select>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("MAGMANAGEMENT_MESSAGETYOE")%>：</label>
														<div class="col-sm-5">
															<select class="form-control" id="message_type">
																<option value="1"><%=Config.message.get("MAGMANAGEMENT_NOTIFICATION")%> </option>
																<option value="2"><%=Config.message.get("MAGMANAGEMENT_SALESPROMITIONINFORMATION")%></option>
																<option value="3"><%=Config.message.get("MAGMANAGEMENT_ACTIONINFORMATION")%></option>
																<option value="4"><%=Config.message.get("MAGMANAGEMENT_INFORMATIONCONTENT")%></option>
															</select>
														</div>
													</div>
													<div class="form-group msg_font form_group">
														<label class="col-md-4 control-label" for="password-input"><%=Config.message.get("MAGMANAGEMENT_MESSAGECONTIENT")%>：</label>
														<div class="input-append">
															<div class="col-md-8">
																<textarea class="form-control" name="textarea"
																	id="news_content" cols="5" rows="3"></textarea>
															</div>
														</div>
													</div>
												</form>
											</div>
											<input type="hidden" id="sure_delete" value="" />
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal"><%=Config.message.get("ROLEMANAGE_CANCEL")%></button>
												<button type="button" class="btn btn-primary"
													data-dismiss="modal" onclick="sendMessage();"><%=Config.message.get("MAGMANAGEMENT_SEND")%></button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
	//查询消息
	function searchNews(){
        var news_content=$("#exampleInputSearch").val().trim();
        var news_type = $("#news_type").val();
        if(news_type=="all"){
            load_page('sysNews.html?news_content='+news_content);
        }else{
            load_page('sysNews.html?news_content='+news_content+'&news_type='+news_type);
        }
    };
    
    function typeChange(){
    	var news_type = $("#news_type").val();
    	if(news_type=="all"){
            load_page('sysNews.html');
        }else{
            load_page('sysNews.html?news_type='+news_type);
        }
    }
   //发送消息
    function sendMessage(){
        var role_type = $("#receive_name").val();
        var news_type = $("#message_type").val();
        var news_content = $("#news_content").val().trim();
        if (news_content==""){
            alert("non-empty");
            return;
        }
        if (news_content.length > 30){
        	alert("No more than 30 words ");
            return;
        }
        //ajax请求
        $.ajax({
            url:"${basePath}sysnews/broadcast",
            type:"post",
            data:{"role_type":role_type ,"news_type":news_type,"news_content":news_content },
            dataType:"json",
            success:function(result){
                alert("succeed！");
                $("#news_content").val("");
                load_page("sysNews.html");
            },
            error:function(){
                alert("error！");
                $("#news_content").val("");
                load_page("sysNews.html");
            }
        });
    }
    
    //删除
    function deleteNews(obj){
        var news_id = $(obj).attr("status");
        $("#sure_delete").val(news_id);
    }
    //确定删除
    function sureDelete(){
        var id = $("#sure_delete").val();
        if(confirm){
            $.ajax({
                url:"${basePath}sysnews/delete",
                method:"post",
                data:{'news_id':id},
                success:function(data){
                    if(data.code==1){
                    	setTimeout(function(){load_page('sysNews.html')},300);
                    } else {
                    	alert("failed！")
                    }
                }
            });
        }
    }


</script>