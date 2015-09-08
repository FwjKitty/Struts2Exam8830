<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.min.css" />
</head>
<body>
	<div class="container-fuild">
		<div class="row">
			<div class="col-sm-12" style="background-color:#eee;">
				<div class="col-sm-11">
					<h3>8830冯婉君</h3>
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
					<h4><a href="CustomerShow?page=1">Customer管理</a></h4>
					<h4><a href="">Film设置</a></h4>
				</div>
				<div class="col-sm-10">
					<h1>客户管理</h1>
					<hr/>
					<div class="col-sm-12">
						<div class="col-sm-4">
							客户列表
						</div>
						<div class="col-sm-4">
							<a href="<%=request.getContextPath() %>/customer/Customer_add.action"><button class="btn btn-primary">新建</button></a>
							<br/><br/>
						</div>
					</div>
					<div class="col-sm-12">
						<table class="table table-stripeed table-bordered table-hover">
							<thead>
								<tr>
									<th>操作</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Address</th>
									<th>Email</th>
									<th>CustomerId</th>
									<th>LastUpdate</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="#request.list" id="customer">
								<tr>
									<td><a href="<%=request.getContextPath() %>/customer/Customer_edit.action?customer.customer_id=${customer.customer_id}">编辑</a> | <a href="<%=request.getContextPath() %>/customer/Customer_del.action?customer.customer_id=${customer.customer_id}">删除</a>
									<td><s:property value="#customer.first_name"/></td>
									<td><s:property value="#customer.last_name"/></td>
									<td><s:property value="#customer.address"/></td>
									<td><s:property value="#customer.email"/></td>
									<td><s:property value="#customer.customer_id"/></td>
									<td><s:property value="#customer.last_update"/></td>
								</tr>
							</s:iterator>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4">${msg}</td>
									<td colspan="3">
									<div class="pull-right">
									<s:if test="page==1">
										<a href="<%=request.getContextPath() %>/customer/Customer_show.action?page=1" class="btn btn-default"><<</a>
									</s:if>
									<s:elseif test="page!=1">
										<a href="<%=request.getContextPath() %>/customer/Customer_show.action?page=${page-1}" class="btn btn-default"><<</a>
									</s:elseif>
										<a href="<%=request.getContextPath() %>/customer/Customer_show.action?page=1" class="btn btn-default">first</a>
										<a href="<%=request.getContextPath() %>/customer/Customer_show.action?page=${fn:substringBefore((count-count%10)/10+1, '.')}" class="btn btn-default">last</a>
										<a href="<%=request.getContextPath() %>/customer/Customer_show.action?page=${page+1}" class="btn btn-default">>></a>
									</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>