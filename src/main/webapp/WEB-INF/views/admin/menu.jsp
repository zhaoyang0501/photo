<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="leftbar leftbar-close clearfix">
	<div class="admin-info clearfix">
		<div class="admin-thumb">
			<i class="icon-user"></i>
		</div>
		<div class="admin-meta">
			<ul>
				<li class="admin-username" style="margin-top: 10px;">欢迎你 ${sessionScope.adminuser.name}</li>
				<li><a href="${pageContext.request.contextPath}/admin/loginout">
				<i class="icon-lock"></i> 退出</a></li>
			</ul>
		</div>
	</div>

	<div class="left-nav clearfix">
		<div class="left-primary-nav">
			<ul id="myTab">
				<li  class="active"><a href="#dailyreport" class="icon-calendar" data-original-title="日报"></a></li>
			</ul>
		</div>
		<div class="responsive-leftbar">
			<i class="icon-list"></i>
		</div>
		<div class="left-secondary-nav tab-content" >
			<div class="tab-pane active dailyreport" id="dailyreport">
				<ul id="nav" class="accordion-nav" >
			
				<li><a href="${pageContext.request.contextPath}/admin/photo/index"><i class="icon-zoom-in"></i>照片审核</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/category/index"><i class="icon-upload"></i>标签管理 </a></li>
				<li><a href="${pageContext.request.contextPath}/admin/adminuser/index"><i class="icon-zoom-in"></i>管理员管理</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/user/index"><i class="icon-zoom-in"></i>注册用户管理</a></li>
		
				<c:if test="${sessionScope.adminuser.role6=='yes'}">
				 <!--  <li><a href="${pageContext.request.contextPath}/admin/adminuser/index"><i class="icon-zoom-in"></i>管理员维护</a></li>-->
				</c:if>	
				
				
				</ul>
			</div>
		</div>
	</div>
</div>