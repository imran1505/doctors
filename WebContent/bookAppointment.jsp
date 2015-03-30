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
	<div style="text-align: center" class="ui-widget">
		<label class="symptom-text" for="tags">Feeling sick? Search for Doctors by department</label><br> 
		<select name="department" id="department">
		<option value="">Select</option>
		<c:forEach var="department" items="${departments}">
			<option value="${department.getDepartmentName()}">${department.getDepartmentName()}</option>
		</c:forEach>
	</select>
		<input class="symptom-serach" id="submit-btn" type="button" value="Search">
	</div>
	</section>
	
	<section class="white-section">
	<div class="result" style="text-align: center" class="ui-widget"></div>
	</section>
</body>
<script>

	$('#submit-btn').click(function(event) {
		event.preventDefault();
		var department = $('#department').val();
		if (department == null || department == "") {
			return;
		}
		$(location).attr('href',"bookAppointment.html?department="+department);
		console.log("asdas");
	});
</script>
</html>