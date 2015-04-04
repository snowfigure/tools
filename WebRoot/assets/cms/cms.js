$(function () {
	function reinitIframe(){    
	    var iframe = document.getElementById("#ajaxPageIframe");    /* 填写要设置的frame的ID */
	    try{    
	        var bHeight = iframe.contentWindow.document.body.scrollHeight;    
	        var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;    
	        var height = Math.max(bHeight, dHeight);    
	        iframe.height =  height;    
	    }catch (ex){}    
	}
	$(window).resize(function() {
	    var height = $(this).height();
	    $("#ajaxPageIframe").attr("height",  height - 188);
	});

	$("#ajaxPageIframe").ready(function(){
		var height = $(window).height();
	    $("#ajaxPageIframe").attr("height",  height - 188);
	});
	
	
	$('#tabs').tabs({
	    border:false
	});
	$('#nav').tree({
	    url: "/cms/menu/index",
	    loadFilter: function(data){
	    	console.log(data);
	    	return data.list;
	    },
	    onClick:function(node){
	    	if(node.url){
	    		if($('#tabs').tabs("exists",node.text)){
	    			$('#tabs').tabs("select",node.text);
	    		}else{
	    			//var content = '<iframe id="ajaxPageIframe"  scrolling="auto" frameborder="0"  src="'+node.url+'" style="width:100%;height:100%;"></iframe>';
                    var content = '<iframe id="ajaxPageIframe" scrolling="auto" frameborder="0"  src="'+node.url+'" style="width:100%;"></iframe>';
	    			$('#tabs').tabs('add',{
	    				title:node.text,
	    				content:content,
	    				closable:true,
	    			});
	    			var height = $(window).height();
	    			//reinitIframe();
	    		    $("#ajaxPageIframe").attr("height",  height - 188);
	    		}
	    	}
	    }
	});
});
