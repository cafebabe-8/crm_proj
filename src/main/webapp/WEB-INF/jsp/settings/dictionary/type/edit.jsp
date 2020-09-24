<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/commons.jsp"%>
	<script>
		jQuery(function ($) {
			// 回显选中项的信息
			$("#edit_form").initForm("/type/queryById?code=${param.code}")
		})





	</script>
</head>
<body style="margin: 10px;">
<div style="position:  relative;">
	<h3>修改字典类型</h3>
	<hr />
</div>
<form id="edit_form" action="/type/update" method="post" class="form-horizontal" style="margin: 10px;" role="form">
	<div class="form-group">
		<label for="code" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" readonly class="form-control" id="code" name="code" style="width: 200%;" value="sex">
		</div>
	</div>

	<div class="form-group">
		<label for="tname" class="col-sm-2 control-label">名称</label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="tname" name="name" style="width: 200%;" value="性别">
		</div>
	</div>

	<div class="form-group">
		<label for="description" class="col-sm-2 control-label">描述</label>
		<div class="col-sm-10" style="width: 300px;">
			<textarea class="form-control" rows="3" id="description" name="description" style="width: 200%;">描述信息</textarea>
		</div>
	</div>

	<div class="form-group" style="text-align: center;">
		<button type="submit" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" style="margin-left: 20px;" onclick="location = 'index.html'">取消</button>
	</div>
</form>
<div style="height: 200px;"></div>
</body>
</html>