var zzid = '';
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
                   +'         <input type="button" class="btn btn-info btn-xs" value="管理权限" onclick="init2(\''+e.uuid+'\')">'
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
//权限树
function init2(o){
    zzid = o;
    $('#qxgl_tree').find('div').remove();
    $.ajax({
        url:path+"/qxgl/qxgl",
        dataType:"json",
        async:false,
        type:"get",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
        },
        success:function(req){
            //请求成功时处理
            if(req.success){
                $('#qxgl_tree').append(dg2(req.data));
                zzjg_glqx_syydqx(o);
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
function dg2(obj){
    var h = '';
    $(obj).each(function(index,e){
        var a = '<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>';
        var b = '',c='';
        if(e.list.length > 0){
            a = '<span class="glyphicon glyphicon-menu-right" onclick="zzjg_tree_or_show(this)" style="cursor:pointer;"></span>';
            b = dg2(e.list);
        }
        if(e.qxfj != 0)
            c = 'display:none;';
        var html = '<div style="margin-top:5px;'+c+'">'
                   +'     <div>'
//                           +'         <span class="glyphicon glyphicon-menu-right"></span>'
                   + a
                   +'         <input type="checkbox" onclick="qxgl_check(this)" data_id="'+e.uuid+'" data_parent_id="'+e.qxfj+'">'
                   +'         <span style="padding:10px;padding-left:1px;color:red;">'+e.qxmc+'</span>'
//                   +'         <input type="button" class="btn btn-info btn-xs" value="管理权限" onclick="zzjg_glqx_btn(\''+e.uuid+'\')">'
                   +'     </div>'
                   +'     <div style="padding-left:10px;">'
                    + b
                   +'     </div>'
                   +'</div>';
        h += html;
    });
    return h;
}
//判断是否选中上级以及下级
function qxgl_check(obj){
//    当前选中的有上级则默认同时选中上级，取消的时候如果有下级则默认同时取消下级

//如果是选中当前所选的则同时选中上级
    if($(obj).is(':checked')){
        dgsj(obj);
    }else{
        var parent = $(obj).parent('div').next('div');
        var pev = $(parent).find('input[type=checkbox]');
//        $(pev).removeAttr("checked");
        $(pev).each(function(){
            if($(this).is(':checked'))
                this.checked=!this.checked;
        });
    }
}
//递归查找上级
function dgsj(obj){
    var parid = $(obj).attr('data_parent_id');
    $(obj).prop('checked','checked');
    if(parid != '0'){
        $('#qxgl_tree').find('input[type=checkbox]').each(function(index,e){
            var uid = $(e).attr('data_id');
            if(uid == parid)
                dgsj(e);
        });
    }
    return;
}
//获取系统所有权限
function zzjg_glqx_syydqx(o){
    $.ajax({
        url:path+"/qxgl/zzqx/"+o,
        dataType:"json",
        async:false,
        type:"get",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
        },
        success:function(req){
            //请求成功时处理
            if(req.success){
                $('#qxgl_tree').find('input[type=checkbox]').each(function(index,e){
                    $(req.data).each(function(index2,e2){
                        if(e2.qxid == $(e).attr('data_id')){
                            $(e).prop('checked','checked');
                            $(e).next('span').css('color','#00acd6');
                        }
                    });
                });
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
//设定权限
function qxgl_set_qx(){
    var qxstr = '';
    $('#qxgl_tree').find('input[type=checkbox]').each(function(index,e){
        if($(e).is(':checked'))
            qxstr += $(e).attr('data_id')+",";
    });
    $.ajax({
        url:path+"/qxgl/qxgl",
        dataType:"json",
        async:true,
        data:{"qxstr":qxstr,"zzid":zzid},
        type:"post",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
            $('#qxgl_set_qx').addClass("disabled");
        },
        success:function(req){
            //请求成功时处理
            if(!req.success)
                alert(req.message);
            else{
                zzjg_glqx_syydqx(zzid);
            }
        },
        complete:function(){
            //请求完成的处理
            $('#qxgl_set_qx').removeClass("disabled");
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
            alert(textStatus);
        }
    });
}