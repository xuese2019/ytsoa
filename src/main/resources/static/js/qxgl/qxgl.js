$(document).ready(function(){
    init();
});
function init(){
    $('#zzjg_tree').find('div').remove();
    $.ajax({
        url:path+"/zzjg/recursive",
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
                $('#zzjg_tree').append(dg(req.data));
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
            alert(textStatus);
        }
    });
}
//递归
function dg(obj){
    var h = '';
    $(obj).each(function(index,e){
        var a = '<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>';
        var b = '',c='';
        if(e.list.length > 0){
            a = '<span class="glyphicon glyphicon-menu-right" onclick="zzjg_tree_or_show(this)" style="cursor:pointer;"></span>';
            b = dg(e.list);
        }
        if(e.zzjgfj != 0)
            c = 'display:none;';
        var html = '<div style="margin-top:5px;'+c+'">'
                   +'     <div>'
//                           +'         <span class="glyphicon glyphicon-menu-right"></span>'
                   + a
                   +'         <span style="padding:10px;padding-left:1px;">'+e.zzjgmc+'</span>'
                   +'         <input type="button" class="btn btn-info btn-xs" value="管理权限" onclick="zzjg_glqx_btn(\''+e.uuid+'\')">'
                   +'     </div>'
                   +'     <div style="padding-left:10px;">'
                    + b
                   +'     </div>'
                   +'</div>';
        h += html;
    });
    return h;
}
//显示隐藏
function zzjg_tree_or_show(obj){
    if($(obj).attr('class') == 'glyphicon glyphicon-menu-right'){
        $(obj).removeClass('glyphicon-menu-right');
        $(obj).addClass('glyphicon-menu-down');
        $(obj).parent('div').next('div').children('div').show();
    }else{
        $(obj).removeClass('glyphicon-menu-down');
        $(obj).addClass('glyphicon-menu-right');
        $(obj).parent('div').next('div').children('div').hide();
    }
}
function zzjg_glqx_btn(o){

}