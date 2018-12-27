var xmwp_page = 1;
$(document).ready(function(){
//条件分页查询
    page($('#xmwp_ser'));
    $('#xmwp_ser').click(function(){
        xmwp_page = 1;
        page(this);
    });
//    修改
    $('#xmwp_update_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/xmwp/xmwp",
            dataType:"json",
            async:true,
            data:$('#xmwp_update_form').serialize(),
            type:"put",
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
                    $('#xmwp_update_form')[0].reset();
                    $('#xmwp_update_close').click();
                    page($('#xmwp_ser'));
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
//条件分页查询
function page(obj){
    $('#xmwp_table_data').find('tr').remove();
    $.ajax({
        url:path+"/xmwp/xmwp/"+Number(xmwp_page),
        dataType:"json",
        async:true,
        data:$('#xmwp_ser_form').serialize(),
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
                    var del = ''
                    if($('#xmwp_shiro_del').length > 0)
                        del = '<input type="button" class="btn btn-danger btn-xs" value="删除" onclick="xmwp_delete(\''+e.uuid+'\')">&nbsp;';
                    var tr = '<tr>'
                            +'<td>'+(index+1)+'</td>'
                            +'<td>'+e.xmmc+'</td>'
                            +'<td>'+e.wtf+'</td>'
                            +'<td>'+e.wtsj+'</td>'
                            +'<td>'+e.bsjdw+'</td>'
                            +'<td>'+e.fxpg+'</td>'
                            +'<td>'+e.ywlx+'</td>'
                            +'<td>'+e.xmfl+'</td>'
                            +'<td>'+e.cjbm+'</td>'
                            +'<td>'+e.cjr+'</td>'
                            +'<td>'+e.xmkssj+'</td>'
                            +'<td>'+e.xmjssj+'</td>'
                            +'<td>'+e.yjsf+'</td>'
                            +'<td>'
                            + del
//                            +'<input type="button" class="btn btn-info btn-xs" value="修改" onclick="xmwp_update(\''+e.uuid+'\')">&nbsp;'
                            +'</td>'
                            +'</tr>';
                    $('#xmwp_table_data').append(tr);
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
        if(Number(xmwp_page) == e)
            $('#page_help').append('<li class="paginate_button active page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
        else
            $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
    });
    if(!obj.isLastPage)
        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+obj.pages+');">末页</a></li>');
}
function click_page(o){
    xmwp_page = o;
    page($('#xmwp_ser'));
}
//删除
function xmwp_delete(o){
    var con = confirm("是否确定删除?");
    if(con){
        $.ajax({
            url:path+"/xmwp/xmwp/"+o,
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
                if(!req.success)
                    alert(req.message);
                else
                    page($('#xmwp_ser'));
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
}
//修改
function xmwp_update(o){
    $('#xmwp_update_model_btn').click();
    $.ajax({
        url:path+"/xmwp/oneId/"+o,
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
            if(!req.success)
                alert(req.message);
            else{
                $('#xmwp_update_form').find('input[name=uuid]').val(req.data.uuid);
                $('#xmwp_update_form').find('input[name=xmwpmc]').val(req.data.xmwpmc);
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