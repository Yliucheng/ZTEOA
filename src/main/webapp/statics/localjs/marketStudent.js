$(function (){
    //根据学生类型来筛选可选择的班级
    $("#stuType1").change(function (){
        var path = getRealPath();
        var str = "";
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
            },
            datatype:"text"
    })
    })

    //根据选择的班级自动选中对应的学生类型
    $("#stuClass1").change(function (){
        var stuType;
        var str = "";
        $.ajax({
            url:"MarketStudentServlet.do?caozuo=setStuType_ByClass",
            type:"post",
            data:{className:document.getElementById("stuClass1").value},
            success:function (data){
                //清除所有的默认选中
                $(".typeneirong").attr("selected",false);
                //data为本班级的学生类型
                var ee = eval(data);
                stuType = ee;
                //选中指令类型
                $("#stuType1 option[value='" + stuType +  "']" ).attr("selected",true);
                $("#stuType1").val(stuType);
            },
            datatype:"text"
        })
    })
    //正则验证
    //校验姓名是否合理
    $("#name").blur(function (){//失去焦点后进行验证
        var zhongwen = /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/;//中文名字：只能输入2-4位的汉字
        var yingwen = /^[a-zA-Z·s]{3,20}$/;;//英文名字：只能输入3-20的英文，可以有·
        if (!(zhongwen.test($("#name").val()) || yingwen.test($("#name").val()) || $("#name").val()=="")){
            $("#spanName").show()
        }else {
            $("#spanName").hide();
        }
    })
    $("#shangchuan").on("click",function (event) {
        event.preventDefault();
        $("#excelFile").click();
    });

    $("#excelFile").change(function () {
        $("#toSubmit").click();
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
