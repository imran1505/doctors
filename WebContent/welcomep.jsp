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
	<c:if test="${not empty cuid}">
	    <div style="text-align: center" class="ui-widget">
		<div class="iframe" style="display:hidden;">
    			<iframe name="ifrm" id="ifrm" src="http://localhost:9090/?name=${name}&id=${cuid}" frameborder="0">
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
"Mouth Sores",
"loss of energy",
"vertigo",
"Enlarged Liver",
"Painful Intercourse",
"pale stools",
"breast tenderness",
"chest pain",
"mania",
"helplessness",
"Hunger",
"Diarrhea",
"easy bruising",
"facial pain",
"back pain",
"jaundice",
"vision changes",
"Vaginal Infections",
"abdominal swelling",
"sweat",
"low backache",
"Dry Mouth",
"Abdominal Pain",
"increased urination",
"eye pain",
"double vision",
"Leukoplakia of the Tongue",
"Leg Swelling",
"self-loathing",
"Easy Bruising",
"Muscle Cramps",
"paralysis",
"pelvic pain",
"burning",
"coma",
"hair loss",
"Elevated Heart Rate",
"black stools",
"hopelessness",
"Fungal Nails",
"Increased Thirst",
"sneezing",
"loss of appetite",
"joint pain",
"blood in urine",
"muscle aches",
"Bone Pain",
"chills",
"red eyes",
"changes in skin color",
"memory problems",
"tiredness",
"Weight Loss",
"painful intercourse",
"numbness",
"Fainting",
"guilt",
"muscle spasms",
"coughing",
"Itching Skin",
"Blood in the Urine",
"painful lymph nodes",
"stress",
"Bloating",
"weakness",
"cough",
"shoulder pain",
"facial swelling",
"burning when trying to urinate",
"Slow-Healing Sores",
"Heavy Menstrual Periods",
"joint pains",
"seizures",
"shivering",
"Headache",
"rectal pain",
"dark urine",
" itchy and watery eyes",
"shortness of breath",
"Abdominal Distension",
"leg swelling",
"Fatigue",
"weight loss",
"leg cramps",
"headaches",
"earache",
"Blood in Urine",
"frequent infections",
"Abnormal Vaginal Bleeding",
"upper abdominal pain",
"white patches in the throat",
"lack of interest in daily activities",
"Dark Stool",
"headache",
"scratchy or sore throat",
"frequent urination",
"Joint Pain",
"Constipation",
"Flatulence",
"Swollen Lymph Nodes",
"red rashses",
"Abdominal Mass",
"muscle pain",
"Change in Stool Consistency",
"rapid heart rate",
"Vaginal Discharge",
"wheezing",
"acne",
"stuffy or runny nose",
"poor appetite",
"purplish skin",
"Watery Stool",
"Night Sweats",
"Weight loss",
"feelings of worthlessness",
"Bleeding After Sexual Intercourse",
"vaginal irritation",
"Malignancies",
"confusion",
"breast swelling",
"Rash",
"Increased Susceptibility to Infection",
"Low Back Pain",
"Confusion",
"gait disturbances",
"depression",
"Bleeding Between Periods",
"gum disease",
"Reddened Skin",
"cloudy urine",
"Testicular Pain",
"vaginal discharge",
"darkened areolas",
"Vomiting",
"anemia",
"sore muscles",
"Sweat",
"abdominal pain",
"Flank Pain",
"tingling",
"vaginal itching",
"irritation",
"vaginal burning",
"Nausea",
"muscle pains",
"absent menstrual periods",
"Appear pale",
"heat intolerance",
"Dizziness",
"Seizures",
"loss of bladder control",
"Frequent Urination",
"Decreased Urine Output",
"dizziness",
"Coma",
"vomiting",
"weight gain",
"discomfort",
"sleep problems",
"mood changes",
"fever",
"fatigue",
"Bleeding Gums",
"Loss of Muscle Control",
"Abdominal Cramping",
"Sore Throat",
"cold",
"Shortness of Breath",
"jaw pain",
"runny nose",
"difficulty speaking",
"diarrhea",
"Blurred Vision",
"urinary urgency",
"Scrotal Pain",
"mouth ulcers",
"Chest Pain",
"Nosebleeds",
"flushing",
"implantation bleeding",
"Yeast Infections",
"Blood in Stool",
"blood in sputus",
"sore throat",
"Abdominal Tenderness",
"palpitations",
"early satiety",
"psychosis",
"Fever",
"incontinence",
"constipation",
"change in consciousness",
"Fungal Infection of the Mouth and Esophagus",
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
"Weight Gain",
"nasal congestion",
"sweating",
"Mucus in Stool",
"Pelvic Pain",
"night sweats",
"rash",
"bleeding mucus membranes",
"Vaginal Candidiasis",
"Weakness",
"Enlarged Spleen",
"breast pain",
"irritability",
"Dehydration"

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