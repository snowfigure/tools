$(function(){
	/*大陆省份证信息校验*/
	$('#mlcardsearch').bind("click",function(){
		var no = $("#mlcardno").val();
		if(no==null || no=="" || no=="undefined"){
			$("#mlcardno").focus();
			return;
		}else{
			$.ajax({
				type:'POST',
				url:'/life/idcard/1-'+no,
				dataType:'json',
				success:function(data){
					if(data.validate==true){
						$('#mlcardresult').html(
								"编号：" + data.idcardno + "<br/>" +
								"归属：" + data.address + "<br/>" +
								"生日：" + data.birthdate + "<br/>" +
								"性别：" + data.gender
						);
					}else{
						$('#mlcardresult').html("不合法的身份证编号");
					}
				}
			});
		}
	});
	/*	15位升级18位	*/
	$('#mlcardno_15_search').bind("click",function(){
		var no = $("#mlcardno_15").val();
		if(no==null || no=="" || no=="undefined"){
			$("#mlcardno_15").focus();
			return;
		}else{
			$.ajax({
				type:'POST',
				url:'/life/idcard/3-'+no,
				dataType:'json',
				success:function(data){
					if(data.validate==true){
						$('#mlcardno_15_result').html(
								//"编号：" + data.idcardno_15 + "<br/>" +
								"升级：" + data.idcardno + "<br/>" +
								"归属：" + data.address + "<br/>" +
								"生日：" + data.birthdate + "<br/>" +
								"性别：" + data.gender
						);
					}else{
						$('#mlcardno_15_result').html("不合法的身份证编号");
					}
				}
			});
		}
	});
	
	
	/*随机生成身份证号码*/
	$('#mlcardno_generate_search').bind("click",function(){

		$.ajax({
			type:'POST',
			url:'/life/idcard/4-0',
			dataType:'json',
			success:function(data){
				if(data.validate==true){
					$('#mlcardno_generate_result').html(
							//"编号：" + data.idcardno_15 + "<br/>" +
							"编号：" + data.idcardno + "<br/>" +
							"归属：" + data.address + "<br/>" +
							"生日：" + data.birthdate + "<br/>" +
							"性别：" + data.gender
					);
					$('#mlcardno_generate').val(data.idcardno);
				}else{
					$('#mlcardno_generate_result').html("出现未知错误");
				}
			}
		});
		
	});
	
	
	
});