/**
 * 
 */

$(".shanchu").mouseover(function(){
  $(this).css("background-color","white");
});
$(".shanchu").mouseout(function(){
	  $(this).css("background-color","#26B99A");
	});
$(".delete").click(function(){

     var classid=$(this).val();
	
	$.ajax({
		// 提交方式，默认GET
		type : "POST",
		url : "classdel", // 提交的服务器地址
		traditional:true, //传入数组，赋值true！！
		data : {"classid":classid}, // 提交到servlet的参数的值
		dataType : "text",
		success : function(data) {
			if(data=="true"){
				alert("该班级没有学生，删除成功！");
				location.href="classServlet";
			}else if(data=="false"){
				alert("删除失败，该班级还有学生")
			}
		},
		error : function() {
			alert('失败');
		}
	});
});