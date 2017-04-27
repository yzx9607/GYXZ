$.extend($.fn.validatebox.defaults.rules, {    
   month:{
	   validator:function(value){
		   var time=value;
		   var s_time=new Date();
		   var s_year=s_time.getFullYear();
		   var s_month=s_time.getMonth()+1;
		   if(s_month<10){
			   s_month="0"+s_month;
		   }
		   var s_date=s_year+"-"+s_month;
		   var t=parseInt(time.replace(/\-/g,""));
		   var s_check=parseInt(s_date.replace(/\-/g,""));
		   return(t<=s_check);
	   },
	   message: '薪资月份不能早于当前月份'
   },
     
      
});

$(function () {  
    $('#db').datebox({  
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层  
            span.trigger('click'); //触发click事件弹出月份层  
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔  
                tds = p.find('div.calendar-menu-month-inner td');  
                tds.click(function (e) {  
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件  
                    var year = /\d{4}/.exec(span.html())[0]//得到年份  
                            , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1  
                    $('#db').datebox('hidePanel')//隐藏日期对象  
                            .datebox('setValue', year + '-' + month); //设置日期的值  
                });  
            }, 0)  
        },  
        parser: function (s) {  
            if (!s) return new Date();  
            var arr = s.split('-');  
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);  
        },  
        formatter: function (d) { 
        	var monthnew=d.getMonth()+1;
        	if((d.getMonth()+1)<10){
        		monthnew="0"+monthnew;
        	}      	
        	return d.getFullYear() + '-' + monthnew;/*getMonth返回的是0开始的，忘记了。。已修正*/ }  
    });  
    var p = $('#db').datebox('panel'), //日期选择对象  
            tds = false, //日期选择对象中月份  
            span = p.find('span.calendar-text'); //显示月份层的触发控件  
});  

$(function () {  
    $('#s_date').datebox({  
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层  
            span.trigger('click'); //触发click事件弹出月份层  
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔  
                tds = p.find('div.calendar-menu-month-inner td');  
                tds.click(function (e) {  
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件  
                    var year = /\d{4}/.exec(span.html())[0]//得到年份  
                            , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1  
                    $('#s_date').datebox('hidePanel')//隐藏日期对象  
                            .datebox('setValue', year + '-' + month); //设置日期的值  
                });  
            }, 0)  
        },  
        parser: function (s) {  
            if (!s) return new Date();  
            var arr = s.split('-');  
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);  
        },  
        formatter: function (d) { 
        	var monthnew=d.getMonth()+1;
        	if((d.getMonth()+1)<10){
        		monthnew="0"+monthnew;
        	}
        	
        	return d.getFullYear() + '-' + monthnew;}  
    });  
    var p = $('#s_date').datebox('panel'), //日期选择对象  
            tds = false, //日期选择对象中月份  
            span = p.find('span.calendar-text'); //显示月份层的触发控件  
});  



//条件查找的参数传递
function saerchsalary(){
	$("#sg").datagrid("load",{
		teacher_name: $("#S_Name").val(),
		pay_status:$("#pay_status").combobox('getValue'),
		s_date : $("#db").datetimebox('getValue')
	}		
	)
}
//清空输入框
function textReset(){
	$('#db').datebox('setValue', '');
	$("#S_Name").val("");
	$("#pay_status").combobox('setValue','');
	$("#sg").datagrid("load",{});
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
//添加工资的servlet指向以及窗口的打开
function openAddsalary(){
	$("#nlg").dialog("close");
	$("#slg").dialog("open").dialog("setTitle","添加工资");
	$("#teacher_id").numberbox("enable",true);
	reset();
	//清空添加框
	url="SalarySave";
}
function close(){
	$("#slg").dialog("close");
}
//保存数据，并返回结果
function savasalary(){
	$("#sfk").form("submit",{
		url:url,
		onSubmit:function(){
			var check= $(this).form("validate");
			if(check!=false){
				$("#dlg").dialog("close");
			}
			return check;
			},
			success:function(result){
				var result=$.parseJSON(result);
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示",result.success);
					$("#slg").dialog("close");
					$("#sg").datagrid("reload");
				}
			}
	})
}

