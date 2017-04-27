//判断用户名是否已经被注册过 
var isRegisted=true;     
function checkName(){
	var newname=document.form1.newname.value;
	 if( checknewname(newname)==false){
		 isRegisted=false;
		}else{
			if(isExist(newname)==false){
				 isRegisted=false;
				}
		}
	
	return isRegisted;
}

 function checknewname(newname){
		var reg=/^\w{4,12}$/;
	if(newname == "")
	{
		document.getElementById("tishi").innerHTML="<span style='color:red;'>请输入用户名</span>";
		return false;
	}
	if(reg.test(newname)==false){
		document.getElementById("tishi").innerHTML="<span style='color:red;'>请输入4-12位的用户名</span>";
		return false;
		}
 }	
//检验用户名是不是非法	
 var isRegisted=true; 
 function isExist(newname) {
		$.ajax({
			url : "../CheckUserName", //请求的url地址 
			dataType : "html", //返回格式为html 
			async : true, //请求是否异步，默认为异步
			data : {"user" : newname}, //参数值 
			type : "post", //请求方式 				 
			success : function(data) {
				console.log(data);
				if (data == 'yes') {
				 	isRegisted = true;
				 	$("#tishi").html("<span style='color:#32CD32'>该用户可用</span>"); 
				
				} else {
					isRegisted = false;
					 $("#tishi").html("<span style='color:red'>该用户已被使用</span>");
						
				}
			}
		})
		 return isRegisted;	
 }
 
 
 
 
 function checkpwds(){
	 if(document.form1.newpwd.value == "")
	 {
		 document.getElementById("tishi1").innerHTML="<span style='color:red;'>请输入密码</span>";
			return false; 
	 }
	var pwd=document.form1.newpwd.value;
	var reg=/^[a-zA-Z0-9]\w{5,11}$/;
	if (reg.test(pwd)==false){
	document.getElementById("tishi1").innerHTML="<span style='color:red;'>请输入6-12位的密码（仅限英文大小写、数字）</span>";
	return false;	
	 }else{
		 document.getElementById("tishi1").innerHTML="<span style='color:#32CD32;'>密码格式正确</span>";
	 }
	 }
	

 function checkpwds1(){
	 if(document.form1.newpwd1.value == "")
     {document.getElementById("tishi2").innerHTML="<span style='color:red;'>请输入确认密码</sapn>";
		 return false;	
		 }
	 if(document.form1.newpwd.value != document.form1.newpwd1.value)
		 {
		 document.getElementById("tishi2").innerHTML="<span style='color:red;'>两次密码不一致</span>";
		 return false;
		 }else{
			 document.getElementById("tishi2").innerHTML="<span style='color:#32CD32;'>确认密码正确</span>";
		 }
 }
function checkrealName(){
	if(document.form1.newrealname.value == "")
	{
		document.getElementById("tishi3").innerHTML="<span style='color:red;'>请输入真实姓名</span>";
		return false;
		}else{
			document.getElementById("tishi3").innerHTML="<span style='color:#32CD32;'>格式正确</span>";
		}
}
 function checkmobile(){
	 var newphone=document.form1.newphone.value;
		var reg=/^[1]{1}[0-9]{10}$/; 
	 if(document.form1.newphone.value == "")
		 {
		 document.getElementById("tishi4").innerHTML="<span style='color:red;'>请输入手机号</span>";
		 return false;
		 }
	 if(reg.test(newphone)==false){
		 
		 
		 
		 document.getElementById("tishi4").innerHTML="<span style='color:red;'>请输入正确的手机号</span>";
		 return false;
	 }else{
		 document.getElementById("tishi4").innerHTML="<span style='color:#32CD32;'>手机号格式正确</span>";
	 }
 }
 function checkcard(newcard){
	 var reg=/^[0-9]{17}[X|x|0-9]{1}$/;
	 if(newcard == "")
		 {
		 document.getElementById("tishi5").innerHTML="<span style='color:red;'>请输入身份证号</span>";
		 return false;
		 }
	 if(reg.test(newcard)==false){
		 document.getElementById("tishi5").innerHTML="<span style='color:red;'>请输入正确的身份证号</span>";
		 return false;
	 }
 } 
	 
	 
var CardHas=true;	 
function CheckCard(){
	 var newcard=document.form1.newcard.value;
	 if(checkcard(newcard)==false){
		 CardHas=false;
	 }else{
		 if(CheckCardExit(newcard)==false){
			 CardHas=false;
			}else{
				CardHas=true;
			}
	 }
	 
/*	 alert('外层'+CardHas);*/
	 return CardHas;
}
var CardExit=false;
function  CheckCardExit(newcard){
	$.ajax({
		url : "../CheckCard", //请求的url地址 
		dataType : "html", //返回格式为html 
		async : true, //请求是否异步，默认为异步
		data : {"card" : newcard}, //参数值 
		type : "post", //请求方式 				 
		success : function(data) {
			if (data == 'yes') {
				CardExit=true;
			 	$("#tishi5").html("<span style='color:#32CD32'>身份证号可用</span>"); 
			 	
			} else {
				CardExit=false;
				 $("#tishi5").html("<span style='color:red'>该身份证号已被注册</span>") 
			
			}
		return CardExit;}

	})
/*		alert(CardExit);*/
		return CardExit;
	}




function checkAll(){
	if( checkName()==false || checkpwds()==false || checkpwds1()==false ||checkrealName()==false ||checkmobile()==false ||CheckCard()==false){
		return false;
	}
	
}