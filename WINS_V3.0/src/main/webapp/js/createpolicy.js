﻿﻿var _changeGroupList=new Array();
var data_medil = [];
var data_mm = [];
var keywords_content=function(target){
	var other="";
	return '<li onclick="$(this).remove();" class="col-md-6" style="width:66%;"><a class="button changearealist" role="button" >'+target.content+'</a><span class="icon"><i class="fa fa-times"></i><input required="required"  name="'+target.name+'" value="'+target.id+'" type="hidden">'+other+'</span></li>';
}
var ips_content=function(target){
	var other="";
	return '<li onclick="$(this).remove();" class="col-md-6" style="width:66%;"><a class="button changeipslist" role="button" >'+target.content+'</a><span class="icon"><i class="fa fa-times"></i><input required="required"  name="'+target.name+'" value="'+target.id+'" type="hidden">'+other+'</span></li>';
}
var urllist_content=function(target){
	var other="";
	return '<li onclick="$(this).remove();" class="col-md-6" style="width:86%;"><a class="button changeurllist" role="button" >'+target.content+'</a><span class="icon"><i class="fa fa-times"></i><input required="required"  name="'+target.name+'" value="'+target.id+'" type="hidden">'+other+'</span></li>';
}

var grouplist_content=function(target){
	var other="";
	return '<li onclick="$(this).remove();" class="col-md-6" style="width:86%;"><a class="button changegrouplist" role="button" >'+target.content+'</a><span class="icon"><i class="fa fa-times"></i><input required="required"  name="'+target.name+'" value="'+target.id+'" type="hidden">'+other+'</span></li>';
}
function areaCityChange(_obj){
	var _cityName=$(_obj).attr('rel').split("_")[0];
	var _cityId=$(_obj).attr('rel').split("_")[1];
	var cityHtml='<li onclick="$(this).remove();" class="col-md-6" style="width:66%;"><a class="button changearealist" role="button" >'+_cityName+'</a><span class="icon"><i class="fa fa-times"></i><input required="required"  name="areas" value="'+_cityId+'" type="hidden"></span></li>';
	$("#activity-interest-selected").append(cityHtml);
}

function areaUrlChange(obj){
	var industryId=$(obj).attr('rel').split("_")[1];
	var price = 0;
	data_mm.splice(0,data_mm.length);
	$("input[name='industry']").each(function(){
		var ooo = {};
		if($(this).val()!=undefined){
			ooo.text = $(this).val();
			ooo.industry = industryId;
			data_mm.push(ooo);
		}
	});
	for(var i=0;i<data_medil.length;i++){
		if(data_medil[i].industryId==industryId){
			var medialId = data_medil[i].mediaId;
			var medialName = data_medil[i].text;
			var ii = 0;
			if(data_mm.length>0){
				var bool = true;
				for(var h=0;h<data_mm.length;h++){
					//alert("(data_mm[h].text): "+(data_mm[h].text)+"|| medialId: "+medialId);
					//alert("data_mm[h].text!=medialId: "+(data_mm[h].text!=medialId));
					if((data_mm[h].text)==medialId){
						bool = false;
						break;
					}
				}
				if(bool){
					var medilHtml='<li onclick="$(this).remove();" class="col-md-6" style="width:66%;"><a class="button changeurllist" role="button" >'+medialName+'</a><span class="icon"><i class="fa fa-times"></i><input required="required" name="industry" value="'+medialId+'" type="hidden"></span></li>';
					$("#activity-industry-selected").append(medilHtml);
					price += Number(data_medil[i].mediaPrice);
					ii++
				}
			}else{
				var medilHtml='<li onclick="$(this).remove();" class="col-md-6" style="width:66%;"><a class="button changeurllist" role="button" >'+medialName+'</a><span class="icon"><i class="fa fa-times"></i><input required="required" name="industry" value="'+medialId+'" type="hidden"></span></li>';
				$("#activity-industry-selected").append(medilHtml);
				price += Number(data_medil[i].mediaPrice);
				ii++
			}
		}
		
	}
}

