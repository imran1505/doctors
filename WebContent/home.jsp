<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String username = (String) session.getAttribute("username");
	String login = (String) session.getAttribute("login");
	String type = (String) session.getAttribute("type");
	if ("true".equals(login) && username != null) {
		System.out.println("in home.jsp");
		if("doctor".equals(type)){
			String redirectURL = "welcome.jsp";
			response.sendRedirect(redirectURL);
		}
		else if("patient".equals(type)){
			String redirectURL = "welcomep.jsp";
			response.sendRedirect(redirectURL);
		}else{
			String redirectURL = "error.jsp";
			response.sendRedirect(redirectURL);
		}
		
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctors Portal</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/tabs.css">
<script src="js/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="js/jqueryform.js"></script>
</head>
<body>
	<div id="headerContainer">
		<div id="headerFont">
			<div
				style="font-size: 36px; font-weight: bold; color: #fff; display: inline">Doctors
				Portal</div>
		</div>
	</div>
	<div id="signInContainer">
		<form name="loginForm" id="loginForm" action="Controller?action=login" method="post">
			<div style="padding: 10px 10px 0 10px;">
				<input type="text" name="username" id="username" class="textBoxBig"
					placeholder="Username">
			</div>
			<div style="padding: 10px;">
				<input type="password" name="password" id="password" class="textBoxSmall" placeholder="Password">
				<input class="submitBtn" id="submitBtn" type="submit" value="Log In">	
			</div>
			<div style="padding: 10px;">
				<!-- <span style="font-weight:bold">I am a &nbsp; &nbsp;</span><select name="type" id="type">
					<option value="doctor">Doctor</option>
					<option value="patient">Patient</option>
				</select> -->
				<input checked style="margin-left: 5px;"type="radio" name="type" value="doctor">Doctor
				<input style="margin-left: 10px;" type="radio" name="type" value="patient">Patient
			
			<!-- <div>
				<input class="submitBtn" id="submitBtn" type="submit" value="Log In">
			</div> -->
				<a style="float: right;text-align: left;padding-right: 50px;font-size: small;" href="recover.jsp">Forgot Password?</a>
				
				<div id="invalidDiv" width="100%" style="display: none; color: red;padding:10px;">
				Invalid user name or password.</div>
			</div>
	</div>
	</form>
	</div>
	<!-- <div id="signUpContainer" >
<div style="padding: 8px 10px;">
<span style="color: #000;font-weight: bold;"><strong>New to Doctors Portal? </strong></span></div>
<form name="signUpForm" id="signUpForm" action="Controller?action=signup" method="post">
<div style="padding: 185x 65px 0 10px;" >
<div><strong>Or Sign Up! Its free.<br><br></strong></div>
<input type="text" name="fname"  id="fname"  class="textBoxHalf" placeholder="First Name">
<input type="text" name="lname"  id="lname"  class="textBoxHalf" placeholder="Last Name">
</div>
<div style="padding: 10px 65px 0 10px;" >
<input type="text" name="email"  id="email"  class="textBoxBig" placeholder="Your Email Address">
</div>
<div style="padding: 10px 65px 0 10px;" >
<input type="text" name="reemail"  id="reemail"  class="textBoxBig" placeholder="Re-Type Email Address">
</div>
<div style="padding: 10px 58px 0 10px;" >
<input type="password" name="password" id="pwdSignUp" class="textBoxSmall" placeholder="Password">
<input class ="submitBtn" id="submitBtn2"  type="submit" value="Sign Up">
<div id="invalidDivSignUp" width="100%" style="display:none;color:red;" >
</div>
</div>
</form>
</div> -->
	<script>
$(document).ready(function() {

  $('#loginForm').submit(function(event) {
	  console.log("hello");
	  var username = $('#username').val();
	  var password = $('#password').val();
	  var type = $('input[name=type]:checked', '#loginForm').val();
	  console.log("username:"+username+" password:"+password);
	  if(username=="" || password=="" || username==null || password==null){
		  console.log("not all fields are populated");
		  $('#invalidDiv').css('display',"block");
		  $('#invalidDiv').text('All fields are mandatory');
		  return false;

	  }

    $.ajax({
    	url: $("#loginForm").attr( "action") +"&username="+username+"&password="+password+"&type="+type,
       	type: "GET",
		data: '{"username":"' + username + '"}',
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "text/plain");
    		xhr.setRequestHeader("Content-Type", "text/plain");
	  		$('#invalidDivSignUp').css('display',"none");
  		    $('#invalidDivSignUp').text('');
    	},
    	success: function(loginResponse) {
    		console.log("loginResponse:"+loginResponse);
			if(loginResponse=="valid"){
			//return false;
			console.log("user verfied true");
			$(location).attr('href',"welcome.jsp");
			}else{
			//return false;
			//$(location).attr('href',"invalid.html");
			$('#username').val("");
			$('#password').val("");
			$('#invalidDiv').css('display',"block");
			$('#invalidDiv').text('Invalid username or password.');
			console.log("user verfied false");
			}
		 		
    	},
		error: function(loginresponse){
		console.log("ajax error");
		}
    });

    event.preventDefault();
  });
  
  
  $('#signUpForm').submit(function(event) {
	  console.log("signing up form");
	  var fname = $('#fname').val();
	  var lname = $('#lname').val();
	  var username = $('#email').val();
	  var reusername = $('#reemail').val();
	  var password = $('#pwdSignUp').val();
	  console.log("fname:"+fname);
	  console.log("lname:"+lname);
	  console.log("email:"+username);
	  console.log("reemail:"+reusername);
	  console.log("password:"+password);
	  if(fname==""){
		  console.log("null");
	  }
	  if(fname == "" || lname== ""  || username=="" || reusername=="" || password==""){
		  console.log("not all fields are populated");
		  $('#invalidDivSignUp').css('display',"block");
		  $('#invalidDivSignUp').text('All fields are mandatory');
		  return false;
	  }
	  
	  //checking if both emails provided are same
	  if(username!=reusername){
		  console.log("both email are not same");
		  $('#invalidDivSignUp').css('display',"block");
		  $('#invalidDivSignUp').text('Re-entered email is not same as email.');
		  return false;
	  }
	  
	  //checking if email is correct
	  var atpos=username.indexOf("@");
	  var dotpos=username.lastIndexOf(".");
	  if (atpos<1 || dotpos<atpos+2 || dotpos+2>=username.length)
	    {
	    console.log("not all fields are populated");
		$('#invalidDivSignUp').css('display',"block");
		$('#invalidDivSignUp').text('Please provide a valid email address.');
	    return false;
	    }
	  
	  if(password.length<6){
		  console.log("password length less than 6 characters");
			$('#invalidDivSignUp').css('display',"block");
			$('#invalidDivSignUp').text('Password should be atleast 6 characters long.');
		    return false;
	  }
	  console.log("username:"+username+" password:"+password);

	  
    $.ajax({
    	url: $("#signUpForm").attr( "action") +"&username="+username+"&password="+password+"&fname="+fname+"&lname="+lname,
       	type: "POST",
		data: '{"username":"' + username + '"}',
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "text/plain");
    		xhr.setRequestHeader("Content-Type", "text/plain");
			//xhr.setRequestParameter("username",username);
			//xhr.setRequestParameter("password",password);
    	},
    	success: function(loginResponse) {
    		console.log("loginResponse:"+loginResponse);
			if(loginResponse=="valid"){
			//return false;
			console.log("user signing up success true");
			$(location).attr('href',"welcome.jsp");
			}else{
			//return false;
			//$(location).attr('href',"invalid.html");
			$('#password').val("");
			$('#invalidDivSignUp').css('display',"block");
			$('#invalidDivSignUp').text(loginResponse);
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
 window.location.href( url);
}
</script>
</body>
</html>