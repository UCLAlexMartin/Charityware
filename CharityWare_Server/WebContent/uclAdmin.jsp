<%@ page import="staticResources.websiteLogin"%>
<%@ page import="staticResources.Configuration"%>
<%
if(session.getAttribute("userTypeId") == null)
{
	response.sendRedirect("login.jsp");
	//out.println("go away null"+"<br/>");
}else
{
	if(websiteLogin.isAuthenticated(session.getAttribute("userTypeId").toString(), request.getRequestURL().toString()))
	{
		//out.println("you may stay"+"<br/>");
	}
	else
	{
	
		response.sendRedirect("login.jsp");
	}
}
%> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- <%@ page import="ConnectionManager.*" %>   
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %> --%>
<%@ page import="staticResources.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="utf-8">
	<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico">
	<title>CharityWare framework Administration</title>		
			<!--Load the AJAX API-->
	    	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	    	<script type="text/javascript" src="js/tabsScript.js"></script>
	    	<!--TO BE TRANSFERED TO A JS FILE-->
	    	<%--<script type="text/javascript">
	
		       // Load the Visualization API and the piechart package.
		      google.load('visualization', '1.0', {'packages':['corechart']});
	
		      // Set a callback to run when the Google Visualization API is loaded.
		      google.setOnLoadCallback(drawChart);
		      function drawChart(point) {
		    	  var data = new google.visualization.DataTable();
	
		      }
		      // Callback that creates and populates a data table,
		      // instantiates the pie chart, passes in the data and
		      // draws it.
	
	
		      jQuery(document).ready(function($){
			      $('#chart0').click(function(){
			    	  $('.content_5_charts').hide();
	
			    	  var data = new google.visualization.DataTable();
	
			    	  	data.addColumn('string', 'Account');
						data.addColumn('number', 'Records');
						data.addRows([
						<%=DatabaseManager.readSystemVerificationCharity()%>
						]);
				       	var options = {'title':'Verified Account VS Unverified Account',
				                       'width':500,
				                       'height':400};
	
				        // Instantiate and draw our chart, passing in some options.
				        var chart0 = new google.visualization.PieChart(document.getElementById('chart0_div'));
				        chart0.draw(data, options);
				        $('#chart0_div').fadeIn();
				        return false;
	
			      });
	
			      $('#chart1').click(function(){
			    	  $('.content_5_charts').hide();
	
			    	  var data = new google.visualization.DataTable();
	
			    	  data.addColumn('string', 'Date');
						data.addColumn('number', 'Records');
						data.addRows([
			<%=DatabaseManager.readSystemAccountDuration()%>
				]);
						var options = { 'title':'Date of Creating Account',
			                       		'width':500,
			                        	'height':400};
	
			        // Instantiate and draw our chart, passing in some options.
			        var chart1 = new google.visualization.PieChart(document.getElementById('chart1_div'));
			        chart1.draw(data, options);
			        $('#chart1_div').fadeIn();
				        return false;  
			      });
	
			      $('#chart2').click(function(){
			    	  $('.content_5_charts').hide();
			    	  var data = new google.visualization.DataTable();
	
			    	  data.addColumn('string', 'Date');
						data.addColumn('number', 'Records');
						data.addRows([
			<%=DatabaseManager.readSystemActiveAccount()%>
				]);
						var options = { 'title':'Active Account VS Disable Account',
			                       		'width':500,
			                        	'height':400};
			        // Instantiate and draw our chart, passing in some options.
			        var chart2 = new google.visualization.PieChart(document.getElementById('chart2_div'));
			        chart2.draw(data, options);
			        $('#chart2_div').fadeIn();
				        return false;  
			      });
	
		      });
	
	    	</script>
	    	 --%>
	    	<!-- Google Charts Stuff -->
	    	
	    	
	</head>
	<body>
		<div class="body">
			<div class="main">
	
		     <jsp:include page="headerLoggedIn.jsp"></jsp:include>	   	    
	
			<script type="text/javascript">
							var url = '<%=Configuration.getSiteUrl()%>';
							$(document).ready(function(){
								
								$.get(url+'RESTSystem/charityService/charityApprovals', function(data) {
										$.each(data, function(i,d){					
											$('#requests tr:last').after("<tr id=row"+d.charity_id+"><td>" + d.charity_name + "</td><td>" + d.registration_no + "</td><td>" + d.email+ "</td><td>" + d.charity_description + 
													"</td><td> <input id=\"btnApprove\" type=image name=Action value="+d.charity_id+" src=\"images/approve3.png\" alt=\"Approve\" onclick=\"approveRequest('"+d.charity_id+"');\" />"+
													"<input id=\"btnReject\" type=image name=Action value="+d.charity_id+" src=\"images/reject3.png\" alt=\"Reject\" onclick=\"declineRequest('"+d.charity_id+"');\"/>  </td></tr>");
										});
									});	
								
								$.get(url+'RESTSystem/charityService/charities', function(data) {
									$.each(data, function(i,d){					
										$('#accounts tr:last').after("<tr id=row"+d.charity_id+"><td>" + d.charity_name + "</td><td>" + d.registration_no + "</td><td>" + d.email+ "</td><td>" + d.charity_description + 
												"</td><td> <input id=\"btnDelete\" type=image name=Action value="+d.charity_id+" src=\"images/delete.png\" alt=\"Delete\" onclick=\"deleteAccount('"+d.charity_id+"');\" /> </td></tr>");
									});
								});	
								
							});
							
							function approveRequest(charityID)
							{
								
								//Execute the service to Generate Schema and Hibernation Configuration File
								$.post(url+'RESTSystem/charityService/generateSchema/'+charityID,function(){
								});
								
								$('#row'+ charityID).remove();
								
								return false;
							}
							
							function declineRequest(charityID)
							{
									
								//Execute the service to Generate Schema and Hibernation Configuration File
								$.post(url+'RESTSystem/charityService/rejectCharity/'+charityID,function(){
								});
								
								$('#row'+ charityID).remove();
								
								return false;
							}
							
							
							function deleteAccount(charityID){
								
								//Update isActive to 0
								
								$.post(url+'RESTSystem/charityService/deleteCharityAccount/'+charityID,function(){
								});
								
								$('#row'+ charityID).remove();
								
								return false;
							}
						
							</script> 
	
	
		    <!-- Main Content -->
		    <article id="content">
		      <div class="wrapper">
		        <div class="contentBox">
	
		        
						<h2> UCL Administration Panel </h2> 
					
					<div id="tabs">
				        <ul> 
				            <li><a href="javascript:tabSwitch(1,3,'tab_', 'content_');" id="tab_1" class="active">Requests</a></li>  
				            <li><a href="javascript:tabSwitch(2,3,'tab_', 'content_');" id="tab_2">Manage Accounts</a></li>  
				            <li><a href="javascript:tabSwitch(3,3,'tab_', 'content_');" id="tab_3">Statistics</a></li>  
				        </ul>
				    </div> 
				    <div class="tabbed_area">       
				       <div id="content_1" class="tabContent">
				   
				          <form name="frmRequests" method="POST" action="">
	      					Manage Charity Requests
					        <br/>
					        <br/>
					        <table id="requests" class="resultSet">
							<tr id="header">
					            <td>Charity Name </td>
					            <td>Charity Registration No. </td>
							    <td>Charity Email </td>
							    <td>Description </td>
						        <td>Action</td>      
							 </tr>
					        </table>        
	     				</form>
				     </div>
	
				     <div id="content_2" class="tabContent">
				     		<form name="frmAccounts" method="POST" action="">
     							Manage User Accounts
			        		<br/>
			        		<br/>

			        		<table id="accounts" class="resultSet">
							<tr id="header">
								<td> Charity Name </td>
								<td> Charity Registration No. </td>
								<td> Charity Email  </td>
								<td> Description </td>
								<td> Delete Account</td>
							</tr>
			        		</table>  
	     				</form>
				     </div>  
	
				     <div id="content_3" class="tabContent">
				     		<ul id="menubar2">
				     			<li><a id="chart0" href ="#"> Verified Account VS Unverified Account </a> <b>|</b> </li>
		             	       	<li><a id="chart1" href ="#"> Date of Creating Account </a> <b>|</b> </li>
		             	       	<li><a id="chart2" href ="#"> Active Account VS Disable Account </a> <b>|</b> </li>
	                        </ul>
	                        
	                        <br/>
	                        <br/>
	
				     		<!--Div that will hold the chart-->
	    					<div id="chart0_div" class="content_5_charts"></div>
	    					<div id="chart1_div" class="content_5_charts"></div>
	    					<div id="chart2_div" class="content_5_charts"></div>
	    					
				     </div>  
				    </div>  
	
		        </div>
		      </div>
			</article>
		    <!-- Main content -->
	
		   <jsp:include page="footer.jsp"></jsp:include>   
	
		  </div>
	</div>
	</body>
</html>
