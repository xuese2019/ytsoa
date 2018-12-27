$(document).ready(function(){
    $('#wtsj').datepicker({
          autoclose: true,
        todayHighlight: true,
        language:"zh-CN",
        format:"yyyy-mm-dd"
    });
//		日期
    DatePicker("#xmkssj","#xmjssj");
//    获取业务类型
    getYwlx();
//    获取项目分类
    getXmfl();
//    新增提交
    $('#xmwp_btn').click(function(index,e){
        var obj = $(this);
        $.ajax({
            url:path+"/xmwp/xmwp",
            dataType:"json",
            async:true,
            data:$('#xmwp_apply_btn').serialize(),
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
                    $('#xmwp_apply_btn')[0].reset()
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
function DatePicker(beginSelector,endSelector){
    // 仅选择日期
    $(beginSelector).datepicker(
        {
            language:  "zh-CN",
            autoclose: true,
            startView: 0,
            format: "yyyy-mm-dd",
            clearBtn:true,
            todayBtn:false
//            endDate:new Date()
        }).on('changeDate', function(ev){
            if(ev.date){
                $(endSelector).datepicker('setStartDate', new Date(ev.date.valueOf()))
            }else{
                $(endSelector).datepicker('setStartDate',null);
            }
        })

        $(endSelector).datepicker(
        {
            language:  "zh-CN",
            autoclose: true,
            startView:0,
            format: "yyyy-mm-dd",
            clearBtn:true,
            todayBtn:false
//            endDate:new Date()
        }).on('changeDate', function(ev){
            if(ev.date){
                $(beginSelector).datepicker('setEndDate', new Date(ev.date.valueOf()))
            }else{
                $(beginSelector).datepicker('setEndDate',new Date());
            }
        })
    }
//获取业务类型
function getYwlx(){
    $('#ywlx').find('option').remove();
    $('#ywlx').append('<option value="">请选择</option>');
    $.ajax({
        url:path+"/ywlx/ywlx",
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
                $(req.data).each(function(index,e){
                    var option = '<option value="'+e.uuid+'">'+e.ywlxmc+'</option>';
                    $('#ywlx').append(option);
                });
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
        }
    });
}
//获取项目分类
function getXmfl(){
    $('#xmfl').find('option').remove();
    $('#xmfl').append('<option value="">请选择</option>');
    $.ajax({
        url:path+"/xmfl/xmfl",
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
                $(req.data).each(function(index,e){
                    var option = '<option value="'+e.uuid+'">'+e.xmflmc+'</option>';
                    $('#xmfl').append(option);
                });
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
        }
    });
}
//获取组织机构
function getZzjg(){
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
                   +'         <input type="button" class="btn btn-info btn-xs" value="选择" onclick="apply_zzjg_sel_btn(\''+e.uuid+'\',\''+e.zzjgmc+'\')">'
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
//选择部门弹窗
function apply_zzjg_select(){
    $('#apply_zzjg_select_model_btn').click();
    getZzjg();
}
//选择部门
function apply_zzjg_sel_btn(a,b){
    $('#cjbmid').val(a);
    $('#cjbmtext').val(b);
    $('#cjrid').val('');
    $('#cjrtext').val('');
}
//    选择承接人弹窗
function apply_account_select(){
    if($('#cjbmid').val() == ''){
        alert('请先选择承接部门');
        return false;
    }
    $('#apply_account_select_model_btn').click();
    $('#account_table_data').find('tr').remove();
    $.ajax({
        url:path+"/account/bmid/"+$('#cjbmid').val(),
        dataType:"json",
        async:true,
        data:$('#yggl_ser_form').serialize(),
        type:"get",
        cache:false,//关闭缓存
        ifModified :true,//关闭缓存
        beforeSend:function(){
            //请求前的处理
        },
        success:function(req){
            //请求成功时处理
            if(req.success){
                $(req.data).each(function(index,e){
                    var tr = '<tr>'
                            +'<td>'+(index+1)+'</td>'
                            +'<td>'+e.name+'</td>'
                            +'<td>'+e.bmid+'</td>'
                            +'<td>'
                            +'<input type="button" class="btn btn-info btn-xs" value="选择" onclick="apply_account_select_add(\''+e.uuid+'\',\''+e.name+'\')">'
                            +'</td>'
                            +'</tr>';
                    $('#account_table_data').append(tr);
                });
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            //请求出错处理
        }
    });
}
//选择承接人
function apply_account_select_add(a,b){
    $('#cjrid').val(a);
    $('#cjrtext').val(b);
}