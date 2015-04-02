<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<style>
table {
  background-color: transparent;
  width:80%;
  margin: 50px auto;
  font-size: 12px !important;
  color:white !important;
  text-transform: capitalize;
} 
tbody {
  display: table-row-group;
  vertical-align: middle;
  border-color: inherit;
}
th {
  font-size: 1.4em;
  text-align: left;
  padding-top: 5px;
  padding-bottom: 4px;
  background-color: #A7C942;
  color: #fff;
}

td,th {
  font-size: 1.2em;
  border: 1px solid #98bf21;
  padding: 10px;
}
</style> 
<body>
	<div id="headerContainer" width="100%"
		style="background-color: #3b5998; height: 70px;">
		<div id="headerFont"
			style="top: 50px; padding-top: 20px; padding-left: 20px;">
			<div
				style="font-size: 36px; font-weight: bold; color: #fff; display: inline">
				Doctors Portal</div>
			<div
				style="font-size: 20px; font-weight: bold; float: right; color: #9aa9c8; display: inline; margin-right: 100px; margin-top: 14px;">
				<%=session.getAttribute("name")%>
				<span> <a href="Controller?action=logout">Log Out</a> </span>
				<span onclick="toggleDivDisplay()">
					<img  src="images/navdd.png" id="navddImg" class="navddImg" class="navddImg" ></img>
					<div id="navDropDown" name= "navDropDown" class="navDropDown" >
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
	<div style="text-align: center; width:49%;display:inline-block;" class="ui-widget">
		<label class="symptom-text" for="tags">You upcoming appointments</label><br> 
		
		<c:if test="${empty confirmedAppointments}">
		     It seems that you don't have any appointment upcoming.
		</c:if>
		<table>
		<c:forEach var="appointment" items="${confirmedAppointments}">
			<tr>
			   <td>${appointment.getPatientName() }</td><td>${appointment.getAppointmentdate() }</td><td>${appointment.getStatus() }</td>
			</tr>
		</c:forEach>
		</table>
		
	</div>
	<div style="text-align: center;width:49%;display:inline-block;" class="ui-widget">
	<br>
	<label class="symptom-text" for="tags">New Appointment Requests</label><br>
	<c:if test="${empty pendingAppointments}">
		     It seems that you haven't got any new appointment request
	</c:if>
	<table>
		<c:forEach var="appointment" items="${pendingAppointments}">
			<tr>
			   <td>${appointment.getPatientName() }</td><td>${appointment.getAppointmentdate() }</td><td>${appointment.getStatus() }</td>
			        <td><input id="${appointment.getId() }" class="confirm" type="button" value="Approve"></td>
			        <td><input id="${appointment.getId() }" class="cancel" type="button" value="Cancel"></td>
			</tr>
		</c:forEach>
	</table>
	 </div>
	 
	<div style="text-align: center;" class="ui-widget">
	<br>
	<label class="symptom-text" for="tags">Your appointment history</label><br>
	<c:if test="${empty pastAppointments}">
		     Never have been an appointment request made to you
	</c:if>
	<table>
		<c:forEach var="appointment" items="${pastAppointments}">
			<tr>
			   <td>${appointment.getPatientName() }</td><td>${appointment.getAppointmentdate() }</td>
			</tr>
		</c:forEach>
	</table>
	 </div>
	</section>
</body>
<script>
	var active, listWidth, sign, factor, member_id;
	$(document).ready(function() {

		$('.tabs li:eq(0) a').addClass('active');
		$('.feat-tabs li a').click(function(event) {
			event.preventDefault();

			var active_tab = $('.feat-tabs li.selected a').attr('href'); //display tab content
			console.log("active tab:" + active_tab)
			var activated_tab = $('.feat-tabs li.selected'); //find already activated tab
			activated_tab.removeClass('selected'); //remove its active class
			$(this).parents('li').addClass('selected'); //add active class to clicked navigation

			//Hide displaying tag
			$(active_tab).removeClass('selected');
			$(active_tab).addClass('hide');

			//show target tab content by removing hide css and applying active css
			var new_tab = $(this).attr('href');
			console.log("new tab:" + new_tab);
			$(new_tab).removeClass('hide');
			$(new_tab).addClass('selected');
		});

	});
	
	
	
	
	var isExtensionCorrect = false;

	var options = { 
			beforeSubmit:  showRequest,  // pre-submit callback 
	        success:       showResponse  // post-submit callback 
		};
	
	function clickFileSelect(){
		document.getElementById('filename').click();
		return false;
	}
	
	function showChangeImage(){
		$('#changeImage').show();
		return false;
	}
	
	
	
	
	function changeFileName(){
		isExtensionCorrect=false;
		console.log("sending file");
		var str = document.getElementById("filename").value;
		console.log("filename:"+str);
		var indexForFileNameStart =str.lastIndexOf('\\');
		var length= str.length;
		console.log("len:"+length);
		console.log("index:"+indexForFileNameStart );
		var fileName = str.substring(indexForFileNameStart+1,length);
		console.log("exact file Name:"+fileName);
		var indexForExt= fileName.lastIndexOf("\.");
		console.log("index for extension:"+indexForExt);
		var ext = fileName.substring(indexForExt+1,fileName.length);
		console.log("extension:"+ext);
		if(ext=='pdf'){
			isExtensionCorrect=true;
			$('#importForm').submit();
		}
		return false;
	}
	
	
	function showRequest(formData, jqForm, options) {
		
		//document.getElementById('saving').style.display='block';
		
	    if(isExtensionCorrect){
			console.log("submitting form");
			return true;
		}
	    console.log("not submitting");
	    return false; 
	}


	function showResponse(responseText, statusText, xhr, $form)  { 
	    console.log("status:"+statusText);
	    console.log("responseText:"+responseText);
	    if(responseText!="false"){
	    	$('#review').show();
	    	/* $('#changeImages').text('Change your uploaded document'); */
	    	var filename = "/static/"+responseText;
	    	$('#pdfview').html(' <embed  src="'+filename+'" width="100%" height="500">');
	    }else{
	    	alert("Document size too large.");
	    }
	    	
		
	} 
	
	$('#importForm').ajaxForm(options);
	
	$('.confirm').click(function(event) {
		event.preventDefault();
		var bookingid = this.id;
		console.log(bookingid);
		var data = {
				'bookingid': bookingid,
		};

	  $.post('Controller?action=approveAppointment', data, function(res) {
			if (res=="true") {
				console.log("booking successful");
				window.alert("Appoitment Request has been succesfully approved");
			}else {
				console.log("booking unsuccessful");
				window.alert("Appoitment Request failed. Please try later.");
			}
			$(location).attr('href',"welcome.html");
		});
	});
	
	
	$('.cancel').click(function(event) {
		event.preventDefault();
		var bookingid = this.id;
		console.log(bookingid);
		var data = {
				'bookingid': bookingid,
		};

	  $.post('Controller?action=cancelAppointment', data, function(res) {
			if (res=="true") {
				console.log("cancelling successful");
				window.alert("Appoitment Request has been cancelled succesfully approved");
			}else {
				console.log("cancelling unsuccessful");
				window.alert("Appoitment Request cancellation failed. Please try later.");
			}
			$(location).attr('href',"welcome.html");
		});
	});
	
	
</script>
</html>