//修改数据参数（id）的传递
function opensalary(){
	$("#nlg").dialog("close");
	var selectedRows=$("#sg").datagrid("getSelections");
	if(selectedRows.length!=1){
		$.messager.alert("提示信息","修改只能选择其中一条数据进行修改");
		return;
	} 

	var editRow=selectedRows[0];
	$("#slg").dialog("open").dialog("setTitle","修改工资信息");
	$("#sfk").form("load",editRow);
	$("#teacher_id").numberbox("disable",true);
	$("#sum_pay").val("更新后自动计算");
	url="SalarySave?id="+editRow.id;
	
	
}
//工资信息删除的参数（id）传递
function salaryDelete(){
	var selectedRows=$("#sg").datagrid("getSelections");
	if(selectedRows.length==0){
		$.messager.alert("删除提示","请至少选择一个薪资单进行删除")
		return;
	}
	var strId=[];
	for(var i=0;i<selectedRows.length;i++){
		strId.push(selectedRows[i].id);//往数组里面添加数据//
	}
	var ids=strId.join(",");
	$.messager.confirm("删除提示","您确定要删除"+strId.length+"条薪资单吗?",function(yes){
		
		if(yes){
			$.post("SalaryDelete",{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("删除成功提示","成功删除了"+result.delNums+"条薪资单")
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
	$('#base_pay').numberbox('setValue', 0);
	$('#add_pay').numberbox('setValue', 0);
	$('#bonus_pay').numberbox('setValue', 0);
	$('#tax_pay').numberbox('setValue', 0);
	$('#subsidy_pay').numberbox('setValue', 0);
	$('#subsidy_pay').numberbox('setValue', 0);
	$('#deduction').numberbox('setValue', 0);
	$("#deduction").val("更新后自动获取");
	$('#other_pay1').numberbox('setValue', 0);
	$('#other_pay2').numberbox('setValue', 0);
	$("#pay_status").val("未发放");
	$("#staff_card").val("");
	$("#sum_pay").val("更新后自动计算");
	$('#s_date').datebox('setValue', '')

	


}

function salaryPay(a){
	var selectedRows=$("#sg").datagrid("getSelections");
	if(selectedRows.length==0){
		$.messager.alert("发放提示","请至少选择一个薪资单进行发放")
		return;
	}
	var strId=[];
	for(var i=0;i<selectedRows.length;i++){
		strId.push(selectedRows[i].id);//往数组里面添加数据//
		if(selectedRows[i].pay_status=='已发放'){
			$.messager.alert("发放提示","包含发放过的薪资单")
			return;
		}
	}
	var ids=strId.join(",");
	$.messager.confirm("发放提示","您确定要发放"+strId.length+"单薪资吗?",function(yes){
		
		if(yes){
			$.post("SalaryPay?a=0",{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("发放成功提示","成功发放了"+result.delNums+"单工资")
					$("#sg").datagrid("reload");
				}else{
					$.messager.alert("发放失败提示",result.errorMsg);
				}		
			},"json")
			//用JSON的方式接收返回的处理结果
		}
		})
}
//撤销薪资
function salaryPay1(){

	
	
	var selectedRows=$("#sg").datagrid("getSelections");
	if(selectedRows.length==0){
		$.messager.alert("撤销提示","请至少选择一个薪资单进行撤销")
		return;
	}
	var strId=[];
	for(var i=0;i<selectedRows.length;i++){
		strId.push(selectedRows[i].id);//往数组里面添加数据//
		if(selectedRows[i].pay_status=='未发放'){
			$.messager.alert("撤销提示","包含未发放的薪资单")
			return;
		}
	}
	var ids=strId.join(",");
	$.messager.confirm("撤销提示","您确定要撤销"+strId.length+"单薪资吗?",function(yes){
		
		if(yes){
			$.post("SalaryPay?a=1",{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("撤销成功提示","成功撤销了"+result.delNums+"单工资")
					$("#sg").datagrid("reload");
				}else{
					$.messager.alert("撤销失败提示",result.errorMsg);
				}		
			},"json")
			//用JSON的方式接收返回的处理结果
		}
		})

}