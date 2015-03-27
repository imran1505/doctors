<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<body>
	<div id="headerContainer">
		<div id="headerFont">
			<div
				style="font-size: 36px; font-weight: bold; color: #fff; display: inline">Doctors
				Portal</div>
		</div>
	</div>
		<div id="signInContainer">
			<form name="signUpForm" id="signUpForm"
				action="Controller?action=newpwd" method="post">
				<div style="padding: 10px 10px 0 10px;">
					<span style="padding: 10px 10px 0 10px;">Please enter your
						new password.</span>
					<div style="padding: 10px">
						<input type="password" name="pwd" id="pwd" class="textBoxBig"
							placeholder="New password">
					</div>
					<div style="padding: 10px">
						<input type="password" name="repwd" id="repwd" class="textBoxSmall" placeholder="Re-Enter password"> 
						<input type="hidden" name="uid" id="uid" value='${uid }'> 
						<input type="hidden" name="code" id="code" value='${code }'> 
						<input type="hidden" name="type" id="type" value='${type }'> 
						<input class="submitBtn" id="submitBtn" type="submit" value="Change">
					</div>
				</div>
				</form>
				<div style="padding: 10px;">
					<div id="invalidDivSignUp" width="100%"
						style="display: none; color: red; padding: 10px"></div>
				</div>
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
	  var type = $('#type').val();
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
    	url: $("#signUpForm").attr( "action") +"&code="+code+"&uid="+uid+"&pwd="+pwd+"&type="+type,
       	type: "POST",
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "text/plain");
    		xhr.setRequestHeader("Content-Type", "text/plain");
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
				redirect("home.html");
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