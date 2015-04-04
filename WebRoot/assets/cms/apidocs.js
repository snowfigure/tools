$(function(){
	$('.apidocs').textbox({
		width:'260px',
		
	});
	/**
	 * 百度短链
	 */
	function shorten(id){
		var url = $(id).textbox('getValue');
		if(url!=null && url!="#" && url!="undefined" && url.substring(0,14)!="http://dwz.cn/"){
			$.ajax({
				type:'post',
				url:'/url/shorten/',
				dataType:'json',
				data:{
					longurl:url
				},
				success:function(data){
					if(data.status==0){
						$(id).textbox('setValue',data.tinyurl);
					}
				}
			});
		}
	}
	/**
	 * adfly短链
	 */
	function adfly(id){
		var url = $(id).textbox('getValue');
		if(url=="undefined" || url==null || url=="" || url=="#") return;
		$.ajax({
			type:'post',
			url:'/url/adfly/',
			dataType:'json',
			data:{
				longurl:url
			},
			success:function(data){
				if(data.data){
					$(id+"_adfly").textbox('setValue',data.data[0].short_url);
				}
			}
		});
	}
	$('#btn_shorten').click(function(){
		shorten("#_online");
		shorten("#_offline");
	});
	$('#btn_adfly').click(function(){
		adfly("#_online");
		adfly("#_offline");
	});
	$('#apidoc_dg').datagrid({
		url:'/cms/apidocs/list',
		columns:[[
		    {field:'id',			title:'id',				width:$(this).width() * 0.1,hidden:true},
		    {field:'name',			title:'名称',				width:$(this).width() * 0.1},
		    {field:'icon',			title:'图标',				width:$(this).width() * 0.15},
		    {field:'online',		title:'在线地址',			width:$(this).width() * 0.18},
		    {field:'online_adfly',	title:'在线地址_adfly',	width:$(this).width() * 0.18},
		    {field:'offline',		title:'离线下载地址',		width:$(this).width() * 0.18},
		    {field:'offline_adfly',	title:'离线下载地址_adfly',	width:$(this).width() * 0.16},
		]],
		singleSelect:true,//是否单选
		rownumbers:true,//行号
		onDblClickRow:function(index,row){
			detail("/cms/apidocs/edit/","编辑",row);
		},
		toolbar: [{
			/** 点击新增按钮 */
			text:"新增",
			iconCls: 'icon-add',
			handler: function(){
				detail("/cms/apidocs/add/","新增");
			}
				
		},{
			text:"修改",
			iconCls: 'icon-edit',
			handler: function(){
				var row = $('#apidoc_dg').datagrid("getSelected");
				if(row==null){
					$.messager.alert('错误','请选择一条记录！','info');
					return;
				}
				
				detail("/cms/apidocs/edit/","编辑",row);
			}
		},{
			text:"删除",
			iconCls: 'icon-remove',
			handler: function(){
				var row = $('#apidoc_dg').datagrid("getSelected");
				if(row==null){
					$.messager.alert('错误','请选择一条记录！','info');
					return;
				}
				$.messager.confirm('确认删除', '确认删除名称为[' + row.name + "]的记录", function(r){
					if (r){
						$.ajax({
							type:'POST',
							url:'/cms/apidocs/del/' + row.id,
							success:function(data){
								if(data=="true"){
									$.messager.alert('成功','删除成功！','info');
									$("#apidoc_dg").datagrid("reload");
								}else{
									$.messager.alert('错误','出现未知的错误<br/>操作失败！','info');
								}
							}
						});//$.ajax
					}
				});//$.messager.confirm
				
			}//handler: function()
		},"-",{
			text:"同步w3c",
			iconCls: 'icon-sum',
			handler: function(){
				$.messager.confirm('重新抽取w3cschool教程', "确认同步w3cschool教程的记录？", function(r){
					if(!r){//给成r
						$.messager.alert('提示',"正在抽取中，请稍候",'info');
						$.ajax({
							type:'post',
							url:'/cms/url/w3cFilter',
							dataType:'json',
							success:function(data){
								$.messager.alert('提示',data,'info');
							}
						});
					}
				});
			}
		}],
        pagination:true,//分页控件
	});
	//设置分页控件
    var pt = $('#apidoc_dg').datagrid('getPager');
    $(pt).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [10,20,50,100],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    $("#detail").dialog({}).dialog("close");
    function detail(submiturl,title,row){
    	$('#form').form({
			url:submiturl,
			success:function(data){
				if(data=="true"){
					$("#apidoc_dg").datagrid("reload");
					$('#form').form("clear");
					if(row){
						$("#detail").dialog("close");
					}
				}else{
					$.messager.alert('错误','出现未知的错误<br/>操作失败！','info');
				}
			}
		});
    	if(row){
    		$('#form').form('load',{
    			'apidocs.id':row.id,
    			'apidocs.name':row.name,
                'apidocs.icon':row.icon,
    			'apidocs.online':row.online,
    			'apidocs.online_adfly':row.online_adfly,
    			'apidocs.offline':row.offline,
    			'apidocs.offline_adfly':row.offline_adfly,
    		});
    	}
		$("#detail").dialog({
			title:title,
			width:390,
			height:260,
			top:'10px',
			closed:false,
			cache:false,
			buttons:[{
				text:'保存',
				handler:function(){ /** 新增对话框中的保存按钮 */
					if($('#apidocs.name').val()=="undefined"){
						$.messager.alert('错误','请输入标题','info');
						return;
					}else{
						$("form").submit();
					}
				}
			},{
				text:'关闭',
				handler:function(){ /** 新增对话框中的关闭按钮 */
					$("#detail").dialog("close");
					$('#form').form("clear");
				}
			}]
		});
    }
    
});