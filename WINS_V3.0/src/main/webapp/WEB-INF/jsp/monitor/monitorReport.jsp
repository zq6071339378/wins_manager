<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="com.datacomo.wins.controller.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<script src="${basePath}js/jquery.form.js"></script>
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
								<li><i class="fa fa-home"></i> <a href="#"><%=Config.message.get("MONITORREPORT_SYSTEMMANAGEMENT")%></a></li>
								<li><a href="#"><%=Config.message.get("MONITORREPORT_MONITORINGMANAGEMENT")%></a></li>
							</ul>
							<!-- /BREADCRUMBS -->
							<div class="clearfix">
								<h3 class="content-title pull-left"><%=Config.message.get("MONITORREPORT_MONITORINGTABLE")%></h3>
							</div>
							<div class="description"><%=Config.message.get("MONITORREPORT_MONITORREPORTCHECK")%></div>
						</div>
					</div>
				</div>
				<!-- /PAGE HEADER -->
				<div class="row">
					<div class="col-md-12">
						<!-- BOX -->
						<div class="box">
							<div class="row ">
								<div class="box-body col-md-2">
									<div class="box-body">
										<div id="tree2" class="tree tree-unselectable">
											<div class="tree-folder">
												<div class="tree-folder-header">
													<i class="fa-folder fa"></i>
													<div class="tree-folder-name"><%=Config.message.get("MONITORREPORT_SERVENAME")%></div>
												</div>
												<div class="tree-folder-content"></div>
												<div class="tree-loader"></div>
											</div>
											<c:forEach items="${result.Server_List}" var="string"
												varStatus="vs">
												<div class="tree-item">
													<div class="tree-item-name">
														<a onclick="getReport('${string}');">${string}</a>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="col-md-10 border_report"
									style="margin-top: -30px; padding-top: 30px;">
									<div class="box border blue">
										<div class="box-title report_title">
											<h5><%=Config.message.get("MONITORREPORT_DETAILDATA")%></h5>
										</div>
										<div class="box-body">
											<table class="table table-striped table-striped1">
												<thead>
													<tr>
														<th><%=Config.message.get("MONITORREPORT_MONITORINGTIME")%></th>
														<th><%=Config.message.get("MONITORREPORT_IP")%></th>
														<th><%=Config.message.get("MONITORREPORT_CPOUSERRATIO")%></th>
														<th><%=Config.message.get("MONITORREPORT_MEMORYUSERRATIO")%></th>
														<th><%=Config.message.get("MONITORREPORT_HARDDISKUSERRATIO")%></th>
														<th><%=Config.message.get("MONITORREPORT_NETWORK(UP/DOWN)")%></th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td id="report_date">${result.Report_Date}</td>
														<td id="server_ip">${result.Server_Ip}</td>
														<td id="cpu_usage">${result.Cpu_Usage}%</td>
														<td id="memory_usage">${result.Memory_Usage}%</td>
														<td id="harddisk_usage">${result.Harddisk_Usage}%</td>
														<td id="network_state">${result.Network_Uplink}kbps/${result.Network_Downlink}kbps</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<div class="box border blue">
										<input id="Report_Date" type="hidden"
											value="${result.Report_Date}"> <input
											id="Server_Name" type="hidden" value="${result.Server_Name}">
										<div class="box-title">
											<h5><%=Config.message.get("MONITORREPORT_CPUPERCENT")%></h5>
											<div class="tools">
												<a href="javascript:;" class="reload"> <i
													class="fa fa-refresh"></i>
												</a> <a href="javascript:;" class="collapse"> <i
													class="fa fa-chevron-up"></i>
												</a>
											</div>
										</div>
										<div class="box-body">
											<div style="padding: 0px; position: relative;" id="chart_cpu"
												class="chart"></div>
										</div>
									</div>
									<div class="box border blue">
										<div class="box-title">
											<h5><%=Config.message.get("MONITORREPORT_MEMORYUSAGE")%></h5>
											<div class="tools">
												<a href="javascript:;" class="reload"> <i
													class="fa fa-refresh"></i>
												</a> <a href="javascript:;" class="collapse"> <i
													class="fa fa-chevron-up"></i>
												</a>
											</div>
										</div>
										<div class="box-body">
											<div style="padding: 0px; position: relative;" id="chart_mem"
												class="chart"></div>
										</div>
									</div>
									<div class="box border blue">
										<div class="box-title">
											<h5><%=Config.message.get("MONITORREPORT_HARDDISKUSAGE")%></h5>
											<div class="tools">
												<a href="javascript:;" class="reload"> <i
													class="fa fa-refresh"></i>
												</a> <a href="javascript:;" class="collapse"> <i
													class="fa fa-chevron-up"></i>
												</a>
											</div>
										</div>
										<div class="box-body">
											<div style="padding: 0px; position: relative;"
												id="chart_disk" class="chart"></div>
										</div>
									</div>
									<div class="box border blue">
										<div class="box-title">
											<h5><%=Config.message.get("MONITORREPORT_NETWORK(UP)(KBPS)")%></h5>
											<div class="tools">
												<a href="javascript:;" class="reload"> <i
													class="fa fa-refresh"></i>
												</a> <a href="javascript:;" class="collapse"> <i
													class="fa fa-chevron-up"></i>
												</a>
											</div>
										</div>
										<div class="box-body">
											<div style="padding: 0px; position: relative;"
												id="chart_upload" class="chart"></div>
										</div>
									</div>
									<div class="box border blue">
										<div class="box-title">
											<h5><%=Config.message.get("MONITORREPORT_NETWORK(DOWN)(KBPS)")%></h5>
											<div class="tools">
												<a href="javascript:;" class="reload"> <i
													class="fa fa-refresh"></i>
												</a> <a href="javascript:;" class="collapse"> <i
													class="fa fa-chevron-up"></i>
												</a>
											</div>
										</div>
										<div class="box-body">
											<div style="padding: 0px; position: relative;"
												id="chart_download" class="chart"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /BOX -->
					</div>
				</div>
			</div>
			<!-- /CONTENT-->
		</div>
	</div>
