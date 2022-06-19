<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="wrapper">
        <div class="top"></div>
        <div class="content">
            <table  cellpadding="0" cellspacing="0" border="1">
				<form action="write.do" method="post">
					<tr>
						<td>제목</td>
						<td><input type="text" name="btitle" style="width:98%"></td>
					</tr>
					
					<tr>
						<td>글쓴이</td>
						<td><input type="text" name="bname" style="width:98%"></td>
					</tr>
					
					<tr>
						<td>내용</td>
						<td><textarea name="bcontent" rows="10" style="width:98%"></textarea></td>
					</tr>						
					<tr>
						<td colspan="2"> <input type="submit" value="입력">
						&nbsp;&nbsp;<a href="list.do">목록보기</a></td>
					</tr>		
				</form>
			</table>
        </div>
    </div>
	
</body>
</html>