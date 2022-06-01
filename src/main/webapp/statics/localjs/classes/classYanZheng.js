$(function() {
	 $("#query").submit(function(){

		   var studytype=$("[name=studytype]").val();
		   var classname=$("[name=classname]").val();
           if(null==studytype||""==studytype){
			   alert("请填写专业类型11");
			   return false;
		   }else if(null==classname||""==classname){
			   alert("请填写班级名称");
			   return false;
		   }
		   return true;
	   });
	 $("#send").click(function (){
	 	$("#classname").val("")
	 })


});
function check(th) {
	// str1 = /^[a-zA-Z]([a-zA-Z0-9\u4e00-\u9fa5]{2,10})$/
	str1 = /^(java|ui|web)([0-9]{1,2})班$/
	var flag=true;
	var regExp = new RegExp(str1);
	flag = regExp.test($(th).val())
	var classname=$(th).val();
	$("#classMes").html("")
	if (!flag) {
		$("#classMes").html("账号只能包含字母数字中文")
		flag = false;
	}else{
		$.ajax({
			type:"GET",
			url:"classServlet.do?param=pd",
			data:"classname="+classname,
			dataType:"text",
			success:function (data){
				// alert(data)
				if (data=="true"){
					$("#classMes").html("可以使用")
					flag = true;
				}else if(data=="false"){
					$("#classMes").html("该班级已存在")
					flag = false;
				}
			}
		})
	}
	$("#query").submit(function(){
		return flag;
	})
}