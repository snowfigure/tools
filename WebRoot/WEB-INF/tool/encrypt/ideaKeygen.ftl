<#include "/WEB-INF/comm/_layout.ftl"/>
<@layout info>

<div class="panel panel-default">
	<div class="panel-heading">
		IntelliJ IDEA注册码生成器
	</div>
	<div class="panel-body">
		<div class="col-xs-12 col-sm-6 col-md-9">
			<input type="text" class="form-control" id="code" placeholder="请输入Company/用户名/注册名" value="791211.com">
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3">
			<button type="button" class="btn btn-info btn-block" id='search'>IDEA注册码生成</button>
		</div>
	</div>
	<div class="panel-body">
		<div class="alert alert-success" id="result">注册码结果</div>


<table class="table table-hover table-bordered">
	<tr>
		<th class="keyRow" style="width: 20%">IDEA版本</th>
		<th style="width: 80%">IDEA注册码</th>
	</tr>
	<tr>
		<td class="keyRow">IDEA 14.x.x版本</td>
		<td>
			<textarea class="form-control" rows="2" id='idea14'></textarea>
		</td>
	</tr>
	<tr>
		<td class="keyRow">IDEA 13.x.x版本</td>
		<td>
			<textarea class="form-control" rows="2" id='idea13'></textarea>
		</td>
	</tr>
	<tr>
		<td class="keyRow">IDEA 12.x.x版本</td>
		<td>
			<textarea class="form-control" rows="2" id='idea12'></textarea>
		</td>
	</tr>
</table>

		
	</div>
</div>

</@layout>

<script type="text/javascript">
	$(function(){
		$('#search').bind("click",function(){
			var company = $("#code").val();
			if(company==null || company=="" || company=="undefined"){
				$("#code").focus();
				$("#idea14").val('');
				$("#idea13").val('');
				$("#idea12").val('');
				return;
			}
			$.ajax({

                type:'POST',
                url:'/encrypt/getIdeaKeygen/',
                dataType:'json',
                data:{
                    company:company,
                },
                success:function(data){
                    $("#idea14").val(data.idea14);
                    $("#idea13").val(data.idea13);
                    $("#idea12").val(data.idea12);
                }

			});
		});
		$('#search').click();
	});

</script>

