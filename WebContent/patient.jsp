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
<title>Doctors Portal</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/tabs.css">
<script src="js/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="js/jqueryform.js"></script>
</head>
<body >
<div id="headerContainer" >
<div id="headerFont" >
<div style="font-size: 36px; font-weight: bold; color: #fff; display: inline">Doctors Portal</div>
</div>
</div>
<div id="signUpContainer" >
<div style="padding: 8px 10px;">
<span style="color: #000;font-weight: bold;"><strong>New to Doctors Portal? </strong>Sign Up! Its free.</span></div>
<!-- <form name="signUpForm" id="signUpForm" action="Controller?action=signup" method="post"> -->
<div style="padding: 10px 10px 0 10px;" >
<input type="text" name="fname"  id="fname"  class="textBoxHalf" placeholder="First Name">
<input type="text" name="lname"  id="lname"  class="textBoxHalf" placeholder="Last Name">
</div>
<div style="padding: 10px 10px 0 10px;" >
<input type="text" name="email"  id="email"  class="textBoxBig" placeholder="Your Email Address">
</div>
<div style="padding: 10px 10px 0 10px;" >
<input type="text" name="reemail"  id="reemail"  class="textBoxBig" placeholder="Re-Type Email Address">
</div>
<div style="padding: 10px;">
<input type="password" name="password" id="pwdSignUp" class="textBoxBig" placeholder="Password">
<!-- <div style="padding: 10px 10px 0 10px;" >
<input type="text" name="address"  id="address"  class="textBoxBig" placeholder="Address">
</div>
<div style="padding: 10px 10px 0 10px;" >
<input type="text" name="city"  id="city"  class="textBoxBig" placeholder="City">
</div>
<div style="padding: 10px 10px 0 10px;" >
<input type="text" name="pincode"  id="pincode"  class="textBoxBig" placeholder="Pincode">
</div>
<div style="padding: 10px 10px 0 10px;" >
<input type="text" name="state"  id="state"  class="textBoxBig" placeholder="State">
</div> -->
<input class ="submitBtn" id="submitBtn2"  value="Sign Up">
<div id="invalidDivSignUp" width="100%" style="display:none;color:red;">
</div>
</div>
<!-- </form> -->
</div>
<script>
$(document).ready(function() {

  
  
  $('#submitBtn2').click(function(event) {
	  console.log("signing up form");
	  var fname = $('#fname').val();
	  var lname = $('#lname').val();
	  var username = $('#email').val();
	  var reusername = $('#reemail').val();
	  var password = $('#pwdSignUp').val();
	  /* var address = $('#address').val(); */
	  /* var city = $('#city').val(); */
	  /* var pincode = $('#pincode').val(); */
	  /* var state = $('#state').val(); */
 	  console.log("fname:"+fname);
	  console.log("lname:"+lname);
	  console.log("email:"+username);
	  console.log("reemail:"+reusername);
	  console.log("password:"+password); 
	  if(fname==""){
		  console.log("null");
	  }
	  if(fname == "" || lname== ""  || username=="" || reusername=="" || password=="" /* || address=="" || city=="" || pincode=="" || state=="" */){
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
	  
	  var data = {
				'username': username,
				'password' : password,
				'fname' : fname,
				'lname' : lname,
				/* 'address': address, */
				/* 'city' : city, */
				/* 'state' : state, */
				/* 'pincode' : pincode, */
		};

	  $.post('Controller?action=patientsignup', data, function(res) {
		  console.log("loginResponse:"+res);
			if (res=="valid") {
				console.log("user signing up success true");
				$(location).attr('href',"welcome.jsp");
			}else {
				$('#password').val("");
				$('#invalidDivSignUp').css('display',"block");
				$('#invalidDivSignUp').text(res);
				console.log("user sing up failed");
			}
		});
	  
    /* $.ajax({
    	url: $("#signUpForm").attr( "action") +"&username="+username+"&password="+password+"&fname="+fname+"&lname="+lname,
       	type: "POST",
       	data:{'username':username
    			'id' : id
    	};
		data: {username:username,
				},
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
    }); */

    event.preventDefault();
  });

});



function redirect ( url ){
 window.location.href( url);
}
</script>
</body>
</html>