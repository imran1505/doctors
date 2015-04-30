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
                            <!-- <li><a data-scroll href="about.html">About</a></li> -->
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
    
    <div id="content" style="margin:120px">
		<div>
			<div>
				<!-- <a href="index.html"><img src="images/globe.jpg" alt="Image"></a> <span></span> -->
			</div>
			<b class="first"><strong>We Have Free Service For Everyone</strong></b>
			<p>
				Our website brings an idea of making world a healthy place. Any one from remote as well can get him diagnosed. A place where doctors get to know his patient better.
			</p>
			<b>We Have Other Servies Coming In Future</b>
			<p>
				Having some problems? Just Register and get yourself diagnosed <a href="home.html/">Register for free</a> and find what you're looking for. But if you don't find any service you can use, you can try to <a href="contact.html">Contact Us</a> and tell us service and tell us all about it. Maybe you're looking for something different, something special. And we love the challenge of doing something different and something special.
			</p>
			<b>Be Part of Our Community</b>
			<p>
				If you're are a doctor please help the world by providing a service to them. You also can register.
			</p>
		</div>
	</div>
	<div id="footer" style="margin:120px">
		<div>
			<div class="first">
				<h3><a href="index.html">Doctors Portal</a></h3>
				<div>
					<p>
						Telephone. : <span>+91 8609887995</span>
					</p>
					<p>
						Fax : <span> 2345678</span>
					</p>
					<p>
						Email : <span>ask@doctor.com</span>	
					</p>
				</div>
			</div>
			<div>
				<h3>Get Social with us!</h3>
				<p>
					This is just a place holder, so you can see what the site would look like. This is just a place holder, so you can see what the site would look like.
				</p>
				<div>
					<a id="facebook" target="_blank">Facebook</a>
					<a id="twitter" target="_blank">Twitter</a>
					<a id="googleplus" target="_blank">Google&#43;</a>
				</div>
			</div>
			<div>
				<p>
					Copyright &copy; 2015 Doctors Portal<br>All rights reserved
				</p>
			</div>
		</div>

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