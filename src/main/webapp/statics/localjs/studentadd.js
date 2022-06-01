$(function() {
	$("#major")
			.change(
					function() {
						var regionMajor = $("#major").val();
						// alert("id:"+regionParentId);
						if (regionMajor != null && regionMajor != "") {
							$.ajax({
										// 提交方式，默认GET
										type : "POST",
										url : "../getBanJi", // 提交的服务器地址
										data : "major=" + regionMajor, // 提交到servlet的参数的值
										dataType : "json",// 提交到servlet后返回的结果的数据类型，text是纯文本类型
										success : function(data) {
											$("#classid").html("");
											var options = "<option value=''>---请选择---</option>";
											for (var i = 0; i < data.length; i++) {
												options += "<option value='"
														+ data[i].classid
														+ "'>"
														+ data[i].classname
														+ "</option>"
											}
											$("#classid").html(options);
										},
										error : function() {
											alert('加载二级菜单失败');
										//	location.href = "401.jsp"
										}
									});
						} else {
							// 假设没有获取带ID，都应该是"请选择"
							$("#classid").html("");
							var options = "<option value=''>---请选择---</option>";
							$("#classid").html(options);
						}
					});
	
});