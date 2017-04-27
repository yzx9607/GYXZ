//验证不能为空格
$.extend($.fn.validatebox.defaults.rules, {
            isNull: {
                validator: function (value, param) { return $.trim(value) != '' },
                message: '不能为空，全空格也不行'
            }
        });
//用来接收条件查找的值
function saerchGroup(){
	$("#dg").datagrid("load",{
		searchGroupName : $("#s_groupName").val(),
	}		
	)
}
//清空输入框
function textreset(){
	$("#s_groupName").val("");
	$("#dg").datagrid("load",{
	})
}
//删除方法的id传参
function groupDelete(){
	var selectedRows=$("#dg").datagrid("getSelections");
	if(selectedRows.length<1){
		$.messager.alert("删除提示","请至少选择一条数据进行删除")
		return;
	}
	var strId=[];
	for(var i=0;i<selectedRows.length;i++){
		strId.push(selectedRows[i].group_id);//往数组里面添加数据//
	}
	var ids=strId.join(",");
	$.messager.confirm("删除提示","您确定要删除吗?",function(yes){
		if(yes){
	$.post("GroupDelete",{delIds:ids},function(result){
		if(result.success){
			$.messager.alert("删除成功提示","已删除"+result.delNums+"条系部信息")
			$("#dg").datagrid("reload");
		}else{
			$.messager.alert("删除失败提示",result.errorMsg);
		}		
	},"json")
		}
	})
	}
//添加方法的传参
		function openAddGroup(){
	//打开窗口录数据
			$("#dlg").dialog("open").dialog("setTitle","添加系部信息");
			reset();
			url="GroupSave";
		}
	//点击取消  关闭窗口
		function closepage(){
			$("#dlg").dialog("close");
		}
		function savaGroup(){
			$("#fm").form("submit",{
				url:url,
				onSubmit:function(){
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
							$("#dg").datagrid("reload");
						}
					}
				
				
			})
		}
		//清空输入框的值
		function reset(){
			$("#group_name").val("");
			$("#group_desc").val("");
			
		}
		
		//打开窗口并将输入框的值传给修改的servlet
		function openModiftyDlg(){
			var selectedRows=$("#dg").datagrid("getSelections");
			if(selectedRows.length!=1){
				$.messager.alert("提示信息","修改只能选择其中一条数据进行修改");
				return;
			}
			var editRow=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","修改系部信息");
			$("#fm").form("load",editRow);
			url="GroupSave?group_id="+editRow.group_id;
			
			
		}