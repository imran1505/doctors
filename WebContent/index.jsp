<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header2.jsp"%>
<body id="bigWrapper" data-spy="scroll" data-target=".navbar-default" data-offset="100">

	        <header>
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" data-spy="affix" data-offset-top="50">

                <div class="container-fluid">
                    <div class="navbar-header">
                        <div id="headerFont2">
						<div style="font-size: 36px; font-weight: bold; color: #fff; display: inline">Doctors Portal</div>
						</div>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse navbar-right" id="navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a data-scroll href="">Home</a></li>
                            <li><a data-scroll href="about.html">About</a></li>
							<li><a data-scroll href="home.html">Login</a></li>
                            <li class="dropdown">
   								<a href="#" class="dropdown-toggle" data-toggle="dropdown">Sing Up<b class="caret"></b></a>
									<ul class="dropdown-menu">
      										<li><a href="patient.html">Patients</a></li>
      										<li class="divider"></li>
      										<li><a href="doctor.html">Doctors</a></li>
					       			</ul>
    						</li>
    						<li><a data-scroll href="contact.html">Contact</a></li>

                        </ul>
                    </div>
                </div>
            </nav>
        </header>
	
	<div class="owl-carousel">
          <div class="item"><img src="images/doctor1.jpg" alt="image"></div>
          <div class="item"><img src="images/doctor2.jpg" alt="image"></div>
          <div class="item"><img src="images/doctor3.jpg" alt="image"></div>
    </div>
	<!-- <section class="black-section" style="color:white !important">
	
	
		<a style="color:white !important" href="home.html">Login as Doctor or Patient</a>
		<br>
		<br>
		<a style="color:white !important" href="doctor.html">Sing Up for Doctors</a>
		<br>
		<br>
		<a style="color:white !important" href="patient.html">Sing Up for Patients</a>
		<br>
		<br>
		<a style="color:white !important" href="recover.html">Recover you Password</a>
		
	</section> -->
	<script type="text/javascript">

		$(document).ready(function() {
		
		  var owl = $('.owl-carousel');
		    owl.owlCarousel({
		    items:1,
		    loop:true,
		    margin:10,
		    lazyLoad: true,
		    autoplay:true,
		    autoplayTimeout:3000,
		  });
		
		  $('.loop').owlCarousel({
		      items:2,
		      center: true,
		      loop:true,
		      margin:10,
		      responsive:{
		      0:{
		          items:1
		      },
		      600:{
		          items:3
		      },
		      1000:{
		          items:5
		      }
		      }
		  });
		
		
		});
		
	</script> 
</body>
</html>