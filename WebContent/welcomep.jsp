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
		<label class="symptom-text" for="tags">Feeling sick? Type your symptoms here</label><br> 
		<input class="symptom-searchbox" id="tags" size="2"> 
		<input class="symptom-serach" id="submit-btn" type="button" value="Search">
	</div>
	</section>
	
	<section class="white-section">
	<div class="result" style="text-align: center" class="ui-widget"></div>
	</section>
</body>
<script>
$(function() {
    var availableTags = [
      "Fever",
      "Nausea",
      "Vomitting",
      "Bleeding",
      "Headache",
      "Cough",
      "Stomach Ache",
      "Swelling",
      "Red Eyes",
      "Rashes",
      "Chest Pain",
      "Throat Pain",
      "Irritation",
      "Shivering",
    ];
    function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
 
    $( "#tags" )
      // don't navigate away from the field on tab when selecting an item
      .bind( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function( request, response ) {
          // delegate back to autocomplete, but extract the last term
          response( $.ui.autocomplete.filter(
            availableTags, extractLast( request.term ) ) );
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
        }
      });
  });
  

	$('#submit-btn').click(function(event) {
		event.preventDefault();
		var symptoms = $('#tags').val();
		if (symptoms == null || symptoms == "") {
			return;
		}
		var data = {
			'symptoms' : symptoms,
		};
		$.post('Controller?action=symptoms', data, function(res) {
			console.log("symptoms response:" + res);
			$('.result').html(res);
		});
	});
</script>
</html>