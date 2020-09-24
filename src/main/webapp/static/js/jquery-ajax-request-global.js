jQuery(function($) {
    // ajax的全局事件，每个ajax都会执行，如果希望某个ajax不使用全局配置，可以覆盖相应的熟悉，或者指定global=false
    // $(document).ajaxComplete(function(e, xhr) {
    //     var data = xhr.responseJSON;
    //     if(data.msg) {
    //         alert(data.msg);
    //     }
    //     if(data.relogin) {
    //         top.location = "/";
    //     }
    // });

    // ajax的全局默认配置，如果不希望使用全局配置，需要在ajax方法中覆盖相应的属性
    $.ajaxSetup({
        complete: function(xhr) {
            var data = xhr.responseJSON;
            if(data.msg) {
                alert(data.msg);
            }
            // 用来判断登陆是否超时
            if(data.relogin) {
                top.location = "/";
            }
        }
    });
});