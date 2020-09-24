<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/commons.jsp"%>

	<script type="text/javascript">
		// 全局变量 用于存储分页复选框的状态
		var stateObj = {
			in: [],
			notin: [],
			lastState: false
		};

		jQuery(function($){

			// 获得表中的数据 分页版本 三个参数分别是 当前页码 每页显示条目数 区分查询还是跳页的标记
			let loadtable = function (currentPage, rowsPerPage, flag) {
				// flag: undefined 用于刷新页面 false 用于标记跳页情形 true用于标记查询清形
				let searchData = {};

				// 如果是跳转页面的情形 就使用旧的搜索条件或者从查询框取空值（没有做过查询时）
				if ( flag === false) {
					searchData = window.oldSearchData || {}
				} else if (flag === true) {
					searchData = $("#search_form").formJSON();
				}

				// 添加分页参数
				searchData.currentPage = currentPage;
				searchData.rowsPerPage = rowsPerPage;
				$.get("/activity/queryAllForPage", searchData,  function (result) {
					let htmlArr = [];
					$(result.list).each(function (index) {
						htmlArr.push(
								'<tr class="'+(index % 2 === 0 ? "active" : "")+'">\
									 <td><input type="checkbox" name="id" value="'+this.id+'"/></td>\
								 	 <td><a style="text-decoration: none; cursor: pointer;" actId="'+this.id+'"">'+this.name+'</a></td>\
								 	 <td>'+this.user.name+'</td>\
									 <td>'+this.startdate+'</td>\
								 	 <td>'+this.enddate+'</td>\
								 </tr>'
						)
					})
					$("#table_body").html(htmlArr.join(""));

					// 配置分页
					$("#page").bs_pagination({
						currentPage: result.currentPage,        	// 页码
						rowsPerPage: result.rowsPerPage,      	// 每页显示的记录条数
						maxRowsPerPage: result.maxRowsPerPage,	// 每页最多显示的记录条数
						totalPages: result.totalPages,     		// 总页数
						totalRows: result.totalRows,      		// 总记录条数
						visiblePageLinks: result.visiblePageLinks,// 显示几个卡片
						size: "small",
						showGoToPage: true,
						showRowsPerPage: true,
						showRowsInfo: true,
						showRowsDefaultInfo: true,
						onChangePage: function (event, result) {
							loadtable(result.currentPage, result.rowsPerPage, false);
						}
					});

					// 回显选中状态
					loadCheckbox();

				}, "json")
			};
			// 获得下拉列表中的数据
			let loadSelect = function(){
				$.get("/user/queryAll",function (result2) {
					$(result2).each(function () {
						if ($('#owner option[value="'+this.id+'"]').val() === undefined){
							$("#owner").append('<option value="'+this.id+'">'+this.name+'</option>')
						}
					});

					// 默认下拉框选择当前登录的用户
					$("#owner").val("${sessionScope.user.id}");

				},"json");
			}
			// 初始化复选框选中状态 使得跳页后能够回显和保存勾选状态
			let loadCheckbox = function(){
				if ( stateObj.lastState ) {
					// 如果全选框上次被勾选 应该从notin数组中找勾选框的状态 反之
					$("#table_body :checkbox[name=id]").prop("checked", function () {
						return $.inArray(this.value, stateObj.notin) === -1;
					});
				} else {
					// 如果复选框的value在in数组中，则勾选，否则不勾选
					$("#table_body :checkbox[name=id]").prop("checked", function () {
						return $.inArray(this.value, stateObj.in) > -1;
					});
				}

			}
			// 刷新页面时刷新数据
			loadtable();
			// // 刷新下拉列表数据 放在按钮点击事件触发时
			// loadSelect();

			// 查询按钮事件
			$("#search_button").click(function () {
				// 效果1 根据查询条件刷新页面数据 而且每次都显示第一页的数据
				// 效果2 刷新oldserachdata 根据loadtable里面的逻辑
				// 在页面跳转时只能使用上一次查询的条件（没有查询过为空）
				// 只有点击了查询按钮才更新查询条件

				window.oldSearchData = $("#search_form").formJSON();
				// 获得当前的每页显示条数
				let rowsPerPage = $("#page").bs_pagination('getOption', 'rowsPerPage');

				// 查询后应当让复选框状态初始化
				$("#select_all").prop("checked", false);
				stateObj = {
					in: [],
					notin: [],
					lastState: false
				}

				// 加载页面 flag为true则利用的是查询框中的数据
				loadtable(1, rowsPerPage, true);
			})


			// 设置日期选择插件
			$("input[time]").datetimepicker({
				language: "zh-CN",
				format: "yyyy-mm-dd",
				minView: "month",
				initialDate: new Date(),
				autoclose: true
			})

			// 创建按钮事件
			$("#insert_btn").click(function () {
				loadSelect();
				// 弹出模态框
				$("#modal").modal("show");
				// 重置表单数据
				$("#activity_form input").val("");
				$("#activity_form textarea").val("");
			});

			// 保存按钮事件
			$("#save_btn").click(function () {
				// 获取表单中的数据
				let formData = $("#activity_form").formJSON();
				 $.post("/activity/insert", formData, function (result) {
						if (result.success) {
							$("#modal").modal("hide");
							loadtable();
							// location = "index.html";
						}
				 }, "json")
			});

			// 修改按钮事件
			$("#update_btn").click(function () {
				loadSelect();
				// 需要选中一个复选框
				let $checked = $("#table_body :checkbox[name=id]:checked:first");
				if ($checked.size() === 0){
					alert("请选择要修改的项");
					return;
				}
				$("#modal").modal("show");
				// 回显数据
				$("#activity_form").initForm("/activity/queryById?id="+$checked.val());


			});

			// 为全选框绑定事件
			$("#select_all").on("click", function () {
				$(":checkbox[name=id]").prop("checked", this.checked);
				// stateObj的处理
				stateObj = {
					in: [],
					notin: [],
					lastState: this.checked
				}
			});

			// 不能直接对每一行的复选框绑定事件 因为ajax请求异步的
			// 需要进行事件委派
			$("#table_body").on("click", ":checkbox[name=id]", function () {
				// let $chk = $(":checkbox[name=id]");
				// 全选框jquery对象
				let $selectall = $("#select_all");
				// $("#select_all").prop("checked", $chk.size() === $chk.filter(":checked").size());
				// 分情况讨论 如果上次全选框勾选 那么操作notin数组 反之操作in数组
				if (stateObj.lastState){
					// 如果点击事件是勾选 那么应该将这个选框排除notin之外
					if (this.checked) {
						// 元素在notin中 那么index大于-1 将其排除
						let index = $.inArray(this.value, stateObj.notin);
						if (index > -1) stateObj.notin.splice(index, 1);
					} else {
						// 取消勾选 那么将复选框的值添加进数组
						stateObj.notin.push(this.value);
					}
					// 对全选框的状态进行操作 如果notin数组长度为0 表示全选 全选框应该打勾
					$selectall.prop("checked", stateObj.notin.length === 0);
				} else {
					// 全选框被点击取消勾选 操作in数组
					if (this.checked) {
						stateObj.in.push(this.value);
					} else {
						let index = $.inArray(this.value, stateObj.in);
						if (index > -1) stateObj.in.splice(index, 1);
					}
					// 如果都被选了 那么全选框也要被选中
					// 获取页面中的总记录数
					let totalraws = $("#page").bs_pagination('getOption', 'totalRows');
					$selectall.prop("checked", totalraws === stateObj.in.length)

				}
			})

			// 删除选中的项
			$("#delete_btn").click(function () {
				// 获取总的记录数
				let totalraws = $("#page").bs_pagination('getOption', 'totalRows');
				// 判断是否勾选复选框
				if (stateObj.lastState && stateObj.notin.length === totalraws
				    || !stateObj.lastState && stateObj.in.length === 0){
					alert("请选择删除对象");
					return;
				}
				// 确认操作
				if ( !confirm("确认删除选中项吗？") ) return;

				// 组装请求的参数
				// 需要的参数：
				// 		stateObj对象中的属性
				// 		#select_all的当前状态 如果为真 直接执行全部删除的sql语句
				// 		oldSearchData || {} 需要筛选条件 比如删除之前点了全选按钮
				// 那么没有筛选条件 就会把数据库中的所有记录都删除 但是按照页面的逻辑
				// 只应该删除筛选的所有记录
				let conditions =  window.oldSearchData || {};
				conditions.in = stateObj.in;
				conditions.notin = stateObj.notin;
				conditions.lastState = stateObj.lastState;
				conditions.selectall = $("#select_all").prop("checked");

				// 发送get请求 $.param由于将对象转换成请求参数
				$.get("/activity/deleteForPagination", $.param(conditions, true), function (result) {

					if (result.success) {
						loadtable();
						// location = "index.html";
						// 删除后应当让复选框状态初始化
						$("#select_all").prop("checked", false);
						stateObj = {
							in: [],
							notin: [],
							lastState: false
						}
					}
				}, "json");

			});

			// 选择导出事件
			$("#export_button").click(function () {
				// 请求参数与删除选中的类似
				// 获取总的记录数
				let totalraws = $("#page").bs_pagination('getOption', 'totalRows');
				// 判断是否勾选复选框
				if (stateObj.lastState && stateObj.notin.length === totalraws
						|| !stateObj.lastState && stateObj.in.length === 0){
					alert("请选择导出对象");
					return;
				}
				// 确认操作
				if ( !confirm("确认导出选中项吗？") ) return;

				// 组装请求的参数
				// 需要的参数：
				// 		stateObj对象中的属性
				// 		#select_all的当前状态 如果为真 直接执行全部删除的sql语句
				// 		oldSearchData || {} 需要筛选条件 比如删除之前点了全选按钮
				// 那么没有筛选条件 就会把数据库中的所有记录都删除 但是按照页面的逻辑
				// 只应该删除筛选的所有记录

				let conditions = window.oldSearchData || {};
				conditions.in = stateObj.in;
				conditions.notin = stateObj.notin;
				conditions.lastState = stateObj.lastState;
				conditions.selectall = $("#select_all").prop("checked");
				console.log($.param(conditions, true))

				// 无法使用ajax请求 下载文件时是直接利用io流完成的
				location = "/activity/exportChosen?" + $.param(conditions, true);

			})

			// 导入按钮事件
			$("#import_button").click(function () {
				let files = $("#upload_file").prop("files");
				if (!files || files.length === 0){
					alert("请选择要导入的excel文件")
				}

				let worksheet = files[0];
				// 对文件名格式进行校验
				// .+ 表示匹配一个或者多个任意字符 除了换行符
				// \. 表示匹配一个. 因为.是元字符 要进行转义
				if (! /^.+\.(xls|xlsx)$/i.test(worksheet.name)){
					alert("文件名或文件格式不正确");
					return;
				}
				// 对文件大小进行校验
				if (worksheet.size > 5 * 1024 * 1024) {
					alert("文件大小超出5MB限制");
					return;
				}
				// 将文件添加到请求数据中
				let formData = new FormData();
				formData.append("worksheet", worksheet);
				$.ajax({
					url: "/activity/import",
					data: formData,
					type: "post",
					contentType: false,
					processData: false, // 不让jquery对data进行处理
					dataType: "json",
					success: function(result){
						if(result.success){
							$("#import_modal").modal("hide");
							$("#upload_file").val(""); // 去掉已被选中的文件
							loadtable();
						}
					}
				})



			})

			// 下载模板按钮事件
			$("#download_template").click(function () {
				location = "/activity/downloadTemplate";
			})

			// 点击活动名称超链跳转到详情页
			$("#table_body").on("click", "a[actId]", function () {
				location = "/activity/detail.html?id="+$(this).attr("actId");
			})

		});
	</script>
