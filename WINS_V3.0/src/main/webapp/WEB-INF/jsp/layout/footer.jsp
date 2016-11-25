<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="./taglib.jsp"></jsp:include>
<!-- JQUERY -->
<script src="js/jquery/jquery-2.0.3.min.js"></script>
<!-- JQUERY UI-->
<script
	src="js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<!-- BOOTSTRAP -->
<script src="bootstrap-dist/js/bootstrap.min.js"></script>
<!-- LESS CSS -->
<script src="js/lesscss/less-1.4.1.min.js" type="text/javascript"></script>
<!-- DATE RANGE PICKER -->
<script type="text/javascript" src="js/moment.js"></script>
<script type="text/javascript" src="js/daterangepicker.js"></script>
<!-- SLIMSCROLL -->
<script type="text/javascript"
	src="js/jQuery-slimScroll-1.3.0/jquery.slimscroll.min.js"></script>
<script type="text/javascript"
	src="js/jQuery-slimScroll-1.3.0/slimScrollHorizontal.min.js"></script>
<!-- BLOCK UI -->
<script type="text/javascript"
	src="js/jQuery-BlockUI/jquery.blockUI.min.js"></script>
<!-- UNIFORM -->
<script type="text/javascript" src="js/uniform/jquery.uniform.min.js"></script>
<!-- BOOTSTRAP WYSIWYG -->
<script type="text/javascript"
	src="js/bootstrap-wysiwyg/jquery.hotkeys.min.js"></script>
<script type="text/javascript"
	src="js/bootstrap-wysiwyg/bootstrap-wysiwyg.min.js"></script>
<!-- COOKIE -->
<script type="text/javascript"
	src="js/jQuery-Cookie/jquery.cookie.min.js"></script>
<!-- CUSTOM SCRIPT -->
<script src="js/script.js"></script>
<script src="js/inbox.js"></script>
<script src="js/Popt.js"></script>
<script src="js/cityJson.js"></script>
<script src="js/citySet.js"></script>
<script src="js/highcharts/highcharts.js"></script>

<script>
    var loadimage = '<div id="main-content"  class="margin-top-50"><div id="content" style="text-align: center;padding-top: 298px;"><img src="img/loaders/4.gif"></div></div>';
		jQuery(document).ready(function() {	
			var _reload_url=(!window.location.hash)?"survey.html":window.location.hash.substr(1);
			$("#main-div").load(_reload_url,function(data){
				//$(this).html(data);
				//App.init();
				handleSidebarCollapse();
			});
			$("#sidebar").delegate("a","click",function(){
				if(!$(this).parent().hasClass("has-sub")){
					$("#sidebar").find('.active').removeClass('active');
					$(this).parent().addClass('active');
					var reload = $(this).attr('href');
					if(reload!=null && reload!="javascript:;" && reload!=""){
						_reload_url=reload.replace(/#/i,"");
						$("#main-div").html(loadimage).load(_reload_url,function(data){
							//$(this).html(data);
							//App.init();
							handleSidebarCollapse();
						});
					}
				}
					
			});
		});
		function load_page(_reload_url,target,callback){
			var id=typeof(target)!='undefined'?target:'#main-div';
			
			$(id).load(_reload_url,function(data){
				//App.init();
				handleSidebarCollapse();
				if(typeof(callback)=='function'){
					//$(id).html(data);
					
					callback.call(this,data);
				}
			});
		};
	</script>
<script>
		jQuery(document).ready(function() {		
			App.setPage("fixed_header_sidebar");  //Set current page
			App.init(); //Initialise plugins and elements
		});
		
		var handleSidebarCollapse = function () {
       
		//Handle sidebar collapse on screen width
		var width = $(window).width();
		if ( width < 768 ) {
			is_mobile = true;
			//Hide the sidebar completely
			jQuery('#main-content').addClass("margin-left-0").addClass('margin-top-100');
		}else {
			is_mobile = false;
			//Show the sidebar completely
			jQuery('#main-content').removeClass("margin-left-0").removeClass("margin-left-100");
		
		}
	}
	</script>
