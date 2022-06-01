$(function (){
    //页面加载完成后，根据班级类型级联可选班级
    var str = "";
    var path = getRealPath();
    var className = $("#stuClass1").val();
    $.ajax({
        url:path+"/MarketStudentServlet.do?caozuo=jilian",
        type:"post",
        data:{stuType:document.getElementById("stuType1").value},
        success:function (data){
            $(".neirong").remove();
            var ee = eval(data);
            var i=0;
            for(i;i<ee.length;i++){
                str="<option class = 'neirong' value = '"+ee[i]+"'>"+ee[i]+"</option>"
                $("#stuClass1").append(str);
            }
            $("#stuClass1 option[value='" + className + "']" ).attr("selected",true);
        },
        datatype:"text"
    })
})

//获取项目跟路径请求
function getRealPath() {
    var curwwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var position = curwwwPath.indexOf(pathName);
    var localhostPath = curwwwPath.substring(0, position);
    var projectName = pathName.substring(0, pathName.substring(1).indexOf('/') + 1);
    var realPath = localhostPath +projectName;
    return realPath;
}