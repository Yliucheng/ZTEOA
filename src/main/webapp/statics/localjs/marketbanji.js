$(function() {
	$("#classtype")
			.change(
					function() {
						var regionMajor = $("#classtype").val();
						if (regionMajor != null && regionMajor != "") {
							$.ajax({
										// 提交方式，默认GET
										type : "POST",
										url : "marketBanJi", // 提交的服务器地址
										data : "classtype=" + regionMajor, // 提交到servlet的参数的值
										dataType : "json",// 提交到servlet后返回的结果的数据类型，text是纯文本类型
										success : function(data) {
											$("#classname").html("");
											var options = "<option value=''>---请选择---</option>";
											for (var i = 0; i < data.length; i++) {
												options += "<option value="+ data[i].classname+">"
														+ data[i].classname + "</option>"
											}
											$("#classname").html(options);
										},
										error : function() {
											alert('加载二级菜单失败');
											//location.href = "401.jsp"
										}
									});
						} else {
							// 假设省没有获取带ID，下面的市、区都应该是"请选择"
							$("#classname").html("");
							var options = "<option value=''>---请选择---</option>";
							$("#classname").html(options);
						}
					});
	
	  $("#queryStudentform").submit(function(){
		   var claVal=$("#classname").val();
		   if(null==claVal||""==claVal){
			   alert("请选择班级再查询");
			   return false;
		   }
		   return true;
	   }); 
});