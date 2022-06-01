/**
 * 
 */

$(".delete").mouseover(function(){
	$(this).css("background-color","#26B99A");

});
$(".delete").mouseout(function(){
	$(this).css("background-color","white");
	});
$(function (){
	$(".delete").click(function(){

		var classid = $(this).attr("value")
		$.ajax({
			type:"GET",
			url:"classServlet.do?param=todelete",
			data:{"classid":classid},
			dataType:"text",
			success:function (data){
				var trim = data.trim();
				// console.log(trim)
				// console.log(trim.length)
				// console.log("true".length)
				if(trim=="true"){
					var b = confirm("该班级没有学生，是否删除班级！");
					if (b){
						deleteClass(classid)
					}
				}
				if(trim=="false"){
					alert("删除失败，该班级还有学生")
				}
			},
			error:function (){

				alert("查询失败")
			}
		})
	});


})

function deleteClass(classid){
	$.get("classServlet.do?param=delete",{"classid":classid},function (data){
		var trim = data.trim();
		if(trim=="true"){
				alert("删除成功！");
				window.location.reload();
		}
		if(trim=="false"){
			alert("删除失败!")
		}
	})
}


// alert(classid)
// $.ajax({
// 	// 提交方式，默认GET
// 	type : "GET",
// 	url : "classServlet.do", // 提交的服务器地址
// 	// traditional:true, //传入数组，赋值true！！
// 	data : {"classid":classid,"param":"todelete"}, // 提交到servlet的参数的值
// 	dataType : "text",
// 	success : function(data) {
// 		if(data=="true"){
// 			alert("该班级没有学生，删除成功！");
// 			location.href="classServlet";
// 		}else if(data=="false"){
// 			alert("删除失败，该班级还有学生")
// 		}
// 	},
// 	error : function() {
// 		alert('失败');
// 	}
// });