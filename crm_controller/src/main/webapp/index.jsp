<html>
<head>
    <script src="static/js/jquery-1.12.4.min.js"></script>
    <script>
        $(()=>{
            $("#btn").click(function () {
                $.get("/activity/queryAll", function (result) {
                    console.log(result);
                })
            })
        })
    </script>
    <title></title>
</head>
<body>
<h2>Hello World!</h2>
<hr>
<input id="btn" type="button" value="show">
</body>
</html>
