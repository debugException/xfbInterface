<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/reset.css"/>
<link rel="stylesheet" type="text/css" href="css/common.css?t=1"/>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<title>${video.videoTitle}</title>
</head>
<body>
	<div class="detail_head">
    	<img src="images/aikan_logo.png" class="detail_head_logo">
        <div class="detail_head_word">
       	  <h1>爱看游戏</h1>
          <p>更高清流畅、直播免费、更多精彩视频、独家内容</p>
        </div>
        <button value="" name="" onclick="download()" class="head_button_download">下载</button>
    </div>
    <div class="video_block">
    	<video id="video_player" width="480" height="300" controls="controls" src="${playUrl}">  
                Your browser does not support the video tag.
		</video>
		<img src="${video.videoImage }" style="width:480px;height:300px;top:90px;left:50%;position:absolute;margin-left:-240px;"/>
	</div>
	<div class="detail_related">
    	<h1>
        	<span class="related_selected">相关</span>
            <span>详情</span>
        </h1>
        <div class="detail_related_block">
        	<div class="related_block">
           	  <ul class="video_list">
           	  <c:forEach items="${videoList}" var="item">
                <li>
                    <img height="125" width="100%" src="${item.videoImage }">
                    <a href="sharevideo.do?videoId=${item.videoID }">${item.videoTitle }</a>
                    <p>播放：${item.videoPvs }</p>
                </li>
                </c:forEach>
                </ul>
                <div style="clear:both;"></div>
            </div>
          <div class="detail_block">
            	${video.videoDescription }
          </div>
        </div>
    </div>
	<div class="footer">
        <button value="" name="" onclick="download()" class="btn_boxdown">下载客户端  发现更多精彩</button>
        <p class="copyright">Copyright©2014 iseegame. All Rights Reserved</p>
    </div>
<script>
$(document).ready(function(){
	$(".detail_related > h1 > span").click(function(){
		$(this).addClass("related_selected").siblings().removeClass();
		$(".detail_related_block > div").hide().eq($(".detail_related span").index(this)).show();
	})	
})
function download(){
	if(isWeiXin()){
		alert("在微信中下载功能可能会受到影响，请点击右上角按钮，用浏览器打开！");
	} else {
		if(isAndroid()){
			window.location.href = "http://inf.joyseed.cn:9000/AKYXHttpInterface/download/android";
		}else if(isIOS()){
			window.location.href = "https://itunes.apple.com/cn/app/id946046809?mt=8";
		}else{
			alert("很抱歉，您的操作系统暂时没有支持的【爱看游戏】客户端！");
		}
	}
}
function isWeiXin(){
	var userAgentInfo = navigator.userAgent;
	var Agents = ["MicroMessenger"];
    var flag = false;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = true;
            break;
        }
    }
    return flag;
}
function isAndroid() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android"];
    var flag = false;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = true;
            break;
        }
    }
    return flag;
}
function isIOS() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["iPhone", "iPad", "iPod"];
    var flag = false;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = true;
            break;
        }
    }
    return flag;
}
</script>
</body>
</html>
