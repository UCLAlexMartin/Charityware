<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- <%@ page import="systemDBHibernateEntities.Charity" %>  
<%@ page import="RESTClient.CharityClient" %>
<%@ page import="systemDBHibernateEntities.User" %>  
<%@ page import="RESTClient.SystemUserClient" %>   --%>       

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
		<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico">
		<link rel="stylesheet" href="css/register.css" type="text/css" media="all">
		<title>CharityWare - Register your Charity</title>		
	</head>
	
	<body>
		<div class="body">
			<div class="main">
			
			<jsp:include page="header.jsp"></jsp:include>  
			
				<!-- Main Content -->
				<article id="content">
				<div class="wrapper">
				<div class="contentBox">
				
					<form name="frmRegister" method="post" action="">
					
					<table style="border-spacing:5px;border-collapse: inherit;">
						<tr>
							<td> 
								<p> 
									<h2> Register </h2> 
								</p>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Name
							</td>
							<td>
								<input type="text" class="registerTextbox" name="txtCharity" id="txtCharity" maxlength="100" pattern="[a-zA-Z0-9]+" placeholder="Charity Name" tabindex="1" required>
							</td>
						</tr>
						
						<tr>
							<td>
								Registration Number
							</td>
							<td>
								<input type="text" class="registerTextbox" name="txtReg" id="txtReg" maxlength="100" tabindex="2" pattern="[0-9]{5,7}" placeholder="Charity Registration No" required>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Email
							</td>
							<td>
								<input type="email" class="registerTextbox" name="txtChEmail" id="txtChEmail" maxlength="250" tabindex="3" placeholder="Charity Email Address" required>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Description
							</td>
							<td>
								<textarea cols=65 rows=4 id="txtChDescription" name="txtChDescription" class="registerTextbox" maxlength="300" tabindex="4" pattern="[a-zA-Z0-9]+" placeholer="Charity Description" required></textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Administrator Username
							</td>
							<td>
								<input type="text" class="registerTextbox" name="txtAdminUsername" id="txtAdminUsername" maxlength="20" tabindex="5" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]$" placeholder="Username must contain a minimum of 2 characters and maximum of 20." required>
							</td>
						</tr>
						
						<tr>
							<td>
								Charity Administrator Password
							</td>
							<td>
								<input type="password"  class="registerTextbox" name="txtAdminPassword" id="txtAdminPassword" maxlength="25" tabindex="6" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" placeholder="Password should contain a minimum of one Uppercase, Lower and Number." required>
							</td>
						</tr>	
						
						<tr>
							<td>
								<input class="registerSubmit" name="btnRegister" type="submit" id="btnRegister" value="REGISTER">
							</td>
						</tr>
						
						<tr>
							<td>
								<br/>
							</td>
						</tr>	
						
						<tr>
							<td>
								<p> <a href="registerHelp.jsp"> Require Assistance ?</a></p>
							</td>
						</tr>
					</table>
					
					</form>
				
				<%-- <%
				if( (request.getParameter("txtCharity") != null) & (request.getParameter("txtReg") != null) & (request.getParameter("txtChEmail") != null) 
				& (request.getParameter("txtChDetails") != null) & (request.getParameter("txtAdminUsername") != null) & (request.getParameter("txtAdminPassword") != null)) { 
				
				User u = new User();
				
				u.setUserName((request.getParameter("txtAdminUsername")).toString());
				u.setUserPassword((request.getParameter("txtAdminPassword")).toString());
				SystemUserClient.addUser(u);
				
				
				//SystemUserClient.addUser((request.getParameter("txtAdminUsername")).toString(), (request.getParameter("txtAdminPassword")).toString());
				//u = SystemUserClient.get((request.getParameter("txtAdminUsername")).toString());
				
				/* Charity ch = new Charity();
				
				ch.setCharityName(request.getParameter("txtCharity"));
				ch.setCharityDescription(request.getParameter("txtChDetails"));
				ch.setEmail(request.getParameter("txtChEmail"));
				ch.setRegistrationNo(request.getParameter("txtReg"));
				ch.setUser(u);
							
				CharityClient.addCharity(ch); */
				
				}
				%>      			 --%>
				
				</div>
				</div>
				</article>
				<!-- Main content -->
			
			<jsp:include page="footer.jsp"></jsp:include> 
			</div>
		</div>
	</body>
</html>