$(function(){
	//星期天时段选择
	$('.changeweektime').click(function(){
		var _weekTime=$(this).html();
		if($.trim(_weekTime)==''){
			$(this).html('<span class="checke_click"></span>');
		}else{
			$(this).html('');
		}
	});
	getAreaList();
	getUrlList();
	getIPSList();
	getGroupList();
	/*setTimeout(function(){getUrlList()},500);
	setTimeout(function(){getIPSList()},1000);*/
	//搜索群组
	$('#searchGroupinput').keyup(function(){
		var _searchName=$(this).val();
		if(_searchName!=''){
			$('.grouplist_change').each(function(i,n){
				var _groupName=$(n).find('h5').html();
				if(_groupName.match(_searchName)){
					$(n).show();
				}else{
					$(n).hide();
				}
			});
		}else{
			$('.grouplist_change').show();
		}
	});
	
	$(".groupalllist").delegate(".clickImg", "click", function(){  
		if($(this).hasClass('selected')){
			$(this).removeClass('selected') 
		}else{
			$(this).addClass('selected')
		}
	});
	//添加定向网址
	$('#addUrlDomainbutton').click(function(){
		var _inputUrl=$('#urlDomainInput_id').val();
		/*var e = /^(?!www)/;
		if(e.test(_inputUrl)){
			alert("网址请以www开头");
			return;
		}*/
		if($.trim(_inputUrl)!=''){
			var _urlhtml='<li onclick="$(this).remove();">\
   				<a role="button" class="button">'+_inputUrl+'</a>\
	   				<span class="icon"><i class="fa fa-times"></i><input type="hidden"  required="required" value="'+_inputUrl+'" name="urlDomain"></span>\
	   			</li>';
			$('.urlDomainlist').append(_urlhtml);
			$('#urlDomainInput_id').val('').focus();
		}else{
			$('#urlDomainInput_id').focus();
		}
	});
	//添加关键字
	$('#addkeywordbutton').click(function(){
		var _inputUrl=$('#keywordInput_id').val();
		if($.trim(_inputUrl)!=''){
			var _urlhtml='<li onclick="$(this).remove();">\
   				<a role="button" class="button">'+_inputUrl+'</a>\
	   				<span class="icon"><i class="fa fa-times"></i><input type="hidden" required="required" value="'+_inputUrl+'" name="kewWord"></span>\
	   			</li>';
			$('.keywordlist').append(_urlhtml);
			$('#keywordInput_id').val('').focus();
		}else{
			$('#keywordInput_id').focus();
		}
	});
	//加载url
	function getUrlList(){
		$.ajax({
			url:'policy/findByIndustry',
			type:'get',
			success:function(data){
				var results=data.result.LIST;
			//读取行业信息
				var data_industry=[];
				var resultN=data.result.MEDIA;
				console.log(resultN);
				for(var i=0;i<results.length;i++){
					var industryId=results[i].industryId;
					//var enName=results[i].enName;
					var enName = '<i rel="'+results[i].enName+'_'+results[i].industryId+'" onclick="areaUrlChange(this)">'+results[i].enName+'</i>';
					var datas=[];
					for(var j=0;j<resultN.length;j++){
						if(industryId == resultN[j].industryId){
							var obje={};
							obje.text=resultN[j].mediaUrl;
							obje.mediaId=resultN[j].mediaId;
							obje.mediaPrice=resultN[j].mediaPrice;
							//datas.push(obje);
						}
					}
					data_industry[i]= {
						text: enName,
					   selectable: false,
					   state: {
						 checked: false,
						 disabled: false,
						 expanded: true,
						 selected: false
					   },
					   nodes:datas
					};
				}
				for (var k=0;k<resultN.length;k++){
					var ob = {};
					ob.mediaId = resultN[k].mediaId;
					ob.text = resultN[k].mediaUrl;
					ob.mediaPrice = resultN[k].mediaPrice;
					ob.industryId = resultN[k].industryId;
					data_medil.push(ob);
				}
				console.log(data_medil);
				var ii=0;
				$('#activity-industry-tree').treeview({
					data:data_industry,
					icon: "glyphicon glyphicon-stop",
					selectedIcon: "glyphicon glyphicon-stop",
					color: "#000000",
					backColor: "#FFFFFF",
					highlightSelected:false,
					showTags:false,
					showIcon:false,
					Expanded:true,
					
					onNodeSelected: function(event, data){
						var obj={'content':data.text,'name':'industry','id':data.mediaId,'other':"selectIndustry"};
						var tree=$("#activity-industry-selected");
						var industry=tree.find('input[name="industry"]');
						var flag=false;
						//当前节点是否已经选择
						$.each(industry,function(i,n){
							if($(n).val()==data.mediaId){
								flag=true;
								return false;
							}
						});
						if(flag){return false}{
							tree.append(urllist_content(obj));
							//	alert(data.mediaPrice);
							var exp=0;
							var price=data.mediaPrice;
							var prices=price*1;
							//	price=price+data.mediaPrice;
							var money= $("#priceUrl").val();
							ii++;
							if(money==null||money==""){
								$("#priceUrl").val(prices);
							}else{
								// $("#priceUrl").val(((money*(ii-1))+prices)/ii);
								$("#priceofUrl").html(((money*(ii-1))+prices)/ii);
							}
						}
					}
				});
			}
		});   
	}


	//加载地区
	function getAreaList(){
		$.ajax({
			url:'policy/sysCityList',
			type:'get',
			success:function(data){
				var datas=[];
				var results=data.result.CITYLIST;
				var provinceId=data.result.provinceId;
				//判断是否是有省id，获取省份名称
				if(provinceId!=null&&provinceId!=""){
					var obj={};
					obj.text=results[1].ProvinceName;
					obj.areaId=provinceId;
					datas.push(obj);
				}
				var data_areas=[];
				//读取市区
				for(var i=0;i<results.length;i++){
					var obj={};
					//var cityName=results[i].CityName;
					var cityName='<i rel="'+results[i].CityName+'_'+results[i].CityId+'" onclick="areaCityChange(this)">'+results[i].CityName+'</i>';
					var _cityId=results[i].CityId;
					var resultN=data.result.CITYDIST;
					var datas=[];
					for(var j=0;j<resultN.length;j++){
						var obje={};
						if(resultN[j].cityId==_cityId){
							obje.text=resultN[j].districtName;
							obje.districtId=resultN[j].cityId+'_'+resultN[j].districtId;
							datas.push(obje);
						}
					}
					data_areas[i]= {
							  text: cityName,
							  selectable: false,
							  state: {
							    checked: false,
							    disabled: false,
							    expanded: true,
							    selected: false
							  },
							  nodes:datas
							};
				}
				
				$('#activity-areas-tree').treeview({
					data:data_areas,
					icon: "glyphicon glyphicon-stop",
				    selectedIcon: "glyphicon glyphicon-stop",
				    color: "#000000",
				    backColor: "#FFFFFF",
					highlightSelected:false,
					showTags:false,
					showIcon:false, 
					Expanded:true,
					onNodeSelected: function(event, data){
						var obj={'content':data.text,'name':'areas','id':data.districtId,'other':"selectAreas"};
						var tree=$("#activity-interest-selected");
						var areas=tree.find('input[name="areas"]');
						var flag=false;
						//当前节点是否已经选择
						$.each(areas,function(i,n){
							if($(n).val()==data.areaId||$(n).val()==data.parentAreaId){
								flag=true;
								return false;
							}
						});
						if(flag){return false}{
							tree.append(keywords_content(obj));
						}
					}
				}); 
			}
		});   
	}

	//加载群组
	function getGroupList(){
		$.ajax({
			url:'policy/searchGroup',
			type:'get',
			success:function(data){
	
				var groupList=data.result.groupLists;
				var group=[];
				for(var k=0;k<groupList.length;k++){
					var obj={};
					 var  groupId=groupList[k].groupId;
					var groupName=groupList[k].groupName; 
					//'<i rel="'+groupList[k].groupName+'_'+groupList[k].groupId;+'" onclick="groupChange(this)">'+groupList[k].groupName+'</i>';
					obj.text=groupName;
					obj.groupId=groupId;
					group.push(obj);
				}
				var  data_groups=[{
						  text: "选择",
						  selectable: false,
						  state: {
						    checked: false,
						    disabled: false,
						    expanded: true,
						    selected: false
						  },
						  nodes: group
						}];
				$('#activity-group-tree').treeview({
					data:data_groups,
					icon: "glyphicon glyphicon-stop",
				    selectedIcon: "glyphicon glyphicon-stop",
				    color: "#000000",
				    backColor: "#FFFFFF",
					highlightSelected:false,
					showTags:false,
					showIcon:false, 
					onNodeSelected: function(event, data){
						var obj={'content':data.text,'name':'group','id':data.groupId,'other':"selectAreas"};
						var tree=$("#activity-groups-selected");
						var group=tree.find('input[name="group"]');
						var flag=false;
						//当前节点是否已经选择
					$.each(group,function(i,n){
							if($(n).val()==data.groupId){
								flag=true;
								return false;
							}
						});
						if(flag){return false}{
							tree.append(grouplist_content(obj));
						}
					}
				}); 
			}
		});   
	}
	
	
	//加载IPS
	function getIPSList(){
		$.ajax({
			url:'policy/sysIpsList',
			type:'get',
			success:function(data){
				var resultCity = data.result.IPSCITY;
				var data_citys=[];
				if(resultCity.length<1){
					return ;
				}
				for(var i=0;i<resultCity.length;i++){
					var datas=[];
					var obj={};
					var cityName=resultCity[i].cityName;
					var city_Id = resultCity[i].cityId;
					var resultIPS = data.result.IPSLIST;
					for(var k=0;k<resultIPS.length;k++){
						if ((Number(resultIPS[k].cityId)) == Number(city_Id)){
							//alert("Number(resultIPS[k].cityId:"+Number(resultIPS[k].cityId)+",Number(resultCity[i].cityId:"+Number(city_Id));
							var obje={};
							obje.text = resultIPS[k].ipsName;
							obje.ipsId = resultIPS[k].ipsId;
							datas.push(obje);
						}
						//console.log(datas);
					}
					data_citys[i]= {
						text: cityName,
						selectable: false,
						state: {
							checked: false,
							disabled: false,
							expanded: true,
							selected: false
						},
						nodes:datas
					};
				}
				$('#activity-ips-tree').treeview({
					data:data_citys,
					icon: "glyphicon glyphicon-stop",
					selectedIcon: "glyphicon glyphicon-stop",
					color: "#000000",
					backColor: "#FFFFFF",
					highlightSelected:false,
					showTags:false,
					showIcon:false,
					Expanded:true,
					onNodeSelected: function(event, data){
						var obj={'content':data.text,'name':'ips','id':data.ipsId,'other':"selectIps"};
						var tree=$("#activity-ips-selected");
						var ips=tree.find('input[name="ips"]');
						var flag=false;
						//当前节点是否已经选择
						$.each(ips,function(i,n){
							if($(n).val()==data.ipsId){
								flag=true;
								return false;
							}
						});
						if(flag){return false}{
							tree.append(ips_content(obj));
						}
					}
				});
			}
		});
	}

});