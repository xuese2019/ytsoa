var yggl_page = 1;
$(document).ready(function(){
    //条件分页查询
    page($('#yggl_ser'));
    $('#yggl_ser').click(function(){
        yggl_page = 1;
        page(this);
    });
    $('#rzrq').datepicker({
          autoclose: true,
        todayHighlight: true,
        language:"zh-CN",
        format:"yyyy-mm-dd"
    });
    $('#yggl_add_btn').click(function(){
        var obj = $(this);
            $.ajax({
                url:path+"/account/account",
                dataType:"json",
                async:true,
                data:$('#yggl_add_form').serialize(),
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
                    else{
                        $('#yggl_add_form')[0].reset();
                        $('#yggl_add_close').click();
                    }
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
//递归
function dg(obj){
    var h = '';
    $(obj).each(function(index,e){
        var a = '<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>';
        var b = '',c='';
        if(e.list.length > 0){
            a = '<span class="glyphicon glyphicon-menu-right" onclick="yggl_zzjg_tree_or_show(this)" style="cursor:pointer;"></span>';
            b = dg(e.list);
        }
        if(e.zzjgfj != 0)
            c = 'display:none;';
        var html = '<div style="margin-top:5px;'+c+'">'
                   +'     <div>'
//                           +'         <span class="glyphicon glyphicon-menu-right"></span>'
                   + a
                   +'         <span style="padding:10px;padding-left:1px;">'+e.zzjgmc+'</span>'
                   +'         <input type="button" class="btn btn-info btn-xs" value="选择" onclick="yggl_zzjg_sel_btn(\''+e.uuid+'\',\''+e.zzjgmc+'\')">'
                   +'     </div>'
                   +'     <div style="padding-left:10px;">'
                    + b
                   +'     </div>'
                   +'</div>';
        h += html;
    });
    return h;
}
//获取组织机构
function getZzjg(){
    $('#yggl_zzjg_tree').find('div').remove();
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
                $('#yggl_zzjg_tree').append(dg(req.data));
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
//选择部门弹窗
function yggl_zzjg_select(){
    $('#yggl_zzjg_select_model_btn').click();
    getZzjg();
}
//显示隐藏
function yggl_zzjg_tree_or_show(obj){
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
//选择部门
function yggl_zzjg_sel_btn(a,b){
    $('#bmid').val(a);
    $('#bmidtext').val(b);
}
//条件分页查询
function page(obj){
    $('#yggl_table_data').find('tr').remove();
    $.ajax({
        url:path+"/account/page/"+Number(yggl_page),
        dataType:"json",
        async:true,
        data:$('#yggl_ser_form').serialize(),
        type:"get",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
            $(obj).addClass("disabled");
        },
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data.list).each(function(index,e){
                    var del = '';
                    if($('#accountDelete').length > 0)
                        del = '<input type="button" class="btn btn-danger btn-xs" value="删除" onclick="yggl_delete(\''+e.uuid+'\')">&nbsp;';
                    var upd = '';
                    if($('#accountUpdate').length > 0)
                        upd = '<input type="button" class="btn btn-info btn-xs" value="修改" onclick="yggl_update(\''+e.uuid+'\')">&nbsp;';
                    var tr = '<tr>'
                            +'<td>'+(index+1)+'</td>'
                            +'<td>'+e.name+'</td>'
                            +'<td>'+e.account+'</td>'
                            +'<td>'+e.sex+'</td>'
                            +'<td>'+e.bmid+'</td>'
                            +'<td>'+e.phone+'</td>'
                            +'<td>'+e.systimes+'</td>'
                            +'<td>'+e.rzrq+'</td>'
                            +'<td>'+(e.isLogin == 'Y' ? '是' : '否')+'</td>'
                            +'<td>'
                            + del
                            + upd
                            +'</td>'
                            +'</tr>';
                    $('#yggl_table_data').append(tr);
                });
                pageHelp(req.data);
            }
        },
        complete:function(){
            //请求完成的处理
            $(obj).removeClass("disabled");
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
        }
    });
}
//分页相关
function pageHelp(obj){
    $('#page_help').find('.page_help').remove();
    $('#page_pages').html(obj.pages);
    $('#page_total').html(obj.total);
    if(!obj.isFirstPage)
        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page(1);">首页</a></li>');
    $(obj.navigatepageNums).each(function(index,e){
        if(Number(yggl_page) == e)
            $('#page_help').append('<li class="paginate_button active page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
        else
            $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
    });
    if(!obj.isLastPage)
        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+obj.pages+');">末页</a></li>');
}
function click_page(o){
    yggl_page = o;
    page($('#yggl_ser'));
}