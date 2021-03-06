var xmkt_page = 1;
$(document).ready(function(){
//条件分页查询
    page($('#xmkt_ser'));
    $('#xmkt_ser').click(function(){
        xmkt_page = 1;
        page(this);
    });
//    修改
    $('#xmkt_update_btn').click(function(){
        var obj = $(this);
        $.ajax({
            url:path+"/xmkt/xmkt",
            dataType:"json",
            async:true,
            data:$('#xmkt_update_form').serialize(),
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
                    $('#xmkt_update_form')[0].reset();
                    $('#xmkt_update_close').click();
                    page($('#xmkt_ser'));
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
    $('#xmkt_table_data').find('tr').remove();
    $.ajax({
        url:path+"/xmkt/xmkt/"+Number(xmkt_page),
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
                    var del = ''
                    if($('#xmkt_shiro_del').length > 0)
                        del = '<input type="button" class="btn btn-danger btn-xs" value="删除" onclick="xmkt_delete(\''+e.uuid+'\')">&nbsp;';
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
//                            +'<td>'+e.qrwcshr+'</td>'
                            +'<td>'+e.xmkssj+'</td>'
                            +'<td>'+e.xmjssj+'</td>'
                            +'<td>'+e.yjsf+'</td>'
                            +'<td>'
                            +(e.xmFlag == 0 ? '未承接' : '')
                            +(e.xmFlag == 1 ? '已承接,项目进行中...' : '')
                            +(e.xmFlag == 2 ? '已完成需确认' : '')
                            +(e.xmFlag == 3 ? '已确认完成' : '')
                            +'</td>'
                            +'<td>'
                            + del
//                            +'<input type="button" class="btn btn-info btn-xs" value="修改" onclick="xmkt_update(\''+e.uuid+'\')">&nbsp;'
                            + ((e.xmFlag == 0 && $('#xmkt_shiro_cjxm').length > 0) ? '<input type="button" class="btn btn-info btn-xs" value="承接" onclick="xmkt_cjxm_btn(\''+e.uuid+'\')">&nbsp;' : '')
                            + (e.xmFlag == 1 ? '<input type="button" class="btn btn-info btn-xs" value="项目完成，申请确认">&nbsp;' : '')
                            + (e.xmFlag == 1 ? '<input type="button" class="btn btn-info btn-xs" value="详情" onclick="xmkt_xmxq(\''+e.uuid+'\')">&nbsp;' : '')
                            + ((e.xmFlag == 2 && $('#xmkt_shiro_cjxm').length > 0) ? '<input type="button" class="btn btn-info btn-xs" value="审核">&nbsp;' : '')
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
//承接项目弹窗
function xmkt_cjxm_model(o){
    $.ajax({
        url:path+"/xmkt/oneId/"+o,
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
            else{
                $('#xmkt_cjxm_model_btn').click();
                $('#xmkt_cjxm_form').find('input[name=uuid]').val(o);
                $('#xmmc').val(req.data.xmmc);
                $('#wtf').val(req.data.wtf);
                $('#wtsj').val(req.data.wtsj);
                $('#bsjdw').val(req.data.bsjdw);
                $('#fxpg').val(req.data.fxpg);
                $('#ywlx').val(req.data.ywlx);
                $('#xmfl').val(req.data.xmfl);
                $('#cjbm').val(req.data.cjbm);
                $('#cjr').val(req.data.cjr);
                $('#xmkssj').val(req.data.xmkssj);
                $('#xmjssj').val(req.data.xmjssj);
                $('#yjsf').val(req.data.yjsf);
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
//承接项目提交
function xmkt_cjxm_btn(o){
    var con = confirm("确定承接该项目吗?");
    if(con){
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
}
//新的页面打开项目详情
function xmkt_xmxq(o){
    window.open(path+"/views/xmkt/xmxq?id="+o);
}
//选择人员相关
//var yggl_page = 1;
//function xmkt_cjxm_select_people_model(){
//    $('#xmkt_cjxm_close').click();
//    yggl_page = 1;
//    $('#xmkt_cjxm_select_people_model_btn').click();
////    account_page($('#yggl_ser'));
//}
//function xmkt_cjxm_select_people_model2(){
//    yggl_page = 1;
//    account_page($('#yggl_ser'));
//}
////条件分页查询
//function account_page(obj){
//    $('#yggl_table_data').find('tr').remove();
//    $.ajax({
//        url:path+"/account/page/"+Number(yggl_page),
//        dataType:"json",
//        async:true,
//        data:$('#yggl_ser_form').serialize(),
//        type:"get",
//        cache:false,//关闭缓存
//        ifModified :true,//关闭缓存
//        beforeSend:function(){
//            //请求前的处理
//            $(obj).addClass("disabled");
//        },
//        success:function(req){
//            //请求成功时处理
//            if(req.success){
//                $(req.data.list).each(function(index,e){
//                    var tr = '<tr>'
//                            +'<td>'+(index+1)+'</td>'
//                            +'<td>'+e.name+'</td>'
//                            +'<td>'+e.account+'</td>'
//                            +'<td>'+e.sex+'</td>'
//                            +'<td>'+e.bmid+'</td>'
//                            +'<td>'+e.phone+'</td>'
//                            +'<td>'
//                            +'<input type="button" class="btn btn-info btn-xs" value="选择" onclick="xmkt_cjxm_select_people_model_select_add(\''+e.uuid+'\',\''+e.name+'\')">'
//                            +'</td>'
//                            +'</tr>';
//                    $('#yggl_table_data').append(tr);
//                });
////                pageHelp(req.data);
//            }else
//                alert(req.message);
//        },
//        complete:function(){
//            //请求完成的处理
//            $(obj).removeClass("disabled");
//        },
//        error:function(XMLHttpRequest, textStatus, errorThrown){
//            //请求出错处理
//        }
//    });
//}
////选择人员
//function xmkt_cjxm_select_people_model_select_add(a,b){
//    var i = 0;
//    $('#xmkt_cjxm_select_people').find('input[type=button]').each(function(index,e){
//        if($(e).attr('data_id') == a){
//            alert('该员工已选择');
//            i = 1;
//            return;
//        }
//    });
//    if(i > 0)
//        return;
//    var usr_btn = '<input type="button" class="btn btn-info btn-xs" data_id="'+a+'" value="'+b+'" ondblclick="javascript:$(this).remove();" title="双击删除">&nbsp;&nbsp;';
//    $('#xmkt_cjxm_select_people').append(usr_btn);
//}
////选择人员保存
//function xmkt_cjxm_select_people_add(){
//
//}
//选择人员分页相关
//function pageHelp(obj){
//    $('#page_help').find('.page_help').remove();
//    $('#page_pages').html(obj.pages);
//    $('#page_total').html(obj.total);
//    if(!obj.isFirstPage)
//        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page(1);">首页</a></li>');
//    $(obj.navigatepageNums).each(function(index,e){
//        if(Number(yggl_page) == e)
//            $('#page_help').append('<li class="paginate_button active page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
//        else
//            $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+e+');">'+e+'</a></li>');
//    });
//    if(!obj.isLastPage)
//        $('#page_help').append('<li class="paginate_button page_help"><a href="javascript:click_page('+obj.pages+');">末页</a></li>');
//}
//function click_page(o){
//    yggl_page = o;
//    page($('#yggl_ser'));
//}