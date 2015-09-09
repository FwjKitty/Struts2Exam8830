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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
</head>
<script type="text/javascript">
function getPage(page) {
	$("#msg").text("");
    $.ajax({
        type: "post",
        url: "<%=request.getContextPath() %>/customer/Customer_getPageResult.action",
        dataType: "json",
        data : {"page" : page},
        timeout: 5000,
        error: function(status){
        	alert(status);
        },
        success: function(list){
        	var nextpage ;
            var lastpage ;
            var finalpage="${fn:substringBefore((count-count%10)/10+1, '.')}";
            if(page==1){
            	lastpage=1;
            }else lastpage=page-1;
            if(finalpage==page){
            	nextpage=page;
            }else nextpage=page+1;
        	$("#result").text("");
        	$("#pageFooter").text("");
    		var _tbody = "";
    		var _tfooter = "";
    		//遍历json数组方法1
    		$.each(list,function(n, customer){
                	_tbody += "<tr><td><a href='Customer_edit.action?customer.customer_id="+customer.customer_id+"'>编辑</a> | <a href='Customer_del.action?customer.customer_id="+customer.customer_id+"'>删除</a></td>"
                		+"<td>"+customer.first_name+"</td>"
                		+"<td>"+customer.last_name+"</td>"
                		+"<td>"+customer.address+"</td>"
                		+"<td>"+customer.email+"</td>"
                		+"<td>"+customer.customer_id+"</td>"
                		+"<td>"+customer.last_update.year+"-"+customer.last_update.month+"-"+customer.last_update.day+" "+customer.last_update.day+":"+customer.last_update.minutes+":"+customer.last_update.seconds+"</td></tr>";
    		});
    		_tfooter = "<button onclick='getPage("+lastpage+")' class='btn btn-default'><<</button>"
				+"<button onclick='getPage(1)' class='btn btn-default'>first</button>"
				+"<button onclick='getPage("+finalpage+")' class='btn btn-default'>last</button>"
				+"<button onclick='getPage("+nextpage+")' class='btn btn-default'>>></button>";
    		$("#result").append(_tbody);
    		$("#pageFooter").append(_tfooter);
        },
        complete: function(XMLHttpRequest, textStatus, errorThrown){
            alert(textStatus);
       }
    });
    <%-- $("#result").load("<%=request.getContextPath() %>/customer/Customer_getPageResult.action",function(list,status){
		//alert(list);
		var _tbody = "";
		//遍历json数组方法1
		$.each(list,function(n, customer){
            alert(n + ',' + customer);
            _tbody += "<tr><td>"+customer.first_name+"</td><td>"+customer.last_name+"</td><td>"+customer.address+  
            "</td><td>"+customer.email+"</td><td>"+customer.customer_id+"</td><td>"+customer.last_update+"</td></tr>";
        });
		$("#result").append(_tbody);
		
		//遍历json对象
		/* for(var i=0,l=json.length;i<l;i++){
		    for(var key in json[i]){
		        alert(key+':'+json[i][key]);
		    }
		 }*/
		//遍历json对象
		/* for(var p in obj){
		    str = str+obj[p]+',';
		    return str;
		} */
      }); --%>
}
</script>
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
							<tbody id="result">
							<s:iterator value="#request.list" id="customer">
								<tr>
									<td><a href="<%=request.getContextPath() %>/customer/Customer_edit.action?customer.customer_id=${customer.customer_id}">编辑</a> | <a href="<%=request.getContextPath() %>/customer/Customer_del.action?customer.customer_id=${customer.customer_id}">删除</a></td>
									<td><s:property value="#customer.first_name"/></td>
									<td><s:property value="#customer.last_name"/></td>
									<td><s:property value="#customer.address"/></td>
									<td><s:property value="#customer.email"/></td>
									<td><s:property value="#customer.customer_id"/></td>
									<td><s:date name="#customer.last_update"/></td>
								</tr>
							</s:iterator>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4" id="msg">${msg}</td>
									<td colspan="3">
									<div class="pull-right" id="pageFooter">
									<s:if test="page+1-1==1">
										<button onclick="getPage(1)" class="btn btn-default"><<</button>
									</s:if>
									<s:else>
										<button onclick="getPage(${page-1})" class="btn btn-default"><<</button>
									</s:else>
										<button onclick="getPage(1)" class="btn btn-default">first</button>
										<button onclick="getPage(${fn:substringBefore((count-count%10)/10+1, '.')})" class="btn btn-default">last</button>
										<button onclick="getPage(${page+1})" class="btn btn-default">>></button>
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