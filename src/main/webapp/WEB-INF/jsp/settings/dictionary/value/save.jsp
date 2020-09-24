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
	<h3>新增字典值</h3>
	<hr />
</div>
<form class="form-horizontal" style="margin: 10px;" role="form">
	<div class="form-group">
		<label for="create-dicTypeCode" class="col-sm-2 control-label">字典类型编码<span style="font-size: 15px; color: red;">*</span></label>
		<div class="col-sm-10" style="width: 300px;">
			<select class="form-control" id="create-dicTypeCode" style="width: 200%;">
			  <option></option>
			  <option>性别</option>
			  <option>机构类型</option>
			</select>
		</div>
	</div>

	<div class="form-group">
		<label for="create-dicValue" class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="create-dicValue" style="width: 200%;">
		</div>
	</div>

	<div class="form-group">
		<label for="create-text" class="col-sm-2 control-label">文本</label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="create-text" style="width: 200%;">
		</div>
	</div>

	<div class="form-group">
		<label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
		<div class="col-sm-10" style="width: 300px;">
			<input type="text" class="form-control" id="create-orderNo" style="width: 200%;">
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