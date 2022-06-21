 window.addEventListener("load",function(){
	    	
	var link = document.getElementsByClassName("link");
	
	console.log(link[0]);
	console.log(link[1]);
	for(var i=0; i<link.length; i++){
		link[i].idx=i;
		link[i].addEventListener("mouseover",function(e){
			var n = e.currentTarget.idx;
			
			for(var j=0; j<link.length; j++){
				if(j == n) {
					link[j].style.color="blue";
				}
			}
			
		});
		
		link[i].addEventListener("mouseleave",function(e){
			var n = e.currentTarget.idx;
			
			for(var j=0; j<link.length; j++){
				if(j == n) {
					link[j].style.color="#000";
				}
			}
			
		});
	}
	
});