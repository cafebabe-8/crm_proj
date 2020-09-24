<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="../../static/css/bootstrap.min.css"/>
    <script type="text/javascript" src="../../static/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/jquery.form.extend.js"></script>
    <script>
        jQuery(function ($) {






            $("#login_button").click(function () {
                // 对输入框进行简单的非空校验
                if (!$("#username_").val()) {
                    alert("请输入用户名");
                    $("#username_").focus();
                    return;
                }
                if (!$("#password_").val()) {
                    alert("请输入密码");
                    $("#password_").focus();
                    return;
                }
                // 利用ajax请求传送用户输入的名字和密码等
                // 传输的数据形式： data={username: ... , password: ..., autologin: ...}
                let formdata = {
                    username: $("#username_").val(),
                    password: $("#password_").val()
                };
                // 如果勾选免登录 则添加autologin的值
                if ($("#auto_login").prop("checked")) {
                    formdata.autologin = $("#auto_login").val(); //"on"
                }
                // 将formdata转化成json格式
                let jsonData = $("#login_form").formJSON();
                // ajax请求
                $.post("/user/login", jsonData, function (result) {
                    // 返回的信息格式：
                    // {
                    //    "success": true,
                    //    "msg": "..."
                    // }
                    if (result.success){
                        // 跳转到工作台首页
                        location = "/workbench/index.html"
                    }else {
                        //提示错误信息
                        $("#msg_").text(result.msg);
                    }


                }, "json")

            })




    })





    </script>



</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="/static/images/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form id="login_form" class="form-horizontal" role="form" >
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input id="username_" name="username" class="form-control" type="text" placeholder="用户名">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input id="password_" name="password" class="form-control" type="password" placeholder="密码">
                </div>
                <div class="checkbox" style="position: relative;top: 30px; left: 10px;">
                    <label>
                        <input id="auto_login" name="autologin"  type="checkbox"> 十天内免登录
                    </label>
                    &nbsp;&nbsp;
                    <span style="color: crimson" id="msg_"></span>
                </div>
                <button  id="login_button" type="button" class="btn btn-primary btn-lg btn-block"
                        style="width: 350px; position: relative;top: 45px;">登录</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>