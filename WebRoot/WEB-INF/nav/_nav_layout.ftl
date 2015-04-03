<#macro layout info>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${info.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="网址导航"/>
    <meta name="description" content="网址导航@在线工具" />

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/layout.css" rel="stylesheet">
    <link href="/assets/nav/nav_layout.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="/assets/ext/html5shiv.js"></script>
    <script src="/assets/ext/respond.min.js"></script>
    <![endif]-->
    <#-- base href="${CPATH}" / -->
        <#include "/WEB-INF/comm/_baidu_tongji.ftl"/>
</head>
<body>


<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#top-navbar-collapse">
            <span class="sr-only">切换导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/">在线工具</a>
    </div>
    <div class="collapse navbar-collapse" id="top-navbar-collapse">
        <ul class="nav navbar-nav">
            <#include "/WEB-INF/nav/_top_navs.ftl"/>
        </ul>
    </div>
</nav>
<!-- 顶部菜单结束 -->


<div class="container" >
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12" >
            <#nested>
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


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/assets/ext/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/ext/_ext.js"></script>
</body>
</html>
</#macro>