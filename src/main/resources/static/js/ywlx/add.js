$(document).ready(function(){
    $('#ywlx_add_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/ywlx/ywlx",
            dataType:"json",
            async:true,
            data:$('#ywlx_add_form').serialize(),
            type:"post",
            cache:false,//关闭缓存
            ifModified :true,//关闭缓存
            beforeSend:function(){
                //请求前的处理
                $(obj).addClass("disabled");
            },
            success:function(req){
                //请求成功时处理
                if(!req.success)
                    $('#errors').text(req.message);
                else
                    window.location.href = path + req.data;
            },
            complete:function(){
                //请求完成的处理
                $(obj).removeClass("disabled");
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //请求出错处理
                $('#errors').text(textStatus);
            }
        });
    });
});