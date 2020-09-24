<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
</head>
<body style="margin: 10px">
<div style="position:  relative;">
	<h3>修改字典值</h3>
	<hr />
</div>
<form class="form-horizontal" style="margin: 10px;" role="form">

	<div class="form-group">
		<label for="edit-dicTypeCode" class="col-sm-2 control-label">字典类型编码</label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="edit-dicTypeCode" style="width: 200%;" value="性别" readonly>
		</div>
	</div>

	<div class="form-group">
		<label for="edit-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="edit-dicValue" style="width: 200%;" value="m">
		</div>
	</div>

	<div class="form-group">
		<label for="edit-text" class="col-sm-2 control-label">文本</label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="edit-text" style="width: 200%;" value="男">
		</div>
	</div>

	<div class="form-group">
		<label for="edit-orderNo" class="col-sm-2 control-label">排序号</label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="edit-orderNo" style="width: 200%;" value="1">
		</div>
	</div>

	<div class="form-group" style="text-align: center;">
		<button type="button" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" style="margin-left: 20px;" onclick="window.history.back();">取消</button>
	</div>
</form>

<div style="height: 200px;"></div>
</body>
</html>