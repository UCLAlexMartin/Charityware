<%@ page import="staticResources.websiteLogin"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/main.css" type="text/css" media="all">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/tabsScript.js"></script>
<div class="body">
	<div class="main">
	
		<!-- header -->
		<header>    
		<div class="wrapper">
			<h1 style="padding: 20px 0 0 20px;"> <a href="default.jsp" id="footer_logo"><span>&nbsp;Charity</span>Ware</a></h1>
			<ul id="menubar">
				<li><a href="<%= websiteLogin.getHomePage(session.getAttribute("userTypeId").toString()) %>">Home</a></li> 
				<li><a href="default.jsp">Log Out</a></li> 
				<li><a href="info.jsp">About Us</a></li>
			</ul> 		
		</div>
		</header>
		<!-- header -->
		
		<div class="wrapper" style="height:220px;">
		</div>
	</div>
</div>