</div>
<script type="text/javascript">
	var Report_Period = new Array();
	var Cpu_Usage = new Array();
	var Memory_Usage = new Array();
	var Harddisk_Usage = new Array();
	var Network_Uplink = new Array();
	var Network_Downlink = new Array();
	$(document).ready(
	 	getReport()
			)

	function getReport(name) {
				var Report_Date = $("#Report_Date").val();
				if(name==null) {
					var Server_Name = $("#Server_Name").val();
				} else {
					var Server_Name = name;
				}
				Report_Period = [];
				Cpu_Usage = [];
				Memory_Usage = [];
				Harddisk_Usage = [];
				Network_Uplink = [];
				Network_Downlink = [];
				$.ajax({
					url : "systasks/monitorlistJson",
					type : "post",
					data : {
						"Report_Date" : Report_Date,
						"Server_Name" : Server_Name
					},
					success : function(data) {
						if(name!=null) {
							var len = data.result.list.length;
							var monitorData = data.result.list[len-1];
							$("#report_date").html(monitorData.Report_Date);
							$("#server_ip").html(monitorData.Server_Ip);
							$("#cpu_usage").html(monitorData.Cpu_Usage+"%");
							$("#memory_usage").html(monitorData.Memory_Usage+"%");
							$("#harddisk_usage").html(monitorData.Harddisk_Usage+"%");
							$("#network_state").html(monitorData.Network_Uplink+"kbps/"+monitorData.Network_Downlink+"kbps");
						}

						$.each(data.result.list, function(key, val) {
							Report_Period.push(val.Report_Period + ":00");
							Cpu_Usage.push(val.Cpu_Usage);
							Memory_Usage.push(val.Memory_Usage);
							Harddisk_Usage.push(val.Harddisk_Usage);
							Network_Uplink.push(val.Network_Uplink);
							Network_Downlink.push(val.Network_Downlink);
						});

						showMonitorTables(Report_Period, Cpu_Usage,
								Memory_Usage, Harddisk_Usage, Network_Uplink,
								Network_Downlink);
					}
				});
			}
	function showMonitorTables(Report_Period, Cpu_Usage, Memory_Usage,
			Harddisk_Usage, Network_Uplink, Network_Downlink) {
		$("#chart_cpu").highcharts({
			chart : {
				zoomType : 'xy'
			},
			credits : {
				enabled : false
			},
			title : {
				text : ''
			},
			subtitle : {
				text : ''
			},
			xAxis : [ {
				categories : Report_Period
			} ],
			yAxis : [ { // Secondary yAxis
				title : {
					text : '',
					style : {
						color : '#4572A7'
					}
				},
				labels : {
					formatter : function() {
						return this.value + '%';
					},
					style : {
						color : '#4572A7'
					}
				},
			} ],
			legend : {
				align : 'center', //水平方向位置
				verticalAlign : 'bottom', //垂直方向位置
				x : 0, //距离x轴的距离
				y : 0
			//距离Y轴的距离
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					events : {
						click : function(e) {
							//alert(e.point.y);
						}
					},
				}
			},
			series : [ {
				name : 'CPU user ratio',
				color : '#4572A7',
				type : 'spline',
				data : Cpu_Usage,

			} ]
		});

		$("#chart_mem").highcharts({
			chart : {
				zoomType : 'xy'
			},
			credits : {
				enabled : false
			},
			title : {
				text : ''
			},
			subtitle : {
				text : ''
			},
			xAxis : [ {
				categories : Report_Period
			} ],
			yAxis : [ { // Secondary yAxis
				title : {
					text : '',
					style : {
						color : '#4572A7'
					}
				},
				labels : {
					formatter : function() {
						return this.value + '%';
					},
					style : {
						color : '#4572A7'
					}
				},
			} ],
			legend : {
				align : 'center', //水平方向位置
				verticalAlign : 'bottom', //垂直方向位置
				x : 0, //距离x轴的距离
				y : 0
			//距离Y轴的距离
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					events : {
						click : function(e) {
							//alert(e.point.y);
						}
					},
				}
			},
			series : [ {
				name : 'Memory urage',
				color : '#4572A7',
				type : 'spline',
				data : Memory_Usage,

			} ]
		});

		$("#chart_disk").highcharts({
			chart : {
				zoomType : 'xy'
			},
			credits : {
				enabled : false
			},
			title : {
				text : ''
			},
			subtitle : {
				text : ''
			},
			xAxis : [ {
				categories : Report_Period
			} ],
			yAxis : [ { // Secondary yAxis
				title : {
					text : '',
					style : {
						color : '#4572A7'
					}
				},
				labels : {
					formatter : function() {
						return this.value + '%';
					},
					style : {
						color : '#4572A7'
					}
				},
			} ],
			legend : {
				align : 'center', //水平方向位置
				verticalAlign : 'bottom', //垂直方向位置
				x : 0, //距离x轴的距离
				y : 0
			//距离Y轴的距离
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					events : {
						click : function(e) {
							//alert(e.point.y);
						}
					},
				}
			},
			series : [ {
				name : 'Hard disk usage',
				color : '#4572A7',
				type : 'spline',
				data : Harddisk_Usage,

			} ]
		});

		$("#chart_upload").highcharts({
			chart : {
				zoomType : 'xy'
			},
			credits : {
				enabled : false
			},
			title : {
				text : ''
			},
			subtitle : {
				text : ''
			},
			xAxis : [ {
				categories : Report_Period
			} ],
			yAxis : [ { // Secondary yAxis
				title : {
					text : '',
					style : {
						color : '#4572A7'
					}
				},
				labels : {
					formatter : function() {
						return this.value;
					},
					style : {
						color : '#4572A7'
					}
				},
			} ],
			legend : {
				align : 'center', //水平方向位置
				verticalAlign : 'bottom', //垂直方向位置
				x : 0, //距离x轴的距离
				y : 0
			//距离Y轴的距离
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					events : {
						click : function(e) {
							//alert(e.point.y);
						}
					},
				}
			},
			series : [ {
				name : 'Network uplink rate',
				color : '#4572A7',
				type : 'spline',
				data : Network_Uplink,

			} ]
		});

		$("#chart_download").highcharts({
			chart : {
				zoomType : 'xy'
			},
			credits : {
				enabled : false
			},
			title : {
				text : ''
			},
			subtitle : {
				text : ''
			},
			xAxis : [ {
				categories : Report_Period
			} ],
			yAxis : [ { // Secondary yAxis
				title : {
					text : '',
					style : {
						color : '#4572A7'
					}
				},
				labels : {
					formatter : function() {
						return this.value;
					},
					style : {
						color : '#4572A7'
					}
				},
			} ],
			legend : {
				align : 'center', //水平方向位置
				verticalAlign : 'bottom', //垂直方向位置
				x : 0, //距离x轴的距离
				y : 0
			//距离Y轴的距离
			},
			plotOptions : {
				series : {
					cursor : 'pointer',
					events : {
						click : function(e) {
							//alert(e.point.y);
						}
					},
				}
			},
			series : [ {
				name : ' Reverse Rate',
				color : '#4572A7',
				type : 'spline',
				data : Network_Downlink,

			} ]
		});
	}
</script>
