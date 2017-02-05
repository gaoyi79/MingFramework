/**
 * 
 */
function fnLoad(obj){
	var id = obj.id;
	var imgindex = parseInt("0"+id.slice(5));
	
	var images = document.getElementsByTagName("img");
	for(var i=0;i<images.length;i++){
		var imagesindex = parseInt("0"+images[i].id.slice(5));
		if(images[i] != null){
			if(imgindex - 5<=imagesindex && imagesindex<imgindex + 20){
				images[i].src = images[i].getAttribute("lazysrc");
			}else{
				images[i].src = "../image/lazy.gif";
			}
		}
	}
}