$("#clickcha,#clickzhong,#clickhao,#clickgood").click(function(){
	obj = document.getElementsByName("studentNo");
	//学号
	check_studentNo = [];
	for(k in obj){
		if(obj[k].checked){
			check_studentNo.push(obj[k].value);
		}
	}
	var classname2=$("#classname").val();
	var major2=$("#major").val();	
	var result=$(this).val();
	//alert(check_studentNo);
	$.ajax({
		// 提交方式，默认GET
		type : "POST",
		url : "examJServlet", // 提交的服务器地址
		traditional:true, //传入数组，赋值true！！
		data : {"studentNoData":check_studentNo,"result":result}, // 提交到servlet的参数的值
		dataType : "text",
		success : function(data) {
			if(data=="success"){
				alert("录入作业情况成功！");
				location.href="workrecordservlet?classname="+classname2+"&major="+major2+"&pageIndex=1";
			}
		},
		error : function() {
			alert('录入作业情况失败');
			//location.href = "401.jsp"
		}
	});
});


$("#selectall").click(function() {
	// 判断全选这个按钮，有没有被选中
	if ($(this).is(":checked")) {
		// 选中了，就把[name='checkBox']3个复选框 依次加上选中属性
		$("[name=studentNo]").each(function() {
			// prop是设置属性 attribute是添加属性 移除属性是removeattribute
			$(this).prop("checked", true);
			$("#selectall").prop("checked", true);
		});
	} else {
		// 没选中，就把false赋给3个复选框
		$("[name=studentNo]").each(function() {
			$(this).prop("checked", false);
			$("#selectall").prop("checked", false);
		});
	}
});

//全选(有一个单选未选上，取消全选)

$("[name=studentNo]").click(function () {
	var obj = document.getElementsByName("studentNo");
    for (var i = 0; i < obj.length; i++) {
        if ($(obj[i]).is(":checked")) {
            $("#selectall").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("#selectall").each(function () {
                $(this).prop("checked", false);
            });
            break;
        }
    }
});