$(function() {
	 $("#query").submit(function(){
		   var name=$("#name").val();
		   var occupation=$("#occupation").val();
		   var education=$("#education").val();
		   var logincode=$("#logincode").val();
		   var userpwd=$("#userpwd").val();
           if(null==name||""==name){
			   alert("请填写学生姓名");
			   return false;
		   }else if(null==occupation||""==occupation){
			   alert("请填写院校");
			   return false;
		   }else if(null==education||""==education){
			   alert("请填写学历");
			   return false;
		   }else if(null==logincode||""==logincode){
			   alert("请填写登录账号");
			   return false;
		   }else if(null==userpwd||""==userpwd){
			   alert("请填写登录密码");
			   return false;
		   }
		   return true;
	   }); 
});
