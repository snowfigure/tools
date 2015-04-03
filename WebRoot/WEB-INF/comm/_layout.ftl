<#macro layout info>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>在线工具-${info.title}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="在线工具,加密/解密,对照表,便民,身份证"/>
	<meta name="description" content="在线工具,为开发人员、网民提供对照表、加密转码、便民工具等在线工具" />
	
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link href="/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="/assets/layout.css" rel="stylesheet">
	<!--[if lt IE 9]>
       <script src="/assets/ext/html5shiv.js"></script>
       <script src="/assets/ext/respond.min.js"></script>
    <![endif]-->
    <#-- base href="${CPATH}" / -->
    <#include "/WEB-INF/comm/_baidu_tongji.ftl"/>
</head>
<body>
	<a href="top"></a>
	<!-- 顶部菜单 开始 -->
	<nav class="navbar navbar-default navbar-inverse" role="navigation">
		<div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" 
	         data-target="#top-navbar-collapse">
	         <span class="sr-only">切换导航</span>
	         <span class="icon-bar"></span>
	         <span class="icon-bar"></span>
	         <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="/"style="font-weight: bold">在线工具</a>
	   </div>
		<div class="collapse navbar-collapse" id="top-navbar-collapse">
		      <ul class="nav navbar-nav">
					<#include "/WEB-INF/comm/_navs.ftl"/>
		      </ul>
		 </div>
	</nav>
	<!-- 顶部菜单结束 -->
	
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<#nested>
				
			</div>
			<div class="col-xs-12 col-sm-12 col-md-12">
			
				<div class="panel panel-default">
					<div class="panel-heading">
						留言板
					</div>
					<div class="panel-body">
						<!-- 多说评论框 start -->
						<div class="ds-thread" 
							data-thread-key="${info.keyid}" 
							data-title="${info.title}" 
							data-url="http://tools.791211.com/${info.suburl}"></div>
						<!-- 多说评论框 end -->
						<#include "/WEB-INF/comm/_duoshuo.ftl"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse" role="navigation">
		<div class="col-xs-12 col-sm-5 col-md-4 navbar-text">
			&copy 2014 <a href='#'>在线工具</a>|
			<a target="_blank" href='http://www.791211.com'>博客</a>|
			<a target="_blank" href='http://weibo.com/sfworkingroom'>微博</a>
		</div>
		<div class="hidden-xs  col-sm-6 col-md-5  navbar-text">

           运行在
		     <a target="_blank" href='http://www.aliyun.com'>阿里云</a> ·
            技术支持 <a target="_blank" href='http://jfinal.com'>JFinal</a> &
			 <a target="_blank" href='http://www.bootcss.com'>BootStrap</a>
		</div>
		<div class="hidden-xs hidden-sm navbar-text">
			苏ICP备14019371号-2			
		</div>
	</nav>
	<!-- 
	<div class="col-xs-12 col-sm-1 col-md-1" style="bottom: 1px;right: 1px;position: absolute;z-index: 99999">
		<a class="btn btn-primary" href="#top" >top</a>
	</div>
	 -->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="/assets/ext/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="/assets/ext/_ext.js"></script>

<script type="text/javascript">
	$(function(){
		$('#${info.topurl}').addClass('active');
	});
</script>
</body>
</html>
</#macro>