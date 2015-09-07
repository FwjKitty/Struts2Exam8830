<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<title>添加客户</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
</head>
<body>
	<div class="container-fuild">
		<div class="row">
			<div class="col-sm-12" style="background-color:#eee;">
				<div class="col-sm-11">
					<h3>1507A05冯婉君</h3>
				</div>
				<div class="col-sm-1">
					<h3>
						${first_name}
						<a href="<%=request.getContextPath() %>/customer/exit.action">
							<button class="btn btn-danger">退出系统</button>
						</a>
					</h3>
				</div>
			</div>
			<div class="col-sm-12">
				<div class="col-sm-2">
					<h4><a href="">Dashboard</a></h4>
					<h4><a href="">应用设置</a></h4>
					<h4><a href="">管理设置</a></h4>
					<h4><a href="">数据配置</a></h4>
					<h4><a href="">主数据管理</a></h4>
					<h4><a href="">客户订单管理</a></h4>
					<h4><a href="">发货单管理</a></h4>
				</div>
				<div class="col-sm-10">
					<h1>修改Customer</h1>
					<hr/>
					<div class="col-sm-12" style="background-color:#fff;">
			  			<form class="form-horizontal" role="form" action="<%=request.getContextPath() %>/customer/Customer_update.action" method="post">
			  				<input type="hidden" name="customer.customer_id" value="${customer.customer_id}"/>
			  				<br/>
					        <strong>基本信息</strong>
					        <hr/>
					        <div class="form-group">
					            <label class="col-sm-1 control-label">First Name</label>
					            <div class="col-sm-4">
					                <input type="text" name="customer.first_name" class="form-control" placeholder="${customer.first_name}" value="${customer.first_name}"/>
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-1 control-label">Last Name</label>
					            <div class="col-sm-4">
					                <input type="text" name="customer.last_name" class="form-control" value="${customer.last_name}" placeholder="${customer.last_name}" />
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-1 control-label">Email</label>
					            <div class="col-sm-4">
					                <input type="text" name="customer.email" class="form-control" value="${customer.email}" placeholder="${customer.email}" />
					            </div>
					        </div>
					        <div class="form-group">
					            <label class="col-sm-1 control-label">Address</label>
					            <div class="col-sm-4">
					                <s:select list="#request.list" name="customer.address_id" headerValue="" headerKey="#customer.address_id" listValue="address" listKey="address_id" value="bean.address"/>
					            </div>
					        </div>
					        <div class="form-group">
					            <div class="col-sm-offset-1 col-sm-4">
				                	<button type="submit" class="btn btn-primary">更新
				                	</button>
					                <button class="btn btn-default" onclick="history.go(-1);">取消
					                </button>
					                <span>${msg}</span>
					            </div>
					        </div>
					    </form>
			  		</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>