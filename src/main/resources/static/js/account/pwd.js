$(document).ready(function(){
    $('#pwd_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/account/updatePwd",
            dataType:"json",
            async:true,
            data:$('#pwd_form').serialize(),
            type:"put",
            cache:false,//关闭缓存
            ifModified :true,//关闭缓存
            beforeSend:function(){
                //请求前的处理
                $(obj).addClass("disabled");
            },
            success:function(req){
                //请求成功时处理
                if(!req.success){
                    $('#errors').show();
                    $('#errors').text(req.message);
                }else{
                    $('#errors').show();
                    $('#errors').text(req.message);
                    setTimeout(window.location.href = path + req.data,3);
                }
            },
            complete:function(){
                //请求完成的处理
                $(obj).removeClass("disabled");
            },
            error:function(){
                //请求出错处理
                $('#errors').show();
                $('#errors').text("未知错误");
            }
        });
    });
});