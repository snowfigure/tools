/**
 * Created with IntelliJ IDEA.
 * User: snowfigure
 * Date: 15-4-3
 * Time: 下午9:39
 * To change this template use File | Settings | File Templates.
 */


$(function(){

    $('#nav_dg').datagrid({
        url:'/cms/nav/list',
        columns:[[
            {field:'id',			title:'id',				width:$(this).width() * 0.1,hidden:true},
            {field:'pid',			title:'pid',				width:$(this).width() * 0.1,hidden:true},
            {field:'name',			title:'名称',				width:$(this).width() * 0.15},
            {field:'pname',			title:'分类',		   width:$(this).width() * 0.15},
            {field:'url',		    title:'URL地址',		     width:$(this).width() * 0.20},
            {field:'info',	        title:'网站介绍',       width:$(this).width() * 0.30},
            {field:'iscomm',		title:'常用链接',		width:$(this).width() * 0.15}
        ]],
        singleSelect:true,//是否单选
        rownumbers:true,//行号
        onDblClickRow:function(index,row){
            detail("/cms/nav/edit/","编辑",row);
        },
        toolbar: [{
            /** 点击新增按钮 */
            text:"新增",
            iconCls: 'icon-add',
            handler: function(){
                detail("/cms/nav/add/","新增");
            }

        },{
            text:"删除",
            iconCls: 'icon-remove',
            handler: function(){
                var row = $('#nav_dg').datagrid("getSelected");
                if(row==null){
                    $.messager.alert('错误','请选择一条记录！','info');
                    return;
                }
                $.messager.confirm('确认删除', '确认删除名称为[' + row.name + "]的记录", function(r){
                    if (r){
                        $.ajax({
                            type:'POST',
                            url:'/cms/nav/del/' + row.id,
                            success:function(data){
                                if(data=="true"){
                                    $.messager.alert('成功','删除成功！','info');
                                    $("#nav_dg").datagrid("reload");
                                }else{
                                    $.messager.alert('错误','出现未知的错误<br/>操作失败！','info');
                                }
                            }
                        });//$.ajax
                    }
                });//$.messager.confirm

            }//handler: function()
        },"-",{
            text:"重新生成静态页面",
            iconCls: 'icon-sum',
            handler: function(){
                $.messager.confirm('重新生成静态页面', "确认重新生成静态页面？", function(r){
                    if(!r){//给成r
                        $.messager.alert('提示',"正在生成中，请稍候",'info');
                        $.ajax({
                            type:'post',
                            url:'/cms/nav/initStaticPage',
                            dataType:'json',
                            success:function(data){
                                $.messager.alert('提示',data,'info');
                            }
                        });
                    }
                });
            }
        }],
        pagination:true //分页控件
    });
//设置分页控件
    var pt = $('#nav_dg').datagrid('getPager');
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
                    $("#nav_dg").datagrid("reload");
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
                'nav.id':row.id,
                'nav.pid':row.pid,
                'nav.name':row.name,
                'nav.url':row.url,
                'nav.info':row.info,
                'nav.iscomm':row.iscomm,
            });
        }
        $("#detail").dialog({
            title:title,
            width:300,
            height:280,
            top:'150px',
            closed:false,
            cache:false,
            buttons:[{
                text:'保存',
                handler:function(){ /** 新增对话框中的保存按钮 */
                    if($('#nav.name').val()=="undefined"){
                        $.messager.alert('错误','请输入名称','info');
                    }else{
                        $("form").submit();
                    }
                }
            },
            {
                text:'抽取title',
                handler:function(){ /** 新增对话框中的关闭按钮 */
                    $.ajax({
                        type:'post',
                        url:'/cms/nav/getTitle?url=' + $('#_url').val()  ,
                        success:function(data){
                            $("#_info").val(data);
                        }
                    });
                }
            },
            {
                text:'关闭',
                handler:function(){ /** 新增对话框中的关闭按钮 */
                $("#detail").dialog("close");
                    $('#form').form("clear");
                }
            }]
        });
    }

});
