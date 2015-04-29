<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String username = (String) session.getAttribute("username");
	String login = (String) session.getAttribute("login");
	String type = (String) session.getAttribute("type");
	if (!"true".equals(login) || username == null || !"patient".equals(type)) {
		String redirectURL = "home.jsp";
		response.sendRedirect(redirectURL);
	}
%>
<%@ include file="header.jsp" %>
<body>
	<div id="headerContainer" width="100%"
		style="background-color: #3b5998; height: 70px;">
		<div id="headerFont"
			style="top: 50px; padding-top: 20px; padding-left: 20px;">
			<div
				style="font-size: 36px; font-weight: bold; color: #fff; display: inline">
				Patients Portal</div>
			<div
				style="font-size: 20px; font-weight: bold; float: right; color: #9aa9c8; display: inline; margin-right: 100px; margin-top: 14px;">
				<%=session.getAttribute("name")%>
				<span> <a href="Controller?action=logout">Log Out</a>
				</span> <span onclick="toggleDivDisplay()"> <img
					src="images/navdd.png" id="navddImg" class="navddImg"
					class="navddImg">
					<div id="navDropDown" name="navDropDown" class="navDropDown">
					    <a class="navAnchor" href="bookAppointment.html">Book Appointment</a>
					    <hr>
						<a class="navAnchor" href="changePwd.html"> Change Password</a>
						<hr>
						<a href="Controller?action=logout">Log Out</a>
						<hr>
					</div>
				</span>
			</div>
		</div>
	</div>
	<section class="black-section">
	<div class="ui-widget" style='color:white'>
		<label class="symptom-text" for="tags">Doctors for department: ${department}</label>
		<hr>
		<p style='margin:20px 0'>Date: <input type="text" id="datepicker" /></p>
		<br>
		<div class="doctorAppointment"> 
		<c:forEach var="doctor" items="${doctors}">
				<input class="doctorName" style="margin-left: 10px;"type="radio" name="type" value="${doctor.getUid()}">&nbsp;${doctor.getName()}
				<br>
				<br>
		</c:forEach>
		<br>
		</div>
	   <br>
	   <hr>
	   <br>	
	   <div class='slots'>
	   		    <div class="boxes" data-value="1">9-10 a.m</div>
				<div class="boxes" data-value="2">10-11 a.m</div>
				<div class="boxes" data-value="3">11-12 a.m</div>
				<br>
				<br>
				<div class="boxes" data-value="4">2-3 p.m</div>
				<div class="boxes" data-value="5">3-4 p.m</div>
				<div class="boxes" data-value="6">4-5 p.m</div>
	   </div>
		<input class="symptom-serach" id="submit-btn" type="button" value="Book">
	</div>
	</section>
	
	<section class="white-section">
	<div class="result" style="text-align: center" class="ui-widget"></div>
	</section>
