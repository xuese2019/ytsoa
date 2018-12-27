var xmgl_page = 1;
$(document).ready(function(){
//条件分页查询
    page($('#xmgl_ser'));
    $('#xmgl_ser').click(function(){
        xmgl_page = 1;
        page(this);
    });
//    修改
    $('#xmgl_update_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/xmgl/xmgl",
            dataType:"json",
            async:true,
            data:$('#xmgl_update_form').serialize(),
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
                    $('#xmgl_update_form')[0].reset();
                    $('#xmgl_update_close').click();
                    page($('#xmgl_ser'));
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
    $('#xmgl_table_data').find('tr').remove();
    $.ajax({
        url:path+"/xmgl/xmgl/"+Number(xmgl_page),
        dataType:"json",
        async:true,
        data:$('#xmgl_ser_form').serialize(),
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
                    if($('#xmgl_shiro_del').length > 0)
                        del = '<input type="button" class="btn btn-danger btn-xs" value="删除" onclick="xmgl_delete(\''+e.uuid+'\')">&nbsp;';
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
//                            +'<input type="button" class="btn btn-info btn-xs" value="修改" onclick="xmgl_update(\''+e.uuid+'\')">&nbsp;'
                            +'</td>'
                            +'</tr>';
                    $('#xmgl_table_data').append(tr);
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
        if(Number(xmgl_page) == e)
            $('#page_help').append('<li class="paginate_button active page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
        else
            $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
    });
    if(!obj.isLastPage)
        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+obj.pages+');">末页</a></li>');
}
function click_page(o){
    xmgl_page = o;
    page($('#xmgl_ser'));
}
//删除
function xmgl_delete(o){
    var con = confirm("是否确定删除?");
    if(con){
        $.ajax({
            url:path+"/xmgl/xmgl/"+o,
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
                    page($('#xmgl_ser'));
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
function xmgl_update(o){
    $('#xmgl_update_model_btn').click();
    $.ajax({
        url:path+"/xmgl/oneId/"+o,
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
                $('#xmgl_update_form').find('input[name=uuid]').val(req.data.uuid);
                $('#xmgl_update_form').find('input[name=xmglmc]').val(req.data.xmglmc);
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