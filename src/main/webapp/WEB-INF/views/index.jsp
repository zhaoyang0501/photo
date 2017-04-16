<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html lang="zh-cn">
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Smart - UI Elements</title>

		<!-- Bootstrap -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
		<!-- DataTables http://www.datatables.net/  -->
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/ace.js"></script>
		<script src="${pageContext.request.contextPath}/js/problem.js"></script>
		<script src="${pageContext.request.contextPath}/admin/js/falgun/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/admin/js/falgun/dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/admin/js/falgun/TableTools.min.js"></script>
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
		$.ace.setContextPath('${pageContext.request.contextPath}');
		$.adminProblem.initSearchDataTable();
	});
</script>
	</head>
<body>
 <%@include file="./header.jsp" %>
<!-- custom body begin -->
 <div class="container">
  <div class="row">
    <div class="col-sm-12"> <img class="img-responsive" src="${pageContext.request.contextPath}/images/banner.jpg"> </div>
  </div>
</div>


<div class="container">
  <div class="page-header">
    <div class="row">
      <div class="col-sm-4">
        <h2>Welcome!</h2>
        <p>现在就加入我们的网站，贡献你的图片素材！</p>
        <p><a class="btn btn-primary pull-right" href="login.html">登录</a></p>
      </div>
      <div class="col-sm-4">
        <h2>Recent Update!</h2>
        <p>看看其他人上传的精彩作品！</p>
        <p><a class="btn btn-primary pull-right" href="picwall.html">照片墙</a></p>
      </div>
      <div class="col-sm-4">
        <h2>Contact Us!</h2>
        <p>了解我们的项目信息，加入我们的团队！</p>
        <p><a class="btn btn-primary pull-right" href="information.html">联系我们</a></p>
      </div>
    </div>
  </div>
</div>

    <input type="hidden" id="categoryid">
     <%@include file="./foot.jsp" %>
</body>
</html>