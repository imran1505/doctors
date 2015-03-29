<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<style>
table {
  background-color: transparent;
  width:auto;
  margin:50px;
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
	<div style="text-align: center" class="ui-widget">
		<label class="symptom-text" for="tags">You upcoming appointments</label><br> 
		
		<!-- appointments -->
		
	</div>

	</section>
	<div style="text-align: center" class="ui-widget">
	<table>
		<c:forEach var="appointment" items="${appointments}">
			<tr>
			   <td>${appointment.getPatientName() }</td><td>${appointment.getAppointmentdate() }</td><td>${appointment.getStatus() }</td><td><input id="${appointment.getId() }" class="confirm" type="button" value="Confirm"></td>
			</tr>
		</c:forEach>
		</table>
	 </div>
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
	
	
</script>
</html>