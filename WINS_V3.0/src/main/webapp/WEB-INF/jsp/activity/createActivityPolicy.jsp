<%@ page import="com.datacomo.wins.controller.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/taglib.jsp"></jsp:include>
<div class="tab-pane fade in active" id="pro_overview">
	<div class="box-body form">
		<form id="wizForm" action="#" class="form-horizontal">
			<div class="wizard-form">
				<div class="wizard-content">
					<div class="tab-content">
						<div class="tab-pane active" id="account">
							<div class="divide-40"></div>
							<div class="form-group create_font">
								<label class="control-label col-md-3 old lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("ACTIVITY_NAME")%>：</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="activity_name" oninput="hideErrorSpan(this)">
									<span class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
								</div>
							</div>
							<div class="divide-20"></div>
							<div class="form-group create_font" style="margin-bottom:0px">
								<label class="control-label col-md-3 lable_height"><span class="star">*</span>&nbsp;<%=Config.message.get("CREATEPOLICY_ADVERTISER")%>：</label>
								<div class="col-md-4">
									<form role="form">
										<div class="form-group" style="margin-left: 0px;margin-right: 0px">
											<input type="hidden" value="" name="PolicyId"/>
											<input class="form-control" id="customer_select" onclick="showCustomer(this)" oninput="search_customer(this)"/>
											<select id="select_customer" multiple class="form-control" style="display: none"></select>
											<span id="select_customer_span" class="error-span" style="color: red; font: 8; margin-left: 0px"></span>
										</div>
									</form>
								</div>
								<div class="col-md-4">
									<ul id="customer_ul" class="list-inline" style="margin-top: 3px;">
										<%--<li class="ul_li" style="border-style: groove;">
											<span class="label" status="-2" style="color: #777777;line-height: 2.0;padding-right: 1px;">ssssssss</span>
											<button type="button" class="close" onclick="$(this).parent().remove()">
												<span aria-hidden="true">&times;</span>
												<span class="sr-only">Close</span>
											</button>
										</li>--%>
									</ul>
								</div>
							</div>
							<div class="divide-20"></div>
							<div class="form-group create_font">
								<label class="control-label col-md-3 lable_height"><span class="star"></span>&nbsp;<%=Config.message.get("ACTIVITY_DESTRIBUTION")%>：</label>
								<div class="col-md-4">
									<textarea id="activity_desc" placeholder="" class="form-control" name="textarea" cols="5" rows="3"></textarea>
								</div>
							</div>
						</div>
						<div class="divide-20"></div>
					</div>
				</div>
			</div>
		</form>
		<div class="row">
			<div class="col-md-6">
				<div id="btn_group" class="col-md-offset-7">
					<a class="btn btn-md btn-default" href="javascript:void(0);" onclick="load_page('activityManage.html')"><%=Config.message.get("CANCEL")%></a>
					<a class="btn btn-md btn-primary btn_left" href="javascript:void(0);" onclick="addActivity()"><%=Config.message.get("SURE")%></a>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var data_cus =[];
	$(function(){
		$.ajax({
			url: "activity/getCustomerInfo",
			method: "post",
			dataType:"json",
			success:function(data){
				console.log(data);
				var code = data.code;
				if(code!=1){
					alert("<%=Config.message.get("SYSTEM_EXCEPYTION_TRY_AGAIN")%>");
					return;
				}
				var cc=data.result.list;
				if(cc.length<1){
					$("#customer_select").val("<%=Config.message.get("NO_SELECTION_OF_ADVERTISERS")%>");
					return;
				}
				for(var i=0;i<cc.length;i++){
					var obj={};
					obj.Id=cc[i].customerId;
					obj.text=cc[i].customerName;
					data_cus.push(obj);
					var option="<option value='"+cc[i].customerId+"'>"+cc[i].customerName+"</option>";
					var $option = $(option);
					$("#select_customer").append($option);
				}
			}
		});
	});

	function showCustomer(obj){
		$(obj).val("");
		$(obj).next().html("");
		$.each(data_cus,function(index, content){
			var option="<option value='"+content.Id+"'>"+content.text+"</option>";
			var $option = $(option);
			$(obj).next().append($option);
		});
		$(obj).next().show();
		$("#select_customer").find("option").each(function(){
			$(this).dblclick(function(){
				var Id = $(this).val();
				var name = $(this).html();
				var ids=[];
				$("#customer_ul").find("span.label").each(function(){
					var id=$(this).attr("status");
					ids.push(id);
				});
				if(ids.length>0){
					for(var i=0;i<ids.length;i++){
						if(Id==ids[i]){
							//alert("Can not add duplicate advertiser");
							return;
						}
					}
				}
				var li='<li class="ul_li" style="border-style: groove;">';
				li+='<span class="label" status="'+Id+'" style="color: #777777;line-height: 2.0;padding-right: 1px;">'+name+'</span>';
				li+='<button type="button" class="close" onclick="$(this).parent().remove()">';
				li+='<span aria-hidden="true" >&times;</span>';
				li+='<span class="sr-only">Close</span></button></li>';
				var $li=$(li);
				$("#customer_ul").append($li);
				$('#customer_select').val('').focus();
				$("#select_customer").hide();
				$("#select_customer_span").hide();
			});
		});
	}

	function search_customer(obj){
		var reg = $(obj).val().trim();
		$(obj).next().html("");
		$.each(data_cus,function(index, content){
			if((content.text.indexOf(reg))!=-1){
				var option="<option value='"+content.Id+"'>"+content.text+"</option>";
				var $option = $(option);
				$(obj).next().append($option);
			}
		});
		$(obj).next().show();
		$("#select_customer").find("option").each(function(){
			$(this).dblclick(function(){
				var Id = $(this).val();
				var name = $(this).html();
				var ids=[];
				$("#customer_ul").find("span.label").each(function(){
					var id=$(this).attr("status");
					ids.push(id);
				});
				if(ids.length>0){
					for(var i=0;i<ids.length;i++){
						if(Id==ids[i]){
							//alert("Can not add duplicate advertiser");
							return;
						}
					}
				}
				var li='<li class="ul_li" style="border-style: groove;">';
				li+='<span class="label" status="'+Id+'" style="color: #777777;line-height: 2.0;padding-right: 1px;">'+name+'</span>';
				li+='<button type="button" class="close" onclick="$(this).parent().remove()">';
				li+='<span aria-hidden="true">&times;</span>';
				li+='<span class="sr-only">Close</span></button></li>';
				var $li=$(li);
				$("#customer_ul").append($li);
				$('#customer_select').val('').focus();
				$("#select_customer").hide();
			});
		});
	}

	function hideErrorSpan(obj){
		$(obj).next().hide();
	}

    function addActivity() {
		var activityName = $("input[name='activity_name']").val().trim();
		var data_Id = "";
		var activityDesc=$("#activity_desc").val();
		$("#customer_ul").find("span.label").each(function(){
			var id=$(this).attr("status");
			data_Id+=id+",";
		});
		if (activityName == "") {
			$("input[name='activity_name']").next().html("请输入活动名称");
			$("input[name='activity_name']").next().show();
			return;
		}
		if(data_Id==""){
			$("#select_customer_span").html("请选择关联的广告主");
			$("#select_customer_span").show();
			return;
		}
        if($("input[name='activity_name']").next().is(':visible') || $("#select_customer_span").is(':visible')){
			return;
        }
		$.ajax({
            url: "activity/add",
			method: "post",
            data:{"activityName":activityName,"data_Id":data_Id,"activityDesc":activityDesc},
            dataType:"json",
            success:function(data){
                window.confirm(data.desc);
                load_page('activityManage.html');
            }
        });
	}

</script>