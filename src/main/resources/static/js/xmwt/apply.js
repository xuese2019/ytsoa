$(document).ready(function(){
    $('#wtsj').datepicker({
          autoclose: true,
        todayHighlight: true,
        language:"zh-CN",
        format:"yyyy-mm-dd"
    });
    $('#xmkssj').datepicker({
          autoclose: true,
        todayHighlight: true,
        language:"zh-CN",
        format:"yyyy-mm-dd"
    });
    $('#xmjssj').datepicker({
          autoclose: true,
        todayHighlight: true,
        language:"zh-CN",
        format:"yyyy-mm-dd"
    });
//    获取业务类型
    getYwlx();
//    获取项目分类
    getXmfl();
});
//获取业务类型
function getYwlx(){
    $('#ywlx').find('option').remove();
    $('#ywlx').append('<option value="">请选择</option>');
    $.ajax({
        url:path+"/ywlx/ywlx",
        dataType:"json",
        async:true,
        type:"get",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
        },
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data).each(function(index,e){
                    var option = '<option value="'+e.uuid+'">'+e.ywlxmc+'</option>';
                    $('#ywlx').append(option);
                });
                pageHelp(req.data);
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
        }
    });
}
//获取项目分类
function getXmfl(){
    $('#xmfl').find('option').remove();
    $('#xmfl').append('<option value="">请选择</option>');
    $.ajax({
        url:path+"/xmfl/xmfl",
        dataType:"json",
        async:true,
        type:"get",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
        },
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data).each(function(index,e){
                    var option = '<option value="'+e.uuid+'">'+e.xmflmc+'</option>';
                    $('#xmfl').append(option);
                });
                pageHelp(req.data);
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
        }
    });
}