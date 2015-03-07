/**
 * 
 */
var divDisplay=false;
function toggleDivDisplay(){
	console.log("clicked ");
	if(!divDisplay){
		document.getElementById('navDropDown').style.display='block';
		divDisplay=true;
		console.log("setting true ");
	}else{
		document.getElementById('navDropDown').style.display='none';
		divDisplay=false;
		console.log("setting false ");
	}
	
}