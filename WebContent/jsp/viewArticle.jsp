<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>글 보기</title>
 	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
 	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#replyBtn").click(function(){
				const id=$.cookie("id");
				if(id){
					$('#replyWriterInput').val(id);
					$("#replyDiv").css("display","block");
				}else{
					alert("로그인부터 하세요");
					window.close();
				}				
			});
			
			$("#replyInsertBtn").click(function(){
				const id=$("#replyWriterInput").val();
				const title=$("#replyTitle").val();
				const content=$("#replyContent").val();
				const parentNO=$("#articleNO").val();
					$.post('main',
							{
								sign:'replyInsert',
								id,
								title,
								content,
								parentNO
							},
							function(data){
								alert(data);
								location.replace("html/boardList.html");
							}
					);		
			});
			
			
		});//end ready
	 
	</script>
</head>
<body>
<h1 style="text-align:center">글 보기</h1>
  
  	<input type="hidden" id="articleNO" value='${vo.getArticleNO() }' > 
    <table border="0" align="center">
     <tr>
	   <td align="right">글제목: </td>
	   <td colspan="2"><input type="text" size="67"  maxlength="500" name="title" value='${vo.getTitle() }' disabled="disabled" /></td>
	 </tr>
	 <tr>
		<td align="right" valign="top"><br>글내용: </td>
		<td colspan=2><textarea name="content" rows="10" cols="65" maxlength="4000" disabled="disabled">${vo.getContent() }</textarea> </td>
     </tr>
     <tr>
        <td align="right">첨부된 이미지파일:  </td>
	     <td> ${vo.getImageFileName() }</td>
         <td><img  id="preview" src="#"   width=100 height=100/></td>
	 </tr>
	 <tr>
	    <td align="right"> </td>
	    <td colspan="2">
	       <input type="button" value="글수정" />
	       <input type="button" value="댓글 달기" id="replyBtn" />
	       <input type=button value="목록보기" onClick="window.history.back()" />
	    </td>
     </tr>
    </table>
    
    <div style='padding:20px;display:none;background:#ccccff' id="replyDiv">
    	<table>
    		<tr><td>댓글 작성자</td><td><input id='replyWriterInput' disabled="disabled"></td></tr>
    		<tr><td>댓글 제목</td><td><input id="replyTitle"></td></tr>
    		<tr><td>댓글 내용</td><td><textarea rows='3' cols="65" id="replyContent"></textarea></td></tr>
    	</table>
    	<center><button id='replyInsertBtn'>댓글 등록</button></center>
    </div>
    

</body>
</html>





