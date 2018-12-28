$(document).ready(function(){
    $('#uuid').text(getParameter('id'));
    getXmJcXx($('#uuid').text());
//    详情信息切换
    $('.xmxqxxqh').on('click',function(){
        $('#xmxqxxqh').find('.row').hide();
        var did = $(this).attr('data_id');
        var d = '#'+did;
        $(d).show();
    });
});
//获取地址参数
function getParameter(param){
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);
    return query.substring(iStart, iEnd);
}
function getXmJcXx(o){
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
                $('#xmmc').val(req.data.xmmc);
                document.title = req.data.xmmc;
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