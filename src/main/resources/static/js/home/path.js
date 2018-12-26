//获取绝对地址，确保每次的ajax都是正确的请求地址
var curWwwPath=window.document.location.href;
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8083
var localhostPaht=curWwwPath.substring(0,pos);
//获取带"/"的项目名，如：/uimcardprj
//var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var path = localhostPaht;

//内部容器跳转页面
function toPage(o){
    $.ajax({
        url:o,
        type:"GET",
        dataType:"html",
        success:function(result){
            $("#body_content").html(result);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
            $.ajax({
                url:path+"/views/error/404",
                type:"GET",
                dataType:"html",
                success:function(result){
                    $("#body_content").html(result);
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert('页面解析错误或页面不存在');
                }
            });
        }
    });
}