</head>
<body>
<!-- 创建市场活动的模态窗口 -->
<div class="modal fade" id="modal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
			</div>
			<div class="modal-body">

				<form id="activity_form" class="form-horizontal" role="form">
					<input name="id" type="hidden" >
					<div class="form-group">
						<label for="owner" class="col-sm-1 control-label" >所有者<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-4" style="width: 300px;">
							<select name="owner" class="form-control" id="owner">
								<option value="">--请选择--</option>
							</select>
						</div>
						<label for="create-marketActivityName" class="col-sm-1 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-1" style="width: 300px;">
							<input name="name" type="text" class="form-control" id="create-marketActivityName">
						</div>
					</div>

					<div class="form-group">
						<label  for="create-startTime" class="col-sm-1 control-label">开始日期</label>
						<div class="col-sm-4" style="width: 300px;">
							<input name="startdate" type="text" class="form-control" time id="create-startTime">
						</div>
						<label  for="create-endTime" class="col-sm-1 control-label">结束日期</label>
						<div class="col-sm-1" style="width: 300px;">
							<input name="enddate"  type="text" class="form-control" time id="create-endTime">
						</div>
					</div>
					<div class="form-group">

						<label for="create-cost" class="col-sm-1 control-label">成本</label>
						<div class="col-sm-10" style="width: 300px;">
							<input name="cost" type="text" class="form-control" id="create-cost">
						</div>
					</div>
					<div class="form-group">
						<label for="create-describe" class="col-sm-1 control-label">描述</label>
						<div class="col-sm-10" style="width: 81%;">
							<textarea name="description" class="form-control" rows="3" id="create-describe"></textarea>
						</div>
					</div>

				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="save_btn" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
			</div>
			<div class="modal-body">

				<form class="form-horizontal" role="form">

					<div class="form-group">
						<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<select class="form-control" id="edit-marketActivityOwner">
								<option>zhangsan</option>
								<option>lisi</option>
								<option>wangwu</option>
							</select>
						</div>
						<label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
						</div>
						<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-cost" class="col-sm-2 control-label">成本</label>
						<div class="col-sm-10" style="width: 300px;">
							<input type="text" class="form-control" id="edit-cost" value="5,000">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-describe" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10" style="width: 81%;">
							<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
						</div>
					</div>

				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
			</div>
		</div>
	</div>
