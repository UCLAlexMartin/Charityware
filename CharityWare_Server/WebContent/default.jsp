<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
	<script type="text/javascript">
	jQuery(document).ready(function(){
		$.get('http://localhost:8080/CharityWare_Lite/REST/userService/charityConfig/hibernate.cfg.xml/users', function(data) {
				$.each(data, function(i,d){
					$('#UsrDD').append("<option id='" + d.user_id  + "'>" + d.userName + "</option>");
				});
			});	
	});
	
	</script>
<title>Insert title here</title>
</head>
<body>
Hello World.
<select id="UsrDD">
<option>Please Select...</option>
</select>

</body>
</html>