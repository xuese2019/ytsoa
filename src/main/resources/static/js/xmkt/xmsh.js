var xmkt_page = 1;
$(document).ready(function(){
//条件分页查询
    page($('#xmkt_ser'));
    $('#xmkt_ser').click(function(){
        xmkt_page = 1;
        page(this);
    });
});
//条件分页查询
function page(obj){
    $('#xmkt_table_data').find('tr').remove();
    $.ajax({
        url:path+"/xmkt/xmsh/"+Number(xmkt_page),
        dataType:"json",
        async:true,
        data:$('#xmkt_ser_form').serialize(),
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
                            +'<td>'+e.xmmc+'</td>'
                            +'<td>'+e.wtf+'</td>'
                            +'<td>'+e.wtsj+'</td>'
                            +'<td>'+e.bsjdw+'</td>'
                            +'<td>'+e.fxpg+'</td>'
                            +'<td>'+e.ywlx+'</td>'
                            +'<td>'+e.xmfl+'</td>'
                            +'<td>'+e.cjbm+'</td>'
                            +'<td>'+e.cjr+'</td>'
                            +'<td>'+e.qrwcshr+'</td>'
                            +'<td>'+e.xmkssj+'</td>'
                            +'<td>'+e.xmjssj+'</td>'
                            +'<td>'+e.yjsf+'</td>'
                            +'<td>'
//                            +'<input type="button" class="btn btn-info btn-xs" value="修改" onclick="xmkt_update(\''+e.uuid+'\')">&nbsp;'
                            + ((e.xmFlag == 3 && $('#xmkt_shiro_shxm').length > 0) ? '<input type="button" class="btn btn-info btn-xs" value="审核" onclick="xmkt_cjxm(\''+e.uuid+'\')">&nbsp;' : '')
                            +'</td>'
                            +'</tr>';
                    $('#xmkt_table_data').append(tr);
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
        if(Number(xmkt_page) == e)
            $('#page_help').append('<li class="paginate_button active page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
        else
            $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
    });
    if(!obj.isLastPage)
        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+obj.pages+');">末页</a></li>');
}
function click_page(o){
    xmkt_page = o;
    page($('#xmkt_ser'));
}
//删除
function xmkt_delete(o){
    var con = confirm("是否确定删除?");
    if(con){
        $.ajax({
            url:path+"/xmkt/xmkt/"+o,
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
                    page($('#xmkt_ser'));
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
function xmkt_update(o){
    $('#xmkt_update_model_btn').click();
    $.ajax({
        url:path+"/xmkt/oneId/"+o,
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
                $('#xmkt_update_form').find('input[name=uuid]').val(req.data.uuid);
                $('#xmkt_update_form').find('input[name=xmktmc]').val(req.data.xmktmc);
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
//承接项目
function xmkt_cjxm(o){
    $.ajax({
        url:path+"/xmkt/cjxm/"+o,
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
            if(!req.success)
                alert(req.message);
            else
                page($('#xmkt_ser'));
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