</div>

<!-- 导入市场活动的模态窗口 -->
<div class="modal fade" id="import_modal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
			</div>
			<div class="modal-body" style="height: 350px;">
				<div style="position: relative;top: 20px; left: 50px;">
					请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
				</div>
				<div style="position: relative;top: 40px; left: 50px;">
<%--					accept属性 限定支持的文件类型--%>
					<input type="file" id="upload_file" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
				</div>
				<div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
					<h3>重要提示</h3>
					<ul>
						<li>操作仅针对Excel，仅支持后缀名为XLS/XLSX的文件。</li>
						<li>给定文件的第一行将视为字段名。</li>
						<li>请确认您的文件大小不超过5MB。</li>
						<li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
						<li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
						<li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
						<li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
					</ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="import_button" type="button" class="btn btn-primary">导入</button>
			</div>
		</div>
	</div>
</div>

<div style="margin:10px;">
	<div style="position: relative; top: -10px;">
		<div class="page-header">
			<h3>市场活动列表</h3>
		</div>
	</div>
</div>
<div style="position: relative; top: -20px; margin:10px; height: 100%;">
	<div style="width: 100%; position: absolute;top: 5px;">
		<div class="btn-toolbar" role="toolbar" style="height: 80px;">
			<form id="search_form" class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">名称</div>
						<input name="name" class="form-control" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">所有者</div>
						<input name="user.name" class="form-control" type="text">
					</div>
				</div>


				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">开始日期</div>
						<input name="startdate" time class="form-control" type="text" id="startTime" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">结束日期</div>
						<input name="enddate"  time class="form-control" type="text" id="endTime">
					</div>
				</div>

				<button id="search_button" type="button" style="float: none;" class="btn btn-default">查询</button>

			</form>
		</div>
		<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
			<div class="btn-group" style="position: relative; top: 18%;">
				<button id="insert_btn" type="button" class="btn btn-primary" data-toggle="modal" ><span class="glyphicon glyphicon-plus"></span> 创建</button>
				<button id="update_btn" type="button" class="btn btn-default" data-toggle="modal" ><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				<button id="delete_btn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
			</div>
			<div class="btn-group" style="position: relative; top: 18%;">
				<button id="upload_button" type="button" class="btn btn-default" data-toggle="modal" data-target="#import_modal" ><span class="glyphicon glyphicon-export"></span> 上传列表数据（导入）</button>
				<button id="download_template" type="button" class="btn btn-default"><span class="glyphicon glyphicon-import"></span> 下载上传模板 </button>
