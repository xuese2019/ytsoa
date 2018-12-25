var ywlx_page = 1;
$(document).ready(function(){
//条件分页查询
    page($('#ywlx_ser'));
    $('#ywlx_ser').click(function(){
        ywlx_page = 1;
        page(this);
    });
//新增
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
                    alert(req.message);
                else{
                    $('#ywlx_add_form')[0].reset()
                    $('#ywlx_add_close').click();
                    page($('#ywlx_ser'));
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
    $('#ywlx_update_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/ywlx/ywlx",
            dataType:"json",
            async:true,
            data:$('#ywlx_update_form').serialize(),
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
                    $('#ywlx_update_form')[0].reset()
                    $('#ywlx_update_close').click();
                    page($('#ywlx_ser'));
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
    $('#ywlx_table_data').find('tr').remove();
    $.ajax({
        url:path+"/ywlx/ywlx/"+Number(ywlx_page),
        dataType:"json",
        async:true,
        data:$('#ywlx_ser_form').serialize(),
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
                            +'<td>'+e.ywlxmc+'</td>'
                            +'<td>'
                            +'<input type="button" class="btn btn-danger btn-xs" value="删除" onclick="ywlx_delete(\''+e.uuid+'\')">&nbsp;'
                            +'<input type="button" class="btn btn-info btn-xs" value="修改" onclick="ywlx_update(\''+e.uuid+'\')">&nbsp;'
                            +'</td>'
                            +'</tr>';
                    $('#ywlx_table_data').append(tr);
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
        if(Number(ywlx_page) == e)
            $('#page_help').append('<li class="paginate_button active page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
        else
            $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
    });
    if(!obj.isLastPage)
        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+obj.pages+');">末页</a></li>');
}
function click_page(o){
    ywlx_page = o;
    page($('#ywlx_ser'));
}
//删除
function ywlx_delete(o){
    var con = confirm("是否确定删除?");
    if(con){
        $.ajax({
            url:path+"/ywlx/ywlx/"+o,
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
                    page($('#ywlx_ser'));
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
function ywlx_update(o){
    $('#ywlx_update_model_btn').click();
    $.ajax({
        url:path+"/ywlx/oneId/"+o,
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
                $('#ywlx_update_form').find('input[name=uuid]').val(req.data.uuid);
                $('#ywlx_update_form').find('input[name=ywlxmc]').val(req.data.ywlxmc);
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