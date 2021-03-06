<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/style.css">

<script src="js/main.js"></script><!-- 마우스효과 구현 -->
</head>

<body>
    <div class="wrapper">
        <div class="top"></div>
        <div class="content">
            <table>
	            <colgroup>
				    <col width="10%"/>
				    <col width="10%"/>
				    <col width="50%"/>
				    <col width="10%"/>				 
				    <col width="10%"/>
				    <col width="10%"/>
				 </colgroup>
                <tr>
                    <th>번호</th>
                    <th>제목</th>         
                    <th>내용</th> 
                    <th>글쓴이</th>        
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>

                <c:forEach var="dto" items="${dtos}">
					<tr>
						<td>${dto.bid}</td>
						<td>
							<c:forEach begin="1" end="${dto.bindent}">[Re]</c:forEach>
							<a href="content_view.do?bid=${dto.bid}" class="link" >${dto.btitle}</a>
						</td>
						<td class="text">
							<a href="content_view.do?bid=${dto.bid}" class="link">${dto.bcontent}</a>
						</td>
						<td>${dto.bname}</td>
						<td style="font-size:12px">${dto.bdate}</td>
						<td>${dto.bhit}</td>				
					</tr>
				</c:forEach>
		
				<tr>
					<td colspan="6" class="write"> <a href="write_view.do">글 작성</a></td>
				</tr>

            </table>
        </div>
    </div>
</body>

</html>