<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jsp/inc/commons.jsp"%>

	<script type="text/javascript">

		//默认情况下取消和保存按钮是隐藏的
		var cancelAndSaveBtnDefault = true;

		jQuery(function($) {
			// 加载备注
			let loadRemark = function(){
				// 移除之前已有的记录
				$("div.remarkDiv").remove();
				// 根据activityid获取所有的备注
				$.get("/activity_remark/queryByActivityId?activityid="+"${activity.id}", function (result) {
					// result = [{remark1}, {remark2}, ...]
					// 拼接备注到页面 用内部插入方法append会插入到添加备注区域后面 因此采用外部插入方法before
					$(result).each(function () {
						$("#remarkDiv").before(
								'<div class="remarkDiv" style="height: 60px;">\
                                    <img title="'+ this.creator.name +'" src="/static/images/user-thumbnail.png" style="width: 30px; height:30px;">\
								<div style="position: relative; top: -40px; left: 40px;" >\
									<h5>'+ this.notecontent +'</h5>\
									<font color="gray">市场活动</font> <font color="gray">-</font> <b>${activity.name}</b> <small style="color: gray;"> \
									'+ (this.edittime || this.createtime) + '由'
								+(this.editor?this.editor.name:this.creator.name)
								+(this.editor?"修改":"创建")+'</small>\
									<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">\
										<a edit="'+this.id+'" class="myHref"  data-toggle="edit_modal" ><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #e6e6e6"></span></a>\
										&nbsp;&nbsp;&nbsp;&nbsp;\
										<a delete="'+this.id+'"  class="myHref" ><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #e6e6e6 "></span></a>\
									</div>\
								</div>\
							</div>');
					})
				})
			}

			// 加载完页面进行备注的刷新
			loadRemark();

			$("#remark").focus(function(){
				if(cancelAndSaveBtnDefault){
					//设置remarkDiv的高度为130px
					$("#remarkDiv").css("height","130px");
					//显示
					$("#cancelAndSaveBtn").show("2000");
					cancelAndSaveBtnDefault = false;
				}
			});

			$("#cancelBtn").click(function(){
				//显示
				$("#cancelAndSaveBtn").hide();
				//设置remarkDiv的高度为130px
				$("#remarkDiv").css("height","90px");
				cancelAndSaveBtnDefault = true;
			});

			// 将事件委派给备注区的父节点
			$("#remark_area").on("mouseenter mouseleave", ".myHref", function () {
				// 光标移到图标上 图标红 移走 图标变灰 默认为灰色
				$(this).children("span").css("color", function (index, value) {
					return value === 'rgb(255, 0, 0)' ? 'rgb(230, 230, 230)' : 'rgb(255, 0, 0)';
				})
			}).on("mouseenter mouseleave", ".remarkDiv", function () {
				// 光标移入移出每一条备注区域时 图标显示或者隐藏
				$(this).children("div").children("div").toggle();
			}).on("click", "a[delete]", function () {
				// 删除按钮的点击事件
				// 弹出确认框
				$("#alertModal").modal("show");

			}).on("click", "a[edit]", function () {
				// 点击编辑按钮 弹出模态框
				$("#edit_modal").modal("show");
				// 回显备注信息
				$.get("/activity_remark/queryById?id="+$(this).attr("edit"), function (result) {
					$("#update_remark_form [name=id]").val(result.id);
					$("#update_remark_form [name=notecontent]").val(result.notecontent);
				}, "json")
			})


			// 确认删除按钮点击事件
			$("#confirm_delete_button").click(function () {
				let id = $("a[delete]").attr("delete");
				$.get("/activity_remark/deleteById?id="+id, function (result) {
					if (result.success){
						$("#alertModal").modal("hide");
						loadRemark();
					}
				} , "json")

			})

			// 编辑模态框确认按钮点击事件
			$("#update_button").click(function () {
				let formData = $("#update_remark_form").formJSON();
				<%--formData.activityid = "${activity.id}";--%>
				$.post("/activity_remark/update", formData, function (result) {
					if (result.success){
						$("#edit_modal").modal("hide");
						$("#update_remark_form :input").val("")
						loadRemark();
					}
				}, "json")
			})

			// 增加按钮点击事件
			$("#save_button").click(function () {
				// 如果文本为空 提示
				let content = $("#remark").val();
				if (content.length === 0) {
					$("#alertModal2").modal("show");
					return;
				}
				let formData = $("#insert_remark_form").formJSON();
				formData.activityid = "${activity.id}";
				console.log(formData)
				$.post("/activity_remark/insert", formData,function (result) {
					if (result.success) {
						$("#insert_remark_form :input").val("");
						loadRemark();
						//显示
						$("#cancelAndSaveBtn").hide();
						//设置remarkDiv的高度为130px
						$("#remarkDiv").css("height","90px");
						cancelAndSaveBtnDefault = true;
					}
				},"json")

			})
		});

	</script>
