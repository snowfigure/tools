var pageSize = 10;

//pageNumber, int pageSize
function getArticles(pageNumber){
	$.ajax({
		type:"GET",
		url:"/article/getList/" + pageNumber,
		dataType: "json",
		success:function(data){
			var html = "";
			console.log(data.list.length);
			for(var i=0;i<data.list.length;i++){
				html +=
					"<!-- 一个博客信息摘要 开始 -->"+
					"<div class='panel panel-default'>"+
					   "<div class='panel-heading'>" +
					   		"<span  id='title'><a href='/article/" + data.list[i].id +"'>" + data.list[i].title + "</a></span>" +
					   		"<span class='label' style='float: right;color:#56B758'>微信开发分类</span>"+
					   		"<span style='clear: right;'></span>" +
					   	"</div>"+
					   "<div class='panel-body' id='panel-abstract'>"+
					   		"<p>"+ data.list[i].abstract+ "</p>"+
					   		"<div class='col-xs-6 col-sm-3  col-md-5' id='tags'>"+
								"<span class='label label-primary'>主要标签</span>"+
								"<span class='label label-success'>成功标签</span>"+
								"<span class='label label-info'>信息标签</span>"+
								"<span class='label label-warning'>警告标签</span>"+
					   		"</div>"+
					   		"<div class='col-xs-6 col-sm-9  col-md-7' id='posts'>posted @ " +
					   			data.list[i].ctime  +
					   		"</div>"+
					   "</div>"+
					"</div>"+
					"<!-- 一个博客信息摘要结束 -->";
			}
			var pageSize = data.pageSize;
			var pageNumber = data.pageNumber;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			var start = (pageNumber-2<1)?(1):(pageNumber-2);
			var end =  (pageNumber+2>totalPage)?(1):(pageNumber+2) + ((pageNumber-2<1)?(2):(0));
			
			
			
			var paper = "<ul class='pagination pagination-md' id='pager'>";
			if(pageNumber==1) {
				paper+="<li class='disabled'><a>&laquo;</a></li>" ;
			}else{
				paper+="<li><a href='article/list/" + start +"'>&laquo;</a></li>" ;
			}
			
			
			for(var i=start;i<=end;i++){
				if(i==pageNumber) {
					paper+="<li class='active'><a>" + i +"</a></li>" ;
				}else{
					paper+="<li><a href='article/list/" + i +"'>" + i +"</a></li>" ;
				}
			}
			if(pageNumber==totalPage) {
				paper+="<li class='disabled'><a>&raquo;</a></li>";
			}else{
				paper+="<li><a href='article/list/" + end +"'>&raquo;</a></li>";
			}
			
			paper+="</ul>";
			html+=paper;
			
			$("#articleList").html(html);
		}
		
	});
}

