<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<body>
	<div id="headerContainer">
		<div id="headerFont">
			<div
				style="font-size: 36px; font-weight: bold; color: #fff; display: inline">Doctors
				Portal</div>
		</div>
	</div>
	<div id="signInContainer" style="text-align:center;">
		<form name="recoverForm" id="recoverForm" action="Controller?action=recover" method="post">
			<div style="padding: 10px 10px 0 10px;">
				<div style="padding: 10px;">
					<input checked style="margin-left: 5px;" type="radio" name="type"value="doctor">Doctor 
					<input style="margin-left: 10px;" type="radio" name="type" value="patient">Patient
				</div>
				<span>Email address</span> <input type="text" name="email" id="email" class="textBoxBig" placeholder="Registered email address"> <br>
				<input class="submitBtn" id="submitBtn" type="submit" value="Recover">
			</div>
			<div style="padding: 10px;">
				<div id="invalidDivSignUp" width="100%" style="display: none; color: red;"></div>
			</div>
			<div id='overlayDiv' class='overlayDiv'"></div>
			<div id='overlayDiv2' class='overlayDiv2'>
				<div style='margin: 50px'>Recovering your password. Please wait .....</div>
			</div>
		</form>
	</div>
</body>
<script>
$(document).ready(function() {
  
  $('#recoverForm').submit(function(event) {
	  console.log("signing up form");
	  var email = $('#email').val();
	  var type = $('input[name=type]:checked', '#recoverForm').val();
	  console.log("email:"+email +" type:"+type);
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
    	url: $("#recoverForm").attr( "action") +"&email="+email+"&type="+type,
       	type: "POST",
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "text/plain");
    		xhr.setRequestHeader("Content-Type", "text/plain");
    		$('#invalidDivSignUp').css('display',"none");
    		$('#invalidDivSignUp').text("");
    		$('#overlayDiv').show();
    		$('#overlayDiv2').show();
    	},
    	success: function(recoveryResponse) {
    		cancelEdit();
    		console.log("revovery response:"+recoveryResponse);
			if(recoveryResponse=="sent"){
				console.log("registered user hence recovery email sent successfully.");
				alert("A recovery link has been sent to your registered email address. Redirecting to home page.");
				redirect("home.html");
			}else{
				$('#invalidDivSignUp').css('display',"block");
				$('#invalidDivSignUp').text(recoveryResponse);
				console.log("recovery failed");
			}
		 		
    	},
		error: function(recoveryResponse){
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