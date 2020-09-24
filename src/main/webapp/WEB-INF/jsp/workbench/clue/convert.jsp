<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/commons.jsp"%>
	<script type="text/javascript">
		jQuery(function($) {
			$("#isCreateTransaction").prop("checked", false);

			$("#isCreateTransaction").click(function() {
				if(this.checked) {
					$("#create-transaction2").show(200);
				} else {
					$("#create-transaction2").hide(200);
				}
			});
			// 加载市场活动函数
			let loadActivity = function(){
				// 获取所有的市场活动
				$.get("/activity/queryAll", function (result) {
					window.actData = result;
					$("#table_body").html("");
					$(result).each(function () {
						$("#table_body").append(
								'<tr>\
                                    <td><input actname="'+this.name+'" actid="'+this.id+'" type="radio" name="actid"/></td>\
								<td>'+this.name+'</td>\
								<td>'+this.startdate+'</td>\
								<td>'+this.enddate+'</td>\
								<td>'+this.user.name+'</td>\
							</tr>'
						)
					})
					// 回显选中的radio
					let a1 =  $("#activity_name").val();
					let a2 = $("#activity_id").val();
					$(":radio[actid]").filter(function () {
						return $(this).attr("actid") === a2;
					}).prop("checked", true);
				})
			}

			// 配置日期事件插件
			$("input[time]").datetimepicker({
				language: "zh-CN",
				format: "yyyy-mm-dd",//显示格式
				minView: "month",//设置只显示到月份
				initialDate: new Date(),//初始化当前日期
				autoclose: true,
				todayBtn: true, //显示今日按钮
				clearBtn: true,
			});

			// 点击搜索市场活动事件
			$("#activity_search_button").click(function () {
				loadActivity();

			})

			// 模糊搜索框搜索事件
			$("#fuzzy_search").on("input", function () {
				// console.log(this.value)
				// // 方式一： 直接从数据库中获取模糊查询的结果
				// $.get("/activity/fuzzyQuery?keyword="+this.value, function (result) {
				// 	$("#table_body").html("");
				// 	$(result).each(function () {
				// 		$("#table_body").append(
				// 				'<tr>\
                //                     <td><input actname="'+this.name+'" actid="'+this.id+'" type="radio" name="actid"/></td>\
				// 				<td>'+this.name+'</td>\
				// 				<td>'+this.startdate+'</td>\
				// 				<td>'+this.enddate+'</td>\
				// 				<td>'+this.user.name+'</td>\
				// 			</tr>'
				// 		)
				// 	})
				// })
				// 方式二: 从已经查询的结果中搜索
				let keyword = this.value;
				// 动态拼接正则表达式
				// /^.*发.*$/i
				let expr = "^.*"+keyword+".*$"
				let reg = new RegExp(expr, "i");
				$("#table_body").html("");
				if (keyword === "") {
					loadActivity();
					return;
				}
				$(window.actData).each(function () {
					if (reg.test(this.name)) {
						$("#table_body").append(
								'<tr>\
                                    <td><input actname="'+this.name+'" actid="'+this.id+'" type="radio" name="actid"/></td>\
							<td>'+this.name+'</td>\
							<td>'+this.startdate+'</td>\
							<td>'+this.enddate+'</td>\
							<td>'+this.user.name+'</td>\
						</tr>'
						)
						// 回显选中的radio
						let a1 =  $("#activity_name").val();
						let a2 = $("#activity_id").val();
						$(":radio[actid]").filter(function () {
							return $(this).attr("actid") === a2;
						}).prop("checked", true);
					}

				})

			})

			// 模态框关闭时清除搜索框中的搜索条件
			$("#searchActivityModal").on("hide.bs.modal", function () {
				$("#fuzzy_search").val("");
			})

			// 将选中的市场活动的名字放入只读的输入框中
			// 同时将对应的市场活动id放入到隐藏域中
			$("#confirm_radio").click(function () {
				// 如果没有选中 直接关闭模态框
				let $radio = $("#table_body :radio:checked");
				let actId = $radio.attr("actid");
				let actname = $radio.attr("actname");
				if (!actId) return;
				$("#activity_name").val(actname);
				$("#activity_id").val(actId);
			})
			// 点击模态框取消按钮时 清空选中的市场活动
			$("#cancel_radio").click(function () {
				$("#activity_name").val("");
				$("#activity_id").val("");
			})


			// 转换按钮确定事件
			$("#confirm_convert").click(function () {
				// 获取表单中的数据
				let formdata = $("#convert_form").formJSON();
				// 当前线索的id 用来删除线索id相关的数据
				formdata.clueid = "${clue.id}";
				// 是否新增交易的标记
				formdata.tranflag = $("#isCreateTransaction").prop("checked");

				$.post("/clue/convert", formdata, function (result) {
					// 模拟点击客户页面按钮
					$("#customer_page", parent.document)[0].click();
				})
			})
		});
	</script>
</head>
<body>
	
<!-- 搜索市场活动的模态窗口 -->
<div class="modal fade" id="searchActivityModal" role="dialog" >
	<div class="modal-dialog" role="document" style="width: 90%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title">搜索市场活动</h4>
			</div>
			<div class="modal-body">
				<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
					<form class="form-inline" role="form">
					  <div class="form-group has-feedback">
						<input id="fuzzy_search" type="text" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						<span class="glyphicon glyphicon-search form-control-feedback"></span>
					  </div>
					</form>
				</div>
				<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
					<thead>
						<tr style="color: #B3B3B3;">
							<td></td>
							<td>名称</td>
							<td>开始日期</td>
							<td>结束日期</td>
							<td>所有者</td>
							<td></td>
						</tr>
					</thead>
					<tbody id="table_body">
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button id="cancel_radio" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button id="confirm_radio" type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		</div>
	</div>
</div>

<div id="title" class="page-header" style="position: relative; left: 20px;">
	<h4>转换线索 <small>${clue.fullname}${clue.appellation}-${clue.company}</small></h4>
</div>
<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
	新建客户：${clue.company}
</div>
<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
	新建联系人：${clue.fullname}${clue.appellation}
</div>
<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
	<input type="checkbox" id="isCreateTransaction"/>
	为客户创建交易
</div>
<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >
	<form id="convert_form">
	  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		<label for="money">金额</label>
		<input name="money" type="text" class="form-control" id="money">
	  </div>
	  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		<label for="tradeName">交易名称</label>
		<input name="name" type="text" class="form-control" id="tradeName">
	  </div>
	  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		<label for="expectedClosingDate">预计成交日期</label>
		<input name="expecteddate" time type="text" class="form-control" id="expectedClosingDate">
	  </div>
	  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		<label for="stage">阶段</label>
		<select name="stage" id="stage"  class="form-control">
			<option value="">--请选择--</option>
			<c:forEach items="${stageList}" var="stage">
				<option value="${stage.value}">${stage.text}</option>
			</c:forEach>
		</select>
	  </div>
	  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		<label for="activity">市场活动源&nbsp;&nbsp;<a id="activity_search_button" href="javascript:void(0);" data-toggle="modal" data-target="#searchActivityModal" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
		<input id="activity_name" type="text" class="form-control" id="activity" placeholder="点击上面搜索" readonly>
	  	<input id="activity_id" type="hidden" name="activityid"/>
	  </div>
	</form>
</div>

<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
	记录的所有者：<br>
	<b>${clue.user.name}</b>
</div>
<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
	<input id="confirm_convert" class="btn btn-primary" type="button" value="转换">
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input class="btn btn-default" type="button" onclick="window.history.back();" value="取消">
</div>
</body>
</html>