<%--				<button id="export_all_button" type="button" class="btn btn-default"><span class="glyphicon glyphicon-import"></span> 下载列表数据（批量导出）</button>--%>
				<button id="export_button" type="button" class="btn btn-default"><span class="glyphicon glyphicon-import"></span> 下载列表数据（选择导出）</button>
			</div>
		</div>
		<div style="position: relative;top: 10px;">
			<table class="table table-hover">
				<thead>
				<tr style="color: #B3B3B3;">
					<td><input id="select_all" type="checkbox" /></td>
					<td>名称</td>
					<td>所有者</td>
					<td>开始日期</td>
					<td>结束日期</td>
				</tr>
				</thead>
				<tbody id="table_body">
				</tbody>
			</table>
		</div>
		<!--分页栏-->
<%--		<div style="height: 50px; position: relative;top: 10px;">--%>
<%--			<div style="position: absolute;">--%>
<%--				<button type="button" class="btn btn-default" style="cursor: default;">共<b>50</b>条记录</button>--%>
<%--			</div>--%>
<%--			<div class="btn-group" style="position: absolute; left: 110px;">--%>
<%--				<button type="button" class="btn btn-default" style="cursor: default;">显示</button>--%>
<%--				<div class="btn-group">--%>
<%--					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">--%>
<%--						10--%>
<%--						<span class="caret"></span>--%>
<%--					</button>--%>
<%--					<ul class="dropdown-menu" role="menu">--%>
<%--						<li><a href="#">20</a></li>--%>
<%--						<li><a href="#">30</a></li>--%>
<%--					</ul>--%>
<%--				</div>--%>
<%--				<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>--%>
<%--			</div>--%>
<%--			<div style="position: absolute; left: 285px;">--%>
<%--				<nav>--%>
<%--					<ul class="pagination" style="margin:0">--%>
<%--						<li class="disabled"><a href="#">首页</a></li>--%>
<%--						<li class="disabled"><a href="#">上一页</a></li>--%>
<%--						<li class="active"><a href="#">1</a></li>--%>
<%--						<li><a href="#">2</a></li>--%>
<%--						<li><a href="#">3</a></li>--%>
<%--						<li><a href="#">4</a></li>--%>
<%--						<li><a href="#">5</a></li>--%>
<%--						<li><a href="#">下一页</a></li>--%>
<%--						<li class="disabled"><a href="#">末页</a></li>--%>
<%--					</ul>--%>
<%--				</nav>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		分页插件效果显示--%>
		<div id="page"></div>
	</div>
</div>
</body>
</html>