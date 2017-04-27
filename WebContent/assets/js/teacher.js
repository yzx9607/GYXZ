$.extend($.fn.validatebox.defaults.rules, {    
	phone: {// 验证手机号码
          validator: function (value) {
              return /^(1)\d{10}$/i.test(value);
          },
          message: '手机号码格式不正确'
      },
     card:{
  	   validator: function (value) {
             return /^[0-9]{17}[X|x|0-9]{1}$/i.test(value);
         },
         message: '身份证格式不正确'
     
   },
   date:{
	   validator:function(value){
		   var time=value.replace(/-/g,"/");
		   var t=Date.parse(time);
		   var s_time=new Date();
		   var s_year=s_time.getFullYear();
		   var s_month=s_time.getMonth()+1;
		   var s_day=s_time.getDate();
		   var s_date=s_year+"/"+s_month+"/"+s_day;
		   var s_check=Date.parse(s_date);
		   return(t<=s_check);
	   },
	   message: '考勤日期不能早于当前日期'
   },
 //验证不能为空格
   isNull: {
       validator: function (value, param) { return $.trim(value) != '' },
       message: '不能为空，全空格也不行'
   }
      
});


//条件查找的参数传递
function searchTeacher(){
	$("#sg").datagrid("load",{
		stuName: $("#S_Name").val(),
		//名字以及所属系部和性别
		groupId:$("#s_groupId").combobox("getValue"),
		StuSex:$("#cc").combobox("getValue"),
	}		
	)
}
//下拉框属性
$('#cc').combobox({    
    url:'combobox_data.json',    
    valueField:'id',    
    textField:'text',
    panelHeight:'70px'
});
$('#sex').combobox({    
    url:'combobox_data.json',    
    valueField:'id',    
    textField:'text',
    panelHeight:'50px'
});
//添加教师的servlet指向以及窗口的打开
function openAddTeacher(){
	$("#nlg").dialog("close");
	$("#slg").dialog("open").dialog("setTitle","添加教师");
	reset();
	//清空添加框
	url="TeacherSave";
}
function close(){
	$("#slg").dialog("close");
}
//保存数据，并返回结果
function saveTeacher(){
	$("#sfk").form("submit",{
		url:url,
		onSubmit:function(){
			if($("#teacher_group").combobox("getValue")==""){
				$.messager.alert("系统提示","请选择所属系部");
				return false;
			}
			var check= $(this).form("validate");
			if(check!=false){
				$("#dlg").dialog("close");
			}
			return check;
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					console.log(result.success);
					$("#slg").dialog("close");
					$("#sg").datagrid("reload");
				}
			}
		
		
	})
}

//修改数据参数（id）的传递
function openTeacher(){
	$("#nlg").dialog("close");
	var selectedRows=$("#sg").datagrid("getSelections");
	if(selectedRows.length!=1){
		$.messager.alert("提示信息","修改只能选择其中一条数据进行修改");
		return;
	} 
	
	
	var editRow=selectedRows[0];
	$("#slg").dialog("open").dialog("setTitle","修改教师信息");
	$("#sfk").form("load",editRow);
	$("#teacher_cards").val(editRow.teacher_card);
	console.log(editRow.teacher_card);
	url="TeacherSave?id="+editRow.teacher_id;
}
//教师信息删除的参数（id）传递
function TeacherDelete(){
	var selectedRows=$("#sg").datagrid("getSelections");
	if(selectedRows.length==0){
		$.messager.alert("删除提示","请至少选择一条信息进行删除")
		return;
	}
	var strId=[];
	for(var i=0;i<selectedRows.length;i++){
		strId.push(selectedRows[i].teacher_id);//往数组里面添加数据//
	}
	var ids=strId.join(",");
	$.messager.confirm("删除提示","您确定要删除"+strId.length+"条信息吗?",function(yes){
		
		if(yes){
			$.post("TeacherDelete",{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("删除成功提示","成功删除了"+result.delNums+"条记录")
					$("#sg").datagrid("reload");
				}else{
					$.messager.alert("删除失败提示",result.errorMsg);
				}		
			},"json")
			//用JSON的方式接收返回的处理结果
		}
		})
		}
//清空输入框
function reset(){
	$('#teacher_id').numberbox('setValue', '');
	$("#teacher_name").val("");
	$("#teacher_email").val("");
	$("#teacher_tel").val("");
	$("#teacher_desc").val("");
	$("#teacher_card").val("");
	$("#teacher_cards").val("");
	$("#teacher_group").combobox('setValue', '');
	$("#teacher_sex").combobox('setValue', '男');
}
//清空搜索输入框
function textReset(){
	$('#birthday').datebox('setValue', '');
	$('#birthday1').datebox('setValue', '');
	$("#S_Name").val("");
	$("#sg").datagrid("load",{});
	$("#s_groupId").combobox('setValue', '');
	$("#cc").combobox('setValue', '');
}