</head>
<body>


	<%--删除警告--%>
	<div class="modal fade" id="alertModal" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

				<div class="modal-body">确定删除这条备注？</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
					<button id="confirm_delete_button" type="button" class="btn btn-primary" >确定</button>
				</div>
			</div>
		</div>
	</div>

	<%--没有填写备注内容警告--%>
	<div class="modal fade" id="alertModal2" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">

				<div class="modal-body">备注文本为空！</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改市场活动备注的模态窗口 -->
	<div class="modal fade" id="edit_modal" role="dialog">

        <div class="modal-dialog" role="document" style="width: 40%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改备注</h4>
                </div>
                <div class="modal-body">
                    <form id="update_remark_form" class="form-horizontal" role="form">

						<%-- 备注的id --%>
						<input type="hidden" id="remark_id" name="id">
                        <div class="form-group">
                            <label for="noteContent" class="col-sm-2 control-label">内容</label>
                            <div class="col-sm-10" style="width: 81%;">
                                <textarea class="form-control" rows="3" id="noteContent" name="notecontent"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="update_button" type="button" class="btn btn-primary">更新</button>
                </div>
            </div>
        </div>
    </div>



	<!-- 返回按钮 -->
	<div style="position: relative; top: 35px; left: 10px;">
		<a href="javascript:void(0);" onclick="window.history.back();"><span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
	</div>

	<!-- 大标题 -->
	<div style="position: relative; left: 40px; top: -30px;">
		<div class="page-header">
			<h3>市场活动-${activity.name} <small>${activity.startdate} ~ ${activity.enddate}</small></h3>
		</div>

	</div>

	<br/>
	<br/>
	<br/>

	<!-- 详细信息 -->
	<div style="position: relative; top: -70px;">
		<div style="position: relative; left: 40px; height: 30px;">
			<div style="width: 300px; color: gray;">所有者</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${activity.user.name}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">名称</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${activity.name}</b></div>
			<!--下划线效果-->
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>

		<div style="position: relative; left: 40px; height: 30px; top: 10px;">
			<div style="width: 300px; color: gray;">开始日期</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${activity.startdate}</b></div>
			<div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">结束日期</div>
			<div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${activity.enddate}</b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 20px;">
			<div style="width: 300px; color: gray;">成本</div>
			<div style="width: 300px;position: relative; left: 200px; top: -20px;"><b><fmt:formatNumber value="${activity.cost}" pattern="#,###" /> </b></div>
			<div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 30px;">
			<div style="width: 300px; color: gray;">创建者</div>
			<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${activity.creator.name}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${activity.createtime}</small></div>
			<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 40px;">
			<div style="width: 300px; color: gray;">修改者</div>
			<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${activity.editor.name}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${activity.edittime}</small></div>
			<div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
		<div style="position: relative; left: 40px; height: 30px; top: 50px;">
			<div style="width: 300px; color: gray;">描述</div>
			<div style="width: 630px;position: relative; left: 200px; top: -20px;">
				<b>
					${activity.description}
				</b>
			</div>
			<div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
		</div>
	</div>

	<!-- 备注 -->
	<div id="remark_area" style="position: relative; top: 30px; left: 40px;">
		<div class="page-header">
			<h4>备注</h4>
		</div>




		<div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
			<form id="insert_remark_form" role="form" style="position: relative;top: 10px; left: 10px;">
				<textarea id="remark" name="notecontent" class="form-control" style="width: 850px; resize : none;" rows="2"  placeholder="添加备注..."></textarea>
				<p id="cancelAndSaveBtn" style="position: relative;left: 737px; top: 10px; display: none;">
					<button id="cancelBtn" type="button" class="btn btn-default">取消</button>
					<button id="save_button" type="button" class="btn btn-primary">保存</button>
				</p>
			</form>
		</div>
	</div>
	<div style="height: 200px;"></div>
</body>
</html>