<%@page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=0.75">
<link rel="stylesheet" type="text/css" href="css/reset.css"/>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<title>${livePlayPage.scheduleName }</title>
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
    	<video id="video_player" width="450" height="300" controls="controls" src="${playUrl}">  
                Your browser does not support the video tag.
		</video>
    </div>
	<div class="comment_block">
		<h1>评论</h1>
		<ul>
			<c:forEach items="${listComment}" var="item">
        	<li>
            	<p>${item.liveComment }</p>
                <div class="comment_bottom">
                	<span class="comment_num">${item.customerName }</span>
                    <span class="comment_time">${item.createTime }</span>
                </div>
            </li>
            </c:forEach>
        </ul>
	</div>
	<div class="footer">
        <button value="" name="" onclick="download()" class="btn_boxdown">下载/打开客户端  发现更多精彩</button>
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
		alert("在微信中下载功能可能会受到影响，请点击右上角按钮，用浏览器打开！");
		window.location = "download/android";
	}
	</script>
</body>
</html>
