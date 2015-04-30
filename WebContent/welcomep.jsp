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
<%@ include file="headerp.jsp" %>
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
	<c:if test="${not empty cuid}">
	    <div style="text-align: center" class="ui-widget">
		<div class="iframe" style="display:hidden;">
    			<iframe name="ifrm" id="ifrm" src="http://128.199.78.67:9090/?name=${name}&id=${cuid}" frameborder="0">
        				Your browser doesn't support chats.
    			</iframe>
		</div>
	</div>
	</c:if>
	<div style="text-align: center" class="ui-widget">
		<label class="symptom-text" for="tags">Feeling sick? Type your symptoms here</label><br> 
		<input class="symptom-searchbox" id="tags" size="2"> 
		<input class="symptom-serach" id="submit-btn" type="button" value="Search">
	</div>
	</section>
	
	<section class="white-section">
	<div class="result" class="ui-widget"></div>
	</section>
</body>
<script>
$(function() {
    var availableTags = [
"constipation or diarrhea",
"mood swings",
"hoarseness",
"mouth sores",
"loss of energy",
"vertigo",
"enlarged Liver",
"painful Intercourse",
"pale stools",
"breast tenderness",
"chest pain",
"mania",
"helplessness",
"hunger",
"diarrhea",
"easy bruising",
"facial pain",
"back pain",
"jaundice",
"vision changes",
"vaginal infections",
"abdominal swelling",
"sweating",
"low backache",
"dry Mouth",
"abdominal Pain",
"increased urination",
"eye pain",
"double vision",
"leukoplakia of the tongue",
"leg swelling",
"self-loathing",
"easy bruising",
"muscle cramps",
"paralysis",
"pelvic pain",
"burning",
"coma",
"hair loss",
"elevated heart rate",
"black stools",
"hopelessness",
"fungal nails",
"increased thirst",
"sneezing",
"loss of appetite",
"joint pain",
"blood in urine",


"chills",
"red eyes",
"changes in skin color",
"memory problems",
"tiredness",
"weight loss",
"painful intercourse",
"numbness",
"fainting",
"guilt",
"muscle spasms",
"coughing",
"itching skin",
"blood in the urine",
"painful lymph nodes",
"stress",
"bloating",
"weakness",

"shoulder pain",
"facial swelling",
"burning when trying to urinate",
"slow-healing sores",
"heavy menstrual periods",
"joint pains",
"seizures",
"shivering",
"headache",
"rectal pain",
"dark urine",
" itchy and watery eyes",
"shortness of breath",
"abdominal distension",
"leg swelling",
"fatigue",

"leg cramps",

"earache",
"blood in urine",
"frequent infections",
"abnormal vaginal bleeding",
"upper abdominal pain",
"white patches in the throat",
"lack of interest in daily activities",
"dark stool",

"scratchy or sore throat",
"frequent urination",

"flatulence",
"swollen Lymph Nodes",
"red rashses",
"abdominal mass",

"change in stool consistency",

"vaginal discharge",
"wheezing",
"acne",
"stuffy or runny nose",
"poor appetite",
"purplish skin",
"watery stool",
"night sweats",
"weight loss",
"feelings of worthlessness",
"bleeding after sexual intercourse",
"vaginal irritation",
"malignancies",
"confusion",
"breast swelling",
"rash",
"increased susceptibility to infection",
"low back pain",

"gait disturbances",
"depression",
"Bleeding Between Periods",
"gum disease",
"Reddened Skin",
"cloudy urine",
"testicular Pain",

"darkened areolas",

"anemia",
"sore muscles",


"flank pain",
"tingling",
"vaginal itching",
"irritation",
"vaginal burning",

"muscle pain",
"absent menstrual periods",
"appear pale",
"heat intolerance",
"dizziness",

"loss of bladder control",
"frequent urination",
"decreased urine output",




"discomfort",
"sleep problems",
"mood changes",

"bleeding gums",
"Loss of Muscle Control",
"abdominal Cramping",
"Sore Throat",
"cold",
"Shortness of Breath",
"jaw pain",
"runny nose",
"difficulty speaking",
"diarrhea",
"Blurred Vision",
"urinary urgency",
"scrotal Pain",
"mouth ulcers",
"chest Pain",
"nosebleeds",
"flushing",
"implantation bleeding",
"Yeast Infections",
"blood in Stool",
"blood in sputus",
"sore throat",
"abdominal Tenderness",
"palpitations",
"early satiety",
"psychosis",
"fever",
"incontinence",
"constipation",
"change in consciousness",
"fungal infection of the mouth and esophagus",
"nausea",
"severe diarrhea",
"body aches",
"bloating",
"bad breath",
"indigestion",
"photosensitivity",
"redness",
"swelling",
"thoughts of suicide",
"absence of menstruation and weight gain",
"severe pain",

"nasal congestion",
"sweating",
"mucus in stool",
"pelvic pain",
"night sweats",

"bleeding mucus membranes",
"vaginal candidiasis",
"weakness",
"enlarged spleen",
"breast pain",

"dehydration"

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
	
	<c:if test="${empty cuid }">
      var timerId = setInterval(
			function(){
				var data = {};
				$.post('Controller?action=getChatUid', data, function(res) {
					console.log("slot:"+res);
					if(res.indexOf("error")==-1){
				        $(location).attr('href',"welcome.html?cuid="+res);
						console.log("symptoms response:" + res);
						return;
				}
					console.log("symptoms response:" + res);
				});
				},3000
			);
	</c:if>
/* 	function loadIframe(iframeName, url) {
	    var $iframe = $('#' + iframeName);
	    if ( $iframe.length ) {
	        $iframe.attr('src',url);   
	        return false;
	    }
	    return true;
	} */
</script>
</html>