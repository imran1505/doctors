<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String username=(String)session.getAttribute( "username" ); 
	String login=(String)session.getAttribute( "login" ); 
	if("true".equals(login) && username!=null){
	System.out.println("in home.jsp");
    String redirectURL = "welcome.jsp";
    response.sendRedirect(redirectURL);}
	String uid= request.getParameter("uid");
	String code= request.getParameter("v");
	if(uid==null || code==null){
		String redirectURL = "home.jsp";
		response.sendRedirect(redirectURL);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Backup</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/tabs.css">
<script src="js/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="js/jqueryform.js"></script>
</head>
<body>
	<div id="headerContainer">
		<div id="headerFont">
			<a href="home.jsp">
				<div
					style="font-size: 36px; font-weight: bold; color: #fff; display: inline">Contact
					Backup</div> </a>
		</div>
		<div id="signInContainer">
			<form name="signUpForm" id="signUpForm"
				action="Controller?action=verify" method="post">
				<div style="padding: 10px 10px 0 10px;">
					<span style="padding: 10px 10px 0 10px;">Please enter your
						new password.</span>
					<div style="padding: 10px">
						<input type="password" name="pwd" id="pwd" class="textBoxBig"
							placeholder="New password">
					</div>
					<div style="padding: 10px">
						<input type="password" name="repwd" id="repwd"
							class="textBoxSmall" placeholder="Re-Enter password"> <input
							type="hidden" name="uid" id="uid" value='<%=uid%>'> <input
							type="hidden" name="code" id="code" value='<%=code%>'> <input
							class="submitBtn" id="submitBtn" type="submit" value="Change">
					</div>
				</div>
				</form>
				<div style="padding: 10px;">
					<div id="invalidDivSignUp" width="100%"
						style="display: none; color: red; padding: 10px"></div>
				</div>
</body>

<script>
$(document).ready(function() {
  
  $('#signUpForm').submit(function(event) {
	  console.log("signing up form");
	  var pwd = $('#pwd').val();
	  var repwd = $('#repwd').val();
	  var code = $('#code').val();
	  var uid = $('#uid').val();
	  console.log("pwd:"+pwd);
	  if(pwd==""){
		  console.log("cannot be null");
		  $('#invalidDivSignUp').css('display',"block");
		  $('#invalidDivSignUp').text('Please enter new password.');
		  return false;
	  }
	  
	  if(pwd!=repwd){
		  console.log("both pwd are not same");
		  $('#invalidDivSignUp').css('display',"block");
		  $('#invalidDivSignUp').text('Both password should be matching.');
		  return false;
	  }
	  if(pwd.length<6){
		  console.log("password length less than 6 characters");
			$('#invalidDivSignUp').css('display',"block");
			$('#invalidDivSignUp').text('Password should be atleast 6 characters long.');
		    return false;
	  }
	  
	  	
		
    $.ajax({
    	url: $("#signUpForm").attr( "action") +"&code="+code+"&uid="+uid+"&pwd="+pwd,
       	type: "POST",
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "text/plain");
    		xhr.setRequestHeader("Content-Type", "text/plain");
			//xhr.setRequestParameter("username",username);
			//xhr.setRequestParameter("password",password);
    	},
    	success: function(loginResponse) {
    		console.log("loginResponse:"+loginResponse);
			if(loginResponse=="true"){
			console.log("password changed successfully.");
			alert("Your password has been changed successfully!. Redirecting to home page. Please login with new password to continue.");
			redirect("home.jsp");
			}else{
				console.log("password not changed successfully.");
				alert("This link has expired. Please generate another link. Redirecting to homepage.");
				redirect("home.jsp");
				console.log("user sing up failed");
			}
		 		
    	},
		error: function(loginresponse){
		console.log("ajax error");
		}
    });

    event.preventDefault();
  });

});


function redirect ( url ){
 window.location.href= url;
}
</script>
</html>