<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-ajax-request-global.js"></script>

</head>
<script>
	jQuery(function ($) {
		// 函数： 发送ajax请求 获得字典类型列表 再插入页面
		let loadTable = function(){
			$("#select_all").prop("checked", false);
			$.post("/type/queryAll", function (result) {
				// 局部刷新列表数据
				let htmlArr = [];
				$(result).each(function (index) {
					let activeStyle = index % 2 === 0 ? 'class="active"' : '';
					htmlArr.push(
							'<tr '+activeStyle+'>\
						<td><input type="checkbox" name="type" value="'+this.code+'" /> </td>\
						<td>'+(index+1)+'</td>\
						<td>'+this.code+'</td>\
						<td>'+this.name+'</td>\
						<td>'+this.description+'</td>\
						</tr>'
					)
				});
				$("#table_body").html(htmlArr.join(""));
			}, "json");
		};

		// 每次加载完页面 刷新所有数据
		loadTable();

		// 为全选框绑定事件
		$("#select_all").on("click", function () {
			$(":checkbox[name=type]").prop("checked", this.checked);
		});

		// 不能直接对每一行的复选框绑定事件 因为ajax请求异步的
		// 需要进行事件委派
		$("#table_body").on("click", ":checkbox[name=type]", function () {
			let $chk = $(":checkbox[name=type]");
			$("#select_all").prop("checked", $chk.size() === $chk.filter(":checked").size());
		})

		// 为删除按钮绑定事件
		$("#del_button").click(function () {
			// 获取勾选了的复选框对象
			let $checked = $(":checkbox[name=type]:checked");
			// 如果未勾选 做提示
			if ($checked.size() === 0){
				alert("请选择删除对象");
				return;
			}
			// 确认操作
			if ( !confirm("确认删除选中项吗？") ) return;

			// 拼接请求的url
			let codeArr = [];
			$checked.each(function () {
				codeArr.push("code="+this.value);
				// console.log(codeArr.join("&"));
			});
			// 发送post请求
			$.post("/type/deleteById", codeArr.join("&"), function (result) {
				// expected response data : success:... msg:...(when exception happens)
				// 如果后台处理过程中发生异常 那么异常处理器会让重定向重定向至错误页面 此时
				// 请求无法处理html代码 需要在异常处理器中分类处理

				if (result.success) {
					loadTable();
				}
			}, "json");

		});

		// 点击编辑按钮时 将要编辑的记录的code传入
		$("#update_button").click(function () {
			// 选择一个或者多个选项 默认只传第一项
		   let $toedit = $(":checkbox[name=type]:checked:first");
		   if ($toedit.size() === 0){
		   		alert("请选择要编辑的项");
		   		return;
		   }
		   location = "edit.html?code=" + $toedit.val();


		});

	});





</script>






<body style="margin: 10px;">

	<div>
		<div style="position: relative;  top: -10px;">
			<div class="page-header">
				<h3>字典类型列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button id="add_button" type="button" class="btn btn-primary" onclick="window.location.href='save.html'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button id="update_button" type="button" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button id="del_button" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" id="select_all" /></td>
					<td>序号</td>
					<td>字典</td>
					<td>名称</td>
					<td>描述</td>
				</tr>
			</thead>
			<tbody id="table_body">
			</tbody>
		</table>
	</div>
	
</body>
</html>