jQuery(document).ready(function(){
			
	$('#btnContactSubmit').click(function(){
				
		$('#txtContactEmail').attr('style', '');
		$('#txtContactName').attr('style', '');
		$('#txtContactComment').attr('style', '');
					
		if($('#txtContactEmail').val() == undefined || $('#contactEmail').val() == "")
		{					
			$("#txtContactEmail").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtContactName').val() == undefined || $('#contactName').val() == "")
		{					
			$("#txtContactName").css({"border":"1px solid #FF0000"});
		}
		
		if($('#txtContactComment').val() == undefined || $('#contactComment').val() == "")
		{					
			$("#txtContactComment").css({"border":"1px solid #FF0000"});
		}
		
		/*alert("bang");
		$.post('url',{param : "val"}, function(){
		
		
		});*/
		
		return false;
	});
});
		