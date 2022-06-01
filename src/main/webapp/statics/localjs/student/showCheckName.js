$(function () {
    var flag=true;
    $(".demo-form2").submit(function () {
        //验证成功才提交表单
        return flag;
    })
    //组件失去焦点事件
    $("#name").blur(function () {
        var name = $("#name").val();//姓名
        $("div>span").html('');
        if(name!=""&&!checkName(name)){
            $("#name").select();
            flag=false;
            return false
        }
        flag=true;
    })

})
function checkName(name){
    let regele=/^[\u4e00-\u9fa5a-zA-Z]{1,5}$/
    if(!regele.test(name)){
        $("#nameMes").text("验证失败！");
        return false;
    }
    return  true;
}