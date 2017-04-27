//修改日期控件为显示月份
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
add("");
function update(){
	var date=$("#s_date").datetimebox('getValue');
	del();
	add(date);
}
function del(){
	$("#salary tr").each(function(){
		if($("#salary tr").size()>2){
			$("#salary tr").last().remove();
		}
	});
}
function add(datetime){
	$.ajax({
		url :"../QianListSalry", //请求的url地址 
		dataType : "json", //返回格式为html 
		async : true, //请求是否异步，默认为异步
		data : {"newdate" : datetime}, //参数值 
		type : "post", //请求方式 				 
		success : function(data) {
			var json=data;
			if(json.date!=null||json.date!=""){
				var date=json.date.split("-");
				$('#timesalary').html(date[0]+"年"+date[1]+"月");
				$('#times').html(date[0]+"年"+date[1]+"月");
			}
			if(json.salary[0]!=null&&json.salary[0]!=""){
				for(var i=0;i<(json.salary.length);i++){
						var salary=json.salary[i];
						var trNode=document.createElement('tr');
						var tdNode1=document.createElement('td')
							tdNode1.innerHTML=salary.id;
						var tdNode2=document.createElement('td')
							tdNode2.innerHTML=salary.s_date;
						var tdNode3=document.createElement('td')
							tdNode3.innerHTML=salary.teacher_id;
						var tdNode4=document.createElement('td')
							tdNode4.innerHTML=salary.teacher_name;
						var tdNode5=document.createElement('td')
							tdNode5.innerHTML=salary.base_pay;
						var tdNode6=document.createElement('td')
							tdNode6.innerHTML=salary.add_pay;
						var tdNode7=document.createElement('td')
							tdNode7.innerHTML=salary.bonus_pay;
						var tdNode8=document.createElement('td')
							tdNode8.innerHTML=salary.tax_pay;
						var tdNode9=document.createElement('td')
							tdNode9.innerHTML=salary.subsidy_pay;
						var tdNode10=document.createElement('td')
							tdNode10.innerHTML=salary.deduction;
						var tdNode11=document.createElement('td')
							tdNode11.innerHTML=salary.other_pay1;
						var tdNode12=document.createElement('td')
							tdNode12.innerHTML=salary.other_pay2;
						var tdNode13=document.createElement('td')
							tdNode13.innerHTML=salary.sum_pay;
						var tdNode14=document.createElement('td')
							tdNode14.innerHTML=salary.pay_status;
							trNode.appendChild(tdNode1);
							trNode.appendChild(tdNode2);
							trNode.appendChild(tdNode3);
							trNode.appendChild(tdNode4);
							trNode.appendChild(tdNode5);
							trNode.appendChild(tdNode6);
							trNode.appendChild(tdNode7);
							trNode.appendChild(tdNode8);
							trNode.appendChild(tdNode9);
							trNode.appendChild(tdNode10);
							trNode.appendChild(tdNode11);
							trNode.appendChild(tdNode12);
							trNode.appendChild(tdNode13);
							trNode.appendChild(tdNode14);
							trNode.style.height="40px";
						$('#salary').append(trNode);
				}
			}else{
					var trNode=document.createElement('tr');
					var tdNode1=document.createElement('td')
					tdNode1.innerHTML="暂无数据";
					var $tdNode1=$(tdNode1);
					$tdNode1.attr("colspan","14");
					$tdNode1.attr("height","100");
					console.log(tdNode1.colspan);
					trNode.appendChild(tdNode1);
					$('#salary').append(trNode);
			}
		}
	})
}
	function time(){
		var date=new Date();
		var year=date.getFullYear();
		var month=date.getMonth()+1;
		var day=date.getDate();
		var hour=date.getHours();
		hour=format(hour);
		var min=date.getMinutes();
		min=format(min);
		var second=date.getSeconds();
		second=format(second);
		var times=year+"年"+month+"月"+day+"日"+hour+"时"+min+"分"+second+"秒";
		var time=document.getElementById('time');
		time.innerHTML=times;
		
	}
	time();
	setInterval(time,1000);
	function format(num){
		if(num<10){
			num="0"+num;
		}else{
			num=num;
		}
		return num;
	}
	
