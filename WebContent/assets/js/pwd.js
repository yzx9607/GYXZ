
//window
var isRegisted=true;     
function CheckTeacher(){
	var newname=document.form1.newname.value;
	 if( checknewname(newname)==false){
		 isRegisted=false;
		}
	 else{
		 isRegisted=true; 
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
	else{
		document.getElementById("tishi").innerHTML="<span style='color:#32CD32;'>用户名格式正确</span>";
		return true;
	}
 }	
 function checkTpwd(){
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
	

 function checkTpwd1(){
	 if(document.form1.newpwd1.value == "")
     {document.getElementById("tishi2").innerHTML="<span style='color:red;'>请输入确认密码</sapn>";
		 return false;	
		 }
	 if(document.form1.newpwd.value != document.form1.newpwd1.value)
		 {
		 document.getElementById("tishi2").innerHTML="<span style='color:red;'>两次密码不一致</span>";
		 return false;
		 }else{
			 document.getElementById("tishi2").innerHTML="<span style='color:#32CD32;'>密码输入正确</span>";
		 }
 }
 function checkTphone(){
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
	 }else{
		 document.getElementById("tishi5").innerHTML="<span style='color:#32CD32;'>身份证号格式正确</span>"; 
		 return true;
	 }
 } 
	 
	 
var CardHas=true;	 
function CheckTCardHas(){
	 var newcard=document.form1.newcard.value;
	 if(checkcard(newcard)==false){
		 CardHas=false;
	 }else{
		 CardHas=true;
	 }
	 return CardHas;
}


function checkforms(){
	if( CheckTeacher()==false || checkTpwd()==false || checkTpwd1()==false ||checkTphone()==false ||CheckTCardHas()==false){
		return false;
	}
	
}