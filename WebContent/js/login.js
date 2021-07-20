$(document).ready(function(){
		$('#loginBtn').click(function(){
			
			const id=$('#id').val();
			const pw=$('#pw').val();
			alert(id+":"+pw);
			
			$.post("main",
					  {		
						sign:"login",
					    id,
					    pw
					  },
					  function(data, status){
						  data=JSON.parse(data);
						  console.log(data);
						  if(data.msg){//fail
							  alert(data.msg);
						  }else{//ok data={"id":"a"}
							 
						  	  $.cookie("id",data.id,{path:'/'});
						  	  
						  	  document.getElementById("welcomeMsg").innerHTML=data.id+"님 환영합니다 <button id='logoutBtn'>로그아웃</button> <button id='memberDeleteBtn'>회원탈퇴</button>";
						  }//end else						
					    
					  }//end function
			
			);//end post			
			
		});//end click
		
	});//end ready
	
	
	
	