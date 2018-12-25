$(document).ready(function(){
    init();
//    新增下级
    $('#zzjg_add_update_model_sub').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/zzjg/zzjg",
            dataType:"json",
            async:true,
            data:$('#zzjg_add_update_model_form').serialize(),
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
                    alert(req.message);
                else{
                    $('#zzjg_add_update_model_form')[0].reset()
                    $('#zzjg_add_update_model_close').click();
                    init();
                }
            },
            complete:function(){
                //请求完成的处理
                $(obj).removeClass("disabled");
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //请求出错处理
                alert(textStatus);
            }
        });
    });
});
//新增下级弹窗
function zzjg_add_btn(o){
    $('#zzjg_add_update_model_form').find('input[name=zzjgfj]').val(o);
    $('#zzjg_add_update_model_btn').click();
}
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
                   +'         <input type="button" class="btn btn-danger btn-xs" value="删除" onclick="zzjg_delete_btn(\''+e.uuid+'\')">'
                   +'         <input type="button" class="btn btn-info btn-xs" value="修改" onclick="zzjg_update_btn(\''+e.uuid+'\',\''+e.zzjgfj+'\')">'
                   +'         <input type="button" class="btn btn-info btn-xs" value="新增下一级" onclick="zzjg_add_btn(\''+e.uuid+'\')">'
                   +'     </div>'
                   +'     <div style="padding-left:10px;">'
                    + b
                   +'     </div>'
                   +'</div>';
        h += html;
    });
    return h;
}
//删除
function zzjg_delete_btn(o){
    $.ajax({
        url:path+"/zzjg/zzjg/"+o,
        dataType:"json",
        async:true,
        type:"delete",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
        },
        success:function(req){
            //请求成功时处理
            if(req.success){
                init();
            }else
                alert(req.message);
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
function zzjg_update_btn(o,p){
    $('#zzjg_add_update_model_form').find('input[name=zzjgfj]').val(p);
    $('#zzjg_add_update_model_form').find('input[name=uuid]').val(o);
    $('#zzjg_add_update_model_btn').click();
}