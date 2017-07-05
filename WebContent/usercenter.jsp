<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta property="wb:webmaster" content="a4f355093baa17ab" />

<link rel="stylesheet" type="text/css"
	href="static/css/yiche_2015_user-center_commons_bbs_style-20150806143402-935.css"
	media="all" />

<title>
	用户中心
</title>
</head>
<body>
<span id="yicheAnchor" name="yicheAnchor"
		style="display: block; height: 0; width: 0; line-height: 0; font-size: 0"></span>
	<div class="mbt-page">
		<!-- header start -->
		<script type="text/javascript"
			src="http://image.bitautoimg.com/bsearch/mobilesug201506/showsearchbox.js"></script>
		<script type="text/javascript">
			function toogleNav() {
				var navE = document.getElementById("nav");
				var popNavE = document.getElementById("popNav");
				if (navE.className == "btn-nav") {
					navE.className = "btn-nav btn-nav-show";
					popNavE.style.display = "block";
					if (document.getElementById('popShare') != null) {
						document.getElementById('popShare').style.display = "none";
						document.getElementById('share').className = 'b-news-share';
					}
				} else {
					navE.className = "btn-nav";
					popNavE.style.display = "none";
				}
			}
			function closeNav() {
				var navE = document.getElementById("nav");
				var popNavE = document.getElementById("popNav");
				navE.className = "btn-nav";
				popNavE.style.display = "none";
			}
		</script>
		<script type="text/javascript"
			src="http://image.bitautoimg.com/uimg/wap/js/jquery-1.8.0.min.js"></script>
		<!-- header end -->
		<div class="clear"></div>
		<!--顶部 end-->

		<div class="uc-index">
			<!--内容 Begin-->
			<div class="user-center">
				<img
					src="static/bg.jpg" height="500px;" width="100%"
					alt="" />
				<!--用户信息 Begin-->

				<div class="content">
					<div class="face-box">
						<div class="img-box">
							<img src="http://pic.baa.bitautotech.com/newavatar/120.jpg"
								alt="${loginInfo.account}" />
						</div>

					</div>
					<h3>${loginInfo.account}</h3>
					<div class="cols-2">
						<div class="txt-right">地址：${loginInfo.location}</div>
					</div>

				</div>

				<!--用户信息 End-->
			</div>
			<div id="guessContainer" class="uc-news-list"></div>
			<!--内容 End-->
			<div class="wrap">
				<a
					href="index.html"
					class="btn-one btn-gray">退出登录</a>
			</div>
		</div>
		
		
</body>
</html>