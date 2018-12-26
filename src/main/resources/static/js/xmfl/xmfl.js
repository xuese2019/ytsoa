var xmfl_page = 1;
$(document).ready(function(){
//条件分页查询
    page($('#xmfl_ser'));
    $('#xmfl_ser').click(function(){
        xmfl_page = 1;
        page(this);
    });
//新增
    $('#xmfl_add_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/xmfl/xmfl",
            dataType:"json",
            async:true,
            data:$('#xmfl_add_form').serialize(),
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
                    $('#xmfl_add_form')[0].reset()
                    $('#xmfl_add_close').click();
                    page($('#xmfl_ser'));
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
//    修改
    $('#xmfl_update_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/xmfl/xmfl",
            dataType:"json",
            async:true,
            data:$('#xmfl_update_form').serialize(),
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
                    $('#xmfl_update_form')[0].reset();
                    $('#xmfl_update_close').click();
                    page($('#xmfl_ser'));
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
    $('#xmfl_table_data').find('tr').remove();
    $.ajax({
        url:path+"/xmfl/xmfl/"+Number(xmfl_page),
        dataType:"json",
        async:true,
        data:$('#xmfl_ser_form').serialize(),
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
                    var tr = '<tr>'
                            +'<td>'+(index+1)+'</td>'
                            +'<td>'+e.xmflmc+'</td>'
                            +'<td>'
                            +'<input type="button" class="btn btn-danger btn-xs" value="删除" onclick="xmfl_delete(\''+e.uuid+'\')">&nbsp;'
                            +'<input type="button" class="btn btn-info btn-xs" value="修改" onclick="xmfl_update(\''+e.uuid+'\')">&nbsp;'
                            +'</td>'
                            +'</tr>';
                    $('#xmfl_table_data').append(tr);
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
        if(Number(xmfl_page) == e)
            $('#page_help').append('<li class="paginate_button active page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
        else
            $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
    });
    if(!obj.isLastPage)
        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+obj.pages+');">末页</a></li>');
}
function click_page(o){
    xmfl_page = o;
    page($('#xmfl_ser'));
}
//删除
function xmfl_delete(o){
    var con = confirm("是否确定删除?");
    if(con){
        $.ajax({
            url:path+"/xmfl/xmfl/"+o,
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
                    page($('#xmfl_ser'));
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
function xmfl_update(o){
    $('#xmfl_update_model_btn').click();
    $.ajax({
        url:path+"/xmfl/oneId/"+o,
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
                $('#xmfl_update_form').find('input[name=uuid]').val(req.data.uuid);
                $('#xmfl_update_form').find('input[name=xmflmc]').val(req.data.xmflmc);
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