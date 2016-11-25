<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.datacomo.wins.controller.Config" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<!--main_container-->
<script type="text/javascript">
    $(document).ready(function() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        if(month >= 1 && month <= 9){
            month = "0" + month;
        }
        var day = date.getDate();
        if(day >= 0 && day <= 9){
            day = "0" + day;
        }
        var fullYear=year+"-"+month+"-"+day;
        var birthday=$('#birthday').val();
        if(birthday==null||birthday==""){
            birthday=fullYear;
        }
        $('#birthday').daterangepicker(
                {
                    singleDatePicker: true,
                    startDate:birthday,
                },
            function(){
                var logTime = $("#birthday").val();
                load_page('operationLog.html?&logTime='+logTime);
            }
        );
    });
</script>

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
								<li><a href="#">Account set</a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("ACCOUNT_SET_OPERATION_OPERATIONLOGS")%></h3>
							</div>
							<div class="description"><%=Config.message.get("ACCOUNT_SET_OPERATION_OPERATIONLOGSCHECK")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<div class='col-sm-3 row_left'>
							<div class="controls">
								<div class="input-group date">
									<input id="birthday" type="text" value="${result.logTime}"
										class="form-control"  readonly="readonly"> <span class="input-group-addon"><span
										class="fa fa-calendar"></span> </span>
								</div>
							</div>
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
										<th><%=Config.message.get("ACCOUNT_SET_SORT")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_OPERATION_OPERATIONACCOUNT")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_OPERATION_TIME")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_OPERATION_CONTENT")%></th>
										<th><%=Config.message.get("ACCOUNT_SET_OPERATION_LOGSTYPE")%></th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${!empty result}">
										<c:forEach var="log" items="${result.list}" varStatus="vs">
											<tr>
												<td>${vs.count}</td>
												<td>${log.userName}</td>
												<td><fmt:formatDate value="${log.logTime}"
														pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>${log.logCont}</td>
												<td><c:if test="${log.logType==1}"><%=Config.message.get("ACCOUNT_SET_OPERATION_LOGSTYPE")%></c:if> <c:if
														test="${log.logType==2}"><%=Config.message.get("ACCOUNT_SET_OPERATION_ADNORMALLOGS")%></c:if> <c:if
														test="${log.logType==3}"><%=Config.message.get("ACCOUNT_SET_OPERATION_ALARMLOGS")%></c:if> <c:if
														test="${log.logType==4}"><%=Config.message.get("ACCOUNT_SET_OPERATION_DEBUGLOGS")%></c:if></td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<div class="row">
								<div class="dataTables_footer clearfix">
									<jsp:include page="../layout/pagination.jsp">
										<jsp:param value="operationLog.html?logTime=${result.logTime}"
											name="actionName" />
									</jsp:include>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
				<!--群组管理TABLE END-->
			</div>
			<!-- /CONTENT END-->
		</div>
	</div>
</div>

