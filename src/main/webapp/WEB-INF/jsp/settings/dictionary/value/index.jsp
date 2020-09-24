<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/commons.jsp"%>
	<script>
		jQuery(function ($) {
				//每次进入该页面刷新下拉菜单的值
				$.post("/type/queryAll", function (result) {

					$(result).each(function () {
						$("#typeCode").append('<option value="'+this.code+'">'+this.name+'</option>');
					})

				}, "json");


				// 刷新所有数据
				let loadTable = function(){
					// if (currPage === undefined || pageSize === undefined){
					// 	currPage = "1";
					// 	pageSize = "10";
					// }
					// window.page = {
					// 	currPage: currPage,
					// 	pageSize: pageSize
					// };
					$.post("/value/queryAll", function (result) {
						// 局部刷新列表数据
						// 利用嵌套查询的结果 类型的结果显示为类型的名字
						let htmlArr = [];
						$(result).each(function (index) {
							let activeStyle = index % 2 === 0 ? 'class="active"' : '';
							htmlArr.push(
									'<tr '+activeStyle+'>\
										<td><input type="checkbox" name="id" value="'+this.id+'" /> </td>\
										<td>'+(index+1)+'</td>\
										<td>'+this.value+'</td>\
										<td>'+this.text+'</td>\
										<td>'+this.orderno+'</td>\
										<td>'+this.dicType.name+'</td>\
								</tr>'
							)
						});
					// <li class="disabled"><a href="#">首页</a></li>
					// 	<li class="disabled"><a href="#">上一页</a></li>
					// 	<li class="active"><a href="#">1</a></li>
					// 	<li><a href="#">2</a></li>
					// 	<li><a href="#">3</a></li>
					// 	<li><a href="#">4</a></li>
					// 	<li><a href="#">5</a></li>
					// 	<li><a href="#">下一页</a></li>
					// 	<li class="disabled"><a href="#">末页</a></li>
						$("#table_body").html(htmlArr.join(""));
					}, "json");
				};
				// 每次加载完页面 刷新所有数据
				loadTable();

				// 为全选框绑定事件
				$("#select_all").on("click", function () {
					$(":checkbox[name=id]").prop("checked", this.checked);
				});

				// 不能直接对每一行的复选框绑定事件 因为ajax请求异步的
				// 需要进行事件委派
				$("#table_body").on("click", ":checkbox[name=id]", function () {
					let $chk = $(":checkbox[name=id]");
					$("#select_all").prop("checked", $chk.size() === $chk.filter(":checked").size());
				})

				// 添加按钮点击事件
				$("#insert_btn").click(function () {
					$("#value_form :input").val("");
					$("#value_dialog").modal("show");
				})

				// 编辑按钮事件
				$("#update_btn").click(function () {
					let $firstbox = $(":checkbox[name=id]:checked:first");
					if ($firstbox.size() === 0){
						alert("请选择要编辑的项")
						return;
					}

					// 回显
					$("#value_form").initForm("/value/queryById?id=" + $firstbox.val());
					$("#value_dialog").modal("show");
				})

				// 提交修改信息
				$("#save_btn").click(function () {
					// 将表单信息转换成json数据
					let formdata = $("#value_form").formJSON();
					$.post("/value/update", formdata, function (result) {
						if (result.success) {
							$("#value_dialog").modal("hide");
							loadTable();
						}
					})
				})

					// 删除选中的项
				$("#del_btn").click(function () {
					// 获取勾选了的复选框对象
					let $checked = $(":checkbox[name=id]:checked");
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
					$.post("/value/deleteByIds", codeArr.join("&"), function (result) {
						// expected response data : success:... msg:...(when exception happens)
						// 如果后台处理过程中发生异常 那么异常处理器会让重定向重定向至错误页面 此时
						// 请求无法处理html代码 需要在异常处理器中分类处理

						if (result.success) {
							loadTable();
						}
					}, "json");

				});




		})

	</script>
</head>
<body style="margin: 10px;">
<div>
	<div style="position: relative; top: -10px;">
		<div class="page-header">
			<h3>字典值列表</h3>
		</div>
	</div>
</div>
<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;">
	<div class="btn-group" style="position: relative; top: 18%;">
	  <button id="insert_btn" type="button" class="btn btn-primary" ><span class="glyphicon glyphicon-plus"></span> 创建</button>
	  <button id="update_btn" type="button" class="btn btn-default" ><span class="glyphicon glyphicon-edit"></span> 编辑</button>
	  <button id="del_btn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
	</div>
</div>

<!-- 添加和修改的模态窗口 -->
<div id="value_dialog" style="" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 id="title" class="modal-title">字典值-添加</h4>
			</div>
			<div class="modal-body">
				<form id="value_form" class="form-horizontal" style="margin: 10px;" role="form">
<%--			     隐藏域 --%>
					<input type="hidden" name="id" />
					<div class="form-group">
						<label for="typecode" class="col-sm-3 control-label">类型编码
							<span style="font-size: 15px; color: red;">*</span>
						</label>
						<div class="col-sm-9">
							<select name="typecode" class="form-control" id="typeCode" style="width: 100%;">
								<option value="">--请选择--</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="create-dicValue" class="col-sm-3 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-9">
							<input name="value" value="456" type="text" class="form-control" id="create-dicValue" style="width: 100%;">
						</div>
					</div>

					<div class="form-group">
						<label for="create-text" class="col-sm-3 control-label">文本</label>
						<div class="col-sm-9">
							<input name="text" type="text" class="form-control" id="create-text" style="width: 100%;">
						</div>
					</div>

					<div class="form-group">
						<label for="create-orderNo" class="col-sm-3 control-label">排序号</label>
						<div class="col-sm-9">
							<input name="orderno" type="text" class="form-control" id="create-orderNo" style="width: 100%;">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="save_btn" type="button" class="btn btn-primary">保存</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div style="position: relative; top: 20px;">
	<table class="table table-hover">
		<thead>
			<tr style="color: #B3B3B3;">
				<td><input id="select_all" type="checkbox" /></td>
				<td>序号</td>
				<td>字典值</td>
				<td>文本</td>
				<td>排序号</td>
				<td>字典名称</td>
			</tr>
		</thead>
		<tbody id="table_body">
<%--			<tr class="active">--%>
<%--				<td><input type="checkbox" /></td>--%>
<%--				<td>1</td>--%>
<%--				<td>m</td>--%>
<%--				<td>男</td>--%>
<%--				<td>1</td>--%>
<%--				<td>sex</td>--%>
<%--			</tr>--%>

		</tbody>
	</table>
</div>
<div style="height: 50px; position: relative;top: 10px;">
	<div style="position: absolute;">
		<button id="totol_rows" type="button" class="btn btn-default" style="cursor: default;"></button>
	</div>
	<div class="btn-group" style="position: absolute; left: 110px;">
		<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				10
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#">20</a></li>
				<li><a href="#">30</a></li>
			</ul>
		</div>
		<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
	</div>
	<div style="position: absolute; left: 285px;">
		<nav>
			<ul id="navi_bar"  class="pagination" style="margin:0">

			</ul>
		</nav>
	</div>
</div>


</body>
</html>