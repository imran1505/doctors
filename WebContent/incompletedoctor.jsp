<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
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
		<label class="symptom-text" for="tags">You haven't verified your account.We need to validate your registration document.</label><br> 
	</div>
	<br>
	<br>
	<div class="container">
			<a id="changeImages" href="#" onclick="clickFileSelect()" class="symptom-text" for="tags" >
			<c:if test="${not empty doctor.registrationDocName and doctor.status ne 'verified'}">
				Change your registration doc
				<div id="pdfview">
					<embed  src="/static/${doctor.registrationDocName}" width="100%" height="700">
				</div>
			</c:if>
			<c:if test="${empty doctor.registrationDocName  or doctor.status eq 'verified'}">
				Upload your registration doc
			</c:if>
			</a>
			<form action="Controller?action=upload&type=image" id="importForm" method="post" enctype="multipart/form-data">
				<input onchange="changeFileName()" type="file" class="submitBtn" id="filename" accept="application/pdf" style="display: none" name="file" size="50" />
			</form>
			<br>
			<div id="review" style="display:none;color:white">Your document has been uploaded. We will review and confirm your registration.</div>
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
	
	
</script>
</html>