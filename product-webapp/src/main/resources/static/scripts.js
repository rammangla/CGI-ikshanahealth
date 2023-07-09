<script type="text/javascript">
function changetype(){
	var state = document.getElementById("readmore");
	var content = document.getElementById("more");
	if (state.innerHTML=="+"){
		state.innerHTML="-";
		content.style.display="inherit";
	}
	else{
		state.innerHTML="+";
		content.style.display="none";
	}
}
</script>
;