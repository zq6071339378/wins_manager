<%--
  Created by IntelliJ IDEA.
  User: yangxiongbin
  Date: 2016/7/6
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
progress {
	border-radius: 2px;
	border-left: 1px #ccc solid;
	border-right: 1px #ccc solid;
	border-top: 1px #aaa solid;
	background-color: #eee;
}

progress::-webkit-progress-bar {
	background-color: #EEEEEE;
}

progress::-webkit-progress-value {
	background-color: chartreuse;
}
</style>
<meta charset="UTF-8">
<title>Policy synchronization</title>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<hr />
	<br />
	<input type="button" value="同步" onclick="synchPolicy()" />
	<p id="ptip">Start synchronization</p>
	<br />
	<progress value="0" max="100" id="test"></progress>
	<script type="text/javascript">
    var maxVal = $("#test").attr("max");
    var urlInfo = window.location.href;
    $(function(){
        checkSynch();
    });
    var int=self.setInterval("checkSynch()",10000);
    function checkSynch(){
        $.ajax({
            url:"synchStatus",
            type:"post",
            dataType:"json",
            success:function(data){
                var synchStatus = data.result.synchStatus;
                var lastStatus = data.result.lastStatus;
                if(lastStatus>=synchStatus){
                    $("#test").attr("value",lastStatus);
                }else{
                    $("#test").attr("value",synchStatus);
                }
                if(synchStatus >= maxVal){
                    clearInterval(int);
                }
            }
        });
    }

    function synchPolicy(){
        $.ajax({
            url:"synchronizePolicy",
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.code!=1){
                    window.confirm(data.desc);
                }else{
                    if(urlInfo.indexOf("#")!=-1){
                        urlInfo = urlInfo.substring(urlInfo.indexOf("#") + 1);
                    }
                    //load_page(urlInfo);
                    window.location=urlInfo;
                    alert("Refresh the page");
                }
            },
            error: function () {
                alert("A synchronous request exceptions");
            }
        });
    }


</script>

	<%--<script type="text/javascript">


    var intValue = 0;
    var intTimer;
    var objPro = document.getElementById('pro');
    var objTip = document.getElementById('ptip');
    function Interval_handler(){
        intValue ++;
        objPro.value=intValue;
        if(intValue>=objPro.max){
            clearInterval(intTimer)
            objTip.innerHTML="同步完成";
        }else{
            objTip.innerHTML="正在同步"+intValue+"%";
        }
    }

    function Btn_Click(){
        intTimer = setInterval(Interval_handler,300);
    }

</script>--%>
</body>
</html>
