<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String username=(String)session.getAttribute( "username" ); 
	String login=(String)session.getAttribute( "login" ); 
	if("true".equals(login) && username!=null){
	System.out.println("in home.jsp");
    String redirectURL = "welcome.jsp";
    response.sendRedirect(redirectURL);}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recover</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/tabs.css">
<script src="js/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="js/jqueryform.js"></script>
</head>
<body >
<div id="headerContainer" >
<div id="headerFont" >
<a href="home.jsp">
<div style="font-size: 36px; font-weight: bold; color: #fff; display: inline">Contact Backup</div>
</a>
</div>
<div id="signInContainer" >
<form name="signUpForm" id="signUpForm" action="Controller?action=recover" method="post">
<div style="padding: 10px 10px 0 10px;" >
<span>Please enter your email address.</span>
<input type="text" name="email"  id="email"  class="textBoxBig" placeholder="Registered email address">
<input class ="submitBtn" id="submitBtn"  type="submit" value="Recover">
</div>
<div style="padding: 10px;">
<div id="invalidDivSignUp" width="100%" style="display:none;color:red;" >
</div>
</div>
<div id='overlayDiv' class='overlayDiv'"></div>
	<div id='overlayDiv2' class='overlayDiv2' >
		<div style='margin:50px'>Recovering your password. Please wait .....</div>
	</div>
</div>
</body>
<script>
$(document).ready(function() {
  
  $('#signUpForm').submit(function(event) {
	  console.log("signing up form");
	  var email = $('#email').val();
	  console.log("email:"+email);
	  if(email==""){
		  console.log("cannot be null");
		  $('#invalidDivSignUp').css('display',"block");
		  $('#invalidDivSignUp').text('Please enter the registered email address.');
		  return false;
	  }
	  //checking if email is correct
	  var atpos=email.indexOf("@");
	  var dotpos=email.lastIndexOf(".");
	  if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
	    {
	    console.log("not all fields are populated");
		$('#invalidDivSignUp').css('display',"block");
		$('#invalidDivSignUp').text('Please provide a valid email address.');
	    return false;
	    }
	  
	  console.log("email enetered"+email);

	  
    $.ajax({
    	url: $("#signUpForm").attr( "action") +"&email="+email,
       	type: "POST",
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "text/plain");
    		xhr.setRequestHeader("Content-Type", "text/plain");
    		$('#invalidDivSignUp').css('display',"none");
    		$('#invalidDivSignUp').text("");
    		$('#overlayDiv').show();
    		$('#overlayDiv2').show();
    	},
    	success: function(loginResponse) {
    		cancelEdit();
    		console.log("loginResponse:"+loginResponse);
			if(loginResponse=="valid"){
				console.log("registered user hence recovery email sent successfully.");
				alert("A recovery link has been sent to your registered email address. Redirecting to home page.");
				redirect("home.jsp");
			}else{
				$('#invalidDivSignUp').css('display',"block");
				$('#invalidDivSignUp').text(loginResponse);
				console.log("user sing up failed");
			}
		 		
    	},
		error: function(loginresponse){
			cancelEdit();
			$('#invalidDivSignUp').css('display',"block");
			$('#invalidDivSignUp').text("Facing technical difficulty.");
			console.log("ajax error");
		}
    });

    event.preventDefault();
  });

});


function cancelEdit(){
	$('#overlayDiv').hide();
	$('#overlayDiv2').hide();
	
}


function redirect ( url ){
 window.location.href= url;
}
</script>
</html>