</body>
<script>
$(document).ready(function(){
//Disabled with:
$('#submit-btn').prop("disabled",true);
$('#submit-btn').css("background", "#ccc");

var slotNumber;
$(".boxes").click(function(){
	$(".boxes").removeClass('selected');
	$(this).addClass('selected');
	
	var value = $(this).attr('data-value');
	$('#submit-btn').prop("disabled",false);
	slotNumber = value;
});
/* background: #3b5998; */

// Enabled with:
/* $('input[type="submit"], input[type="button"], button').disable(false); */
$(function() {
    $( "#datepicker" ).datepicker({ minDate: 0});
  });

	$('#submit-btn').click(
			function(event) {
				event.preventDefault();
				var date = $( "#datepicker" ).datepicker( "getDate" ),
				day  = date.getDate(),  
	            month = date.getMonth() + 1,              
	            year =  date.getFullYear();
				var bookingDate = day + '-' + month + '-' + year;
				var uid = $('input[name=type]:checked').val();
				console.log(day + '-' + month + '-' + year);
				
				var data = {
						'date': bookingDate,
						'uid' : uid,
						'department' : '${department}',
						'slot' :slotNumber,
				};

			  $.post('Controller?action=bookAppointment', data, function(res) {
				  console.log("loginResponse:"+res);
					if (res=="true") {
						console.log("booking request sent");
						window.alert("Your appointment request has been sent to the respective Doctor. Please wait for your confirmation.");
						$(location).attr('href',"welcome.html");
					}else {
						console.log("booking request failed");
						window.alert("Sorry! Your request can't be processed right now. Please try later.");
						$(location).attr('href',"welcome.html");
					}
				});
				
			});
	
 	$("#datepicker").datepicker({
 		  minDate: 0,
		  onSelect: function(dateText) {
		    console.log("Selected date: " + dateText + "; input's current value: " + this.value);
		    var uid = $('input[name=type]:checked').val();
			
			if(uid==null || uid.length==0){
				return;
			}
			getSlots();
		  }
		}); 
 	
 	$('input:radio').change(
 		    function(){
 		        console.log('changed');
 		        var uid = $('input[name=type]:checked').val();
 		        console.log(uid);
 		        
 		        var date = $( "#datepicker" ).datepicker( "getDate" );
 				if(date==null || date.length==0){
 					return;
 				}
 				
 		        getSlots();
 		    }
 		); 
 	
 	function getSlots(){
		event.preventDefault();
		console.log("getting slots");
		var date = $( "#datepicker" ).datepicker( "getDate" ),
		day  = date.getDate(),  
        month = date.getMonth() + 1,              
        year =  date.getFullYear();
		
		if(date==null || date.length==0){
			return;
		}
		
		var bookingDate = day + '-' + month + '-' + year;
		var uid = $('input[name=type]:checked').val();
		
		if(uid==null || uid.length==0){
			return;
		}
		
		console.log(day + '-' + month + '-' + year);
		
		var data = {
				'date': bookingDate,
				'uid' : uid,
				'department' : '${department}',
		};

	  $.post('Controller?action=getSlots', data, function(res) {
			if (res!=null || res.length!=0) {
				console.log("slots recieved:"+res);
				$(".slots").show();
				if(res.indexOf("1") === -1){
					$("div").find("[data-value=1]").addClass('disable');
					var divText = $("div").find("[data-value=1]").text();
					$("div").find("[data-value=1]").text("Filled");
					$("div").find("[data-value=1]").attr("disabled", "disabled").off('click');
				}
				
				if(res.indexOf("2") === -1){
					$("div").find("[data-value=2]").addClass('disable');
					var divText = $("div").find("[data-value=2]").text();
					$("div").find("[data-value=2]").text("Filled");
					$("div").find("[data-value=2]").attr("disabled", "disabled").off('click');
				}
				if(res.indexOf("3") === -1){
					$("div").find("[data-value=3]").addClass('disable');
					var divText = $("div").find("[data-value=3]").text();
					$("div").find("[data-value=3]").text("Filled");
					$("div").find("[data-value=3]").attr("disabled", "disabled").off('click');
				}
				if(res.indexOf("4") === -1){
					$("div").find("[data-value=4]").addClass('disable');
					var divText = $("div").find("[data-value=4]").text();
					$("div").find("[data-value=4]").text("Filled");
					$("div").find("[data-value=4]").attr("disabled", "disabled").off('click');
				}
				if(res.indexOf("5") === -1){
					$("div").find("[data-value=5]").addClass('disable');
					/* var divText = $("div").find("[data-value=5]").text(); */
					$("div").find("[data-value=5]").text("Filled");
					$("div").find("[data-value=5]").attr("disabled", "disabled").off('click');
				}
				if(res.indexOf("5") === -1){
					$("div").find("[data-value=6]").addClass('disable');
					var divText = $("div").find("[data-value=6]").text();
					$("div").find("[data-value=6]").text("Filled");
					$("div").find("[data-value=6]").attr("disabled", "disabled").off('click');
				}
				
				
			}else {
				window.alert("Sorry! No slots on this day Please try some other date.");
				$(location).attr('href',"welcome.html");
			}
		});
 	}
 	
 	
 	
});	
</script>
</html>