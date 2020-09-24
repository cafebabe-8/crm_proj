<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/static/js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-ajax-request-global.js"></script>
	<script>
		jQuery(function ($) {
			$("#save_form").submit(function () {
				// 如果code输入栏为空
				if (!$("#code").val()){
					alert("编码栏不能为空");
					$("#code").focus();
					// return false 才能阻止表单的提交
					return false;
				}

			})
		})

	</script>


</head>
<body style="margin: 10px;">
	<div style="position:  relative;">
		<h3>新增字典类型</h3>
		<hr />
	</div>
	<form id="save_form" action="/type/insert" method="post" class="form-horizontal" style="margin: 10px;" role="form">
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="code" name="code"  style="width: 200%;">
			</div>
		</div>
		
		<div class="form-group">
			<label for="tname" class="col-sm-2 control-label">名称</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="tname" name="name" style="width: 200%;">
			</div>
		</div>
		
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 300px;">
				<textarea class="form-control" rows="3" id="description" name="description" style="width: 200%;"></textarea>
			</div>
		</div>

		<div class="form-group" style="text-align: center;">
			<button id="save_button" type="submit" class="btn btn-primary">保存</button>
			<button type="button" class="btn btn-default" style="margin-left: 20px;" onclick="window.history.back();">取消</button>
		</div>
	</form>
	
	<div style="height: 200px;"></div>
</body>
</html>