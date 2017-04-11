<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@include file="./validatelogin.jsp" %>
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
    <div class="container main">
    <c:if test="${tip!=null}"><div class="alert alert-success" role="alert">${tip }</div></c:if>
  
        <div class="row">
        
          <div class="col-md-3 col-lg-3">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                         <span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
                       		  个人中心
                        </h3>
                    </div>
                    <ul class="list-group">
                      
                         <li class="list-group-item problem-tag">
                                <a href="${pageContext.request.contextPath}/front/center" >照片上传 </a>
                            </li>
                          <li class="list-group-item problem-tag">
                               <a  href="${pageContext.request.contextPath}/front/mypic" >我上传的照片 </a>
                            </li>
                            
                       <li class="list-group-item problem-tag">
                               <a  href="${pageContext.request.contextPath}/front/myinfo"  >个人资料 </a>
                            </li>
                          
                    </ul>
                </div>
            </div>
            <div class="col-md-9 col-lg-9  ">
            
            
              <div class="panel panel-info" style="min-height: 500px">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                         <span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
                       		  我的资料
                        </h3>
                    </div>
                     
                     <div class="panel-body">
	                     <div class='row'>
	                      <div class="col-sm-2 "  style="text-align: right;" >	 <label>用户名</label></div>
	                        <div class="col-sm-10">
								    	 ${user.name }
							</div>
	                     </div>
						
						<div class='row'>
	                      <div class="col-sm-2 "  style="text-align: right;" >	 <label>昵称</label></div>
	                        <div class="col-sm-10">
								    	 ${user.nickname }
							</div>
	                     </div>
	                     
	                     <div class='row'>
	                      <div class="col-sm-2 "  style="text-align: right;" >	 <label>地址</label></div>
	                        <div class="col-sm-10">
								    	 ${user.address }
							</div>
	                     </div>
	                     
	                       <div class='row'>
	                      <div class="col-sm-2 "  style="text-align: right;" >	 <label>邮箱</label></div>
	                        <div class="col-sm-10">
								    	 ${user.email }
							</div>
	                     </div>
	                     
                </div>
                
                
                </div>
                
                
                
                
            </div>

          
        </div>
    </div>
    <input type="hidden" id="categoryid">
     <%@include file="./foot.jsp" %>
</body>
</html>