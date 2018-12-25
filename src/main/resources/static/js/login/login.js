//获取绝对地址，确保每次的ajax都是正确的请求地址
var curWwwPath=window.document.location.href;
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8083
var localhostPaht=curWwwPath.substring(0,pos);
//获取带"/"的项目名，如：/uimcardprj
//var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var path = localhostPaht;
$(document).ready(function(){
    //判断浏览器是否支持H5
     if (window.applicationCache) {
     } else {
        $('div').remove();
        $('body').html('您的浏览器不支持H5，请更换浏览器');
     }
    $('#login_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/login/login",
            dataType:"json",
            async:true,
            data:$('#login_form').serialize(),
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
            error:function(){
                //请求出错处理
                $('#errors').text("未知错误");
            }
        });
    });
});