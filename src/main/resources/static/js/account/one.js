$(document).ready(function(){
    getUser();
});
function getUser(){
    $.ajax({
        url:path+"/account/account/"+$('#one_user_id').text(),
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
                $('#user_account').text(req.data.account);
            }
        },
        complete:function(){
            //请求完成的处理
        },
        error:function(){
            //请求出错处理
        